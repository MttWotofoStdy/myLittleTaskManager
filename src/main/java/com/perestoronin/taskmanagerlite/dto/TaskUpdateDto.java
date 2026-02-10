package com.perestoronin.taskmanagerlite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskUpdateDto {

    private String name;

    private String description;
}
