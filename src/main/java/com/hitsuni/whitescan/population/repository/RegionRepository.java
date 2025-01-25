package com.hitsuni.whitescan.population.repository;

import com.hitsuni.whitescan.population.entity.Country;
import com.hitsuni.whitescan.population.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByNameAndCountry(String name, Country country);
}
