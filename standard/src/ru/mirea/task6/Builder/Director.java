package ru.mirea.task6.Builder;

public class Director {
    public void constructProduct(Builder builder) {
        builder.setId(1);
        builder.setValue(2);
    }
}
