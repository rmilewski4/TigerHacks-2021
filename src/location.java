import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.*;
import com.google.gson.*;

class results {
	public double latitude;
	public double longitude;
	public double elevation;
}

public class location {
	double lat = 0;
	double lon = 0;
	double alt = 0;
	String link = "https://api.getgeoapi.com/v2/ip/check?api_key=88396d3dc2b007e4c23ac95a37f7a3fc0be75cf3&format=json";

	public location() {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).build();

		var response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();

		JsonObject responseJson = new Gson().fromJson(response, JsonObject.class);
		JsonObject LatLon = new Gson().fromJson(responseJson.get("location").toString(), JsonObject.class);
		lat = LatLon.get("latitude").getAsDouble();
		lon = LatLon.get("longitude").getAsDouble();

		String link2 = "https://api.open-elevation.com/api/v1/lookup?locations=" + Double.toString(lat) + ","
				+ Double.toString(lon);
		request = HttpRequest.newBuilder().uri(URI.create(link2)).build();
		response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();

		builder = new GsonBuilder();
		builder.setPrettyPrinting();
		JsonObject modified = new Gson().fromJson(response, JsonObject.class);
		var newMod = modified.toString();
		newMod = newMod.replace('[', ' ');
		newMod = newMod.replace(']', ' ');
		JsonObject elevationObj = new Gson().fromJson(newMod, JsonObject.class);
		JsonObject elevationObj2 = new Gson().fromJson(elevationObj.get("results").toString(), JsonObject.class);

		alt = elevationObj2.get("elevation").getAsDouble();
	}

}
