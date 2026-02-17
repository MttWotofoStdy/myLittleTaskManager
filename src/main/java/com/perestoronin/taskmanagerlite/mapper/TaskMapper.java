package com.perestoronin.taskmanagerlite.mapper;

import com.perestoronin.taskmanagerlite.dto.FullTaskResponseDto;
import com.perestoronin.taskmanagerlite.dto.TaskCreateDto;
import com.perestoronin.taskmanagerlite.dto.TaskResponseDto;
import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.entity.Tasks;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public FullTaskResponseDto toFullTaskResponseDto(Tasks task) {
        FullTaskResponseDto dto = new FullTaskResponseDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
       dto.setStatus(task.getStatus());
        return dto;
    }


public TaskResponseDto toTaskResponseDto(Tasks task){
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setName(task.getName());
        taskResponseDto.setDescription(task.getDescription());
        return taskResponseDto;
    }

    public Tasks toTask(TaskCreateDto taskCreateDto){
        Tasks thisTask = new Tasks();

        thisTask.setName(taskCreateDto.getName());
        thisTask.setDescription(taskCreateDto.getDescription());
        thisTask.setStatus(TaskStatus.ToDo);
        return thisTask;
    }
}
