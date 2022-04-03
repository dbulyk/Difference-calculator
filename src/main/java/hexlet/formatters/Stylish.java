package hexlet.formatters;

import java.util.Map;

public class Stylish {
    public static String formatStylish(Map<String, Map<String, Object>> recordsData) {
        StringBuilder res = new StringBuilder("{\n");
        recordsData.forEach((k, v) -> {
            switch (v.get("diff").toString()) {
                case "removed" -> res.append("   - ").append(k).append(": ").append(v.get("value")).append("\n");
                case "added" -> res.append("   + ").append(k).append(": ").append(v.get("value")).append("\n");
                case "updated" -> {
                    res.append("   - ").append(k).append(": ").append(v.get("oldValue")).append("\n");
                    res.append("   + ").append(k).append(": ").append(v.get("newValue")).append("\n");
                }
                default -> res.append("     ").append(k).append(": ").append(v.get("value")).append("\n");
            }
        });
        res.append("}");
        return res.toString();
    }
}
