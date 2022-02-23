package ru.mirea.task4;

public class CustomExecutors {

    public static CustomExecutorService newFixedThreadPool(int capacity) {
        return new CustomThreadPool(capacity);
    }
}
