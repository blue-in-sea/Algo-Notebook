/**
 * 269. Alien Dictionary
 * There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in 
 * words are sorted lexicographically by the rules of this new language.
 *
 * If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of 
 * letters, return "".
 *
 * Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing 
 * order by the new language's rules. If there are multiple solutions, return any of them.
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 *
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 * Constraints:
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 */
class AlienDictionary {
    // Let n be number of words, and m be the average len of a word
    // Let v be the number of vertax, and e be the number of edges 

    // Time: O(nm + (V+E))
    // Space: O(V + E)

    // buldGraph() - Time: O(nm), Space: O(V+E)
    // getIndegree - Time: O(V+E), Space: O(V)
    // TSorting    - Time: O(V+E), Space: O(V)
    
    public String alienOrder(String[] words) {
        // corner case: if the order is invalid, return an empty string.
        if (words == null || words.length == 0) {
            return new String();
        }
        // corner case: word2 is not a prefix of word1
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].length() > words[i + 1].length() 
                && words[i].startsWith(words[i + 1])) {
                return new String();
            }
        }
        
        Map<Character, Set<Character>> graph = buildGraph(words);
        
        Map<Character, Integer> indegree = getIndegree(graph);
        
        // There may be multiple valid order of letters, return the
        // smallest in normal lexicographical order [PriorityQueue]
        // There may be multiple valid order of letters, return any
        // one of them is fine [FIFO Queue]
        Deque<Character> queue = new ArrayDeque<>();
        for (Character c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character cur = queue.poll();
            sb.append(cur);  // when generate, add to res
            
            for (Character nei : graph.get(cur)) {
                indegree.put(nei, indegree.get(nei) - 1);
                
                if (indegree.get(nei) == 0) {
                    queue.offer(nei);
                }
            }
        }
        

        return sb.length() == graph.size() ? sb.toString() : new String();
    }

    private Map<Character, Set<Character>> buildGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        // intialize vertax 
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                
                graph.computeIfAbsent(c, k -> new HashSet<>());
            }
        }
        
        // process edges 
        for (int i = 0; i < words.length - 1; i++) {
            
            int j = 0;
            while (j < words[i].length() && j < words[i + 1].length()) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    // find a directional egde
                    graph.get(words[i].charAt(j)).add(words[i + 1].charAt(j));
                    break;
                }
                j++; // process to the next char
            }
        }

        return graph;
    }
    
    private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();
        
        for (Character u : graph.keySet()) {
            indegree.put(u, 0);
        }
        
        for (Character u : graph.keySet()) {
            for (Character v : graph.get(u)) {
                indegree.put(v, indegree.get(v) + 1);
            }
        }
        
        return indegree;
    }
}
