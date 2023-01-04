import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class for simulating a battle between two characters.
 */
public class BattleDriver {
  public BattleDriver(Character char1, Character char2, List<Clothing> items) {
    if (items == null || items.size() < 10)
      throw new IllegalArgumentException("Needs at least 10 Clothing items!");
    takeTurns(char1, char2, items);
    System.out.println(determineWinner(char1, char2));
  }

  /**
   * The two characters take turns to "dress" themselves. Each turn, the character can only choose one
   * item via chooseClothing(). After an item is chosen, it is removed from the following turns.
   *
   * For each turn, also print out each character in the fight along with what they are wearing and
   * their attack and defense strength.
   *
   * @param char1 Player 1
   * @param char2 Player 2
   * @param items available Clothing items to choose from
   */
  private void takeTurns(Character char1, Character char2, List<Clothing> items) {
    while (!items.isEmpty()) {
      Clothing clothing1 = chooseClothing(items, char1);
      char1.pickUp(clothing1);
      items.remove(clothing1);
      System.out.println("Character 1:\n" + char1);
      if (items.isEmpty()) break;
      Clothing clothing2 = chooseClothing(items, char2);
      char2.pickUp(clothing2);
      items.remove(clothing2);
      System.out.println("Character 2:\n" + char2);
      System.out.println();
    }
  }

  /**
   * The item is chosen based on:
   *
   * Rule 1: Prefer the type of item that the character has available slot for. For example, if the
   * character already has 2 footwear, 1 hand gear and 1 helmet, the character should try to choose
   * 1 more hand gear.
   *
   * Rule 2: if there are multiple choices after following rule 1, pick the item that has the highest
   * attack strength.
   *
   * Rule 3: If there is still a tie after Rule 1 and 2, pick the item has the highest defense strength.
   *
   * Rule 4: if there is yet still a tie after Rule 1,2,3, pick a random one.
   *
   * @param available available Clothing items to choose from
   * @param character the current player
   * @return the most preferred item that this player chooses
   */
  protected static Clothing chooseClothing(List<Clothing> available, Character character) {
    List<Clothing> afterRule = new ArrayList<>();
    for (Clothing clothing : available) {
      if (clothing instanceof HeadGear && character.headGear == null
        || clothing instanceof HandGear && (character.handGearLeft == null || character.handGearRight == null)
        || clothing instanceof Footwear && (character.footwearLeft == null || character.footwearRight == null))
        afterRule.add(clothing);
    }
    if (afterRule.isEmpty()) afterRule.addAll(available);
    afterRule.sort((a, b) -> a.attack == b.attack ? b.defense - a.defense : b.attack - a.attack);
    Clothing first = afterRule.get(0), last = afterRule.get(afterRule.size() - 1);
    if (afterRule.size() == 1 || first.attack != last.attack || first.defense != last.defense)
      return first;
    Random rand = new Random();
    return afterRule.get(rand.nextInt(afterRule.size()));
  }

  /**
   * The winner is determined by who has less damage after a battle. A character's damage is calculated
   * by the opponent's attack power minus that character's defense strength. If the damages are equal
   * we state a tie.
   *
   * @param char1 Player 1
   * @param char2 Player 2
   * @return a string that represents the battle result
   */
  protected static String determineWinner(Character char1, Character char2) {
    int damage1 = char2.attack - char1.defense, damage2 = char1.attack - char2.defense;
    if (damage1 == damage2) return "Tie!";
    return damage1 < damage2 ? "Player 1 wins!" : "Player 2 wins!";
  }
}
