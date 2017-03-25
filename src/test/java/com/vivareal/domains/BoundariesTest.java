package com.vivareal.domains;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by fpillo on 3/24/2017.
 */
public class BoundariesTest {

    @Test
    public void test_findContainedProperties() {
        final Boundaries boundaries = new Boundaries(new Point(1, 10), new Point(10,1));

        final Propertie inside1 = createPropertie("37363019-4db7-42c1-88fc-678c58cb1544", 2,2);
        final Propertie inside2 = createPropertie("4e56f650-4196-4438-85d6-8173380bb6db", 5,10);
        final Propertie outside1 = createPropertie("aad5fa08-f0a1-4a6e-8abc-5abff2a45e96", 11, 1);
        final Propertie outside2 = createPropertie("47107ea5-ad05-498a-8056-5011b19c3cf4", 4, 0);
        final Collection<Propertie> properties = Arrays.asList(inside1, inside2, outside1, outside2);

        final Collection<Propertie> result = boundaries.findContainedProperties(properties);

        Assert.assertTrue(result.contains(inside1));
        Assert.assertTrue(result.contains(inside2));
        Assert.assertFalse(result.contains(outside1));
        Assert.assertFalse(result.contains(outside2));
    }

    @Test
    public void test_containPoint() {
        final Boundaries boundaries = new Boundaries(new Point(1, 10), new Point(10,1));

        final Point inside = new Point(10, 5);
        final Point outside = new Point(0,1);

        Assert.assertTrue(boundaries.containPoint(inside));
        Assert.assertFalse(boundaries.containPoint(outside));
    }

    @Test
    public void test_hasIntersaction_should_return_true() {
        final Boundaries b1 = new Boundaries(new Point(10, 100), new Point(100, 10));
        final Boundaries b2 = new Boundaries(new Point(90, 20), new Point(200, 0));

        Assert.assertTrue(b1.hasIntersection(b2));
    }

    @Test
    public void test_hasIntersaction_should_return_false() {
        final Boundaries b1 = new Boundaries(new Point(900, 900), new Point(1000, 50));
        final Boundaries b2 = new Boundaries(new Point(10, 1000), new Point(200, 0));

        Assert.assertFalse(b1.hasIntersection(b2));
    }

    private Propertie createPropertie(final String id, final Integer x, final Integer y) {
        final Propertie propertie = new Propertie();
        propertie.setId(id);
        propertie.setX(x);
        propertie.setY(y);

        return propertie;
    }

}
