import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.*;
import com.google.gson.*;
import java.util.ArrayList;
import java.lang.Math;

public class satellite {
	// Creates an Arraylist that will hold a list of satellites using the structure
	// of the satellitetype class
	ArrayList<satellitetype> sats = new ArrayList<satellitetype>();

	public void refresh(double lat, double lon, double alt, int zoom, int catagory) {
		// Creates a temp variable that holds category, this is for including the debris
		// to the arraylist with the category
		int temp = catagory;
		// If the temp ends up being equal to the category that includes debris, set the
		// actually category to 0 (which gets everything, debris included)
		if (temp == 1000) {
			catagory = 0;
		}
		// Create link that will be used to call the satellite API. Includes the user's
		// location via latitude and longitude, their altitude, the search radius (which
		// always remains 70 degrees)
		// the category the user picked, and the API key we generated for use.
		try {
			String link = "https://api.n2yo.com/rest/v1/satellite/above/" + Double.toString(lat) + "/"
					+ Double.toString(lon) + "/" + Double.toString(alt) + "/70/" + catagory
					+ "/&apiKey=3DM8MB-57EGHM-ZBX8TF-4SV3";
			// Prep HTTPclient to call the API
			HttpClient client = HttpClient.newHttpClient();
			// Build the URI with HTTPRequest
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).build();
			// Send the request off to the API, which will then be held in response
			var response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
			// Prep JSON Parser, which is using the GSON Google Library
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			// Create a Json Object that takes in the response variable, with parameters
			// from the JsonObject class
			JsonObject responseJson = new Gson().fromJson(response, JsonObject.class);
			// Create a JSONArray, that takes in the data from the JSON String with
			// parameters from the JsonArray class
			JsonArray responseArray = new Gson().fromJson(responseJson.get("above").toString(), JsonArray.class);
			// Create the satellite array that will hold the parsed JSON data, with the
			// parameters from the created satellitetype class
			satellitetype[] satellites = gson.fromJson(responseArray, satellitetype[].class);
			// Clear the Arraylist to prep it for the new data to be entered
			sats.clear();
			// Width of the image to find the satellites on
			double width = 0;
			// The width that we need changes with the zoom level so we used a switch to
			// change what the width of the satellite field would be depending on the zoom
			// level
			switch (zoom) {
			case 7:
				width = 3.4;
				break;
			case 8:
				width = 1.5;
				break;
			case 9:
				width = .9;
				break;
			case 10:
				width = .45;
				break;
			default:
				width = 1.5;
				break;
			}
			// For each satellite in the parsed JSON data...
			for (satellitetype spaceTrash : satellites) {
				// If the latitude/longitude of the satellite is within the specified width
				// enter the if statement
				if ((Math.abs(spaceTrash.satlat - lat) <= width) && (Math.abs(spaceTrash.satlng - lon) <= width)) {
					// If the satllite name does not have deb (debris)
					if ((spaceTrash.satname.indexOf("DEB") > -1)) {
						// And if temp is not equal to 1000 (any debris case), then continue and don't
						// add that debris satellite to the arraylist
						if ((temp != 1000)) {
							continue;
						}

					}
					// Add the satellite to the arraylist
					sats.add(spaceTrash);
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong. Please check your internet connection and restart the program.");
            System.exit(0);
		}
	}
}