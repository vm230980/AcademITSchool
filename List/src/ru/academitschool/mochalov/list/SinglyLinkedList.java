package ru.academitschool.mochalov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(SinglyLinkedList<T> list) {
        if (list == null) {
            throw new NullPointerException("Переданный список не должен быть null");
        }

        if (list.size == 0) {
            return;
        }

        ListItem<T> item = null;
        ListItem<T> nextItem;
        boolean isHead = true;

        for (ListItem<T> listItem = list.head; listItem != null; listItem = listItem.getNext()) {
            nextItem = new ListItem<>(listItem.getData());

            if (isHead) {
                head = nextItem;
                item = nextItem;
                isHead = false;
                continue;
            }

            item.setNext(nextItem);
            item = item.getNext();
        }

        size = list.size;
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пустой. Значение элемента получить невозможно.");
        }

        return head.getData();
    }

    public T getData(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданное значение " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + (size - 1) + "}.");
        }

        return getItem(index).getData();
    }

    public T setData(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " + "Значение index должно быть в диапазоне {0, " + (size - 1) + "}");
        }

        T oldData = getItem(index).getData();

        getItem(index).setData(data);

        return oldData;
    }

    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " + "Значение index должно быть в диапазоне {0, " + (size - 1) + "}");
        }

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> itemByIndex = head;
        ListItem<T> previousItem = head;

        for (int i = 0; i < index; i++) {
            previousItem = itemByIndex;
            itemByIndex = itemByIndex.getNext();
        }

        T deletedData = itemByIndex.getData();

        if (index == size - 1) {
            previousItem.setNext(null);
            size--;
            return deletedData;
        }

        previousItem.setNext(itemByIndex.getNext());
        size--;
        return deletedData;
    }

    public void addBeforeFirst(T data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Элемент не может быть добавлен по индексу " + index + ". Значение index должно быть в диапазоне {0, " + size + "}");
        }

        if (index == 0) {
            addBeforeFirst(data);
            return;
        }

        ListItem<T> itemByIndex = head;
        ListItem<T> previousItem = head;

        int edge = Math.min(index, size - 1);

        for (int i = 0; i < edge; i++) {
            previousItem = itemByIndex;
            itemByIndex = itemByIndex.getNext();
        }

        if (index == size) {
            itemByIndex.setNext(new ListItem<>(data));
        } else {
            ListItem<T> newItem = new ListItem<>(data, itemByIndex);
            previousItem.setNext(newItem);
        }

        size++;
    }

    public boolean deleteByData(T data) {
        if (size == 0) {
            throw new NoSuchElementException("Список пустой. Операция невозможна.");
        }

        if (data == null) {
            throw new IllegalArgumentException("Объект для поиска не может быть null");
        }

        for (ListItem<T> item = head, previousItem = null; item != null; previousItem = item, item = item.getNext()) {
            if (Objects.equals(item.getData(), data)) {
                if (previousItem == null) {
                    head = item.getNext();
                } else {
                    previousItem.setNext(item.getNext());
                }

                size--;

                return true;
            }
        }

        return false;
    }

    public T deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пустой. Операция невозможна.");
        }

        T deletedData = head.getData();
        head = head.getNext();

        size--;
        return deletedData;
    }

    public void reverse() {
        if (size == 0) {
            return;
        }

        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;
        ListItem<T> nextItem;

        while (currentItem != null) {
            nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public ListItem<T> getItem(int index) {
        ListItem<T> itemByIndex = head;

        for (int i = 0; i < index; i++) {
            itemByIndex = itemByIndex.getNext();
        }

        return itemByIndex;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        ListItem<T> item = head;

        while (item != null) {
            stringBuilder.append(item.getData()).append(", ");
            item = item.getNext();
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");

        return stringBuilder.toString();
    }
}