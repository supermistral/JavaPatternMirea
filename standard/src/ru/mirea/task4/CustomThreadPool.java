package ru.mirea.task4;

import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool implements CustomExecutorService {
    public static int capacity;
    public static int currentCapacity;
    public static boolean isInterrupted = false;
    public static LinkedBlockingQueue<Runnable> linkedBlockingQueue;

    private Execution execution;

    public CustomThreadPool(int capacity) {
        this.capacity = capacity;
        this.currentCapacity = 0;

        linkedBlockingQueue = new LinkedBlockingQueue<>();
        execution = new Execution();
    }

    @Override
    public void submit(Runnable r) {
        linkedBlockingQueue.add(r);
        execution.execute();
    }

    @Override
    public void shutdown() {
        isInterrupted = true;
    }
}
