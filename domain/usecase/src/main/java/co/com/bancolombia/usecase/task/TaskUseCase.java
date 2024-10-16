package co.com.bancolombia.usecase.task;

import co.com.bancolombia.model.exceptions.AlreadyExistsException;
import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class TaskUseCase {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Task create(Task task) {
        if (this.taskRepository.findAll().stream().map(Task::title).toList().contains(task.title())) {
            throw new AlreadyExistsException(
                    "Error al crear la tarea. Ya existe una tarea con el título '" + task.title() + "'."
            );
        }
        return this.taskRepository.create(task);
    }
}
