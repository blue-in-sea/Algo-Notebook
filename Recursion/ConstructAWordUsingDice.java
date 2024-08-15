// https://leetcode.com/discuss/interview-question/267985/google-interview-construct-a-word-using-dice
class ConstructAWordUsingDice {
    public boolean combo(Character[][] dice, String word) {
        List<Set<Character>> words = new ArrayList<>();
        for (Character[] d : dice) {
            Set<Character> curr = new HashSet<>(Arrays.asList(d));
            words.add(curr);
        }

        Set<Integer> visited = new HashSet<>();
        char[] letter = word.toCharArray();
        return helper(0, visited, letter, words);
    }


    private boolean helper(int i, Set<Integer> visited, char[] word, List<Set<Character>> words) {
        if (visited.size() == word.length)
            return true;

        for (int k = 0; k < words.size(); k++) {
            Set<Character> curr = words.get(k);
            if (visited.contains(k)) continue;

            if (curr.contains(word[i])) {
                visited.add(k);
                if (helper(i + 1, visited, word, words)) return true;
                visited.remove(k);
            }
        }
        return false;
    }
}
