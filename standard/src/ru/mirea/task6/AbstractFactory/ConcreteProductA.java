package ru.mirea.task6.AbstractFactory;

public class ConcreteProductA implements ProductA {
    @Override
    public void execute() {
        System.out.println("product A -> execute");
    }
}
