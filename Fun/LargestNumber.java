class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (String a, String b) -> {
            String x = a + b;
            String y = b + a;
            return y.compareTo(x);
        });

        // Special case: if after sort, the first ele is 0
        if (arr[0].equals("0")) return arr[0];
        
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        return new String(sb);
    }
}
