import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {
  private BSTNode root;

  public BSTImpl() {
    this.root = null;
  }

  // difference between lab and lecture:
  // in lab, each null "node" at the end
  // is a node (it is a null node)

  public void add(T obj) {
    root = add(root, obj);
  }

  private BSTNode add(BSTNode node, T obj) {
    if (node == null) {
      node = new BSTNode(obj);
      return node;
    }
    if (node.data.compareTo(obj) < 0)
      node.right = add(node.right, obj);
    else if (node.data.compareTo(obj) > 0 )
      node.left = add(node.left, obj);
    return node;
  }

  /**
   * Returns the size of the tree.
   * @return the number of non-null nodes (nodes
   * that have data) in this tree.
   */
  public int size() {
    return this.size(root);
  }

  public boolean present(T obj) {
    return present(root, obj);
  }

  private boolean present(BSTNode node, T obj) {
    if (node == null) return false;
    if (node.data.equals(obj)) return true;
    return present(node.left, obj) || present(node.right, obj);
  }

  public T minimum() {
    BSTNode temp = root;
    while (temp.left != null)
      temp = temp.left;
    return temp.data;
  }

  public T maximum() {
    BSTNode temp = root;
    while (temp.right != null)
      temp = temp.right;
    return temp.data;
  }

  @Override
  public String toString() {
    return inorder();
  }

  public String preorder() {
    List<T> list = new ArrayList<>();
    preorder(root, list);
    return listToString(list);
  }

  private void preorder(BSTNode node, List<T> list) {
    if (node == null) return;
    list.add(node.data);
    preorder(node.left, list);
    preorder(node.right, list);
  }

  private String listToString(List<T> list) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    int n = list.size();
    for (int i = 0; i < n - 1; i++)
      sb.append(list.get(i)).append(" ");
    sb.append(list.get(n - 1)).append("]");
    return sb.toString();
  }

  public String inorder() {
    List<T> list = new ArrayList<>();
    inorder(root, list);
    return listToString(list);
  }

  private void inorder(BSTNode node, List<T> list) {
    if (node == null) return;
    inorder(node.left, list);
    list.add(node.data);
    inorder(node.right, list);
  }

  public String postorder() {
    List<T> list = new ArrayList<>();
    postorder(root, list);
    return listToString(list);
  }

  private void postorder(BSTNode node, List<T> list) {
    if (node == null) return;
    preorder(node.left, list);
    preorder(node.right, list);
    list.add(node.data);
  }

  public int height() {
    return height(root, 0);
  }

  private int height(BSTNode node, int level) {
    if (node == null) return level;
    return Math.max(height(node.left, level + 1), height(node.right, level + 1));
  }

  private int size(BSTNode node) {
    if (node == null) { // base case
      return 0;
    } else { // recursive case
      return 1 + this.size(node.left) +
          this.size(node.right);
    }
  }

  private class BSTNode {
    private final T data;
    private BSTNode left;
    private BSTNode right;

    private BSTNode(T data, BSTNode left, BSTNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    private BSTNode(T data) {
      this(data, null, null);
    }
  }
}
