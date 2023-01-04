import java.util.Iterator;
import java.lang.Integer;

public class LinkedIntList2 implements Iterable<Integer> {
  LinkedIntNode head;

  @Override
  public Iterator<Integer> iterator() {
    return new LinkedIntListIterator<>(head);
  }

  public boolean anyPrime(LinkedIntList2 list) {
    for (int num : list)
      if (isPrime(num)) return true;
    return false;
  }

  private boolean isPrime(int num) {
    if (num <= 1) return false;
    for (int i = 2; i * i <= num; i++)
      if (num % i == 0) return false;
    return true;
  }

  private class LinkedIntListIterator<Integer> implements Iterator<Integer> {

    private LinkedIntNode current;

    private LinkedIntListIterator(LinkedIntNode head) {
      current = head;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Integer next() {
      Integer data = (Integer) current.data;
      current = current.next;
      return data;
    }
  }

  private class LinkedIntNode {

    Integer data;
    LinkedIntNode next;

    private LinkedIntNode(int data, LinkedIntNode next) {
      this.data = data;
      this.next = next;
    }
  }
}
