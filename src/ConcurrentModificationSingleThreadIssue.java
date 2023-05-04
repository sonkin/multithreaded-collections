import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationSingleThreadIssue {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        // Adding elements to the list
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        // Attempting to remove elements while iterating over the list
        for (Integer number : list) {
            System.out.println("Current number: " + number);
            if (number == 2) {
                list.remove(number);
            }
        }
    }
}
