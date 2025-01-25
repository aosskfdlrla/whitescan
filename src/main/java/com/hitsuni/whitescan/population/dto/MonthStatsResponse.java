package com.hitsuni.whitescan.population.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MonthStatsResponse {
    private String countryName;
    private int totalPopulation;
    private int totalChangeRate;
    private List<RegionStats> regionStats;
}
