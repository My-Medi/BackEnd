package com.my_medi.common.util;

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
}
