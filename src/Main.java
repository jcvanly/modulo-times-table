/**
 * Jack Vanlyssel
 *
 * This code establishes the scene, defines the buttons,
 * and creates the circle that the points will be layered over.
 */

import java.text.DecimalFormat;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Times Table Visualizer");
        double initTimeTableNum = 2; //this is the initial value of the times table
        Circle circle = new Circle(600, 256, 235); //sets the circle and fills
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        //this code sets the layout and functionality of the buttons
        double buttonSpacing = 10; //the spacing of the buttons
        VBox controls = new VBox(buttonSpacing);
        controls.setLayoutX(10);//where the buttons are centered
        controls.setLayoutY(60);
        HBox timesTableBox = new HBox(buttonSpacing);

        //defining labels and buttons
        Button start = new Button("Start");
        Button pause = new Button("Pause");
        Button jumpToButton = new Button("Reset");
        Label timesTableLabel = new Label("Times Table Number:");
        Label stepNumLabel = new Label("Increment:");
        Label delayLabel = new Label("Delay (seconds):");
        Label timesTableNumLabel = new Label("Times Table Number:");
        Label numPointsLabel = new Label("Number Of Points:");
        final Label timesTableValueLabel = new Label(Double.toString(initTimeTableNum));

        //adding functionality to the buttons
        final DecimalFormat timesTableDecimalFormat = new DecimalFormat("#.0");
        timesTableBox.getChildren().addAll(new Node[]{timesTableLabel, timesTableValueLabel});
        HBox stepNumBox = new HBox(buttonSpacing);
        HBox timesTableNumBox = new HBox(buttonSpacing);
        TextField timesTableNumTF = new TextField("2");
        timesTableNumBox.getChildren().addAll(new Node[]{timesTableNumLabel, timesTableNumTF});
        HBox numPointsBox = new HBox(buttonSpacing);
        final TextField numPointsTF = new TextField("360");
        numPointsBox.getChildren().addAll(new Node[]{numPointsLabel, numPointsTF});
        final Slider stepNumSlider = new Slider(0, 5, 1);
        stepNumSlider.setShowTickMarks(true);
        stepNumSlider.setShowTickLabels(true);
        stepNumSlider.setMajorTickUnit(0.25);
        stepNumSlider.setBlockIncrement(0.1);
        stepNumBox.getChildren().addAll(new Node[]{stepNumLabel, stepNumSlider});
        HBox delayBox = new HBox(buttonSpacing);
        final Slider delaySlider = new Slider(0, 5, 0.5);
        delaySlider.setShowTickMarks(true);
        delaySlider.setShowTickLabels(true);
        delaySlider.setMajorTickUnit(0.25);
        delaySlider.setBlockIncrement(0.1);
        delayBox.getChildren().addAll(new Node[]{delayLabel, delaySlider});
        controls.getChildren().addAll(new Node[]{start, pause, timesTableBox, stepNumBox, delayBox, timesTableNumBox, numPointsBox, jumpToButton});
        Canvas canvas = new Canvas(900, 512);
        final Pane root = new Pane(new Node[]{canvas, circle, controls});
        Scene scene = new Scene(root, 900, 512);
        primaryStage.setScene(scene);
        primaryStage.show();
        final PointVisualization visualization = new PointVisualization(initTimeTableNum);
        root.setStyle("-fx-background-color: rgba(245,244,246,0.77);");

        class MyAnimationTimer extends AnimationTimer {
            private long lastUpdate = 0L;
            public void run(boolean jumpTo) {
                root.getChildren().removeIf((node) -> {
                    return node instanceof Group;
                });
                Group lines = visualization.generateLines(Double.parseDouble(numPointsTF.getText()));
                root.getChildren().add(lines);
                timesTableValueLabel.setText(timesTableDecimalFormat.format(visualization.getTimesTableNumber()));
                if (!jumpTo) {
                    visualization.incrementTimesTableNumber(stepNumSlider.getValue());
                }

            }

            public void handle(long now) {
                if ((double)(now - this.lastUpdate) >= delaySlider.getValue() * 1.0E9 && !numPointsTF.getText().equals("")) {
                    this.run(false);
                    this.lastUpdate = now;
                }

            }
        }

        MyAnimationTimer timer = new MyAnimationTimer();

        start.setOnAction((event) -> {
            timer.start();
        });

        pause.setOnAction((event) -> {
            timer.stop();
        });

        jumpToButton.setOnAction((event) -> {
            timer.stop();
            visualization.setTimesTableNumber(Double.parseDouble(timesTableNumTF.getText()));
            timer.run(true);
        });
    }
}

