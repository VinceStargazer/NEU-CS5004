package cs5004.marblesolitaire.model;

import java.util.Arrays;

public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private int score;
  private final char[][] grid;

  /**
   * Initialize the game board with arm thickness = 3 and the empty slot at the center
   */
  public MarbleSolitaireModelImpl() {
    this(3, 3, 3);
  }

  /**
   * Initialize the game board so that the arm thickness is 3 and the empty slot is at the position
   * (sRow, sCol)
   * @param sRow the row number of the empty slot
   * @param sCol the col number of the empty slot
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Initialize the game board with the given arm thickness and the empty slot at the center
   * @param armThickness the given arm thickness
   */
  public MarbleSolitaireModelImpl(int armThickness) {
    this(armThickness, armThickness, armThickness);
  }

  /**
   * Initialize the game board with the given arm thickness and the specified empty slot (sRow, sCol)
   * @param armThickness the given arm thickness
   * @param sRow the row number of the empty slot
   * @param sCol the col number of the empty slot
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if (armThickness <= 0 || armThickness % 2 == 0)
      throw new IllegalArgumentException("Arm thickness must be a positive odd number!");
    int len = 2 * armThickness + 1;
    grid = new char[len][len];
    for (int r = 0; r < len; r++) Arrays.fill(grid[r], 'O');
    int lMargin = armThickness - armThickness / 2, rMargin = armThickness + armThickness / 2;
    // mark all invalid regions empty
    for (int r = 0; r < lMargin; r++) {
      for (int c = 0; c < lMargin; c++) grid[r][c] = ' ';
      for (int c = len - 1; c > rMargin; c--) grid[r][c] = ' ';
    }
    for (int r = len - 1; r > rMargin; r--) {
      for (int c = 0; c < lMargin; c++) grid[r][c] = ' ';
      for (int c = len - 1; c > rMargin; c--) grid[r][c] = ' ';
    }
    if (notValid(sRow, sCol))
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");;
    grid[sRow][sCol] = '_';
    for (int r = 0; r < len; r++)
      for (int c = 0; c < len; c++)
        if (grid[r][c] == 'O') score++;
  }

  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    String msg = canMove(fromRow, fromCol, toRow, toCol);
    if (!msg.equals("Success")) throw new IllegalArgumentException(msg);
    grid[fromRow][fromCol] = '_';
    grid[toRow][toCol] = 'O';
    grid[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = '_';
    score--;
  }

  public boolean isGameOver() {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (canMove(r, c, r - 2, c).equals("Success")
          || canMove(r, c, r + 2, c).equals("Success")
          || canMove(r, c, r, c - 2).equals("Success")
          || canMove(r, c, r, c + 2).equals("Success"))
          return false;
      }
    }
    return true;
  }

  public String getGameState() {
    StringBuilder sb = new StringBuilder();
    for (int r = 0; r < grid.length; r++) {
      for (char c : grid[r]) sb.append(c).append(' ');
      if (r < grid.length - 1) sb.append("\n");
    }
    return sb.toString();
  }

  public int getScore() {
    return score;
  }

  /**
   * Helper method to decide whether the given position is invalid
   * @param r the row number of the given position
   * @param c the row number of the given position
   * @return true if the given position is invalid
   */
  private boolean notValid(int r, int c) {
    return r < 0 || r >= grid.length || c < 0 || c >= grid.length || grid[r][c] == ' ';
  }

  /**
   * Helper method to decide whether it is possible to move from the 'from' position to 'to' position
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow the row number of the position to be moved to
   *              (starts at 0)
   * @param toCol the column number of the position to be moved to
   *              (starts at 0)
   * @return "Success" if the move is possible other the error message
   */
  private String canMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (notValid(fromRow, fromCol))
      return "Invalid cell position (" + fromRow + "," + fromCol + ")";
    if (notValid(toRow, toCol))
      return "Invalid cell position (" + toRow + "," + toCol + ")";
    if (grid[fromRow][fromCol] == '_') return "The 'from' position is empty!";
    if (grid[toRow][toCol] == 'O') return "The 'to' position is not empty!";
    if (!(Math.abs(fromRow - toRow) == 2 && fromCol == toCol || fromRow == toRow && Math.abs(fromCol - toCol) == 2))
      return "'From' position and 'to' position must be horizontally or vertically two positions away!";
    int midRow = (fromRow + toRow) / 2, midCol = (fromCol + toCol) / 2;
    if (grid[midRow][midCol] != 'O')
      return "There is no marble between 'from' and 'to' positions!";
    return "Success";
  }

  public static void main(String[] args) {
    MarbleSolitaireModel test = new MarbleSolitaireModelImpl(3);
    System.out.println(test.getGameState());
    System.out.println(test.getScore());
    System.out.println(test.isGameOver());
    test = new MarbleSolitaireModelImpl(5);
    System.out.println(test.getGameState());
    try {
      new MarbleSolitaireModelImpl(5, 1, 1);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
