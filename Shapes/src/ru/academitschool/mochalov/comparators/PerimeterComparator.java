package ru.academitschool.mochalov.comparators;

import ru.academitschool.mochalov.shape.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape figure1, Shape figure2) {
        return Double.compare(figure1.getPerimeter(), figure2.getPerimeter());
    }
}