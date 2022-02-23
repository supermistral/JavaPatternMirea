package ru.mirea.task4;

public class Execution implements Runnable {

    @Override
    public void run() {
        while (!CustomThreadPool.isInterrupted) {
            if (CustomThreadPool.linkedBlockingQueue.size() != 0) {
                CustomThreadPool.linkedBlockingQueue.poll().run();
            }
        }
    }

    public void execute() {
        if (CustomThreadPool.currentCapacity < CustomThreadPool.capacity) {
            ++CustomThreadPool.currentCapacity;
            Thread thread = new Thread(new Execution());
            thread.start();
        }
    }
}
