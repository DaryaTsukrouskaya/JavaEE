package by.teachmeskills.linkedList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringJoiner;

public class CustomLinkedList<T> {
    private int size;
    private Node<T> header;
    private Node<T> tail;

    CustomLinkedList() {
        size = 0;
        header = new Node<T>();
        tail = new Node<T>(null, null, header);
        header.next = tail;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;
    }

    public Node<T> getNode(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Element with such index cant be added to the list!");
        }
        Node<T> node;
        if (size - index > index) {
            node = header;
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    public void add(T element, int index) {
        Node<T> node = getNode(index);
        Node<T> newNode = new Node<>(element, node, node.prev);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;

    }

    public void add(T element) {
        add(element, size);
        size++;
    }

    public void addAll(Collection<? extends T> collection) {
        for (T element : collection) {
            add(element);
        }
    }

    public T get(int index) {
        Node<T> node = getNode(index);
        return node.getValue();
    }


    public void deleteElement(int index) {
        Node<T> node = getNode(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    public void clear() {
        size = 0;
        header = new Node<T>();
        tail = new Node<T>(null, null, header);
        header.next = tail;
    }

    public int getSize() {
        return size;
    }

    public boolean IsEmpty() {
        return size == 0;
    }

    public Object toArray() {
        Node<T> node = header.next;
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = node.value;
            node = node.next;
        }
        return array;
    }

    @Override
    public String toString() {
        Node<T> node = header.next;
        StringJoiner stringJoiner = new StringJoiner(", ", CustomLinkedList.class.getSimpleName() + "[", "]");
        while (node != tail) {
            stringJoiner.add((CharSequence) node.getValue());
            node = node.next;
        }
        return stringJoiner.toString();
    }
}
