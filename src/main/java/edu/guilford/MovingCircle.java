package edu.guilford;

import javafx.scene.shape.Circle;

public class MovingCircle  extends Circle {
    private double dx; // change in x (horizontal speed)
    private double dy; // change in y (vertical speed)

    public MovingCircle(double centerX, double centerY, double radius, 
        double dx, double dy) {
        super(centerX, centerY, radius); // call the constructor of Circle
        this.dx = dx;
        this.dy = dy;
    }

    public void move() {
        setCenterX(getCenterX() + dx);
        setCenterY(getCenterY() + dy);
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

}
