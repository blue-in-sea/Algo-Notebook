class LongestSubstringwithAtLeastKRepeatingCharacters {
    // Method 1: Silding Window
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (freq.get(s.charAt(i)) < k) {
                int leftPart = longestSubstring(s.substring(0, i), k);
                int rightPart = longestSubstring(s.substring(i + 1), k);
                return Math.max(leftPart, rightPart);
            }
        }

        return s.length();
    }



    // Method 2: DFS
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (freq.get(s.charAt(i)) < k) {
                int leftPart = longestSubstring(s.substring(0, i), k);
                int rightPart = longestSubstring(s.substring(i + 1), k);
                return Math.max(leftPart, rightPart);
            }
        }

        return s.length();
    }
}
