/**
 * This class represents an item that goes on a character’s hand (gloves/swords/shield) and is only
 * used for attack.
 */
public class HandGear extends Clothing {
  public HandGear(String adjective, String noun, int attack) {
    this.adjective = adjective;
    this.noun = noun;
    this.attack = attack;
  }

  @Override
  public Clothing combine(Clothing other) {
    if (!(other instanceof HandGear))
      throw new IllegalArgumentException("Must combine a hand gear!");
    return new HandGear(adjective + ", " + other.adjective, noun, attack + other.attack);
  }
}
