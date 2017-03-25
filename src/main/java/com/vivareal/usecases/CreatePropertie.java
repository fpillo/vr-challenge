package com.vivareal.usecases;

import com.vivareal.domains.Propertie;
import com.vivareal.domains.Province;
import com.vivareal.exceptions.PropertieAlreadyExistsException;
import com.vivareal.gateways.PropertieGateway;
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
public class CreatePropertie {

    private final PropertieGateway propertieGateway;

    private final ProvinceGateway provinceGateway;

    private final Validator validator;

    @Autowired
    public CreatePropertie(final PropertieGateway propertieGateway, final ProvinceGateway provinceGateway, final Validator validator) {
        this.propertieGateway = propertieGateway;
        this.provinceGateway = provinceGateway;
        this.validator = validator;
    }

    public Propertie create(final Propertie propertie) {
        insertId(propertie);
        checkPropertiePointExists(propertie);
        insertProvince(propertie);
        //Set<ConstraintViolation<Propertie>> violations = validator.validate(propertie);
        propertieGateway.save(propertie);

        return propertie;
    }

    private void insertId(final Propertie propertie) {
        propertie.setId(UUID.randomUUID().toString());
    }

    private void insertProvince(final Propertie propertie) {
        final Collection<Province> provinces = provinceGateway.findByPoint(propertie.getPoint());
        provinces.stream().map(province -> province.getName()).collect(Collectors.toList()).forEach(name -> {
            propertie.addProvince(name);
        });
    }

    private void checkPropertiePointExists(final Propertie propertie) {
        propertieGateway.findByPoint(propertie.getPoint()).ifPresent(p -> {throw new PropertieAlreadyExistsException(p);});
    }

}
