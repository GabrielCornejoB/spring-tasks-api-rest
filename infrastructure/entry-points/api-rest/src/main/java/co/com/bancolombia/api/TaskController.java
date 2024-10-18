package co.com.bancolombia.api;

import co.com.bancolombia.api.dtos.CreateTaskRequestBody;
import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.usecase.task.TaskUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TaskController {
    private final TaskUseCase taskUseCase;

    @GetMapping()
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok().body(this.taskUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> find(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok().body(this.taskUseCase.find(id));
    }

    @PostMapping()
    public ResponseEntity<Task> create(
            @Valid @RequestBody CreateTaskRequestBody requestBody
    ) {
        return ResponseEntity.ok().body(this.taskUseCase.create(
                new Task(null, requestBody.title(), requestBody.description())
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        this.taskUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
