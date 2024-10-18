package co.com.bancolombia.model.task.gateways;

import co.com.bancolombia.model.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    Optional<Task> find(int id);

    Task create(Task task);

    void delete(Integer taskId);
}
