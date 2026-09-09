# 🔄 Backtracking - Complete Guide

## 📚 Theory

### What is Backtracking?

**Backtracking** is an algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, and removing those solutions that fail to satisfy the constraints of the problem.

### Backtracking vs Recursion


## Recursion:

* Function calls itself

* Solves smaller subproblems

* May or may not backtrack

## Backtracking:

* Special form of recursion

* Tries all possibilities

* Backtracks when constraint violated

* "Try and error" approach



### When to Use Backtracking?

Use backtracking when:
1. Problem asks for **all possible solutions**
2. Need to find **combinations/permutations**
3. Problem has **constraints** to satisfy
4. Can be modeled as **decision tree**

### Backtracking Template

```java
public List<Result> backtrack(params) {
    List<Result> results = new ArrayList<>();
    helper(results, current_state, other_params);
    return results;
}

private void helper(List<Result> results, 
                    State current, 
                    other_params) {
    // Base case: solution found
    if (is_solution(current)) {
        results.add(new Result(current));
        return;
    }
    
    // Try all possible choices
    for (choice : choices) {
        // Check if valid choice
        if (is_valid(choice, current)) {
            // Make choice
            make_choice(choice, current);
            
            // Recurse
            helper(results, current, other_params);
            
            // Undo choice (BACKTRACK)
            undo_choice(choice, current);
        }
    }
}
```

### Common Backtracking Problems

1. **Permutations** - Arrange items in all possible orders
2. **Combinations** - Select items in all possible ways
3. **Subsets** - Find all possible subsets
4. **N-Queens** - Place queens on chessboard
5. **Sudoku Solver** - Fill sudoku grid
6. **Maze Problems** - Find path in maze
7. **Phone Keypad** - Letter combinations
8. **Word Search** - Find word in grid

---

## 💻 Java Code Examples

### Problem 1: Generate All Subsets (Power Set)

**Question:** Given an integer array nums of unique elements, return all possible subsets (the power set).

```java
/**
 * Problem: Subsets (Power Set)
 * LeetCode: 78
 * 
 * Visual:
 * nums =[1][2][3]
 * 
 * Decision Tree:
 *                    []
 *                   /  \
 *                    [][1]
 *                /  \   / \
 *               [][1][2]
 *            /  \  / \ / \ / \
 *       [][1][2][3]
 * 
 * Subsets: [],,,,,,,[1][2][3]
 * Total: 2ⁿ = 2³ = 8 subsets
 * 
 * Approach:
 * For each element, we have 2 choices:
 * 1. Include it in subset
 * 2. Exclude it from subset
 * 
 * Time Complexity: O(2ⁿ × n)
 * Space Complexity: O(n) recursion stack
 */
public class Subsets {
    
    // Approach 1: Backtracking (Include/Exclude)
    public static java.util.List<java.util.List<Integer>> subsets(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        backtrack(nums, 0, new java.util.ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int[] nums, int index, 
                                  java.util.List<Integer> current,
                                  java.util.List<java.util.List<Integer>> result) {
        // Base case: processed all elements
        if (index == nums.length) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        // Choice 1: Include nums[index]
        current.add(nums[index]);
        backtrack(nums, index + 1, current, result);
        
        // Undo choice (BACKTRACK)
        current.remove(current.size() - 1);
        
        // Choice 2: Exclude nums[index]
        backtrack(nums, index + 1, current, result);
    }
    
    // Approach 2: Iterative using bitmask
    public static java.util.List<java.util.List<Integer>> subsetsBitmask(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        int n = nums.length;
        int totalSubsets = 1 << n; // 2ⁿ
        
        for (int mask = 0; mask < totalSubsets; mask++) {
            java.util.List<Integer> subset = new java.util.ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                // Check if ith bit is set
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            
            result.add(subset);
        }
        
        return result;
    }
    
    // Approach 3: Cascading
    public static java.util.List<java.util.List<Integer>> subsetsCascading(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        result.add(new java.util.ArrayList<>()); // Start with empty subset
        
        for (int num : nums) {
            int size = result.size();
            
            // For each existing subset, create new subset with current number
            for (int i = 0; i < size; i++) {
                java.util.List<Integer> newSubset = new java.util.ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        java.util.List<java.util.List<Integer>> result1 = subsets(nums);
        System.out.println("\nSubsets (Backtracking): " + result1);
        
        java.util.List<java.util.List<Integer>> result2 = subsetsBitmask(nums);
        System.out.println("Subsets (Bitmask): " + result2);
        
        java.util.List<java.util.List<Integer>> result3 = subsetsCascading(nums);
        System.out.println("Subsets (Cascading): " + result3);
        
        System.out.println("\nTotal subsets: " + result1.size() + " (should be 2³ = 8)");
    }
}
```

---

### Problem 2: Permutations

**Question:** Given an array nums of distinct integers, return all possible permutations.

```java
/**
 * Problem: Permutations
 * LeetCode: 46
 * 
 * Visual:
 * nums =[1][2][3]
 * 
 * Decision Tree:
 *                    []
 *                   / | \
 *[1][2][3]
 *                / \  / \  / \
 *[1][2][3]
 *             |    |    |    |    |    |
 *[1][2][3]
 * 
 * Permutations:,,,,,[1][2][3]
 * Total: n! = 3! = 6 permutations
 * 
 * Approach:
 * Swap each element with current position and recurse
 * 
 * Time Complexity: O(n! × n)
 * Space Complexity: O(n) recursion stack
 */
public class Permutations {
    
    // Approach 1: Backtracking with Swap
    public static java.util.List<java.util.List<Integer>> permute(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }
    
    private static void backtrack(int[] nums, int start, 
                                  java.util.List<java.util.List<Integer>> result) {
        // Base case: reached end of array
        if (start == nums.length) {
            java.util.List<Integer> permutation = new java.util.ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        
        // Try each element at current position
        for (int i = start; i < nums.length; i++) {
            // Swap
            swap(nums, start, i);
            
            // Recurse
            backtrack(nums, start + 1, result);
            
            // Undo swap (BACKTRACK)
            swap(nums, start, i);
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // Approach 2: Backtracking with Used Array
    public static java.util.List<java.util.List<Integer>> permuteUsed(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new java.util.ArrayList<>(), used, result);
        return result;
    }
    
    private static void backtrack(int[] nums, java.util.List<Integer> current,
                                  boolean[] used,
                                  java.util.List<java.util.List<Integer>> result) {
        // Base case: permutation complete
        if (current.size() == nums.length) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        // Try each unused element
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue; // Skip used elements
            }
            
            // Choose
            current.add(nums[i]);
            used[i] = true;
            
            // Explore
            backtrack(nums, current, used, result);
            
            // Unchoose (BACKTRACK)
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
    
    // Variation: Permutations II (with duplicates)
    // LeetCode: 47
    public static java.util.List<java.util.List<Integer>> permuteUnique(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.Arrays.sort(nums); // Sort to handle duplicates
        boolean[] used = new boolean[nums.length];
        backtrackUnique(nums, new java.util.ArrayList<>(), used, result);
        return result;
    }
    
    private static void backtrackUnique(int[] nums, java.util.List<Integer> current,
                                       boolean[] used,
                                       java.util.List<java.util.List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            
            current.add(nums[i]);
            used[i] = true;
            
            backtrackUnique(nums, current, used, result);
            
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        java.util.List<java.util.List<Integer>> result1 = permute(nums);
        System.out.println("\nPermutations (Swap): " + result1);
        
        java.util.List<java.util.List<Integer>> result2 = permuteUsed(nums);
        System.out.println("Permutations (Used Array): " + result2);
        
        System.out.println("Total permutations: " + result1.size() + " (should be 3! = 6)");
        
        // With duplicates
        int[] nums2 = {1, 1, 2};
        System.out.println("\nArray with duplicates: " + java.util.Arrays.toString(nums2));
        java.util.List<java.util.List<Integer>> result3 = permuteUnique(nums2);
        System.out.println("Unique permutations: " + result3);
    }
}
```

---

### Problem 3: Combinations

**Question:** Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

```java
/**
 * Problem: Combinations
 * LeetCode: 77
 * 
 * Visual:
 * n = 4, k = 2
 * Numbers:[1][2][3][4]
 * 
 * Decision Tree:
 *                    []
 *                   / | \ \
 *[1][2][3][4]
 *                / | \  | \  |
 *[1][2][3][4]
 * 
 * Combinations:,,,,,[1][2][3][4]
 * Total: C(n,k) = C(4,2) = 6 combinations
 * 
 * Approach:
 * Choose k elements from 1 to n
 * Use start index to avoid duplicates
 * 
 * Time Complexity: O(C(n,k) × k)
 * Space Complexity: O(k) recursion stack
 */
public class Combinations {
    
    // Approach 1: Basic Backtracking
    public static java.util.List<java.util.List<Integer>> combine(int n, int k) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        backtrack(n, k, 1, new java.util.ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int n, int k, int start,
                                  java.util.List<Integer> current,
                                  java.util.List<java.util.List<Integer>> result) {
        // Base case: combination complete
        if (current.size() == k) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        // Try each number from start to n
        for (int i = start; i <= n; i++) {
            // Choose
            current.add(i);
            
            // Explore (use i+1 to avoid duplicates)
            backtrack(n, k, i + 1, current, result);
            
            // Unchoose (BACKTRACK)
            current.remove(current.size() - 1);
        }
    }
    
    // Approach 2: Optimized with Pruning
    public static java.util.List<java.util.List<Integer>> combineOptimized(int n, int k) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        backtrackOptimized(n, k, 1, new java.util.ArrayList<>(), result);
        return result;
    }
    
    private static void backtrackOptimized(int n, int k, int start,
                                          java.util.List<Integer> current,
                                          java.util.List<java.util.List<Integer>> result) {
        // Base case
        if (current.size() == k) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        // Pruning: only iterate if we can still complete the combination
        // Need k - current.size() more elements
        // Available: n - i + 1 elements from i to n
        // So: n - i + 1 >= k - current.size()
        // => i <= n - (k - current.size()) + 1
        int end = n - (k - current.size()) + 1;
        
        for (int i = start; i <= end; i++) {
            current.add(i);
            backtrackOptimized(n, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    // Variation: Combination Sum
    // LeetCode: 39
    public static java.util.List<java.util.List<Integer>> combinationSum(int[] candidates, int target) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        backtrack(candidates, target, 0, new java.util.ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int[] candidates, int target, int start,
                                  java.util.List<Integer> current,
                                  java.util.List<java.util.List<Integer>> result) {
        // Base case: target reached
        if (target == 0) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        // Base case: target exceeded
        if (target < 0) {
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            
            // Use i (not i+1) because we can reuse same element
            backtrack(candidates, target - candidates[i], i, current, result);
            
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        int n = 4, k = 2;
        
        System.out.println("n = " + n + ", k = " + k);
        
        java.util.List<java.util.List<Integer>> result1 = combine(n, k);
        System.out.println("\nCombinations (Basic): " + result1);
        
        java.util.List<java.util.List<Integer>> result2 = combineOptimized(n, k);
        System.out.println("Combinations (Optimized): " + result2);
        
        System.out.println("Total combinations: " + result1.size() + " (should be C(4,2) = 6)");
        
        // Combination Sum
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println("\nCandidates: " + java.util.Arrays.toString(candidates));
        System.out.println("Target: " + target);
        System.out.println("Combinations: " + combinationSum(candidates, target));
    }
}
```

---

### Problem 4: N-Queens

**Question:** The n-queens puzzle is the problem of placing n queens on an n × n chessboard such that no two queens attack each other.

```java
/**
 * Problem: N-Queens
 * LeetCode: 51
 * 
 * Visual (4-Queens):
 * 
 * Solution 1:
 * . Q . .
 * . . . Q
 * Q . . .
 * . . Q .
 * 
 * Solution 2:
 * . . Q .
 * Q . . .
 * . . . Q
 * . Q . .
 * 
 * Constraints:
 * 1. No two queens in same row
 * 2. No two queens in same column
 * 3. No two queens in same diagonal
 * 
 * Approach:
 * Place queen row by row
 * For each row, try all columns
 * Check if position is safe
 * 
 * Time Complexity: O(N!)
 * Space Complexity: O(N²) for board
 */
public class NQueens {
    
    public static java.util.List<java.util.List<String>> solveNQueens(int n) {
        java.util.List<java.util.List<String>> result = new java.util.ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize board with empty cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        backtrack(board, 0, result);
        return result;
    }
    
    private static void backtrack(char[][] board, int row,
                                  java.util.List<java.util.List<String>> result) {
        int n = board.length;
        
        // Base case: all queens placed
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                // Place queen
                board[row][col] = 'Q';
                
                // Recurse to next row
                backtrack(board, row + 1, result);
                
                // Remove queen (BACKTRACK)
                board[row][col] = '.';
            }
        }
    }
    
    // Check if placing queen at (row, col) is safe
    private static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;
        
        // Check column above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    
    // Construct board representation
    private static java.util.List<String> constructBoard(char[][] board) {
        java.util.List<String> solution = new java.util.ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
    
    // Count number of solutions
    public static int totalNQueens(int n) {
        return countSolutions(new char[n][n], 0);
    }
    
    private static int countSolutions(char[][] board, int row) {
        int n = board.length;
        
        if (row == n) {
            return 1;
        }
        
        int count = 0;
        
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                count += countSolutions(board, row + 1);
                board[row][col] = '.';
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int n = 4;
        
        System.out.println("N-Queens Problem (n = " + n + ")");
        
        java.util.List<java.util.List<String>> solutions = solveNQueens(n);
        
        System.out.println("Number of solutions: " + solutions.size());
        
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("\nSolution " + (i + 1) + ":");
            for (String row : solutions.get(i)) {
                System.out.println(row);
            }
        }
        
        // Count for different n
        System.out.println("\nNumber of solutions for different n:");
        for (int i = 1; i <= 8; i++) {
            System.out.println("n = " + i + ": " + totalNQueens(i) + " solutions");
        }
    }
}
```

---

### Problem 5: Sudoku Solver

**Question:** Write a program to solve a Sudoku puzzle by filling the empty cells.

```java
/**
 * Problem: Sudoku Solver
 * LeetCode: 37
 * 
 * Visual:
 * Input:
 * 5 3 . | . 7 . | . . .
 * 6 . . | 1 9 5 | . . .
 * . 9 8 | . . . | . 6 .
 * ------+-------+------
 * 8 . . | . 6 . | . . 3
 * 4 . . | 8 . 3 | . . 1
 * 7 . . | . 2 . | . . 6
 * ------+-------+------
 * . 6 . | . . . | 2 8 .
 * . . . | 4 1 9 | . . 5
 * . . . | . 8 . | . 7 9
 * 
 * Output (Solved):
 * 5 3 4 | 6 7 8 | 9 1 2
 * 6 7 2 | 1 9 5 | 3 4 8
 * 1 9 8 | 3 4 2 | 5 6 7
 * ------+-------+------
 * 8 5 9 | 7 6 1 | 4 2 3
 * 4 2 6 | 8 5 3 | 7 9 1
 * 7 1 3 | 9 2 4 | 8 5 6
 * ------+-------+------
 * 9 6 1 | 5 3 7 | 2 8 4
 * 2 8 7 | 4 1 9 | 6 3 5
 * 3 4 5 | 2 8 6 | 1 7 9
 * 
 * Constraints:
 * 1. Each row must contain 1-9 without repetition
 * 2. Each column must contain 1-9 without repetition
 * 3. Each 3×3 box must contain 1-9 without repetition
 * 
 * Approach:
 * Find empty cell
 * Try digits 1-9
 * Check if valid
 * Recurse
 * Backtrack if no solution
 * 
 * Time Complexity: O(9^(n²))
 * Space Complexity: O(n²)
 */
public class SudokuSolver {
    
    public static void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private static boolean solve(char[][] board) {
        int n = 9;
        
        // Find empty cell
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == '.') {
                    // Try digits 1-9
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            // Place number
                            board[row][col] = num;
                            
                            // Recurse
                            if (solve(board)) {
                                return true;
                            }
                            
                            // Backtrack
                            board[row][col] = '.';
                        }
                    }
                    
                    // No valid number found
                    return false;
                }
            }
        }
        
        // All cells filled
        return true;
    }
    
    // Check if placing num at (row, col) is valid
    private static boolean isValid(char[][] board, int row, int col, char num) {
        int n = 9;
        
        // Check row
        for (int j = 0; j < n; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }
        
        // Check column
        for (int i = 0; i < n; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        
        // Check 3×3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // Print board
    public static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        
        System.out.println("Input Sudoku:");
        printBoard(board);
        
        solveSudoku(board);
        
        System.out.println("\nSolved Sudoku:");
        printBoard(board);
    }
}
```

---

### Problem 6: Phone Keypad

**Question:** Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

```java
/**
 * Problem: Letter Combinations of a Phone Number
 * LeetCode: 17
 * 
 * Visual:
 * digits = "23"
 * 
 * Phone Keypad:
 * 2: abc
 * 3: def
 * 4: ghi
 * 5: jkl
 * 6: mno
 * 7: pqrs
 * 8: tuv
 * 9: wxyz
 * 
 * Decision Tree:
 *              ""
 *             / | \
 *           a   b   c
 *          /|\ /|\ /|\
 *        ad ae af bd be bf cd ce cf
 * 
 * Combinations: "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
 * Total: 3 × 3 = 9 combinations
 * 
 * Time Complexity: O(4ⁿ) where n is length of digits
 * Space Complexity: O(n) recursion stack
 */
public class PhoneKeypad {
    
    // Mapping of digits to letters
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };
    
    public static java.util.List<String> letterCombinations(String digits) {
        java.util.List<String> result = new java.util.ArrayList<>();
        
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }
    
    private static void backtrack(String digits, int index,
                                  StringBuilder current,
                                  java.util.List<String> result) {
        // Base case: combination complete
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        // Get letters for current digit
        int digit = digits.charAt(index) - '0';
        String letters = KEYPAD[digit];
        
        // Try each letter
        for (int i = 0; i < letters.length(); i++) {
            // Choose
            current.append(letters.charAt(i));
            
            // Explore
            backtrack(digits, index + 1, current, result);
            
            // Unchoose (BACKTRACK)
            current.deleteCharAt(current.length() - 1);
        }
    }
    
    // Iterative approach
    public static java.util.List<String> letterCombinationsIterative(String digits) {
        java.util.List<String> result = new java.util.ArrayList<>();
        
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        
        result.add(""); // Start with empty string
        
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            String letters = KEYPAD[digit];
            
            java.util.List<String> temp = new java.util.ArrayList<>();
            
            for (String combo : result) {
                for (int j = 0; j < letters.length(); j++) {
                    temp.add(combo + letters.charAt(j));
                }
            }
            
            result = temp;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        String digits = "23";
        
        System.out.println("Digits: " + digits);
        
        java.util.List<String> result1 = letterCombinations(digits);
        System.out.println("\nCombinations (Backtracking): " + result1);
        
        java.util.List<String> result2 = letterCombinationsIterative(digits);
        System.out.println("Combinations (Iterative): " + result2);
        
        System.out.println("Total combinations: " + result1.size() + " (should be 3×3=9)");
        
        // More examples
        String[] testCases = {"2", "23", "234"};
        System.out.println("\nMore examples:");
        for (String test : testCases) {
            System.out.println(test + " → " + letterCombinations(test).size() + " combinations");
        }
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Subsets | Backtracking | LeetCode 78 |
| Letter Combinations | Phone Keypad | LeetCode 17 |
| Combinations | Backtracking | LeetCode 77 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Permutations | Backtracking | LeetCode 46 |
| Permutations II | With Duplicates | LeetCode 47 |
| Combination Sum | Backtracking | LeetCode 39 |
| Combination Sum II | With Duplicates | LeetCode 40 |
| Word Search | Grid Backtracking | LeetCode 79 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| N-Queens | Constraint Satisfaction | LeetCode 51 |
| N-Queens II | Count Solutions | LeetCode 52 |
| Sudoku Solver | Constraint Satisfaction | LeetCode 37 |
| Word Search II | Trie + Backtracking | LeetCode 212 |

---

## ✅ Key Takeaways

1. **Backtracking Template** - Choose → Explore → Unchoose
2. **Subsets** - Include/Exclude each element
3. **Permutations** - Swap or use used array
4. **Combinations** - Use start index to avoid duplicates
5. **N-Queens** - Check row, column, diagonals
6. **Sudoku** - Validate row, column, 3×3 box
7. **Pruning** - Optimize by cutting invalid branches early

---

**Previous:** [Dynamic Programming](../dynamic-programming/02-advanced.md)  
**Next:** [Greedy Algorithms](../greedy/01-theory.md)