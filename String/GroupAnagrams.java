public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams soln = new GroupAnagrams();
        String[] arr = {"eat","tea","tan","ate","nat","bat"};
        soln.groupAnagramsSort(arr);

    }
    /**
     * Time: O(kn) where n is the size of the input array
     *       and k is the max length of all the strings
     * Space: O(kn) for HashMap
     *
     * step-1: build the frequency table using count-array to store each char's appearance
     *        of each strings, convert the entire count-table as key for hash map's key
     * step-2: add the anagrams to the value list of the map if they share the same keyStr
     * step-3: convert the map's value into the return result in a list of array
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
            for (int i = 0; i < 26; i++) {
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

    /**
     * Time: O(klogk n) where n is the size of the input array
     *       and k is the max length of all the strings
     * Space: O(kn) for HashMap
     */
    public List<List<String>> groupAnagramsSort(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            String key = String.valueOf(ca);
            /**
             * System.out.println("keyStr: " + key);
             * keyStr: aet
             * keyStr: aet
             * keyStr: ant
             * keyStr: aet
             * keyStr: ant
             * keyStr: abt
             * valueOf(char[] data)
             * Returns the string representation of the char array argument.
             */

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}


