package hashmap;

import java.util.*;

/**
 *  A hash buckets-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    double maxLoad = 0.75;
    int N; // number of element
    int M = 16; // number of buckets
    Set hashSet = new HashSet();

    /** Constructors */
    public MyHashMap() {
        this.buckets = createbuckets(this.M);
        this.N = 0;
    }

    public MyHashMap(int initialSize) {
        this.M = initialSize;
        this.buckets = createbuckets(initialSize);
        this.N = 0;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.buckets = createbuckets(initialSize);
        this.M = initialSize;
        this.maxLoad = maxLoad;
        this.N = 0;
    }

    /**
     * Returns a new node to be placed in a hash buckets bucket
     */
    private Node createNode(K key, V value) {
        Node n = new Node(key, value);
        return n;
    }

    /**
     * Returns a data structure to be a hash buckets bucket
     *
     * The only requirements of a hash buckets bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a buckets to back our hash buckets. As per the comment
     * above, this buckets can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A buckets SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param bucketsSize the size of the buckets to create
     */
    private Collection<Node>[] createbuckets(int bucketsSize) {
        Collection<Node>[] newBuckets = new Collection[bucketsSize];
        for (int i = 0; i < bucketsSize; i++) {
            newBuckets[i] = createBucket();
        }
        return newBuckets;
    }

    @Override
    public void clear() {
        this.buckets = createbuckets(this.M);
        this.N = 0;
    }

    @Override
    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        int index = getIndex(key);
        Collection<Node> bucket = this.buckets[index];
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int index = getIndex(key);
        Collection<Node> bucket = this.buckets[index];
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.N;
    }

    @Override
    public void put(K key, V value) {
        Node n = createNode(key, value); // create a new node
        int putIndex = getIndex(key); // find out its position

        Collection<Node> bucket = this.buckets[putIndex];
        // if the key is already in the map, replace the value
        Boolean exist = false;
        for (Node s : bucket) {
            if (s.key.equals(key)) {
                s.value = value;
                exist = true;
                break;
            }
        }
        if (!exist) {
            bucket.add(n);
            this.N += 1;
        }
        // To do: resize
        double load = (double) (this.N + 1) / this.M;
        if (load >= maxLoad) {
            resize();
        }
    }

    private void resize() {
        Collection[] oldbuckets = this.buckets;
        int oldSize = this.M;
        this.M = oldSize * 2; // double its length
        this.N = 0;
        Collection[] newbuckets = createbuckets(this.M);
        this.buckets = newbuckets;
        for (int i = 0; i < oldSize; i++) {
            Collection<Node> bucket = oldbuckets[i];
            for (Node n : bucket) {
                this.put(n.key, n.value);
            }
        }
    }
    private int getIndex(K key) {
        int keyCode = key.hashCode();
        int putIndex = Math.floorMod(keyCode, this.M);
        return putIndex;
    }

    @Override
    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> newSet = new HashSet<K>();
        for (int i = 0; i < this.M; i++) {
            Collection<Node> bucket = this.buckets[i];
            for (Node n : bucket) {
                newSet.add(n.key);
            }
        }
        return newSet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
