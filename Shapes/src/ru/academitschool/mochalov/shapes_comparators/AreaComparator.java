package ru.academitschool.mochalov.shapes_comparators;

import java.util.Comparator;

import ru.academitschool.mochalov.shapes.Shape;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}