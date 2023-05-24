package queues;

import java.util.ArrayDeque;

public class ArrayDequeExample {

    public static void main(String[] args) {
        // Create an ArrayDeque
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Adding elements to the deque
        deque.addFirst(10);
        deque.addLast(20);
        deque.addLast(30);
        deque.addFirst(5);

        // Printing the deque
        System.out.println("Deque: " + deque); // Output: Deque: [5, 10, 20, 30]

        // Accessing elements
        int first = deque.getFirst();
        int last = deque.getLast();
        System.out.println("First element: " + first); // Output: First element: 5
        System.out.println("Last element: " + last); // Output: Last element: 30

        // Removing elements
        int removedFirst = deque.removeFirst();
        int removedLast = deque.removeLast();
        System.out.println("Removed first element: " + removedFirst); // Output: Removed first element: 5
        System.out.println("Removed last element: " + removedLast); // Output: Removed last element: 30

        // Printing the updated deque
        System.out.println("Updated deque: " + deque); // Output: Updated deque: [10, 20]



    }
}
