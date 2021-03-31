package com.codetypo.plantmanager.controller;

import com.codetypo.plantmanager.dto.PlantPutRequest;
import com.codetypo.plantmanager.dto.UserPutRequest;
import com.codetypo.plantmanager.entity.Plant;
import com.codetypo.plantmanager.entity.User;
import com.codetypo.plantmanager.repository.PlantRepo;
import com.codetypo.plantmanager.repository.UserRepo;
import com.codetypo.plantmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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





    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PLANTS mapping methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Get all
    @GetMapping("/plants")
    public List<Plant> getAllPlants() {
        return plantRepo.findAll();
    }

    //Get the one with specified id
    @GetMapping("/plants/{id}")
    public Optional<Plant> getPlant(@PathVariable long id) {
        return plantRepo.findById(id);
    }

    @DeleteMapping("/plants/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable long id){
        int identifier = plantRepo.deletePlantById(id);
        return identifier!=-1? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    //Add new plant, returns Plant JSON object
    //Works with POSTMAN only so far
    @PostMapping("/plants")
    public Plant createPlant(@RequestBody PlantPutRequest request){
        return plantRepo.save(request.getPlant());
    }

    //Update an existing plant
    @PutMapping("/plants/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable long id, @RequestBody Plant plant){
        Plant plantUpdated = plantRepo.save(plant);
        return new ResponseEntity<Plant>(plantUpdated, HttpStatus.OK);
    }
}
