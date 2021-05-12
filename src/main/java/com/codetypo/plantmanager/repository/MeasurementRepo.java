package com.codetypo.plantmanager.repository;

import com.codetypo.plantmanager.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepo extends JpaRepository<Measurement, Long> {
    List<Measurement> findByPlantId(Long plantId);
}
