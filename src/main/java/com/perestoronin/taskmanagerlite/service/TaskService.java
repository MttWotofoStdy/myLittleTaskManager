package com.perestoronin.taskmanagerlite.service;

import com.perestoronin.taskmanagerlite.dto.FullTaskResponseDto;
import com.perestoronin.taskmanagerlite.dto.TaskCreateDto;
import com.perestoronin.taskmanagerlite.dto.TaskResponseDto;
import com.perestoronin.taskmanagerlite.dto.TaskStatusUpdateDto;
import com.perestoronin.taskmanagerlite.entity.Tasks;
import com.perestoronin.taskmanagerlite.mapper.TaskMapper;
import com.perestoronin.taskmanagerlite.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<FullTaskResponseDto> getAllFullTasks() {
        return taskRepository.findAll().stream().map(taskMapper::toFullTaskResponseDto).toList(); //как оно работает?
    }

    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::toTaskResponseDto).toList();
    }

    public FullTaskResponseDto createTask(TaskCreateDto taskCreateDto) {
        Tasks task = taskRepository.save(taskMapper.toTask(taskCreateDto));
        return taskMapper.toFullTaskResponseDto(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public TaskResponseDto changeStatus(Long id, @Valid TaskStatusUpdateDto newStatus) {
        Tasks currTask = taskRepository.getTasksById(id);
        currTask.setStatus(newStatus.getStatus());
        taskRepository.save(currTask);
        return taskMapper.toTaskResponseDto(currTask);
    }
}
