package ru.academitschool.mochalov.shapes_main;

import ru.academitschool.mochalov.shapes_comparators.AreaComparator;
import ru.academitschool.mochalov.shapes_comparators.PerimeterComparator;
import ru.academitschool.mochalov.shapes.Circle;
import ru.academitschool.mochalov.shapes.Rectangle;
import ru.academitschool.mochalov.shapes.Shape;
import ru.academitschool.mochalov.shapes.Square;
import ru.academitschool.mochalov.shapes.Triangle;

import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondPerimeterShape(Shape[] shapes) {
        if (shapes.length <= 1) {
            return null;
        }

        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(2),
                new Rectangle(3, 5),
                new Square(3),
                new Triangle(1, 3, 2, 4, 0, 1),
                new Circle(3),
                new Rectangle(1, 3),
                new Square(3),
                new Triangle(-1, -1, 3, 5, 1, 4)};

        System.out.println("Ширина фигуры " + shapes[0] + " равна " + shapes[0].getWidth());

        System.out.println("Высота фигуры " + shapes[7] + " равна " + shapes[7].getHeight());

        System.out.println();

        Shape maxAreaShape = getMaxAreaShape(shapes);

        if (maxAreaShape != null) {
            System.out.println("Фигура с максимальной площадью равной " +
                    maxAreaShape.getArea() + ", это: " + maxAreaShape);
        } else {
            System.out.println("Массив пустой");
        }

        Shape secondPerimeterShape = getSecondPerimeterShape(shapes);

        if (secondPerimeterShape != null) {
            System.out.println("Фигура со вторым по величине периметром равным " +
                    secondPerimeterShape.getPerimeter() + ", это: " + secondPerimeterShape);
        } else {
            System.out.println("Массив пустой или содержит только один элемент");
        }

        System.out.println();
        System.out.println("Отсортированный массив фигур:");

        for (Shape shape : shapes) {
            System.out.println("" + shape + " | Хэшкод: " + shape.hashCode());
        }

        if (shapes[2].equals(shapes[6])) {
            System.out.println("Объекты " + shapes[2] + " и " + shapes[6] + " равны");
        } else {
            System.out.println("Объекты " + shapes[2] + " и " + shapes[6] + " неравны");
        }
    }
}