import java.net.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
import java.lang.Math;

public class image {
	BufferedImage myPicture = null;
	BufferedImage image;
	DisplayImage window;
	// global variables and atributes

	public image() throws IOException {
		window = new DisplayImage(); // initalizes the window when a image class is created
	}

	public void refresh(double lat, double lon, ArrayList<satellitetype> sats, int zoom) throws IOException {
		try {
			// creates the link to call the google api with, given the lat,lon,zoom, and api
			// key
			String link = "https://maps.googleapis.com/maps/api/staticmap?center=%22" + Double.toString(lat) + ","
					+ Double.toString(lon) + "%22&maptype=hybrid&style=road.highway&size=1000x1000&zoom=" + zoom
					+ "&key=/*APIKEYGOESHERE*/";
			// creates string that will contain the location and label of each satellite to
			// display on the map
			String labels = "";
			// Creates the beginning of the string to be printed in the TextBox
			String newKey = "Satellites:\n\n";
			// each satellite needs to have a label so that in can be identied in the key,
			// so this variable does that
			char char_label = 'A';
			// loops through every satellite in the arraylist
			for (satellitetype satallite : sats) {
				// creates label to send to google to make a pin on the map
				labels += "&markers=color:red%7Clabel:" + char_label + "%7C" + Double.toString(satallite.satlat) + ","
						+ Double.toString(satallite.satlng) + "%7C";
				// formats the string for the key in the textbox
				newKey += "Satellite " + char_label + ": " + satallite.satname + "\n";
				newKey += "Altitude of " + char_label + ": " + Math.round(satallite.satalt) + " km\n\n";
				// moves label to next letter
				char_label += 1;
			}
			link += labels; // combines the request with the satellites that need to be displayed on the
			// turns link into url object
			URL url = new URL(link);
			// gets the image from the google api with the attributes attached in the link
			image = ImageIO.read(url);
			window.refresh(image, newKey); // refreshes the window with the new image, and new key
		} catch (Exception e) {
			System.out.println("Something went wrong. Please check your internet connection and restart the program.");
            System.exit(0);
		}
	}

	public int getZoom() {
		return window.zoom; // returns zoom of the window object of DisplayImage class
	}

	public int getCategory() {
		return window.category; // returns the catagory attribute of the window object of the displayImage class
	}
}
