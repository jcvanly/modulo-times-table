/**
 * Jack Vanlyssel
 *
 * This file generates the points on the circle based on
 * an x value, a y value, and the number of points called
 * for by the user. It then creates those points on the cirlce.
 */

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class PointOnCircle {
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

class PointVisualization {
    private double timesTableNumber;
    private final double radius;

    public PointVisualization(double timesTableNum) {
        this.timesTableNumber = timesTableNum;
        this.radius = 234;
    }

    public void incrementTimesTableNumber(double stepNum) {
        this.timesTableNumber += stepNum;
    }

    public double getTimesTableNumber() {
        return this.timesTableNumber;
    }

    public void setTimesTableNumber(double timesTableNumber) {
        this.timesTableNumber = timesTableNumber;
    }

    public Group generateLines(double numPoints) {
        Color color = new Color(Math.random(), Math.random(), Math.random(), 1);
        Group lines = new Group();
        PointOnCircle[] pointOnCircles = PointOnCircle.generatePoints(this.radius, numPoints);
        PointOnCircle[] pointLists = pointOnCircles;
        int pointLength = pointOnCircles.length;

        for(int i = 0; i < pointLength; ++i) {
            PointOnCircle point = pointLists[i];
            double m = this.getTimesTableNumber();
            double d = m * (double)point.getPointID();
            double correspondingPointId = d % numPoints;
            PointOnCircle pointTo = pointOnCircles[(int)correspondingPointId];
            PointOnCircle pointFrom = pointOnCircles[point.getPointID()];
            Line line = new Line(pointFrom.getPointX(), pointFrom.getPointY(), pointTo.getPointX(), pointTo.getPointY());
            line.setStroke(color);
            lines.getChildren().add(line);
        }
        return lines;
    }
}


