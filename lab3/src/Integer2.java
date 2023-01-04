public class Integer2 implements Comparable<Integer2> {
  private final int value;

  // constructor1
  public Integer2(int value) {
    this.value = value;
  }

  @Override
  public int compareTo(Integer2 o) {
    // if o is null we throw an error
    if (o == null) throw new IllegalArgumentException("Object shouldn't be null");
    return this.value - o.value;

  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (o == null) return false; // if o is null it's not equal to this object
    if (!(o instanceof Integer2 i)) return false;
    return this.value == i.value;
  }

  public Integer2 plus(Integer2 o) {
    // if o is null we throw an error
    if (o == null) throw new IllegalArgumentException("Object shouldn't be null");
    return new Integer2(this.value + o.value);
  }
}
