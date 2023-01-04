package chess;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Bishop
 */
class BishopTest {
  @Test
  public void testCanMove() {
    ChessPiece piece = new Bishop(2, 3, Color.white);
    Set<String> validPos = new HashSet<>();
    for (int r = 0, c = 1; c < 8; r++, c++)
      validPos.add(r + "" + c);
    for (int r = 5, c = 0; r >= 0; r--, c++)
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