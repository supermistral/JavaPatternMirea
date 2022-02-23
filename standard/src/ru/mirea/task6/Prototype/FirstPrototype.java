package ru.mirea.task6.Prototype;

public class FirstPrototype extends Prototype {

    public FirstPrototype() {}

    public FirstPrototype(FirstPrototype target) {
        super(target);
    }

    @Override
    public Prototype clone() {
        return new FirstPrototype(this);
    }
}
