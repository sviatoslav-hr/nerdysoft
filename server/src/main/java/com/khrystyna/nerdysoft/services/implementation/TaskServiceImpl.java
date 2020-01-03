package com.khrystyna.nerdysoft.services.implementation;

import com.khrystyna.nerdysoft.configs.security.Principal;
import com.khrystyna.nerdysoft.dto.forms.TaskForm;
import com.khrystyna.nerdysoft.exceptions.TaskNotFoundException;
import com.khrystyna.nerdysoft.models.Task;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.repository.TaskRepository;
import com.khrystyna.nerdysoft.services.interfaces.TaskService;
import com.khrystyna.nerdysoft.services.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserService userService;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserService userService, TaskRepository taskRepository) {
        this.userService = userService;
        this.taskRepository = taskRepository;
    }

    public Task save(TaskForm taskForm) {
        User author = userService.findById(taskForm.getAuthorId());
        Task task = taskForm.toTask();
        task.setAuthor(author);
        task.setUsers(Collections.singletonList(author));
        task.setDateTime(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task findById(String taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " was not found"));
    }

    @Override
    public Task deleteById(String taskId) {
        Task task = findById(taskId);
        taskRepository.delete(task);
        return task;
    }

    @Override
    public List<Task> findAllForPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
        User user = userService.findByEmail(principal.getUsername());
        return taskRepository.findAllByUsersContainsOrderByDateTimeDesc(user);
    }
}
