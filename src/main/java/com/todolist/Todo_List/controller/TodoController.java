package com.todolist.Todo_List.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.todolist.Todo_List.TodoService;
import com.todolist.Todo_List.model.Todo;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping({ "/", "/todos" })
    public String getAllTodos(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "todos";
    }

    // @GetMapping("/{id}")
    // public String getTodoById(Model model, @PathVariable Long id) {
    // model.addAttribute("todo", todoService.getTodoById(id));
    // return "todo";
    // }

    @GetMapping("/create-todo") // Add this method
    public String createTodo() {
        return "create-todo";
    }

    // @PostMapping("/save-todo")
    // public String saveTodoString(@RequestBody Todo todo) {
    // todoService.saveOrUpdate(todo);
    // return "redirect:/todos";
    // }

    @GetMapping("/trash")
    public String getSoftDeletedTodos(Model model) {
        model.addAttribute("todos", todoService.getAllSoftDeletedTodos());
        return "trash";
    }
}
