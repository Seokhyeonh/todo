package com.sparta.todo.dto;

import com.sparta.todo.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TodoDto {
    private Long id;
    private String title;
    private String content;
    private String assignee;
    private LocalDateTime createdDate;

    public TodoDto(Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.assignee = todo.getAssignee();
        this.createdDate = todo.getCreatedDate();

    }
}
