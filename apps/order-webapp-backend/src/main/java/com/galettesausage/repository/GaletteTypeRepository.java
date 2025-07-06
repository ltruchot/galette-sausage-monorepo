package com.galettesausage.repository;

import com.galettesausage.entity.GaletteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GaletteTypeRepository extends JpaRepository<GaletteType, Long> {
    List<GaletteType> findByAvailableTrue();
}