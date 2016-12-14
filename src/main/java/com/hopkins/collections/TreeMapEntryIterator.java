package com.hopkins.collections;

final class TreeMapEntryIterator<K, V> implements Iterator<Map.Entry<K, V>> {
    private TreeMap.TreeNode<K> root;
    private TreeMap.LeafTreeNode<K, V> next;

    public TreeMapEntryIterator(TreeMap.TreeNode<K> root) {
        this.root = root;
        moveToNext();
    }
    
    private void moveToNext() {
        if (root == null) {
            return;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Map.Entry<K, V> next() {
        Map.Entry<K, V> entry = next;
        moveToNext();
        return entry;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
