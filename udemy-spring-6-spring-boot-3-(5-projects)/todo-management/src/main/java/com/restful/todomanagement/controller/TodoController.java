package com.restful.todomanagement.controller;

import com.restful.todomanagement.dto.TodoDto;
import com.restful.todomanagement.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciamento de tarefas (TODOs).
 * Todas as operações CRUD estão mapeadas neste controlador.
 */
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    /**
     * Construtor que injeta a dependência do serviço TodoService.
     *
     * @param todoService O serviço utilizado para operações relacionadas a TODOs.
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Endpoint para adicionar uma nova tarefa (TODO).
     *
     * @param todoDto Objeto TodoDto contendo os detalhes da nova tarefa.
     * @return ResponseEntity contendo o TodoDto da tarefa adicionada e status HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obter uma tarefa (TODO) pelo ID.
     *
     * @param id O ID da tarefa a ser recuperada.
     * @return ResponseEntity contendo o TodoDto da tarefa e status HTTP 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long id) {
        TodoDto todoDto = todoService.getTodoById(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    /**
     * Endpoint para obter todas as tarefas (TODOs).
     *
     * @return ResponseEntity contendo uma lista de TodoDto das tarefas e status HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    /**
     * Endpoint para atualizar uma tarefa (TODO) pelo ID.
     *
     * @param id      O ID da tarefa a ser atualizada.
     * @param todoDto Objeto TodoDto contendo os detalhes atualizados da tarefa.
     * @return ResponseEntity contendo o TodoDto atualizado e status HTTP 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodoById(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {
        TodoDto updatedTodoDto = todoService.updateTodoById(todoDto, id);
        return new ResponseEntity<>(updatedTodoDto, HttpStatus.OK);
    }

    /**
     * Endpoint para excluir uma tarefa (TODO) pelo ID.
     *
     * @param id O ID da tarefa a ser excluída.
     * @return ResponseEntity com status HTTP 204 (No Content) indicando que a tarefa foi excluída com sucesso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable("id") Long id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para marcar uma tarefa (TODO) como concluída pelo ID.
     *
     * @param id O ID da tarefa a ser marcada como concluída.
     * @return ResponseEntity contendo o TodoDto atualizado e status HTTP 200 (OK).
     */
    @PutMapping("/completed/{id}")
    public ResponseEntity<TodoDto> updateTodoCompletedById(@PathVariable("id") Long id) {
        TodoDto updatedTodoDto = todoService.updateTodoCompletedById(id);
        return new ResponseEntity<>(updatedTodoDto, HttpStatus.OK);
    }
}