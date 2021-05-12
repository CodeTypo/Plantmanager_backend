package com.codetypo.plantmanager.service;

import com.codetypo.plantmanager.entity.Measurement;
import com.codetypo.plantmanager.repository.MeasurementRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementService {


    private MeasurementRepo measurementRepo;

    public List<Measurement> findMeasurementByPlantId(Long plantId){
        return measurementRepo.findByPlantId(plantId);
    }

    public Measurement save(Measurement measurement){
        return measurementRepo.save(measurement);
    }
}
