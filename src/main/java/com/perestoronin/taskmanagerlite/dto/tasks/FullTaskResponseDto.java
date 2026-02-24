package com.perestoronin.taskmanagerlite.dto.tasks;
import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FullTaskResponseDto {

    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
}
