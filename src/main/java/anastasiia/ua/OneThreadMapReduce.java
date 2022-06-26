package anastasiia.ua;

import java.util.Map;
import java.util.stream.Stream;

import static anastasiia.ua.FileWordUtils.getFilesFromDir;

public class OneThreadMapReduce extends Process {

    public OneThreadMapReduce(String dir) {
        super(dir);
    }

    public void process() {
        long startTime = System.currentTimeMillis();

        Stream<Map<String, Integer>> listOfWordsByFiles = getFilesFromDir(dir).stream().map(countWords::count);
        mapOfWords = listOfWordsByFiles.reduce(addCounters::add).orElseThrow();

        long endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
    }

}
