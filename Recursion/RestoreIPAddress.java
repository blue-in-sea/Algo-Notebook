class Solution {
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
        if (level == 4) {
            if (sb.length() == ip.length + 4) {
                res.add(sb.substring(0, sb.length() - 1));
                return;
            }
        }

        if (offset < ip.length) {
            sb.append(ip[offset]).append('.');
            dfs(ip, res, sb, offset + 1, level + 1);
            sb.delete(sb.length() - 2, sb.length());
        }

        if (offset + 1 < ip.length) {
            char a = ip[offset];
            char b = ip[offset + 1];
            if (a != '0') {
                sb.append(a).append(b).append('.');
                dfs(ip, res, sb, offset + 2, level + 1);
            }
            sb.delete(sb.length() - 3, sb.length());
        }

        // state 3: three char in front of each dot
        if (offset + 2 < ip.length) {
            // valid ip range [0, 255]
            char a = ip[offset];
            char b = ip[offset + 1];
            char c = ip[offset + 2];
            if (a == '1' ||
                    a == '2' && b >= '0' && b <= '4' ||
                    a == '2' && b >= '0' && b <= '4' && c >= '0' && c <= '5') {
                sb.append(a).append(b).append(c).append('.');
                dfs(ip, res, sb, offset + 3, level + 1);
                sb.delete(sb.length() - 4, sb.length());
            }
        }
    }
}