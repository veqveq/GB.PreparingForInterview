package ru.veqveq.lesson2.arraylist;

import ru.veqveq.lesson2.MyList;

import java.util.Arrays;

public class MyArrayMyListImpl<E extends Comparable<? super E>>
        extends MyList<E>
        implements MyArrayList<E> {
    private static final int DEFAULT_SIZE = 10;

    private E[] data;

    public MyArrayMyListImpl() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public MyArrayMyListImpl(int size) {
        this.data = (E[]) new Comparable[size];
    }

    @Override
    public void add(E object) {
        checkAndGrow();
        data[size++] = object;
    }

    @Override
    public E get(int index) {
        if (checkIndex(index))
            throw new IndexOutOfBoundsException(
                    String.format("Incorrect index value: %d. Array size: %d", index, size));
        return data[index];
    }

    @Override
    public int indexOf(E object) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(object)) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(E object) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(object)) return true;
        }
        return false;
    }

    @Override
    public void remove(int index) {
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        data[--size] = null;
    }

    @Override
    public void remove(E object) {
        remove(indexOf(object));
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        sb.append(data[size - 1]).append("]");
        System.out.println(sb.toString());
    }

    private void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, recalcSize());
        }
    }

    private int recalcSize() {
        return size == 0 ? 1 : size * 2;
    }

    private boolean checkIndex(int index) {
        return index < 0 || index >= size;
    }
}
