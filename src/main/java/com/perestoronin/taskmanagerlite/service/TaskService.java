package com.perestoronin.taskmanagerlite.service;

import com.perestoronin.taskmanagerlite.dto.tasks.*;
import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.exception.tasksexception.TaskNotFoundException;
import com.perestoronin.taskmanagerlite.entity.Task;
import com.perestoronin.taskmanagerlite.mapper.TaskMapper;
import com.perestoronin.taskmanagerlite.repository.TaskRepository;
import com.perestoronin.taskmanagerlite.specification.TaskSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepositoryInj, TaskMapper taskMapper) {
        this.taskRepository = taskRepositoryInj;
        this.taskMapper = taskMapper;
    }


    @Transactional
    public Page<TaskResponseDto> getAllTasks(
            TaskStatus status,
            String name,
            Pageable pageable
    ) {
        Specification<Task> spec = TaskSpecification.combine(status, name);
        Page<Task> tasks = taskRepository.findAll(spec, pageable);
        return tasks.map(taskMapper::toTaskResponseDto);
    }

    @Transactional
    public TaskDto createTask(CreateTaskRequest createTaskRequest) {

        Task task = taskRepository.save(taskMapper.toTask(createTaskRequest));
        return taskMapper.toFullTaskResponseDto(task);
    }

    @Transactional
    public void deleteTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    @Transactional
    public TaskResponseDto changeStatus(Long id, @Valid TaskChangeStatus newStatus) {
        Task currTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (newStatus.getStatus() == null) {
            throw new IllegalArgumentException("Статус не может быть пустым");
        }
        currTask.setStatus(newStatus.getStatus());
        taskRepository.save(currTask);
        return taskMapper.toTaskResponseDto(currTask);
    }

    @Transactional
    public TaskResponseDto updateTask(Long id, @Valid TaskUpdateDto dto) {
        Task currTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        currTask.setName(dto.getName());
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            currTask.setDescription(dto.getDescription());
        } else{
            currTask.setDescription(null);
        }
        return taskMapper.toTaskResponseDto(currTask);
    }

    @Transactional
    public TaskDto getTaskById(Long id) {
        Task currTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toFullTaskResponseDto(currTask);
    }
}
