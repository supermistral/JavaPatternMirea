package ru.mirea.task21.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketDTO {
    private Long id;
    private String name;
    private String address;

    public MarketDTO(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
