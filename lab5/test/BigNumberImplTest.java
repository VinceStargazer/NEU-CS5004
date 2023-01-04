import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BigNumberImplTest {
  private final String[] strs = {"19834741309", "19834741309", "19834741308", "1293241742443228121312",
          "332871403128000002121", "1232121", "12102403223500", "21981029100021918", "0", "19834741309",
          "1289299100000600000000000000000122000000000000212224"};

  @Test
  public void testConstruction() {
    for (String str : strs) {
      BigNumber num = new BigNumberImpl(str);
      assertEquals(str, num.toString());
    }
    BigNumber num = new BigNumberImpl("0001201");
    assertEquals("1201", num.toString());
    num = new BigNumberImpl();
    assertEquals("0", num.toString());

    Throwable exception = assertThrows(IllegalArgumentException.class, () -> new BigNumberImpl(null));
    assertEquals("Null input!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> new BigNumberImpl("1213a"));
    assertEquals("The string does not represent a valid number!", exception.getMessage());
  }

  @Test
  public void testLength() {
    for (String str : strs) {
      BigNumber num = new BigNumberImpl(str);
      assertEquals(str.length(), num.length());
    }
  }

  @Test
  public void testShiftLeft() {
    String str = "1289";
    StringBuilder sb = new StringBuilder(str);
    BigNumber num = new BigNumberImpl(str);
    for (int i = 0; i < 6; i++) {
      num.shiftLeft(i);
      sb.append("0".repeat(i));
      assertEquals(sb.toString(), num.toString());
    }

    num = new BigNumberImpl();
    num.shiftLeft(18);
    assertEquals("0", num.toString());
  }

  @Test
  public void testShiftRight() {
    String str = "128912034812432190324321421353092109023";
    StringBuilder sb = new StringBuilder(str);
    BigNumber num = new BigNumberImpl(str);
    for (int i = 0; i < 6; i++) {
      num.shiftRight(i);
      for (int j = 0; j < i; j++) sb.deleteCharAt(sb.length() - 1);
      assertEquals(sb.toString(), num.toString());
    }

    num = new BigNumberImpl();
    num.shiftRight(18);
    assertEquals("0", num.toString());
  }

  @Test
  public void testAddDigit() {
    BigNumber num = new BigNumberImpl();
    for (int i = 0; i < 6; i++) {
      num.addDigit(0);
      assertEquals("0", num.toString());
    }

    Throwable exception = assertThrows(IllegalArgumentException.class, () -> num.addDigit(-1));
    assertEquals("Digit must be between 0 and 9!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> num.addDigit(12));
    assertEquals("Digit must be between 0 and 9!", exception.getMessage());

    String str = "12891820254534432";
    for (int i = 0; i < str.length(); i++) {
      num.addDigit(str.charAt(i) - '0');
      assertEquals(str.substring(0, i + 1), num.toString());
    }
  }

  @Test
  public void testGetDigit() {
    String str = "12891820254534432";
    BigNumber num = new BigNumberImpl(str);
    int n = str.length();
    for (int i = 0; i < n; i++)
      assertEquals(str.charAt(i) - '0', num.getDigitAt(n - 1 - i));

    Throwable exception = assertThrows(IllegalArgumentException.class, () -> num.getDigitAt(-1));
    assertEquals("The position must be between 0 and " + (n - 1) + "!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> num.getDigitAt(n));
    assertEquals("The position must be between 0 and " + (n - 1) + "!", exception.getMessage());
  }

  @Test
  public void testCopy() {
    for (String str : strs) {
      BigNumber num = new BigNumberImpl(str), num2 = num.copy();
      assertNotEquals(num, num2);
      assertEquals(num.length(), num2.length());
      assertEquals(num.toString(), num2.toString());
    }
  }

  @Test
  public void testAdd() {
    int[][] pairs = {{99998899, 12898012}, {70039432, 829002191}, {50212312, 91293821}, {1, 999999999},
            {0, 12863412}, {100230094, 0}};
    for (int[] pair : pairs) {
      int a = pair[0], b = pair[1];
      BigNumber num1 = new BigNumberImpl(Integer.toString(a)), num2 = new BigNumberImpl(Integer.toString(b));
      BigNumber sum = num1.add(num2);
      assertEquals(Integer.toString(a), num1.toString());
      assertEquals(Integer.toString(b), num2.toString());
      assertEquals(Integer.toString(a + b), sum.toString());
    }

    BigNumber num1 = new BigNumberImpl("2836422347931275423104");
    BigNumber num2 = new BigNumberImpl("9384723070543954325323");
    BigNumber num3 = num1.add(num2);

    BigInteger bi1 = new BigInteger("2836422347931275423104");
    BigInteger bi2 = new BigInteger("9384723070543954325323");
    BigInteger bi3 = bi1.add(bi2);
    assertEquals(bi3.toString(), num3.toString());
  }

  @Test
  public void testCompareTo() {
    List<BigNumber> actual = new ArrayList<>();
    List<BigInteger> expected = new ArrayList<>();
    for (String str : strs) {
      actual.add(new BigNumberImpl(str));
      expected.add(new BigInteger(str));
    }
    Collections.sort(actual);
    Collections.sort(expected);
    for (int i = 0; i < strs.length; i++)
      assertEquals(actual.get(i).toString(), expected.get(i).toString());
  }
}