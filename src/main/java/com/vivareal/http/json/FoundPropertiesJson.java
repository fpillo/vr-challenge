package com.vivareal.http.json;

import com.vivareal.domains.Property;
import lombok.Getter;

import java.util.Collection;

/**
 * Created by fpillo on 3/24/2017.
 */
@Getter
public class FoundPropertiesJson {

    private Integer foundProperties;

    private Collection<Property> properties;

    public FoundPropertiesJson(final Collection<Property> properties) {
        this.foundProperties = properties.size();
        this.properties = properties;
    }

}
