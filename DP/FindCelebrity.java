package DP;/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
// https://leetcode.com/problems/find-the-celebrity

public class FindCelebrity extends Relation {
    // Time: O(N)
    // Space: O(1)
    public int findCelebrity(int n) {
        int candidate = 0;

        // The definition of a celebrity is that all the other n - 1 people know the celebrity, 
        // but the celebrity does not know any of them.

        /**
         * Fistly, assume everyone to be a candidate, then randomly pick two, check whether a knows b:
         * 1) if a knows b, a is not a cele
         * 2) if a does not know b, b is not cele. 
         * Either way, one person is gonna get eliminated. 
         */
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        /**
         * Secondly, we have 1 person left, we confirm the candidate is a cele
         */
        if (isCelebrity(candidate, n)) {
            return candidate;
        }
        return -1;
    }
    
    private boolean isCelebrity(int candidate, int n) {
        for (int j = 0; j < n; j++) {
            if (candidate == j) continue; // Don't ask if they know themselves.
            if (knows(candidate, j) || !knows(j, candidate)) {
                return false;
            }
        }
        return true;
    }
}
