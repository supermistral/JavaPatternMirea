package ru.mirea.task4;

public class Main {

    public static void main(String[] args) {
        CustomExecutorService service = CustomExecutors.newFixedThreadPool(3);
        for (int i = 0; i < 10; ++i) {
            service.submit(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                System.out.println("thread: " + Thread.currentThread().getName());
            });
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

        service.shutdown();
    }
}
