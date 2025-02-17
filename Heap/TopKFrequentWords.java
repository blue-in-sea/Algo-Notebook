/**
 * 692. Top K Frequent Words
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their 
 * lexicographical order.
 *
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 
 * 4, 3, 2 and 1 respectively.
 */
public class TopKFrequentWords {
    /**
     * Time: O(NlogK)
     * Space: O(N+K) to store the frequencyMap with not more N elements and a heap with K elements.
     */
    public String[] topKFrequent(String[] combo, int k) {
        // handle the special caseof empty combo at the very beginning
        if (combo.length == 0) {
            return new String[0];
        }
        // get all the distinct string as key
        // and their frequency as value
        // Note: the freqMap has at least size 1
        Map<String, Integer> freqMap = getFreqMap(combo);
        // minHeap on the frequencies of the strings/words
        // Note: using Map.Entry as the element <T> directly so that all the 
        // operations are done mostly efficient 

        // PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x); one test case issue!! see note
        // PriorityQueue<Integer> minHeap = new PriorityQueue<>((x, y) -> x - y); one test case issue!! see note
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> 
            e1.getValue() == e2.getValue() ? e2.getKey().compareTo(e1.getKey()) : e1.getValue().compareTo(e2.getValue()));

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        return freqArray(minHeap);
    }

    // func to build our freqMap based on the input combo
    private Map<String, Integer> getFreqMap(String[] combo) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String s : combo) {
            Integer freq = freqMap.get(s);
            if (freq == null) {
                freqMap.put(s, 1);
            } else {
                freqMap.put(s, freq + 1);
            }
        }
        return freqMap;
    }

    // func to convert minHeap to our topK freq. words list
    private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
        String[] result = new String[minHeap.size()];
        for (int i = minHeap.size() - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }
}
