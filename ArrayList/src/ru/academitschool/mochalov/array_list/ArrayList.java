package ru.academitschool.mochalov.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private int minCapacity = 10;
    private T[] items;
    private int size;
    int modCount = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        items = (T[]) new Object[minCapacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int minCapacity) {
        if (minCapacity < 0) {
            throw new IllegalArgumentException("Переданное значение минимальной вместимости списка " + minCapacity +
                    "должно быть >= 0");
        }

        this.minCapacity = minCapacity;
        items = (T[]) new Object[minCapacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(List<T> asList) {
        items = (T[]) new Object[minCapacity];
        addAll(asList);
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
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
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
        private int currentIndex = -1;
        int tempModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            if (tempModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен во время итерации");
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
        Object[] objects = new Object[size];

        if (size >= 0) System.arraycopy(items, 0, objects, 0, size);

        return objects;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size)
            array[size] = null;
        return array;
    }

    @Override
    public boolean add(T item) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = item;
        ++size;
        ++modCount;

        return true;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                remove(i);
                ++modCount;

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
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }

        ensureCapacity(size + c.size());

        for (Object item : c) {
            add((T) item);
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + (size) + "}.");
        }

        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }

        ensureCapacity(size + c.size());

        if (index == size) {
            addAll(c);
        } else {
            for (Object item : c) {
                add(index, (T) item);
                ++index;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }

        for (Object item : c) {
            remove(item);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция не должна быть null");
        }

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(items[i]);
                --i;
            }
        }

        return true;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        ++modCount;
    }

    public T get(int index) {
        if (size == 0) {
            throw new IllegalArgumentException("Список пустой. Значение элемента получить невозможно.");
        }

        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + (size - 1) + "}.");
        }

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + (size - 1) + "}.");
        }

        T temp = items[index];

        items[index] = item;
        ++modCount;

        return temp;
    }

    @Override
    public void add(int index, T item) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + size + "}.");
        }

        if (index == size) {
            increaseCapacity();

            items[size] = item;
        } else {
            if (size >= items.length) {
                increaseCapacity();
            }

            System.arraycopy(items, index, items, index + 1, size - index);
            items[index] = item;
        }

        ++modCount;
        ++size;
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Переданное значение индекса " + index + " некорректно. " +
                    "Значение index должно быть в диапазоне {0, " + (size - 1) + "}.");
        }

        T temp = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        --size;
        ++modCount;

        return temp;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
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
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (T t : this) {
            stringBuilder.append(t).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");

        return stringBuilder.toString();
    }
}