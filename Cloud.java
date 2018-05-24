package randomcirclesgui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class responsible for constructing and drawing a cloud of random circles.
 * @author egsef
 */
public class Cloud {
    private ArrayList<Point2D.Double> cloud;
    private Point2D.Double point;
    private int CIRCLE_SIZE = 15;

    public Cloud() {
        cloud = new ArrayList<>();
    }

    //Adds a Point to the points ArrayList
    public void add(Point2D.Double aPoint) {
        cloud.add(aPoint);
    }

    public void get(int n) {
        cloud.get(n);
    }

    public void increaseSize(int n) {
        CIRCLE_SIZE += 2;
    }

    public void decreaseSize(int n) {
        CIRCLE_SIZE -= 2;
    }

    /**
     * Draws the cloud.
     * @param g2 the graphics context
     * @param n
     */
    public void draw(Graphics2D g2, int n) {
        Random generator = new Random();
        for (int i = 0; i < n; i++) {
            double x = 500 * generator.nextDouble();
            double y = 500 * generator.nextDouble();
            point = new Point2D.Double(x, y);
            cloud.add(point);
            Ellipse2D.Double circle
                    = new Ellipse2D.Double(point.getX(), point.getY(), CIRCLE_SIZE, CIRCLE_SIZE);
            g2.setColor(Color.ORANGE);
            g2.draw(circle);
        }
    }
}
