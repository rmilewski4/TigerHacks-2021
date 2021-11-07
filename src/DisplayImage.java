
import java.awt.image.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class DisplayImage {
    // all of the objects needed to build the Jframe window
    JFrame frame;
    JLabel lbl;
    ImageIcon icon;
    JSlider slide;
    JTextArea textArea;
    JScrollPane scrollableTextArea;
    JComboBox categories;

    // user defined variables that need to be accesed by other classes
    int zoom = 8;
    int category = 0;

    // list of satellite datasets that can be used, and their corresponding id's
    // used by the API
    String[] cats = { "Select Satellite Type", "All Categories", "All Categories (include space junk)", "Starlink",
            "Military", "GPS", "Weather", "Satellite Radio (Sirius XM)", "Television", "Scientific", "Iridium" };
    int[] cat_ids = { 0, 0, 1000, 52, 30, 20, 3, 33, 34, 26, 15 };

    // constructor
    public DisplayImage() throws IOException {
        // creates new window
        frame = new JFrame();
        // sets windows size
        frame.setSize(1000, 1000);
        // creates label which contains the image
        lbl = new JLabel();
        // defines the layout as a FlowLayout which works best for this project
        frame.setLayout(new FlowLayout());
        // creates text area for showing the k and defines its size
        textArea = new JTextArea(20, 20);
        // creates and sets fonts
        Font font = new Font("Calibri", Font.BOLD, 20);
        textArea.setFont(font);
        // makes it so you cant change the contents from the ui
        textArea.setEditable(false);
        // adds scroll bar to textarea
        scrollableTextArea = new JScrollPane(textArea);
        // only enables vertical scroll bar
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // makes the imageless window invisable
        frame.setVisible(false);
        // closes the program when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // creates the slider
        slide = new JSlider(JSlider.HORIZONTAL, 7, 10, 8);
        // sets the size of the slider
        slide.setPreferredSize(new Dimension(650, 80));
        // sets the width of the slider
        slide.setMajorTickSpacing(1);
        // hashtable to set labels for slider
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        // sets 7 equal to zoom out and 10 equal to zoom in. 7&10 are the max and min
        // used for the google api's zoom
        labels.put(7, new JLabel("Zoom Out"));
        labels.put(10, new JLabel("Zoom In"));
        slide.setLabelTable(labels);
        // formats the slider
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
        slide.setSnapToTicks(true);
        // createss the combo box for selecting the dataset
        categories = new JComboBox(cats);
        categories.setSelectedIndex(0);
        // sets size of combobox
        categories.setPreferredSize(new Dimension(350, 20));
        // adds all the objects to the page
        frame.add(lbl);
        frame.pack();
        frame.add(scrollableTextArea);
        frame.pack();
        frame.add(slide);
        frame.add(categories);
    }

    public void refresh(BufferedImage img, String compass) throws IOException {
        // reloads the page and gets user defined data

        // makes the page visible
        frame.setVisible(true);
        // draws the image recieved from google
        icon = new ImageIcon(img);
        // sets the key to the text given from compass
        textArea.setText(compass);
        // sets icon to contain the image
        lbl.setIcon(icon);
        // redraws the frame
        frame.revalidate();
        frame.repaint();
        // sets the size of the grame
        frame.setSize(img.getWidth() + 400, img.getHeight() + 140);
        // gets the value of the zoom slider every time the page is redrawn
        zoom = slide.getValue();
        // gets the catagory id of the selection box
        for (int i = 0; i < cats.length; i++) {
            // loops through all posible datasets to find the index of the slected dataset
            if (cats[i].equals(categories.getSelectedItem().toString())) {
                // gets the corosponding ID of the selected dataset
                category = cat_ids[i];
            }
        }
    }

}
