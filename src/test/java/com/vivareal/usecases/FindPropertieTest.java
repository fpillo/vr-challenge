package com.vivareal.usecases;

import com.vivareal.domains.*;
import com.vivareal.exceptions.PropertieNotFoundException;
import com.vivareal.gateways.PropertieGateway;
import com.vivareal.gateways.inmemory.PropertieGatewayImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

/**
 * Created by fpillo on 3/24/2017.
 */
public class FindPropertieTest {

    private final Spotippos spotippos = new Spotippos();

    private PropertieGateway propertieGateway = new PropertieGatewayImpl(spotippos);

    private final FindPropertie findPropertie = new FindPropertie(propertieGateway);

    @Before
    public void setup() {
        spotippos.removeAllProperties();
        spotippos.removeAllProvinces();

        final Province gode = createProvince("gode", new Point(0, 1000), new Point(600, 500));
        final Province ruja = createProvince("ruja", new Point(400, 1000), new Point(1100, 500));

        final Propertie propertie1 = new Propertie();
        propertie1.setId("f4f37fcd-f67c-4c3a-8c55-391080283f92");
        propertie1.setX(399);
        propertie1.setY(900);
        propertie1.addProvince(gode.getName());

        final Propertie propertie2 = new Propertie();
        propertie2.setId("361e7557-e7da-4be9-9e51-717f9ebb8c72");
        propertie2.setX(1100);
        propertie2.setY(500);
        propertie2.addProvince(gode.getName());

        spotippos.insertProvince(gode);
        spotippos.insertProvince(ruja);
        spotippos.insertPropertie(propertie1);
        spotippos.insertPropertie(propertie2);
    }

    @Test
    public void test_findById() {
        Assert.assertTrue(findPropertie.byId("f4f37fcd-f67c-4c3a-8c55-391080283f92") != null);
    }

    @Test(expected = PropertieNotFoundException.class)
    public void test_findById_notFound_shouldThrow_Exception() {
        findPropertie.byId("20c72d0f-ce42-45b3-a6c4-971c643a5ec4");
    }

    @Test
    public void test_byBoundaries_should_find_one() {
        final Boundaries boundaries = new Boundaries(new Point(380, 901), new Point(410, 300));
        final Collection<Propertie> result = findPropertie.byBoundaries(boundaries);

        Assert.assertTrue(result.size() == 1);
    }

    @Test
    public void test_byBoundaries_should_find_two() {
        final Boundaries boundaries = new Boundaries(new Point(300, 901), new Point(1100, 500));
        final Collection<Propertie> result = findPropertie.byBoundaries(boundaries);

        Assert.assertTrue(result.size() == 2);
    }

    @Test
    public void test_byBoundaries_should_find_zero() {
        final Boundaries boundaries = new Boundaries(new Point(400, 900), new Point(410, 300));
        final Collection<Propertie> result = findPropertie.byBoundaries(boundaries);

        Assert.assertTrue(result.size() == 0);
    }

    private Propertie createPropertie(final String id, final Integer x, final Integer y, final Map<String, Propertie> idMap) {
        final Propertie propertie = new Propertie();
        propertie.setId(id);
        propertie.setX(x);
        propertie.setY(y);
        idMap.put(propertie.getId(), propertie);

        return propertie;
    }

    private Province createProvince(final String name, final Point upperLeft, final Point bottomRight) {
        final Province province = new Province();
        province.setName(name);
        province.setBoundaries(new Boundaries(upperLeft, bottomRight));

        return province;
    }

}
