package ru.academitschool.mochalov.shapes;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getWidth() {
        return sideLength;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        return sideLength * sideLength;
    }

    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String toString() {
        return "Квадрат со стороной " + sideLength;
    }

    @Override
    public boolean equals(Object square) {
        if (square == this) {
            return true;
        }

        if (square == null || square.getClass() != getClass()) {
            return false;
        }

        Square s = (Square) square;
        return sideLength == s.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(sideLength);
        return hash;
    }
}