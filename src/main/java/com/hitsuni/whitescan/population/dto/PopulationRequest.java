package com.hitsuni.whitescan.population.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopulationRequest {
    private String countryName;
    private String regionName;
    private Integer populationNum;
    private LocalDateTime recordDate;
}
