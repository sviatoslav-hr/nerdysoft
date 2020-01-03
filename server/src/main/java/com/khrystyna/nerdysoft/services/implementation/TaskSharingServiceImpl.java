package com.khrystyna.nerdysoft.services.implementation;

import com.khrystyna.nerdysoft.models.Task;
import com.khrystyna.nerdysoft.models.TaskSharing;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.repository.TaskSharingRepository;
import com.khrystyna.nerdysoft.services.interfaces.AuthenticationService;
import com.khrystyna.nerdysoft.services.interfaces.TaskService;
import com.khrystyna.nerdysoft.services.interfaces.TaskSharingService;
import com.khrystyna.nerdysoft.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskSharingServiceImpl implements TaskSharingService {
    private final TaskSharingRepository taskSharingRepository;
    private final TaskService taskService;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public TaskSharingServiceImpl(TaskSharingRepository taskSharingRepository, UserService userService,
                                  TaskService taskService, AuthenticationService authenticationService) {
        this.taskSharingRepository = taskSharingRepository;
        this.userService = userService;
        this.taskService = taskService;
        this.authenticationService = authenticationService;
    }

    @Override
    public List<TaskSharing> findSharedTasks() {
        User user = authenticationService.getAuthenticatedUser();
        return taskSharingRepository.findAllByReceiverOrderByDateTimeDesc(user);
    }

    @Override
    public TaskSharing shareTask(String taskId, String receiverEmail) {
        Task task = taskService.findById(taskId);
        User receiver = userService.findByEmail(receiverEmail);
        User sender = authenticationService.getAuthenticatedUser();
        TaskSharing taskSharing = TaskSharing.builder()
                .receiver(receiver)
                .sender(sender)
                .task(task)
                .dateTime(LocalDateTime.now())
                .build();
        taskSharing = taskSharingRepository.save(taskSharing);
        task.getUsers().add(receiver);
        taskService.save(task);
        return taskSharing;
    }

    @Override
    public void acceptTask(String taskId) {
        Task task = taskService.findById(taskId);
        User receiver = authenticationService.getAuthenticatedUser();
        taskSharingRepository.deleteByReceiverAndTask(receiver, task);
    }

    @Override
    public void declineTask(String taskId) {
        Task task = taskService.findById(taskId);
        User receiver = authenticationService.getAuthenticatedUser();
        task.getUsers().remove(receiver);
        taskService.save(task);
        taskSharingRepository.deleteByReceiverAndTask(receiver, task);
    }
}
