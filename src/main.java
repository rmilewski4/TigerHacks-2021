import java.io.*;
import java.lang.*;
public class main {
    public static void main(String[] args) throws IOException , InterruptedException {
        System.out.println("test");
        location loc = new location();
        satellite satellites = new satellite();
       // satellites.refresh(loc.lat,loc.lon,loc.alt);
        //System.out.println(satellites.sats.get(0).satname);
        image imagefile = new image();
        
        while(true) {
            satellites.refresh(loc.lat,loc.lon,loc.alt);
            imagefile.refresh(loc.lat,loc.lon,satellites.sats);
            Thread.sleep(1000);
        }
    }
}