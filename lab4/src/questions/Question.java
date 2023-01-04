package questions;

public interface Question extends Comparable<Question> {
  String getText();
  boolean answer(String ans);
}
