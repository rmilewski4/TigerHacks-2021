import java.io.*;

public class main {

    public static void main(String[] args) throws IOException, InterruptedException {
        location loc = new location();
        satellite satellites = new satellite();
        image imagefile = new image();
        int zoom = 8;
        int catagory = 0;
        while (true) {
            satellites.refresh(loc.lat, loc.lon, loc.alt, zoom, catagory);
            imagefile.refresh(loc.lat, loc.lon, satellites.sats, zoom);
            zoom = imagefile.getZoom();
            catagory = imagefile.getCategory();
            Thread.sleep(750);
        }
    }
}