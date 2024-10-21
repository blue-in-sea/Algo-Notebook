/**
 * Facebook | Phone screen | Split array equal sum
 * Position: Production Engineer
 *
 * You're given an array made up of positive integers. Split the given array into two smaller arrays where the sums
 * of each smaller array are equal. Print out the two smaller arrays.
 *
 * [1,2,1,1,3] -> [1,2,1] & [1,3]
 * [1,1,1,1,1,5] -> [1,1,1,1,1] & [5]
 * [5,2,3] -> [5] & [2,3]
 *
 * Corner case: print error msg if given array can't be split
 */
public class SplitArrayWithSubsetSum {
      public static List<List<Integer>> splitArr(int[] arr) {
        if (arr == null || arr.length <= 1) {
            throw new IllegalArgumentException("Can't split the input array.");
        }

        int totSum = 0;
        for (int num : arr) {
            totSum += num;
        }

        if (totSum % 2 != 0) {
            return new ArrayList<>(); // Return empty list if total sum is odd
        }

        int halfSum = totSum / 2;
        int curSum = 0;
        int splitIndex = -1;
        for (int i = 0 ; i < arr.length ; i++) {
            curSum += arr[i];
            if (curSum == halfSum) {
                splitIndex = i;
                break;
            }
        }

        return splitIndex == -1 ? new ArrayList<>() : splitByIndex(arr, splitIndex); 
        // Return empty list if no split is found
    }

    private static List<List<Integer>> splitByIndex(int[] arr, int index) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int i = 0 ; i < arr.length ; i++) {
            if (i <= index) {
                first.add(arr[i]);
            } else {
                second.add(arr[i]);
            }
        }
        res.add(first);
        res.add(second);
        return res;
    }
}
