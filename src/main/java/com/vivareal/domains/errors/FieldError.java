package com.vivareal.domains.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by fpillo on 3/25/2017.
 */
@Getter
@AllArgsConstructor
public class FieldError {

    private String field;

    private String message;

    private Object value;

}
