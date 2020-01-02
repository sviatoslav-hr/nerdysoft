package com.khrystyna.nerdysoft.service.interfaces;

import com.khrystyna.nerdysoft.dto.forms.TaskForm;
import com.khrystyna.nerdysoft.models.Task;

import java.util.List;

public interface TaskService {

    Task save(TaskForm taskForm);

    Task findById(String taskId);

    Task deleteById(String taskId);

    List<Task> findAllForPrincipal();
}
