package randomcirclesgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;

/**
 * Part 1; A program that displays a cloud of circles at random positions. Menu
 * items “Fewer” and “More” generate fewer or more random circles. Each time the
 * user selects “Fewer”, the count should be halved. Each time the user clicks
 * on “More”, the count should be doubled.
 *
 * //Part 2 Modification of the program by adding a slider to generate more or
 * fewer random circles in the cloud.
 *
 * @author egsef
 * @version 1.1.1
 */
public class RandomCloudFrame extends JFrame {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private JMenu fileMenu;
    private JMenu generatorMenu;
    private JSlider slider;
    private static CloudComponent cloud;
    static int cloudDensity;
    static JTextField t;
    private final JPanel contentPane;

    /**
     * Constructs the Cloud Frame
     */
    public RandomCloudFrame() {
        //Construct the menu     
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(createFileMenu());
        menuBar.add(createGeneratorMenu());

        //Construct the cloud component.
        generatorMenu.add(createGenerateItem());
        generatorMenu.add(createFewerItem());
        generatorMenu.add(createMoreItem());

        //Construct the content pane.
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(51,51,51));
        contentPane.setForeground(Color.ORANGE);
        TitledBorder b = new TitledBorder(
                "Random cloud display with slider & initial generation of 50 circles");
        b.setTitleColor(Color.ORANGE);
        contentPane.setBorder(b);
        cloud = new CloudComponent(50);
        contentPane.add(cloud, BorderLayout.CENTER);
        t = new JTextField();
        t.setBackground(new Color(51,51,51));
        t.setForeground(Color.GREEN);
        t.setText("Cloud Density: " + cloud.getDensity());
        
        add(t, BorderLayout.SOUTH);
        add(createSlider(), BorderLayout.EAST);
        add(contentPane);
        setTitle("Random Cloud Generator with a Menu and A Slider");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * The main program for running the application.
     */
    public static void main(String[] args) {
        new RandomCloudFrame();
    }

    private JMenu createFileMenu() {
        fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        exitItem.addActionListener(new ExitItemListener());
        fileMenu.add(exitItem);
        return fileMenu;
    }

    /**
     * Generates a cloud from a given density
     * @param n the density of the cloud.
     */
    public void generateCloud(int n) {
        cloud = new CloudComponent(n);
        contentPane.add(cloud, BorderLayout.CENTER);
    }

    private JSlider createSlider() {
        slider = new JSlider(0, 20000, 50);
        slider.setMajorTickSpacing(1000);
        slider.setOrientation(SwingConstants.VERTICAL);
        slider.setBackground(new Color(51,51,51));
        slider.setForeground(Color.ORANGE);
        slider.setBorder(new EtchedBorder());
        slider.setFont(new Font("Dialog", 1, 12));
        slider.addChangeListener(new CloudDensityListener());
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        return slider;
    }
    
    //inner class for implementing the change listener 
    class CloudDensityListener implements ChangeListener {
        public void stateChanged(ChangeEvent event) {
            setCloudDensity();
        }
    }

    public void setCloudDensity() {
        int n = slider.getValue();
        cloud.setDensity(n);
        t.setText("Cloud Density: " + cloud.getDensity());
        repaint();
    }

    private JMenu createGeneratorMenu() {
        generatorMenu = new JMenu("Generator");
        return generatorMenu;
    }

    private JMenuItem createGenerateItem() {
        class GenerateItemListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                cloud.setDensity(50);
                t.setText("Cloud Density: " + cloud.getDensity());
                repaint();
            }
        }
        JMenuItem generateItem = new JMenuItem("Generate 50");
        ActionListener listener3 = new GenerateItemListener();
        generateItem.addActionListener(listener3);
        return generateItem;
    }

    private JMenuItem createFewerItem() {
        class FewerItemListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                int n = cloud.getDensity() / 2;
                cloud.setDensity(n);
                t.setText("Cloud Density: " + cloud.getDensity());
                repaint();
            }
        }
        JMenuItem fewerItem = new JMenuItem("Fewer --");
        fewerItem.setMnemonic(KeyEvent.VK_T);
        ActionListener listener2 = new FewerItemListener();
        fewerItem.addActionListener(listener2);
        return fewerItem;
    }

    private JMenuItem createMoreItem() {
        JMenuItem moreItem = new JMenuItem("More ++");
        moreItem.setMnemonic(KeyEvent.VK_B);
        generatorMenu.add(moreItem);
        ActionListener listener3 = new MoreItemListener();
        moreItem.addActionListener(listener3);
        return moreItem;
    }

    class MoreItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int n = cloud.getDensity() * 2;
            cloud.setDensity(n);
            t.setText("Cloud Density: " + cloud.getDensity());
            repaint();
        }
    }
        //inner class for exit item listener
    class ExitItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }
}
