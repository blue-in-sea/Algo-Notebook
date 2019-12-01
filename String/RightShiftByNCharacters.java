public class RightShiftByNCharacters {
  public String rightShift(String input, int n) {
    if (input == null || input.length() <= 1) {
      return input;
    }
    char[] array = input.toCharArray();
    n %= array.length;
    reverse(array, array.length - n, array.length - 1);
    reverse(array, 0, array.length - n - 1);
    reverse(array, 0, array.length - 1);
    return new String(array);
  }
  public void reverse(char[] array, int left, int right) {
    while (left < right) {
      char tmp = array[left];
      array[left] = array[right];
      array[right] = tmp;
      left++;
      right--;
    }
  }
}
