package com.sparta.todo.entity;


import com.sparta.todo.dto.TodoDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Todo {
        private Long id;
        private String title;
        private String content;
        private String assignee;
        private String password;
        private LocalDateTime createdDate;

    }


