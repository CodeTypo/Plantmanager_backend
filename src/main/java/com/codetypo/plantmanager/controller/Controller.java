package com.codetypo.plantmanager.controller;

import com.codetypo.plantmanager.dto.PlantPutRequest;
import com.codetypo.plantmanager.dto.UserPutRequest;
import com.codetypo.plantmanager.entity.Plant;
import com.codetypo.plantmanager.entity.User;
import com.codetypo.plantmanager.repository.PlantRepo;
import com.codetypo.plantmanager.repository.UserRepo;
import com.codetypo.plantmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "https://mars-low.github.io"}, maxAge = 3600)
@RestController
public class Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PlantRepo plantRepo;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ USER mapping methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @GetMapping("/users")
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(
            @PathVariable(value = "id") Long userId
    ) throws  ResourceNotFoundException {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found ::" + userId));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId,
            @RequestBody User userDetails
    ) throws ResourceNotFoundException {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found ::" + userId));
        user.setFname(userDetails.getFname());
        user.setLname(userDetails.getLname());

        final User updatedUser = userRepo.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/users")
    public ResponseEntity<User> loginUser(@RequestBody LoginForm loginForm){
        User user = null;
        boolean log = false;
        try {
            user = userRepo.findUserByEmail(loginForm.getEmail());

        } catch (Exception e){
            e.printStackTrace();
        }

        if (user != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            log = encoder.matches(loginForm.getPassword(), user.getPassword());
            if (log){
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        }else {
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/addUser")
    //Works with POSTMAN only so far
    public User addUser (@RequestBody User user){
        return userService.createUser(user);
    }



//    @DeleteMapping("/instructors/{id}")
//    public Map < String, Boolean > deleteUser(
//            @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
//        Instructor instructor = instructorRepository.findById(instructorId)
//                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
//
//        instructorRepository.delete(instructor);
//        Map < String, Boolean > response = new HashMap < > ();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PLANTS mapping methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Get all
//    @GetMapping("/plants")
//    public List<Plant> getAllPlants() {
//        return plantRepo.findAll();
//    }

    @GetMapping("/users/{userId}/plants")
    public List<Plant> getPlantsByUser(@PathVariable Long userId){
        return plantRepo.findByUserId(userId);
    }

    @GetMapping("/users/{userId}/plants/{plantId}")
    public Plant getPlant(@PathVariable Long userId,
                                         @PathVariable Long plantId)
            throws ResourceNotFoundException {
        return plantRepo.findByIdAndUserId(plantId,userId).orElseThrow(() -> new ResourceNotFoundException(
                "Plant not found with id " + plantId + " and userId " + userId
        ));
    }

    @PostMapping("/users/{userId}/plants")
    public Plant createPlant(@PathVariable Long userId,
                             @RequestBody Plant plant) throws ResourceNotFoundException {
        return userRepo.findById(userId).map(user -> {
            plant.setUser(user);
            return plantRepo.save(plant);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @PutMapping("/users/{userId}/plants/{plantId}")
    public Plant updatePlant(@PathVariable Long userId,
                             @PathVariable Long plantId,
                             @RequestBody Plant plantRequest) throws ResourceNotFoundException {
        if (!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        return plantRepo.findById(plantId).map(plant -> {
            plant.setName(plantRequest.getName());
            plant.setDescription(plantRequest.getDescription());
            return plantRepo.save(plant);
        }).orElseThrow(() -> new ResourceNotFoundException("Plant not found"));
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



//    //Get the one with specified id
//    @GetMapping("/plants/{id}")
//    public Optional<Plant> getPlant(@PathVariable Long id) {
//        return plantRepo.findById(id);
//    }
//
//    @DeleteMapping("/plants/{id}")
//    public ResponseEntity<Void> deletePlant(@PathVariable Long id){
//        int identifier = plantRepo.deletePlantById(id);
//        return identifier!=-1? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//    }
//
//    //Add new plant, returns Plant JSON object
//    //Works with POSTMAN only so far
////    @PostMapping("users/{id}/plants")
////    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant){
////        plantRepo.save(plant);
////        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
////    }
//
//
//    //Update an existing plant
//    @PutMapping("/plants/{id}")
//    public ResponseEntity<Plant> updatePlant(@Validated @PathVariable Long id, @RequestBody Plant plant){
//        Optional<Plant> p = plantRepo.findById(id);
//        Plant plantToUpdate = p.get();
//        plantToUpdate.setName(plant.getName());
//        plantToUpdate.setDescription(plant.getDescription());
//        plantToUpdate.setMeasured_humidity(plant.getMeasured_humidity());
//
//        final Plant updatedPlant = plantRepo.save(plantToUpdate);
//        return new ResponseEntity<Plant>(updatedPlant, HttpStatus.OK);
//    }
}
