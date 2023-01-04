package questions;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

  @Test
  public void testLikert() {
    Question q = new Likert("This is text");
    assertEquals("This is text", q.getText());
    for (int i = 1; i <= 5; i++)
      assertTrue(q.answer(Integer.toString(i)));
    assertFalse(q.answer(null));
    assertFalse(q.answer(""));
    assertFalse(q.answer("0"));
    assertFalse(q.answer("6"));
    assertFalse(q.answer("05"));
    assertTrue(q.compareTo(new TrueFalse("This is text", true)) > 0);
    assertTrue(q.compareTo(new MultipleChoice("This is text", 3, 7)) > 0);
    assertTrue(q.compareTo(new MultipleSelect("This is text", new int[] {3, 2, 5, 4}, 7)) > 0);
    assertTrue(q.compareTo(new Likert("This is Likert")) > 0);
  }

  @Test
  public void testMultipleChoice() {
    Question q = new MultipleChoice("This is text", 3, 7);
    assertEquals("This is text", q.getText());
    for (int i = 0; i <= 9; i++) {
      if (i == 3) assertTrue(q.answer(Integer.toString(i)));
      else assertFalse(q.answer(Integer.toString(i)));
    }
    assertFalse(q.answer(null));
    assertFalse(q.answer(""));
    assertFalse(q.answer("03"));
    assertTrue(q.compareTo(new TrueFalse("This is text", true)) > 0);
    assertTrue(q.compareTo(new MultipleChoice("This is multiple choice", 3, 7)) > 0);
    assertTrue(q.compareTo(new MultipleSelect("This is text", new int[] {3, 2, 5, 4}, 7)) < 0);
    assertTrue(q.compareTo(new Likert("This is Likert")) < 0);
  }

  @Test
  public void testMultipleSelect() {
    Question q = new MultipleSelect("This is text", new int[] {3, 2, 5, 4}, 7);
    assertEquals("This is text", q.getText());
    assertTrue(q.answer("2 3 4 5"));
    assertTrue(q.answer("3 2 5 4"));
    assertTrue(q.answer("5 4 2 3"));
    assertFalse(q.answer(null));
    assertFalse(q.answer(""));
    assertFalse(q.answer("2345"));
    assertFalse(q.answer("2 3 04 5"));
    assertFalse(q.answer("2 3 4"));
    assertFalse(q.answer("2 3 4 5 6"));
    assertTrue(q.compareTo(new TrueFalse("This is text", true)) > 0);
    assertTrue(q.compareTo(new MultipleChoice("This is text", 3, 7)) > 0);
    assertTrue(q.compareTo(new MultipleSelect("This is multiple select", new int[] {3, 2, 5, 4}, 7)) > 0);
    assertTrue(q.compareTo(new Likert("This is Likert")) < 0);
  }

  @Test
  public void testTrueFalse() {
    Question q = new TrueFalse("This is text", true);
    assertEquals("This is text", q.getText());
    assertTrue(q.answer("True"));
    assertFalse(q.answer(null));
    assertFalse(q.answer(""));
    assertFalse(q.answer("False"));
    assertFalse(q.answer("true"));
    assertTrue(q.compareTo(new TrueFalse("This is true/false", true)) < 0);
    assertTrue(q.compareTo(new MultipleChoice("This is text", 3, 7)) < 0);
    assertTrue(q.compareTo(new MultipleSelect("This is text", new int[] {3, 2, 5, 4}, 7)) < 0);
    assertTrue(q.compareTo(new Likert("This is Likert")) < 0);
  }
}