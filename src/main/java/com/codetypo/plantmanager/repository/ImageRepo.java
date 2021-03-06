package com.codetypo.plantmanager.repository;

import com.codetypo.plantmanager.entity.Image;
import org.ietf.jgss.Oid;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

    @Transactional
    Long deleteByUserId(Long id);

    @Transactional
    Optional<Image> findByUserId(Long id);

}

