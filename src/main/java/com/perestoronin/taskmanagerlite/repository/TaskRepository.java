package com.perestoronin.taskmanagerlite.repository;

import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByStatus(TaskStatus taskStatus);

    Tasks getTasksById(Long id);
}
