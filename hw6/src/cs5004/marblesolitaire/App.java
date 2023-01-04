package cs5004.marblesolitaire;

import cs5004.marblesolitaire.controller.Controller;
import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;
import cs5004.marblesolitaire.view.View;

/**
 * This is where we run the app!
 */
public class App {
  // Main method
  public static void main(String[] args) {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    View view = new View();
    Controller controller = new Controller(model, view);
    controller.init();
  }
}
