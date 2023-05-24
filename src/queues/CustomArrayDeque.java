package queues;

import java.util.NoSuchElementException;

public class CustomArrayDeque<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int head;
    private int tail;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomArrayDeque() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void addFirst(T element) {
        if (size == elements.length) {
            resizeArray();
        }
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = element;
        size++;
        System.out.println("addFirst("+ element+"): "+ this);
    }

    public void addLast(T element) {
        if (size == elements.length) {
            resizeArray();
        }
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
        System.out.println("addLast("+ element+"): "+ this);
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        T removedElement = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        System.out.println(removedElement + " removeFirst(): "+ this);
        return removedElement;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        tail = (tail - 1 + elements.length) % elements.length;
        T removedElement = elements[tail];
        elements[tail] = null;
        size--;
        System.out.println(removedElement + " removeLast(): "+ this);
        return removedElement;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return elements[head];
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return elements[(tail - 1 + elements.length) % elements.length];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            if (i == head) {
                sb.append("(").append(elements[i]).append(")"); // Head value
            } else if (i == (tail - 1 + elements.length) % elements.length) {
                sb.append("[").append(elements[i]).append("]"); // Tail value
            } else if (elements[i] != null) {
                sb.append(elements[i]);
            } else {
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void resizeArray() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[2 * elements.length];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[(head + i) % elements.length];
        }
        elements = newArray;
        head = 0;
        tail = size;
    }

    public static void main(String[] args) {
        CustomArrayDeque<Integer> deque = new CustomArrayDeque<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addLast(30);
        deque.addFirst(5);

        // Removing elements
        int removedFirst = deque.removeFirst();
        int removedLast = deque.removeLast();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addLast(-1);
        deque.addLast(-2);
        deque.addLast(-3);
        deque.removeFirst();
        deque.addFirst(100);
        deque.addFirst(200);
        deque.addFirst(300);

    }
}
