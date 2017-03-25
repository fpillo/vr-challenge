package com.vivareal.exceptions;

/**
 * Created by fpillo on 3/24/2017.
 */
public class PropertyNotFoundException extends ResourceNotFoundException {

    private static final String MESSAGE = "Property %s not found.";

    public PropertyNotFoundException(final String id) {
        super(String.format(MESSAGE, id));
    }

}
