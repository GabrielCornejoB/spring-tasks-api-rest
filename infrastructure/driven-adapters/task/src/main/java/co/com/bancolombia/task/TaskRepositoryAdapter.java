package co.com.bancolombia.task;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.constants.Constants;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskRepositoryAdapter implements TaskRepository {

    private final List<Task> tasks = List.copyOf(Constants.initialTasks);

    @Override
    public List<Task> findAll() {
        return this.tasks;
    }
}
