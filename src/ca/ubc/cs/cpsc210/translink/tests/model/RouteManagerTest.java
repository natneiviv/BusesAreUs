package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test the RouteManager
 */
public class RouteManagerTest {
    private RouteManager rm;
    private Map<String, Route> routeMap;
    private Route r, r1, r2;

    @Before
    public void setup() {
        RouteManager.getInstance().clearRoutes();
        rm = RouteManager.getInstance();
        routeMap = new HashMap<String, Route>();

        r1 = new Route("33");
        r2 = new Route("25");
    }


    @Test
    public void testBoring() {
        Route r43 = new Route("43");
        Route r = RouteManager.getInstance().getRouteWithNumber("43");
        assertEquals(r43, r);
    }

    @Test
    public void testConstructor(){
        assertEquals(0, rm.getNumRoutes());
    }

    @Test
    public void testGetRouteWithNumber(){
        routeMap.put("33", r1);
        assertEquals(r1, rm.getRouteWithNumber("33"));
        assertEquals(r2, rm.getRouteWithNumber("25"));
        assertEquals("", r2.getName());

    }

    @Test
    public void testGetRouteWithNumber2(){
        r = RouteManager.getInstance().getRouteWithNumber("33", "33 UBC");
        assertEquals("33 UBC", r.getName());

    }

    @Test
    public void testGetNumRoutes(){
        Route r = RouteManager.getInstance().getRouteWithNumber("33");
        Route r1 = RouteManager.getInstance().getRouteWithNumber("25");
        assertEquals(2, rm.getNumRoutes());
        rm.clearRoutes();
        assertEquals(0, rm.getNumRoutes());
    }

}
