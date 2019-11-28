package ca.ubc.cs.cpsc210.translink.providers;

import ca.ubc.cs.cpsc210.translink.BusesAreUs;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for Translink Arrival Data Provider
 */
public class HttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Stop stop;

    public HttpArrivalDataProvider(Stop stop) {
        super();
        this.stop = stop;
    }

    @Override
    /**
     * Produces URL used to query Translink web service for expected arrivals at
     * the stop specified in call to constructor.
     *
     * @returns URL to query Translink web service for arrival data
     *
     */
    protected URL getURL() throws MalformedURLException {
        URL stopURL = null;
        try {
            Stop selectedStop = StopManager.getInstance().getSelected();
            if (selectedStop != null) {
                int selectedStopNum = selectedStop.getNumber();
                stopURL = new URL
                        ("http://api.translink.ca/rttiapi/v1/stops/" + selectedStopNum + "/estimates?apikey=kOMI1Flzf2ALZFMvXKuw");
            }
        } catch (MalformedURLException me) {
            System.out.println("URL is malformed.");
        }
        return stopURL;
    }

    @Override
    public byte[] dataSourceToBytes() throws IOException {
        return new byte[0];
    }
}
