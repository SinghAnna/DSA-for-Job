# 🚀 DSA Pattern 12 — Counting / Frequency Array

> **Goal:** Samajhna ki Counting / Frequency Array **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Counting / Frequency Array Kya Hai?

Counting / Frequency Array ek array hota hai jisme hum har possible value ka count store karte hain.

Example:

```text
s = "aabbbcc"

Frequency:
a -> 2
b -> 3
c -> 2
```

Kyunki sirf lowercase English letters hain, hum size `26` ka array use kar sakte hain:

```java
int[] freq = new int;

freq['a' - 'a']++;  // freq++
freq['b' - 'a']++;  // freq++
freq['c' - 'a']++;  // freq++
```

Example:

```text
s = "aabbbcc"

freq = 2  // a
freq = 3  // b
freq = 2  // c
```

---

## 2. Counting / Frequency Array Kyun Use Karte Hain?

Main reason: **jab possible values limited range mein hon.**

Example:

```text
Characters: a to z
Possible values: 26
```

To HashMap ki jagah simple array use kar sakte hain:

```java
int[] freq = new int;
```

### HashMap vs Frequency Array

| Feature | HashMap | Frequency Array |
|---|---|---|
| Possible values | Any | Limited / known range |
| Space | \(O(k)\) | \(O(range)\) |
| Speed | Slightly slower | Faster |
| Code | Flexible | Simple |
| Best for | Any keys | Characters, digits, small numbers |

### Core Intuition

> Agar values 0 se 25, 0 se 9, ya 0 se 127 jaise limited range mein hain, to HashMap ki jagah array use karo.

---

## 3. Kaise Pata Chalega Ki Counting / Frequency Array Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Frequency Array |
|---|---|
| Lowercase English letters | `int[26]` |
| Uppercase English letters | `int[26]` |
| Both cases | `int[128]` or `int[256]` |
| Digits `0-9` | `int[10]` |
| ASCII characters | `int[128]` |
| Small number range | `int[maxValue + 1]` |
| Anagram check | Frequency Array |
| Sort by frequency | Frequency Array |
| Character replacement | Frequency Array |

### Common Keywords

- Frequency
- Count
- Anagram
- Characters
- Digits
- Sort by frequency
- Replace characters
- Repeat

### Mental Checklist

1. Kya input characters, digits, ya small-range numbers hain?  
2. Kya possible values fixed hain?  
3. Kya frequency count karna hai?  
4. Kya HashMap ki zaroorat nahi hai?  
5. Kya memory acceptable hai?  

Agar 2–3 answers **yes** hain, to Counting / Frequency Array try karo.

---

# 4. Frequency Array Templates

## Template 1: Lowercase Letters

```java
int[] freq = new int;

for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}
```

Example:

```text
c = 'c'
'c' - 'a' = 2
freq++
```

---

## Template 2: Uppercase Letters

```java
int[] freq = new int;

for (char c : s.toCharArray()) {
    freq[c - 'A']++;
}
```

---

## Template 3: Both Cases / ASCII

```java
int[] freq = new int;

for (char c : s.toCharArray()) {
    freq[c]++;
}
```

---

## Template 4: Digits

```java
int[] freq = new int;

for (char c : s.toCharArray()) {
    freq[c - '0']++;
}
```

Example:

```text
c = '5'
'5' - '0' = 5
freq++
```

---

# 5. Example 1: Valid Anagram

## Problem

Check karo ki do strings anagram hain ya nahi.

```text
s = "anagram"
t = "nagaram"
Output: true
```

```text
s = "rat"
t = "car"
Output: false
```

## Why Frequency Array?

Sirf lowercase English letters hain.

To `int[26]` frequency array perfect hai.

## Solution

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }

    int[] freq = new int;

    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }

    for (char c : t.toCharArray()) {
        freq[c - 'a']--;

        if (freq[c - 'a'] < 0) {
            return false;
        }
    }

    return true;
}
```

## Dry Run

```text
s = "anagram"
t = "nagaram"

After counting s:
a -> 3
n -> 1
g -> 1
r -> 1
m -> 1

Traverse t:
n -> freq[n]--
a -> freq[a]--
g -> freq[g]--
a -> freq[a]--
r -> freq[r]--
a -> freq[a]--
m -> freq[m]--

All frequencies become 0
Answer = true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(26)\), effectively \(O(1)\)

---

# 6. Example 2: Sort Characters by Frequency

## Problem

String ke characters frequency ke descending order mein sort karo.

```text
s = "tree"
Output: "eert"
Explanation:
e -> 2
r -> 1
t -> 1
```

## Why Frequency Array?

Sirf ASCII characters hain.

To `int[128]` frequency array use kar sakte hain.

## Solution

```java
public String frequencySort(String s) {
    int[] freq = new int;

    for (char c : s.toCharArray()) {
        freq[c]++;
    }

    PriorityQueue<Character> maxHeap =
        new PriorityQueue<>((a, b) -> freq[b] - freq[a]);

    for (char c = 0; c < 128; c++) {
        if (freq[c] > 0) {
            maxHeap.offer(c);
        }
    }

    StringBuilder sb = new StringBuilder();

    while (!maxHeap.isEmpty()) {
        char c = maxHeap.poll();

        for (int i = 0; i < freq[c]; i++) {
            sb.append(c);
        }
    }

    return sb.toString();
}
```

## Dry Run

```text
s = "tree"

freq:
t -> 1
r -> 1
e -> 2

Max heap by frequency:
e -> 2
t -> 1
r -> 1

Build answer:
e -> "ee"
t -> "eet"
r -> "eert"

Answer = "eert"
```

## Complexity

- Time: \(O(n + k \log k)\), where `k` = distinct characters
- Space: \(O(k)\)

---

# 7. Example 3: Find All Anagrams in a String

## Problem

String `s` mein string `p` ke saare anagrams ke starting indices find karo.

```text
s = "cbaebabacd"
p = "abc"

Output:[0][6]
```

## Why Frequency Array + Sliding Window?

`p` ki length fixed hai.

To `s` mein bhi usi length ki window slide karni hai.

Har window ki frequency `p` ki frequency se compare karo.

## Solution

```java
public List<Integer> findAnagrams(String s, String p) {
    List<Integer> ans = new ArrayList<>();

    if (p.length() > s.length()) {
        return ans;
    }

    int[] pFreq = new int;
    int[] windowFreq = new int;

    for (char c : p.toCharArray()) {
        pFreq[c - 'a']++;
    }

    int windowSize = p.length();

    for (int i = 0; i < s.length(); i++) {
        windowFreq[s.charAt(i) - 'a']++;

        if (i >= windowSize) {
            windowFreq[s.charAt(i - windowSize) - 'a']--;
        }

        if (i >= windowSize - 1 && Arrays.equals(pFreq, windowFreq)) {
            ans.add(i - windowSize + 1);
        }
    }

    return ans;
}
```

## Dry Run

```text
s = "cbaebabacd"
p = "abc"

pFreq:
a -> 1
b -> 1
c -> 1

Window size = 3

Window 0: "cba"
windowFreq:
a -> 1
b -> 1
c -> 1

Match
Answer add 0

Window 1: "bae"
Not match

Window 2: "aeb"
Not match

Window 3: "eba"
Not match

Window 4: "bab"
Not match

Window 5: "aba"
Not match

Window 6: "bac"
Match
Answer add 6

Window 7: "acd"
Not match

Final Answer =[0][6]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(26)\)

---

# 8. Example 4: Longest Repeating Character Replacement

## Problem

String mein maximum `k` characters replace karke longest substring of same character banana hai.

```text
s = "AABABBA"
k = 1

Output: 4
Explanation: "AABA" -> "AAAA"
```

## Why Frequency Array?

Sirf uppercase English letters hain.

To `int[26]` frequency array use karenge.

## Condition

Window valid hai agar:

```text
windowLength - maxFrequency <= k
```

Matlab:

```text
Jo characters max frequency wale character se different hain,
unhe replace karna padega.
```

## Solution

```java
public int characterReplacement(String s, int k) {
    int[] freq = new int;
    int left = 0;
    int maxFreq = 0;
    int ans = 0;

    for (int right = 0; right < s.length(); right++) {
        char current = s.charAt(right);
        freq[current - 'A']++;

        maxFreq = Math.max(maxFreq, freq[current - 'A']);

        while ((right - left + 1) - maxFreq > k) {
            freq[s.charAt(left) - 'A']--;
            left++;
        }

        ans = Math.max(ans, right - left + 1);
    }

    return ans;
}
```

## Dry Run

```text
s = "AABABBA"
k = 1

right = 0:
char A
freq[A] = 1
window = "A"
ans = 1

right = 1:
char A
freq[A] = 2
window = "AA"
ans = 2

right = 2:
char B
freq[B] = 1
window = "AAB"
changes needed = 3 - 2 = 1
valid
ans = 3

right = 3:
char A
freq[A] = 3
window = "AABA"
changes needed = 4 - 3 = 1
valid
ans = 4

right = 4:
char B
freq[B] = 2
window = "AABAB"
changes needed = 5 - 3 = 2
invalid

shrink:
remove A
window = "ABAB"
changes needed = 4 - 3 = 1
valid

ans remains 4
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(26)\)

---

# 9. Example 5: Check If All Characters Have Equal Number of Occurrences

## Problem

Check karo ki string ke saare characters same number of times aaye hain ya nahi.

```text
s = "abacbc"
Output: true
Explanation:
a -> 2
b -> 2
c -> 2
```

```text
s = "aaabb"
Output: false
Explanation:
a -> 3
b -> 2
```

## Why Frequency Array?

Sirf lowercase letters hain.

To `int[26]` use karo.

## Solution

```java
public boolean areOccurrencesEqual(String s) {
    int[] freq = new int;

    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }

    int expected = 0;

    for (int count : freq) {
        if (count == 0) {
            continue;
        }

        if (expected == 0) {
            expected = count;
        } else if (count != expected) {
            return false;
        }
    }

    return true;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(26)\)

---

# 10. Example 6: Ransom Note

## Problem

Check karo ki `ransomNote` ban sakti hai ya nahi `magazine` ke letters se.

Har letter sirf ek baar use ho sakta hai.

```text
ransomNote = "a"
magazine = "b"
Output: false
```

```text
ransomNote = "aa"
magazine = "aab"
Output: true
```

## Why Frequency Array?

Sirf lowercase letters hain.

`magazine` ki frequency count karo, phir `ransomNote` ke letters consume karo.

## Solution

```java
public boolean canConstruct(String ransomNote, String magazine) {
    int[] freq = new int;

    for (char c : magazine.toCharArray()) {
        freq[c - 'a']++;
    }

    for (char c : ransomNote.toCharArray()) {
        freq[c - 'a']--;

        if (freq[c - 'a'] < 0) {
            return false;
        }
    }

    return true;
}
```

## Complexity

- Time: \(O(n + m)\)
- Space: \(O(26)\)

---

# 11. Frequency Array vs HashMap

| Feature | Frequency Array | HashMap |
|---|---|---|
| Best when | Values limited range mein hain | Values arbitrary hain |
| Space | \(O(range)\) | \(O(distinct elements)\) |
| Speed | Faster | Slightly slower |
| Code | Simple | Flexible |
| Example | `a-z` characters | Any strings/numbers |
| Memory risk | Agar range bahut bada ho | Usually safe |

### Rule

```text
a-z, A-Z, digits, ASCII -> Frequency Array
Arbitrary numbers/strings -> HashMap
```

---

# 12. Common Mistakes

## Mistake 1: Wrong Index Calculation

Lowercase letters:

```java
freq[c - 'a']++;
```

Uppercase letters:

```java
freq[c - 'A']++;
```

Digits:

```java
freq[c - '0']++;
```

Ye mix mat karo.

---

## Mistake 2: Array Size Galat Rakhna

Sirf lowercase letters:

```java
int[] freq = new int;
```

ASCII characters:

```java
int[] freq = new int;
```

Extended ASCII:

```java
int[] freq = new int;
```

---

## Mistake 3: Negative Frequency Check Na Karna

Anagram / Ransom Note mein:

```java
freq[c - 'a']--;

if (freq[c - 'a'] < 0) {
    return false;
}
```

Ye early exit deta hai.

---

## Mistake 4: Zero Frequency Characters Ko Consider Karna

Equal occurrence problem mein:

```java
if (count == 0) {
    continue;
}
```

Ye zaroori hai.

---

## Mistake 5: Large Range Par Frequency Array Use Karna

Agar values `0` se `10^9` tak ho sakti hain:

```java
int[] freq = new int[1000000001];
```

Ye memory fail kar dega.

Us case mein HashMap use karo.

---

# 13. Counting / Frequency Array Decision Flow

```text
Problem padho
      |
      v
Kya characters / digits / small range values hain?
      |
      +-- No --> HashMap use karo
      |
      +-- Yes
          |
          v
Kya frequency count karna hai?
      |
      +-- Yes --> Frequency Array
      |
      +-- Kya anagram / character comparison hai?
              |
              +-- Yes --> Frequency Array
              |
              +-- Kya sliding window frequency chahiye?
                      |
                      +-- Yes --> Frequency Array + Sliding Window
```

---

# 14. Important Problems

## Easy

- Valid Anagram
- Ransom Note
- Check If All Characters Have Equal Number of Occurrences
- Find the Difference
- First Unique Character in a String

## Medium

- Sort Characters by Frequency
- Find All Anagrams in a String
- Longest Repeating Character Replacement
- Permutation in String
- Task Scheduler
- Reorganize String

## Hard

- Minimum Window Substring
- Substring with Concatenation of All Words
- Sliding Window Median

---

# 15. One-Line Recognition

> **Agar input characters, digits, ya small fixed range ke numbers hain, aur frequency/count chahiye — Counting / Frequency Array use karo.**

Frequency Array ka main power ye hai ki HashMap ki jagah direct index-based counting use karke code simple, fast aur memory-efficient ban jata hai.