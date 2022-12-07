package Distribution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static Distribution.Hexagon.*;

public class Generator {

    public static void main(String[] args) {
        genPrintToString();
    }

    // distribute randomly within one hexagon
    private static void genPrintToString(){
        System.out.println("isIn should be false: " + isIn(BOT_RIGHT.getVec(), MID_RIGHT.getVec(), new Vector2D(4.9,-4.2)));
        System.out.println("isIn should be false: " + isIn(MID_RIGHT.getVec(), TOP_RIGHT.getVec(), new Vector2D(4.9,4.2)));
        System.out.println("isIn should be false: " + isIn(TOP_LEFT.getVec(), MID_LEFT.getVec(), new Vector2D(-4.9,4.2)));
        System.out.println("isIn should be false: " + isIn(BOT_LEFT.getVec(), MID_LEFT.getVec(), new Vector2D(-4.9,-4.2)));
        ArrayList<Vector2D> A1 = getPatients(10,5.0,-5.0,4.33013,-4.33013);
        ArrayList<Vector2D> A2 = getPatients(10,5.0,-5.0,4.33013,-4.33013);
        ArrayList<Vector2D> B = getPatients(10,5.0,-5.0,4.33013,-4.33013);
        System.out.print("A1: ");
        for (int i = 0; i < A1.size(); i++) {
            System.out.print(A1.get(i).toString() + ", ");
        }
        System.out.print("\nA2: ");
        for (int i = 0; i < A2.size(); i++) {
            System.out.print(A2.get(i).toString() + ", ");
        }
        System.out.print("\nB: ");
        for (int i = 0; i < B.size(); i++) {
            System.out.print(B.get(i).toString() + ", ");
        }
    }

    private static ArrayList<Vector2D> getPatients(int size, double xmax, double xmin, double ymax, double ymin) {
        ArrayList<Vector2D> coords = new ArrayList<>();
        // randomBound(max - min) + min
        // https://calcresource.com/geom-hexagon.html
        int j = size;
        for (int i = 0; i < j; i++) {
            double x = (new Random()).nextDouble(xmax - xmin) + xmin;
            double y = (new Random()).nextDouble(ymax - ymin) + ymin;
            // check bounds
            if (isOut(x,y)) {
                coords.add(new Vector2D(x, y));
                j--;
                i--;
            }
        }
        return coords;
    }
    private static boolean isOut(double x, double y) {
        return (!isIn(BOT_RIGHT.getVec(), MID_RIGHT.getVec(), new Vector2D(x,y)) ||
                !isIn(MID_RIGHT.getVec(), TOP_RIGHT.getVec(), new Vector2D(x,y)) ||
                !isIn(TOP_LEFT.getVec(), MID_LEFT.getVec(), new Vector2D(x,y)) ||
                !isIn(BOT_LEFT.getVec(), MID_LEFT.getVec(), new Vector2D(x,y)));
    }
    private static boolean isIn(IVector p1, IVector p2, IVector c){
        return ((p2.x() - p1.x())*(c.y() - p1.y()) - (p2.y() - p1.y())*(c.x() - p1.x())) > 0;
    }

}
