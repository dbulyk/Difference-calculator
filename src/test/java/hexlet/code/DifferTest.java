package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DifferTest {
    @Test
    void generateTest() throws IllegalArgumentException, IOException {
        Throwable thrownEmptyFile = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.getDifference("src/test/resources/file1.json", "src/test/resources/fileEmpty.json"));
        Assertions.assertEquals(thrownEmptyFile.getMessage(),
                "Comparison with an empty file is not allowed. Empty file: src/test/resources/fileEmpty.json");

        Throwable thrownNotSupportedFile = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.getDifference("src/test/resources/file1.yaml", "src/test/resources/file.txt"));
        Assertions.assertEquals(thrownNotSupportedFile.getMessage(),
                "This file format is not supported. Supported formats: json, yaml");

        String expected1 = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        Assertions.assertEquals(expected1,
                Differ.getDifference("src/test/resources/file1.json", "src/test/resources/file2.json"));
        Assertions.assertEquals(expected1,
                Differ.getDifference("src/test/resources/file1.yaml", "src/test/resources/file2.yaml"));


        String expected2 = """
                {
                   follow: false
                   host: hexlet.io
                   proxy: 123.234.53.22
                   timeout: 50
                }""";
        Assertions.assertEquals(expected2,
                Differ.getDifference("src/test/resources/file1.json", "src/test/resources/file3.json"));
        Assertions.assertEquals(expected2,
                Differ.getDifference("src/test/resources/file1.yaml", "src/test/resources/file3.yaml"));
    }
}
