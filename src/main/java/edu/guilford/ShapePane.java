package edu.guilford;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.Random;

public class ShapePane extends Pane {
    private ShapeList shapeList;
    private Random rand = new Random();
    private ControlController controlController = null;
    private int width;
    private int height;
    private ArrayList<MovingCircle> circles = new ArrayList<MovingCircle>();

    public ShapePane(int width, int height) {
        super(); // call the constructor of the parent class (Pane)
        this.width = width;
        this.height = height;
        // initialize the ShapeList with the specified width and height
        this.shapeList = new ShapeList(width, height);
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color: lightgray;"); // optional background color

        this.setOnMouseClicked(e -> {
            controlController.readSlider();

        });

    }

    public void setControlController(ControlController controlController) {
        this.controlController = controlController;
    }

    public ShapeList getShapeList() {
        return shapeList;
    }

    public void setShapeList(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    public void updateShapes() {
        this.getChildren().clear(); // clear existing shapes
        for (Shape shape : shapeList) {
            if (shape instanceof MovingCircle) {
                MovingCircle circle = (MovingCircle) shape;
                circles.add(circle);
                this.getChildren().add(shape); // add each shape to the pane\

                Timeline moveCircles = new Timeline();
                // key frame to generate an event every specified moment
                KeyFrame kfMove = new KeyFrame(Duration.millis(10), 
                new CircAnimate());
                moveCircles.getKeyFrames().add(kfMove);
                moveCircles.setCycleCount(Timeline.INDEFINITE);
                moveCircles.play();

            //     // A Timeline object is a list of KeyFrame objects that specify
            //     // what the object should look like and where it should be at specified times
            //     Timeline timeline = new Timeline();
            //     timeline.setCycleCount(Timeline.INDEFINITE);
            //     timeline.setAutoReverse(true);

            //     // In the first 500 ms, we'll change the position
            //     KeyValue kvx = new KeyValue(circle.centerXProperty(), rand.nextInt(width));
            //     KeyValue kvy = new KeyValue(circle.centerYProperty(), rand.nextInt(height));
            //     KeyFrame kfxy = new KeyFrame(Duration.millis(500), kvx, kvy);

            //     // Then over the next 1000 ms, we'll change the radius
            //     KeyValue kvr = new KeyValue(circle.radiusProperty(), 10 + rand.nextInt(250));
            //     KeyFrame kfr = new KeyFrame(Duration.millis(1000), kvr);

            //     timeline.getKeyFrames().addAll(kfxy, kfr);
            //     timeline.play();
            }



            // // start the fade transition for each shape
            // // In JavaFX, we can set transitions that set a beginning and end
            // // and alter the object smoothly between them 
            // // FadeTransition specifically changes opacity
            // FadeTransition ft = new FadeTransition(Duration.millis(rand.nextInt(2000) + 1000), shape);
            // // Over the specified duration, change the opacity from its original value to 0.1
            // ft.setFromValue(shape.getOpacity());
            // ft.setToValue(0.1);
            // // We can do this for a specified number of cycles or forever, as happens here
            // ft.setCycleCount(FadeTransition.INDEFINITE);
            // // And loop, reversing each time
            // ft.setAutoReverse(true);
            // ft.play();

            // // Path is a JavaFX class that specifies a linear or curvilinear path
            // // through the coordinates
            // // A Path is essentially a series of points
            // Path path = new Path();
            // // We start the path by moving the object to a particular point
            // path.getElements().add(new MoveTo(rand.nextInt(width), rand.nextInt(height)));
            // // And then proceeding in a straight line to the next point
            // path.getElements().add(new LineTo(rand.nextInt(width), rand.nextInt(height)));

            // // Add a curved path
            // path.getElements().add(new CubicCurveTo(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width), rand.nextInt(height), 
            // width / 2, height / 2));
            // path.getElements().add(new ClosePath());
            // PathTransition pt = new PathTransition(Duration.millis(3000), path, shape);
            // pt.setCycleCount(PathTransition.INDEFINITE);
            // pt.setAutoReverse(true);
            // pt.play();

            // // FillTransition is a smooth transition from one fill color to another
            // // To build a transition, instantiate a transition object with the object to be
            // // animated as an attribute
            // FillTransition fillt = new FillTransition(Duration.millis(5000),
            //         shape, (Color) shape.getFill(),
            //         shapeList.randomColor());
            // // set the properties of the transition
            // fillt.setAutoReverse(true);
            // fillt.setCycleCount(FillTransition.INDEFINITE);
            // // tell the transition to start
            // fillt.play();
        }
    }
        private class CircAnimate implements EventHandler<ActionEvent> {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                for (MovingCircle mCircle : circles) {
                    double cx = mCircle.getCenterX();
                    double cy = mCircle.getCenterY();
                    double radius = mCircle.getRadius();

                    if (cx < 0 || cx > width) {
                        mCircle.setDx(-mCircle.getDx());
                    }

                    if (cy < 0 || cy > height) {
                        mCircle.setDy(-mCircle.getDy());
                    }

                    mCircle.setCenterX(cx + mCircle.getDx());
                    mCircle.setCenterY(cy + mCircle.getDy());

                }
            }
            
        }
    

}
