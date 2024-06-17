package Graph;

public class OpenDeadLock {
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
