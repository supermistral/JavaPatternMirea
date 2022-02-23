package ru.mirea.task1;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> formatter = (str) -> {
            int strLength = str.length();
            StringBuilder result = new StringBuilder(strLength);

            for (int i = 0; i < strLength; ++i) {
                result.append(i % 3 != 0 ? str.charAt(i) : str.substring(i, i + 1).toUpperCase());
            }

            System.out.println(result.toString());
        };

        formatter.accept("сan you can a can as a canner can can a can?");
    }
}
