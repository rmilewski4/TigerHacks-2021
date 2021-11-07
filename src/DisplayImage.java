
import java.awt.image.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.Hashtable;

public class DisplayImage {
    JFrame frame;
    JLabel key;
    JLabel lbl;
    ImageIcon icon;
    JSlider slide;
    JTextArea textArea;
    JScrollPane scrollableTextArea;
    int zoom = 8;

    public DisplayImage() throws IOException {

        frame = new JFrame();
        frame.setSize(1000, 1000);
        key = new JLabel();
        lbl = new JLabel();
        frame.setLayout(new FlowLayout());
        // frame.setLayout(new GridLayout());
        key.setFont(new Font("Calibri", Font.BOLD, 30));

        textArea = new JTextArea(20, 20);
        Font font = new Font("Calibri", Font.BOLD, 20);
        textArea.setFont(font);
        textArea.setEditable(false);
        scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.setVisible(false);
        frame.add(lbl);
        frame.pack();
        frame.add(scrollableTextArea);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        slide = new JSlider(JSlider.HORIZONTAL, 7, 10, 8);
        slide.setPreferredSize(new Dimension(650, 80));
        slide.setMajorTickSpacing(1);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(7, new JLabel("Zoom Out"));
        labels.put(10, new JLabel("Zoom In"));
        slide.setLabelTable(labels);

        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
        slide.setSnapToTicks(true);
        frame.add(slide);
        JLabel filler = new JLabel();
        filler.setText("         Satellite Tracker (TM)");
        filler.setFont(new Font("Calibri", Font.BOLD, 30));
        frame.add(filler);

    }

    public void refresh(BufferedImage img, String compass) throws IOException {
        frame.setVisible(true);
        if (zoom == 7) {
            key.setFont(new Font("Calibri", Font.BOLD, 10));
        } else {
            key.setFont(new Font("Calibri", Font.BOLD, 20));
        }
        icon = new ImageIcon(img);
        key.setText(compass);

        textArea.setText(compass);
        // textArea.append(compass);

        lbl.setIcon(icon);

        frame.revalidate();
        frame.repaint();
        frame.setSize(img.getWidth() + 400, img.getHeight() + 140);
        zoom = slide.getValue();
    }

}
