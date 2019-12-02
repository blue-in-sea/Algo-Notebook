class Solution {
    public boolean repeatedSubstringPattern(String s) {
        
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}

/**
 * 假设字符串有n个子串构成,则拼接后的子串为 2n 个,掐头去尾后为 2n-2 个,
 * 
 * 如果此时的字符串至少包含一个原字符串, 则说明至少包含n个子串,
 * 则 2n-2 >= n, n >= 2.则说明该字符串是周期性结构,最少由两个子串构成.
 * 
 * 如果一个都不包含, 即不包含 n 个子串, 
 * 则说明 2n-2 < n, n < 2, 即 n 为 1, 也就是不符合周期性结构.
 */
