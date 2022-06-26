package anastasiia.ua;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static anastasiia.ua.FileWordUtils.getWordsFromFile;

public abstract class Process {

    public String dir;

    public Process(String dir) {
        this.dir = dir;
    }

    public abstract void process();

    public long elapsedTime;
    public Map<String, Integer> mapOfWords = new TreeMap<>();

    public void printResults(boolean printToFile) {
        if (printToFile) {
            try (PrintStream out = new PrintStream(new FileOutputStream("results.txt", true), true)) {
                System.setOut(out);
                out.println(getClass().getSimpleName() + " results:");
                mapOfWords.forEach((word, count) -> out.println("Word\t" + word + "\tfound\t" + count + "\ttimes."));
                out.println("Time elapsed: " + elapsedTime / 1000F + " seconds\n");
            } catch (FileNotFoundException e) {
                System.err.print(e.getMessage());
            }
        } else {
            System.out.println(getClass().getSimpleName() + " results:");
            mapOfWords.forEach((word, count) -> System.out.println("Word\t" + word + "\tfound\t" + count + "\ttimes."));
            System.out.println("Time elapsed: " + elapsedTime / 1000F + " seconds\n");
        }
    }

    public static CountI<String, Integer> countWords = file -> {
        Map<String, Integer> dict = new HashMap<>();
        for (String word : getWordsFromFile(file)) {
            if (dict.containsKey(word)) {
                int value = dict.get(word);
                dict.put(word, ++value);
            } else
                dict.put(word, 1);
        }
        return dict;
    };

    public static AddI<String, Integer> addCounters = (x, y) -> {
        y.forEach((key, value) -> new TreeMap<>(x).merge(key, value, Integer::sum));
        return new TreeMap<>(x);
    };
}
