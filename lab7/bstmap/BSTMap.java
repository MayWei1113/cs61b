package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, Value> implements Map61B {
     private BSTMapNode node;

    public BSTMap(K K, Value V) {
        this.node = new BSTMapNode(K, V);
    }

    public BSTMap() {
        this.node = null;
    }

    private class BSTMapNode {
        K key;
        Value v;
        BSTMapNode left;
        BSTMapNode right;
        int size;


        private BSTMapNode(K k, Value v) {
            this.key = k;
            this.v = v;
            size = 0;
        }
    }
    @Override
    public void clear() {
        this.node = null;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsKey(Comparable key) {
        return containsKey((K) key, this.node);
    }

    private boolean containsKey(K key, BSTMapNode n) {
        if (n == null) {
            return false;
        }
        if (key.equals(n.key)) {
            return true;
        }
        else if (key.compareTo(n.key) < 0) {
            return containsKey(key, n.right);
        }
        else if (key.compareTo(n.key) > 0){
            return containsKey(key, n.left);
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        return get((K) key, this.node);
    }

    private Value get(K key, BSTMapNode n) {
        if (n == null) {
            return null;
        }
        if (key.equals(n.key)) {
            return n.v;
        }
        else if (key.compareTo(n.key) < 0) {
            return get(key, n.right);
        }
        else if (key.compareTo(n.key) > 0){
            return get(key, n.left);
        }
        return null;
    }

    @Override
    public int size() {
        if (this.node == null) {
            return 0;
        }
        return this.node.size;
    }

    @Override
    public void put(Object key, Object value) {
        this.node = put((K) key, (Value) value, this.node);
    }

    private BSTMapNode put(K key, Value v, BSTMapNode n) {
        if (n == null) {
            n = new BSTMapNode(key, v);
        }
        if (key.compareTo(n.key) > 0) {
            n.left = put(key, v, n.left);
        }
        if (key.compareTo(n.key) < 0) {
            n.right = put(key, v, n.right);
        }
        n.size += 1;
        return n;
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printInOrder(this.node);
    }

    private void printInOrder(BSTMapNode n) {
        if (n == null) {
            System.out.print("");
        }
        else {
            printInOrder(n.left);
            System.out.print(n.key);
            printInOrder(n.right);
        }
    }
}
