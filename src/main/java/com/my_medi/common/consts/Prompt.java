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

    public static final String TOTAL_REPORT_PROMPT = """
            당신은 건강검진 리포트를 한국어로 요약합니다.
            아래의 [입력 데이터]만 근거로, 다음 JSON 스키마에 **정확히** 맞춰 응답하세요.
            
            - 반드시 [입력 데이터]의 사실만 사용하세요.
            - 예시 문구/상투어를 재사용하지 마세요.
            - 근거가 없으면 그 항목은 빈 배열([]) 또는 null로 남기세요.
            - JSON 외의 설명 금지.
            
            {
              "majorAbnormalItems": ["문장", ... 최대 6개],
              "lifestyleAdvice": ["식사", "운동", "카페인/음주 습관", "수면" 각 1문장],
              "top3Risks": [
                  { "rank": 1, "title": "위험명", "indicators": "관련 지표", "description": "설명" },
                  { "rank": 2, "title": "위험명", "indicators": "관련 지표", "description": "설명" },
                  { "rank": 3, "title": "위험명", "indicators": "관련 지표", "description": "설명" }
              ],
              "summary": "4문장으로 요약",
              "recommendedActions": ["행동 권장 문구1","행동 권장 문구2","행동 권장 문구3"]
            }
            스키마:
                {
                  "majorAbnormalItems": [
                    "공복혈당  :  107 mg/dL  →  정상기준 (70~99)을 초과해 당뇨 전단계로 분류됩니다.",
                    "LDL콜레스테롤  : 153 mg/dL  →  경계 수치로, 심혈관 질환 위험이 조금씩 생기기 시작한 상태에요.",
                    "중성지방  :  186 mg/dL  →  높은 편으로, 특히 식습관과 관련이 많아요.",
                    "ALT (간 수치)  :  42 IU/L  →  간 기능 이상을 의심할 수 있어요.",
                    "BMI  :  26.1  →  과체중 범위로, 체중관리가 필요할 수 있어요.",
                    "혈압  :  132/87 mmHg  →  고혈압 전단계입니다."
                  ],
                  "lifestyleAdvice": [
                    "야식이나 당류 위주의 식사가 잡다면, 공복혈당과 중성지방 수치를 올릴 수 있어요. 하루 1끼 채소 중심 식단으로 바꿔보는 건 어떨까요?",
                    "현재 활동량이 적다면, 걷기부터라도 시작해보세요. 주 3회 이상, 30분 정도의 유산소 운동이 간 수치와 혈당 조절에 큰 도움이 돼요.",
                    "음주나 잦은 카페인 섭취는 간 수치와 체중 증가에 영향을 줄 수 있어요. 음주는 주 1회 이하, 하루 1~2잔 이내로 조절하는 걸 추천드려요.",
                    "만성피로를 느끼시나요? 수면 시간이 부족하면 혈압과 혈당 모두 영향을 받을 수 있어요. 매일 6~7시간 이상의 숙면이 중요해요."
                  ],
                  "top3Risks": [
                        { "rank": 1, "title": "대사증후군", "indicators": "(공복혈당 ↑, 중성지방 ↑, HDL ↓, 복부비만(BMI 기준))", "description": "여러 지표가 함께 경고를 보내고 있어요. 특히 HDL(좋은 콜레스테롤) 수치가 낮은 것은 체내 지방 대사에 문제가 있다는 신호입니다. 지금부터 식단 개선과 유산소 운동이 필요해요!" },
                        { "rank": 2, "title": "알코올성 지방간", "indicators": "(AST/ALT ↑, 감마-GTP ↑)", "description": "간 수치가 전반적으로 높고, 술을 거의 마시지 않는다면 지방간 가능성을 의심할 수 있어요.  기름진 음식, 야식 등을 줄이고 간 해독을 위한 영양관리도 필요해요." },
                        { "rank": 3, "title": "만성 신장 기능 저하 위험", "indicators": "(크레아티닌 ↑, eGFR ↓)", "description": "현재로선 심각한 단계는 아니지만, 사구체여과율(eGFR) 이 살짝 낮아요. 평소 수분 섭취량과 단백질 섭취량을 점검하고, 앞으로도 꾸준한 모니터링이 필요해요." }
                      ],
                  "summary": "전체적으로 보면, 지금은 건강을 다시 한 번 점검해야 할 시기인 것 같아요. 아직은 대부분 경고 단계이지만 관리로 충분히 회복 가능한 상태입니다. 특히 혈당, 중성지방, 간 수치가 동시에 높게 나온 경우는 생활 습관과 식단의 영향을 강하게 받고 있다는 신호예요!! 지금부터 차근차근 바꿔나간다면 건강을 충분히 회복할 수 있어요!",
                  "recommendedActions": [
                              "영양사와 상담을 통해 식단 가이드를 받아보시고,",
                              "운동처방사와 함께 운동 루틴을 짜보는 것도 좋아요.",
                              "향후 6개월 뒤 재검진을 통해 변화된 수치를 체크해보는 걸 추천드려요!"
                            ]
                }
            
            
            [입력 데이터]
               ```json
               %s
            """;

}
