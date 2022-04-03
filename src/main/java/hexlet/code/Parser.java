package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String file) throws IOException, IllegalArgumentException {
        String fileExtension = file.substring(file.lastIndexOf(".") + 1);
        ObjectMapper mapper = getObjectMapper(fileExtension);
        String fileData = Files.readString(Paths.get(file));

        if (fileData.isEmpty()) {
            throw new IllegalArgumentException("Comparison with an empty file is not allowed. Empty file: " + file);
        }
        return mapper.readValue(fileData, new TypeReference<>() { });
    }

    public static ObjectMapper getObjectMapper(String extension) {
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
