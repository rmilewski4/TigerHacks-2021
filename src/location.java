import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.*;
public class location {
	double lat=0;
	double lon=0;
	String link = "https://api.getgeoapi.com/v2/ip/check?api_key=88396d3dc2b007e4c23ac95a37f7a3fc0be75cf3&format=json";


	public location() {
		HttpClient client = HttpClient.newHttpClient();
   		HttpRequest request = HttpRequest.newBuilder()
        	 .uri(URI.create(link))
        	 .build();
   		var response = client.sendAsync(request, BodyHandlers.ofString())
        	 .thenApply(HttpResponse::body)
         	.thenAccept(System.out::println)
			 .join();
	}
    
}
