package questions;

import java.util.Set;
import java.util.TreeSet;

public class QuestionBank {
  private final Set<Question> bank;

  public QuestionBank() {
    bank = new TreeSet<>();
  }

  public void addQuestion(Question q) {
    bank.add(q);
  }

  public void getQuestionnaire() {
    for (Question q : bank)
      System.out.println(q.getText());
  }

  public static void main(String[] args) {
    QuestionBank qb = new QuestionBank();
    qb.addQuestion(new MultipleSelect("This is a multiple select", new int[] {3, 2, 5, 4}, 7));
    qb.addQuestion(new Likert("This is a Likert"));
    qb.addQuestion(new Likert("I like bananas"));
    qb.addQuestion(new MultipleChoice("This is a multiple choice", 3, 7));
    qb.addQuestion(new TrueFalse("This is a true/false", true));
    qb.getQuestionnaire();
  }
}
