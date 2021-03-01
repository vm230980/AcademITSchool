package ru.academitschool.mochalov.range_main;

import ru.academitschool.mochalov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
            System.out.println("Третье число принадлежит интервалу " + range);
        } else {
            System.out.println("Третье число не принадлежит интервалу " + range);
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
            System.out.println("Третье число принадлежит интервалу " + range);
        } else {
            System.out.println("Третье число не принадлежит интервалу " + range);
        }
        System.out.println();
        System.out.println();

        System.out.println("ОПЕРАЦИИ С ИНТЕРВАЛАМИ");
        Range range1 = new Range(5, 7);
        Range range2 = new Range(9, 12);

        System.out.println("Интервал 1: " + range1);

        System.out.println("Интервал 2: " + range2);

        Range intersection = range1.getIntersection(range2);
        System.out.println("Результат пересечения интервалов: " + intersection);

        Range[] union = range1.getUnion(range2);
        System.out.println("Результат объединения интервалов: " + Range.getRangesArrayString(union));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Результат разности интервалов: " + Range.getRangesArrayString(difference));

        Range[] rangesArray = new Range[]{new Range(1, 5), new Range(2, 8), new Range(4, 9), new Range(10, 19)};
        System.out.println("Массив интервалов: " + Range.getRangesArrayString(rangesArray));
    }
}