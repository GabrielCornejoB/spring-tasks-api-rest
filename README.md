# API de gestión de tareas con Spring + Bancolombia Clean Architecture

## Como ejecutar

Para ejecutar el proyecto se debe de primero cargar el proyecto de gradle y luego ejecutar la clase `MainApplication`
con
su función `main()`. Una vez nuestro servidor en local esté ejecutando podemos realizar las distintas peticiones:

### Obtener todas las tareas

Para obtener todas las tareas se debe llamar el endpoint `http://localhost:8080/api/tasks` con una petición tipo `GET`.
Por defecto cada vez que se ejecuta el programa viene con 3 tareas pre-cargadas. Esta petición no requiere ningún cuerpo
ni parámetro en la petición.

### Obtener una tarea

Para obtener una tarea se debe llamar al endpoint `http://localhost:8080/api/tasks/{id}` con una petición tipo `GET`
donde en vez del `{id}` debe ir el ID de la tarea que se quiere consultar. Ej: `http://localhost:8080/api/tasks/2`.

### Crear una tarea

Para crear una se debe llamar al endpoint `http://localhost:8080/api/tasks` con una petición tipo `POST`. Esta petición
requiere de un cuerpo de tipo texto en formato JSON con las propiedades: `title` y `description`. Estas tienen las
siguientes validaciones:

- El título no puede ser vacío y debe tener entre 3 y 20 carácteres
- La descripción no puede ser vacía y debe tener entre 10 y 100 carácteres

Un ejemplo del cuerpo sería tal que así:

```json
{
  "title": "a desc1",
  "description": "A description"
}
```

### Eliminar una tarea

Para obtener una tarea se debe llamar al endpoint `http://localhost:8080/api/tasks/{id}` con una petición tipo `DELETE`
donde en vez del `{id}` debe ir el ID de la tarea que se quiere eliminar. Ej: `http://localhost:8080/api/tasks/2`.

### Actualizar una tarea

Para crear una se debe llamar al endpoint `http://localhost:8080/api/tasks/{id}` con una petición tipo `PUT` donde en
vez
del `{id}` debe ir el ID de la tarea que se quiere eliminar. Esta petición requiere permite dos posibles cuerpos:
Uno incluyendo la descripción y otro sin incluírla. Además, los campos cuentan con las mismas validaciones que el
endpoint de crear.
A excepción de que la descripción puede no ser enviada.

Un ejemplo del cuerpo con descripción sería tal que así:

```json
{
  "title": "new title",
  "description": "new description"
}
```

Un ejemplo del cuerpo sin descripción sería tal que así:

```json
{
  "title": "a new desc"
}
```