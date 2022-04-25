package ru.mirea.task15.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product implements IndexedModel {
    @Id
    @SequenceGenerator(name = "products_seq", sequenceName = "products_sequence", allocationSize = 1)
    @GeneratedValue(generator = "products_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {

    }

    @Override
    public Long getId() {
        return id;
    }
}
