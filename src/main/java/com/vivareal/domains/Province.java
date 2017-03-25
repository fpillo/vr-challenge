package com.vivareal.domains;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by fpillo on 3/24/2017.
 */
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Province {

    private String name;

    private Boundaries boundaries;

    public boolean containPoint(final Point point) {
        return this.boundaries.containPoint(point);
    }

}
