### JAVA 8 

1. Common APIs & Utils
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
```
DFS & Iterator
> PreOrder
> InOrder
> PostOrder

BFS
> LevelOrder
```

### Map
* `containsKey()`
* `computeIfAbsent()`
* `getOrDefault()`
* `values`
* `keySet()`
* 


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
  

### Queue

### Stack
### Map
### Heap





3. sss
  
* How to Print an array
* How to Print an 2d-array 
* How to Print an collections 

2. Interview Tips
* Fast!
* Algo + Presudo code!
* Time Complexity!
* Focus on the core Algo + DataStr --> Use utils for the trivial component!

3. Complexity Analysis
* Sorting: O(nlogn)
* Divide-n-Conquer
* Recursion 
* Binary search: O(logn)
* Graph traversal: O(V + E)
* BST insert / update / lookup / deletion
* TreeMap insert / update / lookup / deletion
* PriorityQueue(Heap) / update / lookup / deletion 

4. Ability run test case with implemented codes line-by-line to debug 
