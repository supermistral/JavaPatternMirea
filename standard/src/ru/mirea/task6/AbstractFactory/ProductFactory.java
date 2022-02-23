package ru.mirea.task6.AbstractFactory;

public class ProductFactory implements Factory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB();
    }
}
