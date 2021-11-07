
import java.awt.image.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

public class DisplayImage {
    JFrame frame;
    JLabel key;
    JLabel lbl;
    ImageIcon icon;

    public DisplayImage() throws IOException {

        frame = new JFrame();
        frame.setSize(1500, 600);
        key = new JLabel();
        lbl = new JLabel();
        frame.setLayout(new GridLayout());
        key.setFont(new Font("Calibri", Font.BOLD, 30));
        frame.setVisible(false);
        frame.add(lbl);
        frame.pack();
        frame.add(key);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void refresh(BufferedImage img, String compass) throws IOException {
        frame.setVisible(true);
        icon = new ImageIcon(img);
        key.setText(compass);
        lbl.setIcon(icon);
        key.setFont(new Font("Calibri", Font.BOLD, 20));
        frame.revalidate();
        frame.repaint();
        frame.setSize(img.getWidth() * 2 + 30, img.getHeight() + 35);
    }
}