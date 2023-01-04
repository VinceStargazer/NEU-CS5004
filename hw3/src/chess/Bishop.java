package chess;

/**
 * Class representing a bishop
 */
public class Bishop extends Chess {
  public Bishop(int row, int col, Color color) {
    super(row, col, color);
  }

  public boolean canMove(int row, int col) {
    if (row < 0 || col < 0 || row >= 8 || col >= 8)
      throw new IndexOutOfBoundsException("Index out of bounds!");
    return Math.abs(this.row - row) == Math.abs(this.col - col);
  }
}
