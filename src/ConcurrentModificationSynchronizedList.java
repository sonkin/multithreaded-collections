import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentModificationSynchronizedList {

    public static void main(String[] args) {
        List<Integer> unsafeList = new ArrayList<>();
        ExecutorService executor =
                Executors.newFixedThreadPool(2);
        List<Integer> list =
                Collections.synchronizedList(unsafeList);

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
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        Integer number = iterator.next();
                        System.out.println("Iterating: " + number);
                    }
                    TimeUnit.MILLISECONDS.sleep(5);
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
