package chess;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Rook
 */
class RookTest {
  @Test
  public void testCanMove() {
    ChessPiece piece = new Rook(3, 4, Color.white);
    Set<String> validPos = new HashSet<>();
    for (int r = 0; r < 8; r++)
      validPos.add(r + "" + 4);
    for (int c = 0; c < 8; c++)
      validPos.add(3 + "" + c);
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