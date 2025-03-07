package com.example.Media.advice;

<<<<<<< HEAD

=======
>>>>>>> master
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import java.security.SignatureException;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

//Global exception handler for the application.
@RestControllerAdvice
@Slf4j
public class ApplicationControllerAdvice {

<<<<<<< HEAD
  //Handles AccessDeniedException and returns a ProblemDetail response.
  @ResponseStatus(FORBIDDEN)
  @ExceptionHandler(value = AccessDeniedException.class)
  public @ResponseBody
  ProblemDetail BadCredentialsException(final AccessDeniedException exception) {
    ApplicationControllerAdvice.log.error(exception.getMessage(), exception);

    return ProblemDetail.forStatusAndDetail(
      FORBIDDEN,
      "vos droit ne vous pas s'effectuer cette action"
    );
  }

  //Handles BadCredentialsException and returns a ProblemDetail response.
=======
  // Handles AccessDeniedException and returns a ProblemDetail response.
  @ResponseStatus(FORBIDDEN)
  @ExceptionHandler(value = AccessDeniedException.class)
  public @ResponseBody ProblemDetail BadCredentialsException(final AccessDeniedException exception) {
    ApplicationControllerAdvice.log.error(exception.getMessage(), exception);

    return ProblemDetail.forStatusAndDetail(
        FORBIDDEN,
        "vos droit ne vous pas s'effectuer cette action");
  }

  // Handles BadCredentialsException and returns a ProblemDetail response.
>>>>>>> master
  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler(value = BadCredentialsException.class)
  public @ResponseBody ProblemDetail handleBadCredentialsException(final BadCredentialsException exception) {
    log.error(exception.getMessage(), exception);
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
<<<<<<< HEAD
      UNAUTHORIZED,
      "Identifiants invalides"
    );
=======
        UNAUTHORIZED,
        "Identifiants invalides");
>>>>>>> master
    problemDetail.setProperty("error", "Nous n'avons pas pu vous identifier");
    return problemDetail;
  }

<<<<<<< HEAD
  //Handles token-related exceptions and returns a ProblemDetail response.
  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler(value = {MalformedJwtException.class, SignatureException.class})
=======
  // Handles token-related exceptions and returns a ProblemDetail response.
  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler(value = { MalformedJwtException.class, SignatureException.class })
>>>>>>> master
  public @ResponseBody ProblemDetail handleTokenException(final Exception exception) {
    log.error(exception.getMessage(), exception);

    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
<<<<<<< HEAD
      UNAUTHORIZED,
      "Token invalides"
    );
=======
        UNAUTHORIZED,
        "Token invalides");
>>>>>>> master
    problemDetail.setProperty("error", "Token mal formé ou signature invalide");

    return problemDetail;
  }

<<<<<<< HEAD
  //Handles HttpMessageNotReadableException and returns a custom JSON error response.
=======
  // Handles HttpMessageNotReadableException and returns a custom JSON error
  // response.
>>>>>>> master
  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    ApiResponse response = new ApiResponse(
<<<<<<< HEAD
      BAD_REQUEST.value(),
      "Invalid JSON",
      "Invalid JSON format. Please provide valid JSON.",
      null,
      ex.getMessage()
    );
    return ResponseEntity.badRequest().body(response);
  }

  //Handles ResourceNotFoundException and returns a custom JSON error response.
=======
        BAD_REQUEST.value(),
        "Invalid JSON",
        "Invalid JSON format. Please provide valid JSON.",
        null,
        ex.getMessage());
    return ResponseEntity.badRequest().body(response);
  }

  // Handles ResourceNotFoundException and returns a custom JSON error response.
>>>>>>> master
  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ApiResponse response = new ApiResponse(
<<<<<<< HEAD
      NOT_FOUND.value(),
      "Resource Not Found",
      ex.getMessage(),
      null,
      "Resource not found"
    );
    return ResponseEntity.status(NOT_FOUND).body(response);
  }

  //Handles other generic exceptions and returns a Map response.
=======
        NOT_FOUND.value(),
        "Resource Not Found",
        ex.getMessage(),
        null,
        "Resource not found");
    return ResponseEntity.status(NOT_FOUND).body(response);
  }

  // Handles other generic exceptions and returns a Map response.
>>>>>>> master
  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler(value = Exception.class)
  public @ResponseBody Map<String, String> handleGenericException() {
    return Map.of("error", "Description");
  }
}
