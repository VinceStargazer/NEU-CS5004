public interface BigNumber extends Comparable<BigNumber> {
  int length();
  void shiftLeft(int shifts);
  void shiftRight(int shifts);
  void addDigit(int digit);
  int getDigitAt(int position);
  BigNumber copy();
  BigNumber add(BigNumber other);
  int compareTo(BigNumber other);
}
