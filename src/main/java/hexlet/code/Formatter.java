package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.formatters.Json;
import hexlet.formatters.Plain;
import hexlet.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> recordsData, String format)
            throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.formatStylish(recordsData);
            case "plain" -> Plain.formatPlain(recordsData);
            case "json" -> Json.formatJson(recordsData);
            default -> throw new IllegalArgumentException("This format is not supported. Supported formats: stylish");
        };
    }
}
