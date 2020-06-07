class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ladders = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();  // bfs - build graph
        Map<String, Integer> distance = new HashMap<>();    // bfs - store distance

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return ladders;
        dict.add(beginWord);

        bfs(graph, distance, endWord, beginWord, dict);  // end to start 

        List<String> path = new ArrayList<String>();
        dfs(ladders, path, beginWord, endWord, distance, graph);
        
        return ladders;
    }

    private void dfs(List<List<String>> ladders, List<String> path, String curr, String end,
                     Map<String, Integer> distance, Map<String, List<String>> graph) {
        path.add(curr);
        // base case 
        if (curr.equals(end)) {
            ladders.add(new ArrayList<>(path));
        } else{
            for (String next : graph.get(curr)) {
                if (distance.containsKey(next) && distance.get(curr) == distance.get(next) + 1) { 
                    dfs(ladders, path, next, end, distance, graph);
                }
            }
        }

        // back-tracking
        path.remove(path.size() - 1);
    }

    private void bfs(Map<String, List<String>> graph, Map<String, Integer> distance, 
                     String start, String end, Set<String> dict) {
        for (String word : dict) {
            graph.put(word, new ArrayList<>());
        }

        Deque<String> queue = new ArrayDeque<>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            List<String> nextList = expand(curr, dict);
            for (String next : nextList) {
                graph.get(next).add(curr);
                
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(curr) + 1);
                    queue.offer(next);
                }
            }
        }
    }

    private List<String> expand(String curr, Set<String> dict) {
        List<String> nextList = new ArrayList<>();
        for (int i = 0; i < curr.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != curr.charAt(i)) {
                    String next = curr.substring(0, i) + c + curr.substring(i + 1);
                    if (dict.contains(next)) {
                        nextList.add(next);
                    }
                }
            }
        }

        return nextList;
    }
}


/**
 * beginWord = hit, endWord = cog. (require endWord has to be in the wordList)
 * Input wordList: [hot, dot, dog, lot, log, cog]
 *
 * Builder Graph for the word list
 * <K = word, V = replace one char word in wordList>
 * lot: [log, dot, hot]
 * hit: [hot]
 * log: [cog, dog, lot]
 * dot: [dog, lot, hot]
 * cog: [dog, log]
 * hot: [dot, lot, hit]
 * dog: [cog, log, dot]
 *
 * Get distance to the end word
 * <K = word, V = distance to the end>
 * lot: 2
 * hit: 4
 * log: 1
 * dot: 2
 * cog: 0
 * hot: 3
 * dog: 1
 *
 * Output ladders: [[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
 */
