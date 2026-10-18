# 🚀 DSA Pattern 62 — Digit DP

> **Goal:** Digit DP ko product-based company interview level par samajhna — real-world intuition, kyun Digit DP use hota hai, kaise identify karein, number based counting, state design, tight bound, leading zeros, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Digit DP Kya Hai?

Digit DP ek technique hai jisme numbers ke **digits** par DP lagate hain.

### Simple Example

```text
Problem:
1 se N tak kitne numbers mein digit 1 hai?

N = 13

Answer = 6
Numbers:
1, 10, 11, 12, 13
```

### Core Intuition

> Number ko digits mein todo, har digit par decision lo.

Example:

```text
N = 13

Digits:
1, 3

Digit DP:
Position 0 -> digit 1
Position 1 -> digit 3
```

---

## 2. Digit DP Kyun Use Karte Hain?

Main reasons:

1. Count numbers with property
2. Count numbers in range
3. Count numbers with digit constraint
4. Count numbers with sum constraint
5. Count numbers with divisibility constraint

### Core Intuition

> Digit DP large numbers ke counting problems efficiently solve karta hai.

Example:

```text
Naive:
1 se N tak loop -> O(N)

Digit DP:
O(digits * states)
```

---

## 3. Real-World Production Examples

| Real System | Digit DP Use |
|---|---|
| Analytics | Count numbers with property |
| Finance | Count transactions in range |
| E-commerce | Count products in range |
| Gaming | Count scores in range |
| Telecom | Count phone numbers |
| Security | Count valid codes |
| Recommendation System | Count IDs in range |
| Cloud Computing | Count resources in range |
| Search Engine | Count queries in range |
| Ad System | Count impressions in range |
| IoT | Count device IDs |
| Database | Count records in range |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “1 se N tak kitne numbers mein digit 1 hai? Kaise karoge?”

Answer:

```text
Digit DP use karenge.

Har digit position par decision lo.

State:
position, tight, count
```

Ya:

> “1 se N tak kitne numbers 7 se divisible hain? Kaise approach karoge?”

Answer:

```text
Digit DP use karenge.

State:
position, remainder, tight
```

---

## 4. Kaise Pata Chalega Ki Digit DP Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Digit DP |
|---|---|
| Count numbers in range | Digit DP |
| Count numbers with digit constraint | Digit DP |
| Count numbers with sum constraint | Digit DP |
| Count numbers with divisibility | Digit DP |
| Large N | Digit DP |

### Common Keywords

- Count numbers
- Range
- Digit
- Divisible
- Sum
- Constraint
- Large N

### Mental Checklist

1. Kya number based counting hai?  
2. Kya range diya hai?  
3. Kya digit constraint hai?  
4. Kya divisibility constraint hai?  
5. Kya large N hai?  

Agar 2–3 answers **yes** hain, to Digit DP try karo.

---

# 5. Digit DP Problem Solve Karne Ke 5 Steps

```text
1. Number ko digits mein todo
2. State define karo
3. Transition likho
4. Tight bound handle karo
5. Final answer nikalo
```

Example — Count Numbers with Digit 1:

```text
State:
position, tight, count

Transition:
Har digit try karo

Tight:
Agar previous digits N ke equal hain
```

---

# 6. Digit DP Template

```java
public int countNumbers(String num) {

    int n = num.length();

    Integer[][][] memo =
        new Integer[n][n + 1];

    return count(0, 1, 0, num, memo);
}

private int count(
    int position,
    int tight,
    int count,
    String num,
    Integer[][][] memo
) {

    if (position == num.length()) {
        return count;
    }

    if (memo[position][tight][count] != null) {
        return memo[position][tight][count];
    }

    int limit =
        tight == 1
            ? num.charAt(position) - '0'
            : 9;

    int total = 0;

    for (int digit = 0; digit <= limit; digit++) {

        int newTight =
            (tight == 1 && digit == limit) ? 1 : 0;

        int newCount =
            count + (digit == 1 ? 1 : 0);

        total += count(
            position + 1,
            newTight,
            newCount,
            num,
            memo
        );
    }

    memo[position][tight][count] = total;

    return total;
}
```

---

# 7. Example 1: Count Numbers with Digit 1

## Problem

1 se N tak kitne numbers mein digit 1 hai?

```text
N = 13
Output = 6
```

## Why Digit DP?

Number based counting problem hai.

## Solution

```java
public int countDigitOne(int n) {

    String num = String.valueOf(n);

    return countNumbers(num);
}
```

## Dry Run

```text
N = 13

Numbers with digit 1:
1, 10, 11, 12, 13

Count = 6

Answer = 6
```

## Complexity

- Time: \(O(digits \times states)\)
- Space: \(O(digits \times states)\)

---

# 8. Example 2: Count Numbers Divisible by 7

## Problem

1 se N tak kitne numbers 7 se divisible hain?

```text
N = 50
Output = 7
Numbers:
7, 14, 21, 28, 35, 42, 49
```

## Why Digit DP?

Divisibility constraint hai.

## State

```text
position, remainder, tight
```

## Transition

```text
newRemainder =
    (remainder * 10 + digit) % 7
```

## Solution

```java
public int countDivisibleBy7(int n) {

    String num = String.valueOf(n);

    Integer[][][] memo =
        new Integer[num.length()];

    return countDivisible(
        0,
        1,
        0,
        num,
        memo
    );
}

private int countDivisible(
    int position,
    int tight,
    int remainder,
    String num,
    Integer[][][] memo
) {

    if (position == num.length()) {
        return remainder == 0 ? 1 : 0;
    }

    if (memo[position][tight][remainder] != null) {
        return memo[position][tight][remainder];
    }

    int limit =
        tight == 1
            ? num.charAt(position) - '0'
            : 9;

    int total = 0;

    for (int digit = 0; digit <= limit; digit++) {

        int newTight =
            (tight == 1 && digit == limit) ? 1 : 0;

        int newRemainder =
            (remainder * 10 + digit) % 7;

        total += countDivisible(
            position + 1,
            newTight,
            newRemainder,
            num,
            memo
        );
    }

    memo[position][tight][remainder] = total;

    return total;
}
```

## Dry Run

```text
N = 50

Numbers divisible by 7:
7, 14, 21, 28, 35, 42, 49

Count = 7

Answer = 7
```

## Complexity

- Time: \(O(digits \times states)\)
- Space: \(O(digits \times states)\)

---

# 9. Example 3: Count Numbers with Digit Sum

## Problem

1 se N tak kitne numbers ka digit sum exactly S hai?

```text
N = 20
S = 3

Output = 2
Numbers:
3, 12
```

## Why Digit DP?

Digit sum constraint hai.

## State

```text
position, sum, tight
```

## Transition

```text
newSum = sum + digit
```

## Solution

```java
public int countDigitSum(int n, int targetSum) {

    String num = String.valueOf(n);

    Integer[][][] memo =
        new Integer[num.length()][targetSum + 1];

    return countSum(
        0,
        1,
        0,
        targetSum,
        num,
        memo
    );
}

private int countSum(
    int position,
    int tight,
    int sum,
    int targetSum,
    String num,
    Integer[][][] memo
) {

    if (sum > targetSum) {
        return 0;
    }

    if (position == num.length()) {
        return sum == targetSum ? 1 : 0;
    }

    if (memo[position][tight][sum] != null) {
        return memo[position][tight][sum];
    }

    int limit =
        tight == 1
            ? num.charAt(position) - '0'
            : 9;

    int total = 0;

    for (int digit = 0; digit <= limit; digit++) {

        int newTight =
            (tight == 1 && digit == limit) ? 1 : 0;

        total += countSum(
            position + 1,
            newTight,
            sum + digit,
            targetSum,
            num,
            memo
        );
    }

    memo[position][tight][sum] = total;

    return total;
}
```

## Dry Run

```text
N = 20
S = 3

Numbers with digit sum 3:
3, 12

Count = 2

Answer = 2
```

## Complexity

- Time: \(O(digits \times states)\)
- Space: \(O(digits \times states)\)

---

# 10. Example 4: Count Numbers Without Repeated Digits

## Problem

1 se N tak kitne numbers mein repeated digits nahi hain?

```text
N = 20
Output = 19
Explanation: 11 repeat karta hai
```

## Why Digit DP?

Digit constraint hai.

## State

```text
position, mask, tight
```

## Transition

```text
if digit already used:
    skip

else:
    set bit in mask
```

## Solution

```java
public int countUniqueDigits(int n) {

    String num = String.valueOf(n);

    Integer[][][] memo =
        new Integer[num.length()][1 << 10];

    return countUnique(
        0,
        1,
        0,
        num,
        memo
    );
}

private int countUnique(
    int position,
    int tight,
    int mask,
    String num,
    Integer[][][] memo
) {

    if (position == num.length()) {
        return 1;
    }

    if (memo[position][tight][mask] != null) {
        return memo[position][tight][mask];
    }

    int limit =
        tight == 1
            ? num.charAt(position) - '0'
            : 9;

    int total = 0;

    for (int digit = 0; digit <= limit; digit++) {

        if ((mask & (1 << digit)) != 0) {
            continue;
        }

        int newTight =
            (tight == 1 && digit == limit) ? 1 : 0;

        int newMask = mask | (1 << digit);

        total += countUnique(
            position + 1,
            newTight,
            newMask,
            num,
            memo
        );
    }

    memo[position][tight][mask] = total;

    return total;
}
```

## Dry Run

```text
N = 20

Numbers without repeated digits:
1, 2, 3, ..., 20 except 11

Count = 19

Answer = 19
```

## Complexity

- Time: \(O(digits \times states)\)
- Space: \(O(digits \times states)\)

---

# 11. Digit DP Patterns

| Pattern | State | Transition |
|---|---|---|
| Count Digit | position, tight, count | digit count |
| Divisibility | position, remainder, tight | remainder update |
| Digit Sum | position, sum, tight | sum update |
| Unique Digits | position, mask, tight | mask update |
| Leading Zeros | position, tight, leadingZero | handle zeros |

---

# 12. Digit DP vs Normal DP

| Feature | Digit DP | Normal DP |
|---|---|---|
| State | Digits | Index / value |
| Input | Number | Array / String |
| Time | O(digits * states) | O(n) / O(n^2) |
| Best for | Number counting | General problems |
| Example | Count Digit One | Coin Change |

### Rule

```text
Number based counting -> Digit DP
General optimization -> Normal DP
```

---

# 13. Common Mistakes

## Mistake 1: Tight Bound Bhoolna

```java
int limit =
    tight == 1
        ? num.charAt(position) - '0'
        : 9;
```

---

## Mistake 2: Leading Zeros Bhoolna

```java
if (position == 0 && digit == 0) {
    // handle leading zero
}
```

---

## Mistake 3: Memoization Key Galat Karna

```java
memo[position][tight][state]
```

---

## Mistake 4: Divisibility Update Galat Karna

```java
newRemainder =
    (remainder * 10 + digit) % 7;
```

---

## Mistake 5: Edge Cases Bhoolna

```text
N = 0
N = 9
N = 10
N = 100
```

---

# 14. Digit DP Decision Flow

```text
Problem padho
      |
      v
Kya number based counting hai?
      |
      +-- No --> Digit DP probably nahi
      |
      +-- Yes
          |
          v
Kya range diya hai?
      |
      +-- Yes --> Digit DP
      |
      +-- Kya digit constraint hai?
              |
              +-- Yes --> Digit DP
              |
              +-- Kya divisibility constraint hai?
                      |
                      +-- Yes --> Digit DP
                      |
                      +-- Kya large N hai?
                              |
                              +-- Yes --> Digit DP
```

---

# 15. Important Problems

## Medium

- Count Numbers with Unique Digits
- Numbers At Most N Given Digit Set
- Count Numbers with Digit Sum
- Count Numbers Divisible by 7

## Hard

- Count Numbers with Repeated Digits
- Numbers At Most N Given Digit Set
- Count Numbers with Digit Sum
- Count Numbers Divisible by 7

---

# 16. One-Line Recognition

> **Agar problem number based counting, range, digit constraint, ya divisibility maang rahi hai — Digit DP try karo.**

Digit DP ka main power ye hai ki large numbers ke counting problems ko efficient way mein solve karta hai