package ru.academitschool.mochalov.matrix_main;

import ru.academitschool.mochalov.matrix.Matrix;
import ru.academitschool.mochalov.vector.Vector;

public class MatrixMain {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 5);
        Matrix matrix2 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix matrix3 = new Matrix(matrix2);

        System.out.println("Матрица 1: " + matrix1);
        System.out.println("Матрица 2: " + matrix2);
        System.out.println("Матрица 3 (копия матрицы 2): " + matrix3);

        System.out.println();
        System.out.println("НЕСТАТИЧЕСКИЕ МЕТОДЫ");

        System.out.println("Размеры матрицы 2: " + matrix2.getSize());
        System.out.println("Вектор-строка с индексом 1 матрицы 2: " + matrix2.getLineVector(1));

        matrix3.setLineVector(new double[]{7, 8, 9}, 1);
        System.out.println("Измененная вектор-строка с индексом 1 матрицы 3: " + matrix3.getLineVector(1));
        System.out.println("Матрица 2: " + matrix2);
        System.out.println("Матрица 3: " + matrix3);
        System.out.println("Вектор-столбец с индексом 0 матрицы 3: " + matrix3.getColumnVector(0));

        Matrix matrix4 = matrix2.getTransposedMatrix();
        System.out.println("Матрица 4 (транспонированная матрица 2): " + matrix4);

        matrix4.getMatrixScalarMultiplicationResult(2);
        System.out.println("Матрица 4 умноженная на 2: " + matrix4);

        double d = matrix2.matrixDeterminant();
        System.out.println("Определитель матрицы 2: " + d);

        Vector vector1 = new Vector(new double[]{1, 2, 3});
        System.out.println("Вектор 1: " + vector1);
        Vector vector2 = matrix4.getMatrixVectorMultiplicationResult(vector1);
        System.out.println("Результат произведения матрицы 4 и вектора 1: " + vector2);

        Matrix matrix5 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix matrix6 = new Matrix(new double[][]{{2, 3, 4}, {5, 6, 7}, {8, 9, 10}});

        System.out.println("Матрица 5: " + matrix5);
        System.out.println("Матрица 6: " + matrix6);

        matrix5.getSum(matrix6);
        System.out.println("Сумма матриц 5 и 6 равна: " + matrix5);

        System.out.println("Матрица 5: " + matrix5);
        System.out.println("Матрица 6: " + matrix6);

        matrix5.getDifference(matrix6);
        System.out.println("Разность матриц 5 и 6 равна: " + matrix5);
        System.out.println();

        System.out.println("СТАТИЧЕСКИЕ МЕТОДЫ");

        System.out.println("Матрица 5: " + matrix5);
        System.out.println("Матрица 6: " + matrix6);

        Matrix matrixSum = Matrix.getSum(matrix5, matrix6);
        System.out.println("Сумма матриц 5 и 6 равна: " + matrixSum);

        Matrix matrixDifference = Matrix.getDifference(matrix5, matrix6);
        System.out.println("Разность матриц 5 и 6 равна: " + matrixDifference);

        Matrix matrix7 = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        Matrix matrix8 = new Matrix(new double[][]{{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}});

        System.out.println("Матрица 7: " + matrix7);
        System.out.println("Матрица 8: " + matrix8);

        Matrix matrixMultiplicationResult = Matrix.getMatrixMultiplicationResult(matrix7, matrix8);
        System.out.println("Произведение матриц 7 и 8 равно: " + matrixMultiplicationResult);
    }
}