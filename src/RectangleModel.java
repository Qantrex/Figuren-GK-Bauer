import java.awt.Color;
import java.util.Objects;

public class RectangleModel {
    private final int x, y, width, height;
    private final Color color;

    public RectangleModel(int x, int y, int width, int height, Color color) {
        validateDimensions(width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = Objects.requireNonNull(color, "Color cannot be null");
    }

    private void validateDimensions(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive.");
        }
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d) %dx%d Color: #%06X", x, y, width, height, color.getRGB() & 0xFFFFFF);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RectangleModel that = (RectangleModel) obj;
        return x == that.x &&
               y == that.y &&
               width == that.width &&
               height == that.height &&
               Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height, color);
    }
}