package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Arrival;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class StopTest {
    private List<Arrival> arrivals;
    private List<Route> routes;
    private LatLon locn;
    private Stop s1, s2;
    private LatLon l1, l2;
    private Route r1, r2;
    private Arrival a1, a2;

    @Before
    public void setUp() throws Exception {
        l1 = new LatLon(49.2683, -123.2478);
        l2 = new LatLon(49.2647226, -123.2439829);

        s1 = new Stop(60156, "UBC Bus Exchange Unloading Only", l1);
        s2 = new Stop(60156, "Some other stop", l2);

        r1 = new Route("33");
        r2 = new Route("25");
        a1 = new Arrival(23, "UBC", r2);
        a2 = new Arrival(40, "UBC", r1);


    }

    @Test
    public void testConstructor(){
        assertEquals(60156, s1.getNumber());
        assertEquals("UBC Bus Exchange Unloading Only", s1.getName());
        assertEquals(l1, s1.getLocn());
    }

    @Test
    public void testAddRemoveRoutes(){
        s1.addRoute(r1);
        s1.addRoute(r2);
        assertEquals(2, s1.getRoutes().size());
        assertTrue(s1.onRoute(r1));
        s1.removeRoute(r2);
        assertEquals(1, s1.getRoutes().size());


    }

    @Test
    public void testOnRoute(){
        s1.addRoute(r1);
        assertTrue(s1.onRoute(r1));
        assertFalse(s1.onRoute(r2));

    }

    @Test
    public void testAddClearArrivals(){
        s1.addArrival(a1);
        s1.addArrival(a2);
        s1.clearArrivals();

    }

    @Test
    public void testEquals(){
        assertEquals(s1, s2);

    }

    @Test
    public void testSetNameLocn(){
        assertEquals("UBC Bus Exchange Unloading Only", s1.getName());
        s1.setName("Stop1");
        assertEquals("Stop1", s1.getName());
        assertEquals(l1, s1.getLocn());
        s1.setLocn(l2);
        assertEquals(l2, s1.getLocn());
    }

    @Test
    public void testAssociation(){
        s1.addRoute(r1);
        r1.addStop(s1);
        assertTrue(r1.hasStop(s1));
        assertTrue(s1.onRoute(r1));
    }

    @Test
    // added this
    public void testOther(){
        s1.addRoute(r1);
        s1.addRoute(r2);
        r1.addStop(s1);
        Set<Route> routes= new HashSet<>();
        routes.add(r1);
        routes.add(r2);
        assertEquals(routes, s1.getRoutes());

    }
}