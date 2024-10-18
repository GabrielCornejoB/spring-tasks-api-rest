package co.com.bancolombia.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record UpdateTaskRequestBody(
        @NotBlank(message = "El campo título no debe estar vacío")
        @Size(min = 3, message = "El campo título debe tener al menos 3 carácteres")
        @Size(max = 20, message = "El campo título no puede tener más de 20 carácteres")
        String title,


        Optional<
                @Pattern(regexp = "^(?!\\s*$).+", message = "El campo no debe constar únicamente de carácteres en blanco")
                @Size(min = 10, message = "El campo descripción debe tener al menos 10 carácteres")
                @Size(max = 100, message = "El campo título no puede tener más de 100 carácteres")
                        String> description
) {
}
