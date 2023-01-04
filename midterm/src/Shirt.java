public class Shirt extends Clothing {
  public enum Season {
    spring, summer, autumn, winter
  }

  private final int size;
  private final Season season;

  public Shirt(int size, Season season) {
    super();
    this.size = size;
    this.season = season;
  }

  @Override
  public String toString() {
    return "Shirt, size " + size;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (!(o instanceof Shirt other)) return false;
    if (o == this) return true;
    return size == other.size && season == other.season;
  }
}
