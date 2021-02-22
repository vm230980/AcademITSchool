package ru.academitschool.mochalov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Переданная размерность вектора равна " + length + ". Размерность вектора должна быть больше 0");
        }

        components = new double[length];
    }

    public Vector(Vector vector) {
        this(vector.components);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Переданная размерность массива равна " + array.length + ". Размерность массива должна быть больше 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int length, double[] array) {
        if (length < 0) {
            throw new IllegalArgumentException("Переданное значение length равно " + length + ". Значение length должна быть больше либо равна 0");
        }

        components = Arrays.copyOf(array, length);
    }

    public int getArrayLength() {
        return components.length;
    }

    public double[] getComponents() {
        return components;
    }

    //      Сумма векторов
    public void getSum(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        double[] vectorComponentsCopy = Arrays.copyOf(vector.components, components.length);

        for (int i = 0; i < components.length; i++) {
            components[i] = components[i] + vectorComponentsCopy[i];
        }
    }

    //      Разность векторов
    public void getDifference(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        double[] vectorComponentsCopy = Arrays.copyOf(vector.components, components.length);

        for (int i = 0; i < components.length; i++) {
            components[i] = components[i] - vectorComponentsCopy[i];
        }
    }

    //      Умножение вектора на скаляр
    public void getVectorScalarMultiplicationResult(double factor) {
        for (int i = 0; i < components.length; i++) {
            components[i] = components[i] * factor;
        }
    }

    //     Разворот вектора
    public void getInversion() {
        getVectorScalarMultiplicationResult(-1);
    }

    //     Получение длины вектора
    public double getLength() {
        double poweredNumbersSum = 0;

        for (double component : components) {
            poweredNumbersSum += component * component;
        }

        return Math.sqrt(poweredNumbersSum);
    }

    //     Получение компоненты вектора по индексу
    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + " Индекс должен быть в диапазоне значений от 0 до " + (components.length - 1));
        }

        return components[index];
    }

    //     Установка компоненты вектора по индексу
    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + " Индекс должен быть в диапазоне значений от 0 до " + (components.length - 1));
        }

        components[index] = value;
    }

    @Override
    public String toString() {
        if (components.length == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Double c : components) {
            stringBuilder.append(c).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) object;

        return Arrays.equals(components, v.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (double num : components) {
            hash = prime * hash + Double.hashCode(num);
        }

        return hash;
    }

    //      Сумма векторов. Статический метод
    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vector1copy = new Vector(vector1);
        Vector vector2copy = new Vector(vector2);

        vector1copy.getSum(vector2copy);
        return vector1copy;
    }

    //      Разность векторов. Статический метод
    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vector1copy = new Vector(vector1);
        Vector vector2copy = new Vector(vector2);

        vector1copy.getDifference(vector2copy);
        return vector1copy;
    }

    //      Скалярное произведение векторов. Статический метод
    public static double getVectorsScalarMultiplicationResult(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        double[] vector1ComponentsArray = new double[]{};
        double[] vector2ComponentsArray = new double[]{};

        if (vector1.components.length < vector2.components.length) {
            vector1ComponentsArray = Arrays.copyOf(vector1.components, vector2.components.length);
            vector2ComponentsArray = Arrays.copyOf(vector2.components, vector2.components.length);
        } else if (vector1.components.length > vector2.components.length) {
            vector1ComponentsArray = Arrays.copyOf(vector1.components, vector1.components.length);
            vector2ComponentsArray = Arrays.copyOf(vector2.components, vector1.components.length);
        }

        for (int i = 0; i < vector1ComponentsArray.length; i++) {
            scalarProduct += vector1ComponentsArray[i] * vector2ComponentsArray[i];
        }

        return scalarProduct;
    }
}