package ru.mirea.task15.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "market")
@Getter
@Setter
public class Market implements IndexedModel {
    @Id
    @SequenceGenerator(name = "markets_seq", sequenceName = "markets_sequence", allocationSize = 1)
    @GeneratedValue(generator = "markets_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Market(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Market() {

    }

    @Override
    public Long getId() {
        return id;
    }
}
