package co.com.bancolombia.model.exceptions;

import java.util.Date;

public record CustomError(
        Integer statusCode,
        String message,
        Date timestamp
) {
}
