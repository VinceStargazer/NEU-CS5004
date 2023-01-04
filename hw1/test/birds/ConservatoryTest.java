package birds;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ConservatoryTest {
  private final Conservatory conservatory = new Conservatory();

  private final Bird hawk = new Prey("hawk", "outstanding intelligence and strong visual ability", new Bird.Food[]{Bird.Food.otherBirds, Bird.Food.smallMammals}),
          eagle = new Prey("eagle", "sharp, hooked beaks with visible nostrils", new Bird.Food[]{Bird.Food.otherBirds, Bird.Food.smallMammals}),
          kiwi = new Flightless("kiwi", "living on the ground and having no wings", true, 0, new Bird.Food[]{Bird.Food.berries, Bird.Food.fruit, Bird.Food.buds}),
          emu = new Flightless("emu", "living on the ground and having no wings", false, 0, new Bird.Food[]{Bird.Food.berries, Bird.Food.fruit, Bird.Food.smallMammals}),
          owl = new Owl("owl", "the facial disks that frame the eyes and bill", new Bird.Food[]{Bird.Food.smallMammals, Bird.Food.aquaticInvertebrates, Bird.Food.insects}),
          grayParrot = new Parrot("gray parrot", "short, curved beak and intelligence/ability to mimic sounds", new Bird.Food[]{Bird.Food.seeds, Bird.Food.nuts, Bird.Food.vegetation}, 50, "Good Morning!"),
          dove = new Pigeon("dove", "feeding their young bird milk very similar to the milk of mammals", false, new Bird.Food[]{Bird.Food.larvae, Bird.Food.eggs, Bird.Food.fruit}),
          greatAuk = new Shorebird("great auk", "living near wetlands and saltwater shore lands", new Bird.Food[]{Bird.Food.fish, Bird.Food.aquaticInvertebrates}),
          sandpiper = new Shorebird("sandpiper", "living near freshwater and saltwater shore lands", new Bird.Food[]{Bird.Food.fish, Bird.Food.aquaticInvertebrates}),
          gull = new Shorebird("gull", "living near the ocean", new Bird.Food[]{Bird.Food.fruit, Bird.Food.aquaticInvertebrates}),
          swan = new Waterfowl("swan", "living near fresh water sources", new Bird.Food[]{Bird.Food.fish, Bird.Food.aquaticInvertebrates, Bird.Food.eggs}, "lake"),
          duck = new Waterfowl("duck", "living near salt water sources", new Bird.Food[]{Bird.Food.fish, Bird.Food.nuts, Bird.Food.fruit}, "river"),
          goose = new Waterfowl("duck", "living near fresh water sources", new Bird.Food[]{Bird.Food.fish, Bird.Food.nuts, Bird.Food.fruit}, "pond");

  /**
   * Assign 12 existing birds of all kinds to the conservatory.
   */
  @Before
  public void setUp() {
    conservatory.assignBird(hawk);
    conservatory.assignBird(eagle);
    conservatory.assignBird(emu);
    conservatory.assignBird(owl);
    conservatory.assignBird(grayParrot);
    conservatory.assignBird(dove);
    conservatory.assignBird(greatAuk);
    conservatory.assignBird(sandpiper);
    conservatory.assignBird(gull);
    conservatory.assignBird(gull); // make sure the same bird can't be assigned repetitively
    conservatory.assignBird(swan);
    conservatory.assignBird(duck);
  }

  /**
   * Test if assigning an extinct bird will cause an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAssignExtinctException() {
    conservatory.assignBird(kiwi);
  }

  /**
   * Test if assigning too many birds will cause an exception.
   *
   * Note that although 12 + 80 < 100, some aviaries are already occupied by birds whose type is
   * incompatible with owls, so we have to skip those aviaries.
   */
  @Test(expected = IllegalStateException.class)
  public void testAssignOverflowException() {
    for (int i = 0; i < 80; i++)
      conservatory.assignBird(new Owl("owl", "the facial disks that frame the eyes and bill",
              new Bird.Food[]{Bird.Food.smallMammals, Bird.Food.aquaticInvertebrates, Bird.Food.insects}));
  }

  /**
   * Test searchBird() method of Conservatory.
   */
  @Test
  public void testSearchBird() {
    // Aviary 0 only for birds of prey; Aviary 1 only for flightless; Aviary 2 for other types but
    // full after 5 birds were assigned; Aviary 3 for other types; Aviary 4 only for waterfowl
    assertEquals(0, conservatory.searchBird(hawk));
    assertEquals(0, conservatory.searchBird(eagle));
    assertEquals(1, conservatory.searchBird(emu));
    assertEquals(2, conservatory.searchBird(owl));
    assertEquals(2, conservatory.searchBird(grayParrot));
    assertEquals(2, conservatory.searchBird(dove));
    assertEquals(2, conservatory.searchBird(greatAuk));
    assertEquals(2, conservatory.searchBird(sandpiper));
    assertEquals(3, conservatory.searchBird(gull));
    assertEquals(4, conservatory.searchBird(swan));
    assertEquals(4, conservatory.searchBird(duck));
    assertEquals(-1, conservatory.searchBird(goose)); // goose has yet to be assigned
  }

  /**
   * Test removeBird() method of Conservatory.
   */
  @Test
  public void testRemoveBird() {
    assertEquals(0, conservatory.removeBird(eagle));
    assertEquals(-1, conservatory.removeBird(eagle)); // no more eagle since we had it removed
    assertEquals(4, conservatory.removeBird(duck));
    assertEquals(-1, conservatory.searchBird(duck));
    assertEquals(-1, conservatory.removeBird(goose)); // goose has yet to be assigned
  }

  /**
   * Test whether assignBirdToAviary() can handle index overflow.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAssignBirdOverflow() {
    conservatory.assignBirdToAviary(goose, 20);
  }

  /**
   * Test assignBirdToAviary() method of Conservatory.
   */
  @Test
  public void testAssignBirdToAviary() {
    conservatory.assignBirdToAviary(goose, 16);
    assertEquals(16, conservatory.searchBird(goose));
  }

  /**
   * Test getFoodInventory() method of Conservatory.
   */
  @Test
  public void testGetFood() {
    Map<Bird.Food, Integer> actualFood = conservatory.getFoodInventory();
    Map<Bird.Food, Integer> expectedFood = new HashMap<>();
    expectedFood.put(Bird.Food.aquaticInvertebrates, 5);
    expectedFood.put(Bird.Food.seeds, 1);
    expectedFood.put(Bird.Food.eggs, 2);
    expectedFood.put(Bird.Food.nuts, 2);
    expectedFood.put(Bird.Food.fruit, 4);
    expectedFood.put(Bird.Food.larvae, 1);
    expectedFood.put(Bird.Food.fish, 4);
    expectedFood.put(Bird.Food.berries, 1);
    expectedFood.put(Bird.Food.vegetation, 1);
    expectedFood.put(Bird.Food.otherBirds, 2);
    expectedFood.put(Bird.Food.smallMammals, 4);
    expectedFood.put(Bird.Food.insects, 1);
    assertEquals(expectedFood, actualFood);
  }

  /**
   * Test printAviary() method of Conservatory.
   */
  @Test
  public void testPrintAviary() {
    // print Aviary 0
    OutputStream outContent = setOutput();
    conservatory.printAviary(0);
    String expectedOutput = """
            Aviary 0 has:\s
            Hawk characterized by outstanding intelligence and strong visual ability; likes eating other birds, small mammals.
            Eagle characterized by sharp, hooked beaks with visible nostrils; likes eating other birds, small mammals.""";
    assertEquals(expectedOutput, reformatOutput(outContent));

    // print Aviary 1
    outContent = setOutput();
    conservatory.printAviary(1);
    expectedOutput = "Aviary 1 has: \n" +
            "Emu characterized by living on the ground and having no wings; likes eating berries, fruit, small mammals.";
    assertEquals(expectedOutput, reformatOutput(outContent));

    // print Aviary 2
    outContent = setOutput();
    conservatory.printAviary(2);
    expectedOutput = """
            Aviary 2 has:\s
            Owl characterized by the facial disks that frame the eyes and bill; likes eating small mammals, aquatic invertebrates, insects.
            Gray parrot characterized by short, curved beak and intelligence/ability to mimic sounds; likes eating seeds, nuts, vegetation; speaks 50 words; likes saying "Good Morning!".
            Dove characterized by feeding their young bird milk very similar to the milk of mammals; likes eating larvae, eggs, fruit.
            Great auk characterized by living near wetlands and saltwater shore lands; likes eating fish, aquatic invertebrates.
            Sandpiper characterized by living near freshwater and saltwater shore lands; likes eating fish, aquatic invertebrates.""";
    assertEquals(expectedOutput, reformatOutput(outContent));

    // print Aviary 3
    outContent = setOutput();
    conservatory.printAviary(3);
    expectedOutput = "Aviary 3 has: \n" +
            "Gull characterized by living near the ocean; likes eating fruit, aquatic invertebrates.";
    assertEquals(expectedOutput, reformatOutput(outContent));

    // print Aviary 4
    outContent = setOutput();
    conservatory.printAviary(4);
    expectedOutput = """
            Aviary 4 has:\s
            Swan characterized by living near fresh water sources; likes eating fish, aquatic invertebrates, eggs; lives by lake.
            Duck characterized by living near salt water sources; likes eating fish, nuts, fruit; lives by river.""";
    assertEquals(expectedOutput, reformatOutput(outContent));

    // print Aviary 5
    outContent = setOutput();
    conservatory.printAviary(5);
    expectedOutput = "Aviary 5 doesn't have any bird yet.";
    assertEquals(expectedOutput, reformatOutput(outContent));
  }

  /**
   * Test printMap() method of Conservatory.
   */
  @Test
  public void testPrintMap() {
    OutputStream outContent = setOutput();
    conservatory.printMap();
    String expectedOutput = """
            Aviary 0 has: hawk, eagle
            Aviary 1 has: emu
            Aviary 2 has: owl, gray parrot, dove, great auk, sandpiper
            Aviary 3 has: gull
            Aviary 4 has: swan, duck
            Aviary 5 is empty
            Aviary 6 is empty
            Aviary 7 is empty
            Aviary 8 is empty
            Aviary 9 is empty
            Aviary 10 is empty
            Aviary 11 is empty
            Aviary 12 is empty
            Aviary 13 is empty
            Aviary 14 is empty
            Aviary 15 is empty
            Aviary 16 is empty
            Aviary 17 is empty
            Aviary 18 is empty
            Aviary 19 is empty""";
    assertEquals(expectedOutput, reformatOutput(outContent));
  }

  /**
   * Test printBirds() method of Conservatory.
   */
  @Test
  public void testPrintBirds() {
    OutputStream outContent = setOutput();
    conservatory.printBirds();
    String expectedOutput = """
            dove (2)
            duck (4)
            eagle (0)
            emu (1)
            gray parrot (2)
            great auk (2)
            gull (3)
            hawk (0)
            owl (2)
            sandpiper (2)
            swan (4)""";
    assertEquals(expectedOutput, reformatOutput(outContent));
  }

  /**
   * Helper method for reading stdout
   */
  private OutputStream setOutput() {
    OutputStream outContent = new ByteArrayOutputStream();
    System.setOut((new PrintStream(outContent)));
    return outContent;
  }

  /**
   * Helper method for converting OutputStream into a formatted string for comparison.
   */
  private String reformatOutput(OutputStream outContent) {
    return outContent.toString().trim().replace("\r", "");
  }
}