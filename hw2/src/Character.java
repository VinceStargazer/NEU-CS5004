/**
 * A class for simulating a character/player of the battle game
 */
public class Character {
  protected int attack, defense;
  protected HeadGear headGear;
  protected HandGear handGearLeft, handGearRight;
  protected Footwear footwearLeft, footwearRight;

  /**
   * The constructor of Character class
   * @param attack the default attack points
   * @param defense the default defense points
   */
  public Character(int attack, int defense) {
    this.attack = attack;
    this.defense = defense;
  }

  public Character() {
    this(0, 0);
  }

  /**
   * Pick up a given Clothing item by wearing or combining it.
   *
   * @param item the given Clothing item
   */
  public void pickUp(Clothing item) {
    if (item instanceof HeadGear) {
      if (headGear == null) headGear = (HeadGear) item;
      else headGear = (HeadGear) headGear.combine(item);
    } else if (item instanceof HandGear) {
      if (handGearRight == null) handGearRight = (HandGear) item;
      else if (handGearLeft == null) handGearLeft = (HandGear) item;
      else handGearRight = (HandGear) handGearRight.combine(item);
    } else if (item instanceof Footwear) {
      if (footwearRight == null) footwearRight = (Footwear) item;
      else if (footwearLeft == null) footwearLeft = (Footwear) item;
      else footwearRight = (Footwear) footwearRight.combine(item);
    }
    attack += item.attack;
    defense += item.defense;
  }

  public String toString() {
    return (headGear == null ? "" : "Head gear: " +  headGear + "\n")
            + (handGearLeft == null ? "" : "Hand gear-left: " + handGearLeft + "\n")
            + (handGearRight == null ? "" : "Hand gear-right: " + handGearRight + "\n")
            + (footwearLeft == null ? "" : "Footwear-left: " + footwearLeft + "\n")
            + (footwearRight == null ? "" : "Footwear-right: " + footwearRight + "\n")
            + "Attack points: " + attack + "\n"
            + "Defense points: " + defense;
  }
}
