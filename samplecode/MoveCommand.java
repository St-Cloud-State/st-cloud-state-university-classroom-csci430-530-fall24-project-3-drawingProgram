import java.awt.*;
import java.util.Enumeration;

public class MoveCommand extends Command {
    private int deltaX;
    private int deltaY;
    private Enumeration<Item> items;

    public MoveCommand(Enumeration<Item> items, Point startPoint, Point endPoint) {
        this.items = items;
        this.deltaX = endPoint.x - startPoint.x;
        this.deltaY = endPoint.y - startPoint.y;
    }

    public void execute() {
        while (items.hasMoreElements()) {
            Item item = items.nextElement();
            item.translate(deltaX, deltaY);
        }
    }

    public boolean undo() {
        // Reverse the translation
        items = model.getSelectedItems(); // Get the selected items again
        while (items.hasMoreElements()) {
            Item item = items.nextElement();
            item.translate(-deltaX, -deltaY);
        }
        return true;
    }

    public boolean redo() {
        // Repeat the translation
        items = model.getSelectedItems(); // Get the selected items again
        while (items.hasMoreElements()) {
            Item item = items.nextElement();
            item.translate(deltaX, deltaY);
        }
        return true;
    }
} 