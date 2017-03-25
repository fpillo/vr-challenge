package com.vivareal.gateways;

import com.vivareal.domains.Point;
import com.vivareal.domains.Province;

import java.util.Collection;

/**
 * Created by fpillo on 3/24/2017.
 */
public interface ProvinceGateway {

    Collection<Province> save(Collection<Province> provinces);

    Collection<Province> findAll();

    Collection<Province> findByPoint(Point point);

}
