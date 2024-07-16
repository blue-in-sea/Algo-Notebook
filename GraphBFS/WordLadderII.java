/**
 * 126. Word Ladder II
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is 
 * a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter. Note that beginWord does not need to be in wordList.
 * Every si for 1 <= i <= k is in wordList: sk == endWord
 *
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation 
 * sequences from beginWord to endWord, or an empty list if no such sequence exists.
 * Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 */
class WordLadderII {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        List<List<String>> ladders = new ArrayList<>();

        // optimize: convert the wordList to dict 
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(end)) return ladders;

        // 1. bfs from end to compute distance to each word
        Map<String, List<String>> graph = new HashMap<>();  // bfs to build graph
        Map<String, Integer> distance = new HashMap<>();    // bfs store distance

        dict.add(start);
        bfs(graph, distance, end, dict);  // bfs from end 

        // 2. dfs to find all possible paths from start to end
        List<String> path = new ArrayList<String>();
        path.add(start);
        dfs(ladders, path, start, end, distance, graph);
        
        return ladders;
    }

    private void dfs(List<List<String>> ladders, List<String> path, String curr, String end,
                     Map<String, Integer> distance, Map<String, List<String>> graph) {
        // base case: found a valid path from start to end
        if (curr.equals(end)) {
            ladders.add(new ArrayList<>(path));
            return;
        } 

        // recursion:
        // 1. add the a new word
        // 2. backtracking!!
        for (String next : graph.get(curr)) {   
            if (distance.containsKey(next) && distance.get(curr) == distance.get(next) + 1) { 
                path.add(next);
                dfs(ladders, path, next, end, distance, graph);
                path.remove(path.size() - 1);
            }
        }
    }

    private void bfs(Map<String, List<String>> graph, Map<String, Integer> distance, String start, Set<String> dict) {
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
