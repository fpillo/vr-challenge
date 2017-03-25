package com.vivareal.http;

import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Point;
import com.vivareal.domains.Propertie;
import com.vivareal.http.json.FoundPropertiesJson;
import com.vivareal.usecases.CreatePropertie;
import com.vivareal.usecases.FindPropertie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fpillo on 3/24/2017.
 */
@RestController
public class PropertieController {

    private final CreatePropertie createPropertie;

    private final FindPropertie findPropertie;

    @Autowired
    public PropertieController(final CreatePropertie createPropertie, final FindPropertie findPropertie) {
        this.createPropertie = createPropertie;
        this.findPropertie = findPropertie;
    }

    @RequestMapping(value = "/properties", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Propertie create(@RequestBody final Propertie propertie) {
        return createPropertie.create(propertie);
    }

    @RequestMapping(value = "/properties/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Propertie findById(@PathVariable(name = "id") final String id) {
        return findPropertie.byId(id);
    }

    @RequestMapping(value = "/properties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FoundPropertiesJson byBoundaries(@RequestParam(name = "ax") final Integer ax,
                                            @RequestParam(name = "ay") final Integer ay,
                                            @RequestParam(name = "bx") final Integer bx,
                                            @RequestParam(name = "by") final Integer by) {

        final Boundaries boundaries = new Boundaries(new Point(ax, ay), new Point(bx, by));
        return new FoundPropertiesJson(findPropertie.byBoundaries(boundaries));
    }

}
