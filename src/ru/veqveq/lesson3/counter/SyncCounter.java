package ru.veqveq.lesson3.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncCounter {
    private int counter;
    Lock lock;

    public SyncCounter() {
        this.lock = new ReentrantLock();
    }

    public int get() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }

    public void inc() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public void dec() {
        lock.lock();
        try {
            counter--;
        } finally {
            lock.unlock();
        }
    }
}
