package ru.mirea.task6.Prototype;

public class SecondPrototype extends Prototype {

    public SecondPrototype() {}

    public SecondPrototype(SecondPrototype target) {
        super(target);
    }

    @Override
    public Prototype clone() {
        return new SecondPrototype(this);
    }
}
