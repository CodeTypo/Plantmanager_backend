package com.codetypo.plantmanager.controller;

import com.codetypo.plantmanager.entity.Measurement;
import com.codetypo.plantmanager.service.MeasurementService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://mars-low.github.io"}, maxAge = 3600)
@RestController
@Data
@AllArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @GetMapping("/plant/{plantId}/measure")
    public List<Measurement> getMeasurementByPlant(@PathVariable Long plantId){
        return measurementService.findMeasurementByPlantId(plantId);
    }
}
