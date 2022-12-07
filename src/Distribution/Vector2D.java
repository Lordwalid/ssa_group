package Distribution;

import static java.lang.Double.compare;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public record Vector2D(@Override double x, @Override double y) implements IVector {

    @Override
    public IVector sub(IVector other) {
        return new Vector2D(x() - other.x(), y() - other.y());
    }

    @Override
    public IVector changeMagnitude(double newMag) {
        if (getMagnitude() == 0) throw new UnsupportedOperationException();
        double mag = getMagnitude();
        return new Vector2D(x() * newMag / mag, y() * newMag / mag);
    }

    @Override
    public IVector add(IVector other) {
        return new Vector2D(x() + other.x(), y() + other.y());
    }

    @Override
    public IVector multiply(IVector other) {
        return new Vector2D(x() * other.x(), y() * other.y());
    }

    @Override
    public IVector multiply(double value) {
        return new Vector2D(x() * value, y() * value);
    }

    @Override
    public IVector divide(IVector other) {
        return new Vector2D(x() / other.x(), y() / other.y());
    }

    @Override
    public IVector divide(double value) {
        return new Vector2D(x() / value, y() / value);
    }

    @Override
    public double dot(IVector d) {
        if (d instanceof Vector2D v) {
            return (this.x() * v.x()) + (this.y() * v.y());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public IVector translate(IVector difference) {
        return new Vector2D(x() + difference.x(), y() + difference.y());
    }

    @Override
    public String toString() {
        return "{ %f, %f }".formatted(x(), y());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D v = (Vector2D) o;
        return abs(y - v.y) < 1e-6 && abs(x - v.x) < 1e-6;
    }

    @Override
    public double getMagnitude() {
        return sqrt((x() * x()) + (y() * y()));
    }

    @Override
    public IVector difference(IVector h) {
        return new Vector2D(abs(x() - h.x()), abs(y() - h.y()));
    }

    @Override
    public int compareTo(IVector o) {
        if (o instanceof Vector2D v) {
            int x = compare(x(), v.x());
            int y = compare(y(), v.y());
            return x == 0 ? y : x;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public IVector zeroVector() {
        return new Vector2D(0, 0);
    }

    @Override
    public boolean isZero() {
        return x == 0 && y == 0;
    }

    @Override
    public boolean isNaN() {
        return x != x || y != y;
    }
}