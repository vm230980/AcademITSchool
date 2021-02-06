package ru.academitschool.mochalov.shape_main;

import ru.academitschool.mochalov.areaComparator.AreaComparator;
import ru.academitschool.mochalov.circle.Circle;
import ru.academitschool.mochalov.rectangle.Rectangle;
import ru.academitschool.mochalov.shape.Shape;
import ru.academitschool.mochalov.square.Square;
import ru.academitschool.mochalov.triangle.Triangle;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void getMaxAreaShape(Shape[] figures) {
        Comparator areaComparator = new AreaComparator();
        Arrays.sort(figures, areaComparator);
        System.out.println(figures[figures.length - 1]);
    }

    public static void main(String[] args) {
        Circle circle1 = new Circle(2);
        Rectangle rectangle1 = new Rectangle(3, 5);
        Square square1 = new Square(3);
        Triangle triangle1 = new Triangle(1, 3, 2, 4, 0, 1);
        Circle circle2 = new Circle(3);
        Rectangle rectangle2 = new Rectangle(1, 3);
        Square square2 = new Square(2);
        Triangle triangle2 = new Triangle(-1, 0, 3, 2, 1, 4);

        Shape[] figures = {circle1, rectangle1, square1, triangle1, circle2, rectangle2, square2, triangle2};

        getMaxAreaShape(figures);
        }
}