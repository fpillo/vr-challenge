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
public class Propertie {

    @NotBlank(message = "propertie.id.notBlank")
    private String id;

    @NotBlank(message = "propertie.title.notBlank")
    private String title;

    @NotNull(message = "propertie.price.notNull")
    @Min(value = 0, message ="propertie.price.min")
    private Integer price;

    @NotBlank(message = "propertie.description.notBlank")
    private String description;

    @NotNull(message = "propertie.x.notNull")
    @Min(value = 0, message ="propertie.x.min")
    @Max(value = 1400, message ="propertie.x.max")
    private Integer x;

    @NotNull(message = "propertie.y.notNull")
    @Min(value = 0, message ="propertie.y.min")
    @Max(value = 1000, message ="propertie.y.max")
    private Integer y;

    @NotNull(message = "propertie.beds.notNull")
    @Min(value = 0, message ="propertie.beds.min")
    private Integer beds;

    @NotNull(message = "propertie.baths.notNull")
    @Min(value = 0, message ="propertie.baths.min")
    private Integer baths;

    @NotEmpty(message = "propertie.provinces.notEmpty")
    private Collection<String> provinces = new HashSet<>();

    @NotNull(message = "propertie.squareMeters.notNull")
    @Min(value = 1, message ="propertie.squareMeters.min")
    private Integer squareMeters;

    @JsonIgnore
    public Point getPoint() {
        return new Point(x, y);
    }

    public boolean addProvince(final String province) {
        return provinces.add(province);
    }

}
