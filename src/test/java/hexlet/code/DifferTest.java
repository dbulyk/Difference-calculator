package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DifferTest {
    @Test
    void testWithEmptyFile() {
        Throwable thrownEmptyFile = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/fileEmpty.json", "stylish"));
        Assertions.assertEquals(thrownEmptyFile.getMessage(),
                "Comparison with an empty file is not allowed. Empty file: src/test/resources/fileEmpty.json");
    }

    @Test
    void testWithNotSupportedFormat() {
        Throwable thrownNotSupportedFile = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file.txt", "stylish"));
        Assertions.assertEquals(thrownNotSupportedFile.getMessage(),
                "This file extension is not supported. Supported extensions: json, yaml");

        Throwable thrownNotSupportedFormat = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml", "test"));
        Assertions.assertEquals(thrownNotSupportedFormat.getMessage(),
                "This format is not supported. Supported formats: stylish, plain, json");
    }

    @Test
    void testStylishFormat() throws IOException {
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
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml", "stylish"));
    }

    @Test
    void testPlainFormat() throws IOException {
        String expected2 = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
        Assertions.assertEquals(expected2,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json", "plain"));
    }

    @Test
    void testJsonFormat() throws IOException {
        String expected3 = """
                {
                  "chars1" : {
                    "diff" : "unchanged",
                    "value" : [ "a", "b", "c" ]
                  },
                  "chars2" : {
                    "diff" : "updated",
                    "newValue" : false,
                    "oldValue" : [ "d", "e", "f" ]
                  },
                  "checked" : {
                    "diff" : "updated",
                    "newValue" : true,
                    "oldValue" : false
                  },
                  "default" : {
                    "diff" : "updated",
                    "newValue" : [ "value1", "value2" ],
                    "oldValue" : null
                  },
                  "id" : {
                    "diff" : "updated",
                    "newValue" : null,
                    "oldValue" : 45
                  },
                  "key1" : {
                    "diff" : "removed",
                    "value" : "value1"
                  },
                  "key2" : {
                    "diff" : "added",
                    "value" : "value2"
                  },
                  "numbers1" : {
                    "diff" : "unchanged",
                    "value" : [ 1, 2, 3, 4 ]
                  },
                  "numbers2" : {
                    "diff" : "updated",
                    "newValue" : [ 22, 33, 44, 55 ],
                    "oldValue" : [ 2, 3, 4, 5 ]
                  },
                  "numbers3" : {
                    "diff" : "removed",
                    "value" : [ 3, 4, 5 ]
                  },
                  "numbers4" : {
                    "diff" : "added",
                    "value" : [ 4, 5, 6 ]
                  },
                  "obj1" : {
                    "diff" : "added",
                    "value" : {
                      "nestedKey" : "value",
                      "isNested" : true
                    }
                  },
                  "setting1" : {
                    "diff" : "updated",
                    "newValue" : "Another value",
                    "oldValue" : "Some value"
                  },
                  "setting2" : {
                    "diff" : "updated",
                    "newValue" : 300,
                    "oldValue" : 200
                  },
                  "setting3" : {
                    "diff" : "updated",
                    "newValue" : "none",
                    "oldValue" : true
                  }
                }""";
        Assertions.assertEquals(expected3,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json", "json"));
    }
}
