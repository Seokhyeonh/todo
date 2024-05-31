package io.thesun4sky.todoapp.dto;

import io.thesun4sky.todoapp.entity.Comment;
import io.thesun4sky.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class CommentRequestDto {

    private String content;

    private String userName;

    public Comment toEntity(Todo todo) {
        return Comment.builder()
                .content(content)
                .userName(userName)
                .todo(todo)
                .build();
    }
}
