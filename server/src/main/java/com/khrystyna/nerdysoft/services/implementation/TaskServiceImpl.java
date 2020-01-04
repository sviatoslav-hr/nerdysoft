package com.khrystyna.nerdysoft.services.implementation;

import com.khrystyna.nerdysoft.exceptions.TaskNotFoundException;
import com.khrystyna.nerdysoft.models.Task;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.repository.TaskRepository;
import com.khrystyna.nerdysoft.services.interfaces.AuthenticationService;
import com.khrystyna.nerdysoft.services.interfaces.TaskService;
import com.khrystyna.nerdysoft.services.interfaces.TaskSharingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final AuthenticationService authenticationService;
    private TaskSharingService taskSharingService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           AuthenticationService authenticationService) {
        this.taskRepository = taskRepository;
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void setTaskSharingService(TaskSharingService taskSharingService) {
        this.taskSharingService = taskSharingService;
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
        User user = authenticationService.getAuthenticatedUser();
        if (task.getAuthor().getId().equals(user.getId())) {
            try {
                taskSharingService.deleteAllByTaskId(taskId);
            } catch (Exception e) {
                log.warn("Got exception during deleting sharings for task " + taskId, e);
            }
            taskRepository.delete(task);
        } else {
            try {
                taskSharingService.declineTask(taskId);
            } catch (Exception e) {
                log.warn("Got exception during deleting declining task " + taskId, e);
            }
        }
        return task;
    }

    @Override
    public List<Task> findAllForPrincipal() {
        User user = authenticationService.getAuthenticatedUser();
        return taskRepository.findAllByUsersContainsOrderByDateTimeDesc(user);
    }
}
