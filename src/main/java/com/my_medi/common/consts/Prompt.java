package com.my_medi.common.consts;

public class Prompt {

    public static final String TEXT = "text";
    public static final String TYPE_IMAGE_URL = "image_url";
    public static final String IMAGE_URL_URL = "data:image/jpeg;base64,";
    public static final String MODEL_GPT_4O = "gpt-4o";
    public static final String ROLE_USER = "user";
    public static final int MAX_TOKEN = 2000;
    public static final double TEMPERATURE = 0.1;
    public static String IMAGE_PARSING_PROMPT = """
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
                "protein": "정상 | 경계 | 단백뇨 의심",
              },
              "imagingTest": {
                "chestXray": "정상 | 비활동성 폐결핵 | 질환 의심 | 기타",
              }
            }
            
            규칙:
            - 'urineTest.protein'과 'imagingTest.chestXray'는 **반드시 문자열(String)** 로 반환합니다.
            - 각각 위에 제시된 옵션 문자열 중 하나만 반환하세요. (예: "정상")
            - 해당 정보를 확정할 수 없으면 null을 사용하세요.
            - 숫자 값은 반드시 숫자만 추출하고, 없는 정보는 null로 표시해주세요.
            """;

    public static String HEALTH_TERM_PROMPT = """
                    한국어로 답하세요. 유치원생도 이해할 수 있게 아주 쉽게 설명하세요.
                    진단/처방은 하지 말고 일반 정보만 제공하세요.
                    아래 JSON 형식으로만 출력하세요. 추가 텍스트 금지.
            
                    {
                      "term": "string",          // 한글용어(영어번역) 형태로 출력. 예시출력 : 혈색소(Hemoglobin)
                      "shortDesc": "string", // 용어 한 줄 요약. 예시 출력 : 피 속 산소 배달부가 부족하지 않은지 확인해요!
                      "description": "string",     // 2-3문장 요약 ("용어? 용어설명") 형식으로 출력. 혈색소?  내 피가 산소를 얼마나 잘 나르고 있는지를 알려주는 지표예요.
                      "normalRange": "string|null", // 정상범위(있으면 단위 포함, 없으면 null) 예시 출력 : 수치가 너무 낮으면 빈혈일 수 있고, 어지럽거나 피곤한 이유가 될 수 있어요. 보통 여자는 12g/dL 이상, 남자는 13g/dL 이상이면 괜찮다고 해요. 민약 너무 낮으면, 철분이 든 음식(예: 소고기, 시금치, 간, 달걀 등)을 먹거나 병원에서 치료를 받기도 해요.
                    }
            
                    설명할 용어: "%s"
            """;

    public static String HEALTH_TERM_FALLBACK = """
                    해당 용어들을, 너가 초등학생 6학년한테 설명한다고 생각하고 최대한 쉽게 설명해주세요.
                    형식은 다음과 같이 출력하세요.\s
                    ② 당화혈색소 (HbA1c): "지난 3개월 동안 단 거 얼마나 먹었는지 알려줘요!" 당화혈색소는요, 최근 2~3개월 동안 내 피 속에 설탕이 얼마나 많았는지를 알려주는 검사예요.오늘만 좋은 게 아니라, 지금까지 얼마나 잘 관리했는지 보는 거예요.이 수치가 5.7% 이하면 정상, 그보다 높으면 몸이 많이 힘들었을 수 있어요. 이정도 길이와 어투로 설명해주세요.
                    진단/처방 금지. 불확실하면 수치 언급은 피하세요.
                    용어: %s
            """;

}
