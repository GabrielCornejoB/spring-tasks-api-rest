package co.com.bancolombia.task;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.constants.Constants;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Task update(Task updatedTask) {
        this.tasks = this.tasks.stream()
                .map(
                        task -> {
                            if (updatedTask.description() == null) updatedTask.setDescription(task.description());
                            return Objects.equals(task.id(), updatedTask.id()) ? updatedTask : task;
                        }
                )
                .collect(Collectors.toList());

        return updatedTask;
    }

    @Override
    public void delete(Integer taskId) {
        int index = this.tasks.stream().map(Task::id).toList().indexOf(taskId);
        this.tasks.remove(index);
    }
}
