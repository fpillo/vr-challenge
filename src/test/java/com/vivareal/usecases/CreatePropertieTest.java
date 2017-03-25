package com.vivareal.usecases;

import com.vivareal.domains.*;
import com.vivareal.exceptions.PropertieAlreadyExistsException;
import com.vivareal.gateways.PropertieGateway;
import com.vivareal.gateways.ProvinceGateway;
import com.vivareal.gateways.inmemory.PropertieGatewayImpl;
import com.vivareal.gateways.inmemory.ProvinceGatewayImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;

/**
 * Created by fpillo on 3/24/2017.
 */
public class CreatePropertieTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private final Spotippos spotippos = new Spotippos();

    private final PropertieGateway propertieGateway = new PropertieGatewayImpl(spotippos);

    private final ProvinceGateway provinceGateway = new ProvinceGatewayImpl(spotippos);

    private final CreatePropertie createPropertie = new CreatePropertie(propertieGateway, provinceGateway, validator);

    @Before
    public void setup() {
        spotippos.removeAllProvinces();
        spotippos.removeAllProperties();

        final Province gode = createProvince("gode", new Point(0, 1000), new Point(600, 500));
        final Province ruja = createProvince("ruja", new Point(400, 1000), new Point(1100, 500));

        spotippos.insertProvince(gode);
        spotippos.insertProvince(ruja);
    }

    @Test
    public void test_create() {
        final Propertie propertie = new Propertie();
        propertie.setX(399);
        propertie.setY(900);

        final Propertie result = createPropertie.create(propertie);

        Assert.assertTrue(result.getProvinces().containsAll(Arrays.asList("gode")));
    }

    @Test(expected = PropertieAlreadyExistsException.class)
    public void test_create_propertieAlreadyExists_shouldThrow_Exception() {
        final Propertie oldPropertie = new Propertie();
        oldPropertie.setX(399);
        oldPropertie.setY(900);
        createPropertie.create(oldPropertie);

        final Propertie newPropertie = new Propertie();
        newPropertie.setX(399);
        newPropertie.setY(900);
        createPropertie.create(newPropertie);
    }

    private Province createProvince(final String name, final Point upperLeft, final Point bottomRight) {
        final Province province = new Province();
        province.setName(name);
        province.setBoundaries(new Boundaries(upperLeft, bottomRight));

        return province;
    }


}
