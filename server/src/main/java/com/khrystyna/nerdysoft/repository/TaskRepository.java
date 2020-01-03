package com.khrystyna.nerdysoft.repository;

import com.khrystyna.nerdysoft.models.Task;
import com.khrystyna.nerdysoft.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findAllByUsersContainsOrderByDateTimeDesc(User user);
}
