package birds;

public abstract class Bird {
  public enum Food {
    berries, seeds, fruit, insects, otherBirds, eggs, smallMammals, fish,
    buds, larvae, aquaticInvertebrates, nuts, vegetation
  }

  protected String type, characteristic;
  protected boolean isExtinct;
  protected int numWings, location = -1;
  protected Food[] foods;

  /**
   * Abstract constructor for a bird
   *
   * @param type            the type of the bird (e.g., duck, horned puffin, etc.)
   * @param characteristic  the bird's defining characteristic
   * @param isExtinct       whether the bird is extinct
   * @param numWings        the number of wings the bird has
   * @param foods           2-4 food items the bird prefers to eat from the given food enum
   */
  public Bird(String type, String characteristic, boolean isExtinct, int numWings, Food[] foods) {
    if (foods.length < 2)
      throw new IllegalArgumentException("Not enough food is given!");
    if (foods.length > 4)
      throw new IllegalArgumentException("Too much food is given!");
    if (numWings < 0)
      throw new IllegalArgumentException("Number of wings cannot be negative!");
    this.type = type;
    this.characteristic = characteristic;
    this.isExtinct = isExtinct;
    this.numWings = numWings;
    this.foods = foods;
  }
}
