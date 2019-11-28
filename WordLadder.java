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
        
        HashSet<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<>();
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
