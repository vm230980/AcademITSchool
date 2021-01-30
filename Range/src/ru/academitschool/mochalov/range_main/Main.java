package ru.academitschool.mochalov.range_main;

import ru.academitschool.mochalov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число диапазона: ");
        double number1 = scanner.nextDouble();

        System.out.println("Введите второе число диапазона: ");
        double number2 = scanner.nextDouble();

        System.out.println("Введите любое третье число: ");
        double number3 = scanner.nextDouble();

        Range range = new Range(number1, number2);

        double rangeLength = range.getLength();
        boolean isInside = range.isInside(number3);

        System.out.println("Длина диапазона равна: " + rangeLength);

        if (isInside) {
            System.out.printf("Третье число принадлежит диапазону чисел от %.2f до %.2f", number1, number2);
        } else {
            System.out.printf("Третье число не принадлежит диапазону чисел от %.2f до %.2f", number1, number2);
        }

        System.out.println();
        System.out.println("Введите число на которое изменить верхний предел диапазона: ");
        double extensionAmount = scanner.nextDouble();

        range.setTo(number2 + extensionAmount);

        System.out.println("Введите новое число для проверки принадлежности диапазону: ");
        double number4 = scanner.nextDouble();

        rangeLength = range.getLength();
        System.out.println("Новая длина диапазона равна: " + rangeLength);

        isInside = range.isInside(number4);

        if (isInside) {
            System.out.printf("Третье число принадлежит диапазону чисел от %.2f до %.2f", number1, range.getTo());
        } else {
            System.out.printf("Третье число не принадлежит диапазону чисел от %.2f до %.2f", number1, range.getTo());
        }
        System.out.println();
        System.out.println();
        System.out.println("ОПЕРАЦИИ С ИНТЕРВАЛАМИ");
        Range range1 = new Range(-10, 9);
        Range range2 = new Range(10, 15);

        System.out.println("Интервал 1: " +
                "(" + range1.getFrom() + ", " + range1.getTo() + ")");

        System.out.println("Интервал 2: " +
                "(" + range2.getFrom() + ", " + range2.getTo() + ")");

        Range intersectionRange = range1.getIntersection(range2);

        if (intersectionRange != null) {
            System.out.println("Результат пересечения интервалов: " +
                    "(" + intersectionRange.getFrom() + ", " + intersectionRange.getTo() + ")");
        } else {
            System.out.println("Результат пересечения интервалов: null");
        }

        Range[] unionRange = range1.getUnion(range2);

        if (unionRange.length == 1) {
            System.out.println("Результат объединения интервалов: " +
                    "(" + unionRange[0].getFrom() + ", " + unionRange[0].getTo() + ")");
        } else {
            System.out.println("Результат объединения интервалов: " +
                    "(" + unionRange[0].getFrom() + ", " + unionRange[0].getTo() + ") и " +
                    "(" + unionRange[1].getFrom() + ", " + unionRange[1].getTo() + ")");
        }

        Range[] subtractionRange = range1.getSubtraction(range2);

        if (subtractionRange.length == 1) {
            if (subtractionRange[0] == null) {
                System.out.println("Результат разности интервалов: null");
            } else {
                System.out.println("Результат разности интервалов: " +
                        "(" + subtractionRange[0].getFrom() + ", " + subtractionRange[0].getTo() + ")");
            }
        } else {
            System.out.println("Результат разности интервалов: " +
                    "(" + subtractionRange[0].getFrom() + ", " + subtractionRange[0].getTo() + ") и " +
                    "(" + subtractionRange[1].getFrom() + ", " + subtractionRange[1].getTo() + ")");
        }
    }
}