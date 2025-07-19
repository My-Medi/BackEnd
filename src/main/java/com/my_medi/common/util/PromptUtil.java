package com.my_medi.common.util;

import com.my_medi.infra.gpt.dto.OpenAIRequest;
import com.my_medi.infra.gpt.dto.OpenAIResponse;
import org.springframework.http.*;

import java.util.List;

public class PromptUtil {
    public static OpenAIRequest extractTextFromImage(String imageBase64, String openAiApiKey) {
        String prompt = """
            이 한국어 건강검진 보고서에서 다음 정보를 JSON 형태로 추출해주세요:
            
            {
              "checkupDate": "검진일자 (YYYY-MM-DD 형식)",
              "round": "검진차수 (숫자)",
              "measurement": {
                "height": "키(cm, 숫자만)",
                "weight": "체중(kg, 숫자만)",
                "bmi": "BMI (숫자만)",
                "waistCircumference": "허리둘레(cm, 숫자만)",
                "vision": "시력",
                "hearing": "청력"
              },
              "bloodPressure": {
                "systolic": "수축기 혈압 (숫자만)",
                "diastolic": "이완기 혈압 (숫자만)",
                "status": "혈압 상태 (정상/고혈압 등)"
              },
              "bloodTest": {
                "hemoglobin": "혈색소(g/dL, 숫자만)",
                "glucose": "공복혈당(mg/dL, 숫자만)",
                "totalCholesterol": "총콜레스테롤(mg/dL, 숫자만)",
                "hdlCholesterol": "HDL콜레스테롤(mg/dL, 숫자만)",
                "ldlCholesterol": "LDL콜레스테롤(mg/dL, 숫자만)",
                "triglycerides": "중성지방(mg/dL, 숫자만)",
                "creatinine": "혈청크레아티닌(mg/dL, 숫자만)",
                "egfr": "eGFR(mL/min/1.73m², 숫자만)",
                "ast": "AST(IU/L, 숫자만)",
                "alt": "ALT(IU/L, 숫자만)",
                "gammaGtp": "감마지티피(IU/L, 숫자만)"
              },
              "urineTest": {
                "protein": "요단백",
                "glucose": "요당"
              },
              "imagingTest": {
                "chestXray": "흉부촬영 결과",
                "pastMedicalHistory": "과거병력",
                "currentMedication": "복용약물"
              },
              "interview": {
                "smoking": "흡연상태",
                "drinking": "음주상태",
                "exercise": "운동상태",
                "familyHistory": "가족력"
              },
              "additionalTest": {
                "hepatitisB": "B형간염",
                "depression": "우울증",
                "cognitiveFunction": "인지기능장애",
                "osteoporosis": "골밀도검사",
                "additionalNotes": "기타 추가 검사"
              }
            }
            
            숫자 값은 반드시 숫자만 추출하고, 없는 정보는 null로 표시해주세요.
            """;

        OpenAIRequest.Content textContent = OpenAIRequest.Content.builder()
                .type("text")
                .text(prompt)
                .build();

        OpenAIRequest.Content imageContent = OpenAIRequest.Content.builder()
                .type("image_url")
                .imageUrl(
                        OpenAIRequest.ImageUrl.builder()
                                .url("data:image/jpeg;base64," + imageBase64)
                                .build()
                )
                .build();

        OpenAIRequest.Message message = OpenAIRequest.Message.builder()
                .role("user")
                .content(List.of(textContent, imageContent))
                .build();

        return OpenAIRequest.builder()
                .model("gpt-4o")
                .maxTokens(2000)
                .temperature(0.1)
                .messages(List.of(message))
                .build();
    }
}
