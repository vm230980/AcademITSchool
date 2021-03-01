package ru.academitschool.mochalov.vector_main;

import ru.academitschool.mochalov.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        Vector vector1 = new Vector(1, new double[]{});
        Vector vector2 = new Vector(new double[]{4, 5});

        System.out.println();
        System.out.println("ОПЕРАЦИИ С ВЕКТОРАМИ. НЕСТАТИЧЕСКИЕ МЕТОДЫ");
        System.out.println("Вектор 1 равен: " + vector1);
        System.out.println("Вектор 2 равен: " + vector2);

        // Сумма векторов
        vector1.addVector(vector2);
        System.out.println("Сумма векторов 1 и 2 равна: " + vector1);
        System.out.println("----------------------------");

        // Разность векторов
        System.out.println("Вектор 1 равен: " + vector1);
        System.out.println("Вектор 2 равен: " + vector2);
        vector1.subtractVector(vector2);
        System.out.println("Разность векторов 1 и 2 равна: " + vector1);
        System.out.println("----------------------------");

        // Умножение вектора на скаляр
        System.out.println("Вектор 1 равен: " + vector1);
        double factor = 5;
        vector1.multiplyByScalar(factor);
        System.out.println("Умножение вектора 1 на " + factor + " равно: " + vector1);
        System.out.println("----------------------------");

        // Разворот вектора
        System.out.println("Вектор 1 равен: " + vector1);
        vector1.Reverse();
        System.out.println("Развернутый вектор 1 равен: " + vector1);
        System.out.println("----------------------------");

        // Получение длины вектора
        System.out.println("Длина вектора 1 равна: " + vector1.getLength());

        // Получение компоненты вектора по индексу
        System.out.println("Компонента вектора 1 по индексу " + 1 + " равна: " + vector1.getComponent(1));

        // Установка компоненты вектора по индексу
        vector1.setComponent(1, 9);
        System.out.println("Компонента вектора 1 по индексу " + 1 + " после замены равна: " + vector1.getComponent(1));
        System.out.println("Вектор 1 равен: " + vector1);

        // Размерность вектора
        System.out.println("Размерность вектора 1: " + vector1.getSize());

        // Сравнение векторов
        System.out.println();
        System.out.println("СРАВНЕНИЕ ВЕКТОРОВ:");

        Vector vector3 = new Vector(vector1);
        System.out.println("Вектор 1 равен: " + vector1);
        System.out.println("Вектор 2 равен: " + vector2);
        System.out.println("Вектор 3 (копия Вектора 1) равен: " + vector3);

        if (vector1.equals(vector3)) {
            System.out.println("Векторы 1 и 3 равны. Хэшкод вектора 1: " + vector1.hashCode() +
                    " Хэшкод вектора 3: " + vector3.hashCode());
        } else {
            System.out.println("Векторы 1 и 3 неравны. Хэшкод вектора 1: " + vector1.hashCode() +
                    " Хэшкод вектора 3: " + vector3.hashCode());
        }

        if (vector1.equals(vector2)) {
            System.out.println("Векторы 1 и 2 равны. Хэшкод вектора 1: " + vector1.hashCode() +
                    " Хэшкод вектора 2: " + vector2.hashCode());
        } else {
            System.out.println("Векторы 1 и 2 неравны. Хэшкод вектора 1: " + vector1.hashCode() +
                    " Хэшкод вектора 2: " + vector2.hashCode());
        }

        System.out.println();
        System.out.println("ОПЕРАЦИИ С ВЕКТОРАМИ. СТАТИЧЕСКИЕ МЕТОДЫ");

        Vector vector4 = new Vector(new double[]{1, 1, 1});
        Vector vector5 = new Vector(new double[]{2, 2, 2});

        System.out.println("Вектор 4 равен: " + vector4);
        System.out.println("Вектор 5 равен: " + vector5);

        // Сумма векторов. Статический метод
        System.out.println("Сумма векторов 4 и 5 равна: " + Vector.getSum(vector4, vector5));

        // Разность векторов. Статический метод
        System.out.println("Разность векторов 4 и 5 равна: " + Vector.getDifference(vector4, vector5));

        // Скалярное произведение векторов. Статический метод
        System.out.println("Скалярное произведение векторов 4 и 5 равно: " + Vector.getScalarProduct(vector4, vector5));
    }
}