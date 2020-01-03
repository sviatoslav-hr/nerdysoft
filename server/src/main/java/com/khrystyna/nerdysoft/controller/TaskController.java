package com.khrystyna.nerdysoft.controller;

import com.khrystyna.nerdysoft.dto.TaskDto;
import com.khrystyna.nerdysoft.dto.forms.TaskForm;
import com.khrystyna.nerdysoft.service.interfaces.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.findAllForPrincipal().stream()
                .map(TaskDto::of)
                .collect(Collectors.toList());
    }

    @PostMapping("/{taskId}")
    @DeleteMapping("/{taskId}")
    public TaskDto deleteTask(@PathVariable String taskId) {
        return TaskDto.of(taskService.deleteById(taskId));
    }
}
