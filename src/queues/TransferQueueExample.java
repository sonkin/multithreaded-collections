package queues;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueExample {
    public static void main(String[] args) {
        TransferQueue<String> transferQueue = new LinkedTransferQueue<>();

        // Producer thread
        Thread producerThread = new Thread(() -> {
            try {
                String element = "Hello, TransferQueue!";
                System.out.println("Producer is transferring: " + element);
                transferQueue.transfer(element); // Transfer the element to the consumer
                System.out.println("Producer has transferred the element.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumerThread = new Thread(() -> {
            try {
                System.out.println("Consumer is waiting to receive an element.");
                String receivedElement = transferQueue.take(); // Receive the element from the producer
                System.out.println("Consumer has received: " + receivedElement);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start the threads
        producerThread.start();
        consumerThread.start();

        // Wait for the threads to complete
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
