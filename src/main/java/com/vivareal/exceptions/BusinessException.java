package com.vivareal.exceptions;

import com.vivareal.domains.errors.FieldError;
import lombok.Getter;

import java.util.Collection;

/**
 * Created by fpillo on 3/25/2017.
 */
@Getter
public class BusinessException extends RuntimeException {

    private static final String MESSAGE = "Constraints fieldErrors.";

    private Collection<FieldError> fieldErrors;

    public BusinessException(final Collection<FieldError> fieldErrors) {
        super(MESSAGE);
        this.fieldErrors = fieldErrors;
    }

}
