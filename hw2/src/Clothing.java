/**
 * An abstract class for simulating a piece of clothing.
 */
public abstract class Clothing {
  protected String adjective, noun;
  protected int attack, defense;

  public String toString() {
    return adjective + " " + noun + " - defense strength: " + defense + ", attack strength: " + attack;
  }

  public abstract Clothing combine(Clothing other);

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (o.getClass() != this.getClass()) return false;
    Clothing other = (Clothing) o;
    return other.adjective.equals(this.adjective) && other.noun.equals(this.noun)
            && other.attack == this.attack && other.defense == this.defense;
  }
}
