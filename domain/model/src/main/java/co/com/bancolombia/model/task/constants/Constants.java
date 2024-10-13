package co.com.bancolombia.model.task.constants;

import co.com.bancolombia.model.task.Task;

import java.util.List;

public class Constants {
    public static List<Task> initialTasks = List.of(
            new Task(1, "Listar tareas", "Crear las clases necesarias para listar las tareas"),
            new Task(2, "Crear una tarea", "Desarrollar la feature para crear tareas"),
            new Task(3, "Refactorizar", "Corregir código según feedback y dudas")
    );
}
