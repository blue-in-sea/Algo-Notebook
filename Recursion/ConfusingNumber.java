/**
 * 1056. Confusing Number
 * Some of the digits can be rotated by 180 degrees to form new digits.
 *
 * e.g. When 0, 1, 6, 8, 9 are rotated 180 degrees,
 * they become 0, 1, 9, 8, 6 respectively.
 *
 * When 2, 3, 4, 5 and 7 are rotated 180 degrees,
 * they become invalid.
 *
 * A confusing number is a number that when rotated 180 degrees becomes a
 * different number with each digit valid.
 * (Note that the rotated number can be greater than the original number.)
 *
 * Given a positive integer N, return ALL the confusing numbers between 1 and N inclusive.
 * 
 * Input: 6, Output: True
 * We get 9 after rotating 6, 9 is a valid number and 9!=6.
 */
public ConfusingNumber {
    // Algo
    // 1. Get each digit
    // while num > 0
    //  d = num % 10
    //  tmp /= 10
    // 2. Lookup the map & rotate

    // Time: O(L) where L is the len(num)
    // Space: O(L) where we create a new integer which will have the same number of digits as num

    // Version 1: HashMap
    public static boolean confusingNumberMap(int num) {
        Map<Integer, Integer> map = new HashMap();
        map.put(1,1);
        map.put(0,0);
        map.put(6,9);
        map.put(8,8);
        map.put(9,6);

        int tmp = num;
        int rot = 0;
        while (tmp > 0){
            int d = tmp % 10;
            if (!map.containsKey(d)) return false;
            rot = 10 * rot + map.get(d);
            tmp /= 10;
        }

        return rot != num;
    }

    /**
     * d: 9   rot: 6
     * d: 8   rot: 68
     */

    // Version 2: Arr
    public static boolean confusingNumberArr(int num) {
        int[] map = new int[] {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

        int tmp = num;
        int rot = 0;
        while (tmp > 0) {
            int d = tmp % 10;
            if(map[d] == -1) return false;
            rot = rot * 10 + map[d];
            tmp /= 10;
        }

        return rot != num;
    }
}
