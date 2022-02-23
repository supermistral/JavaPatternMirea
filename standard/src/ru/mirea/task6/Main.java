package ru.mirea.task6;

import ru.mirea.task6.AbstractFactory.Factory;
import ru.mirea.task6.AbstractFactory.ProductA;
import ru.mirea.task6.AbstractFactory.ProductB;
import ru.mirea.task6.AbstractFactory.ProductFactory;
import ru.mirea.task6.Builder.ConcreteBuilder;
import ru.mirea.task6.Builder.Director;
import ru.mirea.task6.Builder.Product;
import ru.mirea.task6.FactoryMethod.ConcreteCreator;
import ru.mirea.task6.FactoryMethod.Creator;
import ru.mirea.task6.Prototype.FirstPrototype;
import ru.mirea.task6.Prototype.Prototype;

public class Main {
    public static void main(String[] args) {
        testFactoryMethod();
//        testAbstractFactory();
//        testBuilder();
//        testPrototype();
    }

    public static void testFactoryMethod() {
        Creator factory = new ConcreteCreator();
        factory.operation();
    }

    public static void testAbstractFactory() {
        Factory factory = new ProductFactory();

        ProductA productA = factory.createProductA();
        ProductB productB = factory.createProductB();

        productA.execute();
        productB.execute();
    }

    public static void testBuilder() {
        Director director = new Director();
        ConcreteBuilder builder = new ConcreteBuilder();

        director.constructProduct(builder);
        Product product = builder.getResult();
    }

    public static void testPrototype() {
        Prototype prototype = new FirstPrototype();
        prototype.setValue(10);

        Prototype clonePrototype = prototype.clone();

        System.out.println(prototype.getValue());
        System.out.println(clonePrototype.getValue());
    }
}
