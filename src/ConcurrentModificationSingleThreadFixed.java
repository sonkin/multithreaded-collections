import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * You can fix the ConcurrentModificationException
 * by using an explicit Iterator and its remove()
 * method to safely remove elements while iterating.
 */
public class ConcurrentModificationSingleThreadFixed {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        // Adding elements to the list
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        // Using an Iterator to safely remove elements while iterating
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            System.out.println("Current number: " + number);
            if (number == 2) {
                iterator.remove(); // Safely remove the element
            }
        }

        // Print the remaining elements in the list
        System.out.println("Remaining elements: " + list);
    }
}
