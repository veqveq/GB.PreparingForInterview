package ru.veqveq.lesson2.linkedlist;

public interface MyLinkedList<E> {
    E getFirst();

    E getLast();

    E removeFirst();

    E removeLast();

    void insertFirst(E object);

    void insertLast(E object);

    Node<E> getFirstNode();

    Node<E> getLastNode();

    class Node<E>{
        private final E item;
        private Node<E> previous;
        private Node<E> next;

        public Node(E item, Node<E> previous, Node<E> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }

        public E getItem() {
            return item;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public E remove() {
            if (previous != null) previous.setNext(next);
            if (next != null) next.setPrevious(previous);
            return getItem();
        }

        public Node<E> insertAfter(E item) {
            Node<E> newNode = new Node<>(item, this, next);
            if (next != null) next.setPrevious(newNode);
            this.setNext(newNode);
            return newNode;
        }

        public Node<E> insertBefore(E item) {
            Node<E> newNode = new Node<>(item, previous, this);
            if (previous != null) previous.setNext(newNode);
            this.setPrevious(newNode);
            return newNode;
        }
    }
}
