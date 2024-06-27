/**
 * 49. Group Anagrams
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
public class GroupAnagrams {
    /**
     * Method 1: HashMap + Sort (Interview!!)
     *           <key : sorted(word), value : a list of anagrams to this word> 
     * 
     * Time: O(klogk n) where n is the size of the input array
     *       and k is the max length of all the strings (for sorting)
     * Space: O(kn) for the size of map
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            
            String key = String.valueOf(ca);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(new String(s));
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Method 2: HashMap + count[i] better time complexity than sorting
     * step-1: build the frequency table using count-array to store each char's appearance
     *        of each strings, convert the entire count-table as key for hash map's key
     * step-2: add the anagrams to the value list of the map if they share the same keyStr
     * step-3: convert the map's value into the return result in a list of array
     *
     * Time: O(nk)
     * Space: O(kn) for the size of map
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) { // const time 
                sb.append('#').append(count[i]);
            }

            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}



