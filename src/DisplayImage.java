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
    JLabel key = new JLabel();
    public DisplayImage(BufferedImage img, String compass) throws IOException
    {
        int w = img.getWidth();
        int h = img.getHeight();

        ImageIcon icon=new ImageIcon(img);
        frame=new JFrame();
        frame.setLayout(new GridLayout());
        frame.setSize(w+200,h);
        JLabel lbl=new JLabel();
        key.setText(compass);
        key.setFont(new Font("Calibri", Font.BOLD, 30));
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.pack();
        frame.add(key);
        frame.pack();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}