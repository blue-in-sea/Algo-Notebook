public ConfusingNumber {
    public static boolean confusingNumberMap(int num) {
        Map<Integer, Integer> map = new HashMap();
        map.put(1,1);
        map.put(0,0);
        map.put(6,9);
        map.put(8,8);
        map.put(9,6);

        int temp = num;
        int rot = 0;
        while (temp > 0){
            int d = temp % 10;
            if (!map.containsKey(d)) return false;
            rot = 10 * rot + map.get(d);
            temp /= 10;
        }

        return rot != num;
    }

    public static boolean confusingNumberArr(int num) {
        int[] map = new int[] {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

        int temp = num;
        int rot = 0;
        while (temp > 0) {
            int d = temp % 10;
            if(map[d] == -1) return false;
            rot = rot * 10 + map[d];
            temp /= 10;
        }

        return rot != num;
    }
}
