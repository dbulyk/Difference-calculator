package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Tree {
    public static Map<String, Map<String, Object>> build(Map<String, Object> fileData1, Map<String, Object> fileData2) {
        Map<String, Map<String, Object>> recordsData = new TreeMap<>();
        Set<String> keys = new TreeSet<>(fileData1.keySet());
        keys.addAll(fileData2.keySet());

        keys.forEach(s -> {
            Map<String, Object> record = new TreeMap<>();
            if (!fileData2.containsKey(s)) {
                record.put("value", fileData1.get(s));
                record.put("diff", "removed");
                recordsData.put(s, record);
            } else if (!fileData1.containsKey(s)) {
                record.put("value", fileData2.get(s));
                record.put("diff", "added");
                recordsData.put(s, record);
            } else if (!Objects.equals(fileData1.get(s), fileData2.get(s))) {
                record.put("oldValue", fileData1.get(s));
                record.put("newValue", fileData2.get(s));
                record.put("diff", "updated");
                recordsData.put(s, record);
            } else {
                record.put("value", fileData1.get(s));
                record.put("diff", "unchanged");
                recordsData.put(s, record);
            }
        });
        return recordsData;
    }
}
