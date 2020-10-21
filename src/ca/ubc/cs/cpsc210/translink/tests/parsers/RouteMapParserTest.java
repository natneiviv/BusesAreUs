package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.parsers.RouteMapParser;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test the parser for route pattern map information
 */

// TODO: Write more tests

public class RouteMapParserTest {
    @Before
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    private int countNumRoutePatterns() {
        int count = 0;
        for (Route r : RouteManager.getInstance()) {
            for (RoutePattern rp : r.getPatterns()) {
                count ++;
            }
        }
        return count;
    }

    @Test
    public void testRouteParserNormal() {
        RouteMapParser p = new RouteMapParser("allroutemaps.txt");
        p.parse();
        assertEquals(1232, countNumRoutePatterns());
    }

    @Test
    public void testParseAndStore(){
        RouteManager r = RouteManager.getInstance();
        RouteMapParser p = new RouteMapParser("testroutes.txt");
        p.parse();
        assertEquals(5, countNumRoutePatterns());
        assertEquals(4,  r.getRouteWithNumber("C43").getPatterns().size());
        assertEquals(1, r.getRouteWithNumber("50").getPatterns().size());
        RoutePattern rp = r.getRouteWithNumber("C43").getPattern("EB2");
        assertEquals("EB2", rp.getName());
    }

    @Test
    public void testMissingLatLon(){
        RouteManager r = RouteManager.getInstance();
        RouteMapParser p = new RouteMapParser("testRoutes1.txt");
        p.parse();
        assertEquals(6, countNumRoutePatterns());
        //r.getRouteWithNumber("C41").getPatterns().get(0).getPath().get(0);
    }

}
