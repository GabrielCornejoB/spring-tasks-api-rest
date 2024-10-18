package co.com.bancolombia.model.task;

import lombok.Data;

@Data
public class Task {
    Integer id;
    String title;
    String description;

    public Task(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Integer id() {
        return this.id;
    }

    public String title() {
        return this.title;
    }

    public String description() {
        return this.description;
    }
}
