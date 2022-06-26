package anastasiia.ua;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileWordUtils {

    public static List<File> getFilesFromDir(String folderName) {
        File dir = new File(folderName);
        List<File> files = new ArrayList<>();
        try {
            for (File file : Objects.requireNonNull(dir.listFiles()))
                if (file.getName().endsWith(".txt")) {
                    files.add(file);
                }
        } catch (Exception e) {
            System.err.println(folderName + " wasn't found.");
        }
        return files;
    }

    public static List<String> getWordsFromFile(File file) {
        List<String> words = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
