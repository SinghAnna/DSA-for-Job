# 🚀 DSA Pattern 08 — Sorting + Cycle Detection

> **Goal:** Samajhna ki Sorting + Cycle Detection **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Sorting + Cycle Detection Kya Hai?

Ye pattern tab use hota hai jab humein pata karna ho ki elements ko sorted position par lane ke liye **minimum kitne swaps** chahiye.

Idea:

1. Har element ka current index aur sorted index find karo.
2. Elements ko unke sorted position se connect karo.
3. Ye connections ek ya multiple **cycles** banate hain.
4. Ek cycle ko sort karne ke liye:

\[
\text{swaps} = \text{cycle size} - 1
\]

Example:

```text
arr =[1][2][3][4]

Sorted array:
[2][3][4][1]
```

Element mapping:

```text
Value 4 -> sorted index 3
Value 3 -> sorted index 2
Value 2 -> sorted index 1
Value 1 -> sorted index 0
```

Cycle:

```text
0 -> 3 -> 0
1 -> 2 -> 1
```

Cycle sizes:

```text
Cycle 1 size = 2 -> swaps = 1
Cycle 2 size = 2 -> swaps = 1
```

Total swaps:

```text
1 + 1 = 2
```

---

## 2. Sorting + Cycle Detection Kyun Use Karte Hain?

Main reason: **minimum swaps nikalna.**

Agar hum directly sorting karein:

```java
Arrays.sort(arr);
```

To humein pata hi nahi chalega ki minimum kitne swaps lage.

But cycle detection se hum precisely count kar sakte hain.

### Core Intuition

> Ek cycle mein `k` elements ko sahi jagah par lane ke liye `k - 1` swaps chahiye hote hain.

Example:

```text
Cycle: A -> B -> C -> A
Cycle size = 3
Swaps = 2
```

Kyunki:

```text
Swap 1: A apni correct jagah par chala jata hai
Swap 2: B apni correct jagah par chala jata hai
C automatically apni correct jagah par aa jata hai
```

---

## 3. Kaise Pata Chalega Ki Sorting + Cycle Detection Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Pattern |
|---|---|
| Minimum swaps to sort array | Sorting + Cycle Detection |
| Minimum swaps to make array sorted | Sorting + Cycle Detection |
| Minimum swaps based on digit sum | Sorting + Cycle Detection |
| Minimum operations to arrange elements | Sorting + Cycle Detection |
| Elements have natural sorted position | Sorting + Cycle Detection |
| Need count of misplaced elements | Cycle Detection |

### Common Keywords

- Minimum swaps
- Minimum operations
- Sort by digit sum
- Arrange array
- Minimum moves to sort
- Minimum swaps to sort binary grid

### Mental Checklist

1. Kya problem **minimum swaps** maang rahi hai?  
2. Kya elements ko sort karna hai ya sorted order mein arrange karna hai?  
3. Kya har element ki ek sorted position define ho sakti hai?  
4. Kya sorting allowed hai, ya sirf swap operations allowed hain?  
5. Kya cycle structure ban sakta hai?  

Agar 3–4 answers **yes** hain, to Sorting + Cycle Detection try karo.

---

# 4. Cycle Detection Ka Basic Idea

Maan lo array hai:

```text
arr =[1][2][3]
```

Sorted array:

```text
[1][2][3]
```

Original indices:

```text
Value 2 -> index 0
Value 3 -> index 1
Value 1 -> index 2
```

Sorted order:

```text
Value 1 -> should be at index 0
Value 2 -> should be at index 1
Value 3 -> should be at index 2
```

Mapping:

```text
Index 0 has value 2
Value 2 should go to index 1

Index 1 has value 3
Value 3 should go to index 2

Index 2 has value 1
Value 1 should go to index 0
```

Cycle:

```text
0 -> 1 -> 2 -> 0
```

Cycle size:

```text
3
```

Minimum swaps:

```text
3 - 1 = 2
```

---

# 5. Template — Minimum Swaps to Sort

```java
public int minSwaps(int[] arr) {
    int n = arr.length;

    int[][] pairs = new int[n];

    for (int i = 0; i < n; i++) {
        pairs[i] = arr[i];
        pairs[i] = i;
    }

    Arrays.sort(pairs, (a, b) -> Integer.compare(a, b));

    boolean[] visited = new boolean[n];
    int swaps = 0;

    for (int i = 0; i < n; i++) {
        if (visited[i] || pairs[i] == i) {
            continue;
        }

        int cycleSize = 0;
        int j = i;

        while (!visited[j]) {
            visited[j] = true;
            j = pairs[j];
            cycleSize++;
        }

        swaps += cycleSize - 1;
    }

    return swaps;
}
```

### Important

`pairs[i][1]` original index store karta hai.

Sorting ke baad:

```text
pairs[i] = sorted value
pairs[i] = original index of that value
```

Cycle follow karte waqt:

```text
current index -> original index
```

---

# 6. Example 1: Minimum Swaps to Sort an Array

## Problem

Array diya hai. Minimum swaps nikalo jisse array sorted ho jaye.

```text
arr =[1][2][3][4]
Output: 2
```

## Why Sorting + Cycle Detection?

Har element ki sorted position fixed hai.

Hum dekh sakte hain ki elements kis cycle mein hain.

## Solution

```java
public int minSwaps(int[] arr) {
    int n = arr.length;

    int[][] pairs = new int[n];

    for (int i = 0; i < n; i++) {
        pairs[i] = arr[i];
        pairs[i] = i;
    }

    Arrays.sort(pairs, (a, b) -> Integer.compare(a, b));

    boolean[] visited = new boolean[n];
    int swaps = 0;

    for (int i = 0; i < n; i++) {
        if (visited[i] || pairs[i] == i) {
            continue;
        }

        int cycleSize = 0;
        int j = i;

        while (!visited[j]) {
            visited[j] = true;
            j = pairs[j];
            cycleSize++;
        }

        swaps += cycleSize - 1;
    }

    return swaps;
}
```

## Dry Run

```text
arr =[1][2][3][4]

pairs before sorting:
[
 ,[0][4]
 ,[1][3]
 ,[2]
[3][1]
]

After sorting:
[
 ,[1][3]
 ,[2]
 ,[3][1]
[0][4]
]
```

Cycle detection:

```text
i = 0:
not visited
j = 0

visit 0
j = pairs = 3
cycleSize = 1

visit 3
j = pairs = 0
cycleSize = 2

0 already visited
cycle ends

cycleSize = 2
swaps += 2 - 1 = 1

i = 1:
not visited
j = 1

visit 1
j = pairs = 2
cycleSize = 1

visit 2
j = pairs = 1
cycleSize = 2

cycle ends

swaps += 2 - 1 = 1

Total swaps = 2
```

## Complexity

- Sorting: \(O(n \log n)\)
- Cycle detection: \(O(n)\)
- Total time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 7. Example 2: Minimum Swaps to Sort by Digit Sum

## Problem

Array ke numbers ko unke **digit sum** ke according sort karna hai.

Minimum swaps nikalo.

```text
arr =[18][21][37][45]

Digit sums:
37 -> 3 + 7 = 10
45 -> 4 + 5 = 9
21 -> 2 + 1 = 3
18 -> 1 + 8 = 9
```

Sorted by digit sum:

```text
21 -> 3
45 -> 9
18 -> 9
37 -> 10
```

## Why Sorting + Cycle Detection?

Yahan normal value se sort nahi karna.

Hum custom comparator se digit sum ke according sort karte hain.

Phir original index mapping se cycles nikalte hain.

## Solution

```java
public int minSwapsSortByDigitSum(int[] arr) {
    int n = arr.length;

    int[][] pairs = new int[n];

    for (int i = 0; i < n; i++) {
        pairs[i] = arr[i];
        pairs[i] = i;
    }

    Arrays.sort(pairs, (a, b) -> {
        int sumA = digitSum(a);
        int sumB = digitSum(b);

        if (sumA != sumB) {
            return Integer.compare(sumA, sumB);
        }

        return Integer.compare(a, b);
    });

    boolean[] visited = new boolean[n];
    int swaps = 0;

    for (int i = 0; i < n; i++) {
        if (visited[i] || pairs[i] == i) {
            continue;
        }

        int cycleSize = 0;
        int j = i;

        while (!visited[j]) {
            visited[j] = true;
            j = pairs[j];
            cycleSize++;
        }

        swaps += cycleSize - 1;
    }

    return swaps;
}

private int digitSum(int num) {
    int sum = 0;

    while (num > 0) {
        sum += num % 10;
        num /= 10;
    }

    return sum;
}
```

## Complexity

- Digit sum calculation: \(O(d)\), where `d` = number of digits
- Sorting: \(O(n \log n)\)
- Cycle detection: \(O(n)\)
- Total time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 8. Example 3: Minimum Swaps to Sort Binary String Rows

## Problem

Binary grid mein har row ko descending order mein arrange karna hai.

Minimum swaps nikalo.

Example:

```text
grid = [
 ,[0][1]
 ,[1][0]
[0][1]
]
```

Har row ko descending banane ke liye leading zeroes count karo.

```text
Row 0: 00 1 -> 2 leading zeroes
Row 1: 0 11 -> 1 leading zero
Row 2: 0 10 -> 1 leading zero
```

## Why Sorting + Cycle Detection?

Har row ki ek target position hoti hai.

Rows ko target positions par lane ke liye cycles ban sakte hain.

## Idea

```text
1. Har row ke leading zeroes count karo.
2. Rows ko descending order of leading zeroes mein arrange karo.
3. Cycle detection se minimum swaps nikalo.
```

---

# 9. Cycle Detection Ka Visual Example

```text
Original indices:
0 1 2 3

Values:
4 3 2 1

Sorted values:
1 2 3 4
```

Sorted array mein value `1` original index `3` par thi.

To mapping:

```text
Sorted index 0 -> original index 3
Sorted index 1 -> original index 2
Sorted index 2 -> original index 1
Sorted index 3 -> original index 0
```

Cycles:

```text
0 -> 3 -> 0

1 -> 2 -> 1
```

Cycle sizes:

```text
2 and 2
```

Swaps:

```text
(2 - 1) + (2 - 1) = 2
```

---

# 10. Cycle Size and Swaps Relationship

| Cycle Size | Minimum Swaps |
|---|---|
| 1 | 0 |
| 2 | 1 |
| 3 | 2 |
| 4 | 3 |
| 5 | 4 |
| k | k - 1 |

Formula:

\[
\text{total swaps} = \sum (\text{cycle size} - 1)
\]

---

# 11. Common Mistakes

## Mistake 1: Original Index Store Na Karna

Sorting se original index lost ho jata hai.

Isliye pair banao:

```java
pairs[i] = value;
pairs[i] = originalIndex;
```

---

## Mistake 2: Wrong Cycle Direction

Cycle follow karte waqt:

```java
j = pairs[j];
```

Ye original index par jump karta hai.

Galat direction se cycle size galat aa jayega.

---

## Mistake 3: Visited Array Bhool Jana

Agar visited use nahi karoge, to same cycle baar-baar count hogi.

```java
boolean[] visited = new boolean[n];
```

---

## Mistake 4: Already Sorted Elements Ko Cycle Mein Count Karna

Agar:

```java
pairs[i] == i
```

To element already apni correct position par hai.

Use skip karo:

```java
if (visited[i] || pairs[i] == i) {
    continue;
}
```

---

## Mistake 5: Custom Sort Comparator Galat Likhna

Digit sum jaise problems mein comparator clearly define karo:

```java
if (sumA != sumB) {
    return Integer.compare(sumA, sumB);
}

return Integer.compare(a, b);
```

---

# 12. Sorting + Cycle Detection Decision Flow

```text
Problem padho
      |
      v
Kya minimum swaps chahiye?
      |
      +-- No --> Cycle Detection probably nahi
      |
      +-- Yes
          |
          v
Kya elements ki sorted position define ho sakti hai?
      |
      +-- No --> Cycle Detection difficult
      |
      +-- Yes
          |
          v
Kya normal sorting hai?
      |
      +-- Yes --> Sort by value
      |
      +-- Custom sorting hai?
              |
              +-- Yes --> Sort by custom key
              |
              +-- Build index mapping
                      |
                      +-- Find cycles
                              |
                              +-- swaps += cycleSize - 1
```

---

# 13. Important Problems

## Easy

- Minimum Swaps to Sort an Array
- Height Checker

## Medium

- Minimum Swaps to Sort by Digit Sum
- Minimum Swaps to Arrange a Binary Grid
- Minimum Number of Operations to Sort a Binary String
- Minimum Swaps to Make Strings Equal

## Hard

- Minimum Swaps to Sort a Binary Grid
- Minimum Number of Operations to Make Array Continuous
- Minimum Swaps to Group All 1s Together II

---

# 14. One-Line Recognition

> **Agar problem minimum swaps se array/rows/values ko sorted order mein lane ki baat kar rahi hai — Sorting + Cycle Detection use karo.**

Sorting + Cycle Detection ka main power ye hai ki sorting ke baad element mapping se cycles nikal kar hum exactly minimum swaps count kar lete hain.