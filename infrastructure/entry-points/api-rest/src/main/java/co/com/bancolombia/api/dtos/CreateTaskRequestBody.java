package co.com.bancolombia.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequestBody(
        @NotBlank(message = "El campo título no debe estar vacío")
        @Size(min = 3, message = "El campo título debe tener al menos 3 carácteres")
        @Size(max = 20, message = "El campo título no puede tener más de 20 carácteres")
        String title,

        @NotBlank(message = "El campo descripción no debe estar vacío")
        @Size(min = 10, message = "El campo descripción debe tener al menos 10 carácteres")
        @Size(max = 100, message = "El campo título no puede tener más de 100 carácteres")
        String description
) {
}
