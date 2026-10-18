# 🚀 DSA Pattern 27 — Permutations

> **Goal:** Permutations ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, permutations with/without duplicates, swap-based approach, next permutation, templates, recursion tree, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Permutations Kya Hai?

Permutation ka matlab hai:

```text
Elements ko different order mein arrange karna.
```

Example:

```text
nums =[1][2][3]

Permutations:
[1][2][3]
[2][3][1]
[3][1][2]
[1][2][3]
[2][3][1]
[3][1][2]
```

Total permutations:

\[
n!
\]

For `n = 3`:

```text
3! = 3 * 2 * 1 = 6
```

### Permutation vs Combination

| Feature | Permutation | Combination |
|---|---|---|
| Order matters? | Yes | No |
| Example | `[1, 2]` and `[2, 1]` different | `[1, 2]` and `[2, 1]` same |
| Count | \(n!\) | \(nCr\) |
| Use case | Arrangements | Selections |

---

## 2. Permutations Kyun Use Karte Hain?

Main reasons:

1. **All arrangements generate karna**
2. **Order matter karta hai**
3. **Scheduling / ordering problems**
4. **Password / PIN combinations**
5. **Task ordering**
6. **Route planning**
7. **Backtracking problems**

### Core Intuition

> Har position par har unused element try karo.

Example:

```text
nums =[1][2][3]

First position:
1, 2, 3 — teeno try karo

Second position:
Bache hue elements try karo

Third position:
Last remaining element
```

---

## 3. Real-World Production Examples

| Real System | Permutations Use |
|---|---|
| Task Scheduler | Tasks ke possible orders |
| Delivery System | Delivery stops ke orders |
| Ride Sharing | Pickup/drop sequence |
| Manufacturing | Machine task ordering |
| Testing System | Test case execution order |
| UI Testing | Button click order |
| Game AI | Move ordering |
| Compiler | Instruction ordering |
| Database Query Planner | Join ordering |
| Logistics | Package loading order |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Delivery app mein 4 stops hain. Driver ko minimum time wala route chahiye. Possible routes kaise explore karoge?”

Answer:

```text
Har stop ko node maan lo.
Har possible stop ordering ek permutation hai.

Backtracking se permutations generate karo.
Har route ka time calculate karo.
Minimum time wala route choose karo.
```

Ya:

> “Testing system mein UI buttons ke possible click orders test karne hain. Kaise karoge?”

Answer:

```text
Buttons ko elements maan lo.
Har possible ordering permutation hai.
Backtracking se generate karo.
Invalid orders prune karo.
```

---

## 4. Kaise Pata Chalega Ki Permutations Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Permutations |
|---|---|
| All arrangements chahiye | Permutations |
| Order matters | Permutations |
| All possible orders | Permutations |
| Arrange tasks/items | Permutations |
| Route ordering | Permutations |
| Next lexicographic arrangement | Next Permutation |
| Unique arrangements with duplicates | Permutations II |

### Common Keywords

- Permutations
- Arrangements
- Order matters
- All possible orders
- Rearrange
- Sequence
- Next permutation

### Mental Checklist

1. Kya order matter karta hai?  
2. Kya saare arrangements chahiye?  
3. Kya elements unique hain?  
4. Kya duplicates hain?  
5. Kya constraint hai?  

Agar 2–3 answers **yes** hain, to Permutations try karo.

---

# 5. Permutations Ka Core Idea

Har recursion level par:

```text
Ek position decide karo
Us position par har unused element try karo
```

Example:

```text
nums =[1][2][3]

Position 0:
Try 1
Try 2
Try 3
```

```text
Choose 1:
Remaining:[2][3]

Position 1:
Try 2
Try 3
```

```text
Choose 1, 2:
Remaining: 

Position 2:
Try 3

Permutation:[1][2][3]
```

---

# 6. Template — Permutations Using Used Array

```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    boolean[] used = new boolean[nums.length];

    backtrack(nums, new ArrayList<>(), used, ans);

    return ans;
}

private void backtrack(int[] nums,
                       List<Integer> current,
                       boolean[] used,
                       List<List<Integer>> ans) {

    if (current.size() == nums.length) {
        ans.add(new ArrayList<>(current));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }

        used[i] = true;
        current.add(nums[i]);

        backtrack(nums, current, used, ans);

        used[i] = false;
        current.remove(current.size() - 1);
    }
}
```

### Important

```java
ans.add(new ArrayList<>(current));
```

Direct `current` add mat karo.

---

# 7. Example 1: Permutations

## Problem

Distinct integers array ke saare permutations return karo.

```text
nums =[1][2][3]

Output:
[
 ,[1][2][3]
 ,[2][3][1]
 ,[3][1][2]
 ,[1][2][3]
 ,[1][2][3]
[2][3][1]
]
```

## Why Backtracking?

Har position par unused elements try karne hain.

## Solution

```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    boolean[] used = new boolean[nums.length];

    backtrack(nums, new ArrayList<>(), used, ans);

    return ans;
}

private void backtrack(int[] nums,
                       List<Integer> current,
                       boolean[] used,
                       List<List<Integer>> ans) {

    if (current.size() == nums.length) {
        ans.add(new ArrayList<>(current));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }

        used[i] = true;
        current.add(nums[i]);

        backtrack(nums, current, used, ans);

        used[i] = false;
        current.remove(current.size() - 1);
    }
}
```

## Recursion Tree

```text
                []
        /        |        \
                    
     /   \     /   \     /   \
[1][2][3]
   |     |     |     |     |     |
[2][3][1]
```

## Dry Run

```text
nums =[1][2][3]

Start:
current = []
used = [false, false, false]

Choose 1:
current = 
used = [true, false, false]

    Choose 2:
    current =[1][2]
    used = [true, true, false]

        Choose 3:
        current =[1][2][3]
        Save[2][3][1]

    Choose 3:
    current =[1][3]
    used = [true, false, true]

        Choose 2:
        current =[1][2][3]
        Save[1][2][3]

Choose 2:
current = 
used = [false, true, false]

    Choose 1:
    current =[1][2]

        Choose 3:
        Save[1][2][3]

    Choose 3:
    current =[2][3]

        Choose 1:
        Save[1][3][2]

Choose 3:
current = 
used = [false, false, true]

    Choose 1:
    current =[1][3]

        Choose 2:
        Save[1][2][3]

    Choose 2:
    current =[2][3]

        Choose 1:
        Save[1][2][3]
```

## Complexity

- Time: \(O(n! \times n)\)
- Space: \(O(n)\)

---

# 8. Example 2: Permutations — Swap-Based Approach

## Problem

Same permutations problem ko swap se solve karo.

## Idea

Har position par:

```text
Current index element ko
Baaki har index element ke saath swap karo
```

## Solution

```java
public List<List<Integer>> permuteSwap(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    backtrackSwap(nums, 0, ans);

    return ans;
}

private void backtrackSwap(int[] nums, int index, List<List<Integer>> ans) {
    if (index == nums.length) {
        List<Integer> current = new ArrayList<>();

        for (int num : nums) {
            current.add(num);
        }

        ans.add(current);
        return;
    }

    for (int i = index; i < nums.length; i++) {
        swap(nums, index, i);

        backtrackSwap(nums, index + 1, ans);

        swap(nums, index, i);
    }
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Dry Run

```text
nums =[1][2][3]

index = 0:

Swap 0 and 0:
[1][2][3]

    index = 1:

    Swap 1 and 1:
[1][2][3]

        index = 2:
        Save[1][2][3]

    Swap 1 and 2:
[2][3][1]

        index = 2:
        Save[1][2][3]

Swap 0 and 1:
[2][3][1]

    index = 1:

    Save[1][2][3]
    Save[2][3][1]

Swap 0 and 2:
[1][2][3]

    index = 1:

    Save[2][3][1]
    Save[1][2][3]
```

## Complexity

- Time: \(O(n! \times n)\)
- Space: \(O(n)\)

---

# 9. Example 3: Permutations II — With Duplicates

## Problem

Array mein duplicates ho sakte hain.

Unique permutations return karo.

```text
nums =[1][2]

Output:
[
 ,[1][2]
 ,[2][1]
[1][2]
]
```

## Why Sorting + Skip?

Sorting ke baad duplicate elements adjacent hote hain.

Same recursion level par duplicate element choose karne se duplicate permutation banta hai.

## Duplicate Skip Condition

```java
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    continue;
}
```

## Solution

```java
public List<List<Integer>> permuteUnique(int[] nums) {
    Arrays.sort(nums);

    List<List<Integer>> ans = new ArrayList<>();

    boolean[] used = new boolean[nums.length];

    backtrack(nums, new ArrayList<>(), used, ans);

    return ans;
}

private void backtrack(int[] nums,
                       List<Integer> current,
                       boolean[] used,
                       List<List<Integer>> ans) {

    if (current.size() == nums.length) {
        ans.add(new ArrayList<>(current));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }

        if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
            continue;
        }

        used[i] = true;
        current.add(nums[i]);

        backtrack(nums, current, used, ans);

        used[i] = false;
        current.remove(current.size() - 1);
    }
}
```

## Dry Run

```text
nums =[1][2]

Sorted:
[2][1]

Level 0:
Choose first 1:
current = 

    Level 1:
    Choose second 1:
    current =[1]

        Level 2:
        Choose 2:
        Save[1][2]

    Choose 2:
    current =[2][1]

        Level 2:
        Choose second 1:
        Save[1][2]

Choose second 1 at level 0:
Duplicate and previous 1 not used
Skip

Choose 2:
current = 

    Level 1:
    Choose first 1:
    current =[1][2]

        Level 2:
        Choose second 1:
        Save[1][2]

Final:
[
 ,[1][2]
 ,[2][1]
[1][2]
]
```

## Complexity

- Time: \(O(n! \times n)\) worst case
- Space: \(O(n)\)

---

# 10. Example 4: Next Permutation

## Problem

Array ko next lexicographic permutation mein convert karo.

Agar next permutation nahi hai, to first permutation banao.

```text
nums =[1][2][3]
Output:[1][2][3]
```

```text
nums =[2][3][1]
Output:[3][1][2]
```

## Algorithm

1. Right se traverse karo.
2. Pehla index dhundo jahan:

```text
nums[i] < nums[i + 1]
```

Ise `pivot` bolo.

3. Agar pivot nahi mila, to array already largest permutation hai.
4. Array reverse kar do.
5. Warna pivot ke right side mein pivot se bada just-next element dhundo.
6. Swap karo.
7. Pivot ke baad wala part reverse karo.

## Solution

```java
public void nextPermutation(int[] nums) {
    int n = nums.length;

    int pivot = -1;

    for (int i = n - 2; i >= 0; i--) {
        if (nums[i] < nums[i + 1]) {
            pivot = i;
            break;
        }
    }

    if (pivot == -1) {
        reverse(nums, 0, n - 1);
        return;
    }

    for (int i = n - 1; i > pivot; i--) {
        if (nums[i] > nums[pivot]) {
            swap(nums, i, pivot);
            break;
        }
    }

    reverse(nums, pivot + 1, n - 1);
}

private void reverse(int[] nums, int left, int right) {
    while (left < right) {
        swap(nums, left, right);
        left++;
        right--;
    }
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Dry Run

```text
nums =[1][2][3]

Find pivot:
i = 1:
nums = 2 < nums = 3
pivot = 1

Find just greater from right:
i = 2:
nums = 3 > 2
Swap 2 and 3

nums =[1][2][3]

Reverse after pivot:
Already sorted descending? No

Reverse index 2 to 2:
No change

Answer:
[1][2][3]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 11. Example 5: Letter Case Permutation

## Problem

String ke har letter ko lowercase ya uppercase banakar saare possible strings generate karo.

```text
s = "a1b2"

Output:
["a1b2", "a1B2", "A1b2", "A1B2"]
```

## Why Backtracking?

Har letter ke liye do choices:

```text
Lowercase
Uppercase
```

Digits unchanged rehte hain.

## Solution

```java
public List<String> letterCasePermutation(String s) {
    List<String> ans = new ArrayList<>();

    backtrack(s.toCharArray(), 0, ans);

    return ans;
}

private void backtrack(char[] chars, int index, List<String> ans) {
    if (index == chars.length) {
        ans.add(new String(chars));
        return;
    }

    char current = chars[index];

    if (Character.isLetter(current)) {
        chars[index] = Character.toLowerCase(current);
        backtrack(chars, index + 1, ans);

        chars[index] = Character.toUpperCase(current);
        backtrack(chars, index + 1, ans);

        chars[index] = current;
    } else {
        backtrack(chars, index + 1, ans);
    }
}
```

## Dry Run

```text
s = "a1b"

index 0:
'a' letter

Lowercase:
a1b

Uppercase:
A1b

For a1b:
index 2:
'b' letter

a1b
a1B

For A1b:
A1b
A1B

Final:
["a1b", "a1B", "A1b", "A1B"]
```

## Complexity

- Time: \(O(2^L \times L)\), where `L` = number of letters
- Space: \(O(L)\)

---

# 12. Permutations vs Combinations

| Feature | Permutations | Combinations |
|---|---|---|
| Order | Matters | Does not matter |
| Example | `[1,2]` and `[2,1]` different | `[1,2]` and `[2,1]` same |
| Count | \(n!\) | \(nCr\) |
| Loop start | Usually `0` | Usually `index` |
| Used array | Commonly used | Not always needed |
| Example | Permutations | Combination Sum |

### Rule

```text
Order matters -> Permutations
Order does not matter -> Combinations
```

---

# 13. Common Mistakes

## Mistake 1: Used Array Bhoolna

Bina `used[]` ke same element multiple baar choose ho sakta hai.

```java
if (used[i]) {
    continue;
}
```

---

## Mistake 2: Backtrack Na Karna

```java
used[i] = true;
current.add(nums[i]);

backtrack(...);

used[i] = false;
current.remove(current.size() - 1);
```

Dono undo steps zaroori hain.

---

## Mistake 3: Duplicate Skip Condition Galat Karna

Correct:

```java
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    continue;
}
```

---

## Mistake 4: Result Mein Same List Reference Add Karna

Galat:

```java
ans.add(current);
```

Correct:

```java
ans.add(new ArrayList<>(current));
```

---

## Mistake 5: Next Permutation Mein Reverse Bhoolna

Pivot ke baad wala part descending hota hai.

Use reverse karna zaroori hai:

```java
reverse(nums, pivot + 1, n - 1);
```

---

# 14. Permutations Decision Flow

```text
Problem padho
      |
      v
Kya order matter karta hai?
      |
      +-- No --> Combinations
      |
      +-- Yes
          |
          v
Kya all arrangements chahiye?
      |
      +-- Yes --> Permutations
      |
      +-- Kya duplicates hain?
              |
              +-- Yes --> Sort + Skip duplicates
              |
              +-- Kya next lexicographic arrangement chahiye?
                      |
                      +-- Yes --> Next Permutation
```

---

# 15. Important Problems

## Easy

- Permutations
- Letter Case Permutation
- Permutation Sequence

## Medium

- Permutations II
- Next Permutation
- Beautiful Arrangement
- Letter Combinations of a Phone Number
- Permutation in String

## Hard

- Permutation Sequence
- N-Queens
- Sudoku Solver
- Word Search II

---

# 16. One-Line Recognition

> **Agar problem all possible arrangements, ordering, sequence generation, ya next lexicographic arrangement maang rahi hai — Permutations try karo.**

Permutations ka main power ye hai ki har position par unused elements try karke saare possible orders generate karte hain, aur backtracking se invalid/duplicate paths avoid karte hain.