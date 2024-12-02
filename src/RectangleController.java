import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class RectangleController {
    private final RectangleView view;
    private final List<RectangleModel> rectangles;

    public RectangleController(RectangleView view) {
        this.view = view;
        this.rectangles = new ArrayList<>();
        initializeListeners();
    }

    private void initializeListeners() {
        view.getAddButton().addActionListener(e -> addRectangle());
        view.getResetButton().addActionListener(e -> resetRectangles());
    }

    private void addRectangle() {
        try {
            // Parse inputs
            int x = parseInput(view.getXField().getText(), "X");
            int y = parseInput(view.getYField().getText(), "Y");
            int width = parseInput(view.getWidthField().getText(), "Width");
            int height = parseInput(view.getHeightField().getText(), "Height");

            // Get selected color
            Color color = getColorFromSelection((String) view.getColorBox().getSelectedItem());

            // Create and add rectangle
            RectangleModel rectangle = new RectangleModel(x, y, width, height, color);
            rectangles.add(rectangle);

            // Update the view
            updateView();
        } catch (IllegalArgumentException e) {
            showErrorDialog(e.getMessage());
        }
    }

    private void resetRectangles() {
        rectangles.clear();
        updateView();
    }

    private void updateView() {
        // Update rectangle list display
        StringBuilder listContent = new StringBuilder();
        for (int i = 0; i < rectangles.size(); i++) {
            listContent.append(i).append(": ").append(rectangles.get(i).toString()).append("\n");
        }
        view.getRectangleList().setText(listContent.toString());

        // Update drawing canvas
        view.getCanvasPanel().setRectangles(rectangles);
    }

    private int parseInput(String input, String fieldName) {
        try {
            int value = Integer.parseInt(input);
            if (value < 0) {
                throw new IllegalArgumentException(fieldName + " must be non-negative.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer.");
        }
    }

    private Color getColorFromSelection(String colorName) {
        return switch (colorName) {
            case "Rot" -> Color.RED;
            case "Grün" -> Color.GREEN;
            case "Blau" -> Color.BLUE;
            case "Gelb" -> Color.YELLOW;
            default -> Color.BLACK;
        };
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(view.getFrame(), message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            RectangleView view = new RectangleView();
            new RectangleController(view);
        });
    }
}