/*
 * Copyright (c) 2019-2023. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

/**
 * Critical Section & Synchronization
 * https://www.udemy.com/java-multithreading-concurrency-performance-optimization
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    public static class IncrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;

        Object lock = new Object();

        // when one thread is calling a synchronized method, all other synchronized methods of the same
        // object become inaccessible to the other threads
        public void increment() {
            // critical section, this part of the code is locked
            synchronized (this.lock) {
                items++;
            }
        }

        // we can either make an entire method synchronized, or just a critical section of it
        public synchronized void decrement() {
            items--;
        }

        // same thread can enter different synchronized methods, i.e. thread won't prevent itself from
        // entering another sync methods
        public int getItems() {
            synchronized (this.lock) {
                return items;
            }
        }

        // раз ты открыл этот файл по како-то причине и дошёл до конца, держи IT-шансон
        // https://www.youtube.com/watch?v=oVlvtltUVto
    }
}
