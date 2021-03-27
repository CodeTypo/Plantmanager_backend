package com.codetypo.plantmanager.repository;

import com.codetypo.plantmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User,Long> {
}
