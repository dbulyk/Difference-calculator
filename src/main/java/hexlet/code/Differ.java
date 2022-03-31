package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.Formatter.formatter;

public class Differ {
    public static String getDifference(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> fileData1 = Parser.parse(filepath1);
        Map<String, Object> fileData2 = Parser.parse(filepath2);
        Map<String, Map<String, Object>> recordsData = new TreeMap<>();

        Set<String> keys = new TreeSet<>(fileData1.keySet());
        keys.addAll(fileData2.keySet());
        keys.forEach(s -> {
            if (!fileData2.containsKey(s)) {
                Map<String, Object> record = new TreeMap<>();
                record.put("oldValue", fileData1.get(s));
                record.put("newValue", "");
                recordsData.put(s, record);
            } else if (!fileData1.containsKey(s)) {
                Map<String, Object> record = new TreeMap<>();
                record.put("oldValue", "");
                record.put("newValue", fileData2.get(s));
                recordsData.put(s, record);
            } else if (fileData1.containsKey(s) && fileData2.containsKey(s)
                    && fileData1.get(s) == null && fileData2.get(s) != null) {
                Map<String, Object> record = new TreeMap<>();
                record.put("oldValue", null);
                record.put("newValue", fileData2.get(s));
                recordsData.put(s, record);
            } else if (fileData1.containsKey(s) && fileData2.containsKey(s)
                    && fileData1.get(s) != null && fileData2.get(s) == null) {
                Map<String, Object> record = new TreeMap<>();
                record.put("oldValue", fileData1.get(s));
                record.put("newValue", null);
                recordsData.put(s, record);
            } else if (fileData2.containsKey(s) && fileData2.get(s).equals(fileData1.get(s))) {
                Map<String, Object> record = new TreeMap<>();
                record.put("oldValue", fileData1.get(s));
                record.put("newValue", fileData1.get(s));
                recordsData.put(s, record);
            } else if (fileData1.containsKey(s) && fileData2.containsKey(s)
                    && (!fileData1.get(s).equals(fileData2.get(s)))) {
                Map<String, Object> record = new TreeMap<>();
                record.put("oldValue", fileData1.get(s));
                record.put("newValue", fileData2.get(s));
                recordsData.put(s, record);
            }
        });
        return formatter(recordsData, format);
    }
}
