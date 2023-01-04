import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    System.out.println(list.stream().filter(a -> a < 5).map(a -> a + "").collect(Collectors.joining()));
  }
}
