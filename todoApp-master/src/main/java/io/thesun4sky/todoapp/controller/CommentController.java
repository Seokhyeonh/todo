package io.thesun4sky.todoapp.controller;

import io.micrometer.common.util.StringUtils;
import io.thesun4sky.todoapp.CommonResponse;
import io.thesun4sky.todoapp.dto.CommentRequestDto;
import io.thesun4sky.todoapp.dto.CommentResponseDto;
import io.thesun4sky.todoapp.entity.Comment;
import io.thesun4sky.todoapp.repository.CommentRepository;
import io.thesun4sky.todoapp.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")

    public class CommentController {

        private final CommentService commentService;

        @PostMapping("/create")
        public ResponseEntity<CommonResponse<CommentResponseDto>> postComment(
                @RequestParam Long todoId,@Valid @RequestBody CommentRequestDto dto) {
            Comment comment = commentService.createComment(todoId, dto);
            CommentResponseDto response = new CommentResponseDto(comment);
            return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                    .statusCode(HttpStatus.OK.value())
                    .msg("댓글이 생성되었습니다.")
                    .data(response)
                    .build());
        }

        @GetMapping("/{todoId}")
        public ResponseEntity<CommonResponse<List<CommentResponseDto>>> getComments(@PathVariable Long todoId) {
            List<Comment> comments = commentService.getCommentsByTodoId(todoId);
            List<CommentResponseDto> response = comments.stream()
                    .map(CommentResponseDto::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(CommonResponse.<List<CommentResponseDto>>builder()
                    .statusCode(HttpStatus.OK.value())
                    .msg("댓글 목록 조회가 완료되었습니다.")
                    .data(response)
                    .build());
        }

        @PutMapping("/{id}")
        public ResponseEntity<CommonResponse<CommentResponseDto>> updateComment(
                @PathVariable Long id, @Valid @RequestBody CommentRequestDto dto) {
            Comment comment = commentService.updateComment(id, dto);
            CommentResponseDto response = new CommentResponseDto(comment);
            return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                    .statusCode(HttpStatus.OK.value())
                    .msg("댓글이 수정되었습니다.")
                    .data(response)
                    .build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<CommonResponse<Void>> deleteComment(@PathVariable Long id) {
            commentService.deleteComment(id);
            return ResponseEntity.ok().body(CommonResponse.<Void>builder()
                    .statusCode(HttpStatus.OK.value())
                    .msg("댓글이 삭제되었습니다.")
                    .build());
        }
    }
