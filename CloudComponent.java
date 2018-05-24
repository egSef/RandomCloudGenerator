package randomcirclesgui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
/**
 * Displays the cloud
 * @author egsef
 */
public class CloudComponent extends JComponent {

    private int cloudDensity;

    public CloudComponent(int n) {
        cloudDensity = n;
    }

    public void setDensity(int n) {
        cloudDensity = n;
    }

    public int getDensity() {
        return cloudDensity;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Cloud cloud = new Cloud();
        cloud.draw(g2, cloudDensity);
    }
}
