/**
 * 114. 95 Percentile
 * Given a list of integers representing the lengths of urls, find the 95 percentile of all lengths (95% of the urls have lengths <= returned length).
 *
 * The maximum length of valid url is 4096
 * The list is not null and is not empty and does not contain null
 *
 * [1, 2, 3, ..., 95, 96, 97, 98, 99, 100], 
 * 95 percentile of all lengths is 95.
 */
public class 95Percentile {
    // Find the minimum L such that at leaste 95% * n of the URLs
    // have the length <= L
    // Goal：找到最小的 L 使得小于和等于 L 是 95 %
    // 初始化 if no element, L = 0  >= 0 * 0.95
    public int percentile95(List<Integer> lengths) {
        // Assume input list is not null and has size >= 1 without any null values
        // The length of possible longest URL is 4096
        int[] count = new int[4097];
        // initialize count arr with all the frequency of the length of the URLs
        for (int len : lengths) {
            count[len]++;
        }
        // sum is the left area of how many URLs [.... || L .. 5% ..]
        // has length <= L, sum has to be always smaller to 95% * n
        int sum = 0;
        int len = 0;
        while (sum < 0.95 * lengths.size()) {
            len++;
            sum += count[len];
        }
        return len;
    }
}
