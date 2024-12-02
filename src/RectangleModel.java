import java.awt.Color;
import java.util.Objects;

/**
 * Represents a rectangular shape with position, dimensions, and color.
 * Provides methods for accessing rectangle properties and overrides
 * {@link Object#equals(Object)} and {@link Object#hashCode()} for proper comparison and hashing.
 */
public class RectangleModel {
    private final int x, y, width, height;
    private final Color color;

    /**
     * Constructs a {@code RectangleModel} with the specified position, dimensions, and color.
     * Validates that the width and height are positive, and the color is non-null.
     *
     * @param x      the x-coordinate of the top-left corner of the rectangle.
     * @param y      the y-coordinate of the top-left corner of the rectangle.
     * @param width  the width of the rectangle; must be positive.
     * @param height the height of the rectangle; must be positive.
     * @param color  the color of the rectangle; must not be {@code null}.
     * @throws IllegalArgumentException if {@code width} or {@code height} is not positive.
     * @throws NullPointerException     if {@code color} is {@code null}.
     */
    public RectangleModel(int x, int y, int width, int height, Color color) {
        validateDimensions(width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = Objects.requireNonNull(color, "Color cannot be null");
    }

    /**
     * Validates that the width and height are positive values.
     *
     * @param width  the width of the rectangle to validate.
     * @param height the height of the rectangle to validate.
     * @throws IllegalArgumentException if {@code width} or {@code height} is not positive.
     */
    private void validateDimensions(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive.");
        }
    }

    /**
     * Returns the x-coordinate of the top-left corner of the rectangle.
     *
     * @return the x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the top-left corner of the rectangle.
     *
     * @return the y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns a string representation of the rectangle, including its position, dimensions, and color.
     * The color is represented in hexadecimal format (e.g., {@code #RRGGBB}).
     *
     * @return a string representation of the rectangle.
     */
    @Override
    public String toString() {
        return String.format("(%d, %d) %dx%d Color: #%06X", x, y, width, height, color.getRGB() & 0xFFFFFF);
    }

    /**
     * Compares this rectangle to another object for equality.
     * Two rectangles are considered equal if they have the same position, dimensions, and color.
     *
     * @param obj the object to compare to.
     * @return {@code true} if the specified object is equal to this rectangle; {@code false} otherwise.
     */
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

    /**
     * Returns a hash code for this rectangle.
     * The hash code is computed based on the rectangle's position, dimensions, and color.
     *
     * @return a hash code for this rectangle.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height, color);
    }
}
