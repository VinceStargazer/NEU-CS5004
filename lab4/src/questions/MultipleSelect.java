package questions;

import java.util.Arrays;

public class MultipleSelect implements Question {
  private final String text;
  private final int[] ans;
  private final int options;

  public MultipleSelect(String text, int[] ans, int options) {
    if (options < 3 || options > 8)
      throw new IllegalArgumentException("The number of options must be between 3 and 8!");
    Arrays.sort(ans);
    for (int i = 0; i < ans.length; i++) {
      int a = ans[i];
      if (a < 1 || a > options)
        throw new IllegalArgumentException("Each answer must be between 1 and " + options + "!");
      if (i > 0 && a == ans[i - 1])
        throw new IllegalArgumentException("Repetitive option!");
    }

    this.text = text;
    this.ans = ans;
    this.options = options;
  }

  public String getText() {
    return text;
  }

  public boolean answer(String ans) {
    if (ans == null) return false;
    String[] arr = ans.split(" ");
    if (arr.length != this.ans.length) return false;
    Arrays.sort(arr);
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].length() != 1) return false;
      int a = Integer.parseInt(arr[i]);
      if (a > options || a != this.ans[i]) return false;
    }
    return true;
  }

  @Override
  public int compareTo(Question o) {
    if (o == null) throw new IllegalArgumentException("Null input!");
    if (o instanceof MultipleSelect)
      return text.compareTo(o.getText());
    return o instanceof Likert ? -1 : 1;
  }
}
