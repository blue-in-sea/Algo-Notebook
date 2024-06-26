/**
 * 151. Reverse Words in a String
 * Given an input string s, reverse the order of the words.
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string
 * should only have a single space separating the words. Do not include any extra spaces.
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * Assumptions:
 * 1) The words are separated by one space character
 * 2) If there is a leading or trailing space: (keep) or (trim)
 * 3) input is not null
 */
public class ReverseWordsInASentenceI {
    // Time: O(n) only looping
    // Space: O(1) char arr in-place operation

    /**
     * input = I Love Yahoo
     *        [i ...       j] leverage the reverseString 双指针首位相向而行
     *
     * Step1: reverse the whole sentence                     →    oohaY evoL I
     * Step2: reverse every single word (two pointers)       →    Yahoo Love I
     *
     * Two Pointer:
     * 1) start: store the start index of each word
     * 2) i: linear scan to find the end index of each word
     *
     * Initialize: s = i = 0
     * First, reverse the entire sentence
     * Second, looping the sentence again to reverse every single word
     * case 1	i find the start index of each word, start = i
     * case 2 	i find the end index of each word, reverse the word: reverse(s, start, i)
     */
    public String reverseWords(String s) {

        // since string is immutable, we best to convert it to char array to do the swap in-place 
        char[] array = s.toCharArray();

        // 1. reverse the entire sentence
        reverse(array, 0, s.length() - 1);

        int start = 0;
        // 2. reverse the each of the words 
        for (int i = 0; i < array.length; i++) {
            // find the start index of each word 
            if (array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) start = i;
            // move i to the end index of this word and 
            // reverse this segment of the sentence 
            if (array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) reverse(array, start, i);
        }
        
        return new String(array);

        // Below is for two sepcial test cases in LC:
        // 1) if the words are seperated by more than one space character => keep only one space 
        // 2) if there is leading or tailing space => trim

        // return trimStr(s);
    }

    private void reverse(char[] array, int i, int j) {
        while (i < j) {
            swap(array, i++, j--);
        }
    }

    private void swap(char[] array, int a, int b) {
        char tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    // Assumptions:
    // 1) The words are separated by one space character
    // 2) If there is a leading or trailing space: (keep) or (trim)
    // 3) input is not null
    private Srting trimStr(String s) {
        char[] arr = s.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ' && arr[i - 1] == ' ') continue;
            sb.append(arr[i]);
        }
        
        return sb.toString();
    }
}
