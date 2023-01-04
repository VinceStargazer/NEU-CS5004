package chess;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Pawn
 */
class PawnTest {
  @Test
  public void testCanMove() {
    ChessPiece whitePiece = new Pawn(1, 3, Color.white);
    Set<String> validPos = Set.of("13", "23", "33");
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        if (validPos.contains(r + "" + c))
          assertTrue(whitePiece.canMove(r, c));
        else
          assertFalse(whitePiece.canMove(r, c));
      }
    }
    whitePiece = new Pawn(2, 3, Color.white);
    assertFalse(whitePiece.canMove(4, 3));

    ChessPiece blackPiece = new Pawn(6, 3, Color.black);
    validPos = Set.of("43", "53", "63");
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        if (validPos.contains(r + "" + c))
          assertTrue(blackPiece.canMove(r, c));
        else
          assertFalse(blackPiece.canMove(r, c));
      }
    }
    blackPiece = new Pawn(5, 3, Color.black);
    assertFalse(blackPiece.canMove(3, 3));
  }

  @Test
  public void testCanKill() {
    ChessPiece pawn = new Pawn(1, 3, Color.white);
    assertTrue(pawn.canKill(new Bishop(2, 2, Color.black)));
    assertTrue(pawn.canKill(new Queen(2, 4, Color.black)));
    assertFalse(pawn.canKill(new Pawn(2, 4, Color.white)));
    assertFalse(pawn.canKill(new Rook(2, 3, Color.black)));
  }
}