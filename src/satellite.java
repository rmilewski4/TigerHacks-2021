import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.*;
import com.google.gson.*;
import java.util.ArrayList; 
import java.lang.Math;


public class satellite {
    

    ArrayList<satellitetype> sats = new ArrayList<satellitetype>();

    public void refresh(double lat, double lon, double alt) {
        String link = "https://api.n2yo.com/rest/v1/satellite/above/" + Double.toString(lat) + "/" + Double.toString(lon) + "/" + Double.toString(alt) + "/70/0/&apiKey=3DM8MB-57EGHM-ZBX8TF-4SV3";
        HttpClient client = HttpClient.newHttpClient();
   		HttpRequest request = HttpRequest.newBuilder()
        	 .uri(URI.create(link))
			 .build();
			  
   		var response = client.sendAsync(request, BodyHandlers.ofString())
        	 .thenApply(HttpResponse::body)
			 .join();

		GsonBuilder builder = new GsonBuilder(); 
		builder.setPrettyPrinting(); 
		Gson gson = builder.create();

		JsonObject responseJson = new Gson().fromJson(response, JsonObject.class);
		JsonArray responseArray = new Gson().fromJson(responseJson.get("above").toString(), JsonArray.class);
        satellitetype[] satellites = gson.fromJson(responseArray, satellitetype[].class);
        sats.clear();
        for (satellitetype spaceTrash : satellites) {
			if ((Math.abs(spaceTrash.satlat -lat) <=1.8) && (Math.abs(spaceTrash.satlng -lon )<=1.8) ) {
				sats.add(spaceTrash);
			}
        }
        /*for(int i = 0; i < sats.size(); i++) {
            System.out.println(sats.get(i).satname);
        }*/
    }
}
