package ru.veqveq.lesson2.linkedlist;

import ru.veqveq.lesson2.MyList;

public class MyLinkedListImpl<E extends Comparable<? super E>>
        extends MyList<E>
        implements MyLinkedList<E> {
    private Node<E> first;
    private Node<E> last;

    @Override
    public boolean contains(E object) {
        if (isEmpty()) return false;
        Node<E> current = first;
        while (current != null) {
            if (current.getItem().equals(object)) return true;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void print() {
        Node<E> current = first;
        StringBuilder sb = new StringBuilder("[");
        while (current != null && current.getNext() != null) {
            sb.append(current.getItem())
                    .append(", ");
            current = current.getNext();
        }
        if (current != null) sb.append(current.getItem());
        sb.append("]");
        System.out.println(sb.toString());
    }

    @Override
    public void remove(E object) {
        if (!contains(object)) return;
        Node<E> current = first;
        while (current != null) {
            if (current.getItem().equals(object)) {
                current.remove();
                size--;
                return;
            }
            current = current.getNext();
        }
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
    public E getFirst() {
        return first.getItem();
    }

    @Override
    public E getLast() {
        return last.getItem();
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        E current = first.remove();
        if (size != 1) {
            first = first.getNext();
        } else {
            first = null;
            last = null;
        }
        size--;
        return current;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        E current = last.remove();
        if (size != 1) {
            last = last.getPrevious();
        } else {
            first = null;
            last = null;
        }
        size--;
        return current;
    }

    @Override
    public void insertFirst(E object) {
        Node<E> newNode;
        if (isEmpty()) {
            newNode = new Node<>(object, null, first);
            last = newNode;
        } else {
            newNode = first.insertBefore(object);
        }
        first = newNode;
        size++;
    }

    @Override
    public void insertLast(E object) {
        Node<E> newNode;
        if (isEmpty()) {
            newNode = new Node<>(object, last, null);
            first = newNode;
        } else {
            newNode = last.insertAfter(object);
        }
        last = newNode;
        size++;
    }

    @Override
    public Node<E> getFirstNode() {
        return first;
    }

    @Override
    public Node<E> getLastNode() {
        return last;
    }
}
