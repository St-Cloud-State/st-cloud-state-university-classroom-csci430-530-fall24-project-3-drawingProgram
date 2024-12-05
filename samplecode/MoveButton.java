import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MoveButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private UndoManager undoManager;
    private Point startPoint;

    public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Move");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
        drawingPanel.addMouseMotionListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            startPoint = event.getPoint();
        }

        public void mouseDragged(MouseEvent event) {
            // Optionally, you can provide visual feedback while dragging
        }

        public void mouseReleased(MouseEvent event) {
            Point endPoint = event.getPoint();
            Enumeration<Item> selectedItems = model.getSelectedItems();
            if (selectedItems.hasMoreElements()) {
                MoveCommand moveCommand = new MoveCommand(selectedItems, startPoint, endPoint);
                undoManager.beginCommand(moveCommand);
                moveCommand.execute();
                undoManager.endCommand(moveCommand);
            }
            drawingPanel.removeMouseListener(this);
            drawingPanel.removeMouseMotionListener(this);
            view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
} 