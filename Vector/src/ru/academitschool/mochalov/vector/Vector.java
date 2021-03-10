package ru.academitschool.mochalov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Переданная размерность вектора равна " + size + ". Размерность вектора должна быть больше 0");
        }

        components = new double[size];
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

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Переданное значение size равно " + size + ". Значение size должно быть больше 0");
        }

        components = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double factor) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= factor;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double component : components) {
            sum += component * component;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + " Индекс должен быть в диапазоне значений от 0 до " + (components.length - 1));
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + " Индекс должен быть в диапазоне значений от 0 до " + (components.length - 1));
        }

        components[index] = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (double c : components) {
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
        return Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.add(vector2);
        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.subtract(vector2);
        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        int minArrayLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minArrayLength; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}