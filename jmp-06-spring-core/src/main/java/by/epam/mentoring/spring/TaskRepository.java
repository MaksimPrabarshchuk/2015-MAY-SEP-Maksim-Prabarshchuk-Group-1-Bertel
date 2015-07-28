package by.epam.mentoring.spring;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByTaskArchived(@Param("archivedfalse") int taskArchivedFalse);

    List<Task> findByTaskStatus(@Param("status") String taskStatus);

}
