/**
 * Implemented a probabilistic Map of below APIs
 */

public interface ProbabilisticMap {
    void put(String key, String value);
    String getAndRemoveRandom(String key);
}

/**
 * Functionality
 * 1. Each key is associated with a collection of (possible dup) key
 * 2. getAndRemoveRandom(key) removes a rand element form the given key associated values, return the element
 * 3. put(key, value) adds a rand element form the given key associated values
 *
 * put(k1, v1)
 * put(k2, v2)
 * put(k1, v1)
 *
 * 1st time: return v1 with 2/3 probability; return v2 with 1/3 probability
 */


import java.util.*;

public class ProbabilisticMapListImpl implements ProbabilisticMap {

    private final Random random;
    private final Map<String, List<String>> keyToValues;

    public ProbabilisticMapListImpl(long seed) {
        this.random = new Random();
        this.keyToValues = new HashMap<>();
    }

    // APIs
    @Override
    public void put(String key, String value) {
        keyToValues.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    @Override
    public String getAndRemoveRandom(String key) {
        if (!keyToValues.containsKey(key)) {
            throw new NoSuchElementException("No values for key");
        }

        List<String> values = keyToValues.get(key);
        int size = values.size();
        int randIndex = random.nextInt(size);

        String res = values.get(randIndex);
        // swap the select value with the last index in the list for more efficiency removal
        values.set(randIndex, values.get(size - 1));

        /**
         * Similarly, for ArrayList, if you're calling ArrayList.remove(index) with the index of the last element, then that's O(1).
         * For all other indices, there's a System.arrayCopy() call that can be O(n) - but that's skipped entirely for the last element.
         */
        values.remove(size - 1);
        if (size == 0) {
            values.remove(key);
        }

        return res;
    }
}
