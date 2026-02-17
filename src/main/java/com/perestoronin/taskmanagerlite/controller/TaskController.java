package com.perestoronin.taskmanagerlite.controller;

import com.perestoronin.taskmanagerlite.dto.*;
import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.repository.TaskRepository;
import com.perestoronin.taskmanagerlite.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService, TaskRepository tasksRepository) {
        this.taskService = taskService;

    }

//    @GetMapping
//    public ResponseEntity<FullTaskResponseDto> getAllTasks(){
//        return ResponseEntity.ok(taskService.getAllFullTasks()); //← легаси без пагинации
//    }


    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> getAllTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    )  {


        Pageable pageable = PageRequest.of(page, Math.min(size, 50));
        Page<TaskResponseDto> tasks = taskService.getAllTasks(status, name, pageable);
        return ResponseEntity.ok(tasks);
    }

//    @GetMapping("/namedesc")
//    public ResponseEntity<List<TaskResponseDto>> getNameDescTasks() {
//        return ResponseEntity.ok(taskService.getAllTasks());
//    }

    @PostMapping
    public ResponseEntity<FullTaskResponseDto> createTask(@Valid @RequestBody TaskCreateDto taskCreateDto) {
        return ResponseEntity.ok(taskService.createTask(taskCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable @Valid @Min(1) Long id) {
            taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();// ←как это работает?
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponseDto> changeStatus(@PathVariable Long id, @Valid @RequestBody TaskChangeStatus newStatus) {
        return ResponseEntity.ok(taskService.changeStatus(id, newStatus));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> changeNameOrDesc(@PathVariable Long id, @Valid @RequestBody TaskUpdateDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullTaskResponseDto> getCurrTask(@PathVariable Long id) {
        if (ResponseEntity.ok(taskService.getTaskById(id)).hasBody()) {
            return ResponseEntity.ok(taskService.getTaskById(id));
        } else {
            throw new RuntimeException();
        }
    }
}
