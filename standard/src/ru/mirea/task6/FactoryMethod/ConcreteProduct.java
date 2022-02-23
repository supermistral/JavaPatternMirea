package ru.mirea.task6.FactoryMethod;

public class ConcreteProduct implements Product {
    @Override
    public void execute() {
        System.out.println("Product -> execute");
    }
}
