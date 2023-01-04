package chess;

/**
 * Class representing a pawn
 */
public class Pawn extends Chess {
  public Pawn(int row, int col, Color color) {
    super(row, col, color);
  }

  public boolean canMove(int row, int col) {
    if (row < 0 || col < 0 || row >= 8 || col >= 8)
      throw new IndexOutOfBoundsException("Index out of bounds!");
    if (this.col != col) return false;
    if (this.row == row) return true;
    if (this.color == Color.black)
      return this.row - row == 1 || this.row == 6 && row == 4;
    return row - this.row == 1 || this.row == 1 && row == 3;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (piece == null)
      throw new IllegalArgumentException("Null input!");
    if (Math.abs(this.col - piece.getColumn()) != 1 || this.color == piece.getColor())
      return false;
    if (this.color == Color.black)
      return this.row - piece.getRow() == 1;
    return piece.getRow() - this.row == 1;
  }
}
