package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DifferTest {
    @Test
    void generateTest() throws IllegalArgumentException, IOException {
        Throwable thrownEmptyFile = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.getDifference("src/test/resources/file1.json",
                        "src/test/resources/fileEmpty.json", "stylish"));
        Assertions.assertEquals(thrownEmptyFile.getMessage(),
                "Comparison with an empty file is not allowed. Empty file: src/test/resources/fileEmpty.json");

        Throwable thrownNotSupportedFile = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.getDifference("src/test/resources/file1.yaml",
                        "src/test/resources/file.txt", "stylish"));
        Assertions.assertEquals(thrownNotSupportedFile.getMessage(),
                "This file extension is not supported. Supported extensions: json, yaml");

        Throwable thrownNotSupportedFormat = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.getDifference("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml", "test"));
        Assertions.assertEquals(thrownNotSupportedFormat.getMessage(),
                "This format is not supported. Supported formats: stylish");

        String expected1 = """
                {
                     chars1: [a, b, c]
                   - chars2: [d, e, f]
                   + chars2: false
                   - checked: false
                   + checked: true
                   - default: null
                   + default: [value1, value2]
                   - id: 45
                   + id: null
                   - key1: value1
                   + key2: value2
                     numbers1: [1, 2, 3, 4]
                   - numbers2: [2, 3, 4, 5]
                   + numbers2: [22, 33, 44, 55]
                   - numbers3: [3, 4, 5]
                   + numbers4: [4, 5, 6]
                   + obj1: {nestedKey=value, isNested=true}
                   - setting1: Some value
                   + setting1: Another value
                   - setting2: 200
                   + setting2: 300
                   - setting3: true
                   + setting3: none
                }""";
        Assertions.assertEquals(expected1,
                Differ.getDifference("src/test/resources/file1.json",
                        "src/test/resources/file2.json", "stylish"));
        Assertions.assertEquals(expected1,
                Differ.getDifference("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml", "stylish"));
    }
}
