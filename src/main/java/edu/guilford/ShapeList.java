package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ShapeList extends ArrayList<Shape> {
    private Random random = new Random();
    private int width;
    private int height;
    
    public ShapeList(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    // method to generate a random color
    public Color randomColor() {
        // generate random values for red, green, blue, and alpha (opacity)
        // each value is between 0.0 and 1.0
        return new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 
        random.nextDouble() * 0.7 + 0.3); // alpha value between 0.7 and 1.0
    }

    public void generateShapes(int numShapes) {
        this.clear(); // clear the list before generating new shapes
        // loop to create the specified number of shapes of different types

        for (int i = 0; i < numShapes; i++) {
            // randomly choose a shape type
            int shapeType = random.nextInt(3); // 0, 1, or 2 for circle, rectangle, or triangle
            Shape shape = null;
            Color color = randomColor();
            
            // select what shape to create based on the random shapeType
            switch (shapeType) {
                case 0: // circle
                    double radius = random.nextDouble() * Math.min(width, height) / 10; // random radius
                    double centerX = random.nextDouble() * (width - radius * 2) + radius; // random center x
                    double centerY = random.nextDouble() * (height - radius * 2) + radius; // random center y
                    shape = new MovingCircle(centerX, centerY, radius,
                    random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1); // random dx and dy
                    break;
                case 1: // rectangle
                    double rectWidth = random.nextDouble() * (width / 5); // random width
                    double rectHeight = random.nextDouble() * (height / 5); // random height
                    double rectX = random.nextDouble() * (width - rectWidth); // random x
                    double rectY = random.nextDouble() * (height - rectHeight); // random y
                    shape = new javafx.scene.shape.Rectangle(rectX, rectY, rectWidth, rectHeight);
                    break;
                case 2: // triangle
                    double x1 = random.nextDouble() * width;
                    double y1 = random.nextDouble() * height;
                    double x2 = random.nextDouble() * width;
                    double y2 = random.nextDouble() * height;
                    double x3 = random.nextDouble() * width;
                    double y3 = random.nextDouble() * height;
                    shape = new javafx.scene.shape.Polygon(x1, y1, x2, y2, x3, y3);
                    break;
            }
            
            if (shape != null) {
                shape.setFill(color);
                shape.setStroke(Color.BLACK); // set stroke color to black
                shape.setStrokeWidth(2); // set stroke width
                this.add(shape); // add the shape to the list
            }
        }
    }
}
