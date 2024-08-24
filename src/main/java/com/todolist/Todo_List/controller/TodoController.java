package com.todolist.Todo_List.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todolist.Todo_List.TodoService;
import com.todolist.Todo_List.model.Todo;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping({ "/", "/todos" })
    public String getAllTodos(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("todos", todoService.getAllActiveTodos());
        model.addAttribute("message", message);
        return "todos";
    }

    @GetMapping("/create-todo")
    public String createTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "todo-form";
    }

    @GetMapping("/edit-todo/{id}")
    public String getTodoById(Model model, @PathVariable Long id) {
        model.addAttribute("todo", todoService.getTodoById(id));
        return "todo-form";
    }

    @PostMapping("/save-todo")
    public String saveTodo(@ModelAttribute Todo todo, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("todo", todo);
        boolean isUpdating = todo.getId() != null;
        todoService.saveOrUpdate(todo);
        if (isUpdating) {
            redirectAttributes.addFlashAttribute("message", "Todo updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Todo created successfully!");
        }
        return "redirect:/todos";
    }

    @PostMapping("/soft-delete-todo/{id}")
    public String softDeleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        todoService.softDeleteTodoById(id);
        redirectAttributes.addFlashAttribute("message", "Todo moved to trash successfully!");
        return "redirect:/todos";
    }

    @GetMapping("/trash")
    public String getSoftDeletedTodos(Model model) {
        model.addAttribute("todos", todoService.getAllSoftDeletedTodos());
        return "trash";
    }

    @PostMapping("/restore-todo/{id}")
    public String restoreTodoById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        todoService.restoreTodoById(id);
        redirectAttributes.addFlashAttribute("message", "Todo restored successfully!");
        return "redirect:/trash";
    }

    @PostMapping("/delete-todo/{id}")
    public String deleteTodoById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        todoService.deleteTodoById(id);
        redirectAttributes.addFlashAttribute("message", "Todo deleted successfully!");
        return "redirect:/trash";
    }
}
