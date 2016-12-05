package com.hopkins.collections;

/** A {@link Map} implemented with a 2-3 tree. */
public class TreeMap<K, V> implements Map<K, V> {
    private final Comparator<K> comparator;
    private TreeNode root;
    private int size;
    
    public TreeMap() {
        this.comparator = ComparableComparator.INSTANCE;
    }
    
    public TreeMap(Comparator<K> comparator) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        this.comparator = comparator;
    }
    
    public TreeMap(Map<? extends K, ? extends V> map) {
        this.comparator = ComparableComparator.INSTANCE;
        putAll(map);
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (root == null) {
            return false;
        }
        LeafTreeNode<K, V> node = findNode(root, (K) key);
        return node.key.equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        
    }
    
    private LeafTreeNode<K, V> findNode(TreeNode<K> node, K key) {
        if (node.isLeafNode()) {
            return (LeafTreeNode<K, V>) node;
        } else {
            InnerTreeNode<K> innerNode = (InnerTreeNode<K>) node;
            if (comparator.compare(innerNode.leftPivot, key) < 0) {
                return findNode(innerNode.left, key);
            } else if (innerNode.rightPivot == null 
                    || comparator.compare(innerNode.rightPivot, key) < 0) {
                return findNode(innerNode.middle, key);
            } else {
                return findNode(innerNode.right, key);
            }
        }
    }

    @Override
    public V get(Object key) {
        if (root == null) {
            return null;
        }
        LeafTreeNode<K, V> node = findNode(root, (K) key);
        return node.key.equals(key) ? node.value : null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        
    }

    @Override
    public V put(K key, V value) {
        if (root == null) {
            root = new LeafTreeNode(key, value);
            return null;
        } else {
            LeafTreeNode<K, V> node = findNode(root, key);
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            } else {
                // TODO(ianhopkins): split the leaf node
                LeafTreeNode<K, V> newNode = new LeafTreeNode<>(key, value);
                if (node.parent == null) {
                    
                } else {
                    
                }
                
                return null;
            }
        }
    }
    
    private void addToNode(TreeNode<K> destNode, TreeNode<K> newNode) {
        TreeNode<K> parent = destNode.getParent();
        if (destNode.isLeafNode()) {
            if (parent == null) {
                // The destNode is the only node in the tree, make a new root
                InnerTreeNode<K> newRoot = new InnerTreeNode<>();
                newRoot.left = destNode;
                newRoot.leftPivot = ((LeafTreeNode<K, V>) newNode).key;
                newRoot.middle = newNode;
                root = newRoot;
            } else {
                // Add the new node to the parent
                addToNode(parent, newNode);
            }
        } else {
            InnerTreeNode<K> innerNode = (InnerTreeNode<K>) destNode;
            if (innerNode.right == null) {
                // the destNode has room
            } else if (innerNode.parent == null) {
                // the destNode is the root, make a new root
                InnerTreeNode<K> newRoot = new InnerTreeNode<>();
                InnerTreeNode<K> newRightNode = new InnerTreeNode<>();
                newRoot.left = innerNode;
                newRoot.leftPivot = innerNode.rightPivot;
                newRoot.right = newRightNode;
                
                newRightNode.left = innerNode.right;
            }
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<? extends K, ? extends V> entry = iter.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        LeafTreeNode<K, V> node = findNode(root, (K) key);
        if (node == null) {
            return null;
        } else {
            V oldValue
        }
        
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Collection<V> values() {
        
    }
    
    interface TreeNode<K> {
        InnerTreeNode<K> getParent();
        boolean isLeafNode();
    }
    
    static final class InnerTreeNode<K> implements TreeNode {
        
        InnerTreeNode<K> parent;
        TreeNode<K> left;
        K leftPivot;
        TreeNode<K> middle;
        K rightPivot;
        TreeNode<K> right;
        
        @Override
        public InnerTreeNode<K> getParent() {
            return parent;
        }

        @Override
        public boolean isLeafNode() {
            return false;
        }
    }
    
    static final class LeafTreeNode<K,V> implements TreeNode {
        InnerTreeNode<K> parent;
        K key;
        V value;
        
        LeafTreeNode(K key, V value) {
            if (key == null) {
                throw new NullPointerException();
            }
            this.key = key;
            this.value = value;
        }

        @Override
        public InnerTreeNode getParent() {
            return parent;
        }
        
        @Override
        public boolean isLeafNode() {
            return true;
        }
    }
    
    static final class TreeMapEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        
        TreeMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
