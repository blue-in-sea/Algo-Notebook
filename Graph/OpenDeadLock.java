/**
 * 752. Open the Lock
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', 
 * '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to
 * be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends &  a target representing the value of the wheels that will unlock the 
 * lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation: A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * Example 2:
 *
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
 *
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation: We cannot reach the target without getting stuck.
 */
public class OpenDeadLock {
   /**
     * n = 10 is the number of slots on a wheel
     * w = 4 is the number of wheels
     * d is the number of elements in the dead-ends array.
     *
     * Time:  O( 4(d + 10^4) ) 
     * Space: O( 4(d + 10^4) ) 
     */
    public int openLock(String[] deadends, String target) {
        // corner case check
        
        Set<String> set = new HashSet<>(); // set for all deadends
        for (String end : deadends) set.add(end);
        
        String start = "0000";
        if (set.contains(start)) return -1; // corner case 

        Deque<String> queue = new ArrayDeque<>();
        queue.offer(start);

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int step = 0;
        // breath-first-search by level to calculate the min step
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                // case 1: if we found the target 
                if (curr.equals(target)) return step;

                // case 2: if we found an intermidate node
                //   if node is in the deadend, we don’t keep 
                //   otherwise, we can keep and offer to our queue
                List<String> neis = findAllNeis(curr);
                for (String nei : neis) {
                    if (set.contains(nei) || visited.contains(nei)) continue;
                    queue.offer(nei);
                    visited.add(nei);
                }
            }
            step++;
        }

        return -1;
    }

    private List<String> findAllNeis(String s) {
        List<String> neis = new ArrayList<>();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // increment by 1
            String str1 = sb.substring(0, i) + (c == '9' ? '0' : (char) (c + 1)) + sb.substring(i + 1);
            neis.add(str1);

            // decrement by 1
            String str2 = sb.substring(0, i) + (c == '0' ? '9' : (char) (c - 1)) + sb.substring(i + 1);
            neis.add(str2);
        }
        return neis;
    }
}


