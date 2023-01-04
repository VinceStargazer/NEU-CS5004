/**
 * This class represents an item that goes on a character’s foot (boots/sneakers/hoverboard) and
 * can be used for both attack and defense.
 */
public class Footwear extends Clothing {
  public Footwear(String adjective, String noun, int attack, int defense) {
    this.adjective = adjective;
    this.noun = noun;
    this.attack = attack;
    this.defense = defense;
  }

  @Override
  public Clothing combine(Clothing other) {
    if (!(other instanceof Footwear))
      throw new IllegalArgumentException("Must combine a footwear!");
    return new Footwear(adjective + ", " + other.adjective, noun, attack + other.attack,
            defense + other.defense);
  }
}
