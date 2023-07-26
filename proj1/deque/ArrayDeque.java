package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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

    private int startInd() {
        if (nextFirst == items.length - 1) {
            return 0;
        }
        return nextFirst + 1;
    }

    private int endInd() {
        if (nextLast == 0) {
            return items.length - 1;
        }
        return nextLast - 1;
    }

    /* resize the deque if items.length == size **/
    private void resize(int len) {
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
        if (size <= items.length / 4 & size >= 16) {
            resize(items.length / 4);
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
        if (size < items.length / 4 - 1 & size >= 16) {
            resize(items.length / 4);
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
    @Override
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


    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public class ArrayDequeIterator implements Iterator<T> {
        private int iterNext;
        private T returnItem;
        private int wizPos;
        private ArrayDequeIterator() {
            iterNext = startInd();
            returnItem = null;
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            returnItem = items[iterNext];
            iterNext = iterNext + 1;
            if (iterNext > items.length) {
                iterNext = iterNext - items.length;
            }
            wizPos = wizPos + 1;
            return returnItem;
        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }
}
