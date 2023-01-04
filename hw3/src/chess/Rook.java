package chess;

/**
 * Class representing a rook
 */
public class Rook extends Chess {
  public Rook(int row, int col, Color color) {
    super(row, col, color);
  }

  public boolean canMove(int row, int col) {
    if (row < 0 || col < 0 || row >= 8 || col >= 8)
      throw new IndexOutOfBoundsException("Index out of bounds!");
    return this.row == row || this.col == col;
  }
}
