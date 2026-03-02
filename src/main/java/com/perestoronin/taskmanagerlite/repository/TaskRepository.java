package com.perestoronin.taskmanagerlite.repository;

import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByStatus(TaskStatus taskStatus);

    Optional<Task> getTasksById(Long id);
}
