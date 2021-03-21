package ru.academitschool.mochalov.array_list_main;

import java.util.Arrays;

import ru.academitschool.mochalov.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        ArrayList<Integer> arrayList4 = new ArrayList<>(30);

        System.out.println("Список 1: " + arrayList);
        System.out.println("Размер списка 1: " + arrayList.size());
        System.out.println("Пустой ли список 2: " + arrayList2.isEmpty());
        System.out.println("Содержит ли список 1 значение 2? :" + arrayList.contains(2));

        System.out.println("Получим массив типа Object из списка:");
        Object[] objects1 = arrayList.toArray();
        System.out.println(Arrays.toString(objects1));
        System.out.println("Получим массив типа Integer из списка:");
        Integer[] objects2 = arrayList.toArray(new Integer[0]);
        System.out.println(Arrays.toString(objects2));

        System.out.println("Добавим в список 1 элемент со значением 100: ");
        arrayList.add(100);
        System.out.println(arrayList);

        System.out.println("Удалим элемент со значением 100, был ли удален такой элемент?: " + arrayList.remove((Integer) 100));
        System.out.println(arrayList);

        ArrayList<Integer> arrayList3 = new ArrayList<>(Arrays.asList(10, 11, 12));
        System.out.println("Список 3: " + arrayList3);
        System.out.println("Содержит ли список 1 все значения списка 3?: " + arrayList.containsAll(arrayList3));
        System.out.println("Добавим в список 1 значения из списка 3");
        arrayList.addAll(arrayList3);
        System.out.println("Список 1: " + arrayList);
        System.out.println("Содержит ли список 1 все значения списка 3?: " + arrayList.containsAll(arrayList3));
        System.out.println("Список 1: " + arrayList);
        System.out.println("Вставим по индексу 2 в списке 1 значения из списка 3.");
        arrayList.addAll(2, arrayList3);
        System.out.println(arrayList);
        System.out.println("Оставим в списке 1 только те значения, которые содержатся в списке 3");
        arrayList.retainAll(arrayList3);
        System.out.println("Список 1: " + arrayList);
        System.out.println("Очистим список 3.");
        arrayList3.clear();
        System.out.println("Список 3: " + arrayList3);
        System.out.println("Значение элемента по индексу 1 списка 1  равно: " + arrayList.get(1));
        System.out.println("Установим значение элемента по индексу 1 списка 1 равным 15: " + arrayList.set(1, 15));
        System.out.println("Список 1: " + arrayList);

        System.out.println("Добавим в список 1 элемент со значением 0 по индексу 0: ");
        arrayList.add(0, 0);
        System.out.println(arrayList);

        System.out.println("Добавим в список 1 элемент со значением 100 по индексу 6: ");
        arrayList.add(6, 100);
        System.out.println(arrayList);

        System.out.println("Удалим элемент с индексом 5, его значение было: " + arrayList.remove(5));
        System.out.println(arrayList);

        System.out.println("Индекс первого элемента списка со значением 12: " + arrayList.indexOf(12));
        System.out.println("Индекс последнего элемента списка со значением 12: " + arrayList.lastIndexOf(12));

        arrayList.trimToSize();
    }
}