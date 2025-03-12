public class KthClosestPointToOrigin {
  
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue((o1, o2) -> distance(o1, a, b , c) - distance(o1, a, b , c));
        Set<List<Integer>> visited = new HashSet<>();
        List<Integer> cur = Arrays.asList(0, 0, 0);

        visited.add(cur);
        minHeap.offer(cur);

        while (k > 0) {
            cur = minHeap.poll();

            List<Integer> n = Arrays.asList(cur.get(0) + 1, cur.get(1), cur.get(2));
            if (n.get(0) < a.length && visited.add(n)) {
                minHeap.offer(n);
            }

            n = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2));
            if (n.get(1) < b.length && visited.add(n)) {
                minHeap.offer(n);
            }

            n = Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
            if (n.get(2) < c.length && visited.add(n)) {
                minHeap.offer(n);
            }

            k--;
        }

        // at last, we replace the index with the actual value in a, b, c
        cur.set(0, a[cur.get(0)]);
        cur.set(1, b[cur.get(1)]);
        cur.set(2, c[cur.get(2)]);
        return cur;
    }

    private long distance(List<Integer> point, int[] a, int[] b, int[] c) {
        long d = 0;
        d += a[point.get(0)] * a[point.get(0)];
        d += b[point.get(1)] * b[point.get(1)];
        d += c[point.get(2)] * c[point.get(2)];
        return d;
    }
  
}
