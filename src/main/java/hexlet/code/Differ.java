package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static Map<String, Object> generate(String filepath1, String filepath2) throws IOException {
        String file1 = Files.readString(Paths.get(filepath1));
        String file2 = Files.readString(Paths.get(filepath2));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mapFile1 = mapper.readValue(file1, new TypeReference<>(){});
        Map<String, Object> mapFile2 = mapper.readValue(file2, new TypeReference<>(){});

        return getDifference(mapFile1, mapFile2);
    }

    public static Map<String, Object> getDifference(Map<String, Object> mapFile1, Map<String, Object> mapFile2) {

        return null;
    }
}
