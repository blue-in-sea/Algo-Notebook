public class TopKFrequentWords {
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
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<Map.Entry<String, Integer>>(k,
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e1.getValue().compareTo(e2.getValue());
                    }
                });

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
