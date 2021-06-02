package ru.academitschool.mochalov.tree_main;

import ru.academitschool.mochalov.tree.BinaryTree;

import java.util.Comparator;
import java.util.function.Consumer;

public class BinaryTreeMain {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree1 = new BinaryTree<>();
        Consumer<Integer> action = p -> System.out.print(p + " ");

        Integer[] array = new Integer[]{30, 20, 19, 25, 24, 40, 40, 41, 39, 35, 36, 31, 32};

        for (Integer item : array) {
            binaryTree1.add(item);
        }

        System.out.println("Элементы дерева 1 (проход в ширину):");
        binaryTree1.passNodesInWidth(action);
        System.out.println();

        System.out.println("Элементы дерева 1 (проход в глубину):");
        binaryTree1.passNodesInDepth(action);
        System.out.println();

        System.out.println("Элементы дерева 1 (проход в глубину с рекурсией):");
        binaryTree1.passNodesInDepthRecursive(action);
        System.out.println();
        System.out.println("Размер дерева 1 : " + binaryTree1.getSize());

        System.out.println("Добавим в дерево 1 узел со значением 5.");
        binaryTree1.add(5);
        System.out.println("Размер дерева 1: " + binaryTree1.getSize());

        System.out.println("Есть ли в дереве 1элемент со значением 5: " + binaryTree1.find(5));

        System.out.println("Есть ли в дереве 1 элемент со значением 100: " + binaryTree1.find(100));

        System.out.println("Удалим из дерева 1 элемент со значением 5. Был ли удален элемент: " + binaryTree1.delete(5));
        System.out.println("Размер дерева 1: " + binaryTree1.getSize());

        System.out.println("Удалим из дерева 1 элемент со значением 30 (корень). Был ли удален элемент: " + binaryTree1.delete(30));

        System.out.println("Элементы дерева 1 (проход в глубину с рекурсией):");
        binaryTree1.passNodesInDepthRecursive(action);
        System.out.println();
        System.out.println("Размер дерева 1: " + binaryTree1.getSize());

        Comparator<Integer> comparator = (i1, i2) -> {
            if (i1.equals(i2)) {
                return 0;
            }

            if (i1 > i2) {
                return 1;
            }

            return -1;
        };

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(comparator);

        Integer[] array2 = new Integer[]{15, 1, 8, 5, 8, 11, 17, 9, 2};

        for (Integer item : array2) {
            binaryTree2.add(item);
        }

        System.out.println("Элементы дерева 2 (проход в ширину):");
        binaryTree2.passNodesInDepthRecursive(action);
        System.out.println();
        System.out.println("Размер дерева 2: " + binaryTree2.getSize());
    }
}