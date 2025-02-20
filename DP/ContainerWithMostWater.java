class ContainerWithMostWater {
    // Key: Two pointer 
    // Area = width × height = (r−l) × min(height[l], height[r])
    // Time: O(n), Space: O(1)
    public int maxArea(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, (r - l) * Math.min(array[l], array[r]));

            if (array[l] <= array[r]) {
                // 木桶原理
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }
}
