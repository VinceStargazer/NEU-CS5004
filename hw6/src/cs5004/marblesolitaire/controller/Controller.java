package cs5004.marblesolitaire.controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.view.Square;
import cs5004.marblesolitaire.view.View;

import static cs5004.marblesolitaire.view.Colors.beige;
import static cs5004.marblesolitaire.view.Colors.highlight;

/**
 * Controller for our game
 */
public class Controller {
  private final MarbleSolitaireModel model;
  private final View view;
  private final JComponent[][] board;
  private final JTextField scoreField;
  private final int squareLen;
  private int[] firstClicked, secondClicked;

  /**
   * Initialize the controller
   * @param model the model used to run this game
   * @param view the user interface
   */
  public Controller(MarbleSolitaireModel model, View view) {
    this.model = model;
    this.view = view;
    squareLen = view.squareLen();
    board = view.getBoard();
    scoreField = (JTextField) board[0][0];
    scoreField.setText(" Score: " + model.getScore());

    view.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY(), c = x / squareLen, r = y / squareLen;
        JComponent component = board[r][c];
        if (!(component instanceof Square square)) return;
        if (square.hasMarble()) {
          if (firstClicked != null) {
            if (firstClicked[0] == r && firstClicked[1] == c) {
              // reset marble color
              square.setBackground(beige);
              firstClicked = null;
            } else {
              square.setBackground(highlight);
              secondClicked = new int[]{r, c};
            }
          } else {
            square.setBackground(highlight);
            firstClicked = new int[]{r, c};
          }
        } else if (firstClicked != null) {
            square.setBackground(highlight);
            secondClicked = new int[]{r, c};
        }
      }
    });
  }

  /**
   * A helper method to handle the message prompt
   * @param msg the message we receive from the game (error/game over)
   */
  private void promptMessage(String msg) {
    JDialog dialog = new JDialog();
    dialog.setTitle("Message Dialog");
    dialog.setModal(true);
    dialog.setSize(new Dimension(400, 100));

    // Create a JLabel with the message text
    JTextArea text = new JTextArea(msg);
    text.setSize(new Dimension(400, 100));
    text.setLineWrap(true);
    text.setWrapStyleWord(true);
    text.setFont(text.getFont().deriveFont(18.0f));
    text.setEditable(false);

    // Add the label to the dialog at the center position
    dialog.add(text, BorderLayout.CENTER);

    // Center the dialog on the screen
    dialog.setLocationRelativeTo(null);

    // Display the dialog and wait for it to be closed
    dialog.setVisible(true);
  }

  /**
   * Initialize the game
   */
  public void init() {
    // Initialize the model and the view
    while (!model.isGameOver()) {
      if (firstClicked != null && secondClicked != null) {
        int fromRow = firstClicked[0], fromCol = firstClicked[1];
        int toRow = secondClicked[0], toCol = secondClicked[1];
        Square from = (Square) board[fromRow][fromCol], to = (Square) board[toRow][toCol];
        boolean success = false;
        try {
          model.move(fromRow, fromCol, toRow, toCol);
          success = true;
        } catch (Exception e) {
          promptMessage(e.getMessage());
        }
        if (success) {
          from.removeMarble();
          to.addMarble();
          Square mid = (Square) board[(fromRow + toRow) / 2][(fromCol + toCol) / 2];
          mid.removeMarble();
          view.revalidate();
          view.repaint();
        }
        firstClicked = null;
        secondClicked = null;
        from.setBackground(beige);
        to.setBackground(beige);
        scoreField.setText(" Score: " + model.getScore());
      }
    }
    promptMessage(" Game over! Your final score is " + model.getScore());
    System.exit(0);
  }
}
