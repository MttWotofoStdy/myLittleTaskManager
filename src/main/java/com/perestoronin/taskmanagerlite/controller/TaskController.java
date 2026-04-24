package com.perestoronin.taskmanagerlite.controller;

import com.perestoronin.taskmanagerlite.dto.tasks.*;
import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@Validated
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;

    }



    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAllTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            Sort sort
    ) {
        int pageSize = Math.min(size, 50);
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be positive");
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TaskDto> tasks = taskService.getAllTasks(status, name, pageable);

        return ResponseEntity.ok(tasks);
    }



    @PostMapping
    public ResponseEntity<TaskDto> addTask(@Valid @RequestBody CreateTaskRequest createTaskRequest) {
        return ResponseEntity.ok(taskService.createTask(createTaskRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable @Positive Long id) {
            taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDto> changeStatus(@PathVariable @Positive Long id, @Valid @RequestBody UpdateTaskStatusRequest newStatus) {
        return ResponseEntity.ok(taskService.changeStatus(id, newStatus));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDto> changeNameOrDesc(@PathVariable @Positive Long id, @Valid @RequestBody UpdateTaskRequest dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getCurrTask(@PathVariable @Positive Long id) {
       TaskDto task = taskService.getTaskById(id);
       return ResponseEntity.ok(task);
    }
}
