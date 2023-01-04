package questions;

public class TrueFalse implements Question {
  private final String text;
  private final boolean ans;

  public TrueFalse(String text, boolean ans) {
    this.text = text;
    this.ans = ans;
  }

  public String getText() {
    return text;
  }

  public boolean answer(String ans) {
    if (ans == null) return false;
    return this.ans ? ans.equals("True") : ans.equals("False");
  }

  @Override
  public int compareTo(Question o) {
    if (o == null) throw new IllegalArgumentException("Null input!");
    if (o instanceof TrueFalse)
      return text.compareTo(o.getText());
    return -1;
  }
}
