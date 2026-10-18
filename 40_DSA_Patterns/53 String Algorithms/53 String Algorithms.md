# 🚀 DSA Pattern 53 — String Algorithms

> **Goal:** String Algorithms ko product-based company interview level par samajhna — real-world intuition, kyun string algorithms use hote hain, kaise identify karein, KMP, Rabin-Karp, Z-Algorithm, pattern matching, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. String Algorithms Kya Hain?

String Algorithms pattern matching aur string processing ke liye use hote hain.

### Main Algorithms

```text
1. KMP — Knuth-Morris-Pratt
2. Rabin-Karp
3. Z-Algorithm
4. Manacher’s Algorithm
5. Aho-Corasick
6. Suffix Array
7. Suffix Tree
```

### Core Intuition

> Pattern ko text mein efficiently search karna.

Example:

```text
Text = "ababcabcabababd"
Pattern = "ababd"

Answer = index 10
```

---

## 2. String Algorithms Kyun Use Karte Hain?

Main reasons:

1. Pattern matching
2. Substring search
3. Plagiarism detection
4. DNA sequence matching
5. Search engine indexing
6. Text editor search
7. Spam detection
8. Log analysis

### Core Intuition

> Naive search O(n*m) hota hai, lekin KMP / Rabin-Karp / Z-Algorithm O(n + m) time mein kaam karte hain.

Example:

```text
Naive:
Text length = n
Pattern length = m
Time = O(n * m)

KMP:
Time = O(n + m)
```

---

## 3. Real-World Production Examples

| Real System | String Algorithm Use |
|---|---|
| Search Engine | Pattern matching |
| Text Editor | Find / Replace |
| Plagiarism Checker | Rabin-Karp |
| DNA Matching | KMP |
| Spam Filter | Pattern matching |
| Log Analysis | Pattern matching |
| Antivirus | Signature matching |
| Compiler | Token matching |
| Chat App | Message search |
| Recommendation System | Keyword matching |
| Ad System | Keyword matching |
| Code Review Tool | Pattern detection |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Text editor mein find functionality implement karni hai. Kaise karoge?”

Answer:

```text
KMP / Rabin-Karp use karenge.

Pattern ko text mein efficiently search karenge.
```

Ya:

> “Plagiarism checker mein do documents ke beech common sentences nikalne hain. Kaise approach karoge?”

Answer:

```text
Rabin-Karp use karenge.

Rolling hash se fast substring matching karenge.
```

---

## 4. Kaise Pata Chalega Ki String Algorithm Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible String Algorithm |
|---|---|
| Pattern matching | KMP / Rabin-Karp |
| Substring search | KMP / Rabin-Karp |
| Repeated pattern | KMP |
| Longest prefix suffix | KMP |
| Multiple patterns | Aho-Corasick |
| Palindromic substring | Manacher’s |
| String hashing | Rabin-Karp |

### Common Keywords

- Pattern
- Substring
- Search
- Match
- Repeated
- Prefix
- Suffix
- Palindrome
- Hash

### Mental Checklist

1. Kya pattern search karna hai?  
2. Kya substring search karna hai?  
3. Kya repeated pattern hai?  
4. Kya multiple patterns hain?  
5. Kya palindrome chahiye?  

Agar 2–3 answers **yes** hain, to String Algorithm try karo.

---

# 5. String Algorithms Comparison

| Algorithm | Time | Space | Best For |
|---|---|---|---|
| Naive | O(n*m) | O(1) | Small input |
| KMP | O(n + m) | O(m) | Single pattern |
| Rabin-Karp | O(n + m) | O(1) | Multiple patterns |
| Z-Algorithm | O(n + m) | O(n) | Prefix matching |
| Manacher’s | O(n) | O(n) | Palindromic substring |
| Aho-Corasick | O(n + m + z) | O(total patterns) | Multiple patterns |

---

# 6. KMP — Knuth-Morris-Pratt

## Kya Hai?

KMP ek pattern matching algorithm hai jo:

```text
1. LPS array banata hai
2. Pattern ko text mein efficiently search karta hai
```

### LPS Array

LPS — Longest Proper Prefix which is also Suffix.

Example:

```text
Pattern = "abab"

LPS:
a -> 0
ab -> 0
aba -> 1
abab -> 2
```

### Core Intuition

> Jab mismatch ho, pattern ko efficiently shift karo.

Example:

```text
Text = "ababcabcabababd"
Pattern = "ababd"

Mismatch at index 4:
Pattern shift using LPS
```

---

## KMP Template

```java
public int[] computeLPS(String pattern) {

    int m = pattern.length();

    int[] lps = new int[m];

    int len = 0;

    int i = 1;

    while (i < m) {

        if (pattern.charAt(i) == pattern.charAt(len)) {
            len++;
            lps[i] = len;
            i++;
        } else {

            if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
    }

    return lps;
}

public int kmpSearch(String text, String pattern) {

    int n = text.length();

    int m = pattern.length();

    int[] lps = computeLPS(pattern);

    int i = 0;

    int j = 0;

    while (i < n) {

        if (text.charAt(i) == pattern.charAt(j)) {
            i++;
            j++;
        }

        if (j == m) {
            return i - j;
        } else if (i < n && text.charAt(i) != pattern.charAt(j)) {

            if (j != 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
    }

    return -1;
}
```

---

## Example: KMP Search

## Problem

Text mein pattern search karo.

```text
Text = "ababcabcabababd"
Pattern = "ababd"

Output = 10
```

## Solution

```java
public int strStr(String text, String pattern) {
    return kmpSearch(text, pattern);
}
```

## Dry Run

```text
Text = "ababcabcabababd"
Pattern = "ababd"

LPS:
a -> 0
ab -> 0
aba -> 1
abab -> 2
ababd -> 0

Search:
Match found at index 10

Answer = 10
```

## Complexity

- Time: \(O(n + m)\)
- Space: \(O(m)\)

---

# 7. Rabin-Karp

## Kya Hai?

Rabin-Karp ek pattern matching algorithm hai jo:

```text
1. Rolling hash use karta hai
2. Pattern ko text mein efficiently search karta hai
```

### Core Intuition

> Hash match hone par actual string compare karo.

Example:

```text
Text = "ababcabcabababd"
Pattern = "ababd"

Rolling hash:
Pattern hash
Text substring hash
```

---

## Rabin-Karp Template

```java
public int rabinKarp(String text, String pattern) {

    int n = text.length();

    int m = pattern.length();

    if (m > n) {
        return -1;
    }

    int base = 256;

    int mod = 1_000_000_007;

    int patternHash = 0;

    int textHash = 0;

    int h = 1;

    for (int i = 0; i < m - 1; i++) {
        h = (h * base) % mod;
    }

    for (int i = 0; i < m; i++) {

        patternHash =
            (patternHash * base + pattern.charAt(i)) % mod;

        textHash =
            (textHash * base + text.charAt(i)) % mod;
    }

    for (int i = 0; i <= n - m; i++) {

        if (patternHash == textHash) {

            if (text.substring(i, i + m).equals(pattern)) {
                return i;
            }
        }

        if (i < n - m) {

            textHash =
                (textHash - text.charAt(i) * h) % mod;

            textHash =
                (textHash * base + text.charAt(i + m)) % mod;

            if (textHash < 0) {
                textHash += mod;
            }
        }
    }

    return -1;
}
```

---

## Example: Rabin-Karp Search

## Problem

Text mein pattern search karo.

```text
Text = "ababcabcabababd"
Pattern = "ababd"

Output = 10
```

## Solution

```java
public int strStr(String text, String pattern) {
    return rabinKarp(text, pattern);
}
```

## Dry Run

```text
Text = "ababcabcabababd"
Pattern = "ababd"

Rolling hash:
Pattern hash
Text substring hash

Match found at index 10

Answer = 10
```

## Complexity

- Time: \(O(n + m)\) average
- Space: \(O(1)\)

---

# 8. Z-Algorithm

## Kya Hai?

Z-Algorithm ek string algorithm hai jo:

```text
1. Har index par longest common prefix nikalta hai
2. Pattern matching efficiently karta hai
```

### Z Array

```text
Z[i] = longest common prefix of s and s[i..]
```

Example:

```text
s = "aabxaab"
Z =[0][1][3][7]
```

---

## Z-Algorithm Template

```java
public int[] zAlgorithm(String s) {

    int n = s.length();

    int[] z = new int[n];

    int left = 0;

    int right = 0;

    for (int i = 1; i < n; i++) {

        if (i <= right) {
            z[i] = Math.min(right - i + 1, z[i - left]);
        }

        while (
            i + z[i] < n &&
            s.charAt(z[i]) == s.charAt(i + z[i])
        ) {
            z[i]++;
        }

        if (i + z[i] - 1 > right) {
            left = i;
            right = i + z[i] - 1;
        }
    }

    return z;
}
```

---

## Example: Z-Algorithm Search

## Problem

Text mein pattern search karo.

```text
Text = "ababcabcabababd"
Pattern = "ababd"

Output = 10
```

## Solution

```java
public int strStr(String text, String pattern) {

    String combined = pattern + "#" + text;

    int[] z = zAlgorithm(combined);

    int m = pattern.length();

    for (int i = m + 1; i < combined.length(); i++) {

        if (z[i] == m) {
            return i - m - 1;
        }
    }

    return -1;
}
```

## Dry Run

```text
Text = "ababcabcabababd"
Pattern = "ababd"

Combined = "ababd#ababcabcabababd"

Z array:
Match found at index 10

Answer = 10
```

## Complexity

- Time: \(O(n + m)\)
- Space: \(O(n + m)\)

---

# 9. String Algorithm Patterns

| Pattern | Algorithm | Use |
|---|---|---|
| Single pattern | KMP | Fast search |
| Multiple patterns | Rabin-Karp | Rolling hash |
| Prefix matching | Z-Algorithm | Z array |
| Palindromic substring | Manacher’s | Longest palindrome |
| Multiple patterns | Aho-Corasick | Dictionary matching |
| Suffix matching | Suffix Array | Substring search |

---

# 10. KMP vs Rabin-Karp vs Z-Algorithm

| Feature | KMP | Rabin-Karp | Z-Algorithm |
|---|---|---|---|
| Approach | LPS array | Rolling hash | Z array |
| Time | O(n + m) | O(n + m) average | O(n + m) |
| Space | O(m) | O(1) | O(n + m) |
| Best for | Single pattern | Multiple patterns | Prefix matching |
| Example | Text search | Plagiarism | String matching |

### Rule

```text
Single pattern -> KMP
Multiple patterns -> Rabin-Karp
Prefix matching -> Z-Algorithm
```

---

# 11. Common Mistakes

## Mistake 1: LPS Array Galat Banana

```java
if (pattern.charAt(i) == pattern.charAt(len)) {
    len++;
    lps[i] = len;
    i++;
}
```

---

## Mistake 2: KMP Search Mein Shift Galat Karna

```java
j = lps[j - 1];
```

---

## Mistake 3: Rabin-Karp Mein Hash Collision Ignore Karna

```java
if (text.substring(i, i + m).equals(pattern)) {
    return i;
}
```

---

## Mistake 4: Z-Algorithm Mein Boundaries Galat Karna

```java
if (i + z[i] < n) {
    // safe
}
```

---

## Mistake 5: Edge Cases Bhoolna

```text
Empty pattern
Pattern longer than text
Pattern equal to text
No match
```

---

# 12. String Algorithm Decision Flow

```text
Problem padho
      |
      v
Kya pattern search karna hai?
      |
      +-- Yes
          |
          v
Kya single pattern hai?
      |
      +-- Yes --> KMP
      |
      +-- Kya multiple patterns hain?
              |
              +-- Yes --> Rabin-Karp / Aho-Corasick
              |
              +-- Kya prefix matching hai?
                      |
                      +-- Yes --> Z-Algorithm
                      |
                      +-- Kya palindrome chahiye?
                              |
                              +-- Yes --> Manacher’s
```

---

# 13. Important Problems

## Medium

- Implement strStr()
- Repeated Substring Pattern
- Shortest Palindrome
- Longest Happy Prefix
- Find the Index of the First Occurrence in a String

## Hard

- Shortest Palindrome
- Repeated String Match
- Minimum Number of Moves to Make Palindrome
- Palindrome Pairs
- Concatenated Words

---

# 14. One-Line Recognition

> **Agar problem pattern matching, substring search, repeated pattern, ya palindrome maang rahi hai — String Algorithm try karo.**

String Algorithms ka main power ye hai ki pattern matching problems ko efficient way mein solve karte hain — jisse search, plagiarism detection, aur text processing fast ho jata hai.