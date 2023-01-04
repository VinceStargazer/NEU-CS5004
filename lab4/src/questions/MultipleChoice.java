package questions;

public class MultipleChoice implements Question {
  private final String text;
  private final int ans, options;

  public MultipleChoice(String text, int ans, int options) {
    if (options < 3 || options > 8)
      throw new IllegalArgumentException("The number of options must be between 3 and 8!");
    if (ans < 1 || ans > options)
      throw new IllegalArgumentException("The answer must be between 1 and " + options + "!");
    this.text = text;
    this.ans = ans;
    this.options = options;
  }

  public String getText() {
    return text;
  }

  public boolean answer(String ans) {
    if (ans == null || ans.length() != 1) return false;
    int option = Integer.parseInt(ans);
    if (option > options) return false;
    return option == this.ans;
  }

  @Override
  public int compareTo(Question o) {
    if (o == null) throw new IllegalArgumentException("Null input!");
    if (o instanceof MultipleChoice)
      return this.text.compareTo(o.getText());
    return o instanceof TrueFalse ? 1 : -1;
  }
}
