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

        ListItem<T> item;

        head = new ListItem<>(list.getFirst());
        item = head;

        for (ListItem<T> listItem = list.head.getNext(); listItem != null; listItem = listItem.getNext()) {
            ListItem<T> nextItem = new ListItem<>(listItem.getData());

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
        checkIndex(index, true);

        return getItem(index).getData();
    }

    public T setData(int index, T data) {
        checkIndex(index, true);

        T oldData = getItem(index).getData();

        getItem(index).setData(data);

        return oldData;
    }

    public T delete(int index) {
        checkIndex(index, true);

        if (index == 0) {
            return deleteFirst();
        }

        T deletedData = getItem(index).getData();

        getItem(index - 1).setNext(getItem(index).getNext());
        size--;

        return deletedData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void insert(int index, T data) {
        checkIndex(index, false);

        if (index == 0) {
            addFirst(data);
            return;
        }

        int edge = Math.min(index, size - 1);

        if (index == size) {
            getItem(edge).setNext(new ListItem<>(data));
        } else {
            ListItem<T> newItem = new ListItem<>(data, getItem(edge));
            getItem(edge - 1).setNext(newItem);
        }

        size++;
    }

    public boolean deleteByData(T data) {
        if (size == 0) {
            return false;
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

    private ListItem<T> getItem(int index) {
        ListItem<T> itemByIndex = head;

        for (int i = 0; i < index; i++) {
            itemByIndex = itemByIndex.getNext();
        }

        return itemByIndex;
    }

    private void checkIndex(int index, boolean isAccessTry) {
        if (isAccessTry) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. Значение index должно быть в диапазоне {0, " + (size - 1) + "}.");
            }
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. Значение index должно быть в диапазоне {0, " + size + "}.");
        }
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