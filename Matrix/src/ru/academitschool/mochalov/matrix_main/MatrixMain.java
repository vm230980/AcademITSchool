package ru.academitschool.mochalov.matrix_main;

import ru.academitschool.mochalov.matrix.Matrix;
import ru.academitschool.mochalov.vector.Vector;

public class MatrixMain {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 5);
        Matrix matrix2 = new Matrix(new double[][]{{1}, {4, 5}, {7, 8, 9}});
        Matrix matrix3 = new Matrix(matrix2);
        Matrix matrix10 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}});
        Vector[] vectors = new Vector[]{
                new Vector(new double[]{1}),
                new Vector(new double[]{1, 2}),
                new Vector(new double[]{1, 2, 3})
        };

        Matrix matrix0 = new Matrix(vectors);
        System.out.println("Матрица из массива векторов: " + matrix0);

        System.out.println("Матрица 1: " + matrix1);
        System.out.println("Матрица 2: " + matrix2);
        System.out.println("Матрица 3 (копия матрицы 2): " + matrix3);

        System.out.println();
        System.out.println("НЕСТАТИЧЕСКИЕ МЕТОДЫ");

        System.out.println("Размеры матрицы 2: " + matrix2.getRowsAmount() + "x" + matrix2.getColumnsAmount());
        System.out.println("Вектор-строка с индексом 1 матрицы 2: " + matrix2.getRow(1));

        matrix3.setRow(1, new Vector(new double[]{1, 1, 1}));
        System.out.println("Измененная вектор-строка с индексом 1 матрицы 3: " + matrix3.getRow(1));
        System.out.println("Матрица 2: " + matrix2);
        System.out.println("Матрица 3: " + matrix3);
        System.out.println("Вектор-столбец с индексом 0 матрицы 3: " + matrix3.getColumn(0));

        System.out.println("Матрица 10: " + matrix10);
        matrix10.transpose();
        System.out.println("Транспонированная матрица 10: " + matrix10);

        matrix2.multiplyByScalar(2);
        System.out.println("Матрица 2 умноженная на 2: " + matrix2);

        double d = matrix2.getDeterminant();
        System.out.println("Определитель матрицы 2: " + d);

        Vector vector1 = new Vector(new double[]{1, 2, 3, 4, 5});
        System.out.println("Вектор 1: " + vector1);
        Vector vector2 = matrix10.getProductByVector(vector1);
        System.out.println("Результат произведения матрицы 10 и вектора 1: " + vector2);

        Matrix matrix5 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix matrix6 = new Matrix(new double[][]{{2, 3, 4}, {5, 6, 7}, {8, 9, 10}});

        System.out.println("Матрица 5: " + matrix5);
        System.out.println("Матрица 6: " + matrix6);

        matrix5.add(matrix6);
        System.out.println("Сумма матриц 5 и 6 равна: " + matrix5);

        System.out.println("Матрица 5: " + matrix5);
        System.out.println("Матрица 6: " + matrix6);

        matrix5.subtract(matrix6);
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

        Matrix matrixMultiplicationResult = Matrix.getProduct(matrix7, matrix8);
        System.out.println("Произведение матриц 7 и 8 равно: " + matrixMultiplicationResult);
    }
}