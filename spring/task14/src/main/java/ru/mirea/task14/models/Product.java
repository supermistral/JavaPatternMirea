package ru.mirea.task14.models;

public class Product implements IndexedModel {
    private String name;
    private double price;
    private int id;
    private static int autoIncrement = 0;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = autoIncrement++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }
}
