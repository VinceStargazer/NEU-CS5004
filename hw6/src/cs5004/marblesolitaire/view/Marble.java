package cs5004.marblesolitaire.view;

import java.awt.*;

import javax.swing.*;

import static cs5004.marblesolitaire.view.Colors.*;

/**
 * A class representing a single marble
 */
public class Marble extends JPanel {
  public Marble(int size) {
    setPreferredSize(new Dimension(size, size));
    setBackground(transparent);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Enable anti-aliasing for the Graphics2D object
    Graphics2D g2d = (Graphics2D) g;
    // Enable advanced graphics rendering options
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
    g2d.setColor(green);

    int x = getWidth() / 2 - 35, y = getHeight() / 2 - 35;
    // Draw the 3D circle with its center at the center of the square
    g2d.fillOval(x, y, 70, 70);
  }
}
