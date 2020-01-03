package com.khrystyna.nerdysoft.repository;

import com.khrystyna.nerdysoft.models.Task;
import com.khrystyna.nerdysoft.models.TaskSharing;
import com.khrystyna.nerdysoft.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskSharingRepository extends MongoRepository<TaskSharing, String> {

    List<TaskSharing> findAllByReceiverOrderByDateTimeDesc(User receiver);

    void deleteByReceiverAndTask(User receiver, Task task);
}