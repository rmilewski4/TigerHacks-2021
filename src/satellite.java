import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.*;
import com.google.gson.*;
import java.util.ArrayList; 


public class satellite {
    String link = "https://api.spacexdata.com/v4/starlink";

    ArrayList<satellitetype> sats = new ArrayList<satellitetype>();

    public void refresh(double lat, double lon) {
        HttpClient client = HttpClient.newHttpClient();
   		HttpRequest request = HttpRequest.newBuilder()
        	 .uri(URI.create(link))
			 .build();
			  
   		var response = client.sendAsync(request, BodyHandlers.ofString())
             .thenApply(HttpResponse::body)
            // .thenAccept(System.out::println)
			 .join();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        satellitetype[] satellites = gson.fromJson(response, satellitetype[].class);
        /*for (satellitetype spaceTrash : satellites) {
            
            System.out.println(spaceTrash.spaceTrack.OBJECT_NAME);
            System.out.println(spaceTrash.latitude + "\n");
        } */
        System.out.println(satellites[1000].latitude);
    }
}
