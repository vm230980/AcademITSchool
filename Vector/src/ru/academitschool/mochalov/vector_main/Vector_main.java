package ru.academitschool.mochalov.vector_main;

import ru.academitschool.mochalov.vector.Vector;

public class Vector_main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{2, 3, 0});
        Vector vector2 = new Vector(new double[]{4, 7, 8, 5});

        System.out.println();
        System.out.println("ОПЕРАЦИИ С ВЕКТОРАМИ");
        System.out.println("Вектор 1 равен: " + vector1.toString());
        System.out.println("Вектор 2 равен: " + vector2.toString());

        //      Размерность вектора
        System.out.println("Размерность вектора 1: " + vector1.getSize());
        
        //      Сумма векторов
        Vector sum = vector1.getSum(vector2);
        System.out.println("Сумма векторов 1 и 2 равна: " + sum);

        //      Разность векторов
        Vector difference = vector1.getDifference(vector2);
        System.out.println("Разность векторов 1 и 2 равна: " + difference);

        //      Умножение вектора на скаляр
        double factor = 5;
        Vector scalarMultiplication = vector1.getScalarMultiplication(factor);
        System.out.println("Умножение вектора 1 на " + factor + " равно: " + scalarMultiplication);

        //     Разворот вектора
        System.out.println("Развернутый вектор 1 равен: " + vector1.getInversion());

        //     Получение длины вектора
        System.out.println("Длина вектора 1 равна: " + vector1.getLength());

        //     Получение компоненты вектора по индексу
        System.out.println("Компонента вектора 1 по индексу " + 1 + " равна: " + vector1.getVectorComponent(1));

        //     Установка компоненты вектора по индексу
        vector1.setVectorComponent(1, 9);
        System.out.println("Компонента вектора 1 по индексу " + 1 + " после замены равна: " + vector1.getVectorComponent(1));

        //     Сравнение векторов
        System.out.println();
        System.out.println("СРАВНЕНИЕ ВЕКТОРОВ:");

        Vector vector3 = new Vector(vector1);
        System.out.println("Вектор 1 равен: " + vector1.toString());
        System.out.println("Вектор 2 равен: " + vector2.toString());
        System.out.println("Вектор 3 равен: " + vector3.toString());

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

        //      Сумма векторов. Статический метод
        System.out.println();
        System.out.println("ТЕСТИРОВАНИЕ СТАТИЧЕСКИХ МЕТОДОВ:");

        System.out.println("Вектор 1 равен: " + vector1.toString());
        System.out.println("Вектор 2 равен: " + vector2.toString());
        System.out.println("Сумма векторов 1 и 2 равна: " + Vector.getSum(vector1, vector2));

        //      Разность векторов. Статический метод
        System.out.println("Разность векторов 1 и 2 равна: " + Vector.getDifference(vector1, vector2));

        //      Скалярное произведение векторов. Статический метод
        System.out.println("Скалярное произведение векторов 1 и 2 равно: " + Vector.getScalarProduct(vector1, vector2));
    }
}