import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.*;
import com.google.gson.*;

public class location {
	// creates latitude, longitude, and altitude atributes of the class
	double lat = 0;
	double lon = 0;
	double alt = 0;
	// link of location based api with api key
	String link = "https://api.getgeoapi.com/v2/ip/check?api_key=88396d3dc2b007e4c23ac95a37f7a3fc0be75cf3&format=json";

	// constructor runs when a location class is created
	public location() {
		try {
			HttpClient client = HttpClient.newHttpClient(); // sets up httpclient to call api
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).build();
			// defines how to handle the request
			var response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
			// calls the api and gets response
			GsonBuilder builder = new GsonBuilder(); // initializes the Gson JSon parser
			builder.setPrettyPrinting(); // sets mode for parser

			JsonObject responseJson = new Gson().fromJson(response, JsonObject.class);
			// turns the response from the http get into a json file
			JsonObject LatLon = new Gson().fromJson(responseJson.get("location").toString(), JsonObject.class);
			// gets the location json from the json response
			lat = LatLon.get("latitude").getAsDouble(); // gets the latitude from the json
			lon = LatLon.get("longitude").getAsDouble(); // gets the longitude from the json

			// address of api to get the altitude
			String link2 = "https://api.open-elevation.com/api/v1/lookup?locations=" + Double.toString(lat) + ","
					+ Double.toString(lon); // adds lat and lon to api call
			request = HttpRequest.newBuilder().uri(URI.create(link2)).build(); // formats api request
			response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join(); // calls
																												// api

			builder = new GsonBuilder();
			builder.setPrettyPrinting();
			JsonObject modified = new Gson().fromJson(response, JsonObject.class); // turns elevation response into a
																					// json
			var newMod = modified.toString(); // turns the json into a string
			newMod = newMod.replace('[', ' ');
			// removes the []s from the string because then the parser tries to treat it as
			// a JsonArray
			newMod = newMod.replace(']', ' ');
			// which causes a lot of problems and makes everything much more complicated

			JsonObject elevationObj = new Gson().fromJson(newMod, JsonObject.class);
			// turns the modifies string back into a json
			JsonObject elevationObj2 = new Gson().fromJson(elevationObj.get("results").toString(), JsonObject.class);
			// gets the results sub json from the result json

			alt = elevationObj2.get("elevation").getAsDouble();
			// sets the global variable to the result from the json
		} catch (Exception e) {
			System.out.println("Something went wrong. Please check your internet connection and restart the program.");
			System.exit(0);
		}
	}

}
