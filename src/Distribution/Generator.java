package Distribution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Distribution.Hexagon.*;
import static java.lang.Math.abs;

public class Generator {

    public static void main(String[] args) {
        genPrintToString();
    }

    // generates 10 randomly distributed x and y coordinates within tuples within the bounds of
    // the first hexagon, second hexagon, etc, until 7th hexagon
    private static void genPrintToString(){
        List<IVector> A1 = getPatients(10);
        List<IVector> A2 = getPatients(10);
        List<IVector> B = getPatients(10);
        System.out.print("A1 size: " + A1.size() + ": ");
        for (IVector coordinate : A1) {
            System.out.print(coordinate.toString() + ", ");
        }
        System.out.print("\nA2 size: " + A2.size() + ": ");
        for (IVector coordinate : A2) {
            System.out.print(coordinate.toString() + ", ");
        }
        System.out.print("\nB size: " + B.size() + ": ");
        for (IVector coordinate : B) {
            System.out.print(coordinate.toString() + ", ");
        }
    }

    private static List<IVector> getPatients(int size) {
        List<IVector> coords = Grid.getVecs();
        List<IVector> queue = new ArrayList<>();
        for (Grid g : Grid.getPoints()) {
            // randomBound(max - min) + min
            // https://calcresource.com/geom-hexagon.html
            int j = size;
            for (int i = 0; i < j; i++) {
//                System.out.println(g.getxMax());
//                System.out.println(g.getxMin());
//                System.out.println(g.getyMax());
//                System.out.println(g.getyMin());
                double x = (new Random()).nextDouble(abs(g.getxMax() - g.getxMin())) + g.getxMin();
                double y = (new Random()).nextDouble(abs(g.getyMax() - g.getyMin())) + g.getyMin();
                // check bounds
                if (isOut(x,y,g)) {
                    queue.add(new Vector2D(x, y));
                    j--;
                    i--;
                }
            }
        }
        return queue;
    }
    private static boolean isOut(double x, double y, Grid grid) {
        return (!isIn(BOT_RIGHT.getVec(grid.getxOff(),grid.getyOff()), MID_RIGHT.getVec(grid.getxOff(),grid.getyOff()), new Vector2D(x,y)) ||
                !isIn(MID_RIGHT.getVec(grid.getxOff(),grid.getyOff()), TOP_RIGHT.getVec(grid.getxOff(),grid.getyOff()), new Vector2D(x,y)) ||
                !isIn(TOP_LEFT.getVec(grid.getxOff(),grid.getyOff()), MID_LEFT.getVec(grid.getxOff(),grid.getyOff()), new Vector2D(x,y)) ||
                !isIn(BOT_LEFT.getVec(grid.getxOff(),grid.getyOff()), MID_LEFT.getVec(grid.getxOff(),grid.getyOff()), new Vector2D(x,y)));
    }
    private static boolean isIn(IVector p1, IVector p2, IVector c){
        return ((p2.x() - p1.x())*(c.y() - p1.y()) - (p2.y() - p1.y())*(c.x() - p1.x())) > 0;
    }

}
