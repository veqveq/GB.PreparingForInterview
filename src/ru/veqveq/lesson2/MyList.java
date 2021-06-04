package ru.veqveq.lesson2;

public abstract class MyList<E> {
    protected int size;

    public abstract boolean contains(E object);

    public abstract void print();

    public abstract void remove(E object);

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
