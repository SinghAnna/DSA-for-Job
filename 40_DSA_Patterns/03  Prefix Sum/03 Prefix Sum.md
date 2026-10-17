# 🚀 DSA Pattern 03 — Prefix Sum

> **Goal:** Samajhna ki Prefix Sum **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Prefix Sum Kya Hai?

Prefix Sum ka matlab hai: index `i` tak ke saare elements ka cumulative sum.

```text
arr =[1][2][3][4][5]

prefix = 1
prefix = 1 + 2 = 3
prefix = 1 + 2 + 3 = 6
prefix = 1 + 2 + 3 + 4 = 10
prefix = 1 + 2 + 3 + 4 + 5 = 15
```

Usually hum ek extra size `n + 1` array banate hain:

```text
prefix =[0][1][3][6][10][15]
```

Yahan:

```text
prefix[i] = sum of arr to arr[i - 1]
```

Isliye:

```text
prefix = 0
prefix = arr
prefix = arr + arr
prefix = arr + arr + arr
```

---

## 2. Prefix Sum Kyun Use Karte Hain?

Main reason: **range sum ko \(O(1)\) mein nikalna.**

Agar humein baar-baar poocha jaye:

```text
arr se arr tak ka sum kya hai?
```

Brute force:

```java
int sum = 0;

for (int i = left; i <= right; i++) {
    sum += arr[i];
}
```

Time per query: \(O(n)\)

But prefix sum ke saath:

```text
sum(left, right) = prefix[right + 1] - prefix[left]
```

Time per query: \(O(1)\)

### Example

```text
arr =[1][2][3][4][5]
prefix =[0][1][3][6][10][15]
```

Sum from index `1` to `3`:

```text
arr + arr + arr
= 2 + 3 + 4
= 9
```

Using prefix:

```text
prefix - prefix
= 10 - 1
= 9
```

### Core Intuition

> Pehle `right` tak ka total sum lo, phir usme se `left` se pehle tak ka sum minus kar do.

---

## 3. Kaise Pata Chalega Ki Prefix Sum Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Prefix Sum Use |
|---|---|
| **Range sum** queries | Direct prefix sum |
| **Subarray sum equals K** | Prefix sum + HashMap |
| **Count subarrays with condition** | Prefix frequency |
| **Product of array except self** | Prefix product + suffix product |
| **Equal number of 0s and 1s** | Prefix balance |
| **Divisible by K subarrays** | Prefix modulo |
| **Range update** | Difference array |
| **Prefix maximum/minimum** | Prefix variant |

### Mental Checklist

1. Kya problem **subarray** ya **range** ke baare mein hai?  
2. Kya same range ka sum/value baar-baar calculate karna pad raha hai?  
3. Kya condition prefix value se express ho sakti hai?  
4. Kya `sum(i, j)` ko `prefix[j] - prefix[i]` jaise formula mein likh sakte hain?  
5. Kya subarray count chahiye?  

Agar 2–3 answers **yes** hain, to Prefix Sum try karo.

---

# 4. Types of Prefix Sum

## Type 1: Basic Prefix Sum

Range sum queries ke liye.

```text
prefix[i] = prefix[i - 1] + arr[i - 1]
```

### Template

```java
public int[] buildPrefixSum(int[] arr) {
    int n = arr.length;
    int[] prefix = new int[n + 1];

    for (int i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] + arr[i];
    }

    return prefix;
}

public int rangeSum(int[] prefix, int left, int right) {
    return prefix[right + 1] - prefix[left];
}
```

---

## Type 2: Prefix Sum + HashMap

Subarray sum equals K jaise problems ke liye.

```text
prefix[j] - prefix[i] = K
```

To:

```text
prefix[i] = prefix[j] - K
```

Matlab current prefix se `K` minus karke jo value mile, wo pehle aayi honi chahiye.

### Template

```java
Map<Integer, Integer> map = new HashMap<>();
map.put(0, 1);

int prefixSum = 0;
int count = 0;

for (int num : arr) {
    prefixSum += num;

    count += map.getOrDefault(prefixSum - k, 0);

    map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
}
```

---

## Type 3: Prefix Product

Product of Array Except Self jaise problems mein.

```text
prefixProduct[i] = product of elements before i
suffixProduct[i] = product of elements after i
```

### Template

```java
int[] prefix = new int[n];
int[] suffix = new int[n];

prefix = 1;

for (int i = 1; i < n; i++) {
    prefix[i] = prefix[i - 1] * arr[i - 1];
}

suffix[n - 1] = 1;

for (int i = n - 2; i >= 0; i--) {
    suffix[i] = suffix[i + 1] * arr[i + 1];
}

for (int i = 0; i < n; i++) {
    result[i] = prefix[i] * suffix[i];
}
```

---

## Type 4: Prefix Count / Balance

Jab numbers ko kisi balanced value mein convert karna ho.

Example: equal number of `0`s and `1`s.

```text
0 -> -1
1 -> +1
```

Ab problem ban jati hai:

```text
Longest subarray with sum = 0
```

---

# 5. Example 1: Range Sum Query — Immutable

## Problem

Array diya hai. Multiple queries mein `left` se `right` tak ka sum nikalna hai.

```text
nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
```

## Brute Force

Har query par loop chalao.

```java
public int sumRange(int[] nums, int left, int right) {
    int sum = 0;

    for (int i = left; i <= right; i++) {
        sum += nums[i];
    }

    return sum;
}
```

Time per query: \(O(n)\)

## Why Prefix Sum?

Queries multiple hain. Isliye ek baar prefix array bana lo, phir har query \(O(1)\) mein answer karo.

## Solution

```java
class NumArray {
    private int[] prefix;

    public NumArray(int[] nums) {
        int n = nums.length;
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}
```

## Dry Run

```text
nums = [-2, 0, 3, -5, 2, -1]

prefix = 0
prefix = -2
prefix = -2
prefix = 1
prefix = -4
prefix = -2
prefix = -3
```

Query: `sumRange(2, 5)`

```text
prefix - prefix
= -3 - (-2)
= -1
```

## Complexity

- Build prefix: \(O(n)\)
- Each query: \(O(1)\)
- Space: \(O(n)\)

---

# 6. Example 2: Subarray Sum Equals K

## Problem

Array aur integer `k` diya hai. Kitne contiguous subarrays ka sum exactly `k` hai, ye count karo.

```text
nums =[1]
k = 2

Output: 2
Explanation:  at index 0-1 and 1-2[1]
```

## Brute Force

```java
public int subarraySumBruteForce(int[] nums, int k) {
    int count = 0;

    for (int start = 0; start < nums.length; start++) {
        int sum = 0;

        for (int end = start; end < nums.length; end++) {
            sum += nums[end];

            if (sum == k) {
                count++;
            }
        }
    }

    return count;
}
```

Time: \(O(n^2)\)

## Why Prefix Sum + HashMap?

Current prefix sum ko `currSum` bolo.

Kisi purane prefix sum ko `oldSum` bolo.

Subarray sum:

```text
currSum - oldSum = k
```

To:

```text
oldSum = currSum - k
```

Agar `currSum - k` pehle aaya hai, to utne subarrays banenge.

### Why HashMap?

Kyunki humein ye count karna hai ki `currSum - k` value kitni baar pehle aayi.

## Solution

```java
public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int prefixSum = 0;
    int count = 0;

    for (int num : nums) {
        prefixSum += num;

        count += map.getOrDefault(prefixSum - k, 0);

        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
    }

    return count;
}
```

## Why `map.put(0, 1)`?

Ye starting se hi banne wale valid subarrays handle karta hai.

Example:

```text
nums =[1][2][3]
k = 3
```

Index `1` par:

```text
prefixSum = 3
prefixSum - k = 0
```

`0` pehle aaya tha, isliye subarray `[1, 2]` count hoga.

## Dry Run

```text
nums =[1]
k = 2

Initial:
map = {0: 1}
prefixSum = 0
count = 0

num = 1:
prefixSum = 1
need = 1 - 2 = -1
map has -1? No
count = 0
map = {0: 1, 1: 1}

num = 1:
prefixSum = 2
need = 2 - 2 = 0
map has 0? Yes, frequency = 1
count = 1
map = {0: 1, 1: 1, 2: 1}

num = 1:
prefixSum = 3
need = 3 - 2 = 1
map has 1? Yes, frequency = 1
count = 2
map = {0: 1, 1: 1, 2: 1, 3: 1}

Final Answer = 2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 7. Example 3: Product of Array Except Self

## Problem

Har index ke liye answer banao, jisme us index ke element ko chhodkar baaki sabka product ho.

```text
nums =[1][2][3][4]
Output =[6][8][12][24]
```

## Why Prefix Product?

Index `i` ka answer:

```text
(left side product) * (right side product)
```

Example:

```text
nums =[1][2][3][4]

index 2:
left product = 1 * 2 = 2
right product = 4
answer = 2 * 4 = 8
```

## Solution

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];

    result = 1;

    for (int i = 1; i < n; i++) {
        result[i] = result[i - 1] * nums[i - 1];
    }

    int rightProduct = 1;

    for (int i = n - 1; i >= 0; i--) {
        result[i] *= rightProduct;
        rightProduct *= nums[i];
    }

    return result;
}
```

## Dry Run

```text
nums =[1][2][3][4]

After left pass:
result =[1][2][6]

rightProduct = 1

i = 3:
result = 6 * 1 = 6
rightProduct = 4

i = 2:
result = 2 * 4 = 8
rightProduct = 12

i = 1:
result = 1 * 12 = 12
rightProduct = 24

i = 0:
result = 1 * 24 = 24
rightProduct = 24

Final:
result =[6][8][12][24]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\), excluding output array

---

# 8. Example 4: Contiguous Array

## Problem

Binary array mein longest contiguous subarray nikalo jisme equal number of `0`s and `1`s ho.

```text
nums =[0][1]
Output: 6
```

## Why Prefix Sum?

`0` ko `-1` maan lo aur `1` ko `+1`.

```text
nums      =[0][1]
converted = [-1, 1, -1, 1, 1, -1]
```

Ab problem ban gayi:

> Longest subarray with sum = 0

Agar do same prefix sums dikhe:

```text
prefix[j] == prefix[i]
```

To beech ka subarray sum zero hoga.

## Solution

```java
public int findMaxLength(int[] nums) {
    Map<Integer, Integer> firstIndex = new HashMap<>();
    firstIndex.put(0, -1);

    int prefixSum = 0;
    int ans = 0;

    for (int i = 0; i < nums.length; i++) {
        prefixSum += nums[i] == 1 ? 1 : -1;

        if (firstIndex.containsKey(prefixSum)) {
            ans = Math.max(ans, i - firstIndex.get(prefixSum));
        } else {
            firstIndex.put(prefixSum, i);
        }
    }

    return ans;
}
```

## Why Store First Index?

Longest subarray chahiye.

Isliye jab koi prefix sum pehli baar aaye, uska index store karo.

Agar wahi prefix sum dobara aaye, to:

```text
current index - first index
```

Longest length dega.

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 9. Example 5: Subarray Sums Divisible by K

## Problem

Kitne subarrays ka sum `k` se divisible hai, ye count karo.

```text
nums = [4, 5, 0, -2, -3, 1]
k = 5

Output: 7
```

## Why Prefix Mod?

Agar do prefix sums ka modulo same ho:

```text
prefix[i] % k == prefix[j] % k
```

To unke beech ka subarray `k` se divisible hoga.

```text
prefix[j] - prefix[i] divisible by k
```

## Solution

```java
public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int prefixSum = 0;
    int count = 0;

    for (int num : nums) {
        prefixSum += num;

        int mod = ((prefixSum % k) + k) % k;

        count += map.getOrDefault(mod, 0);

        map.put(mod, map.getOrDefault(mod, 0) + 1);
    }

    return count;
}
```

## Why `((prefixSum % k) + k) % k`?

Java mein negative number ka modulo negative ho sakta hai.

Example:

```text
-1 % 5 = -1
```

But humein modulo `0` se `k - 1` range mein chahiye.

```text
((-1 % 5) + 5) % 5
= (-1 + 5) % 5
= 4
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(k)\)

---

# 10. Example 6: Range Addition / Difference Array

## Problem

Multiple range updates karo. Har update mein `[left, right]` range ke elements mein `value` add karo.

```text
length = 5
updates = [][1][2][3]

Output after update:
[0][2]
```

## Why Difference Array?

Agar har update mein `left` se `right` tak loop chalayenge:

```text
Time per update = O(n)
```

But difference array mein sirf endpoints update karte hain:

```text
diff[left] += value
diff[right + 1] -= value
```

Phir end mein prefix sum lete hain.

## Solution

```java
public int[] getModifiedArray(int length, int[][] updates) {
    int[] diff = new int[length + 1];

    for (int[] update : updates) {
        int left = update;
        int right = update;
        int value = update;

        diff[left] += value;
        diff[right + 1] -= value;
    }

    int[] result = new int[length];
    int sum = 0;

    for (int i = 0; i < length; i++) {
        sum += diff[i];
        result[i] = sum;
    }

    return result;
}
```

## Dry Run

```text
length = 5
update =[1][2][3]

diff = [0, 2, 0, 0, -2, 0]

Prefix sum:
index 0: 0
index 1: 2
index 2: 2
index 3: 2
index 4: 0

result =[0][2]
```

## Complexity

- Each update: \(O(1)\)
- Final construction: \(O(n)\)
- Space: \(O(n)\)

---

# 11. Prefix Sum vs Sliding Window

| Feature | Prefix Sum | Sliding Window |
|---|---|---|
| Main use | Range sum / subarray sum condition | Contiguous window optimize karna |
| Works with negatives? | Yes | Usually no, for sum-based conditions |
| Random range query | Yes, \(O(1)\) | No |
| Subarray count | Very powerful | Possible in some cases |
| Extra space | Usually \(O(n)\) | Often \(O(1)\) or \(O(k)\) |
| Example | Subarray Sum Equals K | Longest Substring Without Repeating |

### Important Rule

```text
Positive numbers + contiguous window condition -> Sliding Window
Negative numbers + subarray sum condition -> Prefix Sum + HashMap
```

---

# 12. Common Mistakes

## Mistake 1: `prefix[0] = 0` Bhool Jana

Prefix array size `n + 1` rakho:

```java
int[] prefix = new int[n + 1];
prefix = 0;
```

Isse `sum(0, i)` easily nikalta hai:

```java
prefix[i + 1] - prefix
```

---

## Mistake 2: Range Formula Galat Lena

Correct formula:

```java
sum(left, right) = prefix[right + 1] - prefix[left]
```

Galat formula:

```java
prefix[right] - prefix[left]
```

---

## Mistake 3: HashMap Update Order Galat Karna

Pehle check karo:

```java
count += map.getOrDefault(prefixSum - k, 0);
```

Phir current prefix insert karo:

```java
map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
```

Agar pehle insert kar doge, to `k = 0` case mein current subarray khud count ho jayega, jo galat hai.

---

## Mistake 4: Negative Modulo Handle Na Karna

```java
int mod = ((prefixSum % k) + k) % k;
```

Ye negative numbers ke liye zaroori hai.

---

## Mistake 5: Integer Overflow

Bade sums ke liye `long` use karo:

```java
long prefixSum = 0;
```

---

# 13. Prefix Sum Decision Flow

```text
Problem padho
      |
      v
Kya subarray / range ka sum, product, count chahiye?
      |
      +-- No --> Prefix Sum probably nahi
      |
      +-- Yes
          |
          v
Kya fixed range queries hain?
      |
      +-- Yes --> Basic Prefix Sum
      |
      +-- Kya subarray sum equals K / count chahiye?
              |
              +-- Yes --> Prefix Sum + HashMap
              |
              +-- Kya divisible by K condition hai?
                      |
                      +-- Yes --> Prefix Sum + Modulo HashMap
```

---

# 14. Important Problems

## Easy

- Running Sum of 1d Array
- Range Sum Query — Immutable
- Find Pivot Index
- Left and Right Sum Differences

## Medium

- Subarray Sum Equals K
- Product of Array Except Self
- Continuous Subarray Sum
- Contiguous Array
- Subarray Sums Divisible by K
- Range Sum Query 2D — Immutable

## Hard

- Shortest Subarray with Sum at Least K
- Number of Submatrices That Sum to Target
- Maximum Score of Good Subarray

---

# 15. One-Line Recognition

> **Agar problem subarray/range ka sum, product, count, ya divisibility condition maang rahi hai — Prefix Sum try karo.**

Prefix Sum ka main power ye hai ki repeated range calculation ko precompute kar deta hai, isliye range queries \(O(n)\) se \(O(1)\) ho jati hain.