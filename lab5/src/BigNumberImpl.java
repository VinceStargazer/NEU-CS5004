import java.util.Stack;

public class BigNumberImpl implements BigNumber {
  private static class ListNode {
    private final int val;
    private ListNode prev, next;
    private ListNode (int val, ListNode prev, ListNode next) {
      this.val = val;
      this.prev = prev;
      this.next = next;
    }

    private ListNode(int val) {
      this(val, null, null);
    }
  }

  private int len;
  private final ListNode sentinel;

  public BigNumberImpl() {
    this("0");
  }

  public BigNumberImpl(String str) {
    if (str == null)
      throw new IllegalArgumentException("Null input!");
    sentinel = new ListNode(-1);
    sentinel.prev = sentinel;
    sentinel.next = sentinel;
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c))
        throw new IllegalArgumentException("The string does not represent a valid number!");
      this.addDigit(c - '0');
    }
  }

  public int length() {
    return Math.max(len, 1);
  }

  public void shiftLeft(int shifts) {
    if (shifts < 0) shiftRight(-shifts);
    else if (len > 0) {
      for (int i = 0; i < shifts; i++) addDigit(0);
    }
  }

  public void shiftRight(int shifts) {
    if (shifts < 0) shiftLeft(-shifts);
    else {
      ListNode tail = sentinel.prev;
      for (int i = 0; i < shifts && len > 0; i++) {
        tail.prev.next = sentinel;
        sentinel.prev = tail.prev;
        tail = tail.prev;
        len--;
      }
    }
  }

  public void addDigit(int digit) {
    if (digit < 0 || digit > 9)
      throw new IllegalArgumentException("Digit must be between 0 and 9!");
    if (len == 0 && digit > 0 || len > 0) {
      ListNode tail = sentinel.prev;
      tail.next = new ListNode(digit, tail, sentinel);
      sentinel.prev = tail.next;
      len++;
    }
  }

  public int getDigitAt(int position) {
    if (len == 0 && position == 0) return 0;
    if (position < 0 || position >= len)
      throw new IllegalArgumentException("The position must be between 0 and " + (len - 1) + "!");
    ListNode tail = sentinel.prev;
    for (int i = 0; i < position; i++) tail = tail.prev;
    return tail.val;
  }

  public BigNumber copy() {
    BigNumber ret = new BigNumberImpl();
    ListNode head = sentinel.next;
    while (head != sentinel) {
      ret.addDigit(head.val);
      head = head.next;
    }
    return ret;
  }

  public BigNumber add(BigNumber other) {
    int len2 = other.length();
    if (len == 0) return other.copy();
    if (len2 == 0) return this.copy();
    Stack<Integer> stack = new Stack<>();
    int carry = 0, pos1 = 0, pos2 = 0;
    while (pos1 < len || pos2 < len2) {
      int sum = carry;
      if (pos1 < len) sum += this.getDigitAt(pos1++);
      if (pos2 < len2) sum += other.getDigitAt(pos2++);
      stack.push(sum % 10);
      carry = sum / 10;
    }
    if (carry > 0) stack.push(carry);
    BigNumber ret = new BigNumberImpl();
    while (!stack.isEmpty()) ret.addDigit(stack.pop());
    return ret;
  }

  public int compareTo(BigNumber other) {
    int len2 = other.length();
    if (len != len2) return len - len2;
    if (len == 0) return 0;
    int pos1 = len - 1, pos2 = len2 - 1;
    while (pos1 >= 0 && pos2 >= 0) {
      int digit1 = this.getDigitAt(pos1--), digit2 = other.getDigitAt(pos2--);
      if (digit1 != digit2) return digit1 - digit2;
    }
    return 0;
  }

  @Override
  public String toString() {
    if (len == 0) return "0";
    StringBuilder sb = new StringBuilder();
    ListNode head = sentinel.next;
    while (head != sentinel) {
      sb.append(head.val);
      head = head.next;
    }
    return sb.toString();
  }
}
