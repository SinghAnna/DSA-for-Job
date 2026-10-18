# 🚀 DSA Pattern 24 — Basic Recursion

> **Goal:** Basic Recursion ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, recursion tree, base case, recursive case, call stack, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Basic Recursion Kya Hai?

Recursion ek technique hai jisme function **khud ko call** karta hai.

Har recursive function ke 2 main parts hote hain:

```text
1. Base Case
2. Recursive Case
```

### Base Case

```text
Wo condition jahan recursion ruk jata hai.
```

### Recursive Case

```text
Function khud ko chhote input ke saath call karta hai.
```

Example — Factorial:

```text
factorial(5) = 5 * factorial(4)
factorial(4) = 4 * factorial(3)
factorial(3) = 3 * factorial(2)
factorial(2) = 2 * factorial(1)
factorial(1) = 1
```

Base case:

```text
n <= 1
```

---

## 2. Basic Recursion Kyun Use Karte Hain?

Main reasons:

1. **Problem naturally recursive hoti hai**
2. **Tree / nested structure traverse karna**
3. **Divide and conquer**
4. **Backtracking**
5. **Dynamic Programming**
6. **Code simple aur readable banana**

### Core Intuition

> Badi problem ko same type ki chhoti problem mein todo.

Example:

```text
factorial(5)

= 5 * factorial(4)
= 5 * 4 * factorial(3)
= 5 * 4 * 3 * factorial(2)
= 5 * 4 * 3 * 2 * factorial(1)
= 5 * 4 * 3 * 2 * 1
= 120
```

---

## 3. Real-World Production Examples

| Real System | Recursion Use |
|---|---|
| File System | Folder ke andar folders/files traverse karna |
| Organization Chart | Manager -> Team -> Sub-team |
| JSON Parsing | Nested JSON objects traverse karna |
| DOM Tree | HTML elements traverse karna |
| Comment System | Nested comments render karna |
| Menu System | Nested categories render karna |
| Compiler | Expression tree parse karna |
| Git | Commit history traversal |
| Kubernetes | Nested resource configuration |
| AI Decision Tree | Decision tree traversal |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Tumhare file storage system mein folders ke andar folders ho sakte hain. Total size kaise nikaloge?”

Answer:

```text
Har folder ke liye:
- Direct files ka size add karo
- Har subfolder ke liye recursively total size nikalo

Base case:
Agar file hai, to uska size return karo.
```

Ya:

> “Nested comments system mein saare replies kaise render karoge?”

Answer:

```text
Har comment ke children ke liye recursively render karo.

Base case:
Agar comment ke replies nahi hain, to wahi return karo.
```

---

## 4. Kaise Pata Chalega Ki Recursion Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Recursion |
|---|---|
| Nested structure | Recursion |
| Tree traversal | Recursion |
| Folder inside folder | Recursion |
| JSON inside JSON | Recursion |
| Divide problem into smaller same problem | Recursion |
| Backtracking | Recursion |
| Combinations / permutations | Recursion |
| Divide and conquer | Recursion |

### Common Keywords

- Nested
- Tree
- Subfolder
- Subproblem
- Divide
- Combination
- Permutation
- Backtrack
- Recursive

### Mental Checklist

1. Kya problem chhoti same problems mein divide ho sakti hai?  
2. Kya natural base case hai?  
3. Kya tree/nested structure hai?  
4. Kya har step par same logic apply hota hai?  
5. Kya recursion depth manageable hai?  

Agar 2–3 answers **yes** hain, to Recursion try karo.

---

# 5. Recursion Ka Structure

```java
returnType function(input) {
    // Base case
    if (baseCondition) {
        return baseAnswer;
    }

    // Recursive case
    return combine(
        currentValue,
        function(smallerInput)
    );
}
```

### Factorial Template

```java
public int factorial(int n) {
    if (n <= 1) {
        return 1;
    }

    return n * factorial(n - 1);
}
```

---

# 6. Call Stack Kaise Kaam Karta Hai?

```text
factorial(5)
```

Call stack:

```text
factorial(5)
factorial(4)
factorial(3)
factorial(2)
factorial(1)
```

Base case hit hone ke baad values return hoti hain:

```text
factorial(1) returns 1
factorial(2) returns 2 * 1 = 2
factorial(3) returns 3 * 2 = 6
factorial(4) returns 4 * 6 = 24
factorial(5) returns 5 * 24 = 120
```

### Stack Overflow

Agar base case na ho:

```java
public int factorial(int n) {
    return n * factorial(n - 1);
}
```

To infinite recursion hogi:

```text
StackOverflowError
```

---

# 7. Example 1: Factorial

## Problem

`n` ka factorial nikalo.

```text
n = 5
Output = 120
```

## Why Recursion?

```text
n! = n * (n - 1)!
```

Problem naturally recursive hai.

## Solution

```java
public int factorial(int n) {
    if (n <= 1) {
        return 1;
    }

    return n * factorial(n - 1);
}
```

## Dry Run

```text
factorial(5)
= 5 * factorial(4)
= 5 * 4 * factorial(3)
= 5 * 4 * 3 * factorial(2)
= 5 * 4 * 3 * 2 * factorial(1)
= 5 * 4 * 3 * 2 * 1
= 120
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\), recursion stack

---

# 8. Example 2: Fibonacci Number

## Problem

`n`th Fibonacci number nikalo.

```text
F(0) = 0
F(1) = 1
F(n) = F(n - 1) + F(n - 2)
```

```text
n = 5
Output = 5
Sequence: 0, 1, 1, 2, 3, 5
```

## Why Recursion?

Fibonacci naturally recursive hai.

## Naive Recursive Solution

```java
public int fib(int n) {
    if (n <= 1) {
        return n;
    }

    return fib(n - 1) + fib(n - 2);
}
```

## Recursion Tree

```text
                fib(5)
              /        \
         fib(4)        fib(3)
         /    \         /    \
     fib(3)  fib(2)  fib(2)  fib(1)
     /   \    /   \    /   \
 fib(2) fib(1) ... ...
```

Same subproblems repeat ho rahe hain.

## Complexity

- Time: \(O(2^n)\)
- Space: \(O(n)\)

### Better — Memoization

```java
public int fibMemo(int n) {
    int[] memo = new int[n + 1];
    return helper(n, memo);
}

private int helper(int n, int[] memo) {
    if (n <= 1) {
        return n;
    }

    if (memo[n] != 0) {
        return memo[n];
    }

    memo[n] = helper(n - 1, memo) + helper(n - 2, memo);

    return memo[n];
}
```

Time: \(O(n)\)  
Space: \(O(n)\)

---

# 9. Example 3: Reverse String

## Problem

Character array ko reverse karo.

```text
Input:
['h', 'e', 'l', 'l', 'o']

Output:
['o', 'l', 'l', 'e', 'h']
```

## Why Recursion?

Har step par:

```text
Left aur right swap karo
Andar wala part recursively reverse karo
```

## Solution

```java
public void reverseString(char[] s) {
    reverse(s, 0, s.length - 1);
}

private void reverse(char[] s, int left, int right) {
    if (left >= right) {
        return;
    }

    char temp = s[left];
    s[left] = s[right];
    s[right] = temp;

    reverse(s, left + 1, right - 1);
}
```

## Dry Run

```text
s = ['h', 'e', 'l', 'l', 'o']

left = 0, right = 4
swap h and o

['o', 'e', 'l', 'l', 'h']

left = 1, right = 3
swap e and l

['o', 'l', 'l', 'e', 'h']

left = 2, right = 2
Base case

Final:
['o', 'l', 'l', 'e', 'h']
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\), recursion stack

---

# 10. Example 4: Sum of Digits

## Problem

Number ke digits ka sum nikalo.

```text
n = 1234
Output = 10
```

## Why Recursion?

```text
sumDigits(1234)
= 4 + sumDigits(123)
= 4 + 3 + sumDigits(12)
= 4 + 3 + 2 + sumDigits(1)
= 4 + 3 + 2 + 1
= 10
```

## Solution

```java
public int sumOfDigits(int n) {
    if (n == 0) {
        return 0;
    }

    return n % 10 + sumOfDigits(n / 10);
}
```

## Dry Run

```text
sumOfDigits(1234)
= 4 + sumOfDigits(123)
= 4 + 3 + sumOfDigits(12)
= 4 + 3 + 2 + sumOfDigits(1)
= 4 + 3 + 2 + 1 + sumOfDigits(0)
= 4 + 3 + 2 + 1 + 0
= 10
```

## Complexity

- Time: \(O(d)\), where `d` = number of digits
- Space: \(O(d)\)

---

# 11. Example 5: Power Function

## Problem

`x` ki power `n` nikalo.

```text
x = 2
n = 10
Output = 1024
```

## Naive Recursion

```java
public double myPow(double x, int n) {
    if (n == 0) {
        return 1;
    }

    return x * myPow(x, n - 1);
}
```

Time: \(O(n)\)

## Better — Fast Power

```text
x^n = (x^(n/2))^2
```

Example:

```text
2^10 = (2^5)^2
2^5 = 2 * (2^2)^2
```

## Solution

```java
public double myPow(double x, int n) {
    long power = n;

    if (power < 0) {
        x = 1 / x;
        power = -power;
    }

    return fastPower(x, power);
}

private double fastPower(double x, long n) {
    if (n == 0) {
        return 1;
    }

    double half = fastPower(x, n / 2);

    if (n % 2 == 0) {
        return half * half;
    }

    return half * half * x;
}
```

## Dry Run

```text
myPow(2, 10)

fastPower(2, 10)
= fastPower(2, 5)^2

fastPower(2, 5)
= fastPower(2, 2)^2 * 2

fastPower(2, 2)
= fastPower(2, 1)^2

fastPower(2, 1)
= fastPower(2, 0)^2 * 2
= 1 * 2
= 2

fastPower(2, 2)
= 4

fastPower(2, 5)
= 4 * 4 * 2
= 32

fastPower(2, 10)
= 32 * 32
= 1024
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(\log n)\)

---

# 12. Example 6: Print 1 to N

## Problem

`1` se `n` tak numbers print karo using recursion.

```text
n = 5
Output:
1 2 3 4 5
```

## Solution

```java
public void print1ToN(int n) {
    if (n == 0) {
        return;
    }

    print1ToN(n - 1);
    System.out.print(n + " ");
}
```

## Dry Run

```text
print1ToN(5)
print1ToN(4)
print1ToN(3)
print1ToN(2)
print1ToN(1)
print1ToN(0)

Base case hit

Return:
print 1
print 2
print 3
print 4
print 5
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 13. Example 7: Print N to 1

## Problem

`n` se `1` tak numbers print karo using recursion.

```text
n = 5
Output:
5 4 3 2 1
```

## Solution

```java
public void printNTo1(int n) {
    if (n == 0) {
        return;
    }

    System.out.print(n + " ");
    printNTo1(n - 1);
}
```

## Dry Run

```text
printNTo1(5)
print 5
printNTo1(4)
print 4
printNTo1(3)
print 3
printNTo1(2)
print 2
printNTo1(1)
print 1
printNTo1(0)
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 14. Recursion vs Iteration

| Feature | Recursion | Iteration |
|---|---|---|
| Code | Clean and short | Sometimes longer |
| Space | Call stack \(O(n)\) | Usually \(O(1)\) |
| Stack overflow risk | Yes | No |
| Best for | Trees, nested structures, backtracking | Simple loops |
| Example | Tree DFS | Array traversal |

### Rule

```text
Nested/tree/backtracking -> Recursion
Simple loop -> Iteration
```

---

# 15. Common Mistakes

## Mistake 1: Base Case Bhoolna

```java
public int factorial(int n) {
    return n * factorial(n - 1);
}
```

Infinite recursion.

Correct:

```java
if (n <= 1) {
    return 1;
}
```

---

## Mistake 2: Base Case Galat Honа

```java
if (n == 0) {
    return 1;
}
```

Factorial ke liye ye theek hai.

But Fibonacci ke liye:

```java
if (n == 0) {
    return 0;
}
```

Zaroori hai.

---

## Mistake 3: Input Reduce Na Karna

```java
return n * factorial(n);
```

Infinite recursion.

Correct:

```java
return n * factorial(n - 1);
```

---

## Mistake 4: Repeated Subproblems Ignore Karna

Fibonacci naive recursion:

```text
Time: O(2^n)
```

Memoization:

```text
Time: O(n)
```

---

## Mistake 5: Deep Recursion Par Stack Overflow

Large `n` ke liye recursion depth badh jayegi.

```text
n = 100000
```

Stack overflow possible.

Iterative solution better ho sakta hai.

---

# 16. Recursion Decision Flow

```text
Problem padho
      |
      v
Kya problem chhoti same problems mein divide ho sakti hai?
      |
      +-- No --> Recursion probably nahi
      |
      +-- Yes
          |
          v
Kya clear base case hai?
      |
      +-- No --> Pehle base case define karo
      |
      +-- Yes
          |
          v
Kya tree / nested structure hai?
      |
      +-- Yes --> Recursion
      |
      +-- Kya choices/backtracking hain?
              |
              +-- Yes --> Recursion + Backtracking
              |
              +-- Kya repeated subproblems hain?
                      |
                      +-- Yes --> Memoization / DP
```

---

# 17. Important Problems

## Easy

- Factorial
- Fibonacci Number
- Sum of Digits
- Reverse String
- Print 1 to N
- Print N to 1
- Power of Two

## Medium

- Pow(x, n)
- Merge Sort
- Binary Tree Inorder Traversal
- Maximum Depth of Binary Tree
- Subsets
- Combination Sum

## Hard

- N-Queens
- Sudoku Solver
- Word Search
- Regular Expression Matching

---

# 18. One-Line Recognition

> **Agar problem ko chhoti same-type subproblems mein divide kiya ja sakta hai, ya nested/tree structure hai — Basic Recursion try karo.**

Recursion ka main power ye hai ki complex problem ko simple recursive structure mein convert kar deta hai, but base case aur stack depth ka dhyan rakhna zaroori hai.