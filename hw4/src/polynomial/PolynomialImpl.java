package polynomial;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class PolynomialImpl implements Polynomial {

  private final TreeMap<Integer, Integer> terms;

  public PolynomialImpl() {
    terms = new TreeMap<>(Collections.reverseOrder());
  }

  public PolynomialImpl(String s) {
    if (s == null) throw new IllegalArgumentException("Null input!");
    terms = parsePolynomial(s);
  }

  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other == null) throw new IllegalArgumentException("Null input!");
    TreeMap<Integer, Integer> temp = new TreeMap<>(terms), terms2 = parsePolynomial(other.toString());
    for (Map.Entry<Integer, Integer> entry : terms2.entrySet()) {
      int power = entry.getKey(), coefficient = entry.getValue();
      int val = temp.getOrDefault(power, 0);
      if (val + coefficient == 0) temp.remove(power);
      else temp.put(power, val + coefficient);
    }
    return new PolynomialImpl(mapToString(temp));
  }

  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) throw new IllegalArgumentException("Negative power is passed!");
    int val = terms.getOrDefault(power, 0);
    if (val + coefficient == 0) terms.remove(power);
    else terms.put(power, val + coefficient);
  }

  public boolean isSame(Polynomial poly) {
    return this.toString().equals(poly.toString());
  }

  public double evaluate(double x) {
    double ret = 0;
    for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
      int power = entry.getKey(), coefficient = entry.getValue();
      ret += Math.pow(x, power) * coefficient;
    }
    return ret;
  }

  public int getCoefficient(int power) {
    return terms.getOrDefault(power, 0);
  }

  public int getDegree() {
    return terms.isEmpty() ? 0 : terms.firstKey();
  }

  @Override
  public String toString() {
    return mapToString(terms);
  }

  private TreeMap<Integer, Integer> parsePolynomial(String s) {
    TreeMap<Integer, Integer> terms = new TreeMap<>(Collections.reverseOrder());
    String[] arr = s.split(" ");
    for (String term : arr) {
      String[] pair = term.split("x\\^");
      int coefficient, power;
      if (pair.length == 1) {
        coefficient = Integer.parseInt(term);
        power = 0;
      } else {
        coefficient = Integer.parseInt(pair[0]);
        power = Integer.parseInt(pair[1]);
        if (power < 0) throw new IllegalArgumentException("Negative power is passed!");
      }
      terms.put(power, terms.getOrDefault(power, 0) + coefficient);
    }
    return terms;
  }

  private String mapToString(TreeMap<Integer, Integer> terms) {
    if (terms.isEmpty()) return "0";
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
      int power = entry.getKey(), coefficient = entry.getValue();
      if (!sb.isEmpty()) {
        sb.append(" ");
        if (coefficient > 0) sb.append("+");
      }
      sb.append(coefficient);
      if (power > 0) sb.append("x^").append(power);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Polynomial polynomial1 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    System.out.println(polynomial1);
    Polynomial polynomial2 = new PolynomialImpl("4x^3 +3x^13 -5 +2x^3 +100x^1");
    System.out.println(polynomial2);
    System.out.println(polynomial1.add(polynomial2));
  }
}
