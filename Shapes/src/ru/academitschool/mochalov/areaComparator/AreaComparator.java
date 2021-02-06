package ru.academitschool.mochalov.areaComparator;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape figure1, Shape figure2) {
        return figure1.getArea() - figure2.getArea();
    }
}