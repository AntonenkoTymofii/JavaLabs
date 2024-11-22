package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RingBuffer {
    private final String[] buffer;
    private int head = 0, tail = 0, count = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public RingBuffer(int capacity) {
        buffer = new String[capacity];
    }

    public void put(String message) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                notFull.await(); // Wait until there's space in the buffer
            }
            buffer[tail] = message;
            tail = (tail + 1) % buffer.length;
            count++;
            notEmpty.signal(); // Signal that buffer is not empty
        } finally {
            lock.unlock();
        }
    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); // Wait until there's data in the buffer
            }
            String message = buffer[head];
            head = (head + 1) % buffer.length;
            count--;
            notFull.signal(); // Signal that buffer has space
            return message;
        } finally {
            lock.unlock();
        }
    }
}