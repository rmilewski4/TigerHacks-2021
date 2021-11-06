import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import com.google.gson.*; 
import javax.imageio.*;
import java.io.*;
public class image {
    BufferedImage myPicture = null;

    public image(double lat, double lon) throws IOException {
        String link = "https://api.nasa.gov/planetary/earth/imagery?lon=" + Double.toString(lon) + "&lat=" + Double.toString(lat) + "&api_key=7kdEcYGWn9fxCcqJ59Jyig1JqxGnQhSmWhjRdyCG";
        HttpClient client = HttpClient.newHttpClient();
   		HttpRequest request = HttpRequest.newBuilder()
        	 .uri(URI.create(link))
			 .build();
			  
   		var response = client.sendAsync(request, BodyHandlers.ofString())
        	 .thenApply(HttpResponse::body)
			 .join();
		URL url = new URL(link);
		Image image = ImageIO.read(url);
        //myPicture = ImageIO.read(img);
    }
}
