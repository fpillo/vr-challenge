package com.vivareal.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by fpillo on 3/24/2017.
 */
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "x", "y"})
public class Property {

    @NotBlank(message = "property.id.notBlank")
    private String id;

    @NotBlank(message = "property.title.notBlank")
    private String title;

    @NotNull(message = "property.price.notNull")
    @Min(value = 0, message ="property.price.min")
    private Integer price;

    @NotBlank(message = "property.description.notBlank")
    private String description;

    @NotNull(message = "property.x.notNull")
    @Min(value = 0, message ="property.x.min")
    @Max(value = 1400, message ="property.x.max")
    private Integer x;

    @NotNull(message = "property.y.notNull")
    @Min(value = 0, message ="property.y.min")
    @Max(value = 1000, message ="property.y.max")
    private Integer y;

    @NotNull(message = "property.beds.notNull")
    @Min(value = 0, message ="property.beds.min")
    private Integer beds;

    @NotNull(message = "property.baths.notNull")
    @Min(value = 0, message ="property.baths.min")
    private Integer baths;

    @NotEmpty(message = "property.provinces.notEmpty")
    private Collection<String> provinces = new HashSet<>();

    @NotNull(message = "property.squareMeters.notNull")
    @Min(value = 1, message ="property.squareMeters.min")
    private Integer squareMeters;

    @JsonIgnore
    public Point getPoint() {
        return new Point(x, y);
    }

    public boolean addProvince(final String province) {
        return provinces.add(province);
    }

}
