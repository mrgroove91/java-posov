import java.util.*;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesCollector implements FileVisitor<Path>{

    List<Path> files = new ArrayList<>();

    public String getExtension(String filename) {
        String extension = "";

        int indexOfLastExtension = filename.lastIndexOf(".");


        // takes the greater of the two values, which mean last file separator
        int indexOfLastSeparator = filename.lastIndexOf("\\");

        // make sure the file extension appear after the last file separator
        if (indexOfLastExtension > indexOfLastSeparator) {
            extension = filename.substring(indexOfLastExtension + 1);
        }

        return extension;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {


        System.out.println("Заходим в каталог " + path.toString());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        String fileName = path.getFileName().toString();
        files.add(path);

        System.out.println("Найден файл " + fileName);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
        System.out.println("Найден файл " + path.toString() + " с ошибкой " + e.toString());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
        System.out.println("Выходим из каталога " + path.toString());
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getAllFiles() {
        return files;
    }

}
