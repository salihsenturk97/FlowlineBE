package com.flowline.ws.core.error;

import com.flowline.ws.core.exceptions.AuthenticationException;
import com.flowline.ws.core.shared.Messages;
import com.flowline.ws.core.exceptions.ActivationNotificationException;
import com.flowline.ws.core.exceptions.InvalidTokenException;
import com.flowline.ws.core.exceptions.NotFoundException;
import com.flowline.ws.core.exceptions.NotUniqueEmailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            NotUniqueEmailException.class,
            ActivationNotificationException.class,
            InvalidTokenException.class,
            NotFoundException.class,
            AuthenticationException.class

    })
    public ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request) {
        System.err.println(LocaleContextHolder.getLocale());
        ApiError error = new ApiError();
        error.setPath(request.getRequestURI());
        error.setMessage(exception.getMessage());

        if (exception instanceof MethodArgumentNotValidException) {
            String message = Messages.getMessageForLocale("flowline.error.validation", LocaleContextHolder.getLocale());
            error.setMessage(message);
            error.setStatus(400);
            Map<String, String> validationErrors = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existing, replacing) -> existing
                    ));
            error.setValidationErrors(validationErrors);
        } else if (exception instanceof NotUniqueEmailException) {
            error.setStatus(400);
            error.setValidationErrors(((NotUniqueEmailException) exception).getValidationErrors());
        } else if (exception instanceof ActivationNotificationException) {
            error.setStatus(502);
        } else if (exception instanceof InvalidTokenException) {
            error.setStatus(400);
        } else if (exception instanceof NotFoundException) {
            error.setStatus(404);
        } else if (exception instanceof AuthenticationException) {
            error.setStatus(401);
        }
        return ResponseEntity.status(error.getStatus()).body(error);
    }

//
//    @ExceptionHandler(NotUniqueEmailException.class)
//    @ResponseStatus(HttpStatus.BAD_GATEWAY)
//    public ResponseEntity<ApiError> handleNotUniqueEmailException(NotUniqueEmailException exception) {
//        ApiError error = new ApiError(502, exception.getMessage(), "/api/v1/users");
//        error.setValidationErrors(exception.getValidationErrors());
//        return ResponseEntity.badRequest().body(error);
//    }
//
//
//    @ExceptionHandler(ActivationNotificationException.class)
//    @ResponseStatus(HttpStatus.BAD_GATEWAY)
//    public ResponseEntity<ApiError> handleActivationNotificationException(ActivationNotificationException exception) {
//        ApiError error = new ApiError(500, exception.getMessage(), "/api/v1/users");
//        return ResponseEntity.status(502).body(error);
//    }
//
//    @ExceptionHandler(InvalidTokenException.class)
//    public ResponseEntity<ApiError> handleActivationNotificationException(InvalidTokenException exception,) {
//        ApiError error = new ApiError(400, exception.getMessage(), request.getRequestURI());
//        return ResponseEntity.status(400).body(error);
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ApiError> handleEntityNotFoundException(NotFoundException exception, HttpServletRequest request) {
//        ApiError error = new ApiError(404, exception.getMessage(), request.getRequestURI());
//        return ResponseEntity.status(404).body(error);
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    ResponseEntity handleAuthenticationException(AuthenticationException exception) {
//        ApiError error = new ApiError(401, exception.getMessage(), "api/v1/auth");
//        return ResponseEntity.status(401).body(error);
//    }


}
