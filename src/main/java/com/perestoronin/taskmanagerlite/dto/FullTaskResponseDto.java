package com.perestoronin.taskmanagerlite.dto;
import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullTaskResponseDto {

    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
}
