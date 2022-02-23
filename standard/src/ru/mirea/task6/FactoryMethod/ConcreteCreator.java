package ru.mirea.task6.FactoryMethod;

public class ConcreteCreator extends Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProduct();
    }
}
