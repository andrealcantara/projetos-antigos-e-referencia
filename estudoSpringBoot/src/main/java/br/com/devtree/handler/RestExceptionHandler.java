package br.com.devtree.handler;

import br.com.devtree.error.ErrorDetails;
import br.com.devtree.error.ResourceNotFoundDetails;
import br.com.devtree.error.ResourceNotFoundException;
import br.com.devtree.error.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException){
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .details(rnfException.getMessage())
                .developersMessage(rnfException.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }



    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        List<FieldError> streamErr = ex.getBindingResult().getFieldErrors();
        ValidationErrorDetails validationErrorDetails = ValidationErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field Validation Error")
                .details(HttpStatus.BAD_REQUEST.toString())
                .field(streamErr.stream().map(FieldError::getField).collect(Collectors.joining("], [","[","]")))
                .fieldMessage(streamErr.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("], [","[","]")))
                .developersMessage(ex.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex,  Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.ErrorDetailsBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title(" - Internal Exception - ")
                .details(ex.getMessage())
                .developersMessage(ex.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }
}
