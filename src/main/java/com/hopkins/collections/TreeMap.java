package com.hopkins.collections;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A {@link Map} implemented with a 2-3 tree.
 */
public class TreeMap<K, V> implements Map<K, V> {
  private static final AtomicInteger NEXT_NODE_ID = new AtomicInteger();

  private final Comparator<K> comparator;
  private final MapEntryIteratorFactory<K, V> iteratorFactory = new MapEntryIteratorFactory<K, V>() {
    @Override
    public Iterator<Entry<K, V>> newIterator() {
      return new TreeMapEntryIterator<>(root);
    }
  };

  private TreeNode<K> root;
  private int size;

  public TreeMap() {
    this.comparator = (Comparator<K>) Comparator.naturalOrder();
  }

  public TreeMap(Comparator<K> comparator) {
    if (comparator == null) {
      throw new NullPointerException();
    }
    this.comparator = comparator;
  }

  public TreeMap(Map<? extends K, ? extends V> map) {
    this.comparator = (Comparator<K>) Comparator.naturalOrder();
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
    return containsValue(root, value);
  }

  private boolean containsValue(TreeNode<K> node, Object value) {
    if (node == null) {
      return false;
    } else if (node.isLeafNode()) {
      LeafTreeNode<K, V> leaf = (LeafTreeNode<K, V>) node;
      return Objects.equals(leaf.value, value);
    } else {
      InnerTreeNode<K> inner = (InnerTreeNode<K>) node;
      if (containsValue(inner.left, value)) {
        return true;
      }
      if (containsValue(inner.middle, value)) {
        return true;
      }
      return containsValue(inner.right, value);
    }
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return new MapEntrySet<>(this, iteratorFactory);
  }

  private LeafTreeNode<K, V> findNode(TreeNode<K> node, K key) {
    if (node.isLeafNode()) {
      return (LeafTreeNode<K, V>) node;
    } else {
      InnerTreeNode<K> innerNode = (InnerTreeNode<K>) node;
      if (comparator.compare(key, innerNode.leftPivot) <= 0) {
        return findNode(innerNode.left, key);
      } else if (innerNode.rightPivot == null
          || comparator.compare(key, innerNode.rightPivot) <= 0) {
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
    return new MapKeySet<>(this, iteratorFactory);
  }

  @Override
  public V put(K key, V value) {
    if (key == null) {
      throw new NullPointerException();
    }
    if (root == null) {
      // New root node
      root = new LeafTreeNode<>(key, value);
      size++;
      return null;
    }
    LeafTreeNode<K, V> node = findNode(root, key);
    if (node.key.equals(key)) {
      // Replace an existing value
      V oldValue = node.value;
      node.value = value;
      return oldValue;
    } else {
      // Add a new node
      addToNode(node, new LeafTreeNode<>(key, value));
      size++;
      return null;
    }
  }

  private void addToNode(TreeNode<K> destNode, TreeNode<K> newNode) {
    TreeNode<K> parent = destNode.getParent();
    if (destNode.isLeafNode()) {
      if (parent == null) {
        makeNewRoot(destNode, newNode);
      } else {
        addToNode(parent, newNode);
      }
    } else {
      InnerTreeNode<K> innerNode = (InnerTreeNode<K>) destNode;
      if (innerNode.right == null) {
        // the destNode has room
        K max = getMaximum(newNode);
        if (comparator.compare(max, innerNode.leftPivot) < 0) {
          innerNode.right = innerNode.middle;
          innerNode.rightPivot = innerNode.leftPivot;
          innerNode.middle = innerNode.left;
          innerNode.leftPivot = max;
          innerNode.left = newNode;
        } else if (comparator.compare(max, getMaximum(innerNode.middle)) < 0) {
          innerNode.right = innerNode.middle;
          innerNode.rightPivot = max;
          innerNode.middle = newNode;
        } else {
          innerNode.right = newNode;
          innerNode.rightPivot = getMaximum(innerNode.middle);
        }
        newNode.setParent(innerNode);
      } else {
        // Split the node into two
        InnerTreeNode<K> splitNode = new InnerTreeNode<>();
        K newNodeMax = getMaximum(newNode);
        if (comparator.compare(newNodeMax, innerNode.leftPivot) < 0) {
          // [New, left] [middle, right]
          splitNode.left = newNode;
          splitNode.leftPivot = newNodeMax;
          splitNode.middle = innerNode.left;

          innerNode.left = innerNode.middle;
          innerNode.leftPivot = innerNode.rightPivot;
          innerNode.middle = innerNode.right;
        } else if (comparator.compare(newNodeMax, innerNode.rightPivot) < 0) {
          // [left, New] [middle, right]
          splitNode.left = innerNode.left;
          splitNode.leftPivot = innerNode.leftPivot;
          splitNode.middle = newNode;

          innerNode.left = innerNode.middle;
          innerNode.leftPivot = innerNode.rightPivot;
          innerNode.middle = innerNode.right;
        } else if (comparator.compare(newNodeMax, getMaximum(innerNode.right)) < 0) {
          // [left, middle] [New, right]
          splitNode.left = newNode;
          splitNode.leftPivot = newNodeMax;
          splitNode.middle = innerNode.right;
        } else {
          // [left, middle] [right, new]
          splitNode.left = innerNode.right;
          splitNode.leftPivot = getMaximum(innerNode.right);
          splitNode.middle = newNode;
        }
        innerNode.rightPivot = null;
        innerNode.right = null;
        splitNode.left.setParent(splitNode);
        splitNode.middle.setParent(splitNode);

        if (innerNode.parent == null) {
          makeNewRoot(innerNode, splitNode);
        } else {
          addToNode(innerNode.parent, splitNode);
        }
      }
    }
  }

  private void makeNewRoot(TreeNode<K> a, TreeNode<K> b) {
    // The destNode is the root, make a new root
    InnerTreeNode<K> newRoot = new InnerTreeNode<>();
    if (comparator.compare(getMaximum(a), getMaximum(b)) < 0) {
      newRoot.left = a;
      newRoot.leftPivot = getMaximum(a);
      newRoot.middle = b;
    } else {
      newRoot.left = b;
      newRoot.leftPivot = getMaximum(b);
      newRoot.middle = a;
    }
    a.setParent(newRoot);
    b.setParent(newRoot);
    root = newRoot;
  }

  private K getMaximum(TreeNode<K> node) {
    if (node.isLeafNode()) {
      LeafTreeNode<K, V> leaf = (LeafTreeNode<K, V>) node;
      return leaf.key;
    } else {
      InnerTreeNode<K> inner = (InnerTreeNode<K>) node;
      if (inner.right == null) {
        return getMaximum(inner.middle);
      } else {
        return getMaximum(inner.right);
      }
    }
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> map) {
    Iterator<?> iter = map.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<K, V> entry = (Map.Entry<K, V>) iter.next();
      put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public V remove(Object key) {
    if (root == null) {
      // Tree is empty
      return null;
    }
    LeafTreeNode<K, V> node = findNode(root, (K) key);
    if (!node.key.equals(key)) {
      // key not found in Tree
      return null;
    } else {
      removeNode(node);
      size--;
      return node.value;
    }
  }

  private void removeNode(TreeNode<K> node) {
    InnerTreeNode<K> parent = node.getParent();
    if (parent == null) {
      // removing the root
      root = null;
    } else if (parent.right != null) {
      // we can just remove the child from this node directly
      if (parent.left == node) {
        parent.left = parent.middle;
        parent.leftPivot = parent.rightPivot;
        parent.middle = parent.right;
        parent.rightPivot = null;
        parent.right = null;
      } else if (parent.middle == node) {
        parent.middle = parent.right;
        parent.rightPivot = null;
        parent.right = null;
      } else {
        parent.right = null;
        parent.rightPivot = null;
      }
    } else {

    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Collection<V> values() {
    return new MapValueCollection<>(this, iteratorFactory);
  }

  String debugString() {
    StringBuilder sb = new StringBuilder();
    sb.append("TreeMap {size: ")
        .append(size)
        .append("}\n  ");
    if (root == null) {
      sb.append("null");
    } else {
      LinkedList<TreeNode<K>> nodeList = new LinkedList<>();
      nodeList.add(root);
      nodeList.add(null);
      while (!nodeList.isEmpty()) {
        TreeNode<K> node = nodeList.removeFirst();
        if (node == null) {
          sb.append("\n  ");
          if (!nodeList.isEmpty()) {
            nodeList.add(null);
          }
        } else {
          sb.append(node.debugString());
          if (!node.isLeafNode()) {
            InnerTreeNode<K> inner = (InnerTreeNode<K>) node;
            nodeList.add(inner.left);
            nodeList.add(inner.middle);
            if (inner.right != null) {
              nodeList.add(inner.right);
            }
          }
        }
      }
    }

    return sb.toString();
  }

  interface TreeNode<K> {
    int getId();

    InnerTreeNode<K> getParent();

    void setParent(InnerTreeNode<K> parent);

    boolean isLeafNode();

    String debugString();
  }

  static final class InnerTreeNode<K> implements TreeNode {
    int id;
    InnerTreeNode<K> parent;
    TreeNode<K> left;
    K leftPivot;
    TreeNode<K> middle;
    K rightPivot;
    TreeNode<K> right;

    public InnerTreeNode() {
      this.id = NEXT_NODE_ID.getAndIncrement();
    }

    @Override
    public int getId() {
      return id;
    }

    @Override
    public InnerTreeNode<K> getParent() {
      return parent;
    }

    @Override
    public void setParent(InnerTreeNode parent) {
      this.parent = parent;
    }

    @Override
    public boolean isLeafNode() {
      return false;
    }

    @Override
    public String debugString() {
      return "Inner {"
          + "id: " + id
          + ", parent id: " + getNodeId(parent)
          + ", left: " + getNodeId(left)
          + ", leftPivot: " + leftPivot
          + ", middle: " + getNodeId(middle)
          + ", rightPivot: " + rightPivot
          + ", right: " + getNodeId(right)
          + "}";
    }
  }

  static final class LeafTreeNode<K, V> implements TreeNode, Map.Entry<K, V> {
    int id;
    InnerTreeNode<K> parent;
    K key;
    V value;

    LeafTreeNode(K key, V value) {
      if (key == null) {
        throw new NullPointerException();
      }
      this.id = NEXT_NODE_ID.getAndIncrement();
      this.key = key;
      this.value = value;
    }

    @Override
    public int getId() {
      return id;
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

    @Override
    public InnerTreeNode getParent() {
      return parent;
    }

    @Override
    public void setParent(InnerTreeNode parent) {
      this.parent = parent;
    }

    @Override
    public boolean isLeafNode() {
      return true;
    }

    @Override
    public String debugString() {
      return "Leaf {"
          + "id: " + id
          + ", parent id: " + getNodeId(parent)
          + ", key: " + key
          + ", value: " + value
          + '}';
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

  private static String getNodeId(TreeNode<?> node) {
    if (node == null) {
      return null;
    } else {
      return String.valueOf(node.getId());
    }
  }
}
