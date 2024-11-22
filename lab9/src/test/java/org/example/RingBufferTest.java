package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingBufferTest {
    private static final int BUFFER_CAPACITY = 10;
    private RingBuffer buffer;

    @BeforeEach
    void setUp() {
        buffer = new RingBuffer(BUFFER_CAPACITY);
    }

    @Test
    void testPutAndTakeSingleThread() throws InterruptedException {
        buffer.put("Message 1");
        assertEquals("Message 1", buffer.take());

        buffer.put("Message 2");
        assertEquals("Message 2", buffer.take());
    }

    @Test
    void testBufferFullAndEmptyConditions() throws InterruptedException {
        // Fill the buffer to capacity
        for (int i = 0; i < BUFFER_CAPACITY; i++) {
            buffer.put("Message " + i);
        }

        // Start a producer thread and check if it blocks on a full buffer
        Thread producer = new Thread(() -> {
            try {
                buffer.put("Overflow Message");  // This should block since the buffer is full
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();

        // Wait a short time to see if the producer thread remains blocked
        Thread.sleep(100);
        assertTrue(producer.isAlive(), "Producer thread should be blocked on full buffer");

        // Now, clear one item from the buffer so the producer can proceed
        buffer.take();
        producer.join();  // Wait for producer to finish

        // Empty the buffer completely
        for (int i = 1; i < BUFFER_CAPACITY; i++) {  // Already removed one item above
            buffer.take();
        }

        // Start a consumer thread and check if it blocks on an empty buffer
        Thread consumer = new Thread(() -> {
            try {
                buffer.take();  // This should block since the buffer is empty
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();

        // Wait a short time to see if the consumer thread remains blocked
        Thread.sleep(100);
        assertFalse(consumer.isAlive(), "Consumer thread should be blocked on empty buffer");

        // Put one item into the buffer so the consumer can proceed
        buffer.put("Test Message");
        consumer.join();  // Wait for consumer to finish
    }
}