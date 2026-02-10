package com.perestoronin.taskmanagerlite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDto {
    @NotBlank
    private String name;
    private String description;

}
