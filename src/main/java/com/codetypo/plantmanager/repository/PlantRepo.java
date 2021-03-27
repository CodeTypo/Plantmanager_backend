package com.codetypo.plantmanager.repository;

import com.codetypo.plantmanager.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PlantRepo extends JpaRepository <Plant,Long> {

    //An additional, deleting method, needed to be tagged as Transactional to work properly
    @Transactional
    int deletePlantById(Long id);

}
