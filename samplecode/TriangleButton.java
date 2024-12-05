import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TriangleButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private TriangleCommand triangleCommand;
    private UndoManager undoManager;

    public TriangleButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Triangle");
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
            if (triangleCommand == null) {
                triangleCommand = new TriangleCommand();
                undoManager.beginCommand(triangleCommand);
            }
            triangleCommand.setPoint(View.mapPoint(event.getPoint()));
            if (triangleCommand.getPointCount() == 3) {
                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(triangleCommand);
                triangleCommand = null; // Reset for next triangle
            }
        }
    }
} 