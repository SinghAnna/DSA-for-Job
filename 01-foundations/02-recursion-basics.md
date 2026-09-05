# 🔄 Recursion Basics

## 📚 Theory

### What is Recursion?

Recursion is when a function calls itself to solve a smaller instance of the same problem.

### Three Rules of Recursion

1. **Base Case** - When to stop (prevents infinite recursion)
2. **Recursive Case** - Function calls itself
3. **Progress** - Each call moves towards base case

### Recursion vs Iteration

| Recursion | Iteration |
|-----------|-----------|
| Uses stack memory | Uses loops |
| Cleaner code | More efficient |
| Risk of stack overflow | No stack overflow |
| Good for trees/graphs | Good for simple loops |

---

## 💻 Java Code Examples

### Example 1: Factorial (Basic Recursion)

```java
/**
 * Factorial using Recursion
 * Time Complexity: O(n)
 * Space Complexity: O(n) - recursion stack
 */
public class Factorial {
    
    // Recursive approach
    public static int factorial(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        // Recursive case
        return n * factorial(n - 1);
    }
    
    // Iterative approach (for comparison)
    public static int factorialIterative(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static void main(String[] args) {
        int n = 5;
        System.out.println("Factorial(" + n + ") = " + factorial(n));
        System.out.println("Factorial(" + n + ") iterative = " + factorialIterative(n));
        
        // Print factorials from 1 to 10
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + "! = " + factorial(i));
        }
    }
}
```

### Example 2: Sum of First N Numbers

```java
/**
 * Sum of first N natural numbers using Recursion
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class SumOfN {
    
    public static int sum(int n) {
        // Base case
        if (n == 1) {
            return 1;
        }
        // Recursive case
        return n + sum(n - 1);
    }
    
    public static void main(String[] args) {
        int n = 10;
        System.out.println("Sum of first " + n + " numbers = " + sum(n));
        // Formula: n*(n+1)/2 = 10*11/2 = 55
    }
}
```

### Example 3: Power Function

```java
/**
 * Power Function: x^n using Recursion
 * Time Complexity: O(n) for naive, O(log n) for optimized
 * Space Complexity: O(n) or O(log n)
 */
public class PowerFunction {
    
    // Naive approach - O(n)
    public static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * power(x, n - 1);
    }
    
    // Optimized approach - O(log n)
    public static int powerOptimized(int x, int n) {
        if (n == 0) {
            return 1;
        }
        
        int half = powerOptimized(x, n / 2);
        
        if (n % 2 == 0) {
            return half * half;
        } else {
            return x * half * half;
        }
    }
    
    public static void main(String[] args) {
        int x = 2, n = 10;
        System.out.println(x + "^" + n + " = " + power(x, n));
        System.out.println(x + "^" + n + " (optimized) = " + powerOptimized(x, n));
    }
}
```

### Example 4: Fibonacci Series

```java
/**
 * Fibonacci Series using Recursion
 * Time Complexity: O(2ⁿ) for naive, O(n) for memoized
 * Space Complexity: O(n)
 */
public class Fibonacci {
    
    // Naive recursive - O(2ⁿ)
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Memoized version - O(n)
    public static int fibonacciMemo(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        
        if (memo[n] != 0) {
            return memo[n];
        }
        
        memo[n] = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        return memo[n];
    }
    
    // Iterative version - O(n), O(1) space
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        
        int prev2 = 0, prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
    
    public static void main(String[] args) {
        int n = 10;
        
        System.out.println("First " + n + " Fibonacci numbers:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        
        System.out.println("\n\nWith memoization:");
        int[] memo = new int[n + 1];
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciMemo(i, memo) + " ");
        }
        
        System.out.println("\n\nIterative:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciIterative(i) + " ");
        }
    }
}
```

### Example 5: Count Digits

```java
/**
 * Count number of digits in a number using Recursion
 * Time Complexity: O(log n) - number of digits
 * Space Complexity: O(log n)
 */
public class CountDigits {
    
    public static int countDigits(int n) {
        // Base case
        if (n == 0) {
            return 0;
        }
        // Recursive case
        return 1 + countDigits(n / 10);
    }
    
    public static void main(String[] args) {
        int num = 12345;
        System.out.println("Digits in " + num + " = " + countDigits(num));
        
        int num2 = 987654321;
        System.out.println("Digits in " + num2 + " = " + countDigits(num2));
    }
}
```

### Example 6: Sum of Digits

```java
/**
 * Sum of digits using Recursion
 * Time Complexity: O(log n)
 * Space Complexity: O(log n)
 */
public class SumOfDigits {
    
    public static int sumDigits(int n) {
        // Base case
        if (n == 0) {
            return 0;
        }
        // Recursive case
        return (n % 10) + sumDigits(n / 10);
    }
    
    public static void main(String[] args) {
        int num = 12345;
        System.out.println("Sum of digits of " + num + " = " + sumDigits(num));
        // 1+2+3+4+5 = 15
    }
}
```

### Example 7: Reverse a String

```java
/**
 * Reverse String using Recursion
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ReverseString {
    
    public static String reverse(String str) {
        // Base case
        if (str.isEmpty()) {
            return str;
        }
        // Recursive case
        return reverse(str.substring(1)) + str.charAt(0);
    }
    
    public static void main(String[] args) {
        String str = "hello";
        System.out.println("Original: " + str);
        System.out.println("Reversed: " + reverse(str));
        
        String str2 = "DSA";
        System.out.println("Original: " + str2);
        System.out.println("Reversed: " + reverse(str2));
    }
}
```

### Example 8: Palindrome Check

```java
/**
 * Check if String is Palindrome using Recursion
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class PalindromeCheck {
    
    public static boolean isPalindrome(String str, int left, int right) {
        // Base case
        if (left >= right) {
            return true;
        }
        
        // If characters don't match
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        
        // Recursive case
        return isPalindrome(str, left + 1, right - 1);
    }
    
    public static boolean isPalindrome(String str) {
        return isPalindrome(str, 0, str.length() - 1);
    }
    
    public static void main(String[] args) {
        String str1 = "racecar";
        String str2 = "hello";
        String str3 = "madam";
        
        System.out.println(str1 + " is palindrome: " + isPalindrome(str1));
        System.out.println(str2 + " is palindrome: " + isPalindrome(str2));
        System.out.println(str3 + " is palindrome: " + isPalindrome(str3));
    }
}
```

### Example 9: Binary Search (Recursive)

```java
/**
 * Binary Search using Recursion
 * Time Complexity: O(log n)
 * Space Complexity: O(log n)
 */
public class BinarySearchRecursive {
    
    public static int binarySearch(int[] arr, int left, int right, int target) {
        // Base case - not found
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        // Base case - found
        if (arr[mid] == target) {
            return mid;
        }
        
        // Recursive case
        if (arr[mid] < target) {
            return binarySearch(arr, mid + 1, right, target);
        } else {
            return binarySearch(arr, left, mid - 1, target);
        }
    }
    
    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length - 1, target);
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 7;
        
        int result = binarySearch(arr, target);
        System.out.println("Found " + target + " at index: " + result);
        
        target = 8;
        result = binarySearch(arr, target);
        System.out.println("Found " + target + " at index: " + result);
    }
}
```

### Example 10: Tower of Hanoi

```java
/**
 * Tower of Hanoi using Recursion
 * Time Complexity: O(2ⁿ)
 * Space Complexity: O(n)
 */
public class TowerOfHanoi {
    
    public static void towerOfHanoi(int n, char from, char to, char aux) {
        // Base case
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
            return;
        }
        
        // Move n-1 disks from 'from' to 'aux'
        towerOfHanoi(n - 1, from, aux, to);
        
        // Move nth disk from 'from' to 'to'
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        
        // Move n-1 disks from 'aux' to 'to'
        towerOfHanoi(n - 1, aux, to, from);
    }
    
    public static void main(String[] args) {
        int n = 3;
        System.out.println("Tower of Hanoi with " + n + " disks:\n");
        towerOfHanoi(n, 'A', 'C', 'B');
        // Total moves: 2ⁿ - 1 = 7
    }
}
```

---

## 📝 Practice Problems

| Problem | Difficulty | Pattern |
|---------|------------|---------|
| Factorial | Easy | Basic Recursion |
| Fibonacci | Easy | Basic Recursion |
| Sum of Digits | Easy | Digit Processing |
| Power Function | Medium | Divide & Conquer |
| Binary Search | Medium | Divide & Conquer |
| Tower of Hanoi | Hard | Classic Recursion |
| Permutations | Medium | Backtracking |
| Subsets | Medium | Backtracking |

---

## ✅ Key Takeaways

1. **Always define base case first** - prevents infinite recursion
2. **Think in terms of smaller subproblems**
3. **Use memoization** to optimize overlapping subproblems
4. **Recursion uses stack memory** - can cause StackOverflowError
5. **Convert to iterative** if space is a concern

---

**Previous:** [Time Complexity](./01-time-complexity.md)  
**Next:** [Input/Output Handling](./03-io-handling.md)