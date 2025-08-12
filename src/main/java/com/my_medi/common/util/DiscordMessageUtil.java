package com.my_medi.common.util;

import com.my_medi.infra.discord.dto.DiscordWebhookErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

public class DiscordMessageUtil {
    public static int getColorByPriority(String priority) {
        switch (priority.toUpperCase()) {
            case "HIGH": return 0xFF0000; // 빨간색
            case "MEDIUM": return 0xFFA500; // 주황색
            case "LOW": return 0x00FF00; // 초록색
            default: return 0x808080; // 회색
        }
    }

    public static String getPriorityEmoji(String priority) {
        switch (priority.toUpperCase()) {
            case "HIGH": return "🔴";
            case "MEDIUM": return "🟠";
            case "LOW": return "🟢";
            default: return "⚪";
        }
    }

    public static String getRequestTypeEmoji(String requestType) {
        switch (requestType.toUpperCase()) {
            case "MODIFICATION": return "🔧";
            case "BUG_FIX": return "🐛";
            case "NEW_FEATURE": return "✨";
            default: return "📝";
        }
    }

    public static DiscordWebhookErrorMessage createMessage(Exception e, WebRequest request) {
        return DiscordWebhookErrorMessage.builder()
                .content("# 🚨 에러 발생 비이이이이사아아아앙")
                .embeds(
                        List.of(
                                DiscordWebhookErrorMessage.Embed.builder()
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

    private static String createRequestFullPath(WebRequest webRequest) {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        String fullPath = request.getMethod() + " " + request.getRequestURL();

        String queryString = request.getQueryString();
        if (queryString != null) {
            fullPath += "?" + queryString;
        }

        return fullPath;
    }

    private static String getStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
