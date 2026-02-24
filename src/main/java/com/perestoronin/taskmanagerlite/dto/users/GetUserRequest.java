package com.perestoronin.taskmanagerlite.dto.users;

import com.perestoronin.taskmanagerlite.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor //если это getDto надо ли убрать сеттеры? Такой  же вопрос к остальным DTO где нет функционала редактирования полей
public class GetUserRequest {
    private Long id;
    private String name;
    private Grade grade;
}
