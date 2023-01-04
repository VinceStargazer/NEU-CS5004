import java.util.ArrayList;
import java.util.List;

public class BST<E extends Comparable<E>> {
  private BSTNode<E> root;

  private static class BSTNode<N extends Comparable<N>> {
    private final N data;
    private BSTNode<N> left;
    private BSTNode<N> right;

    private BSTNode(N data, BSTNode<N> left, BSTNode<N> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    private BSTNode(N data) {
      this(data, null, null);
    }
  }

  public void addNode(E data) {
    root = addNode(root, data);
  }

  private BSTNode<E> addNode(BSTNode<E> node, E data) {
    if (node == null) return new BSTNode<>(data);
    if (node.data.equals(data)) return node;
    if (node.data.compareTo(data) > 0) node.left = addNode(node.left, data);
    else node.right = addNode(node.right, data);
    return node;
  }

  @Override
  public String toString() {
    List<E> list = new ArrayList<>();
    inorder(root, list);
    StringBuilder sb = new StringBuilder();
    sb.append("(");
    for (int i = 0; i < list.size() - 1; i++)
      sb.append(list.get(i)).append(" ");
    sb.append(list.get(list.size() - 1));
    sb.append(")");
    return sb.toString();
  }

  private void inorder(BSTNode<E> node, List<E> list) {
    if (node == null) return;
    inorder(node.left, list);
    list.add(node.data);
    inorder(node.right, list);
  }

  public static void main(String[] args) {
    BST<Integer> bst = new BST<>();
    for (int num : new int[]{4, 5, 1, 2, 2, 7, 6})
      bst.addNode(num);
    System.out.println("done");
    System.out.println(bst);
  }
}