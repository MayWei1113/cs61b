package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private DNode sentinel;
    private int size;

    private class DNode {
        private T item;
        private DNode next;

        private DNode prev;
        private DNode(T i, DNode n, DNode m) {
            item = i;
            next = n;
            prev = m;
        }
    }

    public LinkedListDeque() {
        sentinel = new DNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        sentinel.next = new DNode(x, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    public void addLast(T x) {
        sentinel.prev = new DNode(x, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size = size + 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DNode tmp = sentinel.next;
        while (tmp.item != null) {
            System.out.print(tmp.item.toString());
            System.out.print(" ");
            tmp = tmp.next;
        }
        System.out.print("\n");

    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return first;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return last;

    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        DNode iter = sentinel.next;
        T item = null;
        while (index >= 0) {
            item = iter.item;
            iter = iter.next;
            index = index - 1;
        }
        return item;
    }

    private T getRecursiveHelper(int index, DNode sNode) {
        if (index == 0) {
            return sNode.item;
        }
        sNode = sNode.next;
        return getRecursiveHelper(index - 1, sNode);
    }

    public T getRecursive(int index) {
        if (index >= size | index < 0) {
            return null;
        }
        DNode iter = sentinel.next;
        if (index == 0) {
            T item = iter.item;
            return item;
        }
        index = index - 1;
        return getRecursiveHelper(index, iter.next);
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;
        private DNode itera;
        private T returnItem;

        public LinkedListIterator() {
            wizPos = 0;
            returnItem = null;
            itera = sentinel.next;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {

            returnItem = itera.item;
            itera = itera.next;
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) instanceof Deque) {
                Deque<T> temp = (Deque<T>) this.get(i);
                if (!(temp.equals(other.get(i)))) {
                    return false;
                }
            }
            else if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }
}

