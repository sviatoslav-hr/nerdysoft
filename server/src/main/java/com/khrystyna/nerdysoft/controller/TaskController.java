package com.khrystyna.nerdysoft.controller;

import com.khrystyna.nerdysoft.dto.TaskDto;
import com.khrystyna.nerdysoft.dto.forms.TaskForm;
import com.khrystyna.nerdysoft.service.interfaces.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto save(@RequestBody TaskForm taskForm) {
        return TaskDto.of(taskService.save(taskForm));
    }

    @GetMapping("/{taskId}")
    public TaskDto getTask(@PathVariable String taskId) {
        return TaskDto.of(taskService.findById(taskId));
    }

    @DeleteMapping("/{taskId}")
    public TaskDto deleteTask(@PathVariable String taskId) {
        return TaskDto.of(taskService.deleteById(taskId));
    }
}
