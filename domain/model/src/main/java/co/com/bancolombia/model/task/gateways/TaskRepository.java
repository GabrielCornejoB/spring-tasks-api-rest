package co.com.bancolombia.model.task.gateways;

import co.com.bancolombia.model.task.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();

    Task create(Task task);

    void delete(Integer taskId);
}
