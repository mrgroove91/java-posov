import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class FilesCollectorExperiments {
    public static void main(String[] args) throws IOException {
        FilesCollector fc = new FilesCollector();
        Path pathToSearch = Paths.get("C:/Users/Yuy/Desktop");
        Files.walkFileTree(pathToSearch, fc);
        List<Path> allFiles = fc.getAllFiles();

    }

}
