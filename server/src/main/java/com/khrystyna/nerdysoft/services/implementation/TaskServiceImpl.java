package com.khrystyna.nerdysoft.services.implementation;

import com.khrystyna.nerdysoft.exceptions.TaskNotFoundException;
import com.khrystyna.nerdysoft.models.Task;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.repository.TaskRepository;
import com.khrystyna.nerdysoft.services.interfaces.AuthenticationService;
import com.khrystyna.nerdysoft.services.interfaces.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final AuthenticationService authenticationService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           AuthenticationService authenticationService) {
        this.taskRepository = taskRepository;
        this.authenticationService = authenticationService;
    }

    public Task save(Task task) {
        if (task.getAuthor() == null) {
            User author = authenticationService.getAuthenticatedUser();
            task.setAuthor(author);
            task.setUsers(Collections.singletonList(author));
        }
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
        User user = authenticationService.getAuthenticatedUser();
        return taskRepository.findAllByUsersContainsOrderByDateTimeDesc(user);
    }
}
