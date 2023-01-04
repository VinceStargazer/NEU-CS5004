import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for HeadGear
 */
class HeadGearTest {
  private final Clothing hat = new HeadGear("Fashionable", "Hat", 1),
          helmet = new HeadGear("Solid", "Helmet", 6),
          sword = new HandGear("Sharp", "Sword", 8);

  @Test
  public void testConstructor() {
    assertEquals("Fashionable", hat.adjective);
    assertEquals("Hat", hat.noun);
    assertEquals(0, hat.attack);
    assertEquals(1, hat.defense);
  }

  /**
   * Testing HeadGear.combine() and the related IAE
   */
  @Test
  public void testCombine() {
    Clothing combined = hat.combine(helmet);
    assertEquals("Fashionable, Solid", combined.adjective);
    assertEquals("Hat", combined.noun);
    assertEquals(0, combined.attack);
    assertEquals(7, combined.defense);

    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      hat.combine(sword);
    });
    assertEquals("Must combine a head gear!", exception.getMessage());
  }
  
  @Test
  public void testToString() {
    assertEquals("Fashionable Hat - defense strength: 1, attack strength: 0", hat.toString());
    assertEquals("Solid Helmet - defense strength: 6, attack strength: 0", helmet.toString());
  }
}