package com.example.algorithms.search;

/**
 * Created by fox.hu on 2019/1/3.
 */

public class BinaryTreeST<K extends Comparable<K>, V> implements SymbolTable<K, V> {
    private class Node {
        /**
         * 键
         */
        private K key;
        /**
         * 值
         */
        private V value;
        /**
         * 左右子节点
         */
        private Node left, right;
        /**
         * 以节点为根的子树中的总结点数
         */
        private int sonNum;

        public Node(K key, V value, int sonNum) {
            this.key = key;
            this.value = value;
            this.sonNum = sonNum;
        }
    }

    private Node root;

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private Node put(K key, V value, Node node) {
        //递归调用
        //如果没有该节点 则在适当的位置新建
        if (node == null) {
            return new Node(key, value, 1);
        }
        //比较key和node的值 如果key大则递归node的右子树 小则递归node的左子树 相等则替换该节点value
        //最后更新node的sonnum值
        final int i = key.compareTo(node.key);
        if (i > 0) {
            put(key, value, node.right);
        } else if (i < 0) {
            put(key, value, node.left);
        } else {
            node.value = value;
        }
        node.sonNum = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        //如果key比node中的key大 则在node的右子树中遍历 如果小 则在node的左子树遍历
        final int i = key.compareTo(node.key);
        if (i > 0) {
            return get(node.right, key);
        } else if (i < 0) {
            return get(node.left, key);
        } else {
            return node.value;
        }
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
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.sonNum;
        }
    }

    @Override
    public K min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    @Override
    public K max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public K floor(K key) {
        final Node floor = floor(key, root);
        if (floor == null) {
            return null;
        } else {
            return floor.key;
        }
    }

    private Node floor(K key, Node node) {
        if (node == null) {
            return null;
        }
        final int i = key.compareTo(node.key);
        if (i == 0) {
            return node;
        }

        if (i < 0) {
            return floor(key, node.left);
        }

        final Node right = floor(key, node.right);
        if (right != null) {
            return right;
        } else {
            return node;
        }
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
