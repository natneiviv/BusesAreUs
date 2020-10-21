package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.model.exception.StopException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test the StopManager
 */
public class StopManagerTest {
    private Map<Integer, Stop> stopMap;
    private Stop s1, s2;
    private StopManager sm;
    private LatLon defaultLatLon;

    @Before
    public void setup() {
        StopManager.getInstance().clearStops();
        s1 = new Stop (1, "A", new LatLon(49.2, -123.4));
        s2 = new Stop (2, "B", new LatLon(49.21, -123.41));
        defaultLatLon = new LatLon(49.2609, -123.1139);
        sm = StopManager.getInstance();
        stopMap = new HashMap<Integer, Stop>();


    }

    @Test
    public void testBoring() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        Stop r = StopManager.getInstance().getStopWithNumber(9999);
        assertEquals(s9999, r);
    }

    @Test
    public void testConstructor(){
        StopManager sm1 = StopManager.getInstance();
        assertEquals(0, sm1.getNumStops());
        // test for null?
    }

    @Test (expected = StopException.class)
    public void testGetSelected() throws StopException{
        try {
            sm.setSelected(s1);
            assertNull(sm.getSelected());
        }
        catch (Exception e){
            throw new StopException("Should not get this exception.");
        }


    }

    @Test
    public void testGetSelectedNotNull() throws StopException{
        try {
            Stop s = StopManager.getInstance().getStopWithNumber(1);
            sm.setSelected(s);
        }
        catch(Exception e){
            throw new StopException("Should not get this exception.");
        }
    }

    @Test
    public void testGetStopWithNumber(){
        Stop s = StopManager.getInstance().getStopWithNumber(1);
        s.setName("A");
        s.setLocn(new LatLon(49.2, -123.4));
        assertEquals(s1, sm.getStopWithNumber(1));
        assertEquals("A", sm.getStopWithNumber(1).getName());
        assertEquals( new LatLon(49.2, -123.4), sm.getStopWithNumber(1).getLocn());
        assertEquals(s2, sm.getStopWithNumber(2));
        assertEquals("", sm.getStopWithNumber(2).getName());
        assertEquals(defaultLatLon, sm.getStopWithNumber(2).getLocn());
    }

    @Test (expected = StopException.class)
    public void testSetSelectedStopNoException() throws StopException{
        try {
            stopMap.put(1, s1);
            sm.setSelected(s1);
            sm.clearSelectedStop();
            sm.setSelected(s2);
        }
        catch(Exception e) {
            throw new StopException("Selected stop does not exist");
        }
    }


    @Test
    public void testGetNumStops(){
        assertEquals(0, sm.getNumStops());
        Stop s = StopManager.getInstance().getStopWithNumber(1);
        Stop s2 = StopManager.getInstance().getStopWithNumber(2);
        assertEquals(2, sm.getNumStops());
        sm.clearStops();
        assertEquals(0, sm.getNumStops());

    }

    @Test
    public void testFindNearestTo(){
        Stop s = StopManager.getInstance().getStopWithNumber(1);
        assertEquals(s, sm.findNearestTo(defaultLatLon));
        assertNull(sm.findNearestTo(new LatLon(0,0)));

    }
}
