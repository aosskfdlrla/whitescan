package com.hitsuni.whitescan.population.repository;

import com.hitsuni.whitescan.population.dto.FindPopulation;
import com.hitsuni.whitescan.population.dto.RegionStats;
import com.hitsuni.whitescan.population.entity.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PopulationRepository extends JpaRepository<Population, Long> {
    List<Population> findByRegionIdAndRecordDateBetween(Long regionId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT new com.hitsuni.whitescan.population.dto.RegionStats(r.name, CAST(AVG(p.population) AS int), 0) " +
            "FROM Population p " +
            "JOIN p.region r " +
            "WHERE r.country.Id = :countryId " +
            "AND FUNCTION('YEAR', p.recordDate) = :year " +
            "AND FUNCTION('MONTH', p.recordDate) = :month " +
            "GROUP BY r.name")
    List<RegionStats> findMonthlyStatsByCountryAndYearMonth(Long countryId, int year, int month);

}
