# Problema: API de Gestión de Tareas

### Contexto

En una pequeña empresa de tecnología, el equipo de desarrollo necesita una solución sencilla para gestionar las tareas
de su equipo de manera eficiente. Actualmente, las tareas se organizan manualmente y a menudo se pierden o duplican, lo
que afecta la productividad. Por ello, se ha decidido implementar una API REST que permita a los miembros del equipo
crear, actualizar, eliminar y visualizar tareas de forma centralizada. La API debe ser fácil de integrar con cualquier
aplicación o herramienta interna, permitir la gestión de las tareas de manera eficiente y garantizar la consistencia de
los datos, brindando a los desarrolladores una plataforma para optimizar su flujo de trabajo diario.

### Requisitos:

Crea una API REST para gestionar una lista de tareas. La API debe permitir las siguientes operaciones:

1. **Crear una nueva tarea**: La tarea tendrá un título y una descripción.

2. **Obtener la lista de todas las tareas**: Deberías poder obtener todas las tareas con sus detalles.

3. **Obtener una tarea por su ID**: Deberías poder obtener una tarea específica usando su identificador único.

4. **Actualizar una tarea**: Podrás modificar el título y la descripción de una tarea existente.

5. **Eliminar una tarea**: Deberías poder eliminar una tarea de la lista.

### Detalles de las tareas:

- Cada tarea debe tener un identificador único (ID).

- El título de la tarea no debe estar vacío.

- La descripción de la tarea es opcional más No NULL.

### Endpoints sugeridos:

1. **POST /tasks**

   Crea una nueva tarea.

    - Request body (JSON):

      ```json

      {

        "title": "Comprar leche",

        "description": "Ir al supermercado a comprar leche"

      }

      ```

    - Respuesta esperada (201 Created):

      ```json

      {

        "id": 1,

        "title": "Comprar leche",

        "description": "Ir al supermercado a comprar leche"

      }

      ```


2. **GET /tasks**

   Obtiene la lista de todas las tareas.

    - Respuesta esperada (200 OK):

      ```json

      [

        {

          "id": 1,

          "title": "Comprar leche",

          "description": "Ir al supermercado a comprar leche"

        },

        {

          "id": 2,

          "title": "Estudiar programación",

          "description": "Terminar el capítulo de APIs REST"

        }

      ]

      ```


3. **GET /tasks/{id}**

   Obtiene una tarea por su ID.

    - Respuesta esperada (200 OK):

      ```json

      {

        "id": 1,

        "title": "Comprar leche",

        "description": "Ir al supermercado a comprar leche"

      }

      ```

    - Si no existe la tarea con ese ID, devolver (404 Not Found).


4. **PUT /tasks/{id}**

   Actualiza una tarea existente.

    - Request body (JSON):

      ```json

      {

        "title": "Comprar pan",

        "description": "Ir al supermercado a comprar pan"

      }

      ```

    - Respuesta esperada (200 OK):

      ```json

      {

        "id": 1,

        "title": "Comprar pan",

        "description": "Ir al supermercado a comprar pan"

      }

      ```


5. **DELETE /tasks/{id}**

   Elimina una tarea por su ID.

    - Respuesta esperada (204 No Content).

### Criterios de evaluación:

- (1.4) Estructura clara y ordenada del proyecto. (pueden usar clean arch o un diseño MVC en una sola app) EMPAQUETAR
  POR FEATURE MÁS NO POR LAYER

- (1.4) Manejo adecuado de errores (404, 400, etc.).

- (1.0) Los errores 5xx no deben ser renderizados para la aplicación cliente.

- (1.4) Buenas prácticas de diseño REST (verbos HTTP correctos, códigos de estado).

- (2.0) Cobertura de **pruebas unitarias al 100%**

- (0.8) Documentación mínima (cómo ejecutar y probar la API).

- (1.4) presentar el código y manual de ejecución del proyecto en un repositorio en git (puede ser github, bitbucket,
  gitlab o en zip pero en formato .rar)

 