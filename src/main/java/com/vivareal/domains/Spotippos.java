package com.vivareal.domains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fpillo on 3/24/2017.
 */
public class Spotippos {

    private final Location[][] world = new Location[1001][1401];

    private final Map<String, Property> propertyMap = new HashMap<>();

    public Spotippos(final Collection<Province> provinces) {
        initWorld();
        provinces.forEach(province -> {
            setProvinces(province);
        });
    }

    public Property insertProperty(final Property property) {
        final Location location = world[property.getY()][property.getX()];
        location.setProperty(property);
        propertyMap.put(property.getId(), property);

        return property;
    }

    public Property findPropertiesByPoint(final Point point) {
        return world[point.getY()][point.getX()].getProperty();
    }

    public Property findPropertiesById(final String id) {
        return propertyMap.get(id);
    }

    public Collection<Property> findPropertiesByBoundaries(final Boundaries boundaries) {
        final Collection<Property> properties = new ArrayList<>();
        for (int line = boundaries.getUpperLeft().getX(); line <= boundaries.getBottomRight().getX(); line++) {
            for (int column = boundaries.getBottomRight().getY(); column <= boundaries.getUpperLeft().getY(); column++) {
                if (world[column][line].getProperty() != null) {
                    properties.add(world[column][line].getProperty());
                }
            }
        }

        return properties;
    }

    public Collection<Province> findProvinceByPoint(final Point point) {
        return world[point.getY()][point.getX()].getProvinces();
    }

    private void initWorld() {
        for (int line = 0; line < 1401; line++) {
            for (int column = 0; column < 1001; column++) {
                final Location location = new Location(new Point(line, column));
                world[column][line] = location;
            }
        }
    }

    private void setProvinces(final Province province) {
        for (int line = province.getBoundaries().getUpperLeft().getX(); line <= province.getBoundaries().getBottomRight().getX(); line++) {
            for (int column = province.getBoundaries().getBottomRight().getY(); column <= province.getBoundaries().getUpperLeft().getY(); column++) {
                final Location location = world[column][line];
                location.addProvince(province);
            }
        }
    }

}
