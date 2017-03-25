package com.vivareal.domains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fpillo on 3/24/2017.
 */
public class Spotippos {

    private final Property[][] world = new Property[1001][1401];

    private final Map<String, Property> propertyMap = new HashMap<>();

    private final Map<String, Province> provinceMap = new HashMap<>();

    public Property insertProperty(final Property property) {
        world[property.getY()][property.getX()] = property;
        propertyMap.put(property.getId(), property);

        return property;
    }

    public Province insertProvince(final Province province) {
        provinceMap.put(province.getName(), province);

        return province;
    }

    public Property findPropertiesByPoint(final Point point) {
        return world[point.getY()][point.getX()];
    }

    public Property findPropertiesById(final String id) {
        return propertyMap.get(id);
    }

    public Collection<Property> findPropertiesByBoundaries(final Boundaries boundaries) {
        final Collection<Property> properties = new ArrayList<>();
        for (int line = boundaries.getUpperLeft().getX(); line <= boundaries.getBottomRight().getX(); line++) {
            for (int column = boundaries.getBottomRight().getY(); column <= boundaries.getUpperLeft().getY(); column++) {
                if (world[column][line] != null) {
                    properties.add(world[column][line]);
                }
            }
        }

        return properties;
    }

    public Collection<Province> findProvinceByPoint(final Point point) {
        return provinceMap.values().stream().filter(province -> province.containPoint(point)).collect(Collectors.toList());
    }

    public Collection<Province> findAllProvinces() {
        return provinceMap.values();
    }

    public void removeAllProperties() {
        for (int line = 0; line < 1401; line++) {
            for (int column = 0; column < 1000; column++) {
                world[column][line] = null;
            }
        }
        propertyMap.clear();
    }

    public void removeAllProvinces() {
        provinceMap.clear();
    }

}
