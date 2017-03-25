package com.vivareal.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by fpillo on 3/25/2017.
 */
@Getter
@ToString
public class Location {

    @Setter
    private Point point;

    @Setter
    private Property property;

    private Collection<Province> provinces = new HashSet<>();

    public Location(final Point point) {
        this.point = point;
    }

    public boolean addProvince(final Province province) {
        return provinces.add(province);
    }

}
