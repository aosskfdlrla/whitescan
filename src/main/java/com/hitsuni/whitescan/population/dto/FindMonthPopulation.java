package com.hitsuni.whitescan.population.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindMonthPopulation {
    private Integer year;
    private Integer month;
}
