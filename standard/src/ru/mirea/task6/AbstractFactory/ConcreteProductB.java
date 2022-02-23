package ru.mirea.task6.AbstractFactory;

public class ConcreteProductB implements ProductB {
    @Override
    public void execute() {
        System.out.println("product B -> execute");
    }
}
