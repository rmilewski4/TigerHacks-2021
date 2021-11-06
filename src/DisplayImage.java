import java.awt.FlowLayout;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import java.awt.*;

public class DisplayImage {
    JFrame frame;
    JLabel key;
    JLabel lbl;
    ImageIcon icon;

    public DisplayImage() throws IOException {

        frame = new JFrame();
        frame.setSize(1200, 1000);
        key = new JLabel();
        lbl = new JLabel();

        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void refresh(BufferedImage img, String compass) throws IOException {
        // frame.removeAll();
        // frame.setVisible(false);
        frame.setSize(1200, 1000);
        icon = new ImageIcon(img);
        frame.setLayout(new GridLayout());
        key.setText(compass);
        lbl.setIcon(icon);
        key.setFont(new Font("Calibri", Font.BOLD, 30));
        frame.add(lbl);
        frame.pack();
        frame.add(key);
        frame.pack();
        frame.setVisible(true);
    }
}