import java.io.*;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        location loc = new location();
        satellite satellites = new satellite();
        image imagefile = new image();

        while (true) {
            satellites.refresh(loc.lat, loc.lon, loc.alt);
            imagefile.refresh(loc.lat, loc.lon, satellites.sats);
            Thread.sleep(1500);
        }
    }
}