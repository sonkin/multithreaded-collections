import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentModificationIssue2 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        // Task to add elements to the list
        Runnable addTask = () -> {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Task to iterate through the list
        Runnable iterateTask = () -> {
            while (true) {
                try {
                    for (Integer i: list) {
                        System.out.println("Iterating: " + i);
                    }
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (ConcurrentModificationException e) {
                    e.printStackTrace();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Submit tasks to the executor
        executor.submit(addTask);
        executor.submit(iterateTask);

        // Shutdown the executor after tasks finish
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
