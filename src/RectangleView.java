import javax.swing.*;
import java.awt.*;

/**
 * The view component of the MVC architecture for the rectangle drawing application.
 * Provides the graphical user interface (GUI) for user interactions, including input fields,
 * buttons for actions, a canvas for drawing rectangles, and a list display of added rectangles.
 */
public class RectangleView {
    private final JFrame frame;
    private final DrawingPanel canvasPanel;
    private final JTextField xField, yField, widthField, heightField;
    private final JComboBox<String> colorBox;
    private final JButton addButton, resetButton;
    private final JTextArea rectangleList;

    /**
     * Constructs a new {@code RectangleView} and initializes the GUI components.
     * The GUI includes:
     * <ul>
     *   <li>A canvas panel for drawing rectangles.</li>
     *   <li>Input fields for specifying rectangle properties.</li>
     *   <li>Buttons for adding and resetting rectangles.</li>
     *   <li>A display area for the list of added rectangles.</li>
     * </ul>
     */
    public RectangleView() {
        frame = new JFrame("Rechtecke");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Canvas panel for drawing
        canvasPanel = new DrawingPanel();
        canvasPanel.setPreferredSize(new Dimension(600, 600));
        frame.add(canvasPanel, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(200, 600));

        // Input fields
        xField = createLabeledTextField(controlPanel, "x");
        yField = createLabeledTextField(controlPanel, "y");
        widthField = createLabeledTextField(controlPanel, "Breite");
        heightField = createLabeledTextField(controlPanel, "Höhe");

        // Color selection combo box
        controlPanel.add(new JLabel("Farbe"));
        colorBox = new JComboBox<>(new String[]{"Rot", "Grün", "Blau", "Gelb"});
        controlPanel.add(colorBox);

        // Buttons for adding and resetting
        addButton = new JButton("Hinzufügen");
        resetButton = new JButton("Zurücksetzen");
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(resetButton);
        controlPanel.add(buttonPanel);

        // Rectangle list display
        rectangleList = new JTextArea();
        rectangleList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(rectangleList);
        scrollPane.setPreferredSize(new Dimension(200, 300));
        controlPanel.add(scrollPane);

        frame.add(controlPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    /**
     * Creates a labeled text field, adds it to the specified panel, and returns the text field.
     *
     * @param panel the panel to which the labeled text field is added.
     * @param label the label text for the text field.
     * @return the created {@link JTextField}.
     */
    private JTextField createLabeledTextField(JPanel panel, String label) {
        JPanel container = new JPanel(new BorderLayout());
        container.add(new JLabel(label), BorderLayout.WEST);
        JTextField textField = new JTextField(10);
        container.add(textField, BorderLayout.CENTER);
        panel.add(container);
        return textField;
    }

    // Getters

    /**
     * Returns the main application frame.
     *
     * @return the main {@link JFrame}.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Returns the drawing panel used for rendering rectangles.
     *
     * @return the {@link DrawingPanel}.
     */
    public DrawingPanel getCanvasPanel() {
        return canvasPanel;
    }

    /**
     * Returns the text field for inputting the x-coordinate of a rectangle.
     *
     * @return the x-coordinate {@link JTextField}.
     */
    public JTextField getXField() {
        return xField;
    }

    /**
     * Returns the text field for inputting the y-coordinate of a rectangle.
     *
     * @return the y-coordinate {@link JTextField}.
     */
    public JTextField getYField() {
        return yField;
    }

    /**
     * Returns the text field for inputting the width of a rectangle.
     *
     * @return the width {@link JTextField}.
     */
    public JTextField getWidthField() {
        return widthField;
    }

    /**
     * Returns the text field for inputting the height of a rectangle.
     *
     * @return the height {@link JTextField}.
     */
    public JTextField getHeightField() {
        return heightField;
    }

    /**
     * Returns the combo box for selecting the color of a rectangle.
     *
     * @return the color {@link JComboBox}.
     */
    public JComboBox<String> getColorBox() {
        return colorBox;
    }

    /**
     * Returns the button for adding a rectangle.
     *
     * @return the "Add" {@link JButton}.
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * Returns the button for resetting all rectangles.
     *
     * @return the "Reset" {@link JButton}.
     */
    public JButton getResetButton() {
        return resetButton;
    }

    /**
     * Returns the text area displaying the list of rectangles.
     *
     * @return the rectangle list {@link JTextArea}.
     */
    public JTextArea getRectangleList() {
        return rectangleList;
    }

    /**
     * A custom panel for drawing rectangles. Handles painting of the rectangles stored in a list.
     */
    static class DrawingPanel extends JPanel {
        private final java.util.List<RectangleModel> rectangles = new java.util.ArrayList<>();

        /**
         * Updates the list of rectangles to be drawn and repaints the panel.
         *
         * @param rectangles the list of {@link RectangleModel} objects to draw.
         */
        public void setRectangles(java.util.List<RectangleModel> rectangles) {
            this.rectangles.clear();
            this.rectangles.addAll(rectangles);
            repaint();
        }

        /**
         * Paints the rectangles stored in the panel.
         *
         * @param g the {@link Graphics} object used for drawing.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.LIGHT_GRAY);
            for (RectangleModel rect : rectangles) {
                g.setColor(rect.getColor());
                g.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            }
        }
    }
}