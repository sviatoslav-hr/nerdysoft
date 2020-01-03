package com.khrystyna.nerdysoft.dto;

import com.khrystyna.nerdysoft.models.TaskSharing;
import com.khrystyna.nerdysoft.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSharingDto {
    private String id;
    private TaskDto task;
    private User sender;
    private User receiver;
    private String dateTime;

    public TaskSharingDto(TaskSharing taskSharing) {
        this.id = taskSharing.getId();
        this.task = TaskDto.of(taskSharing.getTask());
        this.sender = taskSharing.getSender();
        this.receiver = taskSharing.getReceiver();
        this.dateTime = taskSharing.getDateTime().toString();
    }

    public static TaskSharingDto of(TaskSharing task) {
        return new TaskSharingDto(task);
    }
}
