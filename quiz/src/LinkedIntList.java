public class LinkedIntList<T> {
  private LinkedIntNode<T> head;

  private static class LinkedIntNode<T> {
    private final T data;
    private LinkedIntNode<T> next;

    private LinkedIntNode(T data, LinkedIntNode<T> next) {
      this.data = data;
      this.next = next;
    }

    private LinkedIntNode(T data) {
      this(data, null);
    }
  }

  public T remove(int index) {
    if (head == null)
      throw new IllegalStateException("LinkedIntList is empty!");
    if (index == 0) {
      T val = head.data;
      head = head.next;
      return val;
    }
    LinkedIntNode<T> temp = head;
    for (int i = 0; i < index - 1 && temp != null; i++)
      temp = temp.next;
    if (temp == null || temp.next == null)
      throw new IllegalStateException("Index overflow!");
    T val = temp.next.data;
    temp.next = temp.next.next;
    return val;
  }

  public int length() {
    return length(head);
  }

  private int length(LinkedIntNode<T> curr) {
    if (curr == null) return 0;
    return 1 + length(curr.next);
  }
}
