package ru.mirea.task6.Prototype;

public abstract class Prototype {
    protected int value;

    public Prototype() {}

    public Prototype(Prototype target) {
        if (target != null) {
            value = target.value;
        }
    }

    public abstract Prototype clone();

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
