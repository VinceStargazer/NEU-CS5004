import java.util.List;
import java.util.stream.Collectors;

public class HigherOrderFunctions {
  public static List<Integer> aboveN(List<Integer> list, int n) {
    return list.stream().filter(num -> num > n).collect(Collectors.toList());
  }

  public static List<String> toListString(List<Integer> list) {
    return list.stream().map(num -> num + "").collect(Collectors.toList());
  }

  public static void main(String[] args) {
    System.out.println(aboveN(List.of(4, 40, 400, 30), 40));
    System.out.println(toListString(List.of(1, 2, 3, 4)));
  }
}
