import java.awt.*;

public class TriangleCommand extends Command {
    private Triangle triangle;
    private Point[] points = new Point[3];
    private int pointCount = 0;

    public void setPoint(Point point) {
        if (pointCount < 3) {
            points[pointCount++] = point;
            if (pointCount == 3) {
                triangle = new Triangle(points[0], points[1], points[2]);
                model.addItem(triangle);
            }
        }
    }

    public void execute() {
        // No additional execution needed as the triangle is created on the third click
    }

    public boolean undo() {
        if (triangle != null) {
            model.removeItem(triangle);
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (triangle != null) {
            model.addItem(triangle);
            return true;
        }
        return false;
    }
} 