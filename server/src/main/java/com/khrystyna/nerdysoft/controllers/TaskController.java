package com.khrystyna.nerdysoft.controllers;

import com.khrystyna.nerdysoft.dto.TaskDto;
import com.khrystyna.nerdysoft.dto.TaskSharingDto;
import com.khrystyna.nerdysoft.dto.forms.TaskForm;
import com.khrystyna.nerdysoft.dto.forms.TaskShareForm;
import com.khrystyna.nerdysoft.services.interfaces.TaskService;
import com.khrystyna.nerdysoft.services.interfaces.TaskSharingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskSharingService taskSharingService;

    public TaskController(TaskService taskService, TaskSharingService taskSharingService) {
        this.taskService = taskService;
        this.taskSharingService = taskSharingService;
    }

    @PostMapping
    public TaskDto save(@RequestBody TaskForm taskForm) {
        return TaskDto.of(taskService.save(taskForm.toTask()));
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

    @PostMapping("/share")
    public TaskSharingDto shareTask(@RequestBody TaskShareForm form) {
        return TaskSharingDto.of(taskSharingService.shareTask(form.getTaskId(), form.getReceiverEmail()));
    }

    @PostMapping("/accept/{taskId}")
    public void acceptTask(@PathVariable String taskId) {
        taskSharingService.acceptTask(taskId);
    }

    @PostMapping("/decline/{taskId}")
    public void declineTask(@PathVariable String taskId) {
        taskSharingService.declineTask(taskId);
    }

    @GetMapping("/shared")
    public List<TaskSharingDto> shared() {
        return taskSharingService.findSharedTasks().stream()
                .map(TaskSharingDto::of)
                .collect(Collectors.toList());
    }

}
