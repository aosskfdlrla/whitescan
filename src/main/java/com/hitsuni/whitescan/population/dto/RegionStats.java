package com.hitsuni.whitescan.population.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionStats {
    private String name;
    private int currentPopulation;
    private int changeRate;
}
