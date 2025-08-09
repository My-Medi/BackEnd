package com.my_medi.infra.discord.service;

import com.my_medi.infra.discord.client.DiscordClient;
import com.my_medi.infra.discord.dto.ApiModificationRequest;
import com.my_medi.infra.discord.dto.DiscordWebhookErrorMessage;
import com.my_medi.infra.discord.dto.DiscordWebhookErrorMessage.Embed;
import com.my_medi.infra.discord.dto.DiscordWebhookMessage;
import io.swagger.v3.oas.models.PathItem;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.my_medi.common.util.DiscordMessageUtil.*;

@Service
@RequiredArgsConstructor
public class DiscordWebhookServiceImpl implements DiscordWebhookService{

    private final Environment env;
    private final RestTemplate restTemplate;
    @Override
    public void sendApiModificationRequest(String apiEndpoint, PathItem.HttpMethod httpMethod, ApiModificationRequest request) {
        try {
            DiscordWebhookMessage message = createWebhookMessage(apiEndpoint, httpMethod, request);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<DiscordWebhookMessage> entity = new HttpEntity<>(message, headers);

            restTemplate.exchange(env.getProperty("discord.webhook.url"), HttpMethod.POST, entity, String.class);

        } catch (Exception e) {
            // 로깅 처리
            System.err.println("Discord webhook 전송 실패: " + e.getMessage());
        }
    }

    private DiscordWebhookMessage createWebhookMessage(String apiEndpoint,
                                                       PathItem.HttpMethod httpMethod,
                                                       ApiModificationRequest request) {
        // 우선순위에 따른 색상 설정
        int color = getColorByPriority(request.getPriority());

        // 필드 생성
        List<DiscordWebhookMessage.Embed.Field> fields = new ArrayList<>();
        fields.add(new DiscordWebhookMessage.Embed.Field("API 엔드포인트", "`" + apiEndpoint + "`", false));
        fields.add(new DiscordWebhookMessage.Embed.Field("API HTTP method", "`" + httpMethod.name() + "`", false));
        fields.add(new DiscordWebhookMessage.Embed.Field("요청자", request.getRequesterName(), true));
        fields.add(new DiscordWebhookMessage.Embed.Field("우선순위", getPriorityEmoji(request.getPriority()) + " " + request.getPriority(), true));
        fields.add(new DiscordWebhookMessage.Embed.Field("요청 타입", getRequestTypeEmoji(request.getRequestType()) + " " + request.getRequestType(), true));
        fields.add(new DiscordWebhookMessage.Embed.Field("상세 내용", request.getDescription(), false));

        // Embed 생성
        DiscordWebhookMessage.Embed embed = new DiscordWebhookMessage.Embed();
        embed.setTitle("🔧 API 수정 요청");
        embed.setDescription("새로운 API 수정 요청이 접수되었습니다.");
        embed.setColor(color);
        embed.setFields(fields.toArray(new DiscordWebhookMessage.Embed.Field[0]));
        embed.setFooter(new DiscordWebhookMessage.Embed.Footer("API 수정 요청 시스템"));
        embed.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // 메인 메시지 생성
        DiscordWebhookMessage message = new DiscordWebhookMessage();
        message.setContent("@here 새로운 API 수정 요청이 있습니다!");
        message.setEmbeds(new DiscordWebhookMessage.Embed[]{embed});

        return message;
    }

    private final DiscordClient discordClient;
    @Override
    public void sendErrorMessage(Exception e, WebRequest request) {
        discordClient.sendAlarm(createMessage(e, request));
    }

    private DiscordWebhookErrorMessage createMessage(Exception e, WebRequest request) {
        return DiscordWebhookErrorMessage.builder()
                .content("# 🚨 에러 발생 비이이이이사아아아앙")
                .embeds(
                        List.of(
                                Embed.builder()
                                        .title("ℹ️ 에러 정보")
                                        .description(
                                                "### 🕖 발생 시간\n"
                                                        + LocalDateTime.now()
                                                        + "\n"
                                                        + "### 🔗 요청 URL\n"
                                                        + createRequestFullPath(request)
                                                        + "\n"
                                                        + "### 📄 Stack Trace\n"
                                                        + "```\n"
                                                        + getStackTrace(e).substring(0, 1000)
                                                        + "\n```")
                                        .build()
                        )
                )
                .build();
    }

    private String createRequestFullPath(WebRequest webRequest) {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        String fullPath = request.getMethod() + " " + request.getRequestURL();

        String queryString = request.getQueryString();
        if (queryString != null) {
            fullPath += "?" + queryString;
        }

        return fullPath;
    }

    private String getStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
