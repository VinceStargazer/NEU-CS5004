package birds;

public class Waterfowl extends Bird {
  protected String waterBody;

  /**
   * Constructor for a waterfowl
   *
   * @param type            the type of the bird (e.g., duck, horned puffin, etc.)
   * @param characteristic  the bird's defining characteristic
   * @param foods           2-4 food items the bird prefers to eat from the given food enum
   * @param waterBody       the name of the body of water that the bird lives by
   */
  public Waterfowl(String type, String characteristic, Food[] foods, String waterBody) {
    super(type, characteristic, false, 2, foods);
    this.waterBody = waterBody;
  }
}
