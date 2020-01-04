package com.khrystyna.nerdysoft.services.interfaces;

import com.khrystyna.nerdysoft.models.TaskSharing;

import java.util.List;

public interface TaskSharingService {

    List<TaskSharing> findSharedTasks();

    TaskSharing shareTask(String taskId, String receiverEmail);

    void acceptTask(String taskId);

    void declineTask(String taskId);

    void deleteAllByTaskId(String taskId);
}
