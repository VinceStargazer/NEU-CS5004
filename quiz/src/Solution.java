import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
  public static Stack<Integer> reverseStack(Stack<Integer> stack) {
    Stack<Integer> res = new Stack<>();
    Queue<Integer> q = new LinkedList<>(stack);
    while (!stack.isEmpty()) res.push(stack.pop());
    while (!q.isEmpty()) stack.push(q.poll());
    return res;
  }

  public static void main(String[] args) {
    Stack<Integer> st = new Stack<>();
    for (int i : new int[] {1,2,3,4,5}) st.push(i);
    Stack<Integer> st2 = reverseStack(st);
    while (!st.isEmpty()) System.out.print(st.pop());
    System.out.println();
    while (!st2.isEmpty()) System.out.print(st2.pop());
  }
}
