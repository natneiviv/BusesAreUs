package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vivientan on 2017-07-31.
 */
public class RoutePatternTest {
Route r1, r2, r3;
RoutePattern routePattern1, routePattern2, routePattern3;
List<LatLon> path1;

    @Before
    public void setUp() {
        r1 = RouteManager.getInstance().getRouteWithNumber("43");
        r2 = RouteManager.getInstance().getRouteWithNumber("25");
        r3 = RouteManager.getInstance().getRouteWithNumber("41");
        path1 = new ArrayList<LatLon>();


        routePattern1 = new RoutePattern("A", "UBC", "West", r1);
        routePattern2 = new RoutePattern("B", "Brentwood Town Centre", "East", r2);
        routePattern3 = new RoutePattern("A", "UBC", "West", r3);
    }

    @Test
    public void testGetMethods(){
        assertEquals("A", routePattern1.getName());
        assertEquals("UBC", routePattern1.getDestination());
        assertEquals("West", routePattern1.getDirection());
    }

    @Test
    public void testEqualsSame(){
        assertEquals(routePattern1, routePattern3);
    }

    @Test
    public void testSetPath(){
        path1.add(new LatLon(0,0));
        path1.add(new LatLon(1,1));
        routePattern1.setPath(path1);
        assertEquals(path1, routePattern1.getPath());


    }


    @Test
    public void testSetDirection(){
        routePattern1.setDirection("East");
        assertEquals("East", routePattern1.getDirection());
        routePattern2.setDirection("North");
        assertEquals("North", routePattern2.getDirection());

    }

    @Test
    public void testSetDestination(){
        routePattern1.setDestination("Joyce Station");
        assertEquals("Joyce Station", routePattern1.getDestination());
    }


}