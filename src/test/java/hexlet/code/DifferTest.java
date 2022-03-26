package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DifferTest {
    @Test
    void generateTest() throws IOException {
        Assertions.assertEquals("Both or one of the files are empty, it is impossible to make a comparison",
                Differ.generate("src/test/resources/fileEmpty1.json", "src/test/resources/fileEmpty2.json"));
        Assertions.assertEquals("Both or one of the files are empty, it is impossible to make a comparison",
                Differ.generate("src/test/resources/file1.json", "src/test/resources/fileEmpty1.json"));

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
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));

        String expected2 = """
                {
                   follow: false
                   host: hexlet.io
                   proxy: 123.234.53.22
                   timeout: 50
                }""";
        Assertions.assertEquals(expected2,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file3.json"));
    }
}
