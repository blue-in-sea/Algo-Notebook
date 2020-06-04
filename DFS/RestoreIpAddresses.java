class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        char[] ip = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        dfs(ip, res, sb, 0, 0);
        return res;
    }

    private void dfs(char[] ip, List<String> res, StringBuilder sb, int offset, int level) {
        if (level == 4) {
            if (sb.length() == ip.length + 4) {
                res.add(sb.substring(0, sb.length() - 1));
            }
            return;
        }

        if (offset < ip.length) {
            int sbLen = sb.length();
            dfs(ip, res, sb.append(ip[offset]).append('.'), offset + 1, level + 1);
            sb.setLength(sbLen);
        }

        if (offset + 1 < ip.length) {
            char a = ip[offset];
            char b = ip[offset + 1];
            if (a != '0') {
                int sbLen = sb.length();
                dfs(ip, res, sb.append(a).append(b).append('.'), offset + 2, level + 1);
                sb.setLength(sbLen);
            }
        }

        if (offset + 2 < ip.length) {
            char a = ip[offset];
            char b = ip[offset + 1];
            char c = ip[offset + 2];
            int sbLen = sb.length();
            if (a == '1' || a == '2' && b >= '0' && b <= '4' || a == '2' && b == '5' && c >= '0' && c <= '5') {
                dfs(ip, res, sb.append(a).append(b).append(c).append('.'), offset + 3, level + 1);
            }
            sb.setLength(sbLen);
        }
    }
}
