package com.khrystyna.nerdysoft.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    @LastModifiedDate
    private LocalDateTime dateTime;
    @DBRef
    private User author;
    @DBRef
    private List<User> users = new LinkedList<>();
}
