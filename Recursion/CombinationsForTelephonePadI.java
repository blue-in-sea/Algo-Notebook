/**
 * 272. Combinations For Telephone Pad I
 * Given a telephone keypad, and an int number, print all words which are possible by pressing these numbers, the 
 * output strings should be sorted.
 *
 * {0 : "", 1 : "", 2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 : "pqrs", 8 : "tuv", 9 : "wxyz"}
 *
 * Assumptions: The given number >= 0
 *
 * if input number is 231, possible words which can be formed are: [ad, ae, af, bd, be, bf, cd, ce, cf]
 */
public class CombinationsForTelephonePadI {
    private final String[] KEYS = {
        "",     //0
        "",     //1
        "abc",  //2
        "def",  //3
        "ghi",  //4
        "jkl",  //5
        "mno",  //6
        "pqrs", //7
        "tuv",  //8
        "wxyz"  //9
    };

    public String[] combinations(int number) {
        // Assume the given number is >= 0
        String digits = Integer.toString(number);
        List<String> res = new ArrayList<>();
        dfs(res, digits, "", 0);
        return res.toArray(new String[0]);
    }

    private void dfs(List<String> res, String digits, String prefix, int index) {
        if (index == digits.length()) {
            res.add(prefix);
            return;
        }

        String letters = KEYS[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            dfs(res, digits, prefix + letters.charAt(i), index + 1);
        }

        if (letters.length() == 0) {
            dfs(res, digits, prefix + "", index + 1);
        }
    }
}
