import javax.swing.*;
import java.awt.*;

/**
 * Container for all GUI components.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box boxContainer = new Box(BoxLayout.Y_AXIS);
        boxContainer.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        boxContainer.add(Box.createVerticalGlue());
        final DrawingCanvas<Boolean> drawingCanvas = new DrawingCanvas<>(
                40, 40,
                400, 400,
                true, false);
        boxContainer.add(drawingCanvas);
        boxContainer.add(Box.createVerticalGlue());

        add(boxContainer);
        pack();

        setMaximumSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(600, 600));
        setPreferredSize(new Dimension(600, 600));
        setLocation(200, 200);
        setVisible(true);
    }
}
