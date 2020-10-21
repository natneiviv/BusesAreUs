package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.StopParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.StopDataMissingException;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Tests for the StopParser
 */

// TODO: Write more tests

public class StopParserTest {
    @Before
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testStopParserNormal() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stops.json");
        p.parse();
        assertEquals(8524, StopManager.getInstance().getNumStops());
    }

    @Test (expected = StopDataMissingException.class)
    public void testStopMissingLat() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingLat.json");
        p.parse();
    }
    @Test (expected = StopDataMissingException.class)
    public void testStopMissingLon() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingLon.json");
        p.parse();
    }
    @Test (expected = StopDataMissingException.class)
    public void testStopMissingRoutes() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingRoutes.json");
        p.parse();
    }
    @Test (expected = StopDataMissingException.class)
    public void testStopMissingStopNo() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingStopNo.json");
        p.parse();
    }
    @Test (expected = JSONException.class)
    public void testStopIncorrectFormat() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsIncorrectFormat.json");
        p.parse();
    }

    @Test //test with missing name
    public void testGetStopsMissingName()throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("newStops.json");
        try {
            p.parse();
        }
        catch(IOException i){
            fail("blah io");
        }
        catch (JSONException j){
            fail("blah json");
        }
        catch(StopDataMissingException s){
            //do nothing
        }
        assertEquals(3, StopManager.getInstance().getNumStops());
        assertEquals(1, RouteManager.getInstance().getNumRoutes());
    }

    @Test //test with missing lat
    public void testStopsMissingName() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingName.json");
        try {
            p.parse();
        }
        catch(IOException i){
            fail("IO Exception / Should not be thrown.");
        }
        catch (JSONException j){
            fail("JSON Exception / Should not be thrown.");
        }
        catch(StopDataMissingException s){
            System.out.println("StopDataMissingException caught.");
        }
        assertEquals(3, StopManager.getInstance().getNumStops());
        assertEquals(4, RouteManager.getInstance().getNumRoutes());
    }

    @Test //test with duplicate names
    public void testDuplicateNames()throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingRoutes.json");
        try {
            p.parse();
        }
        catch(IOException i){
            fail("IO Exception / Should not be thrown.");
        }
        catch (JSONException j){
            fail("JSON Exception / Should not be thrown.");
        }
        catch(StopDataMissingException s){
            System.out.println("StopDataMissingException caught.");
        }
        assertEquals(3, StopManager.getInstance().getNumStops());
        assertEquals(3, RouteManager.getInstance().getNumRoutes());
    }

    @Test //test with missing stopNo
    public void testMissingStopNo()throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingStopNo.json");
        try {
            p.parse();
        }
        catch(IOException i){
            fail("IO Exception / Should not be thrown.");
        }
        catch (JSONException j){
            fail("JSON Exception / Should not be thrown.");
        }
        catch(StopDataMissingException s){
            System.out.println("StopDataMissingException caught.");
        }
        assertEquals(1, StopManager.getInstance().getNumStops());
        assertEquals(1, RouteManager.getInstance().getNumRoutes());
    }
    @Test //test with missing stopNo
    public void testMissingLat()throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingLat.json");
        try {
            p.parse();
        }
        catch(IOException i){
            fail("IO Exception / Should not be thrown.");
        }
        catch (JSONException j){
            fail("JSON Exception / Should not be thrown.");
        }
        catch(StopDataMissingException s){
            System.out.println("StopDataMissingException caught.");
        }
        assertEquals(1, StopManager.getInstance().getNumStops());
        assertEquals(1, RouteManager.getInstance().getNumRoutes());
    }

    @Test //test added after try #1
    public void testRouteContainStops()throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stopsMissingLat.json");
        try {
            p.parse();
        }
        catch(IOException i){
            fail("IO Exception / Should not be thrown.");
        }
        catch (JSONException j){
            fail("JSON Exception / Should not be thrown.");
        }
        catch(StopDataMissingException s){
            System.out.println("StopDataMissingException caught.");
        }
        assertTrue(RouteManager.getInstance().getRouteWithNumber("C23").hasStop(StopManager.getInstance().getStopWithNumber(50001)));
        assertFalse(RouteManager.getInstance().getRouteWithNumber("C21").hasStop(StopManager.getInstance().getStopWithNumber(50002)));
    }




}
