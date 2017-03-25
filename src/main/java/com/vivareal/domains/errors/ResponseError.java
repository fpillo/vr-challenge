package com.vivareal.domains.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Collection;

/**
 * Created by fpillo on 3/25/2017.
 */
@Getter
public class ResponseError {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<FieldError> fieldErrors;

    public ResponseError(final String message) {
        this.message = message;
    }

    public ResponseError(final String message, final Collection<FieldError> fieldErrors) {
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

}
