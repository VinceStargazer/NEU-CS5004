import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for Character
 */
class CharacterTest {
  private final Clothing hat = new HeadGear("Fashionable", "Hat", 1),
          helmet = new HeadGear("Solid", "Helmet", 6),
          gloveMetal = new HandGear("Metal", "Glove", 2),
          sword = new HandGear("Sharp", "Sword", 8),
          shield = new HandGear("Heavy", "Shield", 4),
          boot = new Footwear("Leather", "Boot", 2, 2),
          sneaker = new Footwear("Light", "Sneaker", 1, 0),
          hoverBoard = new Footwear("Happy", "HoverBoard", 3, 1);

  /**
   * Test the constructor of Character class
   */
  @Test
  public void testConstructor() {
    Character char1 = new Character(5, 7);
    assertEquals(5, char1.attack);
    assertEquals(7, char1.defense);
    Character char2 = new Character();
    assertEquals(0, char2.attack);
    assertEquals(0, char2.defense);
  }

  /**
   * Testing Character.pickUp().
   */
  @Test
  public void testPickUp() {
    Character character = new Character();
    character.pickUp(sword);
    assertEquals(sword, character.handGearRight);
    character.pickUp(hoverBoard);
    assertEquals(hoverBoard, character.footwearRight);
    character.pickUp(helmet);
    assertEquals(helmet, character.headGear);
    character.pickUp(shield);
    assertEquals(shield, character.handGearLeft);
    character.pickUp(boot);
    assertEquals(boot, character.footwearLeft);
    // Character is now fully equipped, combining clothing afterwards
    character.pickUp(gloveMetal);
    assertEquals(sword.combine(gloveMetal), character.handGearRight);
    character.pickUp(hat);
    assertEquals(helmet.combine(hat), character.headGear);
    character.pickUp(sneaker);
    assertEquals(hoverBoard.combine(sneaker), character.footwearRight);
  }

  /**
   * Testing Character.toString().
   */
  @Test
  public void testToString() {
    Character character = new Character();
    character.pickUp(sword);
    assertEquals("""
            Hand gear-right: Sharp Sword - defense strength: 0, attack strength: 8
            Attack points: 8
            Defense points: 0""", character.toString());
    character.pickUp(hoverBoard);
    assertEquals("""
            Hand gear-right: Sharp Sword - defense strength: 0, attack strength: 8
            Footwear-right: Happy HoverBoard - defense strength: 1, attack strength: 3
            Attack points: 11
            Defense points: 1""", character.toString());
    character.pickUp(helmet);
    assertEquals("""
            Head gear: Solid Helmet - defense strength: 6, attack strength: 0
            Hand gear-right: Sharp Sword - defense strength: 0, attack strength: 8
            Footwear-right: Happy HoverBoard - defense strength: 1, attack strength: 3
            Attack points: 11
            Defense points: 7""", character.toString());
    character.pickUp(shield);
    assertEquals("""
            Head gear: Solid Helmet - defense strength: 6, attack strength: 0
            Hand gear-left: Heavy Shield - defense strength: 0, attack strength: 4
            Hand gear-right: Sharp Sword - defense strength: 0, attack strength: 8
            Footwear-right: Happy HoverBoard - defense strength: 1, attack strength: 3
            Attack points: 15
            Defense points: 7""", character.toString());
  }
}