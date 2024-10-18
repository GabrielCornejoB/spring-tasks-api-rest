package co.com.bancolombia.usecase.task;

import co.com.bancolombia.model.exceptions.AlreadyExistsException;
import co.com.bancolombia.model.exceptions.NotFoundException;
import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskUseCaseTest {

    @Mock
    private TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

    @InjectMocks
    private TaskUseCase useCase;

    @BeforeEach
    void setup() {
        this.useCase = new TaskUseCase(this.taskRepository);
    }

    @Test
    @DisplayName("GIVEN there are existing tasks WHEN the findAll() fn is called THEN it should return the list of tasks")
    void getAllTasks() {
        // GIVEN
        List<Task> tasksMock = List.of(
                new Task(1, "Titulo 1", "Una desc"),
                new Task(2, "Titulito", "Otra desc")
        );
        when(this.taskRepository.findAll()).thenReturn(tasksMock);

        // WHEN
        List<Task> result = this.useCase.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(tasksMock, result);
    }

    @Test
    @DisplayName("GIVEN there are not tasks with the same title as the new task WHEN the create() fn is called THEN it should create the task")
    void createTaskSuccess() {
        // GIVEN
        List<Task> tasksMock = List.of(
                new Task(1, "Titulo 1", "Una desc"),
                new Task(2, "Titulo 2", "Otra desc")
        );
        Task mockTask = new Task(null, "Nuevo título", "Una nueva descripción");
        when(this.taskRepository.findAll()).thenReturn(tasksMock);
        when(this.taskRepository.create(mockTask)).thenReturn(new Task(3, mockTask.title(), mockTask.description()));

        // WHEN
        Task result = this.useCase.create(mockTask);

        // THEN
        verify(this.taskRepository, times(1)).findAll();
        verify(this.taskRepository, times(1)).create(mockTask);
        assertEquals(result.title(), mockTask.title());
        assertEquals(result.description(), mockTask.description());
        assertNotNull(result.id());
    }

    @Test
    @DisplayName("GIVEN there is a task with the same title as the new task WHEN the create() fn is called THEN it should throw an error")
    void createTaskAlreadyExistsException() {
        // GIVEN
        List<Task> tasksMock = List.of(
                new Task(1, "Titulo 1", "Una desc"),
                new Task(2, "Titulo 2", "Otra desc")
        );
        Task mockTask = new Task(null, "Titulo 1", "Una nueva descripción");
        when(this.taskRepository.findAll()).thenReturn(tasksMock);

        // WHEN
        assertThrows(AlreadyExistsException.class, () -> this.useCase.create(mockTask));

        // THEN
        verify(this.taskRepository, never()).create(mockTask);
    }

    @Test
    @DisplayName("GIVEN there is a task with the ID to delete WHEN the delete() fn is called THEN it should delete the task")
    void deleteSuccess() {
        // GIVEN
        List<Task> tasksMock = List.of(
                new Task(1, "Titulo 1", "Una desc"),
                new Task(2, "Titulo 2", "Otra desc")
        );
        when(this.taskRepository.findAll()).thenReturn(tasksMock);
        int existingId = 1;

        // WHEN
        this.useCase.delete(existingId);

        // THEN
        verify(this.taskRepository, times(1)).delete(existingId);
    }

    @Test
    @DisplayName("GIVEN there is not a task with the ID to delete WHEN the delete() fn is called THEN it throw an error")
    void deleteNotFoundException() {
        // GIVEN
        List<Task> tasksMock = List.of(
                new Task(1, "Titulo 1", "Una desc"),
                new Task(2, "Titulo 2", "Otra desc")
        );
        when(this.taskRepository.findAll()).thenReturn(tasksMock);
        int existingId = 3;

        // WHEN
        assertThrows(NotFoundException.class, () -> this.useCase.delete(existingId));

        // THEN
        verify(this.taskRepository, never()).delete(existingId);
    }
}
