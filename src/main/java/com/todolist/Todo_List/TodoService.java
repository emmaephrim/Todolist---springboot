package com.todolist.Todo_List;

import java.util.ArrayList; // Add this import statement
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import com.todolist.Todo_List.model.Todo;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        ArrayList<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).get();
    }

    public void saveOrUpdate(Todo todo) {
        todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

    public boolean todoExists(Long id) {
        return todoRepository.existsById(id);
    }

    public Todo updateTodoStatus(Long id, String status) {
        Todo todo = todoRepository.findById(id).get();
        todo.setStatus("Completed");
        return todoRepository.save(todo);
    }

    public boolean todoIsSoftDeleted(Long id) {
        return todoRepository.findById(id).get().isSoftDeleted();
    }

    public void softDeleteTodoById(Long id) {
        Todo todo = todoRepository.findById(id).get();
        todo.setSoftDeleted(true);
        todoRepository.save(todo);
    }

    public ArrayList<Todo> getAllSoftDeletedTodos() {
        ArrayList<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todo -> {
            if (todo.isSoftDeleted()) {
                todos.add(todo);
            }
        });
        return todos;
    }

    public void updateTodoDate(Long id, DataSize date) {
    }

    public void updateTodoDescription(Long id, String description) {
    }

    public void updateTodoTitle(Long id, String title) {
    }

    public void updateTodo(Long id, Todo todo) {
    }

    public void updateAllTodos(List<Todo> todos) {
    }
}
