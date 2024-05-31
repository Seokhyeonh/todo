package io.thesun4sky.todoapp.service;

import io.thesun4sky.todoapp.dto.CommentRequestDto;
import io.thesun4sky.todoapp.entity.Comment;
import io.thesun4sky.todoapp.entity.Todo;
import io.thesun4sky.todoapp.repository.CommentRepository;
import io.thesun4sky.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;


import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
@Validated
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public Comment createComment(Long todoId, CommentRequestDto dto) {
        validateCreateComment(todoId, dto); // Ensure todoId and dto are correctly passed and validated
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("일정이 DB에 저장되지 않았습니다."));
        Comment comment = new Comment(dto.getContent(), dto.getUserName(), todo);
        return commentRepository.save(comment);
    }

    private void validateCreateComment(Long todoId, CommentRequestDto dto) {
        // Check if userName is null or if content is empty, throw exception if true
        if (dto.getUserName() == null || dto.getUserName().isEmpty() || dto.getContent().isEmpty()) {
            throw new IllegalArgumentException("일정의 ID를 입력하고 댓글 내용을 작성해주세요.");
        }
    }

    public List<Comment> getCommentsByTodoId(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("일정이 DB에 저장되지 않았습니다."));
        return todo.getComments();
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
        if (StringUtils.isEmpty(dto.getContent())) {
            throw new IllegalArgumentException("수정할 댓글 내용이 비어 있습니다.");
        }
        comment.setContent(dto.getContent());
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
        commentRepository.delete(comment);
    }
}


