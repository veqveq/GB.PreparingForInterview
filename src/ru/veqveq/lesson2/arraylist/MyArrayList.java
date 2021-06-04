package ru.veqveq.lesson2.arraylist;

public interface MyArrayList<E> {
    void add(E object);

    E get(int index);

    int indexOf(E object);

    void remove(int index);
}
