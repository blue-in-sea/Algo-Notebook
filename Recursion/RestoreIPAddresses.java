/**
 * 93. Restore IP Addresses
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * A valid IP address consists of exactly four integers (each integer is between 0 and 255) separated by single points.
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */
class RestoreIPAddresses {
    /**
     * Algo: DFS High Level
     * 1. How many levels in the recursion tree? What does it store on each level?
     *    4 levels, since each level a chunk of ip-address
     *    For each level, it selects a position to place (.)
     * 2. How many different states should we put on each level
     *    1, 2, 3 states
     *    But might be less depend on the relation between offset and ip.length
     *
     * Input: s = "25525511135"
     * Expected: [["255.255.11.135", "255.255.111.35"]]
     *
     *                                               255255511135
     *                                            /            |              \
     * Level 1                             2.                             255.
     *                                    /     |     \    …                /   |    \
     * Level 2                   2.5.                                            255.255.
     *                             /       \                                              /    |    \
     * Level 3             2.5.5.  2.5.55.                                   255.255.11   255.255.111.
     *                         /
     * Level 4      2.5.5.2.
     *                 (return)
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        StringBuilder sb = new StringBuilder(); // path
        dfs(s.toCharArray(), res, sb, 0, 0);
        return res;
    }

    private void dfs(char[] ip, List<String> res, StringBuilder sb, int offset, int level) {
        // base case
        if (level == 4) {
            if (sb.length() == ip.length + 4) {
                res.add(sb.substring(0, sb.length() - 1));
                return;
            }
        }

        // state 1: one char in front of the current dot
        if (offset < ip.length) {
            sb.append(ip[offset]).append('.');
            dfs(ip, res, sb, offset + 1, level + 1);
            sb.delete(sb.length() - 2, sb.length());
        }

        // state 2: two char in front of the current dot
        if (offset + 1 < ip.length) {
            char a = ip[offset];
            char b = ip[offset + 1];
            if (a != '0') {
                sb.append(a).append(b).append('.');
                dfs(ip, res, sb, offset + 2, level + 1);
                sb.delete(sb.length() - 3, sb.length());
            }
        }

        // state 3: three char in front of the current dot
        if (offset + 2 < ip.length) {
            // valid ip range [0, 255]
            char a = ip[offset];
            char b = ip[offset + 1];
            char c = ip[offset + 2];
            if (a == '1' ||
                    a == '2' && b >= '0' && b <= '4' ||
                    a == '2' && b >= '0' && b <= '5' && c >= '0' && c <= '5') {
                sb.append(a).append(b).append(c).append('.');
                dfs(ip, res, sb, offset + 3, level + 1);
                sb.delete(sb.length() - 4, sb.length());
            }
        }
    }
}