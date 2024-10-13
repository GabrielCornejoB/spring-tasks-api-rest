package co.com.bancolombia.usecase.task;

import co.com.bancolombia.model.task.Task;

import java.util.List;

public interface ITaskUseCase {

    List<Task> findAll();

}
