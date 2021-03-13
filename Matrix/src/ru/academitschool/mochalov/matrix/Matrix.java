package ru.academitschool.mochalov.matrix;

import ru.academitschool.mochalov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsAmount, int columnsAmount) {
        if (rowsAmount < 1 || columnsAmount < 1) {
            throw new IllegalArgumentException("Переданное число строк матрицы равно: " + rowsAmount +
                    "Переданное число столбцов матрицы равно: " + columnsAmount +
                    ". Значения строк и столбцов матрицы должны быть >= 1");
        }

        rows = new Vector[rowsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            rows[i] = new Vector(columnsAmount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Длины массива и вложенных массивов должны быть >= 1");
        }

        int maxInnerArrayLength = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (array[i].length > maxInnerArrayLength) {
                maxInnerArrayLength = array[i].length;
            }
        }

        if (maxInnerArrayLength == 0) {
            throw new IllegalArgumentException("Длины массива и вложенных массивов должны быть >= 1");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxInnerArrayLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Длина массива векторов должна быть >= 1");
        }

        int maxVectorSize = vectors[0].getSize();

        for (int i = 1; i < vectors.length; i++) {
            if (vectors[i].getSize() > maxVectorSize) {
                maxVectorSize = vectors[i].getSize();
            }
        }

        double[][] matrixArray = new double[vectors.length][maxVectorSize];

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].getSize(); j++) {
                matrixArray[i][j] = vectors[i].getComponent(j);
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrixArray[i]);
        }
    }

    public int getRowsAmount() {
        return rows.length;
    }

    public int getColumnsAmount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + ". Индекс должен быть в диапазоне значений от 0 до " + (rows.length - 1));
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + ". Индекс должен быть в диапазоне значений от 0 до " + (rows.length - 1));
        }

        if (vector.getSize() != getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Размер вектора " + vector.getSize() + " должен совападать с количеством столбцов матрицы - " + getColumnsAmount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Переданный индекс равен " + index + ". Индекс должен быть в диапазоне значений от 0 до " + (getColumnsAmount() - 1));
        }

        double[] array = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            array[i] = rows[i].getComponent(index);
        }

        return new Vector(array);
    }

    public void transpose() {
        Vector[] newRows = new Vector[getColumnsAmount()];

        for (int i = 0; i < getColumnsAmount(); i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByScalar(double factor) {
        for (Vector row : rows) {
            row.multiplyByScalar(factor);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsAmount()) {
            throw new UnsupportedOperationException("Матрица с размерами " + rows.length + "x" + getColumnsAmount() +
                    " не является квадратной. Вычисление определителя невозможно");
        }

        double[][] matrixArray = new double[rows.length][rows.length];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows.length; j++) {
                matrixArray[i][j] = rows[i].getComponent(j);
            }
        }

        return getDeterminant(matrixArray);
    }

    private static double getDeterminant(double[][] matrixArray) {
        if (matrixArray.length == 1) {
            return matrixArray[0][0];
        }

        if (matrixArray.length == 2) {
            return matrixArray[0][0] * matrixArray[1][1] - matrixArray[0][1] * matrixArray[1][0];
        }

        double determinant = 0;

        for (int i = 0; i < matrixArray[0].length; i++) {
            double[][] minor;

            minor = new double[matrixArray.length - 1][matrixArray[0].length - 1];

            for (int j = 1; j < matrixArray.length; j++) {
                for (int k = 0; k < matrixArray[0].length; k++) {
                    if (k < i) {
                        minor[j - 1][k] = matrixArray[j][k];
                    } else if (k > i) {
                        minor[j - 1][k - 1] = matrixArray[j][k];
                    }
                }
            }

            determinant += matrixArray[0][i] * Math.pow(-1, i) * getDeterminant(minor);
        }

        return determinant;
    }

    public Vector getProductByVector(Vector vector) {
        if (getColumnsAmount() != vector.getSize()) {
            throw new IllegalArgumentException("Число столбцов в матрице - " + getColumnsAmount()
                    + " должно совпадать с числом строк в векторе-столбце - " + vector.getSize());
        }

        double[] array = new double[getRowsAmount()];

        for (int i = 0; i < array.length; i++) {
            array[i] = Vector.getScalarProduct(rows[i], vector);
        }

        return new Vector(array);
    }

    private static boolean haveNotSameSizes(Matrix matrix1, Matrix matrix2) {
        return matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsAmount() != matrix2.getColumnsAmount();
    }

    public void add(Matrix matrix) {
        if (haveNotSameSizes(this, matrix)) {
            throw new IllegalArgumentException("Размеры матрицы 1: " + rows.length + "x" + getColumnsAmount() +
                    " и матрицы 2: " + matrix.rows.length + "x" + matrix.getColumnsAmount() + " неравны. Сложение невозможно");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (haveNotSameSizes(this, matrix)) {
            throw new IllegalArgumentException("Размеры матрицы 1: " + rows.length + "x" + getColumnsAmount() +
                    " и матрицы 2: " + matrix.rows.length + "x" + matrix.getColumnsAmount() + " неравны. Вычитание невозможно");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (haveNotSameSizes(matrix1, matrix2)) {
            throw new IllegalArgumentException("Размеры матрицы 1: " + matrix1.rows.length + "x" + matrix1.getColumnsAmount() +
                    " и матрицы 2: " + matrix2.rows.length + "x" + matrix2.getColumnsAmount() + " неравны. Сложение невозможно");
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (haveNotSameSizes(matrix1, matrix2)) {
            throw new IllegalArgumentException("Размеры матрицы 1: " + matrix1.rows.length + "x" + matrix1.getColumnsAmount() +
                    " и матрицы 2: " + matrix2.rows.length + "x" + matrix2.getColumnsAmount() + " неравны. Вычитание невозможно");
        }
        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsAmount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы: " + matrix1.getColumnsAmount() +
                    " неравно количеству строк второй матрицы: " + matrix2.rows.length + ". Умножение невозможно");
        }

        Matrix product = new Matrix(matrix1.rows.length, matrix2.getColumnsAmount());

        for (int i = 0; i < product.rows.length; i++) {
            for (int j = 0; j < product.getColumnsAmount(); j++) {
                double sum = 0;
                for (int k = 0; k < matrix1.getColumnsAmount(); k++) {
                    sum += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                product.rows[i].setComponent(j, sum);
            }
        }

        return product;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector v : rows) {
            stringBuilder.append(v).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}");

        return stringBuilder.toString();
    }
}