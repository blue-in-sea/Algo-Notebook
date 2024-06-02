public class ReverseWordsInASentenceI {
    public String reverseWords(String s) {
        // Assumptions:
        // 1) The words are separated by one space character
        // 2) If there is a leading or trailing space: (keep) or (trim)
        // 3) input is not null

        // since string is immutable, we best to convert it to char array to do the swap in-place 
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

        // This is for two sepcial test cases in LC:
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

    // This trim & format the final string if required 
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
