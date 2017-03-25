package com.vivareal.exceptions;

/**
 * Created by fpillo on 3/24/2017.
 */
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }

}
