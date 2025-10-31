package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> headNode;
    private Node<T> tailNode;

    @Override
    public void add(T value) {
        if (headNode == null) {
            Node<T> newNode = new Node<>(null, value, null);
            headNode = newNode;
            tailNode = newNode;
        } else {
            Node<T> newNode = new Node<>(tailNode, value, null);
            tailNode.nextNode = newNode;
            tailNode = newNode;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }

        if (headNode == null) {
            Node<T> newNode = new Node<>(null, value, null);
            headNode = newNode;
            tailNode = newNode;
        } else {
            Node<T> nodeByIndex = findNodeByIndex(index);

            if (nodeByIndex == null) {
                Node<T> newNode = new Node<>(tailNode.prevNode, value, null);
                tailNode.nextNode = newNode;
                tailNode = newNode;
            } else if (nodeByIndex.equals(headNode)) {
                Node<T> newNode = new Node<>(null, value, nodeByIndex);
                nodeByIndex.prevNode = newNode;
                headNode = newNode;
            } else {
                Node<T> newNode = new Node<>(nodeByIndex.prevNode, value, nodeByIndex);
                nodeByIndex.prevNode.nextNode = newNode;
                nodeByIndex.prevNode = newNode;
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (T t : list) {
            add(t);
        }
    }

    @Override
    public T get(int index) {
        if (index > size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }

        int startIndex = 0;
        Node<T> findNode = headNode;

        while (startIndex != index) {
            findNode = findNode.nextNode;
            startIndex++;
        }

        return findNode.value;
    }

    @Override
    public T set(T value, int index) {
        Node<T> nodeByIndex = findNodeByIndex(index);
        if (nodeByIndex == null || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
        T value1 = nodeByIndex.value;

        nodeByIndex.value = value;

        return value1;
    }

    @Override
    public T remove(int index) {
        Node<T> nodeByIndex = findNodeByIndex(index);

        if (nodeByIndex == null) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }

        if (index == 0 && size() == 1) {
            headNode = null;
        } else if (nodeByIndex.equals(headNode)) {
            headNode.nextNode.prevNode = null;
            headNode = headNode.nextNode;
        } else if (nodeByIndex.equals(tailNode)) {
            tailNode.prevNode.nextNode = null;
            tailNode = tailNode.prevNode;
        } else {
            Node<T> prevNode = nodeByIndex.prevNode;
            Node<T> nextNode = nodeByIndex.nextNode;

            prevNode.nextNode = nextNode;
            nextNode.prevNode = prevNode;
        }

        return nodeByIndex.value;
    }

    @Override
    public boolean remove(T object) {
        int indexNode = 0;
        Node<T> findNode = headNode;
        boolean ifFindNode = false;

        while (findNode.nextNode != null) {
            T value = findNode.value;
            if (value == object || value != null && value.equals(object)) {
                ifFindNode = true;
                break;
            }
            findNode = findNode.nextNode;
            indexNode++;
        }

        if (ifFindNode || headNode.nextNode == null) {
            remove(indexNode);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        int countSize = 0;
        Node<T> findLastNode = headNode;

        if (findLastNode == null) {
            return countSize;
        }

        while (findLastNode.nextNode != null) {
            findLastNode = findLastNode.nextNode;
            countSize++;
        }

        countSize++;
        return countSize;
    }

    @Override
    public boolean isEmpty() {
        return headNode == null;
    }

    private Node<T> findNodeByIndex(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
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
