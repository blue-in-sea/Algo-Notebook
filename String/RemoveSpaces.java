/**
 * 281. Remove Spaces
 * Given a string, remove all leading/trailing/duplicated empty spaces.
 * The given string is not null.
 *
 * “  a” --> “a”
 * “   I     love MTV ” --> “I love MTV”
 */
public class RemoveSpaces {
  public String removeSpaces(String input) {
    if (input.isEmpty()) {
        return input;
    }
    char[] array = input.toCharArray();
    int end = 0;
    for (int i = 0; i < array.length; i++) {
        if(array[i] == ' ' && (i == 0 || array[i - 1] == ' ')) {
            continue;
        }
        array[end++] = array[i];
    }
    if (end > 0 && array[end - 1] == ' ') {
        return new String(array, 0, end - 1);
    }
    return new String(array, 0, end);
  }
}
