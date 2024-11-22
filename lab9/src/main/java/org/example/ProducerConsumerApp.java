package org.example;

public class ProducerConsumerApp {
    private static final int BUFFER_CAPACITY = 10;
    private static final int MESSAGE_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        RingBuffer buffer1 = new RingBuffer(BUFFER_CAPACITY);
        RingBuffer buffer2 = new RingBuffer(BUFFER_CAPACITY);

        // Start producer threads
        for (int i = 1; i <= 5; i++) {
            int threadNumber = i;
            Thread producer = new Thread(() -> {
                try {
                    for (int j = 0; j < MESSAGE_COUNT / 5; j++) {
                        String message = "Thread #" + threadNumber + " generated message " + j;
                        buffer1.put(message);
                        System.out.println("Produced: " + message);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            producer.setDaemon(true);
            producer.start();
        }

        // Start transfer threads
        for (int i = 1; i <= 2; i++) {
            int threadNumber = i;
            Thread transfer = new Thread(() -> {
                try {
                    while (true) {
                        String message = buffer1.take();
                        String transferredMessage = "Thread #" + threadNumber + " transferred " + message;
                        buffer2.put(transferredMessage);
                        System.out.println("Transferred: " + transferredMessage);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            transfer.setDaemon(true);
            transfer.start();
        }

        // Main thread prints messages from buffer2
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = buffer2.take();
            System.out.println("Main consumed: " + message);
        }
    }
}