# 🚀 DSA Pattern 58 — Math & Number Theory

> **Goal:** Math & Number Theory ko product-based company interview level par samajhna — real-world intuition, kyun number theory use hoti hai, kaise identify karein, GCD, LCM, primes, sieve, modular arithmetic, fast exponentiation, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Math & Number Theory Kya Hai?

Math & Number Theory algorithms numbers ke properties par based hote hain.

### Main Topics

```text
1. GCD / HCF
2. LCM
3. Prime Numbers
4. Sieve of Eratosthenes
5. Modular Arithmetic
6. Fast Exponentiation
7. Combinatorics
8. Bit Manipulation
```

### Core Intuition

> Numbers ke mathematical properties use karke problems efficiently solve karna.

Example:

```text
GCD(12, 18) = 6
LCM(12, 18) = 36
```

---

## 2. Math & Number Theory Kyun Use Karte Hain?

Main reasons:

1. GCD / LCM problems
2. Prime number problems
3. Modular arithmetic
4. Fast exponentiation
5. Combinatorics
6. Cryptography
7. Hashing
8. Competitive programming

### Core Intuition

> Mathematical properties se exponential problems polynomial time mein solve hoti hain.

Example:

```text
Naive:
power(a, b) -> O(b)

Fast Exponentiation:
power(a, b) -> O(log b)
```

---

## 3. Real-World Production Examples

| Real System | Math & Number Theory Use |
|---|---|
| Cryptography | Modular arithmetic |
| Blockchain | Hashing |
| E-commerce | Pricing / discounts |
| Finance | Interest calculation |
| Gaming | Random number generation |
| Telecom | Signal processing |
| Compiler | Register allocation |
| Machine Learning | Vector math |
| Recommendation System | Similarity calculation |
| Cloud Computing | Load balancing |
| Security | Encryption |
| Search Engine | Ranking algorithm |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Two numbers ka GCD nikalna hai efficiently. Kaise karoge?”

Answer:

```text
Euclidean Algorithm use karenge.

GCD(a, b) = GCD(b, a % b)
```

Ya:

> “Large power calculate karna hai without overflow. Kaise approach karoge?”

Answer:

```text
Fast Exponentiation + Modular Arithmetic use karenge.

power(a, b, mod) -> O(log b)
```

---

## 4. Kaise Pata Chalega Ki Math & Number Theory Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Math & Number Theory |
|---|---|
| GCD / LCM | Euclidean Algorithm |
| Prime numbers | Sieve |
| Modular arithmetic | Fast Exponentiation |
| Large powers | Fast Exponentiation |
| Combinatorics | Math formulas |
| Divisibility | Number Theory |

### Common Keywords

- GCD
- LCM
- Prime
- Divisor
- Modulo
- Power
- Factorial
- Combination

### Mental Checklist

1. Kya numbers involved hain?  
2. Kya GCD / LCM chahiye?  
3. Kya prime numbers chahiye?  
4. Kya modulo chahiye?  
5. Kya large power chahiye?  

Agar 2–3 answers **yes** hain, to Math & Number Theory try karo.

---

# 5. Math & Number Theory Problem Solve Karne Ke 5 Steps

```text
1. Mathematical property identify karo
2. Formula / theorem choose karo
3. Algorithm design karo
4. Edge cases handle karo
5. Final answer nikalo
```

Example — GCD:

```text
GCD(a, b) = GCD(b, a % b)

Base case:
GCD(a, 0) = a
```

---

# 6. GCD — Euclidean Algorithm

## Kya Hai?

GCD — Greatest Common Divisor — dono numbers ka sabse bada common divisor.

### Example

```text
GCD(12, 18) = 6
GCD(7, 13) = 1
```

### Euclidean Algorithm

```text
GCD(a, b) = GCD(b, a % b)

Base case:
GCD(a, 0) = a
```

---

## GCD Template

```java
public int gcd(int a, int b) {

    while (b != 0) {

        int temp = b;

        b = a % b;

        a = temp;
    }

    return a;
}
```

---

## Example: GCD

## Problem

Do numbers ka GCD nikalo.

```text
a = 12
b = 18

Output = 6
```

## Solution

```java
public int findGCD(int a, int b) {
    return gcd(a, b);
}
```

## Dry Run

```text
a = 12
b = 18

GCD(12, 18)
= GCD(18, 12)
= GCD(12, 6)
= GCD(6, 0)
= 6

Answer = 6
```

## Complexity

- Time: \(O(\log \min(a, b))\)
- Space: \(O(1)\)

---

# 7. LCM

## Kya Hai?

LCM — Least Common Multiple — dono numbers ka sabse chhota common multiple.

### Formula

```text
LCM(a, b) = (a * b) / GCD(a, b)
```

### Example

```text
LCM(12, 18) = 36
LCM(7, 13) = 91
```

---

## LCM Template

```java
public int lcm(int a, int b) {
    return (a / gcd(a, b)) * b;
}
```

---

## Example: LCM

## Problem

Do numbers ka LCM nikalo.

```text
a = 12
b = 18

Output = 36
```

## Solution

```java
public int findLCM(int a, int b) {
    return lcm(a, b);
}
```

## Dry Run

```text
a = 12
b = 18

GCD(12, 18) = 6

LCM = (12 * 18) / 6
    = 36

Answer = 36
```

## Complexity

- Time: \(O(\log \min(a, b))\)
- Space: \(O(1)\)

---

# 8. Prime Numbers

## Kya Hai?

Prime number wo number hai jiske exactly do divisors hote hain:

```text
1 and itself
```

### Example

```text
2, 3, 5, 7, 11, 13
```

### Prime Check

```text
Naive:
O(n)

Optimized:
O(sqrt(n))
```

---

## Prime Check Template

```java
public boolean isPrime(int n) {

    if (n <= 1) {
        return false;
    }

    if (n <= 3) {
        return true;
    }

    if (n % 2 == 0 || n % 3 == 0) {
        return false;
    }

    for (int i = 5; i * i <= n; i += 6) {

        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }

    return true;
}
```

---

## Example: Prime Check

## Problem

Number prime hai ya nahi check karo.

```text
n = 29
Output = true
```

## Solution

```java
public boolean checkPrime(int n) {
    return isPrime(n);
}
```

## Dry Run

```text
n = 29

29 % 2 != 0
29 % 3 != 0

i = 5:
29 % 5 != 0
29 % 7 != 0

i = 11:
11 * 11 > 29

Answer = true
```

## Complexity

- Time: \(O(\sqrt{n})\)
- Space: \(O(1)\)

---

# 9. Sieve of Eratosthenes

## Kya Hai?

Sieve of Eratosthenes ek algorithm hai jo:

```text
1. 1 se n tak saare prime numbers nikalta hai
2. Efficient way mein
```

### Time Complexity

```text
O(n log log n)
```

---

## Sieve Template

```java
public boolean[] sieve(int n) {

    boolean[] isPrime = new boolean[n + 1];

    Arrays.fill(isPrime, true);

    isPrime = false;

    isPrime = false;

    for (int i = 2; i * i <= n; i++) {

        if (isPrime[i]) {

            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }

    return isPrime;
}
```

---

## Example: Sieve

## Problem

1 se n tak saare prime numbers nikalo.

```text
n = 30

Output:
[2][3][5][7][11][13][17][19][23][29]
```

## Solution

```java
public List<Integer> findPrimes(int n) {

    boolean[] isPrime = sieve(n);

    List<Integer> primes = new ArrayList<>();

    for (int i = 2; i <= n; i++) {

        if (isPrime[i]) {
            primes.add(i);
        }
    }

    return primes;
}
```

## Dry Run

```text
n = 30

Sieve:
2 -> prime
3 -> prime
4 -> not prime
5 -> prime
...
29 -> prime

Answer =[2][3][5][7][11][13][17][19][23][29]
```

## Complexity

- Time: \(O(n \log \log n)\)
- Space: \(O(n)\)

---

# 10. Modular Arithmetic

## Kya Hai?

Modular arithmetic mein numbers ko modulo ke saath handle karte hain.

### Example

```text
(5 + 3) % 7 = 1
(5 * 3) % 7 = 1
```

### Rules

```text
(a + b) % m = ((a % m) + (b % m)) % m
(a * b) % m = ((a % m) * (b % m)) % m
```

---

## Modular Arithmetic Template

```java
public int modAdd(int a, int b, int mod) {
    return ((a % mod) + (b % mod)) % mod;
}

public int modMultiply(int a, int b, int mod) {

    long result = (long) a * b;

    return (int) (result % mod);
}
```

---

## Example: Modular Addition

## Problem

Do numbers ka modular addition nikalo.

```text
a = 5
b = 3
mod = 7

Output = 1
```

## Solution

```java
public int addMod(int a, int b, int mod) {
    return modAdd(a, b, mod);
}
```

## Dry Run

```text
a = 5
b = 3
mod = 7

(5 + 3) % 7 = 1

Answer = 1
```

## Complexity

- Time: \(O(1)\)
- Space: \(O(1)\)

---

# 11. Fast Exponentiation

## Kya Hai?

Fast Exponentiation ek algorithm hai jo:

```text
1. Large power efficiently calculate karta hai
2. Modular arithmetic ke saath
```

### Time Complexity

```text
O(log b)
```

---

## Fast Exponentiation Template

```java
public long power(long base, long exponent, long mod) {

    long result = 1;

    base = base % mod;

    while (exponent > 0) {

        if ((exponent & 1) == 1) {
            result = (result * base) % mod;
        }

        base = (base * base) % mod;

        exponent >>= 1;
    }

    return result;
}
```

---

## Example: Fast Exponentiation

## Problem

Large power calculate karo.

```text
base = 2
exponent = 10
mod = 1000000007

Output = 1024
```

## Solution

```java
public long calculatePower(long base, long exponent, long mod) {
    return power(base, exponent, mod);
}
```

## Dry Run

```text
base = 2
exponent = 10
mod = 1000000007

2^10 = 1024

Answer = 1024
```

## Complexity

- Time: \(O(\log b)\)
- Space: \(O(1)\)

---

# 12. Combinatorics

## Kya Hai?

Combinatorics counting problems handle karti hai.

### Main Formulas

```text
nCr = n! / (r! * (n - r)!)

nPr = n! / (n - r)!
```

### Example

```text
5C2 = 10
5P2 = 20
```

---

## Combinatorics Template

```java
public long combination(int n, int r, int mod) {

    if (r > n) {
        return 0;
    }

    long numerator = 1;

    long denominator = 1;

    for (int i = 0; i < r; i++) {

        numerator = (numerator * (n - i)) % mod;

        denominator = (denominator * (i + 1)) % mod;
    }

    return (numerator * power(denominator, mod - 2, mod)) % mod;
}
```

---

## Example: Combination

## Problem

nCr calculate karo.

```text
n = 5
r = 2
mod = 1000000007

Output = 10
```

## Solution

```java
public long calculateCombination(int n, int r, int mod) {
    return combination(n, r, mod);
}
```

## Dry Run

```text
n = 5
r = 2

5C2 = 10

Answer = 10
```

## Complexity

- Time: \(O(r)\)
- Space: \(O(1)\)

---

# 13. Math & Number Theory Patterns

| Pattern | Algorithm | Use |
|---|---|---|
| GCD / LCM | Euclidean | Divisibility |
| Prime Check | Trial Division | Primality |
| Sieve | Sieve of Eratosthenes | Multiple primes |
| Modular Arithmetic | Mod rules | Overflow handling |
| Fast Exponentiation | Binary Exponentiation | Large powers |
| Combinatorics | nCr / nPr | Counting |

---

# 14. Math & Number Theory vs DP

| Feature | Math & Number Theory | DP |
|---|---|---|
| Approach | Mathematical properties | State + transition |
| Example | GCD | Fibonacci |
| Time | Usually O(log n) | Usually O(n) / O(n^2) |
| Best for | Number problems | Optimization problems |

### Rule

```text
Number properties -> Math & Number Theory
Optimization -> DP
```

---

# 15. Common Mistakes

## Mistake 1: GCD Formula Galat Karna

```java
GCD(a, b) = GCD(b, a % b)
```

---

## Mistake 2: LCM Formula Galat Karna

```java
LCM(a, b) = (a / GCD(a, b)) * b
```

---

## Mistake 3: Prime Check Mein sqrt Bhoolna

```java
for (int i = 5; i * i <= n; i += 6) {
    // check
}
```

---

## Mistake 4: Modular Arithmetic Mein Overflow Bhoolna

```java
long result = (long) a * b;
```

---

## Mistake 5: Fast Exponentiation Mein Binary Logic Bhoolna

```java
if ((exponent & 1) == 1) {
    result = (result * base) % mod;
}
```

---

# 16. Math & Number Theory Decision Flow

```text
Problem padho
      |
      v
Kya GCD / LCM chahiye?
      |
      +-- Yes --> Euclidean Algorithm
      |
      +-- Kya prime numbers chahiye?
              |
              +-- Yes --> Sieve
              |
              +-- Kya large power chahiye?
                      |
                      +-- Yes --> Fast Exponentiation
                      |
                      +-- Kya counting chahiye?
                              |
                              +-- Yes --> Combinatorics
```

---

# 17. Important Problems

## Easy

- GCD / LCM
- Prime Number
- Count Primes
- Fizz Buzz

## Medium

- Pow(x, n)
- Count Primes
- Ugly Number
- Happy Number
- Factorial Trailing Zeroes

## Hard

- Super Pow
- Count Different Palindromic Subsequences
- Number of Digit One
- Permutation Sequence

---

# 18. One-Line Recognition

> **Agar problem GCD, LCM, prime numbers, modular arithmetic, ya large powers maang rahi hai — Math & Number Theory try karo.**

Math & Number Theory ka main power ye hai ki number based problems ko efficient way mein solve karta hai — jisse exponential problems polynomial ya logarithmic time mein convert ho jati hain.