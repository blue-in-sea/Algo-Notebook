/**
 * 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
class LongestCommonPrefix {
    // Method 1: Horizontal scanning (OK!)
    // Time: O(S) where S is the sum of all characters in all strings
    // Space: O(1) we only used constant extra space
    public String longestCommonPrefix(String[] strArr) {
        if (strArr.length == 0) return "";

        String prefix = strArr[0];
        for (int i = 1; i < strArr.length; i++) {
            String s = strArr[i];
            while (s.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix; 
    }

    // Method 2: Vertical scanning
    // Time: O(S) where S is the sum of all characters in all strings.
    // In the worst case there will be n equal strings with length m and the algorithm performs S=m⋅n character
    // Space: O(1) we only used constant extra space
    public String longestCommonPrefix(String[] strArr) {
        if (strArr.length == 0) return "";

        String prefix = strArr[0];
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            for (int j = 1; j < strArr.length; j++) {
                if (i == strArr[j].length() || strArr[j].charAt(i) != c) {
                    return prefix.substring(0, i);
                }
            }
        }
        return prefix;
    }

    // Method 3: Binary search
    // O(S⋅logm), where S is the sum of all characters in all strings
    // The algorithm makes logm iterations, for each of them there are S=m⋅n comparisons
    // Space: O(1) we only used constant extra space
    public String longestCommonPrefix(String[] strArr) {
        if (strArr.length == 0) return "";

        int minLen = Integer.MAX_VALUE;
        for (String s : strArr) {
            minLen = Math.min(minLen, s.length());
        }

        int left = 0, right = minLen;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isCommonPrefix(strArr, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return strArr[0].substring(0, (left + right) / 2);
    }

    private boolean isCommonPrefix(String[] strArr, int len) {
        String prefix = strArr[0].substring(0, len);
        for (String s : strArr) {
            if (!s.startsWith(prefix)) {
                return false;
            }
        }
        return true;
    }

    // ================================================================================
    // Method 4: Trie
    // Time: O(s) where to build trie we need to process all char in the input str arr
    // Time: O(s) for maintaining the trie
    class TrieNode {
        public boolean isEnd;
        public Map<Character, TrieNode> children;
        public TrieNode() {
            this.children = new HashMap<>(); 
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) return;

            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                // insert a new node
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
            }
            curr.isEnd = true;
        }

        public String searchLongestPrefix(String word) {
            TrieNode curr = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (curr.children.containsKey(c) && curr.children.size() == 1 && !curr.isEnd) {
                    prefix.append(c);
                    curr = curr.children.get(c);
                } else {
                    return prefix.toString();
                }
            }

            return prefix.toString();
        }
    }

    public String longestCommonPrefix(String[] strArr) {
        if (strArr.length == 0) return "";
        if (strArr.length == 1) return strArr[0];

        Trie trie = new Trie();
        for (String s : strArr) {
            trie.insert(s);
        }

        return trie.searchLongestPrefix(strArr[0]);
    } 
}
