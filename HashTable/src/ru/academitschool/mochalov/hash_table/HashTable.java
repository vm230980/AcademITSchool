package ru.academitschool.mochalov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static final int DEFAULT_ARRAY_SIZE = 5;

    private ArrayList<T>[] lists;
    private int size = 0;
    private int modCount = 0;

    public HashTable() {
        this(DEFAULT_ARRAY_SIZE);
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

        int index = Math.abs(Objects.hashCode(o) % lists.length);

        if (lists[index] == null || lists[index].isEmpty()) {
            return false;
        }

        return lists[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<T> {
        private int currentListIndex = -1;
        private int count = 0;
        private Iterator<T> listIterator;
        private final int startModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (modCount != startModCount) {
                throw new ConcurrentModificationException("Хэштаблица была изменена во время итерирования");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В Хеш-таблице нет такого элемента");
            }

            if (listIterator == null || !listIterator.hasNext()) {
                currentListIndex++;
                listIterator = null;
            }

            while (lists[currentListIndex] == null || lists[currentListIndex].isEmpty()) {
                currentListIndex++;
            }

            if (listIterator == null) {
                listIterator = lists[currentListIndex].listIterator();
            }

            count++;

            return listIterator.next();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size()];
        int i = 0;

        for (T item : this) {
            objects[i] = item;
            i++;
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

        for (T item : this) {
            //noinspection unchecked
            array[i] = (T1) item;
            i++;
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(T item) {
        int index = Math.abs(Objects.hashCode(item) % lists.length);

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

        int index = Math.abs(Objects.hashCode(o) % lists.length);

        if (lists[index] == null || lists[index].isEmpty()) {
            return false;
        }

        if (lists[index].remove(o)) {
            modCount++;
            size--;

            return true;
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

        if (c.isEmpty()) {
            return false;
        }

        boolean wasAdded = false;

        for (T item : c) {
            wasAdded = add(item);
        }

        return wasAdded;
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

        boolean wasDeleted = false;

        for (ArrayList<T> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            int listSizeBefore = list.size();

            if (list.retainAll(c)) {
                wasDeleted = true;
            }

            size -= listSizeBefore - list.size();
            modCount++;
        }

        return wasDeleted;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        Arrays.fill(lists, null);
        size = 0;
        modCount++;
    }
}