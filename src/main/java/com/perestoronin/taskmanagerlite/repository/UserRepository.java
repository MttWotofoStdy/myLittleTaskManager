package com.perestoronin.taskmanagerlite.repository;

import com.perestoronin.taskmanagerlite.entity.Grade;
import com.perestoronin.taskmanagerlite.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByGrade(Grade grade);
}
