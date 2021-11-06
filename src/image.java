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
        String link = "https://api.nasa.gov/planetary/earth/imagery?lon=" + Double.toString(lon) + "&lat=" + Double.toString(lat) + "&dim=0.64&api_key=7kdEcYGWn9fxCcqJ59Jyig1JqxGnQhSmWhjRdyCG";
        HttpClient client = HttpClient.newHttpClient();
   		HttpRequest request = HttpRequest.newBuilder()
        	 .uri(URI.create(link))
			 .build();
			  
   		var response = client.sendAsync(request, BodyHandlers.ofString())
        	 .thenApply(HttpResponse::body)
			 .join();
		System.out.println(link);
		URL url = new URL(link);
		BufferedImage image = ImageIO.read(url);
		DisplayImage window = new DisplayImage(image);
        //myPicture = ImageIO.read(img);
    }
}
