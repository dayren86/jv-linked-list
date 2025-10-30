package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> headNode;
    private Node<T> tailNode;
//    private int size = 0;

    @Override
    public void add(T value) {
        if (headNode == null) {
            Node<T> tNode = new Node<>(null, value, null);
            headNode = tNode;
            tailNode = tNode;
        } else {
            Node<T> tNode = new Node<>(tailNode, value, null);
            tailNode.nextNode = tNode;
            tailNode = tNode;
        }
    }

    @Override
    public void add(T value, int index) {
        if (headNode == null) {
            Node<T> tNode = new Node<>(null, value, null);
            headNode = tNode;
            tailNode = tNode;
        } else {
            Node<T> nodeByIndex = findNodeByIndex(index);
            if (nodeByIndex == null) {
                add(value);
            } else if (index == 0) {
                Node<T> tNode = new Node<>(nodeByIndex.prevNode, value, nodeByIndex);
                nodeByIndex.prevNode = tNode;
                headNode = tNode;
            }else {
                Node<T> tNode = new Node<>(nodeByIndex.prevNode, value, nodeByIndex);
                nodeByIndex.prevNode = tNode;

            }
        }
    }

    @Override
    public void addAll(List<T> list) {
    }

    @Override
    public T get(int index) {
        return findNodeByIndex(index).value;
    }

    @Override
    public T set(T value, int index) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean remove(T object) {
        return false;
    }

    @Override
    public int size() {
        int countSize = 0;
        Node<T> findLastNode = headNode;
        while (findLastNode.nextNode != null){
            countSize++;
        }
        return countSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private Node<T> findNodeByIndex(int index) {
//        if (index > size()) {
//            throw new IndexOutOfBoundsException("index " + index);
//        }

        int startIndex = 0;
        Node<T> findNode = headNode;
        while (startIndex != index) {
            findNode = findNode.nextNode;
            startIndex++;
        }
        return findNode;
    }

    static class Node<T> {
        private Node<T> prevNode;
        private T value;
        private Node<T> nextNode;

        public Node(Node<T> prevNode, T value, Node<T> nextNode) {
            this.prevNode = prevNode;
            this.value = value;
            this.nextNode = nextNode;
        }
    }
}
