package deque;

public class LinkedListDeque<Freetype> {
    private DNode sentinel;
    private int size;

    public class DNode {
        private Freetype item;
        private DNode next;

        private DNode prev;
        public DNode(Freetype i, DNode n, DNode m) {
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

    public LinkedListDeque(Freetype x) {
        sentinel = new DNode(x, null,  null);
        sentinel.next = new DNode(x, null, sentinel);
        sentinel.prev = sentinel;
        size = 1;
    }

    public void addFirst(Freetype x) {
        sentinel.next = new DNode(x, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    public void addLast(Freetype x) {
        sentinel.prev = new DNode(x, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size = size + 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
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

    public Freetype removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Freetype first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return first;
    }

    public Freetype removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Freetype last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return last;

    }

    public Freetype get(int index) {
        DNode iter = sentinel.next;
        Freetype item = null;
        while (index >= 0) {
            item = iter.item;
            iter = iter.next;
        }
        return item;
    }
}
