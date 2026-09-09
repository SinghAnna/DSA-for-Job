# 👆 Two Pointers - Complete Guide

## 📚 Theory

### What is Two Pointers?

**Two Pointers** is a technique where we use two pointers to iterate through a data structure (usually array or linked list) to solve a problem in a single pass.

### When to Use Two Pointers?

Use two pointers when:
1. Array is **sorted**
2. Need to find **pairs/triplets** with certain sum
3. Need to **reverse** or **rotate** array
4. Need to **remove duplicates** or **move elements**
5. **Palindrome** related problems
6. **Linked list** cycle detection or middle element

### Types of Two Pointers

## 1. Opposite Direction (Converging)

* One pointer at start, one at end

* Move towards each other

* Example: Two Sum in sorted array

## 2. Same Direction (Fast & Slow)

* Both pointers move same direction

* Different speeds

* Example: Linked list cycle detection

## 3. Independent Pointers

* Both pointers move independently

* Example: Merge two sorted arrays


---

## 💻 Java Code Examples

### Problem 1: Two Sum II (Sorted Array)

**Question:** Given a sorted array of integers and a target, find two numbers that add up to target. Return their 1-indexed indices.

```java
/**
 * Problem: Two Sum II - Input Array Is Sorted
 * LeetCode: 165
 * 
 * Visual:
 * arr =, target = 9[2][7][11][15]
 *       0  1   2   3   (indices)
 * 
 * Two Pointers Approach:
 * 
 * left=0, right=3
 * arr+arr = 2+15 = 17 > 9[1]
 * Sum too large, move right pointer left
 * 
 * left=0, right=2
 * arr+arr = 2+11 = 13 > 9
 * Sum too large, move right pointer left
 * 
 * left=0, right=1
 * arr+arr = 2+7 = 9 == 9 ✓
 * Found! Return  (1-indexed)[1][2]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class TwoSumSorted {
    
    // Approach 1: Two Pointers (Opposite Direction)
    public static int[] twoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            
            if (sum == target) {
                // Return 1-indexed
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                // Sum too small, move left pointer right
                left++;
            } else {
                // Sum too large, move right pointer left
                right--;
            }
        }
        
        return new int[]{-1, -1}; // Not found
    }
    
    // Approach 2: HashMap (works for unsorted too)
    public static int[] twoSumHashMap(int[] arr, int target) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement) + 1, i + 1};
            }
            
            map.put(arr[i], i);
        }
        
        return new int[]{-1, -1};
    }
    
    // Variation: Count pairs with given sum
    public static int countPairsWithSum(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int count = 0;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            
            if (sum == target) {
                count++;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return count;
    }
    
    // Variation: Count pairs with sum less than target
    public static int countPairsLessThan(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int count = 0;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            
            if (sum < target) {
                // All pairs (left, left+1), (left, left+2), ..., (left, right) are valid
                count += (right - left);
                left++;
            } else {
                right--;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        int[] result = twoSum(arr, target);
        System.out.println("\nTwo Sum (Two Pointers): [" + result + ", " + result + "]");
        
        int[] result2 = twoSumHashMap(arr, target);
        System.out.println("Two Sum (HashMap): [" + result2 + ", " + result2 + "]");
        
        // Count pairs
        int[] arr2 = {1, 2, 3, 4, 5};
        target = 6;
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("Pairs with sum " + target + ": " + countPairsWithSum(arr2, target));
        
        // Count pairs less than
        target = 7;
        System.out.println("Pairs with sum < " + target + ": " + countPairsLessThan(arr2, target));
    }
}
```

---

### Problem 2: 3Sum

**Question:** Given an integer array nums, return all triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, j != k, and nums[i] + nums[j] + nums[k] == 0.

```java
/**
 * Problem: 3Sum
 * LeetCode: 15
 * 
 * Visual:
 * nums = [-1, 0, 1, 2, -1, -4]
 * 
 * After sorting: [-4, -1, -1, 0, 1, 2]
 * 
 * Two Pointers Approach:
 * 
 * Fix i=0 (nums=-4):
 *   left=1, right=5
 *   sum = -4 + (-1) + 2 = -3 < 0, move left
 *   ... no triplet with -4
 * 
 * Fix i=1 (nums=-1):
 *   left=2, right=5
 *   sum = -1 + (-1) + 2 = 0 ✓
 *   Found triplet: [-1, -1, 2]
 *   Move both pointers, skip duplicates
 * 
 * Fix i=2 (nums=-1):
 *   Skip (duplicate of i=1)
 * 
 * Fix i=3 (nums=0):[1]
 *   left=4, right=5
 *   sum = 0 + 1 + 2 = 3 > 0, move right
 *   ... no more triplets
 * 
 * Result: [[-1, -1, 2], [-1, 0, 1]]
 * 
 * Time Complexity: O(n²)
 * Space Complexity: O(1) excluding result
 */
public class ThreeSum {
    
    public static java.util.List<java.util.List<Integer>> threeSum(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        int n = nums.length;
        
        // Sort array
        java.util.Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Two pointers for remaining array
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    // Found triplet
                    java.util.List<Integer> triplet = new java.util.ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[left]);
                    triplet.add(nums[right]);
                    result.add(triplet);
                    
                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    // Sum too small, move left pointer right
                    left++;
                } else {
                    // Sum too large, move right pointer left
                    right--;
                }
            }
        }
        
        return result;
    }
    
    // Variation: 3Sum Closest
    // LeetCode: 16
    public static int threeSumClosest(int[] nums, int target) {
        java.util.Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums + nums + nums;
        
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                // Update closest sum
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
                
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // Exact match
                }
            }
        }
        
        return closestSum;
    }
    
    // Variation: 3Sum Smaller
    // Count triplets with sum less than target
    public static int threeSumSmaller(int[] nums, int target) {
        java.util.Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum < target) {
                    // All triplets (i, left, left+1), (i, left, left+2), ..., (i, left, right) are valid
                    count += (right - left);
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        java.util.List<java.util.List<Integer>> result = threeSum(nums);
        System.out.println("\nTriplets with sum 0: " + result);
        
        // 3Sum Closest
        int[] nums2 = {-1, 2, 1, -4};
        int target = 1;
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        System.out.println("Target: " + target);
        System.out.println("Closest sum: " + threeSumClosest(nums2, target));
        
        // 3Sum Smaller
        int[] nums3 = {-2, 0, 1, 3};
        target = 2;
        System.out.println("\nArray: " + java.util.Arrays.toString(nums3));
        System.out.println("Target: " + target);
        System.out.println("Count of triplets with sum < " + target + ": " + 
                          threeSumSmaller(nums3, target));
    }
}
```

---

### Problem 3: Container With Most Water

**Question:** Given n non-negative integers representing vertical lines, find two lines that together with x-axis form a container that holds the most water.

```java
/**
 * Problem: Container With Most Water
 * LeetCode: 11
 * 
 * Visual:
 * height =[1][2][3][4][5][6][7][8]
 * 
 * Two Pointers Approach:
 * 
 * left=0, right=8
 * width = 8 - 0 = 8
 * height = min(1, 7) = 1
 * area = 8 × 1 = 8
 * maxArea = 8
 * Move pointer with smaller height (left)
 * 
 * left=1, right=8
 * width = 8 - 1 = 7
 * height = min(8, 7) = 7
 * area = 7 × 7 = 49
 * maxArea = 49 ✓
 * Move pointer with smaller height (right)
 * 
 * left=1, right=7
 * width = 7 - 1 = 6
 * height = min(8, 3) = 3
 * area = 6 × 3 = 18
 * maxArea = 49
 * Move pointer with smaller height (right)
 * 
 * Continue...
 * 
 * Result: 49
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ContainerWithMostWater {
    
    // Approach 1: Two Pointers
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            // Calculate area
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int area = width * h;
            
            // Update maximum
            maxArea = Math.max(maxArea, area);
            
            // Move pointer with smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    // Approach 2: Brute Force - O(n²)
    public static int maxAreaBruteForce(int[] height) {
        int maxArea = 0;
        
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int width = j - i;
                int h = Math.min(height[i], height[j]);
                int area = width * h;
                maxArea = Math.max(maxArea, area);
            }
        }
        
        return maxArea;
    }
    
    // Variation: Trapping Rain Water
    // LeetCode: 42
    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        
        return water;
    }
    
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        
        System.out.println("Heights: " + java.util.Arrays.toString(height));
        
        System.out.println("\nMaximum area (Two Pointers): " + maxArea(height));
        System.out.println("Maximum area (Brute Force): " + maxAreaBruteForce(height));
        
        // Trapping Rain Water
        int[] height2 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("\nHeights: " + java.util.Arrays.toString(height2));
        System.out.println("Water trapped: " + trap(height2) + " units");
    }
}
```

---

### Problem 4: Valid Palindrome II

**Question:** Given a string s, return true if the s can be palindrome after deleting at most one character from it.

```java
/**
 * Problem: Valid Palindrome II
 * LeetCode: 680
 * 
 * Visual:
 * s = "abca"
 * 
 * Two Pointers Approach:
 * 
 * left=0, right=3
 * s='a', s='a', match ✓[1]
 * Move both pointers
 * 
 * left=1, right=2
 * s='b', s='c', mismatch!
 * Try deleting either 'b' or 'c'
 * 
 * Option 1: Delete 'b' (skip left)
 * Check "ca" from index 2 to 3
 * s='c', s='a', mismatch ✗[1]
 * 
 * Option 2: Delete 'c' (skip right)
 * Check "bc" from index 1 to 2
 * s='b', s='c', mismatch ✗
 * 
 * Wait, let me recheck...
 * After deleting 'c': "aba" which is palindrome ✓
 * 
 * Result: true
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ValidPalindromeII {
    
    // Main function
    public static boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try deleting either left or right character
                return isPalindrome(s, left + 1, right) ||
                       isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true; // Already palindrome
    }
    
    // Helper function to check if substring is palindrome
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // Variation: Valid Palindrome (basic)
    // LeetCode: 125
    public static boolean isPalindromeBasic(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != 
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    // Variation: Longest Palindromic Substring
    // LeetCode: 5
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome
            int len1 = expandAroundCenter(s, i, i);
            // Even length palindrome
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int len = Math.max(len1, len2);
            
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && 
               s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    public static void main(String[] args) {
        String s = "abca";
        
        System.out.println("String: \"" + s + "\"");
        System.out.println("Can be palindrome by deleting one char: " + 
                          validPalindrome(s));
        
        // Basic palindrome
        String s2 = "A man, a plan, a canal: Panama";
        System.out.println("\nString: \"" + s2 + "\"");
        System.out.println("Is palindrome: " + isPalindromeBasic(s2));
        
        // Longest palindromic substring
        String s3 = "babad";
        System.out.println("\nString: \"" + s3 + "\"");
        System.out.println("Longest palindromic substring: \"" + 
                          longestPalindrome(s3) + "\"");
    }
}
```

---

### Problem 5: Remove Duplicates from Sorted Array

**Question:** Given a sorted array nums, remove the duplicates in-place such that each element appears only once and return the new length.

```java
/**
 * Problem: Remove Duplicates from Sorted Array
 * LeetCode: 26
 * 
 * Visual:
 * nums =[0][1][2][3][4]
 * 
 * Two Pointers Approach:
 * 
 * i=0 (slow), j=1 (fast)
 * nums=0, nums=0, same, move j
 * 
 * i=0, j=2
 * nums=0, nums=1, different
 * i=1, nums=nums=1, move j
 * 
 * i=1, j=3
 * nums=1, nums=1, same, move j[1]
 * 
 * i=1, j=4
 * nums=1, nums=1, same, move j[2]
 * 
 * i=1, j=5
 * nums=1, nums=2, different[3]
 * i=2, nums=nums=2, move j[3]
 * 
 * Continue...
 * 
 * Result: [0, 1, 2, 3, 4, ...]
 * New length: 5
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class RemoveDuplicates {
    
    // Approach 1: Two Pointers
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int i = 0; // Slow pointer
        
        for (int j = 1; j < nums.length; j++) { // Fast pointer
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        
        return i + 1;
    }
    
    // Variation: Remove Duplicates II (allow at most 2)
    // LeetCode: 80
    public static int removeDuplicatesII(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        
        int i = 2; // Start from index 2
        
        for (int j = 2; j < nums.length; j++) {
            // If current element is different from element at i-2
            if (nums[j] != nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }
        
        return i;
    }
    
    // Variation: Move Zeroes
    // LeetCode: 283
    public static void moveZeroes(int[] nums) {
        int i = 0; // Position for next non-zero
        
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                // Swap
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
    }
    
    // Variation: Remove Element
    // LeetCode: 27
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        
        return i;
    }
    
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        int newLength = removeDuplicates(nums);
        System.out.println("New length: " + newLength);
        System.out.println("After removal: " + 
                          java.util.Arrays.toString(Arrays.copyOf(nums, newLength)));
        
        // Remove Duplicates II
        int[] nums2 = {1, 1, 1, 2, 2, 3};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        newLength = removeDuplicatesII(nums2);
        System.out.println("New length (allow 2): " + newLength);
        System.out.println("After removal: " + 
                          java.util.Arrays.toString(Arrays.copyOf(nums2, newLength)));
        
        // Move Zeroes
        int[] nums3 = {0, 1, 0, 3, 12};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums3));
        moveZeroes(nums3);
        System.out.println("After moving zeroes: " + java.util.Arrays.toString(nums3));
    }
}
```

---

### Problem 6: Linked List Cycle Detection

**Question:** Given head of a linked list, determine if the linked list has a cycle.

```java
/**
 * Problem: Linked List Cycle (Floyd's Cycle Detection)
 * LeetCode: 141
 * 
 * Visual:
 * Linked List with cycle:
 *     1 -> 2 -> 3 -> 4
 *          ^         |
 *          |_________|
 * 
 * Fast & Slow Pointers:
 * 
 * Step 0: slow=1, fast=1
 * Step 1: slow=2, fast=3
 * Step 2: slow=3, fast=3 (meet!) ✓
 * 
 * Cycle detected!
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LinkedListCycle {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Detect cycle
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move 1 step
            fast = fast.next.next;     // Move 2 steps
            
            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        
        return false; // No cycle
    }
    
    // Find start of cycle
    public static ListNode detectCycleStart(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                break;
            }
        }
        
        // No cycle
        if (fast == null || fast.next == null) {
            return null;
        }
        
        // Find start of cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow; // Start of cycle
    }
    
    // Find middle of linked list
    public static ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow; // Middle node
    }
    
    // Find Nth node from end
    public static ListNode findNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        
        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        
        // Move both until fast reaches end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow; // Nth node from end
    }
    
    public static void main(String[] args) {
        // Create linked list with cycle
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next; // Create cycle
        
        System.out.println("Has cycle: " + hasCycle(head));
        
        // Find cycle start
        ListNode cycleStart = detectCycleStart(head);
        System.out.println("Cycle starts at node with value: " + 
                          (cycleStart != null ? cycleStart.val : "None"));
        
        // Find middle
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        
        ListNode middle = findMiddle(head2);
        System.out.println("\nMiddle node: " + middle.val);
        
        // Find Nth from end
        ListNode nth = findNthFromEnd(head2, 2);
        System.out.println("2nd from end: " + nth.val);
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Two Sum II | Opposite Direction | LeetCode 165 |
| Valid Palindrome | Opposite Direction | LeetCode 125 |
| Remove Duplicates | Same Direction | LeetCode 26 |
| Move Zeroes | Same Direction | LeetCode 283 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| 3Sum | Opposite Direction | LeetCode 15 |
| Container With Most Water | Opposite Direction | LeetCode 11 |
| Valid Palindrome II | Opposite Direction | LeetCode 680 |
| Linked List Cycle | Fast & Slow | LeetCode 141 |
| Middle of Linked List | Fast & Slow | LeetCode 876 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Trapping Rain Water | Two Pointers | LeetCode 42 |
| Minimum Window Substring | Sliding Window + Two Pointers | LeetCode 76 |

---

## ✅ Key Takeaways

1. **Opposite Direction** - Use for sorted arrays, pair problems
2. **Same Direction** - Use for in-place modifications
3. **Fast & Slow** - Use for cycle detection, middle element
4. **Sorted Array** - Two pointers works best on sorted data
5. **In-Place** - Two pointers often gives O(1) space solution

---

**Previous:** [Sliding Window](../sliding-window/01-theory.md)  
**Next:** [Prefix Sum](../prefix-sum/01-theory.md)