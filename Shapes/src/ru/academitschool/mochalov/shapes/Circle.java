package ru.academitschool.mochalov.shapes;

import ru.academitschool.mochalov.shape.Shape;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius;
    }

    public double getHeight() {
        return radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Фигура " + getClass().getSimpleName() + " с радиусом " + radius;
    }

    @Override
    public boolean equals(Object circle) {
        if (circle == this) {
            return true;
        }

        if (circle == null || circle.getClass() != getClass()) {
            return false;
        }

        Circle c = (Circle) circle;
        return radius == c.radius;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }
}