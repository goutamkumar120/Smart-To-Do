package com.goutam.ai.Smart_To_Do.controller;

import com.goutam.ai.Smart_To_Do.exception.UserNotFoundException;
import com.goutam.ai.Smart_To_Do.model.Task;
import com.goutam.ai.Smart_To_Do.service.AISuggestionService;
import com.goutam.ai.Smart_To_Do.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final AISuggestionService aiService;

    public TaskController(TaskService taskService, AISuggestionService aiService) {
        this.taskService = taskService;
        this.aiService = aiService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable Long id) {
        Task updatedTask = taskService.updateTask(task, id);
        if (updatedTask == null) {
            throw new UserNotFoundException("No Order Found");
        }
        return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/suggest")
    public String suggestTask(@RequestParam String context) {

        return aiService.getSuggestedTask(context);
    }
}
