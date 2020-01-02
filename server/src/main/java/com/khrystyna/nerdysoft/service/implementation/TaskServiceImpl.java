package com.khrystyna.nerdysoft.service.implementation;

import com.khrystyna.nerdysoft.dto.forms.TaskForm;
import com.khrystyna.nerdysoft.exceptions.UserNotFoundException;
import com.khrystyna.nerdysoft.models.Task;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.repository.TaskRepository;
import com.khrystyna.nerdysoft.repository.UserRepository;
import com.khrystyna.nerdysoft.service.interfaces.TaskService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public Task save(TaskForm taskForm) {
        User author = userRepository.findById(taskForm.getAuthorId()).orElseThrow(
                () -> new UserNotFoundException("User with id " + taskForm.getAuthorId() + " was not found"));
        Task task = taskForm.toTask();
        task.setAuthor(author);
        task.setUsers(Collections.singletonList(author));
        return taskRepository.save(task);
    }
}
