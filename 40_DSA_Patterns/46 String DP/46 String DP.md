# 🚀 DSA Pattern 46 — String DP

> **Goal:** String Dynamic Programming ko product-based company interview level par samajhna — real-world intuition, kyun String DP use hota hai, kaise identify karein, LCS, Edit Distance, Longest Palindromic Subsequence, state design, transition, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. String DP Kya Hai?

String DP mein hum do strings ya ek string ke indexes par DP table banate hain.

```text
dp[i][j]
```

Iska matlab:

```text
dp[i][j] = answer for first i characters of string 1
           and first j characters of string 2
```

Example:

```text
dp[i][j] = LCS length till i and j
dp[i][j] = minimum edits till i and j
dp[i][j] = longest palindromic subsequence in range i..j
```

### Simple Intuition

> Jab problem do strings ya string ke two pointers/indices par depend kare, tab String DP use hoti hai.

Example:

```text
text1 = "abcde"
text2 = "ace"

dp[i][j] = LCS length
```

---

## 2. String DP Kyun Use Karte Hain?

Main reasons:

1. Longest Common Subsequence
2. Edit Distance
3. Longest Palindromic Subsequence
4. Shortest Common Supersequence
5. Distinct Subsequences
6. Interleaving String
7. Regular Expression Matching
8. Wildcard Matching
9. Text similarity
10. Spell checking

### Core Intuition

> Har character pair ke liye decide karo — match hai ya nahi.

Example:

```text
text1[i - 1] == text2[j - 1]
    -> dp[i][j] = dp[i - 1][j - 1] + 1

Else:
    -> dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
```

---

## 3. Real-World Production Examples

| Real System | String DP Use |
|---|---|
| Spell Checker | Edit Distance |
| Search Autocomplete | Prefix matching |
| Text Diff Tool | LCS |
| Git Diff | LCS |
| DNA Matching | Sequence alignment |
| Plagiarism Checker | LCS |
| Recommendation System | Text similarity |
| Chat App | Message similarity |
| Search Engine | Query matching |
| Machine Translation | Sequence alignment |
| Speech Recognition | Word alignment |
| Compiler | Token matching |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Spell checker mein user ke galat word ke liye correct word suggest karna hai. Kaise karoge?”

Answer:

```text
Edit Distance DP use karenge.

dp[i][j] = minimum edits to convert word1[0..i-1] to word2[0..j-1]
```

Ya:

> “Git diff mein do files ke beech common lines nikalni hain. Kaise approach karoge?”

Answer:

```text
LCS DP use karenge.

dp[i][j] = LCS length till line i and line j.
```

---

## 4. Kaise Pata Chalega Ki String DP Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible String DP |
|---|---|
| Two strings | String DP |
| LCS | String DP |
| Edit Distance | String DP |
| Palindromic Subsequence | String DP |
| Shortest Common Supersequence | String DP |
| Distinct Subsequences | String DP |
| Interleaving String | String DP |
| Regex Matching | String DP |
| Wildcard Matching | String DP |

### Common Keywords

- Two strings
- Longest common
- Edit distance
- Minimum edits
- Palindromic
- Subsequence
- Supersequence
- Distinct subsequences
- Interleaving
- Matching

### Mental Checklist

1. Kya do strings hain?  
2. Kya subsequence / subarray chahiye?  
3. Kya minimum edits chahiye?  
4. Kya palindrome chahiye?  
5. Kya matching / pattern chahiye?  

Agar 2–3 answers **yes** hain, to String DP try karo.

---

# 5. String DP Problem Solve Karne Ke 5 Steps

```text
1. State define karo
2. Transition likho
3. Base case likho
4. Iteration order decide karo
5. Final answer nikalo
```

Example — LCS:

```text
State:
dp[i][j] = LCS length till i and j

Transition:
match -> dp[i - 1][j - 1] + 1
else -> max(dp[i - 1][j], dp[i][j - 1])

Base case:
dp[j] = 0
dp[i] = 0

Final answer:
dp[m][n]
```

---

# 6. Example 1: Longest Common Subsequence — LCS

## Problem

Do strings diye hain.

Longest Common Subsequence ki length nikalo.

```text
text1 = "abcde"
text2 = "ace"

Output = 3
Explanation: "ace"
```

## Why DP?

```text
dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1]
```

## Transition

```text
If text1[i - 1] == text2[j - 1]:
    dp[i][j] = dp[i - 1][j - 1] + 1

Else:
    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
```

## Solution

```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {

            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(
                    dp[i - 1][j],
                    dp[i][j - 1]
                );
            }
        }
    }

    return dp[m][n];
}
```

## Dry Run

```text
text1 = "abcde"
text2 = "ace"

dp:
    "" a c e
""  0 0 0 0
a   0 1 1 1
b   0 1 1 1
c   0 1 2 2
d   0 1 2 2
e   0 1 2 3

Answer = 3
```

## Complexity

- Time: \(O(m \times n)\)
- Space: \(O(m \times n)\)

---

# 7. Example 2: Edit Distance

## Problem

Do words diye hain.

Minimum operations nikalo jisse word1 ko word2 banaya ja sake.

Operations:

```text
1. Insert
2. Delete
3. Replace
```

```text
word1 = "horse"
word2 = "ros"

Output = 3
```

## Why DP?

```text
dp[i][j] = minimum edits to convert word1[0..i-1] to word2[0..j-1]
```

## Transition

```text
If characters match:
    dp[i][j] = dp[i - 1][j - 1]

Else:
    dp[i][j] = 1 + min(
        dp[i - 1][j],     // delete
        dp[i][j - 1],     // insert
        dp[i - 1][j - 1]  // replace
    )
```

## Solution

```java
public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
        dp[i] = i;
    }

    for (int j = 0; j <= n; j++) {
        dp[j] = j;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {

            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(
                    dp[i - 1][j],
                    Math.min(
                        dp[i][j - 1],
                        dp[i - 1][j - 1]
                    )
                );
            }
        }
    }

    return dp[m][n];
}
```

## Dry Run

```text
word1 = "horse"
word2 = "ros"

Answer = 3

horse -> rorse  (replace h with r)
rorse -> rose   (remove r)
rose  -> ros    (remove e)
```

## Complexity

- Time: \(O(m \times n)\)
- Space: \(O(m \times n)\)

---

# 8. Example 3: Longest Palindromic Subsequence

## Problem

String diya hai.

Longest Palindromic Subsequence ki length nikalo.

```text
s = "bbbab"
Output = 4
Explanation: "bbbb"
```

## Why DP?

```text
dp[i][j] = longest palindromic subsequence in s[i..j]
```

## Transition

```text
If s[i] == s[j]:
    dp[i][j] = dp[i + 1][j - 1] + 2

Else:
    dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
```

## Solution

```java
public int longestPalindromeSubseq(String s) {
    int n = s.length();

    int[][] dp = new int[n][n];

    for (int i = 0; i < n; i++) {
        dp[i][i] = 1;
    }

    for (int len = 2; len <= n; len++) {
        for (int i = 0; i + len - 1 < n; i++) {

            int j = i + len - 1;

            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = dp[i + 1][j - 1] + 2;
            } else {
                dp[i][j] = Math.max(
                    dp[i + 1][j],
                    dp[i][j - 1]
                );
            }
        }
    }

    return dp[n - 1];
}
```

## Dry Run

```text
s = "bbbab"

Longest palindromic subsequence = "bbbb"

Answer = 4
```

## Complexity

- Time: \(O(n^2)\)
- Space: \(O(n^2)\)

---

# 9. Example 4: Shortest Common Supersequence

## Problem

Do strings diye hain.

Shortest string nikalo jisme dono strings subsequence ho.

```text
str1 = "abac"
str2 = "cab"

Output = "cabac"
```

## Why DP?

```text
dp[i][j] = shortest common supersequence length till i and j
```

## Transition

```text
If characters match:
    dp[i][j] = dp[i - 1][j - 1] + 1

Else:
    dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1])
```

## Solution

```java
public String shortestCommonSupersequence(String str1, String str2) {
    int m = str1.length();
    int n = str2.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
        dp[i] = i;
    }

    for (int j = 0; j <= n; j++) {
        dp[j] = j;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {

            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = 1 + Math.min(
                    dp[i - 1][j],
                    dp[i][j - 1]
                );
            }
        }
    }

    StringBuilder sb = new StringBuilder();

    int i = m;
    int j = n;

    while (i > 0 && j > 0) {

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            sb.append(str1.charAt(i - 1));
            i--;
            j--;
        } else if (dp[i - 1][j] < dp[i][j - 1]) {
            sb.append(str1.charAt(i - 1));
            i--;
        } else {
            sb.append(str2.charAt(j - 1));
            j--;
        }
    }

    while (i > 0) {
        sb.append(str1.charAt(i - 1));
        i--;
    }

    while (j > 0) {
        sb.append(str2.charAt(j - 1));
        j--;
    }

    return sb.reverse().toString();
}
```

## Complexity

- Time: \(O(m \times n)\)
- Space: \(O(m \times n)\)

---

# 10. Example 5: Distinct Subsequences

## Problem

Do strings diye hain.

Number of distinct subsequences of `s` that equal `t`.

```text
s = "rabbbit"
t = "rabbit"

Output = 3
```

## Why DP?

```text
dp[i][j] = number of ways to form t[0..j-1] using s[0..i-1]
```

## Transition

```text
dp[i][j] = dp[i - 1][j]

If s[i - 1] == t[j - 1]:
    dp[i][j] += dp[i - 1][j - 1]
```

## Solution

```java
public int numDistinct(String s, String t) {
    int m = s.length();
    int n = t.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
        dp[i] = 1;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {

            dp[i][j] = dp[i - 1][j];

            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                dp[i][j] += dp[i - 1][j - 1];
            }
        }
    }

    return dp[m][n];
}
```

## Complexity

- Time: \(O(m \times n)\)
- Space: \(O(m \times n)\)

---

# 11. String DP Patterns

| Pattern | State | Transition |
|---|---|---|
| LCS | `dp[i][j]` | match ? diagonal + 1 : max(up, left) |
| Edit Distance | `dp[i][j]` | match ? diagonal : 1 + min(up, left, diagonal) |
| Palindromic Subsequence | `dp[i][j]` | match ? inner + 2 : max(skip left, skip right) |
| Shortest Supersequence | `dp[i][j]` | match ? diagonal + 1 : 1 + min(up, left) |
| Distinct Subsequences | `dp[i][j]` | dp[i - 1][j] + match bonus |
| Interleaving String | `dp[i][j]` | OR of two previous states |

---

# 12. Memoization vs Tabulation

| Feature | Memoization | Tabulation |
|---|---|---|
| Approach | Top-down | Bottom-up |
| Style | Recursion | Iteration |
| Stack overflow | Possible | No |
| Space | Recursion stack + DP | DP only |
| Best for | Complex states | Simple states |
| Example | Edit Distance | LCS |

### Rule

```text
Simple two-string DP -> Tabulation
Complex matching / regex -> Memoization
```

---

# 13. Common Mistakes

## Mistake 1: Base Case Bhoolna

Edit Distance:

```java
dp[i] = i;
dp[j] = j;
```

LCS:

```java
dp[j] = 0;
dp[i] = 0;
```

---

## Mistake 2: State Galat Define Karna

LCS:

```text
dp[i][j] = LCS till i and j
```

Galat:

```text
dp[i][j] = LCS at i and j
```

---

## Mistake 3: Transition Galat Likna

Edit Distance:

```java
dp[i][j] = 1 + Math.min(
    dp[i - 1][j],
    Math.min(
        dp[i][j - 1],
        dp[i - 1][j - 1]
    )
);
```

---

## Mistake 4: Iteration Order Galat Karna

Palindromic Subsequence:

```java
for (int len = 2; len <= n; len++) {
    for (int i = 0; i + len - 1 < n; i++) {
        int j = i + len - 1;
        // dp[i][j]
    }
}
```

---

## Mistake 5: Index Off-By-One

String DP mein:

```text
dp[i][j] uses s[i - 1] and t[j - 1]
```

---

# 14. String DP Decision Flow

```text
Problem padho
      |
      v
Kya do strings hain?
      |
      +-- Yes
          |
          v
Kya LCS chahiye?
      |
      +-- Yes --> LCS DP
      |
      +-- Kya minimum edits chahiye?
              |
              +-- Yes --> Edit Distance DP
              |
              +-- Kya supersequence chahiye?
                      |
                      +-- Yes --> Shortest Common Supersequence DP
```

---

# 15. Important Problems

## Medium

- Longest Common Subsequence
- Edit Distance
- Longest Palindromic Subsequence
- Palindromic Substrings
- Interleaving String
- Uncrossed Lines
- Maximum Length of Repeated Subarray

## Hard

- Regular Expression Matching
- Wildcard Matching
- Distinct Subsequences
- Shortest Common Supersequence
- Scramble String

---

# 16. One-Line Recognition

> **Agar problem do strings, subsequence, edit distance, palindrome, ya pattern matching maang rahi hai — String DP try karo.**