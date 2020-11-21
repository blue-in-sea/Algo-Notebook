public class MinimumTimeDifference {
    // finding the shortest distances between two elements in a circular array
    public int findMinDifference(List<String> timePoints) {
        // corner case 
        if (timePoints == null || timePoints.size() == 0) return 0;

        boolean[] table = new boolean[24 * 60];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(String time: timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (table[h * 60 + m]) return 0;
            min = Math.min(min, h * 60 + m);
            max = Math.max(max, h * 60 + m);
            table[h * 60 + m] = true;
        }
        int minTimeDiff = Integer.MAX_VALUE;
        int prevTime = 0;
        for (int i = min; i <= max; i++) { // set the range from min to max as an optimization
            if (table[i]) {
                if(i == min) {  
                    //the min and max is the special case, it looks like :
                    //0---min----max-----1440, so we have two directions to calculate the distance
                    minTimeDiff = Math.min(minTimeDiff, Math.min(max - min, 1440 - max + min));
                } else {
                    minTimeDiff = Math.min(minTimeDiff, i - prevTime);
                } 
                prevTime = i;
            }
        }

        return minTimeDiff;
    }
}
