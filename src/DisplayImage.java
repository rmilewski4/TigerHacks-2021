import java.awt.FlowLayout;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class DisplayImage {
    JFrame frame;
    public DisplayImage(BufferedImage img) throws IOException
    {
        int w = img.getWidth();
        int h = img.getHeight();

        ImageIcon icon=new ImageIcon(img);
        frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(w,h);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void refresh(BufferedImage img) throws IOException {
        int w = img.getWidth();
        int h = img.getHeight();

        ImageIcon icon=new ImageIcon(img);
        frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(w,h);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}