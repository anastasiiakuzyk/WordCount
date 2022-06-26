package anastasiia.ua;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static anastasiia.ua.FileWordUtils.getFilesFromDir;
import static anastasiia.ua.FileWordUtils.getWordsFromFile;

public class OneThread extends Process {

    public OneThread(String dir) {
        super(dir);
    }

    public void process() {
        long startTime = System.currentTimeMillis();
        List<Map<String, Integer>> listOfWordsByFiles = new ArrayList<>();
        for (File file : getFilesFromDir(dir)) {
            listOfWordsByFiles.add(countWords(file));
        }

        for (Map<String, Integer> fileWordCounter : listOfWordsByFiles) {
            for (Map.Entry<String, Integer> entry : fileWordCounter.entrySet()) {
                String word = entry.getKey();
                int count = entry.getValue();
                if (mapOfWords.containsKey(word)) {
                    count += mapOfWords.get(word);
                }
                mapOfWords.put(word, count);
            }
        }
        long endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
    }

    public static Map<String, Integer> countWords(File file) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : getWordsFromFile(file)) {
            int count;
            if (map.containsKey(word)) {
                count = map.get(word) + 1;
            } else {
                count = 1;
            }
            map.put(word, count);
        }
        return map;
    }
}
