// https://leetcode.com/discuss/interview-question/267985/google-interview-construct-a-word-using-dice
/**
 * Given a word of length n and n six-sided dice with a character in each side. Find out if this word can be
 * constructed by the set of given dice.
 *
 * Input:
 * word = "hello"
 * dice = [[a, l, c, d, e, f], [a, b, c, d, e, f], [a, b, c, h, e, f], [a, b, c, d, o, f], [a, b, c, l, e, f]]
 * Output: true
 * Explanation: dice[2][3] + dice[1][4] + dice[0][1] + dice[4][3] + dice[3][4]
 *
 * Input:
 * word = "hello"
 * dice = [[a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f]]
 * Output: false
 *
 * Input:
 * word = "aaaa"
 * dice = [[a, a, a, a, a, a], [b, b, b, b, b, b], [a, b, c, d, e, f], [a, b, c, d, e, f]]
 * Output: false
 */
class ConstructAWordUsingDice {
    // Time: O(n!) all permutation
    // Space: O(n) for visited sets 
    public boolean combo(Character[][] dice, String word) {
        List<Set<Character>> dict = new ArrayList<>();
        for (Character[] d : dice) {
            Set<Character> curr = new HashSet<>(Arrays.asList(d));
            dict.add(curr);
        }

        Set<Integer> visited = new HashSet<>();
        char[] letter = word.toCharArray();
        return helper(0, visited, letter, dict);
    }

    private boolean helper(int i, Set<Integer> visited, char[] word, List<Set<Character>> dict) {
        if (visited.size() == word.length)
            return true;

        for (int k = 0; k < dict.size(); k++) {
            Set<Character> curr = dict.get(k);
            if (visited.contains(k)) continue;

            if (curr.contains(word[i])) {
                visited.add(k);
                if (helper(i + 1, visited, word, dict)) return true;
                visited.remove(k);
            }
        }
        return false;
    }
}
