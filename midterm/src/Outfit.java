import java.util.ArrayList;
import java.util.List;

public class Outfit {
  private final int size;
  private final List<Clothing> outfit;

  public Outfit(int size, List<Clothing> outfit) {
    this.size = size;
    this.outfit = outfit;
  }

  public void addCloth(Clothing cloth) {
    outfit.add(cloth);
  }

  public Outfit merge(Outfit other) {
    List<Clothing> newList = new ArrayList<>();
    newList.addAll(outfit);
    newList.addAll(other.outfit);
    return new Outfit(size, newList);
  }
}
