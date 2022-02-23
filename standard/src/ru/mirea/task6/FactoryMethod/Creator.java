package ru.mirea.task6.FactoryMethod;

public abstract class Creator {
    public abstract Product createProduct();

    public void operation() {
        Product product = createProduct();
        product.execute();
    }
}
