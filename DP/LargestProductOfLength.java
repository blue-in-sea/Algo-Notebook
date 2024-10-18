/**
 * 191. Largest Product Of Length
 * Given a dictionary containing many words, find the largest product of two words’ lengths, such that the two words do not share any common characters.
 *
 * Assumptions
 * The words only contains characters of 'a' to 'z'
 * The dictionary is not null and does not contains null string, and has at least two strings
 * If there is no such pair of words, just return 0
 * Examples
 *
 * dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)
 */
public class LargestProductOfLength {
    public int largestProduct(String[] dict) {
        // Assume the dict is not null and has length >= 2
        // there is no null String in the dict.
        // The words in the dict only use charcaters 'a' - 'z'
        // Get the bit mask for each of the word in the dict,
        // the bit mask is represented by the lowest 26 bits of integer.
        // each of the bit represnets one of the charcaters 'a' - 'z'

        Map<String, Integer> bitMasks = getBitMasks(dict);
        // sort the dicr by length of the words in descednig order
        Arrays.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String s0, String s1) {
                if (s0.length() == s1.length()) {
                    return 0;
                }
                return s0.length() < s1.length() ? 1 : -1;
            }
        });

        int largest = 0;
        // note the order of constructing all the paris,
        // we make our best to try largest product.
        for (int i = 1; i < dict.length; i++) {
            for (int j = 0; j < i; j++) {
                // early break if the product is already smaller than 
                // the current largest one
                int prod = dict[i].length() * dict[j].length();

                if (prod <= largest) {
                    break;
                }
                // if two words do not share any common characters,
                // the bit masks "and" result should be 0 since
                // there is not any position such that in the two bit masks
                // there are all 1
                int iMask = bitMasks.get(dict[i]);
                int jMask = bitMasks.get(dict[j]);
                if ((iMask & jMask) == 0) {
                    largest = prod;
                }
            }
        }
        return largest;
    }

    private Map<String, Integer> getBitMasks(String[] dict) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : dict) {
            int bitMask = 0;
            for (int i = 0; i < str.length(); i++) {
                // the 26 charcaters 'a' - 'z' are mapped to 0 - 25th bit.
                // to determine which bit it is for a character x,
                // use ('x' - 'b') since their values are in a consecutive range.
                // if character x exists in the word, we set the bit at
                // corresponding index to 1.
                bitMask |= 1 << (str.charAt(i) - 'a');
            }
            map.put(str, bitMask);
        }
        return map;
    }
}
