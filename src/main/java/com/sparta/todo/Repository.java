package com.sparta.todo;

import com.sparta.todo.entity.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
@org.springframework.stereotype.Repository
public class Repository {
        private static final List<Todo> todos = new ArrayList<>();
        private static final AtomicLong counter = new AtomicLong();

        public static List<Todo> findAll() {
            return new ArrayList<>(todos);
        }

        public static Optional<Todo> findById(Long id) {
            return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst();
        }

        public static Todo save(Todo todo) {
            if (todo.getId() == null) {
                todo.setId(counter.incrementAndGet());
                todo.setCreatedDate(LocalDateTime.now());
                todos.add(todo);
            }
            return todo;
        }

    }

