package hexlet.formatters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Plain {
    public static String formatPlain(Map<String, Map<String, Object>> recordsData) {
        StringBuilder res = new StringBuilder();
        recordsData.forEach((k, v) -> {
            switch (v.get("diff").toString()) {
                case "removed" -> res.append("Property '").append(k).append("' was removed\n");
                case "added" -> res.append("Property '").append(k)
                        .append("' was added with value: ").append(isArray(v.get("value"))).append("\n");
                case "updated" -> res.append("Property '").append(k).append("' was updated. From ")
                        .append(isArray(v.get("oldValue"))).append(" to ")
                        .append(isArray(v.get("newValue"))).append("\n");
                default -> res.append("");
            }
        });
        return res.toString().trim();
    }

    public static String isArray(Object object) {
        if (object instanceof Object[] || object instanceof Collections || object instanceof Map
                || object instanceof ArrayList<?>) {
            return "[complex value]";
        } else if (object instanceof String) {
            return "'" + object + "'";
        } else if (object == null) {
            return null;
        }
        return object.toString();
    }
}
