package com.vivareal.gateways;

import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Point;
import com.vivareal.domains.Propertie;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by fpillo on 3/24/2017.
 */
public interface PropertieGateway {

    Propertie save(Propertie propertie);

    Optional<Propertie> findByPoint(Point point);

    Optional<Propertie> findById(String id);

    Collection<Propertie> findPropertieByBoundaries(Boundaries boundaries);

}
