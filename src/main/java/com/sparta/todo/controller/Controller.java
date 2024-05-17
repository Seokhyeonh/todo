package com.sparta.todo.controller;

import com.sparta.todo.Repository;
import com.sparta.todo.dto.TodoDto;
import com.sparta.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/todos")
public class Controller {

    @Autowired
    private Repository repository;

    @GetMapping
    public List<TodoDto> getAllTodos() {
        return Repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<TodoDto> getTodoById(@PathVariable Long id) {
        return Repository.findById(id).map(this::convertToDto);
    }

    @PostMapping("/create")
    public TodoDto createTodo(@RequestBody TodoDto todoDto) {
        Todo todo = convertToEntity(todoDto);
        Todo savedTodo = Repository.save(todo);
        return convertToDto(savedTodo);
    }



    private TodoDto convertToDto(Todo todo) {
        TodoDto todoDto = new TodoDto(todo);
        todoDto.setId(todo.getId());
        todoDto.setTitle(todo.getTitle());
        todoDto.setContent(todo.getContent());
        todoDto.setAssignee(todo.getAssignee());
        todoDto.setCreatedDate(todo.getCreatedDate());
        return todoDto;
    }

    private Todo convertToEntity(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setContent(todoDto.getContent());
        todo.setAssignee(todoDto.getAssignee());
        return todo;
    }
}