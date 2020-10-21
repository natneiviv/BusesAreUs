package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by vivientan on 2017-08-01.
 */
public class RouteTest {
    private List<Stop> stops = new ArrayList<Stop>();
    private List<RoutePattern> routePatterns = new ArrayList<RoutePattern>();
    private String name;
    private RoutePattern routePattern1, routePattern2;
    Route r = new Route("33");
    Route r1 = new Route("43");
    Route r2 = new Route ("33");
    Route r3 = new Route("25");
    LatLon l1 = new LatLon(49.2683, -123.2478);
    LatLon l2 = new LatLon(49.2647226, -123.2439829);
    Stop s1 = new Stop(60156, "UBC Bus Exchange Unloading Only", l1);
    Stop s2 = new Stop (58606, "Wesbrook Mall at 2100 Block", l2);


    @Before
    public void setUp() throws Exception {
        routePattern1 = new RoutePattern("A", "UBC", "West", r1);
        routePattern2 = new RoutePattern("B", "29th Ave Station", "East", r2);
    }

    @Test
    public void testConstructor(){
        assertEquals("33", r.getNumber());
        assertEquals("", r.getName());
        assertEquals(0, r.getPatterns().size());
        assertEquals(0, r.getStops().size());

    }

    @Test
    public void testSetGetName(){
        r.setName("33 UBC");
        assertEquals("33 UBC", r.getName());
    }

    @Test
    public void testAddPattern(){
        assertEquals(0, r.getPatterns().size());
        r.addPattern(routePattern1);
        assertEquals(1, r.getPatterns().size());

    }

    @Test
    public void testAddRemoveStop(){
        r.addStop(s1);
        r.addStop(s2);
        assertEquals(2, r.getStops().size());
        r.removeStop(s2);
        assertEquals(1, r.getStops().size());
    }

    @Test
    public void testHasGetStops(){
        r.addStop(s1);
        r.addStop(s2);
        assertTrue(r.hasStop(s1));
        assertTrue(r.hasStop(s2));
        r.removeStop(s2);
        assertFalse(r.hasStop(s2));

    }


    @Test
    public void testEquals(){
        r.setName("33 UBC");
        r.addStop(s1);
        r.addPattern(routePattern1);
        r2.setName("33 29th Ave Station");
        r2.addPattern(routePattern2);
        assertEquals(r, r2);
    }


    @Test
    public void testToStringAndGetName(){
        assertEquals("Route 33", r.toString());
        assertEquals("", r.getName());
        r.setName("33 UBC");
        assertEquals("33 UBC", r.getName());
    }

    @Test
    public void testGetPatternExists(){
        r.addPattern(routePattern1);
        assertTrue(r.getPatterns().contains(routePattern1));
        assertEquals(routePattern1, r.getPattern("A", "UBC", "West"));
        routePattern1.setDestination("UBC");
        routePattern1.setDirection("West");
    }

    @Test
    public void testGetPatternsDoesNotExist(){
        assertFalse(r.getPatterns().contains(routePattern1));

    }

    @Test
    public void testGetPattern2Exists(){
        r.addPattern(routePattern1);
        assertTrue(r.getPatterns().contains(routePattern1));
        assertEquals(routePattern1, r.getPattern("A"));

    }

    @Test
    public void testGetPattern2EmptyStrings(){
        assertFalse(r.getPatterns().contains(routePattern1.getName()));
        RoutePattern rp = new RoutePattern("A", "", "", new Route(""));
        assertEquals("", rp.getDestination());
        assertEquals("", rp.getDirection());

    }

    @Test
    public void testGetPatterns(){
        routePatterns.add(routePattern1);
        routePatterns.add(routePattern2);
        assertEquals(2, routePatterns.size());
        routePatterns.remove(routePattern1);
        assertEquals(1, routePatterns.size());
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
        r1.addStop(s1);

        List<Stop> stops= new ArrayList<>();
        stops.add(s1);
        assertEquals(stops, r1.getStops());
    }

}