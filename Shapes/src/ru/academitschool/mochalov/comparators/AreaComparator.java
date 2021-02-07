package ru.academitschool.mochalov.comparators;

import java.util.Comparator;

import ru.academitschool.mochalov.shape.Shape;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape figure1, Shape figure2) {
        return Double.compare(figure1.getArea(), figure2.getArea());
    }
}