package com.restful.todomanagement.service;

import com.restful.todomanagement.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodoById(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodoById(TodoDto todoDto, Long id);

    void deleteTodoById(Long id);
    
    TodoDto updateTodoCompletedById(Long id);
}
