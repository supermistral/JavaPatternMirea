package ru.mirea.task19.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "market")
    @JsonIgnoreProperties("market")
    private List<Product> products;

    public Market(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Market() {

    }

    @JsonIgnoreProperties("market")
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String toString() {
        return "Market { name=" + name + " address=" + address + " }";
    }
}
