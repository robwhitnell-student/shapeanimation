package edu.guilford;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.shape.Path;
import java.util.Random;

public class ShapePane extends Pane {
    private ShapeList shapeList;
    private Random rand = new Random();
    private ControlController controlController = null;
    private int width;
    private int height;

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
            this.getChildren().add(shape); // add each shape to the pane

            // start the fade transition for each shape
            FadeTransition ft = new FadeTransition(Duration.millis(rand.nextInt(2000) + 1000), shape);
            ft.setFromValue(shape.getOpacity());
            ft.setToValue(0.1);
            ft.setCycleCount(FadeTransition.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();

            Path path = new Path();
            path.getElements().add(new MoveTo(rand.nextInt(width), rand.nextInt(height)));
            path.getElements().add(new LineTo(rand.nextInt(width), rand.nextInt(height)));

            path.getElements().add(new CubicCurveTo(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width), rand.nextInt(height), 
            width / 2, height / 2));
            path.getElements().add(new ClosePath());
            PathTransition pt = new PathTransition(Duration.millis(3000), path, shape);
            pt.setCycleCount(PathTransition.INDEFINITE);
            pt.setAutoReverse(true);
            pt.play();

            FillTransition fillt = new FillTransition(Duration.millis(5000),
                    shape, (Color) shape.getFill(),
                    shapeList.randomColor());
            fillt.setAutoReverse(true);
            fillt.setCycleCount(FillTransition.INDEFINITE);
            fillt.play();
        }
    }

}
