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
    public DisplayImage(BufferedImage img) throws IOException
    {
        BufferedImage before = img;
        int w = before.getWidth();
        int h = before.getHeight();
        w=721;
        h=721;
        System.out.println("Height = " + h + " Width = " + w);
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(721.0/2048,721.0/2048);
        AffineTransformOp scaleOp = 
        new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);

        ImageIcon icon=new ImageIcon(after);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(w,h);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}