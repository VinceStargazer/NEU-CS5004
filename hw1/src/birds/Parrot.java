package birds;

public class Parrot extends Bird {
  protected int vocabulary;
  protected String favoriteSaying;

  /**
   * Constructor for a parrot
   *
   * @param type            the type of the bird (e.g., duck, horned puffin, etc.)
   * @param characteristic  the bird's defining characteristic
   * @param foods           2-4 food items the bird prefers to eat from the given food enum
   * @param vocabulary      the number of words in their vocabulary
   * @param favoriteSaying  the bird's single "favorite" saying
   */
  public Parrot(String type, String characteristic, Food[] foods, int vocabulary, String favoriteSaying) {
    super(type, characteristic, false, 2, foods);
    this.vocabulary = vocabulary;
    this.favoriteSaying = favoriteSaying;
  }
}
