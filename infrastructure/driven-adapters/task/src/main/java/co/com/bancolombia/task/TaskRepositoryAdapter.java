package co.com.bancolombia.task;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.constants.Constants;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskRepositoryAdapter implements TaskRepository {

    List<Task> tasks = new ArrayList<>(Constants.initialTasks);

    private Integer counter = 3;

    @Override
    public List<Task> findAll() {
        return this.tasks;
    }

    @Override
    public Optional<Task> find(int taskId) {
        return this.tasks.stream().filter(task -> task.id() == taskId).findFirst();
    }

    @Override
    public Task create(Task task) {
        this.counter++;
        Task newTask = new Task(this.counter, task.title(), task.description());
        this.tasks.addLast(newTask);
        return newTask;
    }

    @Override
    public void delete(Integer taskId) {
        int index = this.tasks.stream().map(Task::id).toList().indexOf(taskId);
        this.tasks.remove(index);
    }
}
