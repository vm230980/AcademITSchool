package ru.academitschool.mochalov.hash_table_main;

import ru.academitschool.mochalov.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class HashTableMain {
    public static void main(String[] args) {
        HashTable<Double> hashTable = new HashTable<>();
        System.out.println("Хеш-таблица пустая?: " + hashTable.isEmpty());
        Object[] objects = hashTable.toArray();
        System.out.println("Массив, полученный из Хеш-таблицы 1: " + Arrays.toString(objects));

        hashTable.add(1.1);
        hashTable.add(22.2);
        hashTable.add(155.1);
        hashTable.add(-8.0);
        hashTable.add(null);

        System.out.println("Хеш-таблица: " + Arrays.toString(hashTable.toArray()));

        System.out.println("Хеш-таблица: " + Arrays.toString(hashTable.toArray()));

        System.out.println("Хеш-таблица пустая?: " + hashTable.isEmpty());
        System.out.println("Размер Хеш-таблицы: " + hashTable.size());
        System.out.println("Содержит ли Хеш-таблица элемент со значением \"-9.0\"?: " + hashTable.contains(-9.0));

        Object[] objects2 = hashTable.toArray();
        System.out.println("Массив, полученный из Хеш-таблицы 1: " + Arrays.toString(objects2));
        Double[] numbers = hashTable.toArray(new Double[]{});
        System.out.println("Массив Double, полученный из Хеш-таблиц 1: " + Arrays.toString(numbers));

        System.out.println("Удалим из Хеш-таблицы элемент со значением \"155.1\". Был ли удален такой элемент?: " + hashTable.remove(155.1));
        System.out.println("Хеш-таблица: " + Arrays.toString(hashTable.toArray()));
        ArrayList<Double> arrayList1 = new ArrayList<>(Arrays.asList(-8.0, null, 1.1, 0.1));
        System.out.println("Список 1: " + arrayList1);
        System.out.println("Содержит ли Хеш-таблица все значения Списка 1?: " + hashTable.containsAll(arrayList1));

        System.out.println("Попробуем добавить в Хеш-таблицу элемент со значением 22.2. Был ли добавлен такой элемент?: " + hashTable.add(22.2));
        ArrayList<Double> arrayList2 = new ArrayList<>(Arrays.asList(.4, 22.2, 100.1));
        System.out.println("Список 2: " + arrayList2);
        System.out.println("добавим в Хеш-таблицу элементы Списка 2. Были ли добавлены элементы?: " + hashTable.addAll(arrayList2));
        System.out.println("Хеш-таблица: " + Arrays.toString(hashTable.toArray()));
        System.out.println("Удалим из Хеш-таблицы элементы Списка 2. Были ли удалены элементы?: " + hashTable.removeAll(arrayList2));
        System.out.println("Хеш-таблица: " + Arrays.toString(hashTable.toArray()));
        System.out.println("Размер Хеш-таблицы: " + hashTable.size());
        System.out.println("Сохраним в Хеш-таблице только те элементы, которые содержатся в списке 1. Были ли произведена операция?: " + hashTable.retainAll(arrayList1));
        System.out.println("Хеш-таблица: " + Arrays.toString(hashTable.toArray()));
    }
}