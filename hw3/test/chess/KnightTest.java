package chess;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Knight
 */
class KnightTest {
  @Test
  public void testCanMove() {
    ChessPiece piece = new Knight(3, 4, Color.black);
    Set<String> validPos = Set.of("22", "13", "15", "26", "42", "46", "53", "55", "34");
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        if (validPos.contains(r + "" + c))
          assertTrue(piece.canMove(r, c));
        else
          assertFalse(piece.canMove(r, c));
      }
    }
  }
}