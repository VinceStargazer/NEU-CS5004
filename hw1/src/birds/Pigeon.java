package birds;

public class Pigeon extends Bird {
  public Pigeon(String type, String characteristic, boolean isExtinct, Food[] foods) {
    super(type, characteristic, isExtinct, 2, foods);
  }
}
