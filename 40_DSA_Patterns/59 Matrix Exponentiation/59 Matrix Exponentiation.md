# 🚀 DSA Pattern 59 — Matrix Exponentiation

> **Goal:** Matrix Exponentiation ko product-based company interview level par samajhna — real-world intuition, kyun Matrix Exponentiation use hota hai, kaise identify karein, Fibonacci, linear recurrence, fast exponentiation, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Matrix Exponentiation Kya Hai?

Matrix Exponentiation ek technique hai jisse:

```text
1. Linear recurrence efficiently solve hoti hai
2. Large powers calculate hote hain
3. O(log n) time mein answer milta hai
```

### Simple Example

```text
Fibonacci:
F(n) = F(n - 1) + F(n - 2)

Matrix Exponentiation:
O(log n)
```

### Core Intuition

> Linear recurrence ko matrix form mein convert karo, phir fast exponentiation lagao.

Example:

```text
[F(n), F(n - 1)] = [, ] * [F(n - 1), F(n - 2)][0][1]
```

---

## 2. Matrix Exponentiation Kyun Use Karte Hain?

Main reasons:

1. Fibonacci
2. Linear recurrence
3. Counting problems
4. Graph paths
5. Dynamic programming optimization
6. Competitive programming

### Core Intuition

> Naive recursion O(2^n) hota hai, lekin Matrix Exponentiation O(log n) hota hai.

Example:

```text
Naive:
Fibonacci -> O(2^n)

Matrix Exponentiation:
Fibonacci -> O(log n)
```

---

## 3. Real-World Production Examples

| Real System | Matrix Exponentiation Use |
|---|---|
| Finance | Compound interest |
| Biology | Population growth |
| Physics | Linear systems |
| Computer Graphics | Transformations |
| Machine Learning | Linear models |
| Cryptography | Modular exponentiation |
| Game Development | State transitions |
| Recommendation System | Markov chains |
| Network Analysis | Path counting |
| Compiler | Register allocation |
| Robotics | State estimation |
| Signal Processing | Linear filters |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Fibonacci number calculate karna hai for very large n. Kaise karoge?”

Answer:

```text
Matrix Exponentiation use karenge.

O(log n) time mein answer milega.
```

Ya:

> “Linear recurrence solve karni hai for large n. Kaise approach karoge?”

Answer:

```text
Matrix Exponentiation use karenge.

Recurrence ko matrix form mein convert karo.
```

---

## 4. Kaise Pata Chalega Ki Matrix Exponentiation Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Matrix Exponentiation |
|---|---|
| Fibonacci | Matrix Exponentiation |
| Linear recurrence | Matrix Exponentiation |
| Large n | Matrix Exponentiation |
| Counting paths | Matrix Exponentiation |
| Markov chain | Matrix Exponentiation |

### Common Keywords

- Fibonacci
- Recurrence
- Large n
- Counting
- Paths
- Linear
- Matrix

### Mental Checklist

1. Kya linear recurrence hai?  
2. Kya large n hai?  
3. Kya O(log n) chahiye?  
4. Kya matrix form possible hai?  
5. Kya counting problem hai?  

Agar 2–3 answers **yes** hain, to Matrix Exponentiation try karo.

---

# 5. Matrix Exponentiation Problem Solve Karne Ke 5 Steps

```text
1. Recurrence identify karo
2. Matrix form banao
3. Base case define karo
4. Fast exponentiation lagao
5. Final answer nikalo
```

Example — Fibonacci:

```text
Recurrence:
F(n) = F(n - 1) + F(n - 2)

Matrix:
[, ][0][1]

Base case:
F(0) = 0
F(1) = 1
```

---

# 6. Matrix Exponentiation Template

```java
public long fibonacci(int n) {

    if (n <= 1) {
        return n;
    }

    long[][] matrix = {
        {1, 1},
        {1, 0}
    };

    long[][] result = matrixPower(matrix, n - 1);

    return result;
}

private long[][] matrixPower(long[][] matrix, int power) {

    long[][] result = {
        {1, 0},
        {0, 1}
    };

    while (power > 0) {

        if ((power & 1) == 1) {
            result = multiply(result, matrix);
        }

        matrix = multiply(matrix, matrix);

        power >>= 1;
    }

    return result;
}

private long[][] multiply(long[][] a, long[][] b) {

    int n = a.length;

    long[][] result = new long[n][n];

    for (int i = 0; i < n; i++) {

        for (int j = 0; j < n; j++) {

            for (int k = 0; k < n; k++) {

                result[i][j] += a[i][k] * b[k][j];
            }
        }
    }

    return result;
}
```

---

# 7. Example 1: Fibonacci — Matrix Exponentiation

## Problem

Fibonacci number calculate karo.

```text
n = 10
Output = 55
```

## Why Matrix Exponentiation?

Large n ke liye O(log n) chahiye.

## Solution

```java
public int fib(int n) {
    return (int) fibonacci(n);
}
```

## Dry Run

```text
n = 10

Matrix:
[, ][0][1]

Matrix Power:
[, ][34][55][89]

Answer = 55
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(1)\)

---

# 8. Example 2: Linear Recurrence — Matrix Exponentiation

## Problem

Linear recurrence solve karo.

```text
f(n) = 2 * f(n - 1) + 3 * f(n - 2)

f(0) = 1
f(1) = 2
```

## Why Matrix Exponentiation?

Large n ke liye O(log n) chahiye.

## Solution

```java
public long solveRecurrence(int n) {

    if (n == 0) {
        return 1;
    }

    if (n == 1) {
        return 2;
    }

    long[][] matrix = {
        {2, 3},
        {1, 0}
    };

    long[][] result = matrixPower(matrix, n - 1);

    return result * 2 + result * 1;
}
```

## Dry Run

```text
n = 3

f(0) = 1
f(1) = 2
f(2) = 2 * 2 + 3 * 1 = 7
f(3) = 2 * 7 + 3 * 2 = 20

Answer = 20
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(1)\)

---

# 9. Example 3: Count Paths in Graph

## Problem

Graph diya hai.

Source se destination tak exactly k steps ke paths count karo.

```text
Graph:
0 -> 1
1 -> 2
2 -> 0

Source = 0
Destination = 2
k = 2

Output = 1
```

## Why Matrix Exponentiation?

Path counting matrix exponentiation se hota hai.

## Solution

```java
public int countPaths(
    int n,
    int[][] graph,
    int source,
    int destination,
    int k
) {

    long[][] matrix = new long[n][n];

    for (int[] edge : graph) {
        matrix[edge][edge] = 1;
    }

    long[][] result = matrixPower(matrix, k);

    return (int) result[source][destination];
}
```

## Dry Run

```text
Graph:
0 -> 1
1 -> 2
2 -> 0

Matrix:
[,[0][1]
,[1][0]
 ][0][1]

Matrix Power k = 2:
[,[0][1]
,[1][0]
 ][0][1]

Answer = 1
```

## Complexity

- Time: \(O(n^3 \log k)\)
- Space: \(O(n^2)\)

---

# 10. Example 4: Tribonacci — Matrix Exponentiation

## Problem

Tribonacci number calculate karo.

```text
T(n) = T(n - 1) + T(n - 2) + T(n - 3)

T(0) = 0
T(1) = 1
T(2) = 1
```

## Why Matrix Exponentiation?

Large n ke liye O(log n) chahiye.

## Solution

```java
public long tribonacci(int n) {

    if (n == 0) {
        return 0;
    }

    if (n == 1 || n == 2) {
        return 1;
    }

    long[][] matrix = {
        {1, 1, 1},
        {1, 0, 0},
        {0, 1, 0}
    };

    long[][] result = matrixPower(matrix, n - 2);

    return result * 1 +
           result * 1 +
           result * 0;
}
```

## Dry Run

```text
n = 5

T(0) = 0
T(1) = 1
T(2) = 1
T(3) = 2
T(4) = 4
T(5) = 7

Answer = 7
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(1)\)

---

# 11. Matrix Exponentiation Patterns

| Pattern | Matrix Size | Use |
|---|---|---|
| Fibonacci | 2x2 | Linear recurrence |
| Tribonacci | 3x3 | Linear recurrence |
| Graph Paths | n x n | Path counting |
| Markov Chain | n x n | State transitions |
| Linear System | n x n | State estimation |

---

# 12. Matrix Exponentiation vs DP

| Feature | Matrix Exponentiation | DP |
|---|---|---|
| Time | O(log n) | O(n) / O(n^2) |
| Space | O(1) / O(n^2) | O(n) / O(n^2) |
| Best for | Linear recurrence | General optimization |
| Example | Fibonacci | Coin Change |

### Rule

```text
Linear recurrence -> Matrix Exponentiation
General optimization -> DP
```

---

# 13. Common Mistakes

## Mistake 1: Matrix Form Galat Banana

Fibonacci:

```java
[, ][0][1]
```

---

## Mistake 2: Matrix Multiplication Galat Karna

```java
result[i][j] += a[i][k] * b[k][j];
```

---

## Mistake 3: Fast Exponentiation Mein Binary Logic Bhoolna

```java
if ((power & 1) == 1) {
    result = multiply(result, matrix);
}
```

---

## Mistake 4: Base Case Bhoolna

```java
if (n <= 1) {
    return n;
}
```

---

## Mistake 5: Overflow Handle Na Karna

```java
long result = (long) a * b;
```

---

# 14. Matrix Exponentiation Decision Flow

```text
Problem padho
      |
      v
Kya linear recurrence hai?
      |
      +-- Yes --> Matrix Exponentiation
      |
      +-- Kya large n hai?
              |
              +-- Yes --> Matrix Exponentiation
              |
              +-- Kya counting problem hai?
                      |
                      +-- Yes --> Matrix Exponentiation
                      |
                      +-- Kya general optimization hai?
                              |
                              +-- Yes --> DP
```

---

# 15. Important Problems

## Medium

- Fibonacci Number
- Tribonacci Number
- Climbing Stairs
- Count Sorted Vowel Strings

## Hard

- Fibonacci Number
- Tribonacci Number
- Number of Ways to Stay in the Same Place After Some Steps
- Number of Paths with Max Score

---

# 16. One-Line Recognition

> **Agar problem linear recurrence, Fibonacci, ya large n maang rahi hai — Matrix Exponentiation try karo.**

Matrix Exponentiation ka main power ye hai ki linear recurrence problems ko O(log n) time mein solve karta hai — jisse large n problems efficiently handle ho jati hain.