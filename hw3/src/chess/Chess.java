package chess;

/**
 * Abstract class for every chess type
 */
public abstract class Chess implements ChessPiece {
  protected int row, col;
  protected Color color;

  public Chess(int row, int col, Color color) {
    if (row < 0 || col < 0 || row >= 8 || col >= 8)
      throw new IndexOutOfBoundsException("Index out of bounds!");
    if (color == null)
      throw new IllegalArgumentException("Null input!");
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return col;
  }

  public Color getColor() {
    return color;
  }

  public abstract boolean canMove(int row, int col);

  public boolean canKill(ChessPiece piece) {
    if (piece == null)
      throw new IllegalArgumentException("Null input!");
    return canMove(piece.getRow(), piece.getColumn()) && this.color != piece.getColor();
  }
}
