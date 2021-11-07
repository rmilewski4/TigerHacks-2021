import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.*;
import com.google.gson.*;
import java.util.ArrayList;
import java.lang.Math;

public class satellite {

	ArrayList<satellitetype> sats = new ArrayList<satellitetype>();

	public void refresh(double lat, double lon, double alt, int zoom, int catagory) {
		String link = "https://api.n2yo.com/rest/v1/satellite/above/" + Double.toString(lat) + "/"
				+ Double.toString(lon) + "/" + Double.toString(alt) + "/70/" + Double.toString(catagory + 0.0)
				+ "/&apiKey=3DM8MB-57EGHM-ZBX8TF-4SV3";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).build();

		var response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		JsonObject responseJson = new Gson().fromJson(response, JsonObject.class);
		JsonArray responseArray = new Gson().fromJson(responseJson.get("above").toString(), JsonArray.class);
		satellitetype[] satellites = gson.fromJson(responseArray, satellitetype[].class);
		sats.clear();

		double width = 0;
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

		for (satellitetype spaceTrash : satellites) {
			if ((Math.abs(spaceTrash.satlat - lat) <= width) && (Math.abs(spaceTrash.satlng - lon) <= width)) {
				sats.add(spaceTrash);
			}
		}
	}
}