package com.vivareal.usecases;

import com.vivareal.domains.*;
import com.vivareal.exceptions.PropertyAlreadyExistsException;
import com.vivareal.gateways.PropertyGateway;
import com.vivareal.gateways.inmemory.PropertyGatewayImpl;
import com.vivareal.gateways.inmemory.ProvinceGatewayImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import java.util.Arrays;

/**
 * Created by fpillo on 3/24/2017.
 */
public class CreatePropertyTest {

    private PropertyGateway propertyGateway;

    private CreateProperty createProperty;

    @Before
    public void setup() {
        final Province gode = createProvince("gode", new Point(0, 1000), new Point(600, 500));
        final Province ruja = createProvince("ruja", new Point(400, 1000), new Point(1100, 500));
        final Spotippos spotippos = new Spotippos(Arrays.asList(gode, ruja));

        propertyGateway = new PropertyGatewayImpl(spotippos);
        createProperty = new CreateProperty(propertyGateway, new ProvinceGatewayImpl(spotippos),  Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    public void test_create() {
        final Property property = new Property();
        property.setX(399);
        property.setY(900);

        final Property result = createProperty.create(property);

        Assert.assertTrue(result.getProvinces().containsAll(Arrays.asList("gode")));
    }

    @Test(expected = PropertyAlreadyExistsException.class)
    public void test_create_propertieAlreadyExists_shouldThrow_Exception() {
        final Property oldProperty = new Property();
        oldProperty.setX(399);
        oldProperty.setY(900);
        createProperty.create(oldProperty);

        final Property newProperty = new Property();
        newProperty.setX(399);
        newProperty.setY(900);
        createProperty.create(newProperty);
    }

    private Province createProvince(final String name, final Point upperLeft, final Point bottomRight) {
        final Province province = new Province();
        province.setName(name);
        province.setBoundaries(new Boundaries(upperLeft, bottomRight));

        return province;
    }


}
