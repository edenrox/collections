package com.hopkins.collections;

final class TreeMapEntryIterator<K, V> implements Iterator<Map.Entry<K, V>> {
  private TreeMap.LeafTreeNode<K, V> next;

  public TreeMapEntryIterator(TreeMap.TreeNode<K> root) {
    this.next = findFirstLeaf(root);
  }

  private TreeMap.LeafTreeNode<K, V> findFirstLeaf(TreeMap.TreeNode<K> node) {
    if (node == null) {
      return null;
    } else if (node.isLeafNode()) {
      return (TreeMap.LeafTreeNode<K, V>) node;
    } else {
      TreeMap.InnerTreeNode<K> innerNode = (TreeMap.InnerTreeNode<K>) node;
      return findFirstLeaf(innerNode.left);
    }
  }

  private TreeMap.LeafTreeNode<K, V> findNextLeaf(TreeMap.InnerTreeNode<K> node, TreeMap.TreeNode<K> lastNode) {
    if (node == null) {
      // We've traversed the entire tree
      return null;
    } else {
      if (node.left == lastNode) {
        // We've traversed the left node, on to the middle node
        return findFirstLeaf(node.middle);
      } else if (node.middle == lastNode && node.right != null) {
        // We've traversed the middle node, on to the right node
        return findFirstLeaf(node.right);
      } else {
        // We've traversed this node, on to the parent
        return findNextLeaf(node.getParent(), node);
      }
    }
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public Map.Entry<K, V> next() {
    Map.Entry<K, V> entry = next;
    next = findNextLeaf(next.getParent(), next);
    return entry;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
