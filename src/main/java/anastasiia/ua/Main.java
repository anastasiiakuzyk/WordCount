package anastasiia.ua;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.print("No directories were entered as an argument, please try again");
            System.exit(1);
        }
        String dir = args[0];

        Process[] processes = new Process[]{
                new OneThread(dir),
                new OneThreadMapReduce(dir),
                new MultiThread(dir)};

        for (Process process : processes) {
            process.process();
            process.printResults(true);
        }
    }
}
