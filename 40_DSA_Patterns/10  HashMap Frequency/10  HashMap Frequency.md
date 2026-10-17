# 🚀 DSA Pattern 10 — HashMap Frequency

> **Goal:** Samajhna ki HashMap Frequency **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. HashMap Frequency Kya Hai?

HashMap Frequency ka matlab hai:

```text
Kisi element kitni baar aaya hai, uska count store karna.
```

Example:

```text
arr =[1][2][3]

Frequency:
1 -> 1
2 -> 2
3 -> 3
```

Java mein:

```java
Map<Integer, Integer> freq = new HashMap<>();

for (int num : arr) {
    freq.put(num, freq.getOrDefault(num, 0) + 1);
}
```

HashMap sirf frequency ke liye nahi hota. Hum store kar sakte hain:

```text
Element -> count
Element -> first index
Element -> last index
Element -> list of indices
Element -> related value
```

---

## 2. HashMap Frequency Kyun Use Karte Hain?

Main reason: **element lookup aur frequency count ko fast karna.**

Array mein kisi element ko dhundhne ke liye:

```text
Time: O(n)
```

HashMap mein:

```text
Time: O(1) average
```

Example:

```text
arr =[1][2][3]
target = 4
```

Hum check karna chahte hain:

```text
Kya 4 - arr[i] pehle aaya hai?
```

Brute force:

```text
Har element ke liye baaki array scan karo
Time: O(n^2)
```

HashMap:

```text
Element ko map mein store karo
Complement ko O(1) mein check karo
Time: O(n)
```

### Core Intuition

> Jo information baar-baar chahiye, use ek baar store kar lo.

---

## 3. Kaise Pata Chalega Ki HashMap Frequency Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible HashMap Frequency |
|---|---|
| Frequency / count chahiye | HashMap Frequency |
| Duplicate elements dhundhne hain | HashMap / HashSet |
| First non-repeating element | HashMap Frequency |
| Top K frequent elements | HashMap + Heap |
| Group anagrams | HashMap grouping |
| Pair with given sum | HashMap complement |
| Subarray sum equals K | Prefix Sum + HashMap |
| Character count | HashMap / frequency array |
| Longest consecutive sequence | HashSet |
| Element ka first/last index chahiye | HashMap index storage |

### Common Keywords

- Frequency
- Count
- Duplicate
- Unique
- First non-repeating
- Top K
- Group
- Anagram
- Pair
- Complement

### Mental Checklist

1. Kya elements ki frequency chahiye?  
2. Kya duplicates identify karne hain?  
3. Kya kisi element ka pehla/last occurrence chahiye?  
4. Kya complement value dhundhni hai?  
5. Kya elements ko group karna hai?  
6. Kya \(O(n)\) time solution chahiye?  

Agar 2–3 answers **yes** hain, to HashMap Frequency try karo.

---

# 4. HashMap Frequency Templates

## Template 1: Count Frequency

```java
Map<Integer, Integer> freq = new HashMap<>();

for (int num : arr) {
    freq.put(num, freq.getOrDefault(num, 0) + 1);
}
```

### Character Frequency

```java
Map<Character, Integer> freq = new HashMap<>();

for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

---

## Template 2: Check Complement

Pair with sum `target` ke liye:

```java
Map<Integer, Integer> map = new HashMap<>();

for (int num : arr) {
    int complement = target - num;

    if (map.containsKey(complement)) {
        return new int[]{map.get(complement), currentIndex};
    }

    map.put(num, currentIndex);
}
```

---

## Template 3: First Non-Repeating Element

```java
Map<Character, Integer> freq = new HashMap<>();

for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}

for (int i = 0; i < s.length(); i++) {
    if (freq.get(s.charAt(i)) == 1) {
        return i;
    }
}

return -1;
```

---

## Template 4: Group Elements

```java
Map<String, List<String>> groups = new HashMap<>();

for (String word : words) {
    String key = buildKey(word);

    groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
}
```

---

# 5. Example 1: Two Sum

## Problem

Array aur target diya hai. Do indices return karo jinke elements ka sum target ho.

```text
nums =[2][7][11][15]
target = 9

Output:[0][1]
```

## Brute Force

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (nums[i] + nums[j] == target) {
            return new int[]{i, j};
        }
    }
}
```

Time: \(O(n^2)\)

## Why HashMap?

Har element ke liye humein ye chahiye:

```text
target - currentElement
```

Agar complement pehle aaya hai, to answer mil gaya.

## Solution

```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];

        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }

        map.put(nums[i], i);
    }

    return new int[]{-1, -1};
}
```

## Dry Run

```text
nums =[2][7][11][15]
target = 9

i = 0:
num = 2
complement = 7
map does not contain 7
map = {2: 0}

i = 1:
num = 7
complement = 2
map contains 2
return[0][1]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 6. Example 2: Top K Frequent Elements

## Problem

Array mein `k` sabse frequently occurring elements return karo.

```text
nums =[1][2][3]
k = 2

Output:[1][2]
```

## Why HashMap + Heap?

Step 1: Frequency count karo.

```text
1 -> 3
2 -> 2
3 -> 1
```

Step 2: Top `k` frequency wale elements chahiye.

Iske liye min-heap use karte hain.

Heap mein sirf `k` elements rakhte hain.

## Solution

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();

    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    PriorityQueue<int[]> minHeap =
        new PriorityQueue<>((a, b) -> a - b);

    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
        minHeap.offer(new int[]{entry.getKey(), entry.getValue()});

        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }

    int[] ans = new int[k];

    for (int i = k - 1; i >= 0; i--) {
        ans[i] = minHeap.poll();
    }

    return ans;
}
```

## Why Min-Heap?

Hum top `k` maximum frequency elements chahiye.

Min-heap mein sabse kam frequency wala element top par hota hai.

Agar heap size `k + 1` ho jaye:

```text
Sabse kam frequency wala element remove karo
```

## Dry Run

```text
nums =[1][2][3]
k = 2

freq:
1 -> 3
2 -> 2
3 -> 1

Heap process:
[1][3]
,[2][3][1]
,,[1][2][3]

Heap size > 2:
remove[3][1]

Final heap:
,[1][2][3]

Answer =[2][1]
```

## Complexity

- Frequency count: \(O(n)\)
- Heap operations: \(O(n \log k)\)
- Total time: \(O(n \log k)\)
- Space: \(O(n)\)

---

# 7. Example 3: First Unique Character in a String

## Problem

String mein pehla non-repeating character dhundo aur uska index return karo.

```text
s = "leetcode"
Output: 0
Explanation: 'l' is the first unique character
```

```text
s = "aabb"
Output: -1
```

## Why HashMap Frequency?

Pehle saare characters ki frequency count karo.

Phir left se traverse karke pehla character dhundo jiski frequency `1` hai.

## Solution

```java
public int firstUniqChar(String s) {
    Map<Character, Integer> freq = new HashMap<>();

    for (char c : s.toCharArray()) {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    }

    for (int i = 0; i < s.length(); i++) {
        if (freq.get(s.charAt(i)) == 1) {
            return i;
        }
    }

    return -1;
}
```

## Dry Run

```text
s = "leetcode"

freq:
l -> 1
e -> 3
t -> 1
c -> 1
o -> 1
d -> 1

Traverse:
index 0 -> l -> frequency 1
return 0
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(k)\), where `k` = number of distinct characters

---

# 8. Example 4: Valid Anagram

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

## Why HashMap Frequency?

Anagram ka matlab:

```text
Dono strings mein har character ki frequency same honi chahiye.
```

## Solution

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }

    Map<Character, Integer> freq = new HashMap<>();

    for (char c : s.toCharArray()) {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    }

    for (char c : t.toCharArray()) {
        if (!freq.containsKey(c)) {
            return false;
        }

        freq.put(c, freq.get(c) - 1);

        if (freq.get(c) == 0) {
            freq.remove(c);
        }
    }

    return freq.isEmpty();
}
```

## Dry Run

```text
s = "anagram"
t = "nagaram"

Frequency of s:
a -> 3
n -> 1
g -> 1
r -> 1
m -> 1

Traverse t:
n -> remove
a -> remove
g -> remove
a -> remove
r -> remove
a -> remove
m -> remove

Map empty -> true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(k)\)

---

# 9. Example 5: Group Anagrams

## Problem

Strings ko anagrams ke according group karo.

```text
strs = ["eat", "tea", "tan", "ate", "nat", "bat"]

Output:
[
  ["eat", "tea", "ate"],
  ["tan", "nat"],
  ["bat"]
]
```

## Why HashMap Grouping?

Anagram strings ka sorted form same hota hai.

```text
eat -> aet
tea -> aet
ate -> aet
```

To sorted string ko key bana lo.

## Solution

```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groups = new HashMap<>();

    for (String word : strs) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);

        String key = new String(chars);

        groups.computeIfAbsent(key, x -> new ArrayList<>()).add(word);
    }

    return new ArrayList<>(groups.values());
}
```

## Dry Run

```text
strs = ["eat", "tea", "tan", "ate", "nat", "bat"]

eat -> sorted "aet"
tea -> sorted "aet"
tan -> sorted "ant"
ate -> sorted "aet"
nat -> sorted "ant"
bat -> sorted "abt"

Groups:
"aet" -> ["eat", "tea", "ate"]
"ant" -> ["tan", "nat"]
"abt" -> ["bat"]
```

## Complexity

Let `k` = maximum length of a string.

- Sorting each string: \(O(k \log k)\)
- Total time: \(O(n \times k \log k)\)
- Space: \(O(n \times k)\)

---

# 10. Example 6: Longest Consecutive Sequence

## Problem

Unsorted array mein longest consecutive sequence ki length nikalo.

```text
nums =[1][2][3][4][100][200]
Output: 4
Explanation:[2][3][4][1]
```

## Why HashSet?

Humein sirf ye check karna hai:

```text
Kya num + 1 exist karta hai?
```

HashSet \(O(1)\) average lookup deta hai.

Important optimization:

Sirf sequence ke **start** se counting start karo.

```text
Agar num - 1 exist karta hai,
to num sequence ka start nahi hai.
```

## Solution

```java
public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();

    for (int num : nums) {
        set.add(num);
    }

    int longest = 0;

    for (int num : set) {
        if (!set.contains(num - 1)) {
            int current = num;
            int length = 1;

            while (set.contains(current + 1)) {
                current++;
                length++;
            }

            longest = Math.max(longest, length);
        }
    }

    return longest;
}
```

## Dry Run

```text
nums =[1][2][3][4][100][200]

set = {1, 2, 3, 4, 100, 200}

num = 1:
1 - 1 = 0 not present
Start sequence

1 -> 2 -> 3 -> 4
length = 4

num = 100:
99 not present
100 -> no 101
length = 1

num = 200:
199 not present
200 -> no 201
length = 1

Answer = 4
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 11. HashMap vs HashSet vs Frequency Array

| Feature | HashMap | HashSet | Frequency Array |
|---|---|---|---|
| Stores | Key-value pairs | Only keys | Counts by index |
| Frequency count | Yes | No | Yes |
| Membership check | Yes | Yes | Yes |
| Extra data store | Yes | No | Limited |
| Best for | Frequency, index, grouping | Duplicates, visited | Small fixed charset |
| Example | Two Sum | Longest Consecutive Sequence | Valid Anagram |

### Rule

```text
Frequency + extra info -> HashMap
Only presence / duplicate -> HashSet
Characters a-z / small range -> Frequency Array
```

---

# 12. Common Mistakes

## Mistake 1: `getOrDefault` Bhool Jana

Galat:

```java
freq.put(num, freq.get(num) + 1);
```

Agar `num` pehli baar aaya hai, to `freq.get(num)` `null` hoga.

Correct:

```java
freq.put(num, freq.getOrDefault(num, 0) + 1);
```

---

## Mistake 2: Two Sum Mein Pehle Element Insert Kar Dena

Galat:

```java
map.put(nums[i], i);

if (map.containsKey(target - nums[i])) {
    // same element use ho sakta hai
}
```

Correct order:

```java
if (map.containsKey(complement)) {
    return answer;
}

map.put(nums[i], i);
```

---

## Mistake 3: Frequency Zero Hone Par Entry Remove Na Karna

Kuch problems mein `map.size()` important hota hai.

```java
freq.put(c, freq.get(c) - 1);

if (freq.get(c) == 0) {
    freq.remove(c);
}
```

---

## Mistake 4: Anagram Key Galat Banana

Group Anagrams mein sorted string best key hai.

```java
char[] chars = word.toCharArray();
Arrays.sort(chars);
String key = new String(chars);
```

---

## Mistake 5: Longest Consecutive Sequence Mein Har Number Se Start Karna

Galat:

```java
for (int num : set) {
    // har number se sequence count
}
```

Isse \(O(n^2)\) ho jayega.

Correct:

```java
if (!set.contains(num - 1)) {
    // only sequence start
}
```

---

# 13. HashMap Frequency Decision Flow

```text
Problem padho
      |
      v
Kya frequency / duplicate / grouping chahiye?
      |
      +-- No --> HashMap Frequency probably nahi
      |
      +-- Yes
          |
          v
Kya sirf presence check karni hai?
      |
      +-- Yes --> HashSet
      |
      +-- Kya frequency chahiye?
              |
              +-- Yes --> HashMap Frequency
              |
              +-- Kya complement / pair chahiye?
                      |
                      +-- Yes --> HashMap Complement
                      |
                      +-- Kya group karna hai?
                              |
                              +-- Yes --> HashMap Grouping
```

---

# 14. Important Problems

## Easy

- Two Sum
- Valid Anagram
- Contains Duplicate
- First Unique Character in a String
- Intersection of Two Arrays

## Medium

- Top K Frequent Elements
- Group Anagrams
- Longest Consecutive Sequence
- Subarray Sum Equals K
- Sort Characters by Frequency
- Find All Anagrams in a String

## Hard

- Longest Consecutive Sequence
- Substring with Concatenation of All Words
- Minimum Window Substring

---

# 15. One-Line Recognition

> **Agar problem frequency, duplicates, grouping, complement pair, ya first/last occurrence maang rahi hai — HashMap Frequency try karo.**

HashMap Frequency ka main power ye hai ki repeated lookup aur counting ko \(O(n)\) se \(O(1)\) average time mein convert kar deta hai.