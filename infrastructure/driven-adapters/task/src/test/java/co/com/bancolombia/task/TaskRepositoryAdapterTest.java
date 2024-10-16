package co.com.bancolombia.task;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryAdapterTest {

    private TaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TaskRepositoryAdapter();
    }

    @Test
    @DisplayName("GIVEN there are existing tasks WHEN the findAll() fn is called THEN it should return the list of tasks")
    public void findAll() {
        // WHEN
        List<Task> result = this.repository.findAll();

        // THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("WHEN the create() fn is called THEN it should add the task to the array & increase the counter")
    public void create() {
        // GIVEN
        Task mockArg = new Task(null, "New title", "New description");

        // WHEN
        Task result = this.repository.create(mockArg);

        // THEN
        assertNotNull(result.id());
        assertEquals(result.title(), mockArg.title());
        assertEquals(result.description(), mockArg.description());
    }
}
