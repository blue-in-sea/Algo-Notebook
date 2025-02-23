class AlienDictionary {
    public String alienOrder(String[] words) {
        // corner case: if the order is invalid, return an empty string.
        if (words == null || words.length == 0) {
            return new String();
        }
        // corner case: if the words list only contains 1 character
        if (words.length == 1) {
            return words[0];
        }
        
        Map<Character, Set<Character>> graph = new HashMap<>();
                for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<Character>());
                }
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            // Check that word2 is not a prefix of word1.
            if (words[i].length() > words[i + 1].length() && words[i].startsWith(words[i + 1])) {
                return new String();
            }
            
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    break;
                }
                index++;
            }
        }
        
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
        
        if (sb.length() != graph.size()) {
            return new String();
        }
        
        return sb.toString();
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
