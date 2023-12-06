package com.restful.todomanagement.service;

import com.restful.todomanagement.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    List<TodoDto> getAllTodos();
}
