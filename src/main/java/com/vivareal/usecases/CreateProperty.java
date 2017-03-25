package com.vivareal.usecases;

import com.vivareal.domains.Property;
import com.vivareal.domains.Province;
import com.vivareal.exceptions.PropertyAlreadyExistsException;
import com.vivareal.gateways.PropertyGateway;
import com.vivareal.gateways.ProvinceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private final ValidateDomain validateDomain;

    @Autowired
    public CreateProperty(final PropertyGateway propertyGateway, final ProvinceGateway provinceGateway, final ValidateDomain validateDomain) {
        this.propertyGateway = propertyGateway;
        this.provinceGateway = provinceGateway;
        this.validateDomain = validateDomain;
    }

    public Property create(final Property property) {
        insertId(property);
        checkPropertiePointExists(property);
        insertProvince(property);
        validateDomain.validate(property);
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
        if (propertyGateway.findByPoint(property.getPoint()).isPresent()) {
            throw new PropertyAlreadyExistsException(property);
        }
    }

}
