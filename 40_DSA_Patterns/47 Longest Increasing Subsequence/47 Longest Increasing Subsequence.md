# 🚀 DSA Pattern 47 — Longest Increasing Subsequence (LIS) DP

> **Goal:** LIS ko product-based company interview level par samajhna — real-world intuition, kyun LIS use hota hai, kaise identify karein, O(n²) DP, O(n log n) Binary Search + Patience Sorting, state design, transition, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. LIS Kya Hai?

LIS — Longest Increasing Subsequence — ek subsequence hai jisme elements strictly increasing order mein hote hain.

### Subsequence

Subsequence mein order maintain hota hai, lekin elements skip ho sakte hain.

Example:

```text
nums =[2][3][5][7][9][10][18][101]

LIS =[3][7][18][2]

Length = 4
```

### Important

```text
Subsequence:
Order maintain hota hai

Subarray:
Contiguous elements
```

Example:

```text
nums =[1][2][3][4]

Subsequence  valid[2][4][1]
Subarray  valid[1][2][3]
```

---

## 2. LIS Kyun Use Karte Hain?

Main reasons:

1. Longest increasing sequence
2. Longest decreasing sequence
3. Longest bitonic sequence
4. Russian Doll Envelopes
5. Maximum Height Stack
6. Job Scheduling
7. Version Comparison
8. Data Stream Analysis

### Core Intuition

> Har element ke liye check karo — isse pehle kaunsa smaller element best hai jiske aage ye element jod sakte hain.

Example:

```text
nums =[2][3][5][7][9][10][18][101]

dp = 1  -> 
dp = 1  -> 
dp = 1  -> 
dp = 2  ->[2][5]
dp = 2  ->[2][3]
dp = 3  ->[3][7][2]
dp = 4  ->[2][3][7][101]
dp = 4  ->[3][7][18][2]
```

---

## 3. Real-World Production Examples

| Real System | LIS Use |
|---|---|
| Version Control | Increasing version sequence |
| Data Stream | Longest increasing trend |
| Stock Market | Longest increasing price trend |
| Job Scheduling | Non-overlapping increasing jobs |
| E-commerce | Increasing discount sequence |
| Recommendation System | Increasing relevance sequence |
| Game Development | Increasing level sequence |
| Logistics | Increasing delivery sequence |
| Cloud Monitoring | Increasing load pattern |
| Bioinformatics | Increasing gene sequence |
| Image Processing | Increasing pixel pattern |
| Compiler | Increasing token sequence |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Stock prices ke array mein longest increasing trend nikalna hai. Kaise karoge?”

Answer:

```text
LIS DP use karenge.

dp[i] = length of longest increasing subsequence ending at index i
```

Ya:

> “Microservices ke versions mein longest increasing version chain nikalni hai. Kaise approach karoge?”

Answer:

```text
Versions ko numbers mein convert karo.
LIS DP se longest increasing chain nikal sakte hain.
```

---

## 4. Kaise Pata Chalega Ki LIS Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible LIS |
|---|---|
| Longest increasing subsequence | LIS |
| Longest increasing chain | LIS |
| Longest decreasing subsequence | LIS reverse |
| Longest bitonic subsequence | LIS + LDS |
| Russian Doll Envelopes | LIS on sorted dimension |
| Maximum Height Stack | LIS on sorted dimension |
| Non-overlapping intervals | LIS / Greedy |
| Increasing trend | LIS |

### Common Keywords

- Longest increasing
- Longest decreasing
- Longest chain
- Increasing subsequence
- Decreasing subsequence
- Bitonic
- Stack boxes
- Russian Doll
- Non-overlapping

### Mental Checklist

1. Kya sequence / array hai?  
2. Kya increasing / decreasing order chahiye?  
3. Kya subsequence chahiye?  
4. Kya chain / stack banana hai?  
5. Kya sorting + LIS possible hai?  

Agar 2–3 answers **yes** hain, to LIS try karo.

---

# 5. LIS Problem Solve Karne Ke 5 Steps

```text
1. State define karo
2. Transition likho
3. Base case likho
4. Iteration order decide karo
5. Final answer nikalo
```

Example — LIS O(n²):

```text
State:
dp[i] = length of LIS ending at index i

Transition:
dp[i] = max(dp[j] + 1) for all j < i where nums[j] < nums[i]

Base case:
dp[i] = 1 for all i

Final answer:
max(dp[i])
```

---

# 6. Example 1: LIS — O(n²) DP

## Problem

Array diya hai.

Longest strictly increasing subsequence ki length nikalo.

```text
nums =[2][3][5][7][9][10][18][101]
Output = 4
Explanation:[2][3][7][18]
```

## Why DP?

```text
dp[i] = length of LIS ending at index i
```

## Transition

```text
dp[i] = max(dp[j] + 1)
for all j < i where nums[j] < nums[i]
```

## Solution

```java
public int lengthOfLIS(int[] nums) {
    int n = nums.length;

    int[] dp = new int[n];

    Arrays.fill(dp, 1);

    int answer = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {

            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        answer = Math.max(answer, dp[i]);
    }

    return answer;
}
```

## Dry Run

```text
nums =[2][3][5][7][9][10][18][101]

dp = 1
dp = 1
dp = 1
dp = 2
dp = 2
dp = 3
dp = 4
dp = 4

Answer = 4
```

## Complexity

- Time: \(O(n^2)\)
- Space: \(O(n)\)

---

# 7. Example 2: LIS — O(n log n) Binary Search

## Problem

Same LIS problem.

O(n log n) time mein solve karo.

```text
nums =[2][3][5][7][9][10][18][101]
Output = 4
```

## Why Binary Search?

Har element ke liye:

```text
1. tails array mein correct position dhundo
2. replace karo ya append karo
```

### tails Array

```text
tails[i] = smallest possible tail value of LIS of length i + 1
```

## Solution

```java
public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];

    int size = 0;

    for (int num : nums) {

        int index = binarySearch(tails, 0, size, num);

        if (index == size) {
            tails[size++] = num;
        } else {
            tails[index] = num;
        }
    }

    return size;
}

private int binarySearch(int[] tails, int left, int right, int target) {

    while (left < right) {

        int mid = left + (right - left) / 2;

        if (tails[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}
```

## Dry Run

```text
nums =[2][3][5][7][9][10][18][101]

tails = []

10 -> tails = 
9  -> tails = 
2  -> tails = 
5  -> tails =[2][5]
3  -> tails =[2][3]
7  -> tails =[3][7][2]
101 -> tails =[2][3][7][101]
18 -> tails =[3][7][18][2]

Answer = 4
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 8. Example 3: Russian Doll Envelopes

## Problem

Envelopes diye hain.

Envelope `(w1, h1)` envelope `(w2, h2)` ke andar tabhi ja sakta hai jab:

```text
w1 < w2
h1 < h2
```

Maximum number of envelopes nested nikalo.

```text
envelopes = [,,, ][2][3][4][5][6][7]
Output = 3
Explanation:  ->  ->[2][3][4][5][6][7]
```

## Why LIS?

```text
1. Width ke hisaab se sort karo
2. Agar width same hai, height descending sort karo
3. Heights par LIS nikalo
```

## Solution

```java
public int maxEnvelopes(int[][] envelopes) {

    Arrays.sort(envelopes, (a, b) -> {
        if (a != b) {
            return Integer.compare(a, b);
        }
        return Integer.compare(b, a);
    });

    int[] heights = new int[envelopes.length];

    for (int i = 0; i < envelopes.length; i++) {
        heights[i] = envelopes[i];
    }

    return lengthOfLIS(heights);
}

private int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];

    int size = 0;

    for (int num : nums) {

        int index = binarySearch(tails, 0, size, num);

        if (index == size) {
            tails[size++] = num;
        } else {
            tails[index] = num;
        }
    }

    return size;
}

private int binarySearch(int[] tails, int left, int right, int target) {

    while (left < right) {

        int mid = left + (right - left) / 2;

        if (tails[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}
```

## Dry Run

```text
envelopes = [,,, ][2][3][4][5][6][7]

After sorting:
,,,[2][3][4][5][6][7]

Heights:
[3][4][7]

LIS =[4][7][3]

Answer = 3
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 9. Example 4: Maximum Height by Stacking Cuboids

## Problem

Cuboids diye hain.

Cuboid ko rotate kar sakte ho.

Stack banane hai jisme har cuboid niche wale se strictly smaller dimensions ho.

Maximum height nikalo.

```text
cuboids = [,, ][12][20][23][37][45][50][53][95]
Output = 190
```

## Why LIS?

```text
1. Har cuboid ko sort karo
2. Cuboids ko sort karo
3. LIS style DP lagao
```

## Solution

```java
public int maxHeight(int[][] cuboids) {

    for (int[] cuboid : cuboids) {
        Arrays.sort(cuboid);
    }

    Arrays.sort(cuboids, (a, b) -> {
        if (a != b) return Integer.compare(a, b);
        if (a != b) return Integer.compare(a, b);
        return Integer.compare(a, b);
    });

    int n = cuboids.length;

    int[] dp = new int[n];

    int answer = 0;

    for (int i = 0; i < n; i++) {

        dp[i] = cuboids[i];

        for (int j = 0; j < i; j++) {

            if (cuboids[j] <= cuboids[i] &&
                cuboids[j] <= cuboids[i] &&
                cuboids[j] <= cuboids[i]) {

                dp[i] = Math.max(
                    dp[i],
                    dp[j] + cuboids[i]
                );
            }
        }

        answer = Math.max(answer, dp[i]);
    }

    return answer;
}
```

## Complexity

- Time: \(O(n^2)\)
- Space: \(O(n)\)

---

# 10. Example 5: Longest Bitonic Subsequence

## Problem

Pehle increasing, phir decreasing subsequence.

Maximum length nikalo.

```text
nums =[1][2][4][5][10][11]
Output = 6
Explanation:[1][2][4][10]
```

## Why LIS?

```text
1. Left se LIS nikalo
2. Right se LDS nikalo
3. dp[i] = lis[i] + lds[i] - 1
```

## Solution

```java
public int longestBitonicSubsequence(int[] nums) {
    int n = nums.length;

    int[] lis = new int[n];
    int[] lds = new int[n];

    Arrays.fill(lis, 1);
    Arrays.fill(lds, 1);

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                lis[i] = Math.max(lis[i], lis[j] + 1);
            }
        }
    }

    for (int i = n - 2; i >= 0; i--) {
        for (int j = n - 1; j > i; j--) {
            if (nums[j] < nums[i]) {
                lds[i] = Math.max(lds[i], lds[j] + 1);
            }
        }
    }

    int answer = 0;

    for (int i = 0; i < n; i++) {
        answer = Math.max(answer, lis[i] + lds[i] - 1);
    }

    return answer;
}
```

## Complexity

- Time: \(O(n^2)\)
- Space: \(O(n)\)

---

# 11. LIS Patterns

| Pattern | State | Transition |
|---|---|---|
| LIS | `dp[i]` | max(dp[j] + 1) for nums[j] < nums[i] |
| LDS | `dp[i]` | max(dp[j] + 1) for nums[j] > nums[i] |
| Bitonic | `dp[i]` | lis[i] + lds[i] - 1 |
| Russian Doll | `dp[i]` | LIS on sorted dimension |
| Stack Cuboids | `dp[i]` | LIS with custom condition |
| Number of LIS | `dp[i]` + count | count ways |

---

# 12. O(n²) vs O(n log n)

| Feature | O(n²) DP | O(n log n) Binary Search |
|---|---|---|
| Approach | DP | Patience Sorting |
| Time | \(O(n^2)\) | \(O(n \log n)\) |
| Space | \(O(n)\) | \(O(n)\) |
| Best for | Small n | Large n |
| Reconstruction | Easy | Harder |
| Example | LIS | Russian Doll Envelopes |

### Rule

```text
Small n -> O(n²) DP
Large n -> O(n log n) Binary Search
```

---

# 13. Common Mistakes

## Mistake 1: Subsequence vs Subarray Confuse Karna

```text
Subsequence:
Order maintain, skip allowed

Subarray:
Contiguous
```

---

## Mistake 2: Base Case Bhoolna

```java
Arrays.fill(dp, 1);
```

Har element khud ek LIS hai.

---

## Mistake 3: Transition Galat Likna

```java
if (nums[j] < nums[i]) {
    dp[i] = Math.max(dp[i], dp[j] + 1);
}
```

---

## Mistake 4: Binary Search Boundary Galat Karna

```java
int index = binarySearch(tails, 0, size, num);
```

---

## Mistake 5: Russian Doll Envelopes Mein Sorting Galat Karna

```text
Width ascending
Height descending
```

---

# 14. LIS Decision Flow

```text
Problem padho
      |
      v
Kya increasing / decreasing sequence chahiye?
      |
      +-- No --> DP probably nahi
      |
      +-- Yes
          |
          v
Kya simple array hai?
      |
      +-- Yes --> LIS DP
      |
      +-- Kya boxes / envelopes hain?
              |
              +-- Yes --> Sort + LIS
              |
              +-- Kya bitonic chahiye?
                      |
                      +-- Yes --> LIS + LDS
```

---

# 15. Important Problems

## Medium

- Longest Increasing Subsequence
- Longest Continuous Increasing Subsequence
- Number of Longest Increasing Subsequence
- Maximum Length of Pair Chain
- Russian Doll Envelopes
- Delete and Earn

## Hard

- Maximum Height by Stacking Cuboids
- Number of Longest Increasing Subsequence
- Russian Doll Envelopes
- Longest Valid Parentheses

---

# 16. One-Line Recognition

> **Agar problem longest increasing/decreasing subsequence, chain, stack, ya bitonic sequence maang rahi hai — LIS DP try karo.**

LIS ka main power ye hai ki sequence based optimization problems ko simple state + transition + base case structure mein convert kar deta hai — jisse exponential solutions polynomial ya logarithmic time mein solve ho jate hain.