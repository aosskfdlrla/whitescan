package com.hitsuni.whitescan.population.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Region> regions = new ArrayList<>();

    public Country(String name) {
        this.name = name;
    }

    public Country() {

    }
}
