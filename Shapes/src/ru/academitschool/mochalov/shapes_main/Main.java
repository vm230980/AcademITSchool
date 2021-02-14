package ru.academitschool.mochalov.shapes_main;

import ru.academitschool.mochalov.shapes_comparators.AreaComparator;
import ru.academitschool.mochalov.shapes_comparators.PerimeterComparator;
import ru.academitschool.mochalov.shapes.Circle;
import ru.academitschool.mochalov.shapes.Rectangle;
import ru.academitschool.mochalov.shapes.Shape;
import ru.academitschool.mochalov.shapes.Square;
import ru.academitschool.mochalov.shapes.Triangle;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static Shape getMaxAreaShape(Shape[] shapes) {
        Comparator<Shape> areaComparator = new AreaComparator();

        Arrays.sort(shapes, areaComparator);

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondPerimeterShape(Shape[] shapes) {
        Comparator<Shape> perimeterComparator = new PerimeterComparator();

        if (shapes.length == 1) {
            return shapes[0];
        }

        Arrays.sort(shapes, perimeterComparator);

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Circle circle1 = new Circle(2);
        Rectangle rectangle1 = new Rectangle(3, 5);
        Square square1 = new Square(3);
        Triangle triangle1 = new Triangle(1, 3, 2, 4, 0, 1);
        Circle circle2 = new Circle(3);
        Rectangle rectangle2 = new Rectangle(1, 3);
        Square square2 = new Square(3);
        Triangle triangle2 = new Triangle(-1, 0, 3, 2, 1, 4);

        Shape[] shapes = {circle1, rectangle1, square1, triangle1, circle2, rectangle2, square2, triangle2};

        System.out.println("Ширина фигуры " + circle1 + " равна " + circle1.getWidth());

        System.out.println("Высота фигуры " + triangle1 + " равна " + triangle1.getHeight());

        System.out.println("Фигура с максимальной площадью равной " +
                getMaxAreaShape(shapes).getArea() + ", это: " + getMaxAreaShape(shapes));

        System.out.println("Фигура со вторым по величине периметром равным " +
                getSecondPerimeterShape(shapes).getPerimeter() + ", это: " + getSecondPerimeterShape(shapes));

        for (Shape shape : shapes) {
            System.out.println(shape + " | Хэшкод: " + shape.hashCode());
        }

        if (square1.equals(square2)) {
            System.out.println("Объекты square1 и square2 равны");
        } else {
            System.out.println("Объекты square1 и square2 неравны");
        }
    }
}