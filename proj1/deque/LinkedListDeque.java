package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private DNode sentinel;
    private int size;

    public class DNode {
        private T item;
        private DNode next;

        private DNode prev;
        public DNode(T i, DNode n, DNode m) {
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

    public LinkedListDeque(T x) {
        sentinel = new DNode(x, null,  null);
        sentinel.next = new DNode(x, null, sentinel);
        sentinel.prev = sentinel;
        size = 1;
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
        DNode iter = sentinel.next;
        T item = null;
        while (index >= 0) {
            item = iter.item;
            iter = iter.next;
        }
        return item;
    }
}
