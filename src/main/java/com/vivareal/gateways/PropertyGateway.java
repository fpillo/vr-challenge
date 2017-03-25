package com.vivareal.gateways;

import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Point;
import com.vivareal.domains.Property;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by fpillo on 3/24/2017.
 */
public interface PropertyGateway {

    Property save(Property property);

    Optional<Property> findByPoint(Point point);

    Optional<Property> findById(String id);

    Collection<Property> findPropertiesByBoundaries(Boundaries boundaries);

}
