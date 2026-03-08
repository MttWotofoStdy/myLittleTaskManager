package com.perestoronin.taskmanagerlite.dto.tasks;


import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatchTaskStatusRequest {

    @NotNull
    private TaskStatus status;

}
