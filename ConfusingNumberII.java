public class ConfusingNumberII {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(confusingNumberII(20));
        /** when N = 20, res = [10, 16, 18, 19, 6, 9], cnt = 6 */
    }

    public static int confusingNumberII(int N) {
        int[] arr = {0,1,6,8,9};
        int[] map = {0,1,9,8,6};

        int[] cnt = new int[1];
        cnt[0] = 0;
        dfs(N, arr, map, cnt, 0, 0, 1);
        return cnt[0];
    }

    private static void dfs(int N, int[] arr, int[] map, int[] cnt, long num, long rotatedNum, int base) {
        if (num > N) {
            return;
        }

        if (num != rotatedNum) {
            cnt[0]++;
        }

        for (int i = 0; i < arr.length; i++) {
            if (num == 0 && arr[i] == 0) {
                continue;
            }

            dfs(N, arr, map, cnt, num * 10 + arr[i], map[i] * base + rotatedNum, base * 10);
        }
    }
}
