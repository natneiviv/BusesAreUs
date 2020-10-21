package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.parsers.RouteParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.RouteDataMissingException;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the RouteParser
 */
// TODO: Write more tests

public class RouteParserTest {
    private Route r;
    @Before
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    @Test
    public void testRouteParserNormal() throws RouteDataMissingException, JSONException, IOException {
        RouteParser p = new RouteParser("allroutes.json");
        p.parse();
        assertEquals(229, RouteManager.getInstance().getNumRoutes());
    }

    @Test (expected = RouteDataMissingException.class)
    public void testMissingName() throws RouteDataMissingException, IOException, JSONException {
        RouteParser p = new RouteParser("missingFieldsNames.json");
        p.parse();

    }

    @Test (expected = JSONException.class)
    public void testIncorrectFormat() throws RouteDataMissingException, IOException, JSONException {
        RouteParser p = new RouteParser("incorrectformat.json");
        p.parse();

    }

    @Test (expected = RouteDataMissingException.class)
    public void testMissingPatterns() throws RouteDataMissingException, IOException, JSONException {
        RouteParser p = new RouteParser("missingFieldsPatterns.json");
        p.parse();
    }

    @Test (expected = RouteDataMissingException.class)
    public void testMissingDir() throws RouteDataMissingException, IOException, JSONException {
        RouteParser p = new RouteParser("missingFieldsDir.json");
        p.parse();
    }

    @Test (expected = RouteDataMissingException.class)
    public void testMissingRouteNo() throws RouteDataMissingException, IOException, JSONException{
        RouteParser p = new RouteParser("missingFieldsDir.json");
        p.parse();
    }
    @Test
    /*
    this test was for missing routeName, as well also tested
    adding pattern correctly
    */
    public void testAddingCorrectly()throws RouteDataMissingException, IOException, JSONException{
        RouteParser p = new RouteParser("routesTestAdd.json");
        try {
            p.parse();
        }
        catch (IOException i){
            fail("blah io");
        }
        catch(JSONException je) {
            fail("blah json");
        }
        catch(RouteDataMissingException r){
            // do nothing
        }
        assertEquals(2, RouteManager.getInstance().getNumRoutes());
        assertEquals(0, RouteManager.getInstance().getRouteWithNumber("N8").getPatterns().size());
        assertEquals(0, RouteManager.getInstance().getRouteWithNumber("N9").getPatterns().size());

    }

    @Test
    /*
    this test was for missing routeNo
    also tested adding pattern correctly when one of the elements making up
    a routePattern is missing
    e.g. have pattern 1
              pattern 2 *missing elements*
              pattern 3
    this test should show that can only count one pattern, then moves
    on to the next route
    */
    public void testAddingWhenMissingRouteNo()throws RouteDataMissingException, IOException, JSONException{
        RouteParser p = new RouteParser("missingFieldsRouteNo.json");
        try {
            p.parse();
        }
        catch (IOException i){
            fail("IOException / Should not be thrown.");
        }
        catch(JSONException je) {
            fail("JSONException / Should not be thrown.");
        }
        catch(RouteDataMissingException r){
            System.out.println("RouteDataMissingException caught.");
        }
        assertEquals(2, RouteManager.getInstance().getNumRoutes());
        assertEquals(1, RouteManager.getInstance().getRouteWithNumber("N20").getPatterns().size());
    }

    public void testAddingWhenMissingPatternNo() throws RouteDataMissingException, IOException, JSONException{
        RouteParser p = new RouteParser("missingFieldsPatternNo.json");
        try {
            p.parse();
        }
        catch (IOException i){
            fail("IOException / Should not be thrown.");
        }
        catch(JSONException je) {
            fail("JSONException / Should not be thrown.");
        }
        catch(RouteDataMissingException r){
            System.out.println("RouteDataMissingException caught.");
        }
        assertEquals(2, RouteManager.getInstance().getNumRoutes());
        assertEquals(2, RouteManager.getInstance().getRouteWithNumber("C9").getPatterns().size());
        assertEquals(4, RouteManager.getInstance().getRouteWithNumber("C92").getPatterns().size());

    }

}

