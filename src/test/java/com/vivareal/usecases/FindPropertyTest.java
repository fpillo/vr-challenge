package com.vivareal.usecases;

import com.vivareal.domains.*;
import com.vivareal.exceptions.PropertyNotFoundException;
import com.vivareal.gateways.inmemory.PropertyGatewayImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by fpillo on 3/24/2017.
 */
public class FindPropertyTest {

    private FindProperty findProperty;

    @Before
    public void setup() {
        final Province gode = createProvince("gode", new Point(0, 1000), new Point(600, 500));
        final Province ruja = createProvince("ruja", new Point(400, 1000), new Point(1100, 500));

        final Spotippos spotippos = new Spotippos(Arrays.asList(gode, ruja));

        final Property property1 = new Property();
        property1.setId("f4f37fcd-f67c-4c3a-8c55-391080283f92");
        property1.setX(399);
        property1.setY(900);
        property1.addProvince(gode.getName());

        final Property property2 = new Property();
        property2.setId("361e7557-e7da-4be9-9e51-717f9ebb8c72");
        property2.setX(1100);
        property2.setY(500);
        property2.addProvince(gode.getName());

        spotippos.insertProperty(property1);
        spotippos.insertProperty(property2);

        findProperty = new FindProperty(new PropertyGatewayImpl(spotippos));
    }

    @Test
    public void test_findById() {
        Assert.assertTrue(findProperty.byId("f4f37fcd-f67c-4c3a-8c55-391080283f92") != null);
    }

    @Test(expected = PropertyNotFoundException.class)
    public void test_findById_notFound_shouldThrow_Exception() {
        findProperty.byId("20c72d0f-ce42-45b3-a6c4-971c643a5ec4");
    }

    @Test
    public void test_byBoundaries_should_find_one() {
        final Boundaries boundaries = new Boundaries(new Point(380, 901), new Point(410, 300));
        final Collection<Property> result = findProperty.byBoundaries(boundaries);

        Assert.assertTrue(result.size() == 1);
    }

    @Test
    public void test_byBoundaries_should_find_two() {
        final Boundaries boundaries = new Boundaries(new Point(300, 901), new Point(1100, 500));
        final Collection<Property> result = findProperty.byBoundaries(boundaries);

        Assert.assertTrue(result.size() == 2);
    }

    @Test
    public void test_byBoundaries_should_find_zero() {
        final Boundaries boundaries = new Boundaries(new Point(400, 900), new Point(410, 300));
        final Collection<Property> result = findProperty.byBoundaries(boundaries);

        Assert.assertTrue(result.size() == 0);
    }

    private Province createProvince(final String name, final Point upperLeft, final Point bottomRight) {
        final Province province = new Province();
        province.setName(name);
        province.setBoundaries(new Boundaries(upperLeft, bottomRight));

        return province;
    }

}
