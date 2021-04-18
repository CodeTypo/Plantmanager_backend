package com.codetypo.plantmanager.controller;

import com.codetypo.plantmanager.entity.Plant;
import com.codetypo.plantmanager.repository.PlantRepo;
import com.codetypo.plantmanager.repository.UserRepo;
import com.codetypo.plantmanager.search.SearchService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "https://mars-low.github.io"}, maxAge = 3600)
@RestController
@Data
public class PlantController {

    private final PlantRepo plantRepo;
    private final SearchService searchService;
    private final UserRepo userRepo;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PLANTS mapping methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @GetMapping("/plants")
    public ResponseEntity<List<Plant>> searchPlants(@RequestParam("name") String name) {
        List<Plant> result = searchService.getPlantsBasedOnWord(name);
        return ResponseEntity.ok(result);
    }

    //Get all
    @GetMapping("/plants/all")
    public List<Plant> getAllPlants() {
        return plantRepo.findAll();
    }

    //    //Get the one with specified id
    @GetMapping("/plants/{id}")
    public Plant getPlant(@PathVariable Long id) {
        return plantRepo.findById(id).get();
    }

    @PostMapping("/users/{userId}/plants")
    public Plant createPlant(@PathVariable Long userId,
                             @RequestBody Plant plant) throws ResourceNotFoundException {
        return userRepo.findById(userId).map(user -> {
            plant.setUser(user);
            return plantRepo.save(plant);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @DeleteMapping("/users/{userId}/plants/{plantId}")
    public ResponseEntity<?> deletePlant(@PathVariable Long userId,
                                         @PathVariable Long plantId)
            throws ResourceNotFoundException {
        return plantRepo.findByIdAndUserId(plantId,userId).map(plant -> {
            plantRepo.delete(plant);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Plant not found with id " + plantId + " and userId " + userId
        ));
    }

//    @DeleteMapping("/plants/{id}")
//    public ResponseEntity<Void> deletePlant(@PathVariable Long id){
//        int identifier = plantRepo.deletePlantById(id);
//        return identifier!=-1? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//    }

    //Add new plant, returns Plant JSON object
    //Works with POSTMAN only so far
//    @PostMapping("users/{id}/plants")
//    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant){
//        plantRepo.save(plant);
//        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
//    }


    //Update an existing plant
    @PutMapping("/plants/{id}")
    public ResponseEntity<Plant> updatePlant(@Validated @PathVariable Long id, @RequestBody Plant plant){
        Optional<Plant> p = plantRepo.findById(id);
        Plant plantToUpdate = p.get();
        plantToUpdate.setName(plant.getName());
        plantToUpdate.setDescription(plant.getDescription());
        plantToUpdate.setMeasured_humidity(plant.getMeasured_humidity());

        final Plant updatedPlant = plantRepo.save(plantToUpdate);
        return new ResponseEntity<Plant>(updatedPlant, HttpStatus.OK);
    }
}
