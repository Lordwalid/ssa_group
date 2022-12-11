package Distribution;

public sealed interface IVector extends Comparable<IVector> permits Vector2D {

    double x();

    double y();

    IVector sub(IVector other);

    IVector changeMagnitude(double magnitude);

    IVector add(IVector other);

    IVector multiply(IVector other);

    IVector multiply(double value);

    IVector divide(IVector other);

    IVector divide(double value);

    double dot(IVector d);

    boolean isZero();

    boolean isNaN();

    IVector translate(IVector difference);

    double getMagnitude();

    IVector difference(IVector h);

    IVector zeroVector();

}

