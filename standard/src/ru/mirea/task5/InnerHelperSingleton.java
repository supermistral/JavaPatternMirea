package ru.mirea.task5;

public class InnerHelperSingleton {
    private static class InnerHelper {
        private static final InnerHelperSingleton INSTANCE = new InnerHelperSingleton();
    }

    private InnerHelperSingleton() {}

    public static InnerHelperSingleton getInstance() {
        return InnerHelper.INSTANCE;
    }
}
