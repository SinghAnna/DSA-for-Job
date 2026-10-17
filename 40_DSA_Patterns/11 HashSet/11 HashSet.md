# 🚀 DSA Pattern 11 — HashSet

> **Goal:** Samajhna ki HashSet **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. HashSet Kya Hai?

HashSet ek data structure hai jo **unique elements** store karta hai.

Isme hum sirf ye track karte hain:

```text
Element exist karta hai ya nahi?
```

Example:

```text
Input:[1][2][3]

HashSet:
{1, 2, 3}
```

Java mein:

```java
Set<Integer> set = new HashSet<>();

set.add(1);
set.add(2);
set.add(2);

System.out.println(set); //[1][2]
```

HashSet duplicate values store nahi karta.

---

## 2. HashSet Kyun Use Karte Hain?

Main reason: **fast membership check aur duplicate detection.**

Array mein check karna:

```text
Kya 5 array mein exist karta hai?
```

Linear search:

```text
Time: O(n)
```

HashSet:

```text
Time: O(1) average
```

### Core Operations

| Operation | Method | Average Time |
|---|---|---|
| Add element | `add(x)` | \(O(1)\) |
| Check existence | `contains(x)` | \(O(1)\) |
| Remove element | `remove(x)` | \(O(1)\) |
| Size | `size()` | \(O(1)\) |

### Core Intuition

> Agar humein baar-baar poochna hai ki koi value pehle aayi hai ya nahi, to HashSet use karo.

---

## 3. Kaise Pata Chalega Ki HashSet Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible HashSet Use |
|---|---|
| Duplicate check karna hai | HashSet |
| Element exist karta hai ya nahi | HashSet |
| Visited nodes/elements track karne hain | HashSet |
| Intersection of arrays | HashSet |
| Longest consecutive sequence | HashSet |
| Unique elements chahiye | HashSet |
| Cycle detection in values/nodes | HashSet |
| Seen values track karna hai | HashSet |

### Common Keywords

- Duplicate
- Unique
- Contains
- Exists
- Visited
- Seen
- Intersection
- Consecutive
- Repeated

### Mental Checklist

1. Kya duplicates detect karne hain?  
2. Kya kisi value ka existence check karna hai?  
3. Kya visited/seen elements track karne hain?  
4. Kya order ki zaroorat nahi hai?  
5. Kya frequency ki zaroorat nahi hai?  

Agar 2–3 answers **yes** hain, to HashSet try karo.

---

# 4. HashSet Templates

## Template 1: Duplicate Detection

```java
Set<Integer> seen = new HashSet<>();

for (int num : nums) {
    if (seen.contains(num)) {
        return true;
    }

    seen.add(num);
}

return false;
```

---

## Template 2: Unique Elements

```java
Set<Integer> unique = new HashSet<>();

for (int num : nums) {
    unique.add(num);
}
```

---

## Template 3: Intersection

```java
Set<Integer> set1 = new HashSet<>();

for (int num : nums1) {
    set1.add(num);
}

Set<Integer> result = new HashSet<>();

for (int num : nums2) {
    if (set1.contains(num)) {
        result.add(num);
    }
}
```

---

## Template 4: Visited Tracking

```java
Set<Integer> visited = new HashSet<>();

if (visited.contains(current)) {
    return;
}

visited.add(current);
```

---

# 5. Example 1: Contains Duplicate

## Problem

Array mein koi value at least twice aayi hai ya nahi, check karo.

```text
nums =[1][2][3]
Output: true
```

```text
nums =[1][2][3][4]
Output: false
```

## Brute Force

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (nums[i] == nums[j]) {
            return true;
        }
    }
}
```

Time: \(O(n^2)\)

## Why HashSet?

Har element ko set mein daalte jao.

Agar element pehle se present hai, to duplicate mil gaya.

## Solution

```java
public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();

    for (int num : nums) {
        if (seen.contains(num)) {
            return true;
        }

        seen.add(num);
    }

    return false;
}
```

## Dry Run

```text
nums =[1][2][3]

num = 1:
not in set
set = {1}

num = 2:
not in set
set = {1, 2}

num = 3:
not in set
set = {1, 2, 3}

num = 1:
already in set
return true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 6. Example 2: Longest Consecutive Sequence

## Problem

Unsorted array mein longest consecutive sequence ki length nikalo.

```text
nums =[1][2][3][4][100][200]
Output: 4
Explanation:[1][2][3][4]
```

## Why HashSet?

Humein baar-baar check karna hai:

```text
Kya num + 1 exist karta hai?
Kya num - 1 exist karta hai?
```

HashSet \(O(1)\) average lookup deta hai.

## Key Optimization

Sirf sequence ke **start** se counting start karo.

```text
Agar num - 1 set mein hai,
to num sequence ka start nahi hai.
```

Example:

```text
nums =[1][2][3][4]

num = 2:
1 exists
So 2 is not sequence start

num = 1:
0 does not exist
So 1 is sequence start
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
0 not in set
Sequence start

1 -> 2 -> 3 -> 4
length = 4
longest = 4

num = 2:
1 exists
Skip

num = 3:
2 exists
Skip

num = 4:
3 exists
Skip

num = 100:
99 not in set
100 -> 101 not present
length = 1

num = 200:
199 not in set
200 -> 201 not present
length = 1

Answer = 4
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 7. Example 3: Intersection of Two Arrays

## Problem

Do arrays ka intersection return karo. Result mein unique elements hone chahiye.

```text
nums1 =[1][2]
nums2 =[2]

Output: 
```

## Why HashSet?

Humein sirf ye check karna hai:

```text
Kya nums2 ka element nums1 mein exist karta hai?
```

Aur result unique hona chahiye.

## Solution

```java
public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set1 = new HashSet<>();

    for (int num : nums1) {
        set1.add(num);
    }

    Set<Integer> resultSet = new HashSet<>();

    for (int num : nums2) {
        if (set1.contains(num)) {
            resultSet.add(num);
        }
    }

    int[] ans = new int[resultSet.size()];
    int index = 0;

    for (int num : resultSet) {
        ans[index++] = num;
    }

    return ans;
}
```

## Dry Run

```text
nums1 =[1][2]
nums2 =[2]

set1 = {1, 2}

num = 2:
set1 contains 2
resultSet = {2}

num = 2:
already in resultSet

Answer = 
```

## Complexity

- Time: \(O(n + m)\)
- Space: \(O(n)\)

---

# 8. Example 4: Happy Number

## Problem

Ek number ko repeatedly replace karo uske digits ke squares ke sum se.

Agar process `1` par pahunch jaye, to number happy hai.

Warna cycle mein phas jayega.

```text
n = 19

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

Output: true
```

## Why HashSet?

Agar same number dobara aaye, to cycle ban gaya.

Isliye visited numbers ko HashSet mein store karo.

## Solution

```java
public boolean isHappy(int n) {
    Set<Integer> seen = new HashSet<>();

    while (n != 1 && !seen.contains(n)) {
        seen.add(n);
        n = getNext(n);
    }

    return n == 1;
}

private int getNext(int n) {
    int sum = 0;

    while (n > 0) {
        int digit = n % 10;
        sum += digit * digit;
        n /= 10;
    }

    return sum;
}
```

## Dry Run

```text
n = 19

seen = {}

19 not seen
seen = {19}
n = 82

82 not seen
seen = {19, 82}
n = 68

68 not seen
seen = {19, 82, 68}
n = 100

100 not seen
seen = {19, 82, 68, 100}
n = 1

n == 1
return true
```

## Complexity

- Time: \(O(\log n)\) per step, with limited cycle length
- Space: \(O(\log n)\)

---

# 9. Example 5: Linked List Cycle

## Problem

Linked list mein cycle hai ya nahi, check karo.

## Why HashSet?

Har visited node ko HashSet mein store karo.

Agar koi node dobara mile, to cycle hai.

## Solution

```java
public boolean hasCycle(ListNode head) {
    Set<ListNode> visited = new HashSet<>();

    ListNode current = head;

    while (current != null) {
        if (visited.contains(current)) {
            return true;
        }

        visited.add(current);
        current = current.next;
    }

    return false;
}
```

## Dry Run

```text
1 -> 2 -> 3 -> 2

visited = {}

Node 1:
not visited
visited = {1}

Node 2:
not visited
visited = {1, 2}

Node 3:
not visited
visited = {1, 2, 3}

Node 2:
already visited
return true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

### Better Alternative

Linked list cycle ke liye Fast & Slow Pointers \(O(1)\) space solution deta hai.

But HashSet approach simple aur intuitive hai.

---

# 10. Example 6: Unique Email Addresses

## Problem

Email addresses normalize karke unique emails count karo.

Rules:

- Local name mein `.` ignore hota hai
- `+` ke baad local name ignore hota hai
- Domain same rehta hai

```text
emails = [
  "test.email+alex@leetcode.com",
  "test.e.mail+bob.cathy@leetcode.com",
  "testemail+david@lee.tcode.com"
]

Output: 2
```

## Why HashSet?

Humein sirf unique normalized emails count karne hain.

## Solution

```java
public int numUniqueEmails(String[] emails) {
    Set<String> uniqueEmails = new HashSet<>();

    for (String email : emails) {
        String[] parts = email.split("@");

        String local = parts;
        String domain = parts;

        local = local.split("\\+").replace(".", "");

        uniqueEmails.add(local + "@" + domain);
    }

    return uniqueEmails.size();
}
```

## Complexity

- Time: \(O(n \times m)\), where `m` = average email length
- Space: \(O(n \times m)\)

---

# 11. HashSet vs HashMap

| Feature | HashSet | HashMap |
|---|---|---|
| Stores | Only values | Key-value pairs |
| Duplicate | Not allowed | Duplicate keys not allowed |
| Frequency count | No | Yes |
| Membership check | Yes | Yes |
| Extra information | No | Yes |
| Best for | Seen/visited/unique | Frequency, index, grouping |
| Example | Contains Duplicate | Two Sum |

### Simple Rule

```text
Sirf "kya ye element aaya hai?" -> HashSet
"Kitni baar aaya?" ya "kahan aaya?" -> HashMap
```

---

# 12. Common Mistakes

## Mistake 1: HashSet Mein Duplicate Add Karne Ki Expectation

```java
set.add(2);
set.add(2);
```

Set size `1` rahega, `2` nahi.

---

## Mistake 2: Frequency Chahiye Aur HashSet Use Karna

Agar frequency chahiye:

```text
HashMap use karo
```

HashSet sirf presence track karta hai.

---

## Mistake 3: Longest Consecutive Sequence Mein Har Number Se Start Karna

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
    // sequence start
}
```

---

## Mistake 4: Linked List Mein Value Ki Jagah Node Store Na Karna

Cycle detection mein node object store karo:

```java
Set<ListNode> visited = new HashSet<>();
```

Sirf node value store karna galat ho sakta hai, kyunki same value wale different nodes ho sakte hain.

---

## Mistake 5: Order Chahiye Aur HashSet Use Karna

HashSet order maintain nahi karta.

Agar insertion order chahiye:

```java
Set<Integer> set = new LinkedHashSet<>();
```

Agar sorted order chahiye:

```java
Set<Integer> set = new TreeSet<>();
```

---

# 13. HashSet Decision Flow

```text
Problem padho
      |
      v
Kya duplicates / presence / visited check karna hai?
      |
      +-- No --> HashSet probably nahi
      |
      +-- Yes
          |
          v
Kya frequency bhi chahiye?
      |
      +-- Yes --> HashMap
      |
      +-- Kya order chahiye?
              |
              +-- Insertion order -> LinkedHashSet
              |
              +-- Sorted order -> TreeSet
              |
              +-- No order needed -> HashSet
```

---

# 14. Important Problems

## Easy

- Contains Duplicate
- Intersection of Two Arrays
- Happy Number
- Unique Email Addresses
- Two Sum

## Medium

- Longest Consecutive Sequence
- Longest Substring Without Repeating Characters
- Linked List Cycle
- Insert Delete GetRandom O(1)
- Repeated DNA Sequences

## Hard

- Longest Consecutive Sequence
- Word Ladder
- Minimum Window Substring

---

# 15. One-Line Recognition

> **Agar problem duplicates, presence, visited elements, ya unique values track karne ki baat kar rahi hai — HashSet try karo.**

HashSet ka main power ye hai ki membership check ko \(O(n)\) linear search se \(O(1)\) average time mein convert kar deta hai.