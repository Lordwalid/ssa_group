package Distribution;

public enum Hexagon {

    TOP_LEFT, TOP_RIGHT,
    MID_LEFT, MID_RIGHT,
    BOT_LEFT, BOT_RIGHT;

    public IVector getVec() {
        switch(this) {
            case TOP_LEFT:
                return new Vector2D(-2.5,5.0);
            case TOP_RIGHT:
                return new Vector2D(2.5,5.0);
            case MID_LEFT:
                return new Vector2D(0,-5);
            case MID_RIGHT:
                return new Vector2D(0,5);
            case BOT_LEFT:
                return new Vector2D(-5,-2.5);
            case BOT_RIGHT:
                return new Vector2D(-5,2.5);
        }
        return null;
    }
}
