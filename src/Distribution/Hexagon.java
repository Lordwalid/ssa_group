package Distribution;

import java.util.ArrayList;
import java.util.List;

public enum Hexagon {

    TOP_LEFT, TOP_RIGHT, MID_LEFT, MID_RIGHT, BOT_LEFT, BOT_RIGHT;

    static List<IVector> points = new ArrayList<>();

    public IVector getVec(double xOffset, double yOffset) {
        switch(this) {
            case TOP_LEFT:
                return new Vector2D(-2.5+xOffset,5.0+yOffset);
            case TOP_RIGHT:
                return new Vector2D(2.5+xOffset,5.0+yOffset);
            case MID_LEFT:
                return new Vector2D(0+xOffset,-5+yOffset);
            case MID_RIGHT:
                return new Vector2D(0+xOffset,5+yOffset);
            case BOT_LEFT:
                return new Vector2D(-5+xOffset,-2.5+yOffset);
            case BOT_RIGHT:
                return new Vector2D(-5+xOffset,2.5+yOffset);
        }
        return null;
    }
    public static List<IVector> getAllVecs(double xOffset, double yOffset) {
        points.add(TOP_LEFT.getVec(xOffset, yOffset));
        points.add(TOP_RIGHT.getVec(xOffset, yOffset));
        points.add(MID_LEFT.getVec(xOffset, yOffset));
        points.add(MID_RIGHT.getVec(xOffset, yOffset));
        points.add(BOT_LEFT.getVec(xOffset, yOffset));
        points.add(BOT_RIGHT.getVec(xOffset, yOffset));
        return points;
    }

}
