package com.perestoronin.taskmanagerlite.service;

import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.exception.TaskNotFoundException;
import com.perestoronin.taskmanagerlite.dto.*;
import com.perestoronin.taskmanagerlite.entity.Tasks;
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
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepositoryInj, TaskMapper taskMapper) {
        this.taskRepository = taskRepositoryInj;
        this.taskMapper = taskMapper;
    }

//    @Transactional
//    public List<FullTaskResponseDto> getAllFullTasks() {
//
//        return taskRepository.findAll().stream().map(taskMapper::toFullTaskResponseDto).toList(); //как оно работает?
//
//    }
@Transactional
    public Page<TaskResponseDto> getAllTasks(
            TaskStatus status,
            String name,
            Pageable pageable
    ) {
        Specification<Tasks> spec = TaskSpecification.combine(status, name);
        Page<Tasks> tasks = taskRepository.findAll(spec, pageable);
        return tasks.map(taskMapper::toTaskResponseDto);
    }

    @Transactional
    public FullTaskResponseDto createTask(TaskCreateDto taskCreateDto) {
        Tasks task = taskRepository.save(taskMapper.toTask(taskCreateDto));
        if (taskCreateDto.getName() == null && !taskCreateDto.getName().isBlank()) {
            throw new IllegalArgumentException();

        }
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
        Tasks currTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if(newStatus.getStatus() == null ){
            throw new IllegalArgumentException("Статус не может быть пустым");
        }
        currTask.setStatus(newStatus.getStatus());
        taskRepository.save(currTask);
        return taskMapper.toTaskResponseDto(currTask);
    }

    @Transactional
    public TaskResponseDto updateTask(Long id, @Valid TaskUpdateDto dto) {
        Tasks currTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (dto.getName() != null && dto.getName().isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        currTask.setName(dto.getName());
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            currTask.setDescription(dto.getDescription());
        }
        return taskMapper.toTaskResponseDto(currTask);
    }

@Transactional
public FullTaskResponseDto getTaskById(Long id) {
    Tasks currTask = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
    return taskMapper.toFullTaskResponseDto(currTask);}


}
