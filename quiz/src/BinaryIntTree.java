public class BinaryIntTree {
  private BinaryIntNode root;

  private class BinaryIntNode {
    private final int data;
    private BinaryIntNode left;
    private BinaryIntNode right;

    public BinaryIntNode(int data, BinaryIntNode left, BinaryIntNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public void add(int num) {
      root = add(root, num);
    }

    private BinaryIntNode add(BinaryIntNode node, int num) {
      if (node == null) {
        node = new BinaryIntNode(num, null, null);
        return node;
      }
      if (node.data > num)
        node.left = add(node.left, num);
      else if (node.data < num)
        node.right = add(node.right, num);
      return node;
    }

    public void remove(int num) {
      root = remove(root, num);
    }

    private BinaryIntNode remove(BinaryIntNode node, int num) {
      if (node == null) return null;
      if (node.data > num)
        node.left = remove(node.left, num);
      else if (node.data < num)
        node.right = remove(node.right, num);
      else {
        if (node.right == null) return node.left;
        if (node.left == null) return node.right;
        BinaryIntNode next = node.right;
        while (next.left != null) next = next.left;
        next.left = node.left;
        return node.right;
      }
      return node;
    }
  }
}
