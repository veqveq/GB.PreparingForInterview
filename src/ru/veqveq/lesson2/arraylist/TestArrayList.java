package ru.veqveq.lesson2.arraylist;

public class TestArrayList {
    public static void main(String[] args) {
        MyArrayMyListImpl<Integer> arrayList = new MyArrayMyListImpl<>(2);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.print();
        arrayList.remove(3);
        arrayList.print();
        arrayList.remove(new Integer(4));
        arrayList.print();
        System.out.println(arrayList.contains(2));
        System.out.println(arrayList.contains(4));
    }
}
