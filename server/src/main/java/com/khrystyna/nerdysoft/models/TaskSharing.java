package com.khrystyna.nerdysoft.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskSharing {
    @Id
    private String id;
    @DBRef
    private Task task;
    @DBRef
    private User sender;
    @DBRef
    private User receiver;
    private LocalDateTime dateTime;
}
