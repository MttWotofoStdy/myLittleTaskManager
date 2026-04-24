package com.perestoronin.taskmanagerlite.dto.tasks;

import com.perestoronin.taskmanagerlite.entity.TaskStatus;



public record TaskDto
        (Long id, String name, String description, TaskStatus status) {

}
