/**
 * This class represents an item that goes on a character’s head (hats/helmets/visors) and is only
 * used for defense.
 */
public class HeadGear extends Clothing {
  public HeadGear(String adjective, String noun, int defense) {
    this.adjective = adjective;
    this.noun = noun;
    this.defense = defense;
  }

  @Override
  public Clothing combine(Clothing other) {
    if (!(other instanceof HeadGear))
      throw new IllegalArgumentException("Must combine a head gear!");
    return new HeadGear(adjective + ", " + other.adjective, noun, defense + other.defense);
  }
}
