import java.awt.*;
import java.util.ArrayList;

public class PolygonCommand extends Command {
    private Polygon polygon;
    private ArrayList<Point> points = new ArrayList<>();

    public PolygonCommand() {
        polygon = new Polygon();
    }

    public void addPoint(Point point) {
        points.add(point);
        polygon.addPoint(point);
    }

    public void execute() {
        // No additional execution needed as the polygon is created on right-click
    }

    public boolean undo() {
        if (polygon != null) {
            model.removeItem(polygon);
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (polygon != null) {
            model.addItem(polygon);
            return true;
        }
        return false;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
} 