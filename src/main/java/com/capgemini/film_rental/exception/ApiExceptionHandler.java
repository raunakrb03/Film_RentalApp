
package com.capgemini.film_rental.exception;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@ControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFound(NotFoundException ex){ return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); }
}
