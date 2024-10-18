package co.com.bancolombia.api;

import co.com.bancolombia.api.dtos.CreateTaskRequestBody;
import co.com.bancolombia.model.task.Task;
import co.com.bancolombia.usecase.task.TaskUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        // THEN
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(11))
                .andExpect(jsonPath("$[0].title").value("titulongo"))
                .andExpect(jsonPath("$[0].description").value("Una desc"))
                .andExpect(jsonPath("$[1].id").value(23))
                .andExpect(jsonPath("$[1].title").value("Titulito"))
                .andExpect(jsonPath("$[1].description").value("otra desc"));
    }

    @Test
    @DisplayName("GIVEN the request body is valid WHEN the POST:/tasks endpoint is called THEN it should return the created Task with status 200")
    public void create() throws Exception {
        // GIVEN
        CreateTaskRequestBody requestBody = new CreateTaskRequestBody("Titlee", "Descriptioooooon");
        Task mockTask = new Task(5, requestBody.title(), requestBody.description());
        Task arg = new Task(null, requestBody.title(), requestBody.description());

        when(this.useCase.create(arg)).thenReturn(mockTask);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(this.baseUrl)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON);

        // WHEN
        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        // THEN
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.title").value(mockTask.title()))
                .andExpect(jsonPath("$.description").value(mockTask.description()));
    }

    @Test
    @DisplayName("GIVEN the path variable id is valid WHEN the DELETE:/tasks/{id} endpoint is called THEN it should return 204 no content")
    public void delete() throws Exception {
        // GIVEN
        int pathVar = 1;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(this.baseUrl + "/" + pathVar)
                .contentType(MediaType.APPLICATION_JSON);

        // WHEN
        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        // THEN
        resultActions.andExpect(status().isNoContent());
    }
}
