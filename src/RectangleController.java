import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing the interaction between the {@link RectangleView} and the rectangle data model.
 * This class handles user inputs, updates the view, and maintains a list of rectangles.
 */
public class RectangleController {
    private final RectangleView view;
    private final List<RectangleModel> rectangles;

    /**
     * Constructs a new {@code RectangleController} with the specified view.
     * Initializes event listeners for user interactions.
     *
     * @param view the {@link RectangleView} instance to be controlled.
     */
    public RectangleController(RectangleView view) {
        this.view = view;
        this.rectangles = new ArrayList<>();
        initializeListeners();
    }

    /**
     * Initializes listeners for the buttons in the view.
     * Sets up actions for adding and resetting rectangles.
     */
    private void initializeListeners() {
        view.getAddButton().addActionListener(e -> addRectangle());
        view.getResetButton().addActionListener(e -> resetRectangles());
    }

    /**
     * Handles the logic for adding a new rectangle based on user input.
     * Validates input fields, creates a new {@link RectangleModel}, and updates the view.
     * Displays an error dialog if input validation fails.
     */
    private void addRectangle() {
        try {
            int x = parseInput(view.getXField().getText(), "X");
            int y = parseInput(view.getYField().getText(), "Y");
            int width = parseInput(view.getWidthField().getText(), "Width");
            int height = parseInput(view.getHeightField().getText(), "Height");

            Color color = getColorFromSelection((String) view.getColorBox().getSelectedItem());

            RectangleModel rectangle = new RectangleModel(x, y, width, height, color);
            rectangles.add(rectangle);

            updateView();
        } catch (IllegalArgumentException e) {
            showErrorDialog(e.getMessage());
        }
    }

    /**
     * Clears the list of rectangles and updates the view.
     */
    private void resetRectangles() {
        rectangles.clear();
        updateView();
    }

    /**
     * Updates the rectangle list display and the canvas in the view.
     */
    private void updateView() {
        StringBuilder listContent = new StringBuilder();
        for (int i = 0; i < rectangles.size(); i++) {
            listContent.append(i).append(": ").append(rectangles.get(i).toString()).append("\n");
        }
        view.getRectangleList().setText(listContent.toString());

        view.getCanvasPanel().setRectangles(rectangles);
    }

    /**
     * Parses an input string into an integer. Throws an exception if the input is invalid.
     *
     * @param input     the input string to parse.
     * @param fieldName the name of the field being parsed, used in error messages.
     * @return the parsed integer value.
     * @throws IllegalArgumentException if the input is not a valid non-negative integer.
     */
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

    /**
     * Converts a color name selected in the view into a {@link Color} object.
     *
     * @param colorName the name of the selected color.
     * @return the corresponding {@link Color} object.
     */
    private Color getColorFromSelection(String colorName) {
        return switch (colorName) {
            case "Rot" -> Color.RED;
            case "Grün" -> Color.GREEN;
            case "Blau" -> Color.BLUE;
            case "Gelb" -> Color.YELLOW;
            default -> Color.BLACK;
        };
    }

    /**
     * Displays an error dialog with a specified message.
     *
     * @param message the error message to display.
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(view.getFrame(), message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * The entry point of the application. Initializes the view and controller.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            RectangleView view = new RectangleView();
            new RectangleController(view);
        });
    }
}