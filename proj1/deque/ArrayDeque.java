package deque;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    public int startInd() {
        if (nextFirst == items.length - 1) {
            return 0;
        }
        return nextFirst + 1;
    }

    public int endInd() {
        if (nextLast == 0) {
            return items.length - 1;
        }
        return nextLast - 1;
    }

    /* resize the deque if items.length == size **/
    public void resize(int len) {
        T[] newItems = (T[]) new Object[len];
        int start = startInd();
        int end = endInd();
        if (end >= start) {
            System.arraycopy(items, start, newItems, 0, size);
        } else {
            System.arraycopy(items, start, newItems, 0, size - start);
            System.arraycopy(items, 0, newItems, size - start + 1, nextLast);
        }
        nextLast = size;
        nextFirst = len - 1;
        items = newItems;
    }

    /* add an element to the last of the deque **/
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast += 1;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        size = size + 1;
    }

    /* add an element to the front of an deque **/
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst -= 1;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        size = size + 1;
    }

    /* find out if the deque is empty **/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* return the size of the deque **/
    public int size() {
        return size;
    }

    /* print the deque **/
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(this.get(i));
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    /* remove the first element of a deque **/
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        if (size <= items.length/4 & size >= 16) {
            resize(items.length/4);
        }
        T removal = this.get(0);
        nextFirst = nextFirst + 1;
        if (nextFirst >= items.length) {
            nextFirst = 0;
        }
        items[nextFirst] = null;
        size = size - 1;
        return removal;
    }

    /* remove and return the last element of a deque **/
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        if (size <= items.length/4 & size >= 16) {
            resize(items.length/4);
        }
        T removal = this.get(size - 1);
        nextLast = nextLast - 1;
        if (nextLast < 0) {
            nextLast += items.length;
        }
        items[nextLast] = null;
        size = size - 1;
        return removal;
    }

    /* get the ith element of the deque **/
    public T get(int i) {
        if (this.isEmpty()) {
            return null;
        }
        int index = i + nextFirst + 1;
        if (index >= items.length) {
            index = index - items.length;
        }
        return items[index];
    }


}
