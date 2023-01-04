import org.junit.jupiter.api.Test;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;

import static org.junit.jupiter.api.Assertions.*;

class MarbleSolitaireModelTest {

  @Test
  public void testConstructors() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireModelImpl(1, 1));
    assertEquals("Invalid empty cell position (1,1)", e.getMessage());
    e = assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireModelImpl(8));
    assertEquals("Arm thickness must be a positive odd number!", e.getMessage());
  }

  @Test
  public void testMove() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    model.move(1, 3, 3, 3);
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O _ O O O\s
            O O O O O O O\s
            O O O O O O O\s
                O O O    \s
                O O O    \s""", model.getGameState());
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> model.move(2, 3, 2, 5));
    assertEquals("The 'from' position is empty!", e.getMessage());
    e = assertThrows(IllegalArgumentException.class, () -> model.move(1, 1, 1, 3));
    assertEquals("Invalid cell position (1,1)", e.getMessage());
    e = assertThrows(IllegalArgumentException.class, () -> model.move(4, 1, 4, 3));
    assertEquals("The 'to' position is not empty!", e.getMessage());
    e = assertThrows(IllegalArgumentException.class, () -> model.move(3, 2, 2, 3));
    assertEquals("'From' position and 'to' position must be horizontally or vertically two positions away!", e.getMessage());
    e = assertThrows(IllegalArgumentException.class, () -> model.move(5, 3, 2, 3));
    assertEquals("'From' position and 'to' position must be horizontally or vertically two positions away!", e.getMessage());
    e = assertThrows(IllegalArgumentException.class, () -> model.move(0, 3, 2, 3));
    assertEquals("There is no marble between 'from' and 'to' positions!", e.getMessage());
  }

  @Test
  public void testIsGameOver() {
    assertFalse(new MarbleSolitaireModelImpl().isGameOver());
  }

  @Test
  public void testGetGameState() {
    assertEquals("""
                O O O    \s
                O O O    \s
            O O O O O O O\s
            O O O _ O O O\s
            O O O O O O O\s
                O O O    \s
                O O O    \s""", new MarbleSolitaireModelImpl().getGameState());
  }

  @Test
  public void testGetScore() {
    assertEquals(32, new MarbleSolitaireModelImpl().getScore());
  }

  @Test
  public void testDefaultModel() {
    // Keep playing until game over
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    model.move(1, 3, 3, 3);
    model.move(4, 3, 2, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O O O O O\s
            O O O _ O O O\s
            O O O _ O O O\s
                O O O    \s
                O O O    \s""", model.getGameState());

    model.move(3, 1, 3, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ O O O O\s
            O O O _ O O O\s
                O O O    \s
                O O O    \s""", model.getGameState());

    model.move(6, 3, 4, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ O O O O\s
            O O O O O O O\s
                O _ O    \s
                O _ O    \s""", model.getGameState());

    model.move(3, 3, 5, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ _ O O O\s
            O O O _ O O O\s
                O O O    \s
                O _ O    \s""", model.getGameState());

    model.move(4, 1, 4, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ _ O O O\s
            O _ _ O O O O\s
                O O O    \s
                O _ O    \s""", model.getGameState());

    model.move(4, 3, 6, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ _ O O O\s
            O _ _ _ O O O\s
                O _ O    \s
                O O O    \s""", model.getGameState());

    model.move(3, 5, 3, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ O _ _ O\s
            O _ _ _ O O O\s
                O _ O    \s
                O O O    \s""", model.getGameState());

    model.move(4, 5, 4, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ O _ _ O\s
            O _ _ O _ _ O\s
                O _ O    \s
                O O O    \s""", model.getGameState());

    model.move(3, 3, 1, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O O O    \s
                O O O    \s
            O O O _ O O O\s
            O _ _ _ _ _ O\s
            O _ _ O _ _ O\s
                O _ O    \s
                O O O    \s""", model.getGameState());

    model.move(0, 3, 2, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                O _ O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ _ _ _ O\s
            O _ _ O _ _ O\s
                O _ O    \s
                O O O    \s""", model.getGameState());

    model.move(6, 2, 4, 2);
    assertFalse(model.isGameOver());
    assertEquals("""
                O _ O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ _ _ _ O\s
            O _ O O _ _ O\s
                _ _ O    \s
                _ O O    \s""", model.getGameState());

    model.move(6, 4, 6, 2);
    assertFalse(model.isGameOver());
    assertEquals("""
                O _ O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ _ _ _ O\s
            O _ O O _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(4, 3, 4, 1);
    assertFalse(model.isGameOver());
    assertEquals("""
                O _ O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ _ _ _ O\s
            O O _ _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(4, 0, 4, 2);
    assertFalse(model.isGameOver());
    assertEquals("""
                O _ O    \s
                O _ O    \s
            O O O O O O O\s
            O _ _ _ _ _ O\s
            _ _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(2, 0, 4, 0);
    assertFalse(model.isGameOver());
    assertEquals("""
                O _ O    \s
                O _ O    \s
            _ O O O O O O\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(2, 2, 2, 0);
    assertFalse(model.isGameOver());
    assertEquals("""
                O _ O    \s
                O _ O    \s
            O _ _ O O O O\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(0, 2, 2, 2);
    assertFalse(model.isGameOver());
    assertEquals("""
                _ _ O    \s
                _ _ O    \s
            O _ O O O O O\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(2, 3, 2, 1);
    assertFalse(model.isGameOver());
    assertEquals("""
                _ _ O    \s
                _ _ O    \s
            O O _ _ O O O\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(2, 5, 2, 3);
    assertFalse(model.isGameOver());
    assertEquals("""
                _ _ O    \s
                _ _ O    \s
            O O _ O _ _ O\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(0, 4, 2, 4);
    assertFalse(model.isGameOver());
    assertEquals("""
                _ _ _    \s
                _ _ _    \s
            O O _ O O _ O\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(2, 0, 2, 2);
    assertFalse(model.isGameOver());
    assertEquals("""
                _ _ _    \s
                _ _ _    \s
            _ _ O O O _ O\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(2, 3, 2, 5);
    assertFalse(model.isGameOver());
    assertEquals("""
                _ _ _    \s
                _ _ _    \s
            _ _ O _ _ O O\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(2, 6, 2, 4);
    assertFalse(model.isGameOver());
    assertEquals("""
                _ _ _    \s
                _ _ _    \s
            _ _ O _ O _ _\s
            _ _ _ _ _ _ O\s
            O _ O _ _ _ O\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());

    model.move(4, 6, 2, 6);
    assertTrue(model.isGameOver());
    assertEquals("""
                _ _ _    \s
                _ _ _    \s
            _ _ O _ O _ O\s
            _ _ _ _ _ _ _\s
            O _ O _ _ _ _\s
                _ _ O    \s
                O _ _    \s""", model.getGameState());
    assertEquals(7, model.getScore());
  }
}