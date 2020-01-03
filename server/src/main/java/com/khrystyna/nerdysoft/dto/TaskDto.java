package com.khrystyna.nerdysoft.dto;

import com.khrystyna.nerdysoft.models.Task;
import com.khrystyna.nerdysoft.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String id;
    private String title;
    private String description;
    private String dateTime;
    private User author;
    private List<User> users;

    private TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dateTime = task.getDateTime() != null ? task.getDateTime().toString() : "";
        this.author = task.getAuthor();
        this.users = task.getUsers();
    }

    public static TaskDto of(Task task) {
        return new TaskDto(task);
    }
}
