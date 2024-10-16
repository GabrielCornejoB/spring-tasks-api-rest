package co.com.bancolombia.task;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.constants.Constants;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskRepositoryAdapter implements TaskRepository {

    List<Task> tasks = new ArrayList<>(Constants.initialTasks);

    private Integer counter = this.tasks.size();

    @Override
    public List<Task> findAll() {
        return this.tasks;
    }

    @Override
    public Task create(Task task) {
        this.counter++;
        Task newTask = new Task(this.counter, task.title(), task.description());
        this.tasks.add(newTask);
        return newTask;
    }
}
