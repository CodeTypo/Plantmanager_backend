package com.codetypo.plantmanager.repository;

import com.codetypo.plantmanager.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepo extends JpaRepository <Plant,Long> {

    //An additional, deleting method, needed to be tagged as Transactional to work properly
    @Transactional
    int deletePlantById(Long id);

    List<Plant> findByUserId(Long id);

    Optional<Plant> findByIdAndUserId(Long id, Long userId);
}
