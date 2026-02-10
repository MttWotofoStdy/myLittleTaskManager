package com.perestoronin.taskmanagerlite.controller;

import com.perestoronin.taskmanagerlite.dto.FullTaskResponseDto;
import com.perestoronin.taskmanagerlite.dto.TaskCreateDto;
import com.perestoronin.taskmanagerlite.dto.TaskResponseDto;
import com.perestoronin.taskmanagerlite.dto.TaskStatusUpdateDto;
import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<FullTaskResponseDto>> getAlltasks() {
        return ResponseEntity.ok(taskService.getAllFullTasks());
    }

    @GetMapping("/namedesc")
    public ResponseEntity<List<TaskResponseDto>> getNameDescTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<FullTaskResponseDto> createTask(@Valid @RequestBody TaskCreateDto taskCreateDto) {
        return ResponseEntity.ok(taskService.createTask(taskCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();// ←как это работает?
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponseDto> changeStatus(@PathVariable Long id, @Valid @RequestBody TaskStatusUpdateDto newStatus) {
        return ResponseEntity.ok(taskService.changeStatus(id, newStatus));
    }
}
