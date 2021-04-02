package com.codetypo.plantmanager.repository;

import com.codetypo.plantmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User,Long> {
    User findUserByEmail(String email);
}
