package cs5004.marblesolitaire.view;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static cs5004.marblesolitaire.view.Colors.*;

/**
 * View for our game
 */
public class View extends JFrame {
  private static final int boardWidth = 800, boardHeight = 800;
  private final JComponent[][] board;

  /**
   * Initiate user interface components and layout with default parameters
   */
  public View() {
    // Initialize and configure the components
    this(3, 3, 3);
  }

  /**
   * Initiate user interface components and layout with given parameters
   * @param armThickness the arm thickness of the game board
   * @param sRow the row number of the empty square
   * @param sCol the col number of the empty square
   */
  public View(int armThickness, int sRow, int sCol) {
    char[][] grid = getChessBoard(armThickness, sRow, sCol);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(boardWidth, boardHeight));
    int len = grid.length;
    board = new JComponent[len][len];
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(len, len));
    for (int r = 0; r < len; r++) {
      for (int c = 0; c < len; c++) {
        if (r == 0 && c == 0) {
          // Create a text field to display the score
          JTextField scoreField = new JTextField(" Score: 0");
          scoreField.setFont(scoreField.getFont().deriveFont(24.0f));
          scoreField.setForeground(Color.white);
          scoreField.setBackground(bgGreen);
          scoreField.setBorder(new EmptyBorder(0, 0, 0, 0));
          scoreField.setEditable(false);
          panel.add(scoreField);
          board[r][c] = scoreField;
        } else if (grid[r][c] == ' ') {
          JPanel square = new JPanel();
          square.setPreferredSize(new Dimension(boardWidth / len, boardWidth / len));
          square.setBackground(bgGreen);
          panel.add(square);
          board[r][c] = square;
        } else {
          Square square = new Square(boardWidth / len);
          if (grid[r][c] == 'O') square.addMarble();
          panel.add(square);
          board[r][c] = square;
        }
      }
    }
    add(panel);
    pack();
    setVisible(true);
  }

  /**
   * @return the length of each square
   */
  public int squareLen() {
    return boardWidth / board.length;
  }

  /**
   * @return a matrix of JComponents representing this board
   */
  public JComponent[][] getBoard() {
    return board;
  }

  /**
   * Helper method to locate the empty square and invalid squares
   */
  private char[][] getChessBoard(int armThickness, int sRow, int sCol) {
    int len = 2 * armThickness + 1;
    char[][] grid = new char[len][len];
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
    grid[sRow][sCol] = '_';
    return grid;
  }

  public static void main(String[] args) {
    new View();
  }
}
