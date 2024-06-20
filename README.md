# Hey, Algorithm! 
Welcome to Iris Algo Notebook

# Algo 
### 1. Bianry Search
1) Classical Binary Search
* Time: **O(logn)** by sufficiently reducing the searching space by half `m = l + (l - r) / 2` 
* `l = r`: stop at the *same* index 
* `l < r - 1`: stop at the *adjacent* index

### 3. Recursion

### 3. DFS
DSF (with Backtracking) 

1) DSF I
* 1) All Subsets I & II
* Q2 All Valid Parentheses
* Q3 All Permutations I & II with Dup
* Q4 All Permutations of Subsets
* Q5 99 CENTS
* Q6 Factor Combinations

2) DSF Epic
* Q7 Desirable Number
* Q8 Restore IP Address
* Q9 Find All Confusing Numbers

3) DFS Partition 
* Q10 Minimum Partition
* Q11 Partition with Equal Subset Sum
* Q12 Partition with Equal Subset Sum to K
* Q13 Split Array with Subset Sum

4) Boyer-Moore Majority Vote algorithm
* Q14 Majority Number I
* Q15 Majority Number II

### 4. String
1) String Reversal (相向双指针) 首位相向而行
* [Reverse a String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ReverseString.java)
* [Reverse Words in a Sentence I](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ReverseWordsInASentenceI.java)
* Right Shift by N Characters
* Char Replacement

2) String Shuffling
* String Shuffling
* Reverse String Shuffling

3) String Permutation
* All Permutation I: No duplication in the input string 
* All Permutation II: Might be duplication in the input string 

4) String En/Decoding
* Compress String 
* Decompress String

5) Sliding Window in A String
* Longest substring that contains only character 
* Find all anagrams of a substring S2 in a long string S1
* Find longest subarray that consists of all ‘1’s


### 5. Two pointer 
[同向双指针] sliding window, fast-slow pointers  
[逆向双指针] 中心开花, 两边往中间走
  1. longest subarray contains only 1s
  2. two sum
  3. two sum smaller
  4. 2 sum closest 
  5. find the closes sum to target from two sorted arrays
  6. rainbow sort
  7. longest substring without repeating characters 
  8. sliding window maximum
  9. sliding window median 
  10. minimum substring ...

  
### 6. Trie 
1) Trie for prefix lookup vs. HashMap for a word search
* [Prefix Trie](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Trie/PrefixTrie.java)
* [Prefix Trie II](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Trie/PrefixTrieII.java)


### 7. Merge Interval
1) Insert a interval: no sorting 
* [InsertInterval](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/InsertInterval.java)
2) Merge a list of intervals: sorting & merge
* [Merge Intervals](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MergeIntervals.java)
* [Meeting Rooms](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MeetingRooms.java)
3) Find # of overlap intervals: flatten the 2D intervals into TreeMap (sorted data-str) & scan the list of time again 
* [Meeting Rooms II](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MeetingRoomsII.java)
* [Numebr of Airplanes On the Sky](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/NumberOfAirplanesII.java)





