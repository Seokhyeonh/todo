package io.thesun4sky.todoapp.dto;

import io.thesun4sky.todoapp.entity.Comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long commentId;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.userName = comment.getUserName();
        this.createdAt = comment.getCreatedAt();

    }
}
