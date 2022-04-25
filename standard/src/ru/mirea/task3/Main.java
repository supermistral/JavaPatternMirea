package ru.mirea.task3;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        testSemaphoreHashSet();
//        testLockArrayList();
    }

    public static void testSemaphoreHashSet() throws Exception {
        Set<Integer> hashset = new SemaphoreHashSet<>(5);

        Thread one = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                hashset.add(i);

                try {
                    Thread.sleep(10);
                    System.out.println("Add -> " + hashset.toString());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Thread two = new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                hashset.remove(i);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        one.start();
        Thread.sleep(1000);
        two.start();

        one.join();
        two.join();
        System.out.println(hashset.toString());
    }

    public static void testLockArrayList() throws Exception {
        List<Integer> arraylist = new LockArrayList<>();

        Thread one = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                arraylist.add(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 100; i < 110; ++i) {
                arraylist.add(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();
        System.out.println(Arrays.toString(arraylist.toArray()));
    }
}
