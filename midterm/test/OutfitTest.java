import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutfitTest {
  @Test
  public void testMerge() {
    Clothing pants = new Pants(40, Pants.Color.black);
    for (int i : new int[] {3, 4, 5}) pants.rate(i);
    Clothing pants2 = new Pants(38, Pants.Color.blue);
    for (int i : new int[] {3, 3, 4}) pants2.rate(i);
    Clothing shirt = new Shirt(40, Shirt.Season.autumn);
    for (int i : new int[] {4, 4, 5}) shirt.rate(i);
    Clothing shirt2 = new Shirt(38, Shirt.Season.spring);
    for (int i : new int[] {3, 4, 4}) shirt2.rate(i);
    Outfit outfit1 = new Outfit(40, List.of(pants, shirt));
    Outfit outfit2 = new Outfit(38, List.of(pants2, shirt2));
    Outfit newOutfit = outfit1.merge(outfit2);
//    assertEquals(List.of(pants, shirt), outfit1.outfit);
//    assertEquals(List.of(pants2, shirt2), outfit2.outfit);
//    assertEquals(List.of(pants, shirt, pants2, shirt2), newOutfit.outfit);
  }
}