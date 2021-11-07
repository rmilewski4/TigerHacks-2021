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

	/*
	 * public image(double lat, double lon,ArrayList<satellitetype> sats) throws
	 * IOException { String link =
	 * "https://maps.googleapis.com/maps/api/staticmap?center=%22" +
	 * Double.toString(lat) + "," + Double.toString(lon) +
	 * "%22&maptype=hybrid&style=road.highway&size=1000x1000&zoom=8&key=AIzaSyDUBTNpv8NESX8ipwc8PTqqHkON7vUlEBs";
	 * String labels = "&markers=color:red%7C"; for (satellitetype satallite : sats)
	 * { labels += Double.toString(satallite.satlat)+ "," +
	 * Double.toString(satallite.satlng) + "%7C"; } link+=labels;
	 * System.out.println(link.charAt(205)); HttpClient client =
	 * HttpClient.newHttpClient(); HttpRequest request = HttpRequest.newBuilder()
	 * .uri(URI.create(link)) .build();
	 * 
	 * var response = client.sendAsync(request, BodyHandlers.ofString())
	 * .thenApply(HttpResponse::body) .join(); System.out.println(link); URL url =
	 * new URL(link); BufferedImage image = ImageIO.read(url); DisplayImage window =
	 * new DisplayImage(image); }
	 */
	public image() throws IOException {
		window = new DisplayImage();
	}

	public void refresh(double lat, double lon, ArrayList<satellitetype> sats) throws IOException {
		String link = "https://maps.googleapis.com/maps/api/staticmap?center=%22" + Double.toString(lat) + ","
				+ Double.toString(lon)
				+ "%22&maptype=hybrid&style=road.highway&size=1000x1000&zoom=8&key=AIzaSyDUBTNpv8NESX8ipwc8PTqqHkON7vUlEBs";
		String labels = "";
		String key = "<html> Satellites: <br/><br/>";
		char char_label = 'A';
		for (satellitetype satallite : sats) {
			labels += "&markers=color:red%7Clabel:" + char_label + "%7C" + Double.toString(satallite.satlat) + ","
					+ Double.toString(satallite.satlng) + "%7C";
			key += "Satellite " + char_label + ": " + satallite.satname + "<br/>";
			key += "Altitude " + char_label + ": " + Math.round(satallite.satalt) + " km<br/><br/>";
			char_label += 1;
		}
		key += "</html>";
		link += labels;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).build();

		client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
		URL url = new URL(link);
		image = ImageIO.read(url);
		window.refresh(image, key);
	}
}