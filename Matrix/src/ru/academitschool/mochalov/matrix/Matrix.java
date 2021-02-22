package ru.academitschool.mochalov.matrix;

import ru.academitschool.mochalov.vector.Vector;

public class Matrix {
    private final Vector[] lines;

    public Matrix(int n, int m) {
        lines = new Vector[n];

        for (int i = 0; i < n; i++) {
            lines[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        this.lines = new Vector[matrix.lines.length];

        for (int i = 0; i < lines.length; i++) {
            this.lines[i] = new Vector(matrix.lines[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Невозможно создать пустую матрицу");
        }

        lines = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            lines[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vector) {
        lines = vector;
    }

    public String getSize() {
        if (lines.length == 0) {
            return "Размеры матрицы 0x0";
        }

        return "Размер матрицы " + lines.length + "x" + lines[0].getArrayLength();
    }

    public Vector getLineVector(int index) {
        if (index < 0 || index >= lines.length) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + ". Индекс должен быть в диапазоне значений от 0 до " + (lines.length - 1));
        }

        return lines[index];
    }

    public void setLineVector(double[] array, int index) {
        if (index < 0 || index >= lines.length) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + ". Индекс должен быть в диапазоне значений от 0 до " + (lines.length - 1));
        }

        lines[index] = new Vector(array);
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index >= lines[0].getArrayLength()) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + ". Индекс должен быть в диапазоне значений от 0 до " + (lines[0].getArrayLength() - 1));
        }

        double[] array = new double[lines.length];
        int i = 0;

        for (Vector v : lines) {
            array[i] = v.getComponents()[index];
            i++;
        }

        return new Vector(array);
    }

    public Matrix getTransposedMatrix() {
        Matrix transposedMatrix = new Matrix(lines[0].getArrayLength(), lines.length);

        for (int i = 0; i < lines[0].getArrayLength(); i++) {
            for (int j = 0; j < lines.length; j++) {
                transposedMatrix.lines[i].setComponent(j, lines[j].getComponent(i));
            }
        }

        return transposedMatrix;
    }

    public void getMatrixScalarMultiplicationResult(double factor) {
        for (Vector line : lines) {
            line.getVectorScalarMultiplicationResult(factor);
        }
    }

    public double matrixDeterminant() {
        if (lines.length != lines[0].getArrayLength()) {
            throw new IllegalArgumentException("Матрица, для которой вызвано вычисление определителя, не является квадратной");
        }

        double[][] matrixArray = new double[lines.length][lines.length];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines.length; j++) {
                matrixArray[i][j] = lines[i].getComponent(j);
            }
        }

        return arrayDeterminant(matrixArray);
    }

    private static double arrayDeterminant(double[][] matrixArray) {
        if (matrixArray.length == 1) {
            return matrixArray[0][0];
        }

        if (matrixArray.length == 2) {
            return matrixArray[0][0] * matrixArray[1][1] - matrixArray[0][1] * matrixArray[1][0];
        }

        double[][] tempArray;
        double determinant = 0;

        for (int i = 0; i < matrixArray[0].length; i++) {
            tempArray = new double[matrixArray.length - 1][matrixArray[0].length - 1];

            for (int j = 1; j < matrixArray.length; j++) {
                for (int k = 0; k < matrixArray[0].length; k++) {
                    if (k < i) {
                        tempArray[j - 1][k] = matrixArray[j][k];
                    } else if (k > i) {
                        tempArray[j - 1][k - 1] = matrixArray[j][k];
                    }
                }
            }

            determinant += matrixArray[0][i] * Math.pow(-1, i) * arrayDeterminant(tempArray);
        }

        return determinant;
    }

    public Vector getMatrixVectorMultiplicationResult(Vector vector) {
        if (lines[0].getArrayLength() != vector.getArrayLength()) {
            throw new IllegalArgumentException("Число столбцов в матрице должно совпадать с числом строк в векторе-столбце.");
        }

        double[] array = new double[vector.getArrayLength()];
        double sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                sum += lines[i].getComponent(j) * vector.getComponent(j);
            }

            array[i] = sum;
            sum = 0;
        }

        return new Vector(array);
    }

    public void getSum(Matrix matrix) {
        if (lines.length != matrix.lines.length || lines[0].getArrayLength() != matrix.lines[0].getArrayLength()) {
            throw new IllegalArgumentException("Размеры матриц неравны. Сложение невозможно");
        }

        for (int i = 0; i < lines.length; i++) {
            lines[i].getSum(matrix.lines[i]);
        }
    }

    public void getDifference(Matrix matrix) {
        if (lines.length != matrix.lines.length || lines[0].getArrayLength() != matrix.lines[0].getArrayLength()) {
            throw new IllegalArgumentException("Размеры матриц неравны. Вычитание невозможно");
        }

        for (int i = 0; i < lines.length; i++) {
            lines[i].getDifference(matrix.lines[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.lines.length != matrix2.lines.length || matrix1.lines[0].getArrayLength() != matrix2.lines[0].getArrayLength()) {
            throw new IllegalArgumentException("Размеры матриц неравны. Сложение невозможно");
        }

        Matrix matrix1copy = new Matrix(matrix1);
        Matrix matrix2copy = new Matrix(matrix2);
        matrix1copy.getSum(matrix2copy);

        return matrix1copy;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.lines.length != matrix2.lines.length || matrix1.lines[0].getArrayLength() != matrix2.lines[0].getArrayLength()) {
            throw new IllegalArgumentException("Размеры матриц неравны. Сложение невозможно");
        }

        Matrix matrix1copy = new Matrix(matrix1);
        Matrix matrix2copy = new Matrix(matrix2);
        matrix1copy.getDifference(matrix2copy);

        return matrix1copy;
    }

    public static Matrix getMatrixMultiplicationResult(Matrix matrix1, Matrix matrix2) {
        if (matrix1.lines[0].getArrayLength() != matrix2.lines.length) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы неравно количеству строк второй матрицы. " +
                    "Умножение невозможно");
        }

        Matrix multiplicationResult = new Matrix(matrix1.lines.length, matrix2.lines[0].getArrayLength());
        Matrix matrix1copy = new Matrix(matrix1);
        Matrix matrix2copy = new Matrix(matrix2);
        double sum = 0;

        for (int i = 0; i < multiplicationResult.lines.length; i++) {
            for (int j = 0; j < multiplicationResult.lines[0].getArrayLength(); j++) {
                for (int k = 0; k < matrix1.lines[0].getArrayLength(); k++) {
                    sum += matrix1copy.lines[i].getComponent(k) * matrix2copy.lines[k].getComponent(j);
                }

                multiplicationResult.lines[i].setComponent(j, sum);
                sum = 0;
            }
        }

        return multiplicationResult;
    }

    @Override
    public String toString() {
        if (lines.length == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector v : lines) {
            stringBuilder.append(v.toString()).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}");

        String string = stringBuilder.toString();
        string = string.replace('[', '{');
        string = string.replace(']', '}');

        return string;
    }
}