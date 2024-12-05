import java.awt.*;
import java.util.ArrayList;

public class Polygon extends Item {
    private ArrayList<Point> points;

    public Polygon() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public boolean includes(Point point) {
        // Implement logic to check if a point is inside the polygon
        return false; // Placeholder
    }

    public void render(UIContext uiContext) {
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % points.size());
            uiContext.drawLine(p1, p2);
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
} 