package ru.academitschool.mochalov.shapes;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Окружность с радиусом " + radius;
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