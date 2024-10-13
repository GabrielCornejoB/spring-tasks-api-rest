package co.com.bancolombia.usecase.task;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
                new Task(11, "Titulo 1", "Una desc"),
                new Task(23, "Titulito", "Otra desc")
        );
        when(this.taskRepository.findAll()).thenReturn(tasksMock);

        // WHEN
        List<Task> result = this.useCase.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(tasksMock, result);
    }

}
