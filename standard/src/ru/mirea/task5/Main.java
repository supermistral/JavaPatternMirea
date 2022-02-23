package ru.mirea.task5;

public class Main {
    public static void main(String[] args) {
        testLazy();
        testThreadSafe();
        testInnerHelper();
    }

    public static void testLazy() {
        LazySingleton singleton = LazySingleton.getInstance();
        LazySingleton anotherSingleton = LazySingleton.getInstance();
        System.out.println(singleton);
        System.out.println(anotherSingleton);
    }

    public static void testThreadSafe() {
        Thread t1 = new Thread(() -> {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
            System.out.println(singleton);
        });
        Thread t2 = new Thread(() -> {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
            System.out.println(singleton);
        });

        t1.start();
        t2.start();
    }

    public static void testInnerHelper() {
        InnerHelperSingleton singleton = InnerHelperSingleton.getInstance();
        InnerHelperSingleton anotherSingleton = InnerHelperSingleton.getInstance();
        System.out.println(singleton);
        System.out.println(anotherSingleton);
    }
}
