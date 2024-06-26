# Hey, Algorithm! 
Welcome to Iris Algo Notebook

# Algo 
### Bianry Search
1) Classical Binary Search
* Time: **O(logn)** by sufficiently reducing the searching space by half `m = l + (l - r) / 2` 
* `l = r`: stop at the *same* index 
* `l < r - 1`: stop at the *adjacent* index

### Recursion

### DFS
#### DSF based upon Recursive Thinking (Base Case + Sub-Problems)  
> 1. How many levels in the recursion tree? What does it store on each level?
> (每层代表什么意义？一般来说解题之前就需要理解 DFS 要 recursion 多少层) 
> 2. How many different states should we put on each level
> (每层有多少个状态 / case 需要 try) ⇒ 每层有多少个 node，每个node由多个分叉或state
 
#### DSF (with Backtracking)
> Backtracking 的核心：从一个初始状态出发，暴力搜索所有可能的解决方案，当遇到正确的解则将其记录，直到找到解或者尝试了所有可能的选择都无法找到解为止。

#### DSF (with Memoization)
> Memoization 的核心是去重和剪枝：通过记录之前的结果，去skip后面重复的结果集

1) DSF I
* Q1 All Subsets I & II
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

### Graph
1) Eulerian Path (Directed Connected Graph)
* [Reconstruct Itinerary](https://github.com/blue-in-sea/Algo-Notebook/edit/master/Graph/ReconstructItinerary.java)
2) Topological Sort (BFS + IndegreeMap)
* [Course Schedule I (can take all course?)](https://github.com/blue-in-sea/Algo-Notebook/blob/master/GraphBFS/CourseSchedule.java)
* [Course Schedule II (course list)](https://github.com/blue-in-sea/Algo-Notebook/blob/master/GraphBFS/CourseSchedule.java)


### DP
1) Greedy
* [Maximum Subarray Sum](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/MaximumSubarray.java)
* [Maximum Integer With At Most 1 Swap](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/MaximumSubarray.java)
2) 
3) ddd

### String
1) String Reversal (相向双指针) 首位相向而行
* [Reverse a String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ReverseString.java)
* [Reverse Words in a Sentence I](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ReverseWordsInASentenceI.java)
* [Right Shift by N Characters](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/RightShiftByNCharacters.java)
* Char Replacement

2) String Shuffling
* [String Shuffling](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/StringShuffling.java)
* Reverse String Shuffling

3) String Permutation
* All Permutation I: No duplication in the input string 
* All Permutation II: Might be duplication in the input string 

4) String En/Decoding
* [Compress String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/CompressStringII.java)
* [Decompress String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/DecompressStringII.java)

5) Sliding Window in A String
* Longest substring that contains only character
* [Longest Substring Without Repeating Character](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/LongestSubstringWithoutRepeating.java)
* Find all anagrams of a substring S2 in a long string S1


### Sliding Window
[同向双指针] Sliding window: fast-slow pointers  
 1. [Minimum Size Subarray Sum](https://github.com/blue-in-sea/Algo-Notebook/tree/master/SlidingWindow)
 2. [Longest Subarray Contains Only 1s](https://github.com/blue-in-sea/Algo-Notebook/blob/master/SlidingWindow/MaxConsecutiveOnes.java)
 3. [Longest Subarray Contains Only 1s With At Most 1 Zero Flip](https://github.com/blue-in-sea/Algo-Notebook/blob/master/SlidingWindow/MaxConsecutiveOnesWithOneZeroFlip.java)
 4. [Longest Subarray Contains Only 1s With At Most K Zeros Flip](https://github.com/blue-in-sea/Algo-Notebook/blob/master/SlidingWindow/MaxConsecutiveOnesWithKZeroFlip.java)
 5. Max Bank Transactions


[逆向双指针] 中心开花, 两边往中间走
 
  2. two sum
  3. two sum smaller
  4. 2 sum closest 
  5. find the closes sum to target from two sorted arrays
  6. rainbow sort
  7. longest substring without repeating characters 
  9. minimum substring ...

### Heap
  1. [Sliding Window Median](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Heap/SlidingWindowMedian.java)
  2. TopK
  
### Trie 
1) Trie for prefix lookup vs. HashMap for a word search
* [Prefix Trie](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Trie/PrefixTrie.java)
* [Prefix Trie II](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Trie/PrefixTrieII.java)


### Merge Interval
1) Insert a interval: no sorting 
* [InsertInterval](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/InsertInterval.java)
2) Merge a list of intervals: sorting & merge
* [Merge Intervals](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MergeIntervals.java)
* [Meeting Rooms](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MeetingRooms.java)
3) Find # of overlap intervals: flatten the 2D intervals into TreeMap (sorted data-str) & scan the list of time again 
* [Meeting Rooms II](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MeetingRoomsII.java)
* [Numebr of Airplanes On the Sky](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/NumberOfAirplanesII.java)





