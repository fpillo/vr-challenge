package com.vivareal.usecases;

import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Property;
import com.vivareal.exceptions.PropertyNotFoundException;
import com.vivareal.gateways.PropertyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by fpillo on 3/24/2017.
 */
@Component
public class FindProperty {

    private final PropertyGateway propertyGateway;

    @Autowired
    public FindProperty(final PropertyGateway propertyGateway) {
        this.propertyGateway = propertyGateway;
    }

    public Property byId(final String id) {
        return propertyGateway.findById(id).orElseThrow(() -> new PropertyNotFoundException(id));
    }

    public Collection<Property> byBoundaries(final Boundaries boundaries) {
        return propertyGateway.findPropertiesByBoundaries(boundaries);
    }

}
