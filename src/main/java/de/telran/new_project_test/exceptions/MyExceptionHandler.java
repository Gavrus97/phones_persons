package de.telran.new_project_test.exceptions;

import de.telran.new_project_test.dto.response.HttpError;
import de.telran.new_project_test.dto.response.ValidationResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        var error = ValidationResponseDTO
                .builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<HttpError> handleHttpErrors(ResponseStatusException ex) {
        var error = HttpError
                .builder()
                .message(ex.getReason())
                .build();

        return ResponseEntity
                .status(ex.getStatus())
                .body(error);
    }
}
