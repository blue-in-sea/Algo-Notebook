### Common APIs and Utils - Java 8
### Print
1. Print an array 
```
System.out.println(Arrays.toString(array));
```

2. Print a 2D array
```
System.out.println(Arrays.deepToString(array));
```

3. Print a 1D collection: list / queue / stack
```
System.out.println(list);
```

4. Print a map 
```
map.forEach((key, value) -> System.out.println(key + ": " + value));
```

5. Print a tree
DFS
```
void dfs(TreeNode root) {
  if (root == null) return;
  // print(root.val); - preorder
  dfs(root.left);
  // print(root.val); - inorder
  dfs(root.right);
  // print(root.val); - postorder
}
```  
BFS
```
LevelOrder 
```

### Map
* `size()` - Returns the number of key-value mappings in this map.
* `clear()` - Removes all of the mappings from this map (optional operation).

* `containsKey(Object key)` - Returns true if this map maps one or more keys to the specified value.
* `containsValue(Object value)` - Returns true if this map maps one or more keys to the specified value.

* `get(Object key)` - Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
* `getOrDefault(Object key, V defaultValue)` - Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.
```

map.put(c, map.getOrDefault(c, 0) + 1);

```

* `computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)` - If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.
```

graph.computeIfAbsent(c, k -> new PriorityQueue()).add(n);

graph.computeIfAbsent(c, k -> new ArrayList<>()).add(n);

curr.getChildren().computeIfAbsent(c, n -> new TrieNode());

```

* `keySet()` - Returns a Set view of the keys contained in this map.
* `values()` - Returns a Collection view of the values contained in this map.
```
// remove all 0 values element from the map
map.values().removeIf(v -> v == 0); 
```

* `forEach(BiConsumer<? super K,? super V> action)` - Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.
```

map.forEach((k, v) -> func);

```

* `Map.Entry<K,V>` - Interface a map entry (key-value pair).
```

for (Map.Entry<Integer, Integer> e : map.entrySet()) {
   Integer k = e.getKey();
   Integer v = e.getValue();
}

```

### String
* `length()` - Returns the length of this string.
* `isEmpty()` - Returns true if, and only if, length() is 0.

* `charAt(int index)` - Returns the char value at the specified index.
* `toCharArray()` - Converts this string to a new character array.
* `substring(int beginIndex)` - Returns a string that is a substring of this string.
* `substring(int beginIndex, int endIndex)` - Returns a string that is a substring of this string.

```
"abc".substring(1,3) -> 'bc' [1, 3)
```

* `contains(CharSequence s)` - Returns true if and only if this string contains the specified sequence of char values.
* `endsWith(String suffix)` - Tests if this string ends with the specified suffix.

* `equals(Object anObject)` - Compares this string to the specified object.
* `equalsIgnoreCase(String anotherString)` - Compares this String to another String, ignoring case considerations. 
  
* `compareTo(String anotherString)` - Compares two strings lexicographically.
* `compareToIgnoreCase(String str)` - Compares two strings lexicographically, ignoring case differences.

* `indexOf(int ch)` - Returns the index within this string of the first occurrence of the specified character.
* `indexOf(String str, int fromIndex)` - Returns the index within this string of the first occurrence of the specified substring, starting at the specified index.
* `lastIndexOf(int ch)` - Returns the index within this string of the last occurrence of the specified character.
* `lastIndexOf(int ch, int fromIndex)` - Returns the index within this string of the last occurrence of the specified character, searching backward starting at the specified index.

### Queue (FIFO) "First-In, First-Out"
```
         Throws exception	Returns special value
Insert	  add(e)	         offer(e)
Remove	  remove()	      poll()
Examine	  element()	      peek()

```
* `empty()` - Returns false if non-empty
* `offer(E e)` - Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.
* `peek()` - Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
* `poll()` - Retrieves and removes the head of this queue, or returns null if this queue is empty.

### Stack (LIFO) "Last-In, First-Out"
* `empty()` - Returns false if non-empty
* `peek()` - Looks at the object at the top of this stack without removing it from the stack.
* `pop()` - Removes the object at the top of this stack and returns that object as the value of this function.
* `push(E item)` - Pushes an item onto the top of this stack.

### List

### PriorityQueue
* `add(E e)` - Inserts the specified element into this priority queue.
* `offer(E e)` - Inserts the specified element into this priority queue.
* `peek()` - Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
* `poll()` - Retrieves and removes the head of this queue, or returns null if this queue is empty.




