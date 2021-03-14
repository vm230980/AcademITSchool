package ru.academitschool.mochalov.list_main;

import ru.academitschool.mochalov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> list5 = new SinglyLinkedList<>();
        System.out.println("Значения элементов списка 5: " + list5);
        SinglyLinkedList<Integer> list6 = new SinglyLinkedList<>();
        list6.addBeforeHead(15);

        for (int i = 5; i >= 0; i--) {
            list.addBeforeHead(i);
        }

        System.out.println("Значения элементов списка: " + list);
        System.out.println("Размер списка равен: " + list.getSize());
        System.out.println("Значение первого элемента равно: " + list.getHeadData());
        System.out.println("Значение элемента с индексом 3 равно: " + list.getItemData(3));

        System.out.println("Старое значение элемента с индексом 3 равно: " +
                list.setItemData(3, 100) +
                "." + System.lineSeparator() + "Новое значение элемента с индексом 3 равно: " +
                list.getItemData(3));
        System.out.println("Значения элементов списка: " + list);

        System.out.println("Удалим элемент с индексом " + 5);
        list.delete(5);
        System.out.println("Значения элементов списка: " + list);
        System.out.println("Размер списка равен: " + list.getSize());

        System.out.println("Добавим в начало списка элемент со значением: -1");
        list.addBeforeHead(-1);
        System.out.println("Значения элементов списка: " + list);

        System.out.println("Вставим элемент по индексу 6");
        list.insert(6, -100);
        System.out.println("Значения элементов списка: " + list);
        System.out.println("Размер списка равен: " + list.getSize());

        list.insert(6, 3);
        System.out.println("ВНИМАНИЕ Значения элементов списка: " + list);
        System.out.println("Размер списка равен: " + list.getSize());
        list.insert(0, 3);
        list.insert(0, 3);
        list.insert(6, 3);
        System.out.println("Значения элементов списка: " + list);
        System.out.println("Размер списка равен: " + list.getSize());
        System.out.println("Пробуем удалить  элементы со значением: 3. Элементы были удалены?: " + list.deleteByData(3));
        System.out.println("Значения элементов списка: " + list);
        System.out.println("Размер списка равен: " + list.getSize());
        System.out.println("Пробуем удалить  элементы со значением: 3. Элементы были удалены?: " + list.deleteByData(3));

        System.out.println("Удаляем первый элемент со значением: " + list.deleteHead());
        System.out.println("Значения элементов списка: " + list);

        list.reverse();
        System.out.println("Значения элементов списка после разворота: " + list);

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>(list);
        System.out.println("Значения элементов списка 2 (копия списка 1): " + list2);
        System.out.println("Размер списка 2 равен: " + list2.getSize());
    }
}