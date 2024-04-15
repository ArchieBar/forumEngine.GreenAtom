package ru.greenatom.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.greenatom.exception.message.MessageNotFoundException;
import ru.greenatom.exception.model.ErrorResponse;
import ru.greenatom.exception.topic.TopicDuplicateException;
import ru.greenatom.exception.topic.TopicNotFoundException;

import javax.validation.ValidationException;
import java.util.Arrays;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({
            TopicNotFoundException.class,
            MessageNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundObjectHandler(RuntimeException e) {
        return new ErrorResponse("Объект не найден.", e.getMessage());
    }

    @ExceptionHandler({
            TypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidInputExceptionHandler(Exception e) {
        return new ErrorResponse("Ошибка ввода.", e.getMessage());
    }

    @ExceptionHandler({
            TopicDuplicateException.class,
            DataIntegrityViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse duplicateObjectExceptionHandler(RuntimeException e) {
        return new ErrorResponse("Ошибка ранее созданного объекта.", e.getMessage());
    }

    @ExceptionHandler({
            ValidationException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse objectValidationExceptionHandler(Exception e) {
        return new ErrorResponse("Ошибка валидации.", e.getMessage());
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorResponse serverErrorHandler(Throwable e) {
        return new ErrorResponse("Ошибка сервера.", e.getMessage());
    }
}
