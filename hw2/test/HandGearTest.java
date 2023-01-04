import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for HandGear
 */
class HandGearTest {
  private final Clothing sword = new HandGear("Sharp", "Sword", 8),
          shield = new HandGear("Heavy", "Shield", 4),
          boot = new Footwear("Leather", "Boot", 2, 2);

  @Test
  public void testConstructor() {
    assertEquals("Sharp", sword.adjective);
    assertEquals("Sword", sword.noun);
    assertEquals(8, sword.attack);
    assertEquals(0, sword.defense);
  }

  /**
   * Testing HandGear.combine() and the related IAE.
   */
  @Test
  public void testCombine() {
    Clothing combined = sword.combine(shield);
    assertEquals("Sharp, Heavy", combined.adjective);
    assertEquals("Sword", combined.noun);
    assertEquals(12, combined.attack);
    assertEquals(0, combined.defense);

    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      sword.combine(boot);
    });
    assertEquals("Must combine a hand gear!", exception.getMessage());
  }

  @Test
  public void testToString() {
    assertEquals("Sharp Sword - defense strength: 0, attack strength: 8", sword.toString());
    assertEquals("Heavy Shield - defense strength: 0, attack strength: 4", shield.toString());
  }
}