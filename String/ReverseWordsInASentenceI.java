public class ReverseWordsInASentenceI {
  // Time: O(N)
  // Space: O(1)
  public String reverseWords(String input) {
    // Assumptions:
    // 1) The words are seperated by one space character
    // 2) There is no leading or tailing space
    // 3) input is not null
    char[] array = input.toCharArray();  // convert to char array, solve in-place
    reverse(array, 0, array.length - 1); // 1. reverse the entir char array

    int start = 0;
    // 2. reverse the each of the words 
    for (int i = 0; i < array.length; i++) {
      // the start index of a word 
      if (array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) {
        start = i;
      }
      // the end index of word
      if (array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) {
        reverse(array, start, i);
      }
    }
    return new String(array);
  }

  private void reverse(char[] array, int left, int right) {
    while (left < right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
        left++;
        right--;
    }
  }
}
