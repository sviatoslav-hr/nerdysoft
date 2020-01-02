package com.khrystyna.nerdysoft.dto.forms;

import com.khrystyna.nerdysoft.models.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskForm {
    private String id;
    private String title;
    private String description;
    private String authorId;
    private List<String> usersIds;

    public Task toTask() {
        return Task.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .build();
    }
}
