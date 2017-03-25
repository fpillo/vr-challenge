package com.vivareal.exceptions;

import com.vivareal.domains.Property;

/**
 * Created by fpillo on 3/24/2017.
 */
public class PropertyAlreadyExistsException extends ResourceAlreadyExistsException {

    private static final String MESSAGE = "Property %s already exists.";

    public PropertyAlreadyExistsException(final Property property) {
        super(String.format(MESSAGE, property.toString()));
    }

}
