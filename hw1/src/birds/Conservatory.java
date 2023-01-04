package birds;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a conservatory that houses many types of birds
 * with various aviaries. It allows you to rescue new birds and bring
 * them into your conservatory
 */
public class Conservatory {
  private static final int numAviaries = 20, maxAviarySpace = 5;
  private final List<List<Bird>> aviaries;
  private final Map<Bird.Food, Integer> foodInventory; // data structure used to record food demand
  private final List<Bird> birdList; // data structure used to sort the birds by their types
  private int vacancy;

  /**
   * Constructor for a bird conservatory of 20*5 space.
   */
  public Conservatory() {
    aviaries = new ArrayList<>();
    for (int i = 0; i < numAviaries; i++)
      aviaries.add(new ArrayList<>());
    foodInventory = new HashMap<>();
    birdList = new ArrayList<>();
    vacancy = 100;
  }

  /**
   * Calculate what food needs to be kept and in what quantities.
   *
   * @return a map containing the corresponding quantities of each bird food
   */
  public Map<Bird.Food, Integer> getFoodInventory() {
    return foodInventory;
  }

  /**
   * Assign a bird to the first eligible aviary in the conservatory. Assignments follow the following criteria:
   * - There is a maximum of 20 aviaries in the conservatory
   * - Any bird can be inserted into an empty aviary
   * - No aviary can house more than 5 birds
   * - No extinct birds can be added to an aviary
   * - Flightless birds, birds of prey, and waterfowl should not be mixed with other bird types
   *
   * @param bird the bird we need to assign to this conservatory
   */
  public void assignBird(Bird bird) {
    if (bird == null)
      throw new IllegalArgumentException("Null input!");
    if (vacancy == 0)
      throw new IllegalStateException("No more vacancies!");
    if (bird.isExtinct)
      throw new IllegalArgumentException("You cannot rescue an extinct bird!");
    if (birdList.contains(bird)) return;

    boolean isValid = false;
    for (int i = 0; i < numAviaries; i++) {
      if (assignBirdToAviary(bird, i)) {
        isValid = true;
        break;
      }
    }

    if (!isValid) throw new IllegalStateException("No more vacancies!");
  }

  /**
   * Assign a bird to a GIVEN aviary in the conservatory.
   *
   * @param bird the bird we need to assign to this conservatory.
   * @param index the index of the aviary we need to assign.
   * @return whether the assignment is successful.
   */
  public boolean assignBirdToAviary(Bird bird, int index) {
    if (index < 0 || index >= numAviaries)
      throw new IllegalArgumentException("Aviary index must be between 0 and 19!");
    List<Bird> aviary = aviaries.get(index);
    if (!canAssign(bird, aviary)) return false;
    bird.location = index;
    aviary.add(bird);
    birdList.add(bird);
    vacancy--;
    for (Bird.Food food : bird.foods)
      foodInventory.put(food, foodInventory.getOrDefault(food, 0) + 1);
    return true;
  }

  /**
   * Have a guest look up which aviary a bird is in
   *
   * @param bird the bird we need to look up
   * @return the aviary index or -1 if the bird doesn't exist
   */
  public int searchBird(Bird bird) {
    if (bird == null) throw new IllegalArgumentException("Null input!");
    for (int i = 0; i < numAviaries; i++)
      if (aviaries.get(i).contains(bird)) return i;
    return -1;
  }

  /**
   * Remove a certain bird from the conservatory
   *
   * @param bird the bird we need to remove
   * @return the aviary index or -1 if the bird doesn't exist
   */
  public int removeBird(Bird bird) {
    if (bird == null) throw new IllegalArgumentException("Null input!");
    for (int i = 0; i < numAviaries; i++) {
      if (aviaries.get(i).remove(bird)) {
        vacancy++;
        birdList.remove(bird);
        return i;
      }
    }
    return -1;
  }

  /**
   * Print a sign for any given aviary that gives a description of the birds it houses
   * and any interesting information that it may have about that animal.
   *
   * @param index the aviary index
   */
  public void printAviary(int index) {
    if (index < 0 || index >= numAviaries)
      throw new IndexOutOfBoundsException("Index out of bounds!");
    List<Bird> aviary = aviaries.get(index);
    if (aviary.isEmpty()) {
      System.out.println("Aviary " + index + " doesn't have any bird yet.");
      return;
    }

    System.out.println("Aviary " + index + " has: ");
    for (Bird bird : aviary) {
      System.out.print(bird.type.substring(0, 1).toUpperCase() + bird.type.substring(1).toLowerCase()
              + " characterized by " + bird.characteristic + "; likes eating ");
      for (int i = 0; i < bird.foods.length; i++) {
        System.out.print(formatFood(bird.foods[i]));
        if (i < bird.foods.length - 1) System.out.print(", ");
      }
      if (bird instanceof Waterfowl)
        System.out.print("; lives by " + ((Waterfowl) bird).waterBody);
      else if (bird instanceof Parrot)
        System.out.print("; speaks " + ((Parrot) bird).vocabulary + " words; likes saying \""
                + ((Parrot) bird).favoriteSaying + "\"");
      System.out.println(".");
    }
  }

  /**
   * Print a “map” that lists all the aviaries by location and the birds they house.
   */
  public void printMap() {
    for (int i = 0; i < numAviaries; i++) {
      if (aviaries.get(i).isEmpty()) {
        System.out.println("Aviary " + i + " is empty");
        continue;
      }
      System.out.print("Aviary " + i + " has: ");
      List<Bird> aviary = aviaries.get(i);
      for (int j = 0; j < aviary.size(); j++) {
        System.out.print(aviary.get(j).type);
        if (j < aviary.size() - 1) System.out.print(", ");
      }
      System.out.println();
    }
  }

  /**
   * Print an index that lists all birds in the conservatory in alphabetical
   * order and their location.
   */
  public void printBirds() {
    birdList.sort(Comparator.comparing(a -> a.type));
    for (Bird bird : birdList)
      System.out.println(bird.type + " (" + bird.location + ")");
  }

  /**
   * Helper method for checking if the given bird can be successfully assigned to a given aviary.
   */
  private boolean canAssign(Bird bird, List<Bird> aviary) {
    if (aviary.size() >= maxAviarySpace) return false;
    if (aviary.isEmpty()) return true;
    return (!(aviary.get(0) instanceof Flightless) || bird instanceof Flightless)
            && (!(aviary.get(0) instanceof Prey) || bird instanceof Prey)
            && (!(aviary.get(0) instanceof Waterfowl) || bird instanceof Waterfowl)
            && (!(bird instanceof Flightless) || aviary.get(0) instanceof Flightless)
            && (!(bird instanceof Prey) || aviary.get(0) instanceof Prey)
            && (!(bird instanceof Waterfowl) || aviary.get(0) instanceof Waterfowl);
  }

  /**
   * Helper method for converting food in a more readable format.
   */
  private String formatFood(Bird.Food food) {
    if (food == Bird.Food.otherBirds)
      return "other birds";
    else if (food == Bird.Food.smallMammals)
      return "small mammals";
    else if (food == Bird.Food.aquaticInvertebrates)
      return "aquatic invertebrates";
    else
      return food + "";
  }
}
