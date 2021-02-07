package ru.academitschool.mochalov.range_main;

import ru.academitschool.mochalov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число интервала: ");
        double firstNumber = scanner.nextDouble();

        System.out.println("Введите второе число интервала: ");
        double lastNumber = scanner.nextDouble();

        System.out.println("Введите любое третье число: ");
        double number = scanner.nextDouble();

        Range range = new Range(firstNumber, lastNumber);

        double rangeLength = range.getLength();
        boolean isInside = range.isInside(number);

        System.out.println("Длина интервала равна: " + rangeLength);

        if (isInside) {
            System.out.printf("Третье число принадлежит интервалу чисел от %.2f до %.2f", firstNumber, lastNumber);
        } else {
            System.out.printf("Третье число не принадлежит интервалу чисел от %.2f до %.2f", firstNumber, lastNumber);
        }

        System.out.println();
        System.out.println("Введите число на которое изменить верхний предел интервала: ");
        double extensionAmount = scanner.nextDouble();

        range.setTo(lastNumber + extensionAmount);

        System.out.println("Введите новое число для проверки принадлежности интервалу: ");
        double number4 = scanner.nextDouble();

        rangeLength = range.getLength();
        System.out.println("Новая длина интервала равна: " + rangeLength);

        isInside = range.isInside(number4);

        if (isInside) {
            System.out.printf("Третье число принадлежит интервалу чисел от %.2f до %.2f", firstNumber, range.getTo());
        } else {
            System.out.printf("Третье число не принадлежит интервалу чисел от %.2f до %.2f", firstNumber, range.getTo());
        }
        System.out.println();
        System.out.println(); */
        System.out.println("ОПЕРАЦИИ С ИНТЕРВАЛАМИ");
        Range range1 = new Range(5, 6);
        Range range2 = new Range(4, 10);

        System.out.println("Интервал 1: " + range1);

        System.out.println("Интервал 2: " + range2);

        Range intersectionRange = range1.getIntersection(range2);

        if (intersectionRange != null) {
            System.out.println("Результат пересечения интервалов: " + intersectionRange);
        } else {
            System.out.println("Результат пересечения интервалов: null");
        }

        Range[] union = range1.getUnion(range2);

        if (union.length == 1) {
            System.out.print("Результат объединения интервалов: ");
            Range.printRange(union);
        } else {
            System.out.print("Результат объединения интервалов: ");
            Range.printRange(union);
        }

        Range[] difference = range1.getDifference(range2);

        System.out.print("Результат разности интервалов: ");
        Range.printRange(difference);
    }
}