package com.salesianos.triana.dam.TrianaTourist.errores;


import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.EntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.errores.modelo.ApiError;
import com.salesianos.triana.dam.TrianaTourist.errores.modelo.ApiSubError;
import com.salesianos.triana.dam.TrianaTourist.errores.modelo.ApiValidationSubError;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        List<ApiSubError> subErrorList = new ArrayList<>();

        ex.getAllErrors().forEach(error -> {

            // Si el error de validación se ha producido a raíz de una anotación en un atributo...
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;

                subErrorList.add(
                        ApiValidationSubError.builder()
                                .objeto(fieldError.getObjectName())
                                .campo(fieldError.getField())
                                .valorRechazado(fieldError.getRejectedValue())
                                .mensaje(fieldError.getDefaultMessage())
                                .build()
                );
            }
            else
            {
                ObjectError objectError = (ObjectError) error;

                subErrorList.add(
                        ApiValidationSubError.builder()
                                .objeto(objectError.getObjectName())
                                .mensaje(objectError.getDefaultMessage())
                                .build()
                );
            }


        });

        return buildApiError("Hay errores en el formulario", request, subErrorList);

    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return buildApiError(ex, request);
    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstrintViolationException(ConstraintViolationException ex, WebRequest request) {
        return buildApiError("Errores varios en la validación",
                request,
                ex.getConstraintViolations()
                        .stream()
                        .map(cv -> ApiValidationSubError.builder()
                                .objeto(cv.getRootBeanClass().getSimpleName())
                                .campo(((PathImpl)cv.getPropertyPath()).getLeafNode().asString())
                                .valorRechazado(cv.getInvalidValue())
                                .mensaje(cv.getMessage())
                                .build())
                        .collect(Collectors.toList())
        );

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiError(ex, request);
    }

    private final ResponseEntity<Object> buildApiError(Exception ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI()));

    }

    private ResponseEntity<Object> buildApiError(String mensaje, WebRequest request, List<ApiSubError> subErrores) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(HttpStatus.BAD_REQUEST, mensaje, ((ServletWebRequest) request).getRequest().getRequestURI(), subErrores));

    }

}
