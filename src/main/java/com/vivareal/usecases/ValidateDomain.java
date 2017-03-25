package com.vivareal.usecases;

import com.vivareal.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by fpillo on 3/25/2017.
 */
@Component
public class ValidateDomain {

    private final Validator validator;

    @Autowired
    public ValidateDomain(final Validator validator) {
        this.validator = validator;
    }

    public void validate(final Object object) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new BusinessException();
        }
    }


}
