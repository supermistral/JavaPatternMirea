package ru.mirea.task14.models;

public class Market implements IndexedModel {
    private static int autoIncrement = 0;

    private String name;
    private String address;
    private int id;

    public Market(String name, String address) {
        this.name = name;
        this.address = address;
        this.id = autoIncrement++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }
}
