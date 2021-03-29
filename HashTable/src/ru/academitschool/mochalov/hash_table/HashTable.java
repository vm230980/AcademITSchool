package ru.academitschool.mochalov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] lists;
    private int size = 0;
    private int modCount = 0;
    private static final int defaultArraySize = 5;

    public HashTable() {
        this(defaultArraySize);
    }

    public HashTable(int arraySize) {
        if (arraySize < 1) {
            throw new IllegalArgumentException("Переданный размер массива таблицы " + arraySize + " должен быть >= 1.");
        }

        //noinspection unchecked
        lists = (ArrayList<T>[]) new ArrayList[arraySize];
    }

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }

        for (T t : this) {
            if (Objects.equals(t, o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentListIndex = -1;
        private int listsItemIndex = 0;
        private final int startModCount = modCount;

        public boolean hasNext() {
            while (lists[listsItemIndex] == null || lists[listsItemIndex].isEmpty()) {
                listsItemIndex++;
            }

            if (currentListIndex + 1 < lists[listsItemIndex].size()) {
                return true;
            }

            for (int i = listsItemIndex + 1; i < lists.length; i++) {
                if (lists[i] != null && !lists[i].isEmpty()) {
                    return true;
                }
            }

            return false;
        }

        public T next() {
            if (startModCount != modCount) {
                throw new ConcurrentModificationException("Хеш-таблица была изменена во время итерирования");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В Хеш-таблице нет такого элемента");
            }

            if (currentListIndex != lists[listsItemIndex].size() - 1) {
                ++currentListIndex;
                return lists[listsItemIndex].get(currentListIndex);
            }

            do {
                listsItemIndex++;
            } while (lists[listsItemIndex] == null || lists[listsItemIndex].isEmpty());

            currentListIndex = 0;

            return lists[listsItemIndex].get(currentListIndex);
        }
    }

    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[]{};
        }

        Object[] objects = new Object[size()];
        int i = 0;

        for (ArrayList<T> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            for (T t : list) {
                objects[i] = t;
                i++;
            }
        }

        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        int i = 0;

        for (ArrayList<T> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            for (T t : list) {
                //noinspection unchecked
                array[i] = (T1) t;
                i++;
            }
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(T item) {
        int index = Math.abs(item.hashCode() % lists.length);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);
        modCount++;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }

        for (ArrayList<T> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            if (list.remove(o)) {
                modCount++;
                size--;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }

        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }

        for (T item : c) {
            if (!add(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }

        boolean wasRemoved = false;

        for (ArrayList<T> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            int listSizeBefore = list.size();

            if (list.removeAll(c)) {
                wasRemoved = true;
            }

            size -= listSizeBefore - list.size();
            modCount++;
        }

        return wasRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }

        boolean wasRetained = false;

        for (ArrayList<T> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            int listSizeBefore = list.size();

            if (list.retainAll(c)) {
                wasRetained = true;
            }

            size -= listSizeBefore - list.size();
            modCount++;
        }

        return wasRetained;
    }

    @Override
    public void clear() {
        Arrays.fill(lists, null);
        size = 0;
        modCount++;
    }
}