/**
 * Jack Vanlyssel
 *
 * This file generates the points on the cirlce based on
 * an x value, a y value, and the number of points called
 * for by the user.
 */

public class PointOnCircle {
    private final int id;
    private final double x;
    private final double y;

    public PointOnCircle(int id, double x, double y) {this.id = id; this.x = x; this.y = y;}

    public int getId() {
        return this.id;
    }

    public double getX() {
        return this.x + 600;
    }

    public double getY() {
        return this.y + 256;
    }

    public static PointOnCircle[] generatePoints(double radius, double num) {
        PointOnCircle[] points = new PointOnCircle[(int)num];
        int i = 0;
        double pointSeparation = 360 / num;

        for(double angle = 180; angle < 540 && (double)i != num; angle += pointSeparation) {
            double x = Math.cos(Math.toRadians(angle)) * radius;
            double y = StrictMath.sin(Math.toRadians(angle)) * radius;
            points[i] = new PointOnCircle(i, x, y);
            ++i;
        }
        return points;
    }
}

