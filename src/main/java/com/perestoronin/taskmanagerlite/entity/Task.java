package com.perestoronin.taskmanagerlite.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "taski")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;
    @Column
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
