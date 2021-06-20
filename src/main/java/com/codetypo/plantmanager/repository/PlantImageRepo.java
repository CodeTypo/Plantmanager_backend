package com.codetypo.plantmanager.repository;

import com.codetypo.plantmanager.entity.PlantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PlantImageRepo extends JpaRepository<PlantImage, Long> {

    @Transactional
    Long deleteByPlantId(Long id);

    @Transactional
    Optional<PlantImage> findByPlantId(Long id);

}

