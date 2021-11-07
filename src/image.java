import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
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

	public image() throws IOException {
		window = new DisplayImage();
	}

	public void refresh(double lat, double lon, ArrayList<satellitetype> sats, int zoom) throws IOException {
		String link = "https://maps.googleapis.com/maps/api/staticmap?center=%22" + Double.toString(lat) + ","
				+ Double.toString(lon) + "%22&maptype=hybrid&style=road.highway&size=1000x1000&zoom=" + zoom
				+ "&key=AIzaSyDUBTNpv8NESX8ipwc8PTqqHkON7vUlEBs";
		String labels = "";
		String newKey = "Satellites:\n\n";
		char char_label = 'A';
		for (satellitetype satallite : sats) {
			labels += "&markers=color:red%7Clabel:" + char_label + "%7C" + Double.toString(satallite.satlat) + ","
					+ Double.toString(satallite.satlng) + "%7C";
			newKey += "Satellite " + char_label + ": " + satallite.satname + "\n";
			newKey += "Altitude of " + char_label + ": " + Math.round(satallite.satalt) + " km\n\n";
			char_label += 1;
		}
		link += labels;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).build();

		client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
		URL url = new URL(link);
		image = ImageIO.read(url);
		window.refresh(image, newKey);
	}

	public int getZoom() {
		return window.zoom;
	}
}