import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedievalLevelBuilderTest {
  private Level level;

  @Before
  public void setUp() {
    level = new MedievalLevelBuilder(8, 10, 100, 100).
            addRoom("store").addGold(0, 100).addWeapon(0, "sword").addWeapon(0, "shield").
            addPotion(0).addSpecial(0, "ring of power", 1000).addHuman(0, "merchant").
            addRoom("battlefield").addGoblins(1, 20).addOgre(1).addOrc(1).addOrc(1).addOrc(1).
            addHuman(1, "archer").addHuman(1, "caster").addWeapon(1, "dagger").
            addRoom("tavern").addSpecial(2, "wine", 1).addHuman(2, "owner").
            addHuman(2, "bartender").addHuman(2, "customer").build();
  }

  @Test
  public void testLevelNum() {
    assertEquals(8, level.getLevelNumber());
  }

  @Test
  public void testString() {
    // We can simply check if this level contains all the info we passed in by builder
    System.out.println(level);
  }
}