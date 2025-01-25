package com.hitsuni.whitescan.population.service;

import com.hitsuni.whitescan.population.dto.*;
import com.hitsuni.whitescan.population.entity.Country;
import com.hitsuni.whitescan.population.entity.Population;
import com.hitsuni.whitescan.population.entity.Region;
import com.hitsuni.whitescan.population.repository.CountryRepository;
import com.hitsuni.whitescan.population.repository.PopulationRepository;
import com.hitsuni.whitescan.population.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PopulationService {

    private final CountryRepository countryRepository;
    private final PopulationRepository populationRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public void savePopulation(PopulationRequest request) {
        Country country = countryRepository.findByName(request.getCountryName()).
                orElseGet(() -> countryRepository.save(new Country(request.getCountryName())));

        // 2. Region 조회 또는 생성
        Region region = regionRepository.findByNameAndCountry(request.getRegionName(), country)
                .orElseGet(() -> regionRepository.save(new Region(request.getRegionName(), country)));

        // 3. Population 저장
        Population population = new Population(region, request.getPopulationNum(), request.getRecordDate());
        populationRepository.save(population);
    }

    @Transactional
    public RegionsResponse getPopulationStatistics(Long regionId, FindPopulation findPopulation) {
        List<Population> populations = populationRepository.findByRegionIdAndRecordDateBetween(regionId, findPopulation.getStartDate(), findPopulation.getEndDate());

        if (populations.isEmpty()) {
            throw new NoSuchElementException("해당 기간의 데이터가 없습니다");
        }

        int currentPopulation = populations.get(populations.size() - 1).getPopulation();
        int maxPopulation = populations.stream().mapToInt(Population::getPopulation).max().orElse(0);
        int minPopulation = populations.stream().mapToInt(Population::getPopulation).min().orElse(0);
        double avgPopulation = populations.stream().mapToInt(Population::getPopulation).average().orElse(0);

        return new RegionsResponse(currentPopulation, maxPopulation, minPopulation, avgPopulation);
    }

    @Transactional
    public MonthStatsResponse getMonthlyStats(Long countryId, int year, int month) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NoSuchElementException("국가를 찾을 수 없습니다"));

        List<RegionStats> regionStats = populationRepository.findMonthlyStatsByCountryAndYearMonth(countryId, year, month);

        return new MonthStatsResponse(
                country.getName(),
                regionStats.stream().mapToInt(RegionStats::getCurrentPopulation).sum(),
                regionStats.stream().mapToInt(RegionStats::getChangeRate).sum(),
                regionStats
        );
    }
}
