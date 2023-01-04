package birds;

public class Prey extends Bird {
  public Prey(String type, String characteristic, Food[] foods) {
    super(type, characteristic, false, 2, foods);
  }
}
