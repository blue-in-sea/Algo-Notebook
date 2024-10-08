class 4Sum {
    public List<List<Integer>> fourSum(int[] arr, int k) {
        List<List<Integer>> res = new ArrayList<>();

        if (arr == null || arr.length <= 3) return res;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            for (int j = i + 1; j < arr.length - 2; j++) {

                int l = j + 1, r = arr.length - 1;
                while (l < r) {
                    long tmp = arr[l] + arr[r];

                    if (arr[i] + arr[j] + tmp == k) {
                        res.add(Arrays.asList(arr[i], arr[j], arr[l], arr[r]));
                        l++;
                        r--;

                        while (l < r && arr[l] == arr[l - 1]) l++;
                        while (l < r && arr[r] == arr[r + 1]) r--;
                    } else if (arr[i] + arr[j] + tmp < k) {
                        l++;
                    } else {
                        r--;
                    }
                }

                while (j + 1 < arr.length && arr[j] == arr[j + 1]) j++;
            }
        }

        return res;
    }
}
