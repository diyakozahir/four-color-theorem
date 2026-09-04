import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;

import static java.lang.Integer.min;

/**
 * Canvas for drawing maps.
 * @param <E> the type of element to be stored to represent the state of the map.
 */
public class DrawingCanvas<E> extends JPanel {
    private List<List<E>> drawingGrid;

    private final E paintedValue;
    private final E notPaintedValue;

    private boolean mouseClicked;

    private final int canvasWidth;
    private final int canvasHeight;

    private final int cellSize;

    private int intX = 0;
    private int intY = 0;

    /**
     * Constructor for DrawingCanvas. Creates map within the dimensions canvasWidth by canvasHeight in a grid of the
     * form gridRows by gridColumns.
     * @param gridRows the number of pixel rows in the map
     * @param gridColumns the number of pixel columns in the map
     * @param canvasWidth the maximum width of the canvas
     * @param canvasHeight the maximum height of the canvas
     * @param paintedValue the value to store at a point in the grid when painted
     * @param notPaintedValue the value to store at a point in the grid when not painted
     */
    public DrawingCanvas(int gridRows, int gridColumns,
                         int canvasWidth, int canvasHeight,
                         E paintedValue, E notPaintedValue) {
        super();
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        cellSize = min(canvasHeight / gridRows, canvasWidth / gridColumns);
        this.canvasHeight = cellSize * gridRows;
        this.canvasWidth = cellSize * gridColumns;

        this.paintedValue = paintedValue;
        this.notPaintedValue = notPaintedValue;
        drawingGrid = new ArrayList<>(gridRows);
        for (int i = 0; i < gridRows; i++) {
            List<E> row = new ArrayList<>(gridColumns);
            for (int j = 0; j < gridColumns; j++) {
                row.add(notPaintedValue);
            }
            drawingGrid.add(row);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClicked = true;
                super.mouseClicked(e);
                drawRect(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseClicked = true;
                super.mouseDragged(e);
                if (0 <= e.getX() && e.getX() < canvasWidth &&
                        0 <= e.getY() && e.getY() < canvasHeight) {
                    drawRect(e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * Paint the pixel at which the coordinate (x, y) is located within.
     * @param x the horizontal coordinate to paint at
     * @param y the vertical coordinate to paint at
     */
    private void drawRect(int x, int y) {
        int column = x / cellSize;
        int row = y / cellSize;
        drawingGrid.get(row).set(column, paintedValue);
        intX = column * cellSize;
        intY = row * cellSize;
        repaint(intX, intY, cellSize, cellSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mouseClicked) {
            g.setColor(Color.BLACK);
            g.fillRect(intX, intY, cellSize, cellSize);
            mouseClicked = false;
        } else {
            for (int row = 0; row < drawingGrid.size(); row++) {
                for (int column = 0; column < drawingGrid.get(row).size(); column++) {
                    if (drawingGrid.get(row).get(column).equals(paintedValue)) {
                        g.setColor(Color.BLACK);
                        g.fillRect(column * cellSize, row * cellSize, cellSize, cellSize);
                    }
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvasWidth, canvasHeight);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(canvasWidth, canvasHeight);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(canvasWidth, canvasHeight);
    }
}