package ru.nazarov.practice.response.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nazarov.practice.exception.DataNotFoundException;
import ru.nazarov.practice.organization.controller.OrganizationController;
import ru.nazarov.practice.response.view.ErrorView;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Handler для перехвата исключений
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView unhandledException(Exception e) {
        String errorCode = UUID.randomUUID().toString();
        logger.error(e.getMessage() + " Error code: " + errorCode);
        return new ErrorView("Internal Server Error; Status code: " + HttpStatus.INTERNAL_SERVER_ERROR + ". Error code: " + errorCode);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorView dataNotFound(DataNotFoundException ex) {
        return new ErrorView(ex.getMessage() + "; Status code: " + HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorView notValidArgument(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        String notValidFields = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". "));

        return new ErrorView(notValidFields + "; Status code: " + HttpStatus.BAD_REQUEST);
    }

}
