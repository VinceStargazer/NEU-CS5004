import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClothingTest {
  private static final Clothing pants = new Pants(40, Pants.Color.black),
          pants2 = new Pants(38, Pants.Color.blue),
          shirt = new Shirt(42, Shirt.Season.autumn),
          shirt2 = new Shirt(39, Shirt.Season.spring);

  @BeforeAll
  public static void setUp() {
    for (int i : new int[] {3, 4, 5}) pants.rate(i);
    for (int i : new int[] {3, 3, 4}) pants2.rate(i);
    for (int i : new int[] {4, 4, 5}) shirt.rate(i);
    for (int i : new int[] {3, 4, 4}) shirt2.rate(i);
  }

  @Test
  public void testToString() {
    assertEquals("Pants, size 40", pants.toString());
    assertEquals("Pants, size 38", pants2.toString());
    assertEquals("Shirt, size 42", shirt.toString());
    assertEquals("Shirt, size 39", shirt2.toString());
  }

  @Test
  public void testRatingOrder() {
    List<Clothing> clothingList = new ArrayList<>();
    clothingList.add(pants);
    clothingList.add(pants2);
    clothingList.add(shirt);
    clothingList.add(shirt2);
    Collections.sort(clothingList);
    assertEquals(clothingList.get(0), new Shirt(42, Shirt.Season.autumn));
    assertEquals(clothingList.get(1), new Pants(40, Pants.Color.black));
    assertEquals(clothingList.get(2), new Shirt(39, Shirt.Season.spring));
    assertEquals(clothingList.get(3), new Pants(38, Pants.Color.blue));
  }
}