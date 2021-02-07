package ru.academitschool.mochalov.shapes;

import ru.academitschool.mochalov.shape.Shape;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Фигура " + getClass().getSimpleName() + " со сторонами " + width + " и " + height;
    }

    @Override
    public boolean equals(Object rectangle) {
        if (rectangle == this) {
            return true;
        }

        if (rectangle == null || rectangle.getClass() != getClass()) {
            return false;
        }

        Rectangle r = (Rectangle) rectangle;
        return width == r.width && height == r.height;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }
}