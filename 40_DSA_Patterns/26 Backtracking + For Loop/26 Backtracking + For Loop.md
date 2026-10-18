# 🚀 DSA Pattern 26 — Backtracking + For Loop

> **Goal:** Backtracking + For Loop ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, combinations, combination sum, duplicate handling, pruning, templates, recursion tree, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Backtracking + For Loop Kya Hai?

Backtracking ek technique hai jisme hum:

```text
1. Ek choice choose karte hain
2. Recurse karte hain
3. Choice undo karte hain
```

For Loop tab use hota hai jab humein current position se **multiple options** try karne hon.

```text
for (int i = start; i < n; i++) {
    choose nums[i]
    backtrack(i or i + 1)
    unchoose nums[i]
}
```

### Core Idea

```text
Choose -> Explore -> Unchoose
```

Example:

```text
candidates =[2][3][6][7]
target = 7

Possible combinations:
[2][3]

```

Yahan hum current index se aage ke candidates try karte hain.

---

## 2. Backtracking + For Loop Kyun Use Karte Hain?

Main reasons:

1. **Combinations generate karna**
2. **Same element reuse allow karna**
3. **Duplicate combinations avoid karna**
4. **Pruning se unnecessary recursion avoid karna**
5. **All valid solutions explore karna**
6. **Constraint satisfaction problems solve karna**

### Include / Exclude vs For Loop Backtracking

| Feature | Include / Exclude | Backtracking + For Loop |
|---|---|---|
| Decision | Har element include/exclude | Current index se aage choose |
| Best for | Subsets, knapsack | Combinations |
| Same element reuse | Usually not | Possible |
| Duplicate handling | Sorting + skip | Sorting + skip in loop |
| Example | Subsets | Combination Sum |

### Core Intuition

> Current position se aage ke options try karo, taaki duplicate combinations na banein.

---

## 3. Real-World Production Examples

| Real System | Backtracking + For Loop Use |
|---|---|
| E-commerce | Product combinations within budget |
| Travel Booking | Flight + hotel + cab combinations |
| Food Delivery | Combo meal generation |
| Ad System | Keyword combinations |
| Subscription Planner | Feature combinations |
| Coupon System | Valid discount combinations |
| Test Case Generator | Input combinations |
| Route Planner | Multiple route combinations |
| Scheduling System | Time slot combinations |
| Configuration Manager | Valid config combinations |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “E-commerce app mein user ke budget ke andar possible product bundles generate karne hain. Kaise karoge?”

Answer:

```text
Har product ko candidate maan lo.
Budget ko target maan lo.

Backtracking + for loop se combinations generate karo.
Jab budget exceed ho jaye, pruning karo.
```

Ya:

> “Coupon system mein multiple discount coupons combine karke target discount banane hain. Valid combinations kaise nikaloge?”

Answer:

```text
Har coupon ko candidate maan lo.
Target discount ko target maan lo.

Backtracking se combinations explore karo.
Used coupons track karo.
Invalid branches prune karo.
```

---

## 4. Kaise Pata Chalega Ki Backtracking + For Loop Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Backtracking + For Loop |
|---|---|
| Combinations chahiye | Backtracking + For Loop |
| Combination sum | Backtracking + For Loop |
| Same element reuse allowed | Backtracking + For Loop |
| All valid combinations | Backtracking + For Loop |
| Constraints ke saath combinations | Backtracking + For Loop |
| Phone letter combinations | Backtracking + For Loop |
| Generate parentheses | Backtracking |
| Word search | Backtracking |

### Common Keywords

- Combination
- Combination Sum
- All possible
- Generate
- Choose
- Candidates
- Reuse
- Unique combinations
- Constraints

### Mental Checklist

1. Kya combinations generate karni hain?  
2. Kya order matter nahi karta?  
3. Kya same element reuse ho sakta hai?  
4. Kya duplicates avoid karne hain?  
5. Kya constraint hai, jaise target sum?  

Agar 2–3 answers **yes** hain, to Backtracking + For Loop try karo.

---

# 5. Backtracking Ka Core Structure

```java
public void backtrack(int start, List<Integer> current) {
    if (baseCase) {
        saveResult(current);
        return;
    }

    for (int i = start; i < n; i++) {
        // choose
        current.add(candidate[i]);

        // explore
        backtrack(i or i + 1, current);

        // unchoose
        current.remove(current.size() - 1);
    }
}
```

### Important Difference

```text
Combination Sum:
Same element reuse allowed
Recurse with i

Combination Sum II:
Same element reuse not allowed
Recurse with i + 1
```

---

# 6. Example 1: Combination Sum

## Problem

Candidates array aur target diya hai.

Aise unique combinations return karo jinka sum target ho.

Same number unlimited times use ho sakta hai.

```text
candidates =[2][3][6][7]
target = 7

Output:
[
 ,[2][3]
  
]
```

## Why Backtracking + For Loop?

Har position se aage ke candidates try karne hain.

Same candidate reuse allowed hai, isliye recursion mein `i` pass karte hain, not `i + 1`.

## Solution

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();

    backtrack(candidates, 0, target, new ArrayList<>(), ans);

    return ans;
}

private void backtrack(int[] candidates,
                       int index,
                       int target,
                       List<Integer> current,
                       List<List<Integer>> ans) {

    if (target == 0) {
        ans.add(new ArrayList<>(current));
        return;
    }

    if (target < 0) {
        return;
    }

    for (int i = index; i < candidates.length; i++) {
        current.add(candidates[i]);

        backtrack(candidates, i, target - candidates[i], current, ans);

        current.remove(current.size() - 1);
    }
}
```

## Why `backtrack(candidates, i, ...)`?

Kyunki same candidate dobara use ho sakta hai.

Example:

```text
2 -> 2 -> 3
```

Yahan `2` do baar use hua.

## Why Start From `index`?

Agar hum hamesha `0` se start karenge, to duplicate combinations banenge.

```text
[2][3]
[3][2]
```

Dono same combination hain.

Isliye current index se aage hi try karte hain.

## Recursion Tree

```text
target = 7
candidates =[2][3][6][7]

Start: []
Choose 2 -> target = 5
    Choose 2 -> target = 3
        Choose 2 -> target = 1
            No valid
        Choose 3 -> target = 0
[2][3]
    Choose 3 -> target = 2
        No valid
    Choose 6 -> negative
Choose 3 -> target = 4
    Choose 3 -> target = 1
        No valid
    Choose 6 -> negative
Choose 6 -> target = 1
    No valid
Choose 7 -> target = 0
    
```

## Dry Run

```text
candidates =[2][3][6][7]
target = 7

backtrack(0, 7, [])

i = 0:
choose 2
current = 
target = 5

    backtrack(0, 5, )

    i = 0:
    choose 2
    current =[2]
    target = 3

        backtrack(0, 3, )[2]

        i = 0:
        choose 2
        target = 1
        no valid

        i = 1:
        choose 3
        target = 0
        Save[2][3]

    i = 1:
    choose 3
    current =[2][3]
    target = 2
    no valid

    i = 2:
    choose 6
    target = -1
    invalid

i = 1:
choose 3
current = 
target = 4

    backtrack(1, 4, )

    i = 1:
    choose 3
    target = 1
    no valid

    i = 2:
    choose 6
    invalid

i = 2:
choose 6
target = 1
no valid

i = 3:
choose 7
target = 0
Save 

Final:
[
 ,[2][3]
  
]
```

## Complexity

- Time: Approximately \(O(k^{target})\), where `k` = number of candidates
- Space: \(O(target)\)

---

# 7. Pruning — Important Optimization

Agar candidates sorted hain, to jab candidate target se bada ho jaye, aage ke candidates bhi bade honge.

Isliye loop break kar sakte hain.

## Optimized Solution

```java
public List<List<Integer>> combinationSumOptimized(int[] candidates, int target) {
    Arrays.sort(candidates);

    List<List<Integer>> ans = new ArrayList<>();

    backtrack(candidates, 0, target, new ArrayList<>(), ans);

    return ans;
}

private void backtrack(int[] candidates,
                       int index,
                       int target,
                       List<Integer> current,
                       List<List<Integer>> ans) {

    if (target == 0) {
        ans.add(new ArrayList<>(current));
        return;
    }

    for (int i = index; i < candidates.length; i++) {
        if (candidates[i] > target) {
            break;
        }

        current.add(candidates[i]);

        backtrack(candidates, i, target - candidates[i], current, ans);

        current.remove(current.size() - 1);
    }
}
```

## Why Pruning?

```text
candidates =[2][3][6][7]
target = 2

Candidate 3 > 2
Candidate 6 > 2
Candidate 7 > 2

To unhe try karne ka koi point nahi.
```

---

# 8. Example 2: Combination Sum II

## Problem

Candidates array mein duplicates ho sakte hain.

Har candidate sirf ek baar use ho sakta hai.

Unique combinations return karo.

```text
candidates =[1][2][5][6][7][10]
target = 8

Output:
[
 ,[1][6]
 ,[2][5][1]
 ,[7][1]
[6][2]
]
```

## Why `i + 1`?

Har candidate sirf ek baar use ho sakta hai.

Isliye recursion mein:

```java
backtrack(candidates, i + 1, target - candidates[i], current, ans);
```

## Why Skip Duplicates?

Sorted array mein same elements adjacent hote hain.

Agar same level par duplicate candidate choose karenge, to duplicate combinations banenge.

```text
[1][2][5]
  // duplicate[2][5][1]
```

## Solution

```java
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);

    List<List<Integer>> ans = new ArrayList<>();

    backtrack(candidates, 0, target, new ArrayList<>(), ans);

    return ans;
}

private void backtrack(int[] candidates,
                       int index,
                       int target,
                       List<Integer> current,
                       List<List<Integer>> ans) {

    if (target == 0) {
        ans.add(new ArrayList<>(current));
        return;
    }

    for (int i = index; i < candidates.length; i++) {
        if (i > index && candidates[i] == candidates[i - 1]) {
            continue;
        }

        if (candidates[i] > target) {
            break;
        }

        current.add(candidates[i]);

        backtrack(candidates, i + 1, target - candidates[i], current, ans);

        current.remove(current.size() - 1);
    }
}
```

## Why This Duplicate Check?

```java
if (i > index && candidates[i] == candidates[i - 1]) {
    continue;
}
```

- `i == index`: Current level ka pehla occurrence, allow karo.
- `i > index`: Same level ka duplicate, skip karo.

## Dry Run

```text
candidates =[1][2][5][6][7][10]
target = 8

Sorted:
[1][2][5][6][7][10]

Level 0:
Choose first 1:
target = 7

    Level 1:
    Choose second 1:
    target = 6

        Level 2:
        Choose 6:
        target = 0
        Save[1][6]

    Choose 2:
    target = 5

        Level 2:
        Choose 5:
        target = 0
        Save[1][2][5]

    Choose 7:
    target = 0
    Save[1][7]

Choose second 1 at level 0:
Duplicate -> skip

Choose 2:
target = 6

    Choose 6:
    target = 0
    Save[2][6]

Final:
[
 ,[1][6]
 ,[1][2][5]
 ,[7][1]
[2][6]
]
```

## Complexity

- Time: \(O(2^n)\) worst case
- Space: \(O(n)\)

---

# 9. Example 3: Combination Sum III

## Problem

Numbers `1` se `9` use karke `k` numbers ki combination banao jinka sum `n` ho.

Har number sirf ek baar use ho sakta hai.

```text
k = 3
n = 7

Output:
[
[1][2][4]
]
```

## Solution

```java
public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> ans = new ArrayList<>();

    backtrack(k, n, 1, new ArrayList<>(), ans);

    return ans;
}

private void backtrack(int k,
                       int target,
                       int start,
                       List<Integer> current,
                       List<List<Integer>> ans) {

    if (current.size() == k) {
        if (target == 0) {
            ans.add(new ArrayList<>(current));
        }

        return;
    }

    for (int i = start; i <= 9; i++) {
        if (i > target) {
            break;
        }

        current.add(i);

        backtrack(k, target - i, i + 1, current, ans);

        current.remove(current.size() - 1);
    }
}
```

## Dry Run

```text
k = 3
n = 7

Start: []

Choose 1:
current = 
target = 6

    Choose 2:
    current =[1][2]
    target = 4

        Choose 3:
        target = 1
        Invalid

        Choose 4:
        target = 0
        Save[1][2][4]

    Choose 3:
    target = 3

        Need one more number
        No valid

Choose 2:
current = 
target = 5

    Choose 3:
    target = 2
    Invalid

    Choose 4:
    target = 1
    Invalid

Choose 3:
current = 
target = 4

    Need two more numbers
    No valid

Final:
[
[1][2][4]
]
```

## Complexity

- Time: \(O(C(9, k))\)
- Space: \(O(k)\)

---

# 10. Example 4: Letter Combinations of a Phone Number

## Problem

Phone keypad ke digits diye hain.

Unke possible letter combinations return karo.

```text
digits = "23"

Output:
["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
```

## Why Backtracking + For Loop?

Har digit ke liye multiple letters hain.

Har level par current digit ke letters try karte hain.

## Solution

```java
public List<String> letterCombinations(String digits) {
    List<String> ans = new ArrayList<>();

    if (digits == null || digits.length() == 0) {
        return ans;
    }

    String[] mapping = {
        "", "", "abc", "def", "ghi",
        "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    backtrack(digits, 0, new StringBuilder(), mapping, ans);

    return ans;
}

private void backtrack(String digits,
                       int index,
                       StringBuilder current,
                       String[] mapping,
                       List<String> ans) {

    if (index == digits.length()) {
        ans.add(current.toString());
        return;
    }

    String letters = mapping[digits.charAt(index) - '0'];

    for (char c : letters.toCharArray()) {
        current.append(c);

        backtrack(digits, index + 1, current, mapping, ans);

        current.deleteCharAt(current.length() - 1);
    }
}
```

## Recursion Tree

```text
digits = "23"

Level 0: digit 2 -> abc
Level 1: digit 3 -> def

a
├── d
├── e
└── f

b
├── d
├── e
└── f

c
├── d
├── e
└── f
```

## Dry Run

```text
digits = "23"

index = 0:
letters for 2 = abc

Choose a:
current = "a"

    index = 1:
    letters for 3 = def

    Choose d -> "ad"
    Choose e -> "ae"
    Choose f -> "af"

Choose b:
current = "b"

    "bd"
    "be"
    "bf"

Choose c:
current = "c"

    "cd"
    "ce"
    "cf"

Final:
["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
```

## Complexity

Let:

```text
n = digits length
```

- Time: \(O(4^n \times n)\)
- Space: \(O(n)\)

---

# 11. Example 5: Generate Parentheses

## Problem

`n` pairs of parentheses ke saare valid combinations generate karo.

```text
n = 3

Output:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

## Why Backtracking?

Har position par do choices:

```text
Add (
Add )
```

Constraints:

```text
open < n -> ( add kar sakte hain
close < open -> ) add kar sakte hain
```

## Solution

```java
public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<>();

    backtrack(ans, new StringBuilder(), 0, 0, n);

    return ans;
}

private void backtrack(List<String> ans,
                       StringBuilder current,
                       int open,
                       int close,
                       int n) {

    if (current.length() == 2 * n) {
        ans.add(current.toString());
        return;
    }

    if (open < n) {
        current.append('(');
        backtrack(ans, current, open + 1, close, n);
        current.deleteCharAt(current.length() - 1);
    }

    if (close < open) {
        current.append(')');
        backtrack(ans, current, open, close + 1, n);
        current.deleteCharAt(current.length() - 1);
    }
}
```

## Dry Run

```text
n = 2

Start:
current = ""
open = 0
close = 0

Add (
current = "("
open = 1

Add (
current = "(("
open = 2

Add )
current = "(()"
close = 1

Add )
current = "(())"
Answer added

Backtrack:
current = "("

Add )
current = "()"
close = 1

Add (
current = "()("
open = 2

Add )
current = "()()"
Answer added
```

## Complexity

- Time: \(O(4^n / \sqrt{n})\)
- Space: \(O(n)\)

---

# 12. Example 6: Word Search

## Problem

Grid mein word dhundo.

Har cell ek baar use ho sakta hai.

Adjacent cells se move kar sakte ho.

```text
board = [
  ['A', 'B', 'C', 'E'],
  ['S', 'F', 'C', 'S'],
  ['A', 'D', 'E', 'E']
]

word = "ABCCED"

Output: true
```

## Why Backtracking + For Loop?

Har cell se 4 directions try karne hain.

Visited cells mark karke backtrack karna hai.

## Solution

```java
public boolean exist(char[][] board, String word) {
    int rows = board.length;
    int cols = board.length;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (dfs(board, word, i, j, 0)) {
                return true;
            }
        }
    }

    return false;
}

private boolean dfs(char[][] board, String word, int i, int j, int index) {
    if (index == word.length()) {
        return true;
    }

    if (i < 0 || i >= board.length ||
        j < 0 || j >= board.length ||
        board[i][j] != word.charAt(index)) {

        return false;
    }

    char temp = board[i][j];
    board[i][j] = '#';

    boolean found =
        dfs(board, word, i + 1, j, index + 1) ||
        dfs(board, word, i - 1, j, index + 1) ||
        dfs(board, word, i, j + 1, index + 1) ||
        dfs(board, word, i, j - 1, index + 1);

    board[i][j] = temp;

    return found;
}
```

## Why Mark and Unmark?

Same cell dobara use na ho.

```java
board[i][j] = '#';
```

Recursion ke baad:

```java
board[i][j] = temp;
```

## Complexity

- Time: \(O(m \times n \times 4^L)\), where `L` = word length
- Space: \(O(L)\)

---

# 13. Backtracking Templates

## Template 1: Combination Sum — Reuse Allowed

```java
for (int i = index; i < candidates.length; i++) {
    current.add(candidates[i]);

    backtrack(candidates, i, target - candidates[i], current, ans);

    current.remove(current.size() - 1);
}
```

## Template 2: Combination Sum II — No Reuse

```java
for (int i = index; i < candidates.length; i++) {
    if (i > index && candidates[i] == candidates[i - 1]) {
        continue;
    }

    current.add(candidates[i]);

    backtrack(candidates, i + 1, target - candidates[i], current, ans);

    current.remove(current.size() - 1);
}
```

## Template 3: Permutations

```java
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
```

---

# 14. Common Mistakes

## Mistake 1: `i` vs `i + 1` Confusion

Combination Sum:

```text
Same element reuse allowed
Recurse with i
```

Combination Sum II:

```text
Element reuse not allowed
Recurse with i + 1
```

---

## Mistake 2: Duplicate Skip Galat Karna

Correct:

```java
if (i > index && candidates[i] == candidates[i - 1]) {
    continue;
}
```

Agar `i > 0` use karoge, to valid first occurrence bhi skip ho jayega.

---

## Mistake 3: Backtrack Na Karna

```java
current.add(candidate);
backtrack(...);
// Missing remove
```

Isse current list galat ho jayegi.

Correct:

```java
current.add(candidate);
backtrack(...);
current.remove(current.size() - 1);
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

## Mistake 5: Pruning Bhoolna

Sorted candidates mein:

```java
if (candidates[i] > target) {
    break;
}
```

Ye unnecessary recursion avoid karta hai.

---

## Mistake 6: Base Case Galat Honа

Combination Sum:

```java
if (target == 0)
```

Combination Sum III:

```java
if (current.size() == k)
```

Problem ke according base case change hota hai.

---

# 15. Backtracking + For Loop Decision Flow

```text
Problem padho
      |
      v
Kya combinations generate karni hain?
      |
      +-- No --> Backtracking + For Loop probably nahi
      |
      +-- Yes
          |
          v
Kya same element reuse allowed hai?
      |
      +-- Yes --> Recurse with i
      |
      +-- No
          |
          v
Kya duplicates hain?
      |
      +-- Yes --> Sort + Skip duplicates
      |
      +-- Kya grid/word search hai?
              |
              +-- Yes --> Mark visited + Backtrack
              |
              +-- Kya constraints hain?
                      |
                      +-- Yes --> Pruning
```

---

# 16. Important Problems

## Easy

- Letter Combinations of a Phone Number
- Binary Watch
- Letter Case Permutation

## Medium

- Combination Sum
- Combination Sum II
- Combination Sum III
- Generate Parentheses
- Word Search
- Subsets
- Subsets II
- Permutations

## Hard

- Word Search II
- Sudoku Solver
- N-Queens
- Palindrome Partitioning
- Remove Invalid Parentheses

---

# 17. One-Line Recognition

> **Agar problem combinations generate karne, candidates choose karne, constraints satisfy karne, ya all valid solutions explore karne ki baat kar rahi hai — Backtracking + For Loop try karo.**

Backtracking + For Loop ka main power ye hai ki current level par multiple options try karte hain, invalid paths prune karte hain, aur choice undo karke next possibility explore karte hain.