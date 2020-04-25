/* *
 * Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, 
 * and return the length of the transformation sequence. Return 0 if there is no such transformations.
 * In each transformation, you can only change one letter, and the word should still in the dictionary 
 * after each transformation.
 * 
 * Assumptions
 * 1. All words have the same length.
 * 2. All words contain only lowercase alphabetic characters.
 * 3. There is no duplicates in the word list.
 * 4.The beginWord and endWord are non-empty and are not the same.
 *
 * Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot"}
 * Output: 3
 * Explanation: git -> hit -> hot
 *
 * Method 1: BFS from end to start words. 
 * Time: O(M×N), where M is the length of words and N is the total number of words in the input word list.
 * Space Complexity: O(M×N), to store all M transformations for each of the N words.
 *
 * Method 2: BFS from end word, transform one letter in each step, and eventually to become start words. 
 * Time: O(25xM), where M is the length of words, and 26 char tables.
 */
public class WordLadder {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null) {
            return 0;
        }
        
        if (start.equals(end)) {
            return 1;
        }
        
        dict.add(start);
        dict.add(end);
        
        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);
        
        int length = 1;
        while (!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord : getNextWords(word, dict)) {
                    if (visited.contains(nextWord)) {  // dedup
                        continue;
                    }
                    if (nextWord.equals(end)) {
                        return length;
                    }
                    
                    queue.offer(nextWord);
                    visited.add(nextWord);
                }
            }
        }
        
        return 0;
    }
    
    // replace character of a string 
    private String replace(String s, int index, char c) {
        char[] word = s.toCharArray();
        word[index] = c;
        return new String(word);
    }
    
    // get connections with given word
    // given word = 'hot', dict = {'hot', 'hit', 'hog'}, it will return ['hit', 'hog']
    private List<String> getNextWords(String word, Set<String> dict) {
        List<String> nextWords = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
}
