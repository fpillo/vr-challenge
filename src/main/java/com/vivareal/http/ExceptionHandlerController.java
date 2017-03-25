package com.vivareal.http;

import com.vivareal.exceptions.BusinessException;
import com.vivareal.exceptions.ResourceAlreadyExistsException;
import com.vivareal.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fpillo on 3/24/2017.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException(final ResourceNotFoundException ex) {
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleResourceAlreadyExistsException(final ResourceAlreadyExistsException ex) {
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBusinessException(final BusinessException ex) {
    }

}
