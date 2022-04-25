package ru.mirea.task18.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private double price;

    public ProductDTO(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
