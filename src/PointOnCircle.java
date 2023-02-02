/**
 * Jack Vanlyssel
 *
 * This file generates the points on the cirlce based on
 * an x value, a y value, and the number of points called
 * for by the user.
 */

public class PointOnCircle {
    private final int pointID;
    private final double pointX;
    private final double pointY;

    public PointOnCircle(int id, double x, double y) {this.pointID = id; this.pointX = x; this.pointY = y;}

    public int getPointID() {return this.pointID;}

    public double getPointX() {
        return this.pointX + 600;
    }

    public double getPointY() {
        return this.pointY + 256;
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

