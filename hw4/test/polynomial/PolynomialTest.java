package polynomial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

  private final Polynomial p1 = new PolynomialImpl("3x^4 -5x^3 +2x^1 -4");

  @Test
  void testAdd() {
    Polynomial p2 = new PolynomialImpl("2x^3 +2x^2 +4");
    assertEquals("3x^4 -3x^3 +2x^2 +2x^1", p1.add(p2).toString());
    Polynomial p3 = new PolynomialImpl("5x^3 +10x^1 +8");
    assertEquals("3x^4 +12x^1 +4", p1.add(p3).toString());
  }

  @Test
  void testAddTerm() {
    Polynomial p2 = new PolynomialImpl("3x^4 -5x^3 +2x^1 -4");
    p2.addTerm(5, 3);
    assertEquals("3x^4 +2x^1 -4", p2.toString());

    Polynomial p3 = new PolynomialImpl();
    assertEquals("0", p3.toString());
    p3.addTerm(4, 1);
    p3.addTerm(2, 5);
    p3.addTerm(-3, 2);
    p3.addTerm(-10, 0);
    assertEquals("2x^5 -3x^2 +4x^1 -10", p3.toString());
  }

  @Test
  void testIsSame() {
    Polynomial p2 = new PolynomialImpl("-5x^3 +3x^4 -4 +2x^1");
    assertTrue(p1.isSame(p2));
    Polynomial p3 = new PolynomialImpl("-5x^2 +3x^4 -4 +2x^1");
    assertFalse(p1.isSame(p3));
  }

  @Test
  void testEvaluate() {
    assertEquals(40.0625, p1.evaluate(2.5), 1e-5);
    assertEquals(-4, p1.evaluate(0), 1e-5);
  }

  @Test
  void testGetCoefficient() {
    assertEquals(3, p1.getCoefficient(4));
    assertEquals(-5, p1.getCoefficient(3));
    assertEquals(0, p1.getCoefficient(2));
    assertEquals(2, p1.getCoefficient(1));
    assertEquals(-4, p1.getCoefficient(0));
  }

  @Test
  void testGetDegree() {
    assertEquals(4, p1.getDegree());
    Polynomial p2 = new PolynomialImpl("2x^12 -3x^10 +4x^8 -10");
    assertEquals(12, p2.getDegree());
    Polynomial p3 = new PolynomialImpl();
    assertEquals(0, p3.getDegree());
  }
}