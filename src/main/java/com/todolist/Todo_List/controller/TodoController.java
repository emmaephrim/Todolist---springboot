package com.todolist.Todo_List.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = { "/save-todo", "/update-todo" }, method = { RequestMethod.POST, RequestMethod.PUT })
    public String saveOrUpdateTodo(@ModelAttribute Todo todo, Model model, RedirectAttributes redirectAttributes) {
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

    @PostMapping("/bulk-update-todos")
    public String bulkUpdateTodos(@RequestParam("selectedIds") List<Long> selectedIds,
            @RequestParam("newStatus") String newStatus, RedirectAttributes redirectAttributes) {

        switch (newStatus) {
            case "delete":
                for (Long id : selectedIds) {
                    todoService.deleteTodoById(id);
                }
                redirectAttributes.addFlashAttribute("message", "Todo deleted successfully!");
                break;
            case "soft-delete":
                for (Long id : selectedIds) {
                    todoService.softDeleteTodoById(id);
                }
                redirectAttributes.addFlashAttribute("message", "Todo deleted successfully!");
                break;
            case "restore":
                for (Long id : selectedIds) {
                    todoService.restoreTodoById(id);
                }
                redirectAttributes.addFlashAttribute("message", "Todo restored successfully!");
                break;
            default:
                for (Long id : selectedIds) {
                    todoService.updateTodoStatus(id, newStatus);
                }
                redirectAttributes.addFlashAttribute("message", "Todo updated successfully!");
                break;
        }

        return "redirect:/todos";
    }

    @PutMapping("/soft-delete-todo/{id}")
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

    @GetMapping("/todos/status/{status}")
    public String getTodosByStatus(Model model, @PathVariable String status) {
        model.addAttribute("todos", todoService.getAllTodosByStatus(status.toLowerCase()));
        return "todos";
    }

    @PutMapping("/restore-todo/{id}")
    public String restoreTodoById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        todoService.restoreTodoById(id);
        redirectAttributes.addFlashAttribute("message", "Todo restored successfully!");
        return "redirect:/trash";
    }

    @DeleteMapping("/delete-todo/{id}")
    public String deleteTodoById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        todoService.deleteTodoById(id);
        redirectAttributes.addFlashAttribute("message", "Todo deleted successfully!");
        return "redirect:/trash";
    }

    @GetMapping("/table")
    public String getTable(Model model) {
        // model.addAttribute("todos", todoService.getAllActiveTodos());
        return "table";
    }
}
