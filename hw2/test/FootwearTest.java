import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for Footwear
 */
class FootwearTest {
  private final Clothing hoverBoard = new Footwear("Happy", "HoverBoard", 3, 1),
          sandal = new Footwear("Scurry", "Sandal", 1, 0),
          sword = new HandGear("Sharp", "hoverBoard", 8);

  /**
   * Testing the constructor of Footwear
   */
  @Test
  public void testConstructor() {
    assertEquals("Happy", hoverBoard.adjective);
    assertEquals("HoverBoard", hoverBoard.noun);
    assertEquals(3, hoverBoard.attack);
    assertEquals(1, hoverBoard.defense);
  }

  /**
   * Testing Footwear.combine() and the related IAE.
   */
  @Test
  public void testCombine() {
    Clothing combined = hoverBoard.combine(sandal);
    System.out.println(combined);
    assertEquals("Happy, Scurry", combined.adjective);
    assertEquals("HoverBoard", combined.noun);
    assertEquals(4, combined.attack);
    assertEquals(1, combined.defense);

    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      hoverBoard.combine(sword);
    });
    assertEquals("Must combine a footwear!", exception.getMessage());
  }

  @Test
  public void testToString() {
    assertEquals("Happy HoverBoard - defense strength: 1, attack strength: 3", hoverBoard.toString());
    assertEquals("Scurry Sandal - defense strength: 0, attack strength: 1", sandal.toString());
  }
}