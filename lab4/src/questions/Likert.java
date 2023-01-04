package questions;

public class Likert implements Question {
  private final String text;
  public Likert(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public boolean answer(String ans) {
    if (ans == null || ans.length() != 1) return false;
    int option = Integer.parseInt(ans);
    return option >= 1 && option <= 5;
  }

  @Override
  public int compareTo(Question o) {
    if (o == null) throw new IllegalArgumentException("Null input!");
    if (o instanceof Likert)
      return text.compareTo(o.getText());
    return 1;
  }
}
