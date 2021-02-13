package ru.academitschool.mochalov.vector;

import java.util.Arrays;

public class Vector {
    private int size;
    private double[] array;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.size = size;
        this.array = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.size, vector.array);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.array = array;
        this.size = array.length;
    }

    public Vector(int size, double[] array) {
        if (array.length == 0 || size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.size = size;
        this.array = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        String arrayString = Arrays.toString(array).replace('[', '{');
        arrayString = arrayString.replace(']', '}');

        return arrayString;
    }

    // Метод приведения к одинаковой размерности
    public void makeEqualDimension(Vector vector) {
        if (size > vector.size) {
            vector.array = Arrays.copyOf(vector.array, size);
            vector.size = size;
        } else if (size < vector.size) {
            array = Arrays.copyOf(array, vector.size);
            size = vector.size;
        }
    }

    //      Сумма векторов
    public Vector getSum(Vector vector) {

        Vector vector1 = new Vector(this);
        Vector vector2 = new Vector(vector);

        vector1.makeEqualDimension(vector2);

        double[] sum = new double[vector1.size];

        for (int i = 0; i <= vector1.size - 1; i++) {
            sum[i] = vector1.array[i] + vector2.array[i];
        }

        return new Vector(vector1.size, sum);
    }

    //      Разность векторов
    public Vector getDifference(Vector vector) {

        Vector vector1 = new Vector(this);
        Vector vector2 = new Vector(vector);

        vector1.makeEqualDimension(vector2);

        double[] difference = new double[vector1.size];

        for (int i = 0; i <= vector1.size - 1; i++) {
            difference[i] = vector1.array[i] - vector2.array[i];
        }

        return new Vector(vector1.size, difference);
    }

    //      Умножение вектора на скаляр
    public Vector getScalarMultiplication(double factor) {
        double[] scalarMultiplication = new double[size];

        for (int i = 0; i <= size - 1; i++) {
            scalarMultiplication[i] = array[i] * factor;
        }

        return new Vector(size, scalarMultiplication);
    }

    //     Разворот вектора
    public Vector getInversion() {
        double[] inversion = new double[size];

        for (int i = 0; i <= size - 1; i++) {
            inversion[i] = array[i] * -1;
        }

        return new Vector(size, inversion);
    }

    //     Получение длины вектора
    public double getLength() {
        double poweredNumbersSum = 0;

        for (int i = 0; i <= size - 1; i++) {
            poweredNumbersSum += Math.pow(array[i], 2);
        }

        return Math.sqrt(poweredNumbersSum);
    }

    //     Получение компоненты вектора по индексу
    public double getVectorComponent(int index) {
        if (index < 0 || index > array.length - 1) {
            throw new IllegalArgumentException("У данного вектора нет компонента с таким индексом");
        }

        return array[index];
    }

    //     Установка компоненты вектора по индексу
    public void setVectorComponent(int index, double value) {
        if (index < 0 || index > array.length - 1) {
            throw new IllegalArgumentException("У данного вектора нет компонента с таким индексом");
        }

        array[index] = value;
    }

    @Override
    public boolean equals(Object vector) {
        if (vector == this) {
            return true;
        }

        if (vector == null || vector.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) vector;

        if (size != v.size) {
            return false;
        }

        return Arrays.equals(array, v.array);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(size);

        for (double num : array) {
            hash = prime * hash + Double.hashCode(num);
        }

        return hash;
    }

    //      Сумма векторов. Статический метод
    public static Vector getSum(Vector vector1, Vector vector2) {

        Vector vector1copy = new Vector(vector1);
        Vector vector2copy = new Vector(vector2);

        vector1copy.makeEqualDimension(vector2copy);

        double[] sum = new double[vector1copy.size];

        for (int i = 0; i <= vector1copy.size - 1; i++) {
            sum[i] = vector1copy.array[i] + vector2copy.array[i];
        }

        return new Vector(vector1copy.size, sum);
    }

    //      Разность векторов. Статический метод
    public static Vector getDifference(Vector vector1, Vector vector2) {

        Vector vector1copy = new Vector(vector1);
        Vector vector2copy = new Vector(vector2);

        vector1copy.makeEqualDimension(vector2copy);

        double[] difference = new double[vector1copy.size];

        for (int i = 0; i <= vector1copy.size - 1; i++) {
            difference[i] = vector1copy.array[i] - vector2copy.array[i];
        }

        return new Vector(vector1copy.size, difference);
    }

    //      Скалярное произведение векторов. Статический метод
    public static double getScalarProduct(Vector vector1, Vector vector2) {

        Vector vector1copy = new Vector(vector1);
        Vector vector2copy = new Vector(vector2);

        vector1copy.makeEqualDimension(vector2copy);

        double scalarProduct = 0;

        for (int i = 0; i <= vector1copy.size - 1; i++) {
            scalarProduct += vector1copy.array[i] * vector2copy.array[i];
        }

        return scalarProduct;
    }
}