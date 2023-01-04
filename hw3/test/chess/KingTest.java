package chess;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for King
 */
class KingTest {
  @Test
  public void testCanMove() {
    ChessPiece piece = new King(3, 4, Color.black);
    Set<String> validPos = new HashSet<>();
    for (int r = 2; r <= 4; r++)
      for (int c = 3; c <= 5; c++)
        validPos.add(r + "" + c);
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