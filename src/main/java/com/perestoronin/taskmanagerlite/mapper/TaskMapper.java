package com.perestoronin.taskmanagerlite.mapper;

import com.perestoronin.taskmanagerlite.dto.tasks.TaskDto;
import com.perestoronin.taskmanagerlite.dto.tasks.CreateTaskRequest;
import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto toFullTaskResponseDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        return dto;
    }


//    public TaskResponseDto toTaskResponseDto(Task task) {
//        TaskResponseDto taskResponseDto = new TaskResponseDto();
//        taskResponseDto.setName(task.getName());
//        taskResponseDto.setDescription(task.getDescription());
//        return taskResponseDto;
//    }

    public Task toTask(CreateTaskRequest createTaskRequest) {
        Task thisTask = new Task();

        thisTask.setName(createTaskRequest.getName());
        thisTask.setDescription(createTaskRequest.getDescription());
        thisTask.setStatus(TaskStatus.ToDo);
        return thisTask;
    }
}
