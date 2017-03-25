package com.vivareal.usecases;

import com.vivareal.domains.*;
import com.vivareal.exceptions.BusinessException;
import com.vivareal.exceptions.PropertyAlreadyExistsException;
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

    private CreateProperty createProperty;

    @Before
    public void setup() {
        final Province gode = createProvince("gode", new Point(0, 1000), new Point(600, 500));
        final Province ruja = createProvince("ruja", new Point(400, 1000), new Point(1100, 500));
        final Spotippos spotippos = new Spotippos(Arrays.asList(gode, ruja));

        createProperty = new CreateProperty(new PropertyGatewayImpl(spotippos), new ProvinceGatewayImpl(spotippos), new ValidateDomain(Validation.buildDefaultValidatorFactory().getValidator()));
    }

    @Test
    public void test_create() {
        final Property property = new Property();
        property.setX(399);
        property.setY(900);
        property.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
        property.setPrice(1250000);
        property.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        property.setBeds(4);
        property.setBaths(3);
        property.setSquareMeters(210);

        final Property result = createProperty.create(property);

        Assert.assertTrue(result.getProvinces().containsAll(Arrays.asList("gode")));
    }

    @Test(expected = PropertyAlreadyExistsException.class)
    public void test_create_propertyAlreadyExists_shouldThrow_Exception() {
        final Property oldProperty = new Property();
        oldProperty.setX(399);
        oldProperty.setY(900);
        oldProperty.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
        oldProperty.setPrice(1250000);
        oldProperty.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        oldProperty.setBeds(4);
        oldProperty.setBaths(3);
        oldProperty.setSquareMeters(210);

        createProperty.create(oldProperty);

        final Property newProperty = new Property();
        newProperty.setX(399);
        newProperty.setY(900);
        oldProperty.setTitle("Novo Imóvel");
        oldProperty.setPrice(2550000);
        oldProperty.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        oldProperty.setBeds(2);
        oldProperty.setBaths(2);
        oldProperty.setSquareMeters(110);

        createProperty.create(newProperty);
    }

    @Test(expected = BusinessException.class)
    public void test_create_invalid_constraints_shouldThrow_Exception() {
        final Property oldProperty = new Property();
        oldProperty.setX(600);
        oldProperty.setY(450);
        oldProperty.setTitle("");
        oldProperty.setPrice(null);
        oldProperty.setDescription(null);
        oldProperty.setBeds(4);
        oldProperty.setBaths(3);
        oldProperty.setSquareMeters(210);

        createProperty.create(oldProperty);
    }

    private Province createProvince(final String name, final Point upperLeft, final Point bottomRight) {
        final Province province = new Province();
        province.setName(name);
        province.setBoundaries(new Boundaries(upperLeft, bottomRight));

        return province;
    }


}
