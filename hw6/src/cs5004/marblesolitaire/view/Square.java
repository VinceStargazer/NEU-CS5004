package cs5004.marblesolitaire.view;

import java.awt.*;

import javax.swing.*;

import static cs5004.marblesolitaire.view.Colors.*;

/**
 * A class representing each square of the game board
 */
public class Square extends JPanel {
  private final int size;
  private Marble marble;

  /**
   * Initiate a square with the given size
   * @param size the length of this square
   */
  public Square(int size) {
    this.size = size;
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(size, size));
    setBorder(BorderFactory.createLineBorder(bgGreen, 10));
    setBackground(beige);
  }

  /**
   * Add a marble to the square
   */
  public void addMarble() {
    marble = new Marble(size);
    add(marble, BorderLayout.CENTER);
  }

  /**
   * Remove the marble from the square
   */
  public void removeMarble() {
    remove(getComponentCount() - 1);
    marble = null;
  }

  /**
   * check if there is a marble in the given square
   * @return whether this square contains a marble
   */
  public boolean hasMarble() {
    return marble != null;
  }
}
