package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Object node;

    private class BSTMapNode {
        K key;
        V v;
        BSTMapNode left;
        BSTMapNode right;
        int size = 0;

        private BSTMapNode(K k, V v) {
            this.key = k;
            this.v = v;
            size = 1;
        }
    }
    @Override
    public void clear() {
        this.node = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, (BSTMapNode) this.node);
    }


    private boolean containsKey(K key, BSTMapNode n) {
        if (n == null) {
            return false;
        }
        if (key.equals(n.key)) {
            return true;
        } else if (key.compareTo(n.key) > 0) {
            return containsKey(key, n.right);
        } else if (key.compareTo(n.key) < 0) {
            return containsKey(key, n.left);
        }
        return false;
    }

    @Override
    public V get(K key) {
        return get(key, (BSTMapNode) this.node);
    }

    private V get(K key, BSTMapNode n) {
        if (n == null) {
            return null;
        }
        if (key.equals(n.key)) {
            return n.v;
        } else if (key.compareTo(n.key) > 0) {
            return get(key, n.right);
        } else if (key.compareTo(n.key) < 0) {
            return get(key, n.left);
        }
        return null;
    }

    @Override
    public int size() {
        if (this.node == null) {
            return 0;
        }
        BSTMapNode n = (BSTMapNode) this.node;
        return n.size;
    }

    @Override
    public void put(K key, V value) {
        BSTMapNode n = put(key, value, (BSTMapNode) this.node);
        this.node = n;
    }

    private BSTMapNode put(K key, V v, BSTMapNode n) {
        if (n == null) {
            n = new BSTMapNode(key, v);
        }
        if (key.compareTo(n.key) < 0) {
            n.left = put(key, v, n.left);
            n.size += 1;
        }
        if (key.compareTo(n.key) > 0) {
            n.right = put(key, v, n.right);
            n.size += 1;
        }
        return n;
    }

    @Override
    public Set<K> keySet() {
        //throw new UnsupportedOperationException();
        Set<K> s = new java.util.HashSet<K>();
        s = keySet((BSTMapNode) this.node, s);
        return s;
    }

    private Set keySet(BSTMapNode n, Set s) {
        if (n == null) {
            return s;
        }
        s.addAll(keySet(n.left, s));
        s.add(n.key);
        s.addAll(keySet(n.right, s));
        return s;
    }

    @Override
    public V remove(K key) {
        V v = this.get(key);
        if (v != null) {
            BSTMapNode n = removeHelper(key, (BSTMapNode) this.node);
            this.node = n;
        }
        return v;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    private BSTMapNode removeHelper(K key, BSTMapNode n) {
        if (n.key.compareTo(key) > 0) {
            n.left = removeHelper(key, n.left);
            n.size -= 1;
        }
        if (n.key.compareTo(key) < 0) {
            n.right =  removeHelper(key, n.right);
            n.size -= 1;
        }
        if (n.key.compareTo(key) == 0) {
            if (n.left == null & n.right == null) {
                n = null;
            } else if (n.left == null) {
                n = n.right;
            } else if (n.right == null) {
                n = n.left;
            } else {
                BSTMapNode lRight = findLeftRight(n.left);
                K tempKey = lRight.key;
                V tempV = lRight.v;
                n = removeHelper(tempKey, n);
                n.key = tempKey;
                n.v = tempV;
                n.size -= 1;
            }

        }
        return n;

    }

    private BSTMapNode findLeftRight(BSTMapNode n) {
        if (n.right == null) {
            return n;
        }
        return findLeftRight(n.right);
    }


    @Override
    public Iterator<K> iterator() {
        //throw new UnsupportedOperationException();
        Set<K> s = this.keySet();
        return s.iterator();
    }

    public void printInOrder() {
        printInOrder((BSTMapNode) this.node);
    }

    private void printInOrder(BSTMapNode n) {
        if (n == null) {
            System.out.print("");
        } else {
            printInOrder(n.left);
            System.out.print(n.key);
            printInOrder(n.right);
        }
    }
}
