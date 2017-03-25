package com.vivareal.gateways.inmemory;

import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Point;
import com.vivareal.domains.Propertie;
import com.vivareal.domains.Spotippos;
import com.vivareal.gateways.PropertieGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by fpillo on 3/24/2017.
 */
@Component
public class PropertieGatewayImpl implements PropertieGateway {

    private final Spotippos spotippos;

    @Autowired
    public PropertieGatewayImpl(final Spotippos spotippos) {
        this.spotippos = spotippos;
    }

    @Override
    public Propertie save(final Propertie propertie) {
        return spotippos.insertPropertie(propertie);
    }

    @Override
    public Optional<Propertie> findByPoint(Point point) {
        return Optional.ofNullable(spotippos.findPropertieByPoint(point));
    }

    @Override
    public Optional<Propertie> findById(final String id) {
        return Optional.ofNullable(spotippos.findPropertieById(id));
    }

    @Override
    public Collection<Propertie> findPropertieByBoundaries(final Boundaries boundaries) {
        return spotippos.findPropertieByBoundaries(boundaries);
    }


}
