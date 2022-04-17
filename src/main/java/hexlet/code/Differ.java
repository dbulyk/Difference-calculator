package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Formatter.format;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> fileData1 = readFile(filepath1);
        Map<String, Object> fileData2 = readFile(filepath2);
        Map<String, Map<String, Object>> recordsData = Tree.build(fileData1, fileData2);

        return format(recordsData, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Map<String, Object> readFile(String filepath) throws IOException {
        String format = filepath.substring(filepath.lastIndexOf(".") + 1);
        String data = Files.readString(Paths.get(filepath));
        return Parser.parse(data, format);
    }
}
