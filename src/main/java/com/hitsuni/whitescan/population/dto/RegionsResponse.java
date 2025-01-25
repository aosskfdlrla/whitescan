package com.hitsuni.whitescan.population.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class RegionsResponse {
    private int currentPopulation;
    private int maxPopulation;
    private int minPopulation;
    private double averagePopulation;
}
