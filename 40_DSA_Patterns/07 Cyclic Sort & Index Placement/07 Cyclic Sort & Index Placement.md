# 🚀 DSA Pattern 07 — Cyclic Sort / Index Placement

> **Goal:** Samajhna ki Cyclic Sort **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Cyclic Sort Kya Hai?

Cyclic Sort ek technique hai jisme hum array ke elements ko unke **correct index** par place karte hain.

Idea simple hai:

```text
Agar array mein numbers 1 se n tak hain,
to value v ka correct index = v - 1
```

Example:

```text
arr =[1][2][3][4][5]

Value 1 -> index 0
Value 2 -> index 1
Value 3 -> index 2
Value 4 -> index 3
Value 5 -> index 4
```

To sorted array hoga:

```text
[1][2][3][4][5]
```

Cyclic Sort har element ko uske correct position par bhejta hai.

---

## 2. Cyclic Sort Kyun Use Karte Hain?

Main reason: **jab array mein numbers 1 se n ya 0 se n-1 range mein hon, aur humein O(n) time + O(1) space solution chahiye.**

Example:

```text
arr =[1][2][3][4][5]
```

Normal sorting:

```java
Arrays.sort(arr);
```

Time: \(O(n \log n)\)

But Cyclic Sort:

```text
Time: O(n)
Space: O(1)
```

### Core Intuition

> Har number ki ek natural correct position hoti hai. Us number ko us position par bhej do.

Example:

```text
arr =[1][2][3][4][5]

index 0 par 3 hai
but 3 ka correct index 2 hai

To 3 ko index 2 par bhejo
```

---

## 3. Kaise Pata Chalega Ki Cyclic Sort Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Cyclic Sort |
|---|---|
| Array contains numbers from `1` to `n` | Cyclic Sort |
| Array contains numbers from `0` to `n-1` | Cyclic Sort |
| Missing number dhundhna hai | Cyclic Sort |
| Duplicate number dhundhna hai | Cyclic Sort |
| All missing numbers dhundhne hain | Cyclic Sort |
| O(n) time and O(1) space chahiye | Cyclic Sort |
| Values index-based placement allow karti hain | Cyclic Sort |

### Common Keywords

- Missing number
- First missing positive
- Find all numbers disappeared
- Find duplicate number
- Set mismatch
- Find all duplicates

### Mental Checklist

1. Kya array mein numbers limited range mein hain?  
2. Kya numbers `1 to n` ya `0 to n-1` hain?  
3. Kya missing/duplicate number dhundhna hai?  
4. Kya \(O(n)\) time aur \(O(1)\) space chahiye?  
5. Kya value ko index par map kar sakte hain?  

Agar 3–4 answers **yes** hain, to Cyclic Sort try karo.

---

# 4. Index Mapping

## Case 1: Numbers `1` to `n`

```text
Value v -> correct index v - 1
```

Example:

```text
arr =[1][2][3][4][5]

Value 1 -> index 0
Value 2 -> index 1
Value 3 -> index 2
Value 4 -> index 3
Value 5 -> index 4
```

Formula:

```java
int correctIndex = arr[i] - 1;
```

---

## Case 2: Numbers `0` to `n - 1`

```text
Value v -> correct index v
```

Example:

```text
arr =[0][1][2][3][4]

Value 0 -> index 0
Value 1 -> index 1
Value 2 -> index 2
Value 3 -> index 3
Value 4 -> index 4
```

Formula:

```java
int correctIndex = arr[i];
```

---

# 5. Cyclic Sort Template

## For Numbers `1` to `n`

```java
public void cyclicSort(int[] arr) {
    int i = 0;

    while (i < arr.length) {
        int correctIndex = arr[i] - 1;

        if (arr[i] != arr[correctIndex]) {
            swap(arr, i, correctIndex);
        } else {
            i++;
        }
    }
}

private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

### Important Condition

```java
if (arr[i] != arr[correctIndex])
```

Ye duplicate elements se infinite loop avoid karta hai.

Example:

```text
arr =[1][2]

i = 0:
arr = 1
correctIndex = 0
arr == arr
i++

i = 1:
arr = 1
correctIndex = 0
arr == arr
i++
```

Agar hum blindly swap karte:

```java
swap(arr, i, correctIndex);
```

To same elements baar-baar swap hote rehte, infinite loop ban jata.

---

# 6. Example 1: Missing Number

## Problem

Array mein `n` distinct numbers hain range `[0, n]` ke. Ek number missing hai. Wo nikalo.

```text
nums =[0][1][3]
Output: 2
```

## Why Cyclic Sort?

Numbers `0` to `n` hain.

Value `v` ka correct index `v` hai.

Cyclic sort ke baad jo index apne value se match nahi karega, wahi missing number hoga.

## Solution

```java
public int missingNumber(int[] nums) {
    int i = 0;

    while (i < nums.length) {
        int correctIndex = nums[i];

        if (nums[i] < nums.length && nums[i] != nums[correctIndex]) {
            swap(nums, i, correctIndex);
        } else {
            i++;
        }
    }

    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i) {
            return i;
        }
    }

    return nums.length;
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Why `nums[i] < nums.length`?

Kyunki missing number `n` ho sakta hai.

Example:

```text
nums =[0][1]
n = 2
missing = 2
```

Value `2` array mein hai hi nahi, to uska correct index array ke bahar hoga.

Isliye:

```java
if (nums[i] < nums.length)
```

## Dry Run

```text
nums =[0][1][3]

i = 0:
nums = 3
correctIndex = 3
3 >= nums.length
i++

i = 1:
nums = 0
correctIndex = 0
nums != nums
swap

nums =[0][1][3]

i = 1:
nums = 3
correctIndex = 3
3 >= nums.length
i++

i = 2:
nums = 1
correctIndex = 1
nums != nums
swap

nums =[0][1][3]

Now:
index 0 -> 0 correct
index 1 -> 1 correct
index 2 -> 3 incorrect

Answer = 2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 7. Example 2: Find All Numbers Disappeared in an Array

## Problem

Array length `n` hai aur numbers `1` se `n` tak hone chahiye.

Kuch numbers missing hain. Saare missing numbers return karo.

```text
nums =[1][2][3][4][7][8]
Output:[5][6]
```

## Why Cyclic Sort?

Numbers `1` se `n` hain.

Value `v` ka correct index `v - 1` hai.

Cyclic sort ke baad jo index apne correct value se match nahi karega, wahan missing number hoga.

## Solution

```java
public List<Integer> findDisappearedNumbers(int[] nums) {
    int i = 0;

    while (i < nums.length) {
        int correctIndex = nums[i] - 1;

        if (nums[i] != nums[correctIndex]) {
            swap(nums, i, correctIndex);
        } else {
            i++;
        }
    }

    List<Integer> missing = new ArrayList<>();

    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) {
            missing.add(i + 1);
        }
    }

    return missing;
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Dry Run

```text
nums =[1][2][3][4][7][8]

After cyclic sort:
nums =[1][2][3][4][7][8]

Check:
index 0 -> value 1 correct
index 1 -> value 2 correct
index 2 -> value 3 correct
index 3 -> value 4 correct
index 4 -> value 3 incorrect -> missing 5
index 5 -> value 2 incorrect -> missing 6
index 6 -> value 7 correct
index 7 -> value 8 correct

Missing =[5][6]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\), excluding output list

---

# 8. Example 3: Find the Duplicate Number

## Problem

Array mein `n + 1` numbers hain range `[1, n]` ke. Ek number duplicate hai. Wo nikalo.

```text
nums =[1][2][3][4]
Output: 2
```

## Why Cyclic Sort?

Numbers `1` se `n` hain.

Value `v` ka correct index `v - 1` hai.

Agar kisi correct index par already wahi value present hai, to current value duplicate hai.

## Solution

```java
public int findDuplicate(int[] nums) {
    int i = 0;

    while (i < nums.length) {
        int correctIndex = nums[i] - 1;

        if (nums[i] != nums[correctIndex]) {
            swap(nums, i, correctIndex);
        } else {
            if (i != correctIndex) {
                return nums[i];
            }

            i++;
        }
    }

    return -1;
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Dry Run

```text
nums =[1][2][3][4]

i = 0:
nums = 1
correctIndex = 0
already correct
i++

i = 1:
nums = 3
correctIndex = 2
swap

nums =[1][2][3][4]

i = 1:
nums = 4
correctIndex = 3
swap

nums =[1][2][3][4]

i = 1:
nums = 2
correctIndex = 1
correct
i++

i = 2:
correct
i++

i = 3:
correct
i++

i = 4:
nums = 2
correctIndex = 1
nums == nums
i != correctIndex

Duplicate = 2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 9. Example 4: Set Mismatch

## Problem

Array mein numbers `1` se `n` hone chahiye.

Ek number duplicate hai aur ek number missing hai.

Dono return karo.

```text
nums =[1][2][4]
Output:[2][3]
Explanation: 2 is duplicated, 3 is missing
```

## Why Cyclic Sort?

Cyclic sort ke baad:

- Jo index par duplicate value hai, wo duplicated number hai.
- Jo index apne correct value se match nahi karta, wahan missing number hai.

## Solution

```java
public int[] findErrorNums(int[] nums) {
    int i = 0;

    while (i < nums.length) {
        int correctIndex = nums[i] - 1;

        if (nums[i] != nums[correctIndex]) {
            swap(nums, i, correctIndex);
        } else {
            i++;
        }
    }

    int[] ans = new int;

    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) {
            ans = nums[i];
            ans = i + 1;
        }
    }

    return ans;
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Dry Run

```text
nums =[1][2][4]

After cyclic sort:
nums =[2][4][1]

Check:
index 0 -> 1 correct
index 1 -> 2 correct
index 2 -> 2 incorrect
index 3 -> 4 correct

At index 2:
nums = 2 -> duplicate
index + 1 = 3 -> missing

Answer =[2][3]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\), excluding output

---

# 10. Example 5: First Missing Positive

## Problem

Unsorted integer array mein smallest missing positive integer nikalo.

```text
nums = [3, 4, -1, 1]
Output: 2
```

```text
nums =[7][8][9][11][12]
Output: 1
```

## Why Cyclic Sort?

Hum sirf positive numbers ko apne correct position par rakh sakte hain.

```text
Value v -> correct index v - 1
```

Negative numbers, zero, aur `n` se bade numbers ignore karte hain.

## Solution

```java
public int firstMissingPositive(int[] nums) {
    int i = 0;

    while (i < nums.length) {
        int correctIndex = nums[i] - 1;

        if (nums[i] > 0 &&
            nums[i] <= nums.length &&
            nums[i] != nums[correctIndex]) {

            swap(nums, i, correctIndex);
        } else {
            i++;
        }
    }

    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) {
            return i + 1;
        }
    }

    return nums.length + 1;
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Why Ignore Invalid Values?

Sirf values `1` se `n` tak relevant hain.

Example:

```text
nums =[7][8][9][11][12]
```

Answer `1` hoga, kyunki `1` missing hai.

Values `7, 8, 9, 11, 12` kisi correct index par place nahi ho sakti.

## Dry Run

```text
nums = [3, 4, -1, 1]

i = 0:
nums = 3
correctIndex = 2
swap

nums = [-1, 4, 3, 1]

i = 0:
nums = -1
invalid
i++

i = 1:
nums = 4
correctIndex = 3
swap

nums = [-1, 1, 3, 4]

i = 1:
nums = 1
correctIndex = 0
swap

nums = [1, -1, 3, 4]

i = 1:
nums = -1
invalid
i++

i = 2:
nums = 3
correctIndex = 2
correct
i++

i = 3:
nums = 4
correctIndex = 3
correct
i++

Final:
nums = [1, -1, 3, 4]

index 0 -> 1 correct
index 1 -> -1 incorrect

Answer = 2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 11. Cyclic Sort vs Normal Sorting

| Feature | Cyclic Sort | Normal Sorting |
|---|---|---|
| Time | \(O(n)\) | \(O(n \log n)\) |
| Space | \(O(1)\) | Usually \(O(1)\) or \(O(n)\) |
| Works when | Values map to indices | Any values |
| Main use | Missing/duplicate numbers | General ordering |
| Stability | No | Depends on algorithm |
| Example | First Missing Positive | Sort array |

---

# 12. Common Mistakes

## Mistake 1: Wrong Index Formula

### Numbers `1` to `n`

```java
int correctIndex = nums[i] - 1;
```

### Numbers `0` to `n - 1`

```java
int correctIndex = nums[i];
```

Ye confuse mat karo.

---

## Mistake 2: Duplicate Par Infinite Loop

Galat:

```java
if (nums[i] != correctIndexValue) {
    swap();
}
```

Correct:

```java
if (nums[i] != nums[correctIndex]) {
    swap();
}
```

Duplicate case mein dono values same hoti hain, isliye swap nahi karna chahiye.

---

## Mistake 3: Out-of-Range Values Ko Place Karne Ki Koshish Karna

First Missing Positive mein:

```java
if (nums[i] > 0 && nums[i] <= nums.length)
```

Ye check zaroori hai.

Negative numbers, zero, aur `n` se bade numbers ko place nahi kar sakte.

---

## Mistake 4: Har Swap Ke Baad `i++` Karna

Galat:

```java
swap(nums, i, correctIndex);
i++;
```

Correct:

```java
swap(nums, i, correctIndex);
// i ko same rakho
```

Kyunki nayi value `i` par aayi hai, use bhi correct position par bhejna hai.

---

## Mistake 5: Final Check Galat Karna

### Numbers `1` to `n`

```java
if (nums[i] != i + 1)
```

### Numbers `0` to `n - 1`

```java
if (nums[i] != i)
```

---

# 13. Cyclic Sort Decision Flow

```text
Problem padho
      |
      v
Kya numbers limited range mein hain?
      |
      +-- No --> Cyclic Sort probably nahi
      |
      +-- Yes
          |
          v
Kya numbers 1 to n hain?
      |
      +-- Yes --> correctIndex = value - 1
      |
      +-- Kya numbers 0 to n-1 hain?
              |
              +-- Yes --> correctIndex = value
              |
              +-- Missing / duplicate dhundhna hai?
                      |
                      +-- Yes --> Cyclic Sort
```

---

# 14. Important Problems

## Easy

- Missing Number
- Find All Numbers Disappeared in an Array
- Set Mismatch

## Medium

- Find the Duplicate Number
- Find All Duplicates in an Array
- First Missing Positive
- Find the Smallest Missing Positive Number

## Hard

- First Missing Positive
- Couples Holding Hands
- Minimum Swaps to Sort

---

# 15. One-Line Recognition

> **Agar array mein numbers 1 se n ya 0 se n-1 hain, aur missing/duplicate number O(n) time + O(1) space mein dhundhna hai — Cyclic Sort use karo.**

Cyclic Sort ka main power ye hai ki har value ko uske natural index par place karke hum sorting ko \(O(n \log n)\) se \(O(n)\) tak optimize kar dete hain.