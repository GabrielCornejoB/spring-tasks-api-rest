package co.com.bancolombia.task;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.model.task.gateways.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
    @DisplayName("GIVEN there is a task with the searched id WHEN the find() fn is called THEN it should return the task")
    public void find() {
        // Given
        int taskId = 1;

        // When
        Optional<Task> result = this.repository.find(taskId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(taskId, result.get().id());
    }

    @Test
    @DisplayName("GIVEN there is not a task with the searched id WHEN the find() fn is called THEN it should return empty")
    public void findNotFound() {
        // Given
        int taskId = 9999;

        // When
        Optional<Task> result = this.repository.find(taskId);

        // Then
        assertTrue(result.isEmpty());
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

    @Test
    @DisplayName("GIVEN there is a task with the same ID as the argument WHEN the delete fn is called THEN it should set the tasks array to an array with the task of the args")
    public void delete() {
        // GIVEN
        Integer existingId = 1;
        List<Task> initialTasks = this.repository.findAll();
        int initialSize = initialTasks.size();

        // WHEN
        this.repository.delete(existingId);

        // THEN
        List<Task> tasks = this.repository.findAll();
        assertNotEquals(initialSize, tasks.size());
        boolean taskExists = tasks.stream().anyMatch(task -> task.id().equals(existingId));
        assertFalse(taskExists);
    }
}
