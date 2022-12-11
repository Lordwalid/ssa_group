package Distribution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Grid {
    O(0,0,5,-5,4.33013,-4.33013),
    N(0,4.33013*2, 5, -5, 4.33013*3, 4.33013),
    NE(7.5,4.33013,12.5,2.5,4.33013*2,0),
    SE(7.5,-4.33013,12.5,2.5,0,-4.33013*2),
    S(0,4.33013*-2, 5,-5,-4.33013,-4.33013*3),
    SW(-7.5,-4.33013,-2.5,-12.5,0,-4.33013*2),
    NW(-7.5,4.33013,-2.5,-12.5,4.33013*2,0);
    private double xOff, yOff, xMax, xMin, yMax,yMin;
    Grid(double xOff, double yOff, double xMax, double xMin, double yMax, double yMin) {
        this.xOff = xOff;
        this.yOff = yOff;
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
    }
    public String toString() {
        switch (this) {
            case O: return "O";
            case N: return "N";
            case NE: return "NE";
            case SE: return "SE";
            case S: return "S";
            case SW: return "SW";
            case NW: return "NW";
        }
        return null;
    }

    public static List<Grid> getPoints() {
        return Arrays.asList(O,N,NE,SE,S,SW,NW);
    }

    public static List<IVector> getVecs() {
        List<IVector> grid = new ArrayList<>();
        for (Grid g : getPoints()) {
            for (int i = 0; i < g.getPoints().size(); i++) {
                grid.add(Hexagon.getAllVecs(g.getxOff(),g.getyOff()).get(i));
            }
        }
        return grid;
    }

    public double getxOff() {
        return xOff;
    }

    public double getyOff() {
        return yOff;
    }

    public double getxMax() {
        return xMax;
    }

    public double getxMin() {
        return xMin;
    }

    public double getyMax() {
        return yMax;
    }

    public double getyMin() {
        return yMin;
    }
}
