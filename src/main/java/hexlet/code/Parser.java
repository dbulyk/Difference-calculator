package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String data, String format) throws IllegalArgumentException,
            JsonProcessingException {
        ObjectMapper mapper = getObjectMapper(format);

        if (data.isEmpty()) {
            throw new IllegalArgumentException("Comparison with an empty file is not allowed.");
        }
        return mapper.readValue(data, new TypeReference<>() { });
    }

    private static ObjectMapper getObjectMapper(String extension) {
        if (extension.equals("yml")) {
            return new ObjectMapper(new YAMLFactory());
        } else if (extension.equals("json")) {
            return new ObjectMapper();
        } else {
            throw new IllegalArgumentException("This file extension is not supported. "
                    + "Supported extensions: json, yml");
        }
    }
}
