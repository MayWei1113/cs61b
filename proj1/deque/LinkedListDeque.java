package deque;

public class LinkedListDeque<freetype> {
    private DNode sentinel;
    public int size;

    public class DNode{
        public freetype item;
        public DNode next;

        public DNode prev;
        public DNode(freetype i, DNode n, DNode m){
            item = i;
            next = n;
            prev = m;
        }
    }

    public LinkedListDeque(){
        sentinel = new DNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(freetype x){
        sentinel = new DNode(x,null, null);
        sentinel.next = new DNode(x,null,sentinel);
        sentinel.prev = sentinel;
        size = 1;
    }

    public void addFirst(freetype x){
        sentinel.next = new DNode(x,sentinel.next,sentinel);
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    public void addLast(freetype x){
        sentinel.prev = new DNode(x,sentinel,sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size = size + 1;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        DNode tmp = sentinel.next;
        while(tmp.item != null){
            System.out.print(tmp.item.toString());
            System.out.print(" ");
            tmp = tmp.next;
        }

    }

    public freetype removeFirst(){
        if(this.isEmpty()){
            return null;
        }
        freetype first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size -1;
        return first;
    }

    public freetype removeLast(){
        if(this.isEmpty()){
            return null;
        }
        freetype last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size -1;
        return last;

    }

    public freetype get(int index) {
        DNode iter = sentinel.next;
        freetype item = null;
        while (index >= 0) {
            item = iter.item;
            iter = iter.next;
        }
        return item;
    }
}
