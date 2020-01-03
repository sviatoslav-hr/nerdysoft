package com.khrystyna.nerdysoft.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskShareForm {

    @NotNull
    private String taskId;

    @NotNull
    private String receiverEmail;
}
