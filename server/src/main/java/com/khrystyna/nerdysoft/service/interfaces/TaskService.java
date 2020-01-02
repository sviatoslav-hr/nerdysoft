package com.khrystyna.nerdysoft.service.interfaces;

import com.khrystyna.nerdysoft.dto.forms.TaskForm;
import com.khrystyna.nerdysoft.models.Task;

public interface TaskService {
    Task save(TaskForm taskForm);
}
