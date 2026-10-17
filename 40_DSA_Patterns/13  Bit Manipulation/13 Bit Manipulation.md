# 🚀 DSA Pattern 13 — Bit Manipulation

> **Goal:** Bit Manipulation ko zero se samajhna — binary number, bitwise operators, bit tricks, XOR magic, templates, dry runs, common mistakes aur important problems.

---

## 1. Bit Manipulation Kya Hai?

Bit Manipulation ka matlab hai number ke **binary bits** ke saath kaam karna.

Har integer memory mein bits ki form mein store hota hai.

Example:

```text
Number 5

Binary: 101

Bits:
bit 2 = 1
bit 1 = 0
bit 0 = 1
```

Value calculation:

```text
1 * 2^2 + 0 * 2^1 + 1 * 2^0
= 4 + 0 + 1
= 5
```

Java mein `int` 32-bit hota hai:

```text
00000000 00000000 00000000 00000101  -> 5
```

---

## 2. Bit Manipulation Kyun Use Karte Hain?

Main reasons:

1. **Fast operations** — bitwise operations CPU level par bahut fast hoti hain.
2. **Space optimization** — ek `int` mein 32 boolean flags store kar sakte hain.
3. **Uniqueness problems** — XOR se duplicate pairs eliminate hote hain.
4. **Power of 2 problems** — bit tricks se easy detection.
5. **Subset generation** — har subset ko bitmask se represent kar sakte hain.
6. **Optimization** — kabhi-kabhi \(O(n^2)\) ko \(O(n)\) ya \(O(2^n)\) ko optimize karna.

### Core Intuition

> Number ko bits ki form mein dekho, aur bits par logical operations apply karo.

---

## 3. Binary Number Basics

### Binary Positions

```text
Binary: 1011

Position: 3 2 1 0
Bits:     1 0 1 1

Value:
1 * 2^3
+ 0 * 2^2
+ 1 * 2^1
+ 1 * 2^0

= 8 + 0 + 2 + 1
= 11
```

### Decimal to Binary

```text
13

13 / 2 = 6 remainder 1
6 / 2  = 3 remainder 0
3 / 2  = 1 remainder 1
1 / 2  = 0 remainder 1

Binary (reverse order): 1101
```

### Binary to Decimal

```text
1101

= 1 * 8 + 1 * 4 + 0 * 2 + 1 * 1
= 8 + 4 + 0 + 1
= 13
```

---

## 4. Bitwise Operators

| Operator | Symbol | Meaning |
|---|---|---|
| AND | `&` | Dono bits 1 hon to 1 |
| OR | `|` | Koi ek bit 1 ho to 1 |
| XOR | `^` | Different bits hon to 1 |
| NOT | `~` | Bits invert |
| Left Shift | `<<` | Bits left move |
| Right Shift | `>>` | Bits right move |
| Unsigned Right Shift | `>>>` | Sign bit ignore karke right shift |

---

## 5. AND Operator `&`

Rule:

```text
0 & 0 = 0
0 & 1 = 0
1 & 0 = 0
1 & 1 = 1
```

Example:

```text
  1101   -> 13
& 1011   -> 11
------
  1001   -> 9
```

Java:

```java
int ans = 13 & 11; // 9
```

### Use Cases

- Check bit is set or not
- Turn off bits
- Check even/odd
- Power of two check

---

## 6. OR Operator `|`

Rule:

```text
0 | 0 = 0
0 | 1 = 1
1 | 0 = 1
1 | 1 = 1
```

Example:

```text
  1101   -> 13
| 1011   -> 11
------
  1111   -> 15
```

Java:

```java
int ans = 13 | 11; // 15
```

### Use Cases

- Set a bit
- Combine flags
- Add permission bits

---

## 7. XOR Operator `^`

Rule:

```text
0 ^ 0 = 0
0 ^ 1 = 1
1 ^ 0 = 1
1 ^ 1 = 0
```

Example:

```text
  1101   -> 13
^ 1011   -> 11
------
  0110   -> 6
```

Java:

```java
int ans = 13 ^ 11; // 6
```

### XOR Properties

```text
a ^ a = 0
a ^ 0 = a
a ^ b = b ^ a
(a ^ b) ^ c = a ^ (b ^ c)
```

### XOR Magic

Agar same numbers pair mein aayein, to XOR unhe cancel kar deta hai.

Example:

```text
1 ^ 2 ^ 3 ^ 2 ^ 1
= (1 ^ 1) ^ (2 ^ 2) ^ 3
= 0 ^ 0 ^ 3
= 3
```

---

## 8. NOT Operator `~`

`~` har bit ko invert karta hai.

```text
n = 5
Binary: 00000000 00000000 00000000 00000101

~n:
11111111 11111111 11111111 11111010
= -6
```

Formula:

```text
~n = -(n + 1)
```

Example:

```text
~5 = -6
~0 = -1
~-1 = 0
```

---

## 9. Left Shift `<<`

Left shift bits ko left move karta hai.

```text
n << k = n * 2^k
```

Example:

```text
5 << 1

Binary: 101
After:  1010

= 10
```

```text
5 << 2

Binary: 101
After:  10100

= 20
```

Java:

```java
int ans = 5 << 2; // 20
```

---

## 10. Right Shift `>>`

Right shift bits ko right move karta hai.

```text
n >> k = n / 2^k
```

Example:

```text
20 >> 1

Binary: 10100
After:  1010

= 10
```

```text
20 >> 2

Binary: 10100
After:  101

= 5
```

Java:

```java
int ans = 20 >> 2; // 5
```

---

# 11. Important Bit Tricks

## Trick 1: Check Even or Odd

```java
boolean isEven = (n & 1) == 0;
boolean isOdd = (n & 1) == 1;
```

Example:

```text
5 -> binary 101
5 & 1 = 1 -> odd

6 -> binary 110
6 & 1 = 0 -> even
```

---

## Trick 2: Check kth Bit

```java
boolean isSet = (n & (1 << i)) != 0;
```

Example:

```text
n = 13 -> 1101
Check bit 2:

1 << 2 = 100

1101
& 0100
------
0100

Result != 0
Bit 2 is set
```

---

## Trick 3: Set kth Bit

```java
n = n | (1 << i);
```

Example:

```text
n = 8 -> 1000
Set bit 1:

1 << 1 = 0010

1000
| 0010
------
1010

n = 10
```

---

## Trick 4: Clear kth Bit

```java
n = n & ~(1 << i);
```

Example:

```text
n = 10 -> 1010
Clear bit 1:

1 << 1 = 0010
~0010 = 1101

1010
& 1101
------
1000

n = 8
```

---

## Trick 5: Toggle kth Bit

```java
n = n ^ (1 << i);
```

Example:

```text
n = 10 -> 1010
Toggle bit 1:

1 << 1 = 0010

1010
^ 0010
------
1000

n = 8
```

---

## Trick 6: Check Power of Two

```java
boolean isPowerOfTwo = n > 0 && (n & (n - 1)) == 0;
```

Example:

```text
n = 8 -> 1000
n - 1 = 7 -> 0111

1000
& 0111
------
0000

Power of two
```

```text
n = 10 -> 1010
n - 1 = 9 -> 1001

1010
& 1001
------
1000

Not zero
Not power of two
```

---

## Trick 7: Remove Last Set Bit

```java
n = n & (n - 1);
```

Example:

```text
n = 12 -> 1100
n - 1 = 11 -> 1011

1100
& 1011
------
1000

Last set bit removed
```

---

## Trick 8: Isolate Last Set Bit

```java
int lastSetBit = n & (-n);
```

Example:

```text
n = 12 -> 1100
-n = -12

1100
& 0100
------
0100

Last set bit = 4
```

---

## Trick 9: Count Set Bits — Brian Kernighan

```java
public int countSetBits(int n) {
    int count = 0;

    while (n != 0) {
        n = n & (n - 1);
        count++;
    }

    return count;
}
```

Example:

```text
n = 13 -> 1101

1101 -> 1100 -> 1000 -> 0000

Count = 3
```

---

## Trick 10: Get ith Bit, Set ith Bit, Clear ith Bit

```java
// Get ith bit
int bit = (n >> i) & 1;

// Set ith bit
n = n | (1 << i);

// Clear ith bit
n = n & ~(1 << i);

// Toggle ith bit
n = n ^ (1 << i);
```

---

# 12. Example 1: Single Number

## Problem

Array mein har element twice aata hai, sirf ek element once aata hai. Wo element nikalo.

```text
nums =[1][2][4]
Output: 4
```

## Why XOR?

XOR properties:

```text
a ^ a = 0
a ^ 0 = a
```

Duplicate pairs cancel ho jayenge.

## Solution

```java
public int singleNumber(int[] nums) {
    int ans = 0;

    for (int num : nums) {
        ans ^= num;
    }

    return ans;
}
```

## Dry Run

```text
nums =[1][2][4]

ans = 0

ans = 0 ^ 4 = 4
ans = 4 ^ 1 = 5
ans = 5 ^ 2 = 7
ans = 7 ^ 1 = 6
ans = 6 ^ 2 = 4

Answer = 4
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 13. Example 2: Single Number II

## Problem

Har element three times aata hai, sirf ek element once aata hai. Wo element nikalo.

```text
nums =[2][3]
Output: 3
```

## Why Normal XOR Fails?

```text
2 ^ 2 ^ 2 = 2
```

XOR pair cancel karta hai, but triple cancel nahi karta.

## Bit Counting Idea

Har bit position par count karo kitne numbers mein bit set hai.

Agar count `3` ka multiple hai, to unique number mein wo bit set nahi hoga.

Agar count `3` ka multiple nahi hai, to unique number mein wo bit set hoga.

## Solution

```java
public int singleNumber(int[] nums) {
    int ans = 0;

    for (int bit = 0; bit < 32; bit++) {
        int count = 0;

        for (int num : nums) {
            if ((num & (1 << bit)) != 0) {
                count++;
            }
        }

        if (count % 3 != 0) {
            ans |= (1 << bit);
        }
    }

    return ans;
}
```

## Dry Run

```text
nums =[2][3]

Binary:
2 -> 010
2 -> 010
3 -> 011
2 -> 010

Bit 0:
2 -> 0
2 -> 0
3 -> 1
2 -> 0

count = 1
1 % 3 != 0
Set bit 0

Bit 1:
2 -> 1
2 -> 1
3 -> 1
2 -> 1

count = 4
4 % 3 != 0
Set bit 1

Answer:
bit 1 + bit 0
= 10 + 01
= 11
= 3
```

## Complexity

- Time: \(O(32 \times n)\)
- Space: \(O(1)\)

---

# 14. Example 3: Number of 1 Bits

## Problem

Integer ke binary representation mein kitne `1` bits hain, count karo.

```text
n = 11
Binary = 1011
Output = 3
```

## Solution 1 — Simple Shift

```java
public int hammingWeight(int n) {
    int count = 0;

    while (n != 0) {
        count += n & 1;
        n = n >>> 1;
    }

    return count;
}
```

## Solution 2 — Brian Kernighan

```java
public int hammingWeight(int n) {
    int count = 0;

    while (n != 0) {
        n = n & (n - 1);
        count++;
    }

    return count;
}
```

## Dry Run

```text
n = 11 -> 1011

1011
& 1010
------
1010

count = 1

1010
& 1001
------
1000

count = 2

1000
& 0111
------
0000

count = 3

Answer = 3
```

## Complexity

- Simple approach: \(O(32)\)
- Brian Kernighan: \(O(\text{number of set bits})\)

---

# 15. Example 4: Counting Bits

## Problem

`0` se `n` tak har number ke set bits count karo.

```text
n = 5

Output:[0][1][2]
```

## Why DP + Bit Manipulation?

Observation:

```text
countBits[i] = countBits[i >> 1] + (i & 1)
```

Kyunki:

```text
i >> 1 -> last bit remove
i & 1 -> last bit
```

Example:

```text
i = 5 -> 101
i >> 1 = 2 -> 10
i & 1 = 1

countBits = countBits + 1
             = 1 + 1
             = 2
```

## Solution

```java
public int[] countBits(int n) {
    int[] ans = new int[n + 1];

    for (int i = 1; i <= n; i++) {
        ans[i] = ans[i >> 1] + (i & 1);
    }

    return ans;
}
```

## Dry Run

```text
n = 5

i = 1:
1 >> 1 = 0
1 & 1 = 1
ans = ans + 1 = 1

i = 2:
2 >> 1 = 1
2 & 1 = 0
ans = ans + 0 = 1

i = 3:
3 >> 1 = 1
3 & 1 = 1
ans = ans + 1 = 2

i = 4:
4 >> 1 = 2
4 & 1 = 0
ans = ans + 0 = 1

i = 5:
5 >> 1 = 2
5 & 1 = 1
ans = ans + 1 = 2

Answer =[0][1][2]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 16. Example 5: Missing Number

## Problem

Array mein `n` distinct numbers hain range `[0, n]` ke. Ek number missing hai. Wo nikalo.

```text
nums =[0][1][3]
Output: 2
```

## Why XOR?

Numbers `0` se `n` tak hone chahiye.

Agar hum saare expected numbers aur array numbers XOR karein, to missing number bach jayega.

## Solution

```java
public int missingNumber(int[] nums) {
    int ans = nums.length;

    for (int i = 0; i < nums.length; i++) {
        ans ^= i ^ nums[i];
    }

    return ans;
}
```

## Dry Run

```text
nums =[0][1][3]
n = 3

Expected XOR:
0 ^ 1 ^ 2 ^ 3

Array XOR:
3 ^ 0 ^ 1

Combined:
(0 ^ 1 ^ 2 ^ 3) ^ (3 ^ 0 ^ 1)
= 2

Answer = 2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 17. Example 6: Power of Two

## Problem

Check karo ki number power of two hai ya nahi.

```text
n = 16
Output: true
```

```text
n = 18
Output: false
```

## Why Bit Trick?

Power of two mein sirf ek set bit hota hai.

```text
1 -> 0001
2 -> 0010
4 -> 0100
8 -> 1000
16 -> 10000
```

## Solution

```java
public boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
}
```

## Dry Run

```text
n = 16

16 -> 10000
15 -> 01111

10000
& 01111
------
00000

Answer = true
```

## Complexity

- Time: \(O(1)\)
- Space: \(O(1)\)

---

# 18. Example 7: Reverse Bits

## Problem

32-bit number ke bits reverse karo.

```text
n = 43261596
Binary = 00000010100101000001111010011100

Reversed = 00111001011110000010100101000000
Output = 964176192
```

## Solution

```java
public int reverseBits(int n) {
    int result = 0;

    for (int i = 0; i < 32; i++) {
        result = (result << 1) | (n & 1);
        n = n >>> 1;
    }

    return result;
}
```

## Dry Run

```text
n = 5 -> 101

Assume 3-bit for simplicity.

Step 1:
n & 1 = 1
result = 0 << 1 | 1 = 1
n = 10

Step 2:
n & 1 = 0
result = 1 << 1 | 0 = 2
n = 5

Step 3:
n & 1 = 1
result = 2 << 1 | 1 = 5

Reversed = 101 -> 5
```

## Complexity

- Time: \(O(32)\)
- Space: \(O(1)\)

---

# 19. Example 8: Sum of Two Integers Without + Operator

## Problem

Do integers ko `+` operator use kiye bina add karo.

```text
a = 1
b = 2
Output = 3
```

## Why XOR + AND?

Binary addition mein:

```text
XOR -> sum without carry
AND << 1 -> carry
```

Example:

```text
a = 1 -> 01
b = 2 -> 10

XOR:
01 ^ 10 = 11 -> 3

AND:
01 & 10 = 00
carry = 0

Answer = 3
```

Example with carry:

```text
a = 5 -> 101
b = 3 -> 011

XOR:
101 ^ 011 = 110 -> 6

AND:
101 & 011 = 001
carry = 001 << 1 = 010 -> 2

Now:
a = 6
b = 2

XOR:
110 ^ 010 = 100 -> 4

AND:
110 & 010 = 010
carry = 100 -> 4

Now:
a = 4
b = 4

XOR:
100 ^ 100 = 000

AND:
100 & 100 = 100
carry = 1000 -> 8

Now:
a = 0
b = 8

XOR:
000 ^ 1000 = 1000 -> 8

AND:
000 & 1000 = 000
carry = 0

Answer = 8
```

## Solution

```java
public int getSum(int a, int b) {
    while (b != 0) {
        int carry = (a & b) << 1;
        a = a ^ b;
        b = carry;
    }

    return a;
}
```

## Complexity

- Time: \(O(1)\) practically, maximum 32 iterations
- Space: \(O(1)\)

---

# 20. Bitmask and Subsets

Bitmask se hum subset represent kar sakte hain.

Example:

```text
nums =[1][2][3]

Mask 000 -> {}
Mask 001 -> {1}
Mask 010 -> {2}
Mask 011 -> {1, 2}
Mask 100 -> {3}
Mask 101 -> {1, 3}
Mask 110 -> {2, 3}
Mask 111 -> {1, 2, 3}
```

Total subsets:

\[
2^n
\]

## Template — Generate Subsets Using Bitmask

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    int n = nums.length;

    for (int mask = 0; mask < (1 << n); mask++) {
        List<Integer> subset = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                subset.add(nums[i]);
            }
        }

        ans.add(subset);
    }

    return ans;
}
```

## Dry Run

```text
nums =[1][2][3]

mask = 0 -> 000 -> {}
mask = 1 -> 001 -> {1}
mask = 2 -> 010 -> {2}
mask = 3 -> 011 -> {1, 2}
mask = 4 -> 100 -> {3}
mask = 5 -> 101 -> {1, 3}
mask = 6 -> 110 -> {2, 3}
mask = 7 -> 111 -> {1, 2, 3}
```

## Complexity

- Time: \(O(n \times 2^n)\)
- Space: \(O(n \times 2^n)\)

---

# 21. Bit Manipulation vs HashMap

| Feature | Bit Manipulation | HashMap |
|---|---|---|
| Space | Often \(O(1)\) | Usually \(O(n)\) |
| Speed | Very fast | Fast |
| Best for | Bits, XOR, masks | Frequency, grouping |
| Works with | Integers / bits | Any object |
| Example | Single Number | Top K Frequent |

---

# 22. Common Mistakes

## Mistake 1: `>>` vs `>>>` Confusion

```java
n >> 1
```

Sign bit preserve karta hai.

```java
n >>> 1
```

Sign bit ignore karke logical right shift karta hai.

Negative numbers ke saath `>>>` use karo.

---

## Mistake 2: Overflow in Left Shift

```java
1 << 31
```

Ye negative number ban sakta hai.

Large values ke liye `long` use karo.

---

## Mistake 3: Power of Two Check Mein `n = 0` Bhoolna

```java
0 & -1 == 0
```

But `0` power of two nahi hai.

Correct:

```java
n > 0 && (n & (n - 1)) == 0
```

---

## Mistake 4: XOR Order Galat Samajhna

XOR associative aur commutative hai:

```text
a ^ b ^ c = c ^ a ^ b
```

Isliye order matter nahi karta.

---

## Mistake 5: Negative Numbers Mein Bit Count Galat Karna

Negative numbers Java mein two’s complement form mein store hote hain.

```text
-1 -> 11111111 11111111 11111111 11111111
```

---

# 23. Bit Manipulation Decision Flow

```text
Problem padho
      |
      v
Kya elements once/twice/thrice appear karte hain?
      |
      +-- Yes --> XOR / Bit Counting
      |
      v
Kya power of two / bits check karne hain?
      |
      +-- Yes --> Bit Tricks
      |
      v
Kya subsets generate karne hain?
      |
      +-- Yes --> Bitmask
      |
      v
Kya addition without + chahiye?
      |
      +-- Yes --> XOR + AND Carry
```

---

# 24. Important Problems

## Easy

- Single Number
- Number of 1 Bits
- Counting Bits
- Power of Two
- Missing Number
- Reverse Bits
- Hamming Distance

## Medium

- Single Number II
- Sum of Two Integers
- Subsets
- Bitwise AND of Numbers Range
- Minimum Flips to Make a OR b Equal to c

## Hard

- Single Number III
- Maximum XOR of Two Numbers in an Array
- Minimum Number of K Consecutive Bit Flips

---

# 25. One-Line Recognition

> **Agar problem uniqueness, duplicates, powers of two, bits, subsets, ya bitwise operations ki baat kar rahi hai — Bit Manipulation try karo.**

Bit Manipulation ka main power ye hai ki XOR duplicates cancel kar deta hai, bit tricks complex checks ko \(O(1)\) mein convert kar dete hain, aur bitmask se subsets efficiently represent hote hain.