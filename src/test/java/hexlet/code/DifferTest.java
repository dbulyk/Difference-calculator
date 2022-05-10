package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {
    @Test
    void testWithEmptyFile() {
        Throwable thrownEmptyFile = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/fileEmpty.json", "stylish"));
        Assertions.assertEquals(thrownEmptyFile.getMessage(),
                "Comparison with an empty file is not allowed.");
    }

    @Test
    void testWithNotSupportedFormat() {
        Throwable thrownNotSupportedFile = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.generate("src/test/resources/file1.yml",
                        "src/test/resources/file.txt", "stylish"));
        Assertions.assertEquals(thrownNotSupportedFile.getMessage(),
                "This file extension is not supported. Supported extensions: json, yml");

        Throwable thrownNotSupportedFormat = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Differ.generate("src/test/resources/file1.yml",
                        "src/test/resources/file2.yml", "test"));
        Assertions.assertEquals(thrownNotSupportedFormat.getMessage(),
                "This format is not supported. Supported formats: stylish, plain, json");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testFormatJson(String extension) throws IOException {
        String expectedJson = Files.readString(Path.of("src/test/resources/result_json.json"));
        Assertions.assertEquals(expectedJson,
                Differ.generate("src/test/resources/file1." + extension,
                        "src/test/resources/file2." + extension, "json"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testFormatPlain(String extension) throws IOException {
        String expectedJson = Files.readString(Path.of("src/test/resources/result_plain.txt"));
        Assertions.assertEquals(expectedJson,
                Differ.generate("src/test/resources/file1." + extension,
                        "src/test/resources/file2." + extension, "plain"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testFormatStylish(String extension) throws IOException {
        String expectedJson = Files.readString(Path.of("src/test/resources/result_stylish.txt"));
        Assertions.assertEquals(expectedJson,
                Differ.generate("src/test/resources/file1." + extension,
                        "src/test/resources/file2." + extension, "stylish"));
    }
}
