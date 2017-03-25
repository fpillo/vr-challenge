package com.vivareal.usecases;

import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Propertie;
import com.vivareal.exceptions.PropertieNotFoundException;
import com.vivareal.gateways.PropertieGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by fpillo on 3/24/2017.
 */
@Component
public class FindPropertie {

    private final PropertieGateway propertieGateway;

    @Autowired
    public FindPropertie(final PropertieGateway propertieGateway) {
        this.propertieGateway = propertieGateway;
    }

    public Propertie byId(final String id) {
        return propertieGateway.findById(id).orElseThrow(() -> new PropertieNotFoundException(id));
    }

    public Collection<Propertie> byBoundaries(final Boundaries boundaries) {
        return propertieGateway.findPropertieByBoundaries(boundaries);
    }

}
