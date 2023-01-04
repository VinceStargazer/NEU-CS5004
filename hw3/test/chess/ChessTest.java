package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A generic test class for Chess methods
 */
class ChessTest {
  /**
   * Test IOE of a chess constructor
   */
  @Test
  public void testConstructorException() {
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> new Queen(3, 8, Color.white));
    assertEquals("Index out of bounds!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> new Knight(4, 1, null));
    assertEquals("Null input!", exception.getMessage());
  }

  /**
   * Test IOE of Czhess.canMove() and chess.canKill()
   */
  @Test
  public void testMethodsException() {
    ChessPiece chess = new Rook(5, 2, Color.white);
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> chess.canMove(8, 2));
    assertEquals("Index out of bounds!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> chess.canKill(null));
    assertEquals("Null input!", exception.getMessage());
  }

  /**
   * Test Chess.getRow(), Chess.getColumn(), and Chess.getColor()
   */
  @Test
  public void testGetters() {
    ChessPiece chess = new Bishop(3, 7, Color.white);
    assertEquals(3, chess.getRow());
    assertEquals(7, chess.getColumn());
    assertEquals(Color.white, chess.getColor());
  }

  /**
   * Test Bishop.canKill() (also for other subtypes that use Chess.canKill())
   */
  @Test
  public void testCanKill() {
    ChessPiece chess = new Bishop(6, 4, Color.black);
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        if (chess.canMove(r, c))
          assertTrue(chess.canKill(new Queen(r, c, Color.white)));
        assertFalse(chess.canKill(new Queen(r, c, Color.black)));
      }
    }
  }
}