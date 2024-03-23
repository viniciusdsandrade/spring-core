package com.restful.todomanagement.service.impl;

import com.restful.todomanagement.dto.TodoDto;
import com.restful.todomanagement.entity.Todo;
import com.restful.todomanagement.exception.ResourceNotFoundException;
import com.restful.todomanagement.repository.TodoRepository;
import com.restful.todomanagement.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço para operações relacionadas a tarefas (TODOs).
 */
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    /**
     * Construtor que injeta as dependências do repositório e do ModelMapper.
     *
     * @param todoRepository O repositório utilizado para acesso a dados de TODOs.
     * @param modelMapper    O ModelMapper utilizado para mapear entidades para DTOs e vice-versa.
     */
    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Adiciona uma nova tarefa (TODO).
     *
     * @param todoDto Objeto TodoDto contendo os detalhes da nova tarefa.
     * @return TodoDto da tarefa adicionada.
     */
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    /**
     * Obtém uma tarefa (TODO) pelo ID.
     *
     * @param id O ID da tarefa a ser recuperada.
     * @return TodoDto da tarefa encontrada.
     * @throws ResourceNotFoundException Se a tarefa não for encontrada.
     */
    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o ID: " + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    /**
     * Obtém todas as tarefas (TODOs).
     *
     * @return Lista de TodoDto das tarefas.
     */
    @Override
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Atualiza uma tarefa (TODO) pelo ID.
     *
     * @param todoDto Objeto TodoDto contendo os detalhes atualizados da tarefa.
     * @param id      O ID da tarefa a ser atualizada.
     * @return TodoDto da tarefa atualizada.
     * @throws ResourceNotFoundException Se a tarefa não for encontrada.
     */
    @Override
    public TodoDto updateTodoById(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o ID: " + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    /**
     * Exclui uma tarefa (TODO) pelo ID.
     *
     * @param id O ID da tarefa a ser excluída.
     */
    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    /**
     * Marca uma tarefa (TODO) como concluída pelo ID.
     *
     * @param id O ID da tarefa a ser marcada como concluída.
     * @return TodoDto da tarefa atualizada.
     * @throws ResourceNotFoundException Se a tarefa não for encontrada.
     */
    @Override
    public TodoDto updateTodoCompletedById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o ID: " + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
