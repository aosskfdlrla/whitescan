package com.hitsuni.whitescan.population.controller;

import com.hitsuni.whitescan.population.dto.*;
import com.hitsuni.whitescan.population.service.PopulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class populationController {

    private final PopulationService populationService;

    @PostMapping("/population-records")
    public ResponseEntity<String> InsertPopulation(@RequestBody PopulationRequest populationRequest) {

        populationService.savePopulation(populationRequest);
        return ResponseEntity.ok("저장된 데이터 정보");
    }

    @GetMapping("/regions/{regionId}/stats")
    public ResponseEntity<RegionsResponse> SelectPopulation(@PathVariable Long regionId,
                                                            @ModelAttribute FindPopulation findPopulation) {
        RegionsResponse regionsResponse = populationService.getPopulationStatistics(regionId, findPopulation);
        return ResponseEntity.ok(regionsResponse);
    }

    @GetMapping("/countries/{countryId}/monthly-stats")
    public ResponseEntity<MonthStatsResponse> SelectMonthPopulation(@PathVariable Long countryId,
                                                      @ModelAttribute FindMonthPopulation findMonthPopulation) {
        MonthStatsResponse response = populationService.getMonthlyStats(countryId, findMonthPopulation.getYear(), findMonthPopulation.getMonth());
        return ResponseEntity.ok(response);
    }
}
