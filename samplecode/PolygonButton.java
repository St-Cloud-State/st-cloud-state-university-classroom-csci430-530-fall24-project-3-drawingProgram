import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PolygonButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private PolygonCommand polygonCommand;
    private UndoManager undoManager;

    public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (polygonCommand == null) {
                polygonCommand = new PolygonCommand();
                undoManager.beginCommand(polygonCommand);
            }

            if (SwingUtilities.isLeftMouseButton(event)) {
                polygonCommand.addPoint(View.mapPoint(event.getPoint()));
                if (polygonCommand.getPoints().size() > 1) {
                    // Draw line between last two points (optional visual feedback)
                    drawingPanel.repaint();
                }
            } else if (SwingUtilities.isRightMouseButton(event)) {
                // Finalize the polygon on right-click
                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(polygonCommand);
                polygonCommand = null; // Reset for next polygon
            }
        }
    }
} 