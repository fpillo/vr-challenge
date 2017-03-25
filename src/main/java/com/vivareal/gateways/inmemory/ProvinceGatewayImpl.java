package com.vivareal.gateways.inmemory;

import com.vivareal.domains.Point;
import com.vivareal.domains.Province;
import com.vivareal.domains.Spotippos;
import com.vivareal.gateways.ProvinceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by fpillo on 3/24/2017.
 */
@Component
public class ProvinceGatewayImpl implements ProvinceGateway {

    private final Spotippos spotippos;

    @Autowired
    public ProvinceGatewayImpl(final Spotippos spotippos) {
        this.spotippos = spotippos;
    }

    @Override
    public Collection<Province> save(final Collection<Province> provinces) {
        provinces.forEach(province -> {
            spotippos.insertProvince(province);
        });

        return provinces;
    }

    @Override
    public Collection<Province> findAll() {
        return spotippos.findAllProvinces();
    }

    @Override
    public Collection<Province> findByPoint(final Point point) {
        return spotippos.findProvinceByPoint(point);
    }

}
