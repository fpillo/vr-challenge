package com.vivareal.gateways.inmemory;

import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Point;
import com.vivareal.domains.Property;
import com.vivareal.domains.Spotippos;
import com.vivareal.gateways.PropertyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by fpillo on 3/24/2017.
 */
@Component
public class PropertyGatewayImpl implements PropertyGateway {

    private final Spotippos spotippos;

    @Autowired
    public PropertyGatewayImpl(final Spotippos spotippos) {
        this.spotippos = spotippos;
    }

    @Override
    public Property save(final Property property) {
        return spotippos.insertProperty(property);
    }

    @Override
    public Optional<Property> findByPoint(Point point) {
        return Optional.ofNullable(spotippos.findPropertiesByPoint(point));
    }

    @Override
    public Optional<Property> findById(final String id) {
        return Optional.ofNullable(spotippos.findPropertiesById(id));
    }

    @Override
    public Collection<Property> findPropertiesByBoundaries(final Boundaries boundaries) {
        return spotippos.findPropertiesByBoundaries(boundaries);
    }


}
