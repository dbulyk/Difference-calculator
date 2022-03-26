package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        String file1 = Files.readString(Paths.get(filepath1));
        String file2 = Files.readString(Paths.get(filepath2));

        if (file1.isEmpty() || file2.isEmpty()) {
            return "Both or one of the files are empty, it is impossible to make a comparison";
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> fileData1 = mapper.readValue(file1, new TypeReference<>() { });
        Map<String, Object> fileData2 = mapper.readValue(file2, new TypeReference<>() { });



        String res = getDifference(fileData1, fileData2);
        System.out.println(res);
        return res;
    }

    public static String getDifference(Map<String, Object> fileData1, Map<String, Object> fileData2) {
        Set<String> keys = new TreeSet<>(fileData1.keySet());
        keys.addAll(fileData2.keySet());
        StringBuilder res = new StringBuilder("{\n");
        keys.forEach(s -> {        //Взял forEach т.к. судя по тестам производительности он наиболее быстрый
            if (!fileData2.containsKey(s)) {
                res.append(" - ")
                        .append(s)
                        .append(": ")
                        .append(fileData1.get(s))
                        .append("\n");
            }

            if (!fileData1.containsKey(s)) {
                res.append(" + ")
                        .append(s)
                        .append(": ")
                        .append(fileData2.get(s))
                        .append("\n");
            }

            if (fileData2.containsKey(s) && fileData2.get(s).equals(fileData1.get(s))) {
                res.append("   ")
                        .append(s)
                        .append(": ")
                        .append(fileData1.get(s))
                        .append("\n");
            }

            if (fileData1.containsKey(s) && fileData2.containsKey(s) && !fileData1.get(s)
                    .equals(fileData2.get(s))) {
                res.append(" - ")
                        .append(s)
                        .append(": ")
                        .append(fileData1.get(s))
                        .append("\n");
                res.append(" + ")
                        .append(s)
                        .append(": ")
                        .append(fileData2.get(s))
                        .append("\n");
            }
        });
        res.append("}");
        return res.toString();
    }
}
