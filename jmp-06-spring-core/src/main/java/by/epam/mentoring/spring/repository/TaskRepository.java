package by.epam.mentoring.spring.repository;

import by.epam.mentoring.spring.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, Integer> {

    List<Task> findByTaskArchived(Integer isTaskArchived);

    List<Task> findByTaskStatus(String taskStatus);

}
