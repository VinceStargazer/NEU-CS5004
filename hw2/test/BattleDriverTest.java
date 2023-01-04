import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattleDriverTest {
  private final Clothing hat = new HeadGear("Fashionable", "Hat", 1),
          helmet = new HeadGear("Solid", "Helmet", 6),
          visor = new HeadGear("Portable", "Visor", 0),
          gloveCotton = new HandGear("Cotton", "Glove", 1),
          gloveMetal = new HandGear("Metal", "Glove", 2),
          sword = new HandGear("Sharp", "Sword", 8),
          shield = new HandGear("Heavy", "Shield", 4),
          pistol = new HandGear("Dangerous", "Pistol", 10),
          boot = new Footwear("Leather", "Boot", 2, 2),
          sneaker = new Footwear("Light", "Sneaker", 1, 0),
          hoverBoard = new Footwear("Happy", "HoverBoard", 3, 1),
          sandal = new Footwear("Scurry", "Sandal", 1, 0);

  /**
   * Testing if an exception is thrown when the passed-in clothing items are fewer than 10.
   */
  @Test
  public void testException() {
    Character char1 = new Character(), char2 = new Character();
    List<Clothing> items = List.of(hat, helmet, visor, gloveCotton, gloveMetal, sword, shield,
            pistol, boot);
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      new BattleDriver(char1, char2, new ArrayList<>(items));
    });
    assertEquals("Needs at least 10 Clothing items!", exception.getMessage());
  }

  /**
   * Testing BattleDriver.chooseClothing().
   */
  @Test
  public void testChooseClothing() {
    Character character = new Character();
    List<Clothing> items = new ArrayList<>(List.of(hat, helmet, visor, gloveCotton, gloveMetal, sword, shield,
            pistol, boot, sneaker, hoverBoard, sandal));
    assertEquals(pistol, BattleDriver.chooseClothing(items, character));
    items.remove(pistol);
    assertEquals(sword, BattleDriver.chooseClothing(items, character));
    items.remove(sword);
    assertEquals(shield, BattleDriver.chooseClothing(items, character));

    items.clear();
    for (int i = 0; i < 10; i++)
      items.add(new HeadGear("Strange", "Helmet", i));
    // if there is a tie in attack points, we choose the item with the highest defense point
    assertEquals(new HeadGear("Strange", "Helmet", 9), BattleDriver.chooseClothing(items, character));
  }

  /**
   * Testing BattleDriver.determineWinner().
   */
  @Test
  public void testDetermineWinner() {
    Character char1 = new Character(100, 76), char2 = new Character(80, 98);
    assertEquals("Player 2 wins!", BattleDriver.determineWinner(char1, char2));
    char1 = new Character(70, 40); char2 = new Character(50, 60);
    assertEquals("Tie!", BattleDriver.determineWinner(char1, char2));
    char1 = new Character(40, 80); char2 = new Character(90, 20);
    assertEquals("Player 1 wins!", BattleDriver.determineWinner(char1, char2));
  }

  @Test
  public void testRes1() {
    Character char1 = new Character(), char2 = new Character();
    List<Clothing> items = List.of(hat, helmet, visor, gloveCotton, gloveMetal, sword, shield,
            pistol, boot, sneaker, hoverBoard, sandal);
    new BattleDriver(char1, char2, new ArrayList<>(items));
    // Expecting "Player 1 wins!"
  }

  @Test
  public void testRes2() {
    Character char1 = new Character(), char2 = new Character();
    List<Clothing> items = new ArrayList<>();
    for (int i = 0; i < 10; i++)
      items.add(new Footwear("Happy", "HoverBoard", 3, 1));
    new BattleDriver(char1, char2, items);
    // Expecting "Tie!"
  }

  @Test
  public void testRes3() {
    Character char1 = new Character(), char2 = new Character();
    char1.pickUp(visor);
    char2.pickUp(pistol);
    List<Clothing> items = List.of(hat, helmet, gloveCotton, gloveMetal, shield, sword, boot, sneaker,
            hoverBoard, sandal);
    new BattleDriver(char1, char2, new ArrayList<>(items));
    // Expecting "Player 2 wins!"
  }
}