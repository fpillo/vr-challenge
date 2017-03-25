package com.vivareal.http;

import com.vivareal.domains.errors.ResponseError;
import com.vivareal.exceptions.BusinessException;
import com.vivareal.exceptions.ResourceAlreadyExistsException;
import com.vivareal.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fpillo on 3/24/2017.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseError handleResourceNotFoundException(final ResourceNotFoundException ex) {
        return new ResponseError(ex.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseError handleResourceAlreadyExistsException(final ResourceAlreadyExistsException ex) {
        return new ResponseError(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError handleBusinessException(final BusinessException ex) {
        return new ResponseError(ex.getMessage(), ex.getFieldErrors());
    }

}
