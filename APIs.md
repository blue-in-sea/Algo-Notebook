### Common Utils - Java 8
### Printing
#### 1. Print an array 
```
System.out.println(Arrays.toString(array));
```

#### 2. Print a 2D array
```
System.out.println(Arrays.deepToString(array));
```

#### 3. Print a 1D collection: list / queue / stack
```
System.out.println(list);
```

#### 4. Print a map 
```
map.forEach((key, value) -> System.out.println(key + ": " + value));
```

#### 5. Print a tree
##### DFS
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
##### BFS
```
void LevelOrder() {
  queue
  while (!queue.isEmpty()) {
     check size for curLevel
     for(curLevel) {
        // generate the curr node 
        node = queue.poll();
        print(node.val);

        // expand children
        if (node.left != null) queue.offer(node.left);
        if (node.left != null) queue.offer(node.right);
     }
  }
  println();
  level++;
}
```
### Common APIs - Java 8

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

### LinkedList
To delete a list node
1) Find previous node of the node to be deleted.
2) Change the next of previous node.
3) Free memory for the node to be deleted.
   
[Add, Delete, Print Node a Single LinkedList](https://github.com/blue-in-sea/Algo-Notebook/blob/master/LinkedList/LinkedList.java)


```
                             ArrayList                                           LinkedList
                             (array)                                             (double-linked-list)


Random Access (get)         O(1) - Direct access by index.                       O(n) - Must traverse the list from the beginning or end.
                            Fast *                                               Slow


Search (contains):        O(n) - Must iterate through the list.                   Search (contains): O(n) - Must iterate through the list.
           


Insertion (add):          1. At the end: O(1) - Amortized constant time           1. At the beginning: O(1)
                            though it can be O(n) if resizing is needed.          2. At the end: O(1) if tail reference is maintained; otherwise, O(n)
                                                                                
                          2. At a specific index: O(n) Requires shifting.         3. At a specific index: O(n) - Must traverse to the index.


Deletion (remove)         1. From the end: O(1)                                   1. From the end: O(1) 
                          2. At a specific index: O(n) Requires shifting.         2. From a specific index: O(n) Requires traversal

                          Trick: Swap with the last ele & delete!!

```
* `ArrayList` => random access & frequently add or remove elements at the end
* `LinkedList` =>  frequent insertion and deletion occur at the beginning or middle of the list.

### List

* `sort(Comparator<? super E> c)` - Sorts this list according to the order induced by the specified Comparator.
```
Collections.sort(list);           or           Collections.sort(l, (a, b) -> a.compareTo(b));        
Collections.reverse(list);        or           Collections.sort(l, (a, b) -> b.compareTo(a));
```
  
* `size()` - Returns the number of elements in this list.
* `isEmpty()` - Returns true if this list contains no elements.
* `equals(Object o)` - Compares the specified object with this list for equality.
  
* `get(int index)` - Returns the element at the specified position in this list.
* `indexOf(Object o)` -  Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
* `lastIndexOf(Object o)` - Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
* `set(int index, E element)` - Replaces the element at the specified position in this list with the specified element (optional operation).
  
* `add(E e)` - Appends the specified element to the end of this list (optional operation).
* `add(int index, E element)` - Inserts the specified element at the specified position in this list (optional operation).
* `addAll(Collection<? extends E> c)` - Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).
* `addAll(int index, Collection<? extends E> c)` - Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
* `clear()` - Removes all of the elements from this list (optional operation).

* `remove(int index)` - Removes the element at the specified position in this list (optional operation).
*	`remove(Object o)` - Removes the first occurrence of the specified element from this list, if it is present (optional operation).
* `removeAll(Collection<?> c)` - Removes from this list all of its elements that are contained in the specified collection (optional operation).
* `replaceAll(UnaryOperator<E> operator)` - Replaces each element of this list with the result of applying the operator to that element.
* `retainAll(Collection<?> c)` - Retains only the elements in this list that are contained in the specified collection (optional operation).

* `contains(Object o)` - Returns true if this list contains the specified element.
* `containsAll(Collection<?> c)` - Returns true if this list contains all of the elements of the specified collection.

* `List<E>	subList(int fromIndex, int toIndex)` - Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
```
list [a, b, c, d]  => list.subList(1, 3) [b, c]
      0  1  2  3                  [1, 3] inclusive
```
* `Object[]	toArray()` - Returns an array containing all of the elements in this list in proper sequence (from first to last element).

* `Iterator<E>	iterator()` - Returns an iterator over the elements in this list in proper sequence.
* `ListIterator<E>	listIterator()` - Returns a list iterator over the elements in this list (in proper sequence).
* `ListIterator<E>	listIterator(int index)` - Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.

```
// For-loop 
for (E element : list) {
    // ..
}

// Iterator 
for (Iterator<E> iter = list.iterator(); iter.hasNext(); ) {
    E element = iter.next();
    // 1 - can call methods of element
    // 2 - can use iter.remove() to remove the current element from the list
}

// Functional 
list.stream().map(e -> e + 1); // Can apply a transformation function for e
``` 

* `Spliterator<E>	spliterator()` - Creates a Spliterator over the elements in this list.
```
list.add(new User("Hi", 20));
Spliterator<User> users = list.spliterator();
users.forEachRemaining((n) -> System.out.println("name : " + n.name + " age: " + n.age);); // "name: hi age: 20
```

### Set
* `add(E e)` - Adds the specified element to this set if it is not already present (optional operation).
* `addAll(Collection<? extends E> c)` - Adds all of the elements in the specified collection to this set if they're not already present (optional operation).
```
s1.addAll(s2) // Union of two collections 
```
* `clear()` - Removes all of the elements from this set (optional operation).
* `contains(Object o)` - Returns true if this set contains the specified element.
* `containsAll(Collection<?> c)` - Returns true if this set contains all of the elements of the specified collection.
* `equals(Object o)` - Compares the specified object with this set for equality.
* `isEmpty()` - Returns true if this set contains no elements.
  
* `remove(Object o)` - Removes the specified element from this set if it is present (optional operation).
* `removeAll(Collection<?> c)` - Removes from this set all of its elements that are contained in the specified collection (optional operation).
* `retainAll(Collection<?> c)` - Retains only the elements in this set that are contained in the specified collection (optional operation).
```
s1.retainAll(s2) // Intersection of two collections 
```
* `size()` - Returns the number of elements in this set (its cardinality).

* `int hashCode()` -  Returns the hash code value for this set. ** 
* `Object[] toArray()` - Returns an array containing all of the elements in this set.
* `Iterator<E> iterator()` - Returns an iterator over the elements in this set.
* `Spliterator<E>	spliterator()` - Creates a Spliterator over the elements in this set.


### Queue (FIFO) "First-In, First-Out"
```
         Throws exception	Returns special value
Insert	  add(e)	         offer(e)
Remove	  remove()	       poll()
Examine	  element()	       peek()

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


### PriorityQueue
* `add(E e)` - Inserts the specified element into this priority queue.
* `offer(E e)` - Inserts the specified element into this priority queue.
* `peek()` - Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
* `poll()` - Retrieves and removes the head of this queue, or returns null if this queue is empty.

### Random 

* `doubles()` - Returns an effectively unlimited stream of pseudorandom double values, each between zero (inclusive) and one (exclusive).

* `nextBoolean()` -  Returns the next pseudorandom, uniformly distributed boolean value from this random number generator's sequence.
* `nextBytes(byte[] bytes)` - Generates random bytes and places them into a user-supplied byte array.
* `nextDouble()` - Returns the next pseudorandom, uniformly distributed double value between 0.0 and 1.0 from this random number generator's sequence.
* `nextFloat()` - Returns the next pseudorandom, uniformly distributed float value between 0.0 and 1.0 from this random number generator's sequence.
* `nextLong()` - Returns the next pseudorandom, uniformly distributed long value from this random number generator's sequence.
* `setSeed(long seed)` - Sets the seed of this random number generator using a single long seed.

* `nextInt()` - Returns the next pseudorandom, uniformly distributed int value from this random number generator's sequence.
* `nextInt(int bound)` - Returns a pseudorandom, uniformly distributed int value between *0 (inclusive) and the specified value (exclusive)*, drawn from this random number generator's sequence.

```
/**
  * A seed is a number that initializes the selection of numbers by a random number generator; given the same seed number,
  * a random number generator will generate the same series of random numbers each time a simulation is run.
  */
Random random = new Random();
random.setSeed(12345L);
random.nextInt();      // Random Integer Number - 1553932502
random.nextLong();     // Random Long Number - -8979699159253079774
random.nextBoolean();  // Random Boolean - true
```

Three ways to generate a random number 
```
public class Foo {
   private Random random 

   public Foo() {
       this(System.currentTimeMillis());  
   }

   public Foo(long seed) {
       this.random = new Random(seed);
   }

   public synchronized double getNext() {
        return generator.nextDouble();
   }
}
```
To randomly generate a number from [0, n)
```
Random rand = new Random();
int prob = rand.nextInt(n);    =>  int prob = (int) (Math.random() * n);
```


### Exceptions throw 

* throw new `IllegalArgumentException`("invalid input");
* throw new `ArithmeticException`("illegal input");

In the function handle 
```
public static void main(String args[]) throws `IOException` {

}
```





