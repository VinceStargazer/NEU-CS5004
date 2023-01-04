public interface BST<T> {
  void add(T obj); // only one that modifies the tree

  int size();  boolean present(T obj);

  T minimum();

  T maximum();

  String toString();

  String preorder();

  String inorder();

  String postorder();

  int height();

}
