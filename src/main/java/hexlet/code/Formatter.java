package hexlet.code;

import java.util.Map;

public class Formatter {
    public static String formatter(Map<String, Map<String, Object>> stringData, String format) {
        if (format.equals("stylish")) {
            return stylishFormat(stringData);
        } else {
            throw new IllegalArgumentException("This format is not supported. Supported formats: stylish");
        }
    }

    public static String stylishFormat(Map<String, Map<String, Object>> stringData) {
        StringBuilder res = new StringBuilder("{\n");
        stringData.forEach((k, v) -> {
            if (v.get("oldValue") == null || v.get("newValue") == null
                    || !v.get("oldValue").equals(v.get("newValue"))
                    && !v.get("oldValue").equals("") && !v.get("newValue").equals("")) {
                res.append("   - ").append(k).append(": ").append(v.get("oldValue")).append("\n");
                res.append("   + ").append(k).append(": ").append(v.get("newValue")).append("\n");
            } else if (v.get("oldValue").equals("")) {
                res.append("   + ").append(k).append(": ").append(v.get("newValue")).append("\n");
            } else if (v.get("newValue").equals("")) {
                res.append("   - ").append(k).append(": ").append(v.get("oldValue")).append("\n");
            } else if (v.get("oldValue").equals(v.get("newValue"))) {
                res.append("     ").append(k).append(": ").append(v.get("newValue")).append("\n");
            }
        });
        res.append("}");
        return res.toString();
    }
}
