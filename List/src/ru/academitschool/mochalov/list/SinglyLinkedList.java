package ru.academitschool.mochalov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return size;
    }

    public T getHeadData() {
        if (size == 0) {
            throw new IllegalArgumentException("Список пустой. Значение элемента получить невозможно.");
        }

        return head.getData();
    }

    public T getItemData(int index) {
        if (size == 0) {
            throw new IllegalArgumentException("Список пустой. Значение элемента получить невозможно.");
        }

        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Переданное значение " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + (size - 1) + "}.");
        }

        ListItem<T> itemByIndex = head;

        for (int i = 0; i < index; i++) {
            itemByIndex = itemByIndex.getNext();
        }

        return itemByIndex.getData();
    }

    public T setItemData(int index, T data) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + (size - 1) + "}");
        }

        ListItem<T> itemByIndex = head;

        for (int i = 0; i < index; i++) {
            itemByIndex = itemByIndex.getNext();
        }

        T oldData = itemByIndex.getData();

        itemByIndex.setData(data);

        return oldData;
    }

    public void delete(int index) {
        if (size == 0) {
            throw new IllegalArgumentException("Список пустой. Удаление элемента невозможно.");
        }

        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + (size - 1) + "}");
        }

        if (size == 1) {
            head = null;
        } else if (index == 0) {
            head = head.getNext();
        } else {
            ListItem<T> itemByIndex = head;
            ListItem<T> previousItem = head;

            for (int i = 0; i < index; i++) {
                previousItem = itemByIndex;
                itemByIndex = itemByIndex.getNext();
            }

            if (index == size - 1) {
                previousItem.setNext(null);
            } else {
                previousItem.setNext(itemByIndex.getNext());
            }
        }

        size--;
    }

    public void addBeforeHead(T data) {
        if (size == 0) {
            head = new ListItem<>(data);
            size = 1;
        } else {
            head = new ListItem<>(data, head);
            size++;
        }
    }

    public void insert(int index, T data) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Элемент не может быть добавлен по индексу " + index +
                    ". Значение index должно быть в диапазоне {0, " + size + "}");
        }

        if (index == 0) {
            addBeforeHead(data);
        } else {
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
    }

    public boolean deleteByData(T data) {
        if (size == 0) {
            throw new IllegalArgumentException("Список пустой. Операция невозможна.");
        }

        boolean wasDeleted = false;

        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;
            deleteByData(data);
            wasDeleted = true;
        }

        for (ListItem<T> item = head, previousItem = head; item != null; previousItem = item, item = item.getNext()) {
            if (item.getData().equals(data)) {
                previousItem.setNext(item.getNext());
                wasDeleted = true;
                size--;
            }
        }

        return wasDeleted;
    }

    public T deleteHead() {
        if (size == 0) {
            throw new IllegalArgumentException("Список пустой. Операция невозможна.");
        }

        T data = head.getData();

        delete(0);

        return data;
    }

    public void reverse() {
        if (size == 0) {
            throw new IllegalArgumentException("Список пустой. Операция невозможна.");
        }

        for (int i = 0; i < size / 2; i++) {
            T tempData = getItemData(i);
            setItemData(i, getItemData(size - i - 1));
            setItemData(size - i - 1, tempData);
        }
    }

    public SinglyLinkedList(SinglyLinkedList<T> list) {
        if (list.size == 0) {
            head = null;
            size = 0;
        } else {
            head = new ListItem<>(list.getHeadData());
            size = 1;

            for (int i = 1; i < list.size; i++) {
                insert(i, list.getItemData(i));
            }
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "Список пустой.";
        }

        StringBuilder stringBuilder = new StringBuilder();

        ListItem<T> item = head;

        while (item != null) {
            stringBuilder.append(item.getData()).append(" ");
            item = item.getNext();
        }

        return stringBuilder.toString().trim();
    }
}