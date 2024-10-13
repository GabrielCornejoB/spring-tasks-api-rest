package co.com.bancolombia.api;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.usecase.task.TaskUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
