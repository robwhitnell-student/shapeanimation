package edu.guilford;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class ControlController {
    @FXML
    private Slider shapeSlider;

    private ShapeList shapeList;
    private ShapePane shapePane;

    @FXML
    public void processButton() {
        System.out.println("Button pressed");
        shapeList.clear();
        shapePane.getChildren().clear(); // clear existing shapes
    }

    public ShapeList getShapeList() {
        return shapeList;
    }

    public void setShapeList(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    public ShapePane getShapePane() {
        return shapePane;
    }

    public void setShapePane(ShapePane shapePane) {
        this.shapePane = shapePane;
    }

    @FXML
    public void readSlider() {
        // Get the value from the slider and print it
        double sliderValue = shapeSlider.getValue();
        System.out.println("Slider value: " + sliderValue);

        // Generate shapes based on the slider value
        if (shapeList != null && shapePane != null) {
            int numShapes = (int) sliderValue; // convert to int for number of shapes
            shapeList.generateShapes(numShapes); // generate new shapes
            shapePane.updateShapes(); // update the ShapePane with the new shapes
        }
    }
}
