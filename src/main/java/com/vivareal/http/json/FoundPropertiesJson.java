package com.vivareal.http.json;

import com.vivareal.domains.Propertie;
import lombok.Getter;

import java.util.Collection;

/**
 * Created by fpillo on 3/24/2017.
 */
@Getter
public class FoundPropertiesJson {

    private Integer foundProperties;

    private Collection<Propertie> properties;

    public FoundPropertiesJson(final Collection<Propertie> properties) {
        this.foundProperties = properties.size();
        this.properties = properties;
    }

}
