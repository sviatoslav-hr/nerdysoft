package com.khrystyna.nerdysoft.repository;

import com.khrystyna.nerdysoft.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
