package com.khrystyna.nerdysoft.services.interfaces;

import com.khrystyna.nerdysoft.models.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task);

    Task findById(String taskId);

    Task deleteById(String taskId);

    List<Task> findAllForPrincipal();
}
