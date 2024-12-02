import javax.swing.*;
import java.awt.*;

public class RectangleView {
    private final JFrame frame;
    private final DrawingPanel canvasPanel;
    private final JTextField xField, yField, widthField, heightField;
    private final JComboBox<String> colorBox;
    private final JButton addButton, resetButton;
    private final JTextArea rectangleList;

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

        controlPanel.add(new JLabel("Farbe"));
        colorBox = new JComboBox<>(new String[]{"Rot", "Grün", "Blau", "Gelb"});
        controlPanel.add(colorBox);

        // Buttons
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

    private JTextField createLabeledTextField(JPanel panel, String label) {
        JPanel container = new JPanel(new BorderLayout());
        container.add(new JLabel(label), BorderLayout.WEST);
        JTextField textField = new JTextField(10);
        container.add(textField, BorderLayout.CENTER);
        panel.add(container);
        return textField;
    }

    // Getters
    public JFrame getFrame() { return frame; }
    public DrawingPanel getCanvasPanel() { return canvasPanel; }
    public JTextField getXField() { return xField; }
    public JTextField getYField() { return yField; }
    public JTextField getWidthField() { return widthField; }
    public JTextField getHeightField() { return heightField; }
    public JComboBox<String> getColorBox() { return colorBox; }
    public JButton getAddButton() { return addButton; }
    public JButton getResetButton() { return resetButton; }
    public JTextArea getRectangleList() { return rectangleList; }

    // Custom drawing panel
    static class DrawingPanel extends JPanel {
        private final java.util.List<RectangleModel> rectangles = new java.util.ArrayList<>();

        public void setRectangles(java.util.List<RectangleModel> rectangles) {
            this.rectangles.clear();
            this.rectangles.addAll(rectangles);
            repaint();
        }

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