import java.io.*;

public class main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Creates a new object that goes to the location class, goes into location
        // constructor
        location loc = new location();
        // Creates a new object that goes to the satellite class, goes into satellites
        // constructor
        satellite satellites = new satellite();
        // Creates a new object that goes to the image class, goes into image
        // constructor
        image imagefile = new image();
        // Creates variables that will be passed inbetween classes for use by the
        // program, zoom to zoom the image in and out
        int zoom = 8;
        // Holds category ID to choose what satellites are displayed by the API
        int catagory = 0;
        // Main executable loop
        while (true) {
            // Gets the new satellite list by calling the refresh function in the satellite
            // class. Will take in the users latitude, longitude, altitude, their zoom
            // level, and category
            satellites.refresh(loc.lat, loc.lon, loc.alt, zoom, catagory);
            // Updates the image drawn to the screen by taking in the users latitude,
            // longitude, the arraylist holding all of the satellites, and the zoom level
            imagefile.refresh(loc.lat, loc.lon, satellites.sats, zoom);
            // Find what the zoom is by calling the specified function
            zoom = imagefile.getZoom();
            // Find what the category is by calling the specified function
            catagory = imagefile.getCategory();
            // Wait for 750 Milliseconds before continuing
            Thread.sleep(750);
        }
    }
}