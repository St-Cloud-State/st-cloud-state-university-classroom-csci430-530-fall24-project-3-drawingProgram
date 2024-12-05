import java.awt.*;

public class Triangle extends Item {
    private Point point1;
    private Point point2;
    private Point point3;

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public boolean includes(Point point) {
        // Implement logic to check if a point is inside the triangle
        return false; // Placeholder
    }

    public void render(UIContext uiContext) {
        uiContext.drawLine(point1, point2);
        uiContext.drawLine(point2, point3);
        uiContext.drawLine(point3, point1);
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint3() {
        return point3;
    }
} 