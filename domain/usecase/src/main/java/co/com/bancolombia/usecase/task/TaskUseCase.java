package co.com.bancolombia.usecase.task;

import co.com.bancolombia.model.exceptions.AlreadyExistsException;
import co.com.bancolombia.model.exceptions.NotFoundException;
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

    public Task find(int id) {
        return this.taskRepository.find(id).orElseThrow(
                () -> new NotFoundException("La tarea con el id '" + id + "' no existe.")
        );
    }

    public Task create(Task task) {
        if (this.taskTitleAlreadyExists(task.title())) {
            throw new AlreadyExistsException(
                    "Error al crear la tarea. Ya existe una tarea con el título '" + task.title() + "'."
            );
        }
        return this.taskRepository.create(task);
    }

    public Task update(Task task) {
        if (this.taskRepository.find(task.id()).isEmpty()) {
            throw new NotFoundException(
                    "Error al actualizar la tarea. No existe una tarea con el ID '" + task.id() + "'."
            );
        }
        if (this.taskTitleAlreadyExists(task.title())) {
            throw new AlreadyExistsException(
                    "Error al actualizar la tarea. Ya existe una tarea con el título '" + task.title() + "'."
            );
        }
        return this.taskRepository.update(task);
    }

    public void delete(Integer taskId) {
        if (this.taskRepository.find(taskId).isEmpty()) {
            throw new NotFoundException(
                    "Error al eliminar la tarea. No existe una tarea con el ID '" + taskId + "'."
            );
        }
        this.taskRepository.delete(taskId);
    }

    private boolean taskTitleAlreadyExists(String title) {
        return this.taskRepository.findAll().stream().map(Task::title).toList().contains(title);
    }
}
