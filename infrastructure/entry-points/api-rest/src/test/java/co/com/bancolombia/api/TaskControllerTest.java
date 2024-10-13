package co.com.bancolombia.api;

import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.usecase.task.TaskUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(
        loader = AnnotationConfigWebContextLoader.class,
        classes = {TaskController.class, TaskControllerTest.class}
)
@WebMvcTest(value = TaskController.class)
class TaskControllerTest {

    private final String baseUrl = "/api/tasks";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskUseCase useCase;

    @Test
    @DisplayName("GIVEN there are tasks created WHEN the GET:/tasks endpoint is called THEN it should return the tasks with status 200")
    public void getAll() throws Exception {
        // GIVEN
        List<Task> mockTasks = List.of(
                new Task(11, "titulongo", "Una desc"),
                new Task(23, "Titulito", "otra desc")
        );
        when(this.useCase.findAll()).thenReturn(mockTasks);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(this.baseUrl).contentType(MediaType.APPLICATION_JSON);

        // WHEN
        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        // THEN (MISSING ASSERTIONS)
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(11))
                .andExpect(jsonPath("$[0].title").value("titulongo"))
                .andExpect(jsonPath("$[0].description").value("Una desc"))
                .andExpect(jsonPath("$[1].id").value(23))
                .andExpect(jsonPath("$[1].title").value("Titulito"))
                .andExpect(jsonPath("$[1].description").value("otra desc"));
    }
}
