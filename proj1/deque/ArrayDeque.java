package deque;

public class ArrayDeque<Freetype> {
    private int size;
    private Freetype[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Freetype[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    /* add an element to the last of the deque **/
    public void addLast(Freetype x) {
        //if (size == items.length) {
        //resize();
        //}
        items[nextLast] = x;
        nextLast += 1;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        size = size + 1;
    }

    /* add an element to the front of an deque **/
    public void addFirst(Freetype x) {
        //if (size == items.length) {
        //resize();
        //}
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
    public Freetype removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Freetype removal = this.get(0);
        nextFirst = nextFirst + 1;
        if (nextFirst >= items.length) {
            nextFirst = 0;
        }
        items[nextFirst] = null;
        size = size - 1;
        return removal;
    }

    /* remove and return the last element of a deque **/
    public Freetype removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Freetype removal = this.get(size - 1);
        nextLast = nextLast - 1;
        if (nextLast < 0) {
            nextLast += items.length;
        }
        items[nextLast] = null;
        size = size - 1;
        return removal;
    }

    /* get the ith element of the deque **/
    public Freetype get(int i) {
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
