
import java.awt.image.*;
import java.io.IOException;
import javax.swing.*;
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
    JComboBox categories;
    int zoom = 8;
    int category = 0;
    String[] cats = { "Select Satellite Type", "All Categories", "Brightest Satellites", "Starlink", "Military", "GPS",
            "Weather", "Satellite Radio (Sirius XM)", "Television", "Scientific", "Iridium" };
    int[] cat_ids = { 0, 0, 1, 52, 30, 20, 3, 33, 34, 26, 15 };

    public DisplayImage() throws IOException {
        frame = new JFrame();
        frame.setSize(1000, 1000);
        key = new JLabel();
        lbl = new JLabel();
        frame.setLayout(new FlowLayout());
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
        categories = new JComboBox(cats);
        categories.setSelectedIndex(0);
        categories.setPreferredSize(new Dimension(350, 20));
        frame.add(categories);
    }

    public void refresh(BufferedImage img, String compass) throws IOException {
        frame.setVisible(true);
        icon = new ImageIcon(img);
        key.setText(compass);

        textArea.setText(compass);

        lbl.setIcon(icon);

        frame.revalidate();
        frame.repaint();
        frame.setSize(img.getWidth() + 400, img.getHeight() + 140);
        zoom = slide.getValue();
        for (int i = 0; i < cats.length; i++) {
            if (cats[i].equals(categories.getSelectedItem().toString())) {
                category = cat_ids[i];
            }
        }
    }

}
