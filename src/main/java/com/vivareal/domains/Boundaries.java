package com.vivareal.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by fpillo on 3/24/2017.
 */
@Getter
@AllArgsConstructor
@ToString
public class Boundaries {

    private Point upperLeft;

    private Point bottomRight;

    public Collection<Property> findContainedProperties(final Collection<Property> properties) {
        return properties.stream().filter(propertie ->
            containX(propertie.getPoint()) && containY(propertie.getPoint())
        ).collect(Collectors.toList());
    }

    public boolean containPoint(final Point point) {
        return containX(point) && containY(point);
    }

    public boolean hasIntersection(final Boundaries boundaries) {
       if (this.getUpperLeft().getX() > boundaries.getBottomRight().getX() || boundaries.getUpperLeft().getX() > this.getBottomRight().getX()) {
           return false;
       }

       if (this.getUpperLeft().getY() < boundaries.getBottomRight().getY() || boundaries.getUpperLeft().getY() < this.getBottomRight().getY()) {
            return false;
       }

        return true;
    }

    private boolean containX(final Point point) {
        return point.getX() >= upperLeft.getX() && point.getX() <= bottomRight.getX();
    }

    private boolean containY(final Point point) {
        return point.getY() <= upperLeft.getY() && point.getY() >= bottomRight.getY();
    }

}
