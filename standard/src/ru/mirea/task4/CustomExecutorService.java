package ru.mirea.task4;

public interface CustomExecutorService {
    void submit(Runnable r);
    void shutdown();
}
