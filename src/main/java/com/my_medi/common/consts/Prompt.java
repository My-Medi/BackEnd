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
}
