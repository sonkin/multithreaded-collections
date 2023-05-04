import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LostUpdateIssue {

    public static void main(String[] args) {
        long start = System.nanoTime();

        List<Integer> list = new ArrayList<>();
        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        // Task to add elements to the list
        Runnable addTask1 = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        // Task to add elements to the list
        Runnable addTask2 = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        // Submit tasks to the executor
        executor.submit(addTask1);
        executor.submit(addTask2);

        // Shutdown the executor after tasks finish
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Number of elements: "+list.size());

        long time = System.nanoTime() - start;
        System.out.println("time passed: "+time/1000_000+"ms");
    }
}
