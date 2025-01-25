package com.hitsuni.whitescan.population.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "population")
public class Population {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    private Integer population;
    private LocalDateTime recordDate;

    public Population(Region region, Integer populationNum, LocalDateTime recordDate) {
        this.region = region;
        this.population = populationNum;
        this.recordDate = recordDate;
    }

    public Population() {

    }
}
