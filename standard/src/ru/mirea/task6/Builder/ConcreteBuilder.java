package ru.mirea.task6.Builder;

public class ConcreteBuilder implements Builder {
    private int id;
    private int value;

    @Override
    public void setId(int id) {
        System.out.println("1 build part");
        this.id = id;
    }

    @Override
    public void setValue(int value) {
        System.out.println("2 build part");
        this.value = value;
    }

    public Product getResult() {
        return new Product(id, value);
    }
}
