package listadt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListADTUtilitiesTest {
  private final Integer[] intArr = {1, 2, 3, 4, 5};
  private final String[] strArr = {"Hey,", "I love", "eating", "bananas!"};

  @Test
  public void testToList() {
    ListADT<Integer> intList = ListADTUtilities.toList(intArr);
    assertEquals(intArr.length, intList.getSize());
    for (int i = 0; i < intArr.length; i++)
      assertEquals(intArr[i], intList.get(i));

    ListADT<String> strList = ListADTUtilities.toList(strArr);
    assertEquals(strArr.length, strList.getSize());
    for (int i = 0; i < strArr.length; i++)
      assertEquals(strArr[i], strList.get(i));
  }

  @Test
  public void testAddAll() {
    ListADT<Integer> intList = ListADTUtilities.toList(new Integer[] {1, 2});
    ListADTUtilities.addAll(intList, 3, 4, 5);
    assertEquals(intArr.length, intList.getSize());
    for (int i = 0; i < intArr.length; i++)
      assertEquals(intArr[i], intList.get(i));

    ListADT<String> strList = ListADTUtilities.toList(new String[] {"Hey,", "I love"});
    ListADTUtilities.addAll(strList, "eating", "bananas!");
    assertEquals(strArr.length, strList.getSize());
    for (int i = 0; i < strArr.length; i++)
      assertEquals(strArr[i], strList.get(i));
  }

  @Test
  public void testFrequency() {
    ListADT<Integer> intList = new ListADTImpl<>();
    for (Integer i : new Integer[] {5, null, 2, 2, 3, null, 3, null, 2})
      intList.addBack(i);
    assertEquals(1, ListADTUtilities.frequency(intList, 5));
    assertEquals(2, ListADTUtilities.frequency(intList, 3));
    assertEquals(3, ListADTUtilities.frequency(intList, 2));
    assertEquals(3, ListADTUtilities.frequency(intList, null));
  }

  @Test
  public void testDisjoint() {
    ListADT<String> strList1 = ListADTUtilities.toList(strArr),
            strList2 = ListADTUtilities.toList(new String[] {"I love", "nothing but", "universe"}),
            strList3 = ListADTUtilities.toList(new String[] {"This is the", "end of", "universe", ". Right?"});
    assertFalse(ListADTUtilities.disjoint(strList1, strList2));
    assertFalse(ListADTUtilities.disjoint(strList2, strList3));
    assertTrue(ListADTUtilities.disjoint(strList1, strList3));
  }

  @Test
  public void testEquals() {
    ListADT<Integer> intList1 = ListADTUtilities.toList(intArr),
            intList2 = ListADTUtilities.toList(new Integer[] {1, 2, 3, 4, 5}),
            intList3 = ListADTUtilities.toList(new Integer[] {5, 4, 3, 2, 1}),
            intList4 = ListADTUtilities.toList(new Integer[] {1, 2, 3, 4, 5, 6});
    assertTrue(ListADTUtilities.equals(intList1, intList2));
    assertTrue(ListADTUtilities.equals(intList2, intList1));
    assertFalse(ListADTUtilities.equals(intList1, intList3));
    assertFalse(ListADTUtilities.equals(intList1, intList4));
  }

  @Test
  public void testReverse() {
    ListADT<Integer> intList = ListADTUtilities.toList(intArr);
    ListADTUtilities.reverse(intList);
    Integer[] arr = {5, 4, 3, 2, 1};
    assertEquals(arr.length, intList.getSize());
    for (int i = 0; i < intList.getSize(); i++)
      assertEquals(arr[i], intList.get(i));
  }

  @Test
  public void testSwap() {
    ListADT<Integer> intList = ListADTUtilities.toList(intArr);
    ListADTUtilities.swap(intList, 0, 4);
    Integer[] arr = {5, 2, 3, 4, 1};
    assertEquals(arr.length, intList.getSize());
    for (int i = 0; i < intList.getSize(); i++)
      assertEquals(arr[i], intList.get(i));

    ListADTUtilities.swap(intList, 4, 1);
    arr = new Integer[] {5, 1, 3, 4, 2};
    assertEquals(arr.length, intList.getSize());
    for (int i = 0; i < intList.getSize(); i++)
      assertEquals(arr[i], intList.get(i));
  }
}