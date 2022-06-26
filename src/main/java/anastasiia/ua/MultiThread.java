package anastasiia.ua;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

import static anastasiia.ua.FileWordUtils.getFilesFromDir;

public class MultiThread extends Process {

    static final Integer threadsNumber = 3;

    public MultiThread(String dir) {
        super(dir);
    }

    public void process() {
        long startTime = System.currentTimeMillis();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsNumber);
        try(Stream<Map<String, Integer>> listOfWordsByFiles =
                executor.submit(() ->
                                getFilesFromDir(dir)
                                        .parallelStream()
                                        .map(countWords::count))
                        .get()){

            executor.shutdown();

            mapOfWords = listOfWordsByFiles.reduce(addCounters::add).orElseThrow();

            long endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
        } catch (ExecutionException | InterruptedException exception){
            System.err.println(exception.getMessage());
        }
    }

}
