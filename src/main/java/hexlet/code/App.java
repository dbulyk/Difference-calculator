package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", version = "gendiff 1.0", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format = "stylish";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private static String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private static String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    /**
     * @author Dmitry Bulykin
     * @return String
     */
    @Override
    public String call() {
        try {
            String res = Differ.getDifference(filepath1, filepath2, format);
            System.out.println(res);
            return res;
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
