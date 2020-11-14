public class ReverseWordsInASentenceI {
    public String reverseWords(String s) {
        // Assumptions:
        // 1) The words are separated by one space character
        // 2) If there is a leading or trailing space: (keep) or (trim)
        // 3) input is not null

        // since string is immutable, we best to convert it to char array 
        // when doing the manipulation on the given string 
        char[] array = s.toCharArray();

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

    }

    private void reverse(char[] array, int i, int j) {
        while (i < j) {
            swap(array, i++, j--);
        }
    }
    
    // ===== LC test case =====

  
}
