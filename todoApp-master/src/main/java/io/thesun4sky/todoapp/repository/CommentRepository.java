package io.thesun4sky.todoapp.repository;

import io.thesun4sky.todoapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface CommentRepository extends JpaRepository<Comment, Long> {

    }
