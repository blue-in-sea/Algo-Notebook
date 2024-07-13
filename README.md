背包# Hey, Algorithm! 
Welcome to Iris Algo Notebook

# Algo 
### Bianry Search
> O(logn) - Sufficiently reduce search space by half

1) [Classical Binary Search](https://github.com/blue-in-sea/Algo-Notebook/blob/master/BinarySearch/ClassicalBinarySeach.java)
* Time: **O(logn)** by sufficiently reducing the searching space by half `m = l + (l - r) / 2`
* next search space either [l, m] or [m r]
* `l < r`: stop at the *same* index 
* `l < r - 1`: stop at the *adjacent* index or `l + 1 < r`
  

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

* [Bubble Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/BubbleSort.java) Bubbles rise up to the surface, each element of the array move to the end in each iteration
* Insertion Sort
* [Selection Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/SelectionSort.java) Selects the min element from an unsorted list in each iteration; swap min to the beginning. 
* [Merge Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/MergeSort.java) Divide & Conquer + Combine (Helper Arr 谁小移谁)
* [Heap Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/HeapSort.java)
* Quick Sort
* Counting Sort
* Radix Sort

#### Sort Color
> 挡板规则 [0, i): red, [i, j): white, [j, k]: unchecked element, [(k, len - 1]: blue
* [Rainbow Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/RainbowSort.java) to sort 3 colors 
* [Rainbow SortII](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/RainbowSortII.java) to sort 4 colors (divide-n-conquer) similar to mergeSort
* [Rainbow SortIII](https://github.com/blue-in-sea/Algo-Notebook/tree/master/Sorting) to sort k colors (divide-n-conquer) similar to RainbowSortII
  
#### Others
* [Wiggle Sort](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Sorting/WiggleSort.java)
  


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
* [Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
```
return Math.max(leftDepth, rightDepth) + 1
```

3. Reconstruct Binary Tree
4. Max Path Sum
5. LCA - [课件](https://docs.google.com/document/d/1NjbwbS9Ckrri21dzoZYgec5YaUcTLDuuJCL-xSB2mcY/edit#heading=h.qy2juo1v0edg)

* LCA
```
Base Case: 1. root is null; 2. root is a or b
return 1. root or 2. non-null side
```

#### (Advance)
6. Weighted Binary Tree
* Minimum Edge Weight Equilibrium Queries in a Tree

### DFS
#### DSF based upon Recursive Thinking (Base Case + Sub-Problems)  
> 1. How many levels in the recursion tree? What does it store on each level?
> * (每层代表什么意义？一般来说解题之前就需要理解 DFS 要 recursion 多少层) 
> 2. How many different states should we put on each level
> * (每层有多少个状态 / case 需要 try) ⇒ 每层有多少个 node，每个node由多个分叉或state

#### DSF Complexity 
> * Time: O(level ^ state ^  complexity used in based case)
> * Space: O(level * memeory used in each stack call)
 
#### DSF (with Backtracking)
> Backtracking 的核心：从一个初始状态出发，暴力搜索所有可能的解决方案，当遇到正确的解则将其记录，直到找到解或者尝试了所有可能的选择都无法找到解为止。

#### DSF (with Memoization)
> Memoization 的核心是去重和剪枝：通过记录之前的结果，去skip后面重复的结果集

#### 1) DSF I - [课件](https://docs.google.com/document/d/1gU26-F17g3CT7iA1sUbKx-fJDLcATDqBhCgc_iVK_i4/edit)
```
排列 Permutations(C, K) = N!/(N-K)!                                        where N 的 K 排列, if K = N, O(N!)
组合 Combinations: C(N, K) = (N−k)!k! / N!                                 where N 的 K 组合, N choose K 
子集 Subsets: 2^N since each element could be select or unselect
```
1. [Combinations](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/Combination.java)

2. [All Subsets](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/AllSubsets.java)
   * [All Subsets II (Dup)](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/AllSubsetsII.java)
   * [All Subset of Size K](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/AllSubsetsOfSizeK.java)
   * [Maximum Score Words Formed by Letters](https://leetcode.com/problems/maximum-score-words-formed-by-letters/description/)

3. [All Valid Combinations Of Parentheses](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/AllValidCombinationsOfParentheses.java)
  
4. [All Permutations](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/AllPermutation.java)
  * [All Permutations II (Dup)](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/AllPermutationsII.java)
5. All Permutations of Subsets
6. Factor Combinations

#### 2) DSF Backtracking - [课件](https://docs.google.com/document/d/1wHGdet_cS2FO9AQXxd_1xid7u1AJ42mcq3vZvRn2ATw/edit#heading=h.t2lrjyikhz5t)
7. Desirable Number
8. [Restore IP Address](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/RestoreIPAddresses.java)
9. [Find All Confusing Numbers](https://github.com/blue-in-sea/Algo-Notebook/edit/master/Recursion/ConfusingNumberAll.java)
  * [Find The Confusing Numbers (basic)](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/ConfusingNumber.java)
  * [Find The Cnt of All Confusing Numbers (same)](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Recursion/ConfusingNumberII.java)

#### 3) DFS Partition 
10. [Minimum Partition](https://blog.csdn.net/weixin_43981315/article/details/105569525) - DP [01背包]
11. [Partition with Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/description/) - DP [01背包]
12. [Partition with Equal Subset Sum to K](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)
13. Split Array with Subset Sum

#### 4) Boyer-Moore Majority Vote algorithm
14. Majority Number I
15. Majority Number II

### Graph
#### 1) Eulerian Path (Directed Connected Graph)
* [Reconstruct Itinerary](https://github.com/blue-in-sea/Algo-Notebook/edit/master/Graph/ReconstructItinerary.java)
#### 2) Topological Sort (BFS + IndegreeMap)
* [Course Schedule I (can take all course?)](https://github.com/blue-in-sea/Algo-Notebook/blob/master/GraphBFS/CourseSchedule.java)
* [Course Schedule II (course list)](https://github.com/blue-in-sea/Algo-Notebook/blob/master/GraphBFS/CourseSchedule.java)
#### 3) Connect Graph 
* [Key And Rooms](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Graph/KeysAndRooms.java)
* [Number of Connected Components in an Undirected Graph](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Graph/NumberOfConnectedComponentsUndirectedGraph.java)
* [Number of Islands](https://github.com/blue-in-sea/Algo-Notebook/blob/master/GraphBFS/NumberOfIslandsBFS.java)


### DP
> DP 的优化在于 Memo 去避免重复计算；DP 的证明需要用到 induction（数学归纳总法）
#### 1) Greedy
* [Maximum Subarray Sum](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/MaximumSubarray.java)
* [Maximum Integer With At Most 1 Swap](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/MaximumSubarray.java)
  
#### 2) 一维 DP
* [Longest Palindromic Subsequence](https://laioffer.com/zh/videos/2018-03-14-516-longest-palindromic-subsequence/)
* Max Subarray Sum

#### Prefix Sum 
* [Subarray Sum Equals K](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/SubarraySumEqualsK.java)
  
#### 3) 背包问题
##### 0/1 背包
* [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/description/)
* [Minimum Partition](https://blog.csdn.net/weixin_43981315/article/details/105569525)
  
#### 3) 背包问题
##### 0/1 背包
* [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/description/)
* [Minimum Partition](https://blog.csdn.net/weixin_43981315/article/details/105569525)

##### 滚动数组
* [Coin Change](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/CoinChange.java)
* Maximum Profit From Trading Stocks 

5) Best Time to Buy and Sell Stock
* [Best Time to Buy and Sell Stock: For At Most 1 Trade](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStock.java)
* [Best Time to Buy and Sell Stock II: Hold at Most 1 Share At The Time](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockII.java)
* [Best Time to Buy and Sell Stock III: Complete At Most 2 Transactions](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockIII.java)
* [Best Time to Buy and Sell Stock IV: Complete At Most k Transactions](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockIV.java)

<img width="427" alt="state" src="https://github.com/blue-in-sea/Algo-Notebook/assets/44347786/7ce8bd10-b1ac-4059-8775-7384df37d562">

* [Best Time to Buy and Sell Stock with Cool Down](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockWithCoolDown.java)
* [Best Time to Buy and Sell Stock with Transcation Fee](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/BestTimeBuyAndSellStockWithFee.java)
* [Maximum Profit From Trading Stocks](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/MaximumProfitFromTradingStocks.java)


### String
#### [逆反向双指针] 首位相向而行
 1. [Valid Palindrome](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ValidPalindrome.java)
    * [Valid Palindrome II](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ValidPalindromeII.java)
 2. [Reverse String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ReverseString.java)
    * [Reverse Words in a Sentence I](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ReverseWordsInASentenceI.java)
    * [Right Shift by N Characters](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/RightShiftByNCharacters.java)
 3. [String Replacement](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/StringReplace.java)
    * [String Replace Basic](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/StringReplaceBasic.java)
    * [String Replace Shorter](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/StringReplaceShorter.java)

#### [同向双指针] Fast-slow pointer  
 4. [Compressing String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/CompressString.java) & [Compress String II](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/CompressStringII.java)

 5. [Decompressing String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/DecodeString.java) & [Decompress String III](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/DecompressStringII.java)

 6. [Move Zeros](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/MoveZeroes.java)
 7. [Array Deuplication - Keep Only 1](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ArrayDeduplicationI.java)
    * [Array Deuplication II - Keep Only 2](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ArrayDeduplicationII.java)
    * [Array Deuplication III - Keep None](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ArrayDeduplicationIII.java)
    * [Array Deuplication IV - Zuma](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/ArrayDeduplicationIV.java)
 
#### Divide-n-conquer
 8. [String Shuffling](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/StringShuffling.java)
 9. Reverse String Shuffling

    
### Sliding Window
#### [同向双指针] Sliding window: left-right pointers  
 1. [Is Subsequence](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/IsSubsequence.java)
 2. [Minimum Size Subarray Sum](https://github.com/blue-in-sea/Algo-Notebook/tree/master/SlidingWindow)
 3. [Minimum Window Substring](https://github.com/blue-in-sea/Algo-Notebook/blob/master/SlidingWindow/MinimumWindowSubstring.java)
 4. [Longest Substring Without Repeating Character](https://github.com/blue-in-sea/Algo-Notebook/blob/master/SlidingWindow/LongestSubstringWithoutRepeating.java)
 5. [Longest Subarray Contains Only 1s](https://github.com/blue-in-sea/Algo-Notebook/blob/master/SlidingWindow/MaxConsecutiveOnes.java)
 6. [Longest Subarray Contains Only 1s With At Most 1 Zero Flip](https://github.com/blue-in-sea/Algo-Notebook/blob/master/SlidingWindow/MaxConsecutiveOnesWithOneZeroFlip.java)
 7. [Longest Subarray Contains Only 1s With At Most K Zeros Flip](https://github.com/blue-in-sea/Algo-Notebook/blob/master/SlidingWindow/MaxConsecutiveOnesWithKZeroFlip.java)
 8. [Find All Anagrams in a String](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/AllAnagrams.java)
    * [Group Anagrams](https://github.com/blue-in-sea/Algo-Notebook/blob/master/String/GroupAnagrams.java)


### Two Sum 
  1. [All Topics](https://leetcode.com/discuss/interview-question/3685049/25-variations-of-two-sum-question)
  2. [2Sum](https://github.com/blue-in-sea/Algo-Notebook/blob/master/TwoSum/2Sum.java)
  3. [2Sum One Pair of Indices](https://github.com/blue-in-sea/Algo-Notebook/blob/master/TwoSum/2SumWithIndices.java)
  4. [2Sum All Pairs of Indices](https://github.com/blue-in-sea/Algo-Notebook/blob/master/TwoSum/2SumPairsII.java)
  5. 2Sum BST
  7. [3Sum](https://github.com/blue-in-sea/Algo-Notebook/blob/master/TwoSum/3Sum.java)
  8. [3Sum Closest](https://github.com/blue-in-sea/Algo-Notebook/blob/master/TwoSum/3SumClosest.java)
  9. 3Sum Smaller
  10. Find the Closest Sum to Target From Two Sorted Arrays

### Heap
```
1. A complete binary tree
2. All nodes in the tree follow the property that they are greater than their children

        (maxHeap)                       (minHeap)
          12                               1
    10         9                      5        9
  5   6      1   x                 10   6    12   x
```
#### MinHeap + MaxHeap
  1. [Sliding Window Median](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Heap/SlidingWindowMedian.java)

#### Kth-Problem
  2. [Merge K Sorted Lists](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Heap/MergeKSortedLists.java)
  3. [Merge K Sorted Arrays](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Heap/MergeKSortedLists.java)
  3. [TopK Frquent Nums](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Heap/TopKFrequentNum.java)
  4. [TopK Frquent Words](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Heap/TopKFrequentWords.java) plus [tutorial](https://laioffer.com/zh/videos/2018-03-13-692-top-k-frequent-words/)
  5. [Kth Closest Point To Origin](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Heap/KthClosestPointToOrigin.java)
  6. [Kth Largest Element In Array](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Heap/KthLargestElementInArray.java)
  7. 

### Comparator 
* [Largest Number](https://leetcode.com/problems/largest-number/)

### String Conversion
* [Max Swap](https://github.com/blue-in-sea/Algo-Notebook/blob/master/DP/MaximumSwap.java)

### Merge Interval
1) Insert a interval: no sorting 
* [InsertInterval](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/InsertInterval.java)
2) Merge a list of intervals: sorting & merge
* [Merge Intervals](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MergeIntervals.java)
* [Meeting Rooms](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MeetingRooms.java)
3) Find # of overlap intervals: flatten the 2D intervals into TreeMap (sorted data-str) & scan the list of time again 
* [Meeting Rooms II](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/MeetingRoomsII.java)
* [Numebr of Airplanes On the Sky](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Merge-Interval/NumberOfAirplanesII.java)


### MonoStack
```
维护一个单调序列
```
* [DailyTemperatures](https://github.com/blue-in-sea/Algo-Notebook/blob/master/MonoStack/DailyTemperatures.java) `单调非递减序列 peek[min->max] 看右边差几天得到一个更暖的天气`
* [Largest Rectangle In Histogram](https://github.com/blue-in-sea/Algo-Notebook/blob/master/MonoStack/LargestRectangleInHistogram.java) `单调非递减增加列 peek[max->min] 看右边差几步遇见一个更短的木板`
* [Max Binary Tree](https://github.com/blue-in-sea/Algo-Notebook/blob/master/MonoStack/MaxTree.java) `单调非递减序列 peek[min->max] 看右边差几步得到一个更大的元素去当root`
* [Valid Parentheses](https://github.com/blue-in-sea/Algo-Notebook/blob/master/MonoStack/ValidParentheses.java) `stack的单调性在于只存 ( [ { 左括号`

### Trie 
1) Trie for prefix lookup vs. HashMap for a word search
* [Prefix Trie](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Trie/PrefixTrie.java)
* [Prefix Trie II](https://github.com/blue-in-sea/Algo-Notebook/blob/master/Trie/PrefixTrieII.java)


## Advance Data Structure 
### Quad Tree 
* [Build a quad tree](https://github.com/blue-in-sea/Algo-Notebook/blob/master/OOD/QuadTree.java)
  
### LRU





