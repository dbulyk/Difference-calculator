package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String getDifference(String filepath1, String filepath2) throws IOException {
        Map<String, Object> fileData1 = Parser.parse(filepath1);
        Map<String, Object> fileData2 = Parser.parse(filepath2);

        Set<String> keys = new TreeSet<>(fileData1.keySet());
        keys.addAll(fileData2.keySet());
        StringBuilder res = new StringBuilder("{\n");
        keys.forEach(s -> {
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
