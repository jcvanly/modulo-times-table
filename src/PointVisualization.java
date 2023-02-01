/**
 * Jack Vanlyssel
 *
 * This file aids in the visualization of the points
 * that are going to appear on the circle.
 */

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class PointVisualization {
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
            double d = m * (double)point.getId();
            double correspondingPointId = d % numPoints;
            PointOnCircle pointTo = pointOnCircles[(int)correspondingPointId];
            PointOnCircle pointFrom = pointOnCircles[point.getId()];
            Line line = new Line(pointFrom.getX(), pointFrom.getY(), pointTo.getX(), pointTo.getY());
            line.setStroke(color);
            lines.getChildren().add(line);
        }
        return lines;
    }
}
