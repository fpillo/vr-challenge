package com.vivareal.usecases;

import com.vivareal.domains.Property;
import com.vivareal.domains.Province;
import com.vivareal.exceptions.PropertyAlreadyExistsException;
import com.vivareal.gateways.PropertyGateway;
import com.vivareal.gateways.ProvinceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by fpillo on 3/24/2017.
 */
@Component
public class CreateProperty {

    private final PropertyGateway propertyGateway;

    private final ProvinceGateway provinceGateway;

    private final Validator validator;

    @Autowired
    public CreateProperty(final PropertyGateway propertyGateway, final ProvinceGateway provinceGateway, final Validator validator) {
        this.propertyGateway = propertyGateway;
        this.provinceGateway = provinceGateway;
        this.validator = validator;
    }

    public Property create(final Property property) {
        insertId(property);
        checkPropertiePointExists(property);
        insertProvince(property);
        //Set<ConstraintViolation<Property>> violations = validator.validate(property);
        propertyGateway.save(property);

        return property;
    }

    private void insertId(final Property property) {
        property.setId(UUID.randomUUID().toString());
    }

    private void insertProvince(final Property property) {
        final Collection<Province> provinces = provinceGateway.findByPoint(property.getPoint());
        provinces.stream().map(province -> province.getName()).collect(Collectors.toList()).forEach(name -> {
            property.addProvince(name);
        });
    }

    private void checkPropertiePointExists(final Property property) {
        propertyGateway.findByPoint(property.getPoint()).ifPresent(p -> {throw new PropertyAlreadyExistsException(p);});
    }

}
