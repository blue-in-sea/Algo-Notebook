public class Solution {
  public int ladderLength(String start, String end, List<String> dict) {
    if (dict == null) {
      return 0;
    }

    if (start.equals(end)) {
      return 1;
    }

    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();

    queue.offer(start);
    visited.add(start);

    int step = 1;
    while(!queue.isEmpty()) {
      step++;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String cur = queue.poll();
        for (String nextWord : getNextWords(cur, dict)) {
          if (visited.contains(nextWord)) {
            continue;
          }

          if (nextWord.equals(end)) {
            return step;
          }

          queue.offer(nextWord);
          visited.add(nextWord);
        }
      }
    }

    return 0;  // if no path found
  }

  private String replace(String s, int index, char c) {
    char[] word = s.toCharArray();
    word[index] = c;
    return new String(word);
  }

  private List<String> getNextWords(String word, List<String> dict) {
    List<String> list = new ArrayList<>();
    for (char c = 'a'; c <= 'z'; c++) {
      for (int i = 0; i < word.length(); i++) {
        if (c == word.charAt(i)) {
          continue;
        }
        String nextWord = replace(word, i, c);
        if (dict.contains(nextWord)) {
          list.add(nextWord);;
        }
      }
    }
    return list;
  }
}
