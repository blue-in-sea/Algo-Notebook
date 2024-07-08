# Hey, Algorithm! 
Welcome to Iris Algo Notebook

# Algo 
### Bianry Search
1) [Classical Binary Search](https://github.com/blue-in-sea/Algo-Notebook/blob/master/BinarySearch/ClassicalBinarySeach.java)
* Time: **O(logn)** by sufficiently reducing the searching space by half `m = l + (l - r) / 2`
* next search space either [l, m] or [m r]
* `l = r`: stop at the *same* index 
* `l < r - 1`: stop at the *adjacent* index

2) Search in a **Sorted** sequence
* [Find First Occurrence](https://github.com/blue-in-sea/Algo-Notebook/blob/master/BinarySearch/FirstOccurrence.java)
  
  * `if (a[mid] >= target) r = mid;` for 1st occurence next search range `[l, m]` 前半部分
 
* [Find Last Occurence](https://github.com/blue-in-sea/Algo-Notebook/blob/master/BinarySearch/LastOccurrence.java)
  
  * `if (a[mid] <= target) l = mid;` for last occurence next search range `[m, r]` 后半部分
  
* [Find Largest Smaller Equal Elements](https://github.com/blue-in-sea/Algo-Notebook/blob/master/BinarySearch/FindPeakElement.java)
  
  * `if (a[mid] <= target) l = mid;` 相似于上题，找在 target 的左区间尽可能的大，或者等于 target 的元素
  
* [Find K Closest Element](https://github.com/blue-in-sea/Algo-Notebook/blob/master/BinarySearch/KClosestElements.java)
  
  * `if (x - arr[mid] > arr[mid + k] - x) l = mid + 1;` 找 k 个元素的起点，比较 `abs(x, a[m])` vs `abs(x, a[m+k])` 两个端点的对于 `x` 绝 对距离
  
* [Peak Element](https://github.com/blue-in-sea/Algo-Notebook/blob/master/BinarySearch/FindPeakElement.java)
  
  * `if (a[mid] < a[mid + 1]) l = mid + 1;` 如果中间的数比后一位数小的话，peek点肯定在mid右边包括mid `[m, r]`

### Recursion


### [Sorting](https://leetcode.com/problems/sort-an-array/editorial)

```
    Sorting                        Best Case                         Ave Case                            Worset Case - Big O       
 
1. Bubble Sort                     O(n)                                O(n^2)                               O(n^2)
2. Insertion Sort                  O(n)                                O(n^2)                               O(n^2)
3. Selection Sort                  O(n^2)                              O(n^2)                               O(n^2)
4. Merge Sort *                    O(nlogn)                            O(nlogn)                         *   O(nlogn)
5. Heap Sort  *                    O(nlogn)                            O(nlogn)                         *   O(nlogn) 
6. Quick Sort x                    O(nlogn)                            O(nlogn)                         x   O(n^2)
7. Counting Sort                   O(n+k)                              O(n+k)                               O(n+k)
8. Radix Sort                      O(d(n+k))                           O(d(n+k))                            O(d(n+k))
```

* [Bubble Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/BubbleSort.java)
* Insertion Sort
* [Selection Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/SelectionSort.java)
* [Merge Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/MergeSort.java)
* [Heap Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/HeapSort.java)
* Quick Sort
* Counting Sort
* Radix Sort 


### Binary Tree

1. BFS (Level Order)
2. DFS (PreOrder / InOrder / PostOrder)

![binary-tree](https://github.com/blue-in-sea/Algo-Notebook/assets/44347786/e80ccfd2-3715-4e86-86de-d6777f7c073a)

```
Pororder 
[3,9,1,5,3,4,8,20,15,9,7]

Inorder
[5,1,9,4,3,8,3,15,9,20,7]

Postorder 
[5,1,4,8,3,9,9,15,7,20,3]
```
3. Reconstruct Binary Tree
4. Max Path Sum
5. LCA

#### (Advance)
6. Weighted Binary Tree
* Minimum Edge Weight Equilibrium Queries in a Tree

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
3) Best Time to Buy and Sell Stock
* [Best Time to Buy and Sell Stock: For At Most 1 Trade](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStock.java)
* [Best Time to Buy and Sell Stock II: Hold at Most 1 Share At The Time](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockII.java)
* [Best Time to Buy and Sell Stock III: Complete At Most 2 Transactions](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockIII.java)
* [Best Time to Buy and Sell Stock IV: Complete At Most k Transactions](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockIV.java)

<img width="427" alt="state" src="https://github.com/blue-in-sea/Algo-Notebook/assets/44347786/7ce8bd10-b1ac-4059-8775-7384df37d562">

* [Best Time to Buy and Sell Stock with Cool Down](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockWithCoolDown.java)
* [Best Time to Buy and Sell Stock with Transcation Fee](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockWithFee.java)
* [Maximum Profit From Trading Stocks](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/MaximumProfitFromTradingStocks.java)


### String
1) String Reversal (相向双指针) 首位相向而行
* [Reverse a String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ReverseString.java)
* [Reverse Words in a Sentence I](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ReverseWordsInASentenceI.java)
* [Right Shift by N Characters](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/RightShiftByNCharacters.java)
* [String Replacement](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/StringReplace.java)
* [Valid Palindrome](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ValidPalindrome.java) 
* [Valid PalindromeII: After Deleting At Most 1 Char](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ValidPalindromeII.java)

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





