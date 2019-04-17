package com.example.algorithms.search;

public class BlackRedTree<K extends Comparable<K>, V> implements SymbolTable<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        K key;
        V val;
        Node left;
        Node right;
        int N; //这棵树的总节点数
        boolean color; //是否是红节点

        public Node(K key, V val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private int size(Node left) {
        return 0;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, K key, V value) {
        if (h == null) {
            return new Node(key, value, 1, RED); //和父节点是红链接相连
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.val = value;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public K min() {
        return null;
    }

    @Override
    public K max() {
        return null;
    }

    @Override
    public K floor(K key) {
        return null;
    }

    @Override
    public K ceiling(K key) {
        return null;
    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public K select(int k) {
        return null;
    }
}
