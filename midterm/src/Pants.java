public class Pants extends Clothing {
  public enum Color {
    red, green, blue, yellow, purple, orange, white, black, gray
  }

  private final int size;
  private final Color color;

  public Pants(int size, Color color) {
    super();
    this.size = size;
    this.color = color;
  }

  @Override
  public String toString() {
    return "Pants, size " + size;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (!(o instanceof Pants other)) return false;
    if (o == this) return true;
    return size == other.size && color == other.color;
  }
}
