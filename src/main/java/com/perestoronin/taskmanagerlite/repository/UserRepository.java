package com.perestoronin.taskmanagerlite.repository;

import com.perestoronin.taskmanagerlite.entity.Grade;
import com.perestoronin.taskmanagerlite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGrade(Grade grade);
}
