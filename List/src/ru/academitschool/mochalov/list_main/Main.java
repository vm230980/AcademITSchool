package ru.academitschool.mochalov.list_main;

import ru.academitschool.mochalov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> list6 = new SinglyLinkedList<>();
        list6.addFirst(15);
        System.out.println("Значения элементов списка 6: " + list6);

        for (int i = 8; i >= 0; i--) {
            list1.addFirst(i);
        }

        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
        System.out.println("Создадим список 3 из списка 1.");
        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>(list1);
        System.out.println("Значения элементов списка 3: " + list3);
        System.out.println("Значение первого элемента списка 1 равно: " + list1.getFirst());
        System.out.println("Значение элемента списка 1 с индексом 3 равно: " + list1.getData(3));

        System.out.println("Старое значение элемента списка 1 с индексом 3 равно: " +
                list1.setData(3, 100) +
                "." + System.lineSeparator() + "Новое значение элемента списка 1 с индексом 3 равно: " +
                list1.getData(3));
        System.out.println("Значения элементов списка 1 : " + list1);

        System.out.println("Удалим элемент списка 1 с индексом " + 5);
        System.out.println("Значение удаленного элемента: " + list1.delete(5));
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());

        System.out.println("Добавим в начало списка 1 элемент со значением: -1");
        list1.addFirst(-1);
        System.out.println("Значения элементов списка 1: " + list1);

        System.out.println("Вставим в список 1 элемент со значением \"-100\" по индексу 6");
        list1.insert(6, -100);
        System.out.println("Значения элементов списка 1 : " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
        System.out.println("Вставим в список 1 элемент со значением \"999\" по индексу 10");
        list1.insert(10, 999);
        System.out.println("Значения элементов списка 1: " + list1);
        list1.insert(6, 3);
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
        list1.insert(6, 3);
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
        System.out.println("Удалим первое вхождение в список 1 элемента со значением: 3. Элемент был удалён?: " + list1.deleteByData(3));
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
        System.out.println("Удаляем первый элемент списка 1 со значением: " + list1.deleteFirst());
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Удаляем элемент списка 1 с индексом 3: " + list1.delete(3));
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
        list1.reverse();
        System.out.println("Значения элементов списка 1 после разворота: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
        list1.insert(7, null);
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
        list1.deleteByData(null);
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());

        list1.delete(6);
        System.out.println("Значения элементов списка 1: " + list1);
        System.out.println("Размер списка 1 равен: " + list1.getSize());
    }
}