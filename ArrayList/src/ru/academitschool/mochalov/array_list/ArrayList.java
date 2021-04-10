package ru.academitschool.mochalov.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] items;
    private int size;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Переданное значение минимальной вместимости списка " + capacity + " должно быть >= 0");
        }

        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public ArrayList(Collection<? extends T> c) {
        //noinspection unchecked
        items = (T[]) new Object[c.size()];
        addAll(c);
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int startModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (startModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен во время итерирования");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В списке нет элемента с индексом " + (currentIndex + 1));
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(T item) {
        add(size, item);

        return true;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkCollection(c);

        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkCollection(c);

        checkIndex(index, false);

        if (c.size() == 0) {
            return false;
        }

        ensureCapacity(size + c.size());

        if (index < size) {
            System.arraycopy(items, index, items, index + c.size(), size - index);
        }

        int i = index;

        for (T item : c) {
            items[i] = item;
            i++;
        }

        size += c.size();
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkCollection(c);

        boolean wasRemoved = false;

        for (int i = 0; i < size; i++) {
            if (c.contains(items[i])) {
                remove(i);
                --i;
                wasRemoved = true;
            }
        }

        return wasRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkCollection(c);

        boolean wasRetained = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                --i;
                wasRetained = true;
            }
        }

        return wasRetained;
    }

    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, size, null);
        size = 0;
        modCount++;
    }

    public T get(int index) {
        checkIndex(index, true);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index, true);

        T oldItem = items[index];

        items[index] = item;
        return oldItem;
    }

    @Override
    public void add(int index, T item) {
        checkIndex(index, false);

        if (size >= items.length) {
            increaseCapacity();
        }

        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;
        ++modCount;
        ++size;
    }

    public T remove(int index) {
        checkIndex(index, true);

        T deletedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        --size;
        items[size] = null;
        ++modCount;

        return deletedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            //noinspection unchecked
            items = (T[]) new Object[DEFAULT_CAPACITY];
            return;
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void checkIndex(int index, boolean isAccessTry) {
        if (isAccessTry) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " + "Значение index должно быть в диапазоне {0, " + (size - 1) + "}.");
            }
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " + "Значение index должно быть в диапазоне {0, " + size + "}.");
        }
    }

    private static void checkCollection(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (T item : this) {
            stringBuilder.append(item).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");

        return stringBuilder.toString();
    }
}