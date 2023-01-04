package listadt;

import java.util.Objects;

public class ListADTUtilities {
  /**
   * Create a new instance of the ListADT that contains all the specified elements in the order they
   * appeared in elements. This method throws an IllegalArgumentException if elements contains one or
   * more null values.
   *
   * @param elements the specified elements that shouldn't contain null values
   * @return a new instance of the ListADT that contains all the specified elements
   */
  public static <T> ListADT<T> toList(T[] elements) {
    ListADT<T> res = new ListADTImpl<>();
    for (T e : elements) {
      if (e == null)
        throw new IllegalArgumentException("Elements shouldn't be null!");
      res.addBack(e);
    }
    return res;
  }

  /**
   * Add the specified elements to the end of the specified list. Elements should be added in the same
   * order they appear in elements. This method throws an IllegalArgumentException if elements contains
   * one or more null values.
   *
   * @param list the specified list
   * @param elements the specified elements
   */
  @SafeVarargs
  public static <T> void addAll(ListADT<T> list, T... elements) {
    for (T e : elements) {
      if (e == null)
        throw new IllegalArgumentException("Elements shouldn't be null");
      list.addBack(e);
    }
  }

  /**
   * Return the number of elements in the specified list equal to the specified element.
   *
   * @param list the specified list
   * @param element the specified element
   * @return the number of equal elements
   */
  public static <T> int frequency(ListADT<T> list, T element) {
    int freq = 0;
    for (int i = 0; i < list.getSize(); i++) {
      if (Objects.equals(element, list.get(i))) freq++;
    }
    return freq;
  }

  /**
   * Return true if the two lists have no elements in common. This method throws an IllegalArgumentException
   * if either list is null or if either list contains a null element.
   *
   * @param one the first specified list
   * @param two the second specified list
   * @return if the two lists have no elements in common
   */
  public static boolean disjoint(ListADT<?> one, ListADT<?> two) {
    if (one == null || two == null)
      throw new IllegalArgumentException("Null input!");
    for (int i = 0; i < one.getSize(); i++) {
      if (one.get(i) == null) throw new IllegalArgumentException("Elements shouldn't be null!");
      for (int j = 0; j < two.getSize(); j++) {
        if (two.get(j) == null) throw new IllegalArgumentException("Elements shouldn't be null!");
        if (Objects.equals(one.get(i), two.get(j))) return false;
      }
    }
    return true;
  }

  /**
   * Return true if the two lists are equal. Two lists are equal if they have the same elements in
   * the same order. If either list is null, or if either list contains a null element, this method
   * will throw an IllegalArgumentException.
   *
   * @param one the first specified list
   * @param two the second specified list
   * @return if the two lists are equal
   */
  public static boolean equals(ListADT<?> one, ListADT<?> two) {
    if (one == null || two == null)
      throw new IllegalArgumentException("Null input!");
    int m = one.getSize(), n = two.getSize();
    if (m != n) return false;
    for (int i = 0; i < n; i++) {
      if (one.get(i) == null || two.get(i) == null)
        throw new IllegalArgumentException("Elements shouldn't be null!");
      if (!Objects.equals(one.get(i), two.get(i))) return false;
    }
    return true;
  }

  /**
   * Reverse the order of the elements in the specified list
   *
   * @param list the specified list
   */
  public static void reverse(ListADT<?> list) {
    reverseHelper(list);
  }

  private static <T> void reverseHelper(ListADT<T> list) {
    ListADT<T> temp = new ListADTImpl<>();
    while (list.getSize() > 0) {
      T e = list.get(0);
      list.remove(e);
      temp.addFront(e);
    }
    for (int i = 0; i < temp.getSize(); i++)
      list.addBack(temp.get(i));
  }

  /**
   * Swap the elements at the specified position in the specified list. This method will throw an
   * IndexOutOfBoundsException if either i or j are out of range.
   *
   * @param list the specified list
   * @param i the first specified index
   * @param j the second specified index
   */
  public static void swap(ListADT<?> list, int i, int j) {
    int n = list.getSize();
    if (i < 0 || j < 0 || i >= n || j >= n)
      throw new IndexOutOfBoundsException("Index out of range!");
    if (i == j) return;
    swapHelper(list, Math.min(i, j), Math.max(i, j));
  }

  private static <T> void swapHelper(ListADT<T> list, int i, int j) {
    ListADT<T> temp = new ListADTImpl<>();
    T a = list.get(i), b = list.get(j);
    for (int k = 0; list.getSize() > 0; k++) {
      T e = list.get(0);
      list.remove(e);
      if (k == i) temp.addBack(b);
      else if (k == j) temp.addBack(a);
      else temp.addBack(e);
    }
    for (int k = 0; k < temp.getSize(); k++)
      list.addBack(temp.get(k));
  }
}
