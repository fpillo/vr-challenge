package com.vivareal.http;

import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Point;
import com.vivareal.domains.Property;
import com.vivareal.http.json.FoundPropertiesJson;
import com.vivareal.usecases.CreateProperty;
import com.vivareal.usecases.FindProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fpillo on 3/24/2017.
 */
@RestController
public class PropertyController {

    private final CreateProperty createProperty;

    private final FindProperty findProperty;

    @Autowired
    public PropertyController(final CreateProperty createProperty, final FindProperty findProperty) {
        this.createProperty = createProperty;
        this.findProperty = findProperty;
    }

    @RequestMapping(value = "/properties", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Property create(@RequestBody final Property property) {
        return createProperty.create(property);
    }

    @RequestMapping(value = "/properties/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Property findById(@PathVariable(name = "id") final String id) {
        return findProperty.byId(id);
    }

    @RequestMapping(value = "/properties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FoundPropertiesJson byBoundaries(@RequestParam(name = "ax") final Integer ax,
                                            @RequestParam(name = "ay") final Integer ay,
                                            @RequestParam(name = "bx") final Integer bx,
                                            @RequestParam(name = "by") final Integer by) {

        final Boundaries boundaries = new Boundaries(new Point(ax, ay), new Point(bx, by));
        return new FoundPropertiesJson(findProperty.byBoundaries(boundaries));
    }

}
