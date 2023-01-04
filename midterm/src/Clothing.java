import java.util.ArrayList;
import java.util.List;

public abstract class Clothing implements Comparable<Clothing> {
  private final List<Integer> ratings;

  public Clothing() {
    ratings = new ArrayList<>();
  }

  public void rate(int rating) {
    if (rating < 1 || rating > 5)
      throw new IllegalArgumentException("Rating must be between 1 and 5!");
    ratings.add(rating);
  }

  @Override
  public int compareTo(Clothing o) {
    int sum1 = 0, sum2 = 0;
    for (int rating : ratings) sum1 += rating;
    for (int rating : o.ratings) sum2 += rating;
    double average1 = sum1 / (double) ratings.size();
    double average2 = sum2 / (double) o.ratings.size();
    return Double.compare(average2, average1);
  }
}
