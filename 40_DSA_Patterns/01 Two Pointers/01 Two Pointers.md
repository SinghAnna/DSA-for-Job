# 🚀 DSA Pattern 01 — Two Pointers

> **Goal:** Samajhna ki Two Pointers **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Two Pointers Kya Hai?

Two Pointers ek technique hai jisme hum array, string, ya linked list ke **do indices/pointers** use karte hain.

```text
arr =[1][3][5][7][9]
       ↑           ↑
     left        right
```

Hum dono pointers ko ek rule ke basis par move karte hain:

- Dono opposite ends se center ki taraf
- Dono same direction mein
- Ek slow, ek fast

### Basic Idea

```text
Brute Force:
for i = 0 to n-1:
    for j = i+1 to n-1:
        check arr[i], arr[j]

Two Pointers:
left = 0
right = n-1

while left < right:
    compare / decide
    move left or right
```

Brute force often \(O(n^2)\) hota hai. Two Pointers ise often \(O(n)\) mein convert karta hai.

---

## 2. Two Pointers Kyun Use Karte Hain?

Main reason: **jab har possible pair check karna unnecessary ho.**

Example: sorted array mein target sum find karna.

```text
arr =[1][3][5][7][9]
target = 10
```

Brute force mein hum saare pairs check karenge:

```text
(1,3), (1,5), (1,7), (1,9)
(3,5), (3,7), (3,9)
(5,7), (5,9)
(7,9)
```

Time: \(O(n^2)\)

But array sorted hai:

```text
left = 0  -> arr[left] = 1
right = 4 -> arr[right] = 9

sum = 1 + 9 = 10
```

Answer mil gaya.

### Decision Logic

Agar:

```text
sum < target
```

To `left++` karo, kyunki sorted array mein right side ki value badi hoti hai.

Agar:

```text
sum > target
```

To `right--` karo, kyunki left side ki value chhoti hoti hai.

### Core Intuition

> Ek comparison se hum unnecessary possibilities eliminate kar dete hain.

Yahi reason hai ki Two Pointers \(O(n^2)\) ko \(O(n)\) bana deta hai.

---

## 3. Kaise Pata Chalega Ki Two Pointers Use Hoga?

Interview mein problem padhte waqt ye signals dhundo:

| Problem Signal | Possible Pattern |
|---|---|
| Array is **sorted** | Two Pointers |
| Find a **pair** with target sum | Two Pointers |
| Find **triplet/quadruple** with condition | Sort + Two Pointers |
| Check **palindrome** | Opposite-direction Two Pointers |
| Remove duplicates **in-place** | Reader-Writer Two Pointers |
| Move zeroes / partition array | Same-direction Two Pointers |
| Linked list cycle | Fast & Slow Pointers |
| Find middle of linked list | Fast & Slow Pointers |
| Container with most water | Opposite-direction Two Pointers |
| Merge two sorted arrays/lists | Two-sequence Two Pointers |

### Mental Checklist

1. Kya array/string sorted hai?  
2. Kya pair, triplet, ya two-position relation chahiye?  
3. Kya brute force \(O(n^2)\) lagega?  
4. Kya ek comparison se decide ho sakta hai ki left move kare ya right?  
5. Kya \(O(1)\) extra space chahiye?  

Agar 2–3 answers **yes** hain, to Two Pointers try karo.

---

# 4. Types of Two Pointers

## Type 1: Opposite Direction Two Pointers

Dono pointers opposite ends se start hote hain.

```text
left →               ← right
```

### Use When

- Sorted array
- Palindrome check
- Pair sum
- Container with most water
- Reverse array/string

### Template

```java
int left = 0;
int right = n - 1;

while (left < right) {
    // process arr[left] and arr[right]

    if (condition) {
        left++;
    } else {
        right--;
    }
}
```

---

## Type 2: Same Direction Two Pointers

Dono pointers left se start hote hain, but speed different hoti hai.

```text
slow →
fast →→
```

### Use When

- Remove duplicates
- Move zeroes
- Partition array
- Linked list cycle
- Find middle node

### Template

```java
int slow = 0;

for (int fast = 0; fast < n; fast++) {
    if (shouldKeep(arr[fast])) {
        arr[slow] = arr[fast];
        slow++;
    }
}
```

`fast` array ko **read** karta hai.  
`slow` correct position par **write** karta hai.

Isliye ise **Reader-Writer Two Pointers** bhi kehte hain.

---

## Type 3: Two Sequence Two Pointers

Do alag arrays/strings par ek-ek pointer chalta hai.

```text
arr1: i →
arr2: j →
```

### Use When

- Merge two sorted arrays
- Check subsequence
- Compare two strings
- Intersection of sorted arrays

### Template

```java
int i = 0, j = 0;

while (i < n && j < m) {
    if (condition) {
        i++;
    } else {
        j++;
    }
}
```

---

# 5. Example 1: Two Sum II — Sorted Array

## Problem

Sorted array `numbers` aur target `target` diya hai. Do indices find karo jinka sum `target` ho.

```text
numbers =[2][7][11][15]
target = 9

Answer =[1][2]
```

## Brute Force

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (numbers[i] + numbers[j] == target) {
            return new int[]{i + 1, j + 1};
        }
    }
}
```

Time: \(O(n^2)\)

## Why Two Pointers?

Array sorted hai.

- Agar sum chhota hai, to left badhao.
- Agar sum bada hai, to right ghatao.

## Two Pointers Solution

```java
public int[] twoSum(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length - 1;

    while (left < right) {
        int sum = numbers[left] + numbers[right];

        if (sum == target) {
            return new int[]{left + 1, right + 1};
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }

    return new int[]{-1, -1};
}
```

## Dry Run

```text
numbers =[2][7][11][15]
target = 9

left = 0, right = 3
sum = 2 + 15 = 17
17 > 9 -> right--

left = 0, right = 2
sum = 2 + 11 = 13
13 > 9 -> right--

left = 0, right = 1
sum = 2 + 7 = 9
Answer found
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 6. Example 2: Valid Palindrome

## Problem

String sirf alphanumeric characters consider karke palindrome hai ya nahi check karo.

```text
s = "A man, a plan, a canal: Panama"
Output: true
```

## Why Two Pointers?

Palindrome ka matlab:

```text
first character == last character
second character == second-last character
```

To naturally dono ends se compare karna padega.

## Solution

```java
public boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }

        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }

        if (Character.toLowerCase(s.charAt(left)) !=
            Character.toLowerCase(s.charAt(right))) {
            return false;
        }

        left++;
        right--;
    }

    return true;
}
```

## Dry Run

```text
s = "race a car"

left = 'r'
right = 'r'
match

left = 'a'
right = 'a'
match

left = 'c'
right = 'c'
match

left = 'e'
right = 'a'
mismatch -> false
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 7. Example 3: Remove Duplicates from Sorted Array

## Problem

Sorted array mein duplicates remove karo in-place. Unique count return karo.

```text
nums =[1][2][3]
Output: 3

First 3 elements:[1][2][3]
```

## Why Two Pointers?

- `fast` pointer array read karega.
- `slow` pointer batayega ki next unique element kahan likhna hai.
- Array sorted hai, isliye duplicates adjacent honge.

## Solution

```java
public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;

    int slow = 1;

    for (int fast = 1; fast < nums.length; fast++) {
        if (nums[fast] != nums[slow - 1]) {
            nums[slow] = nums[fast];
            slow++;
        }
    }

    return slow;
}
```

## Dry Run

```text
nums =[1][2][3]

slow = 1

fast = 1:
nums = 1, nums = 1
duplicate -> skip

fast = 2:
nums = 2, nums = 1
unique -> nums = 2
slow = 2

fast = 3:
nums = 2, nums = 2
duplicate -> skip

fast = 4:
nums = 3, nums = 2
unique -> nums = 3
slow = 3

Answer = 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 8. Example 4: 3Sum

## Problem

Array mein teen numbers find karo jinka sum `0` ho.

```text
nums = [-1, 0, 1, 2, -1, -4]

Output:
[
  [-1, -1, 2],
  [-1, 0, 1]
]
```

## Why Sort + Two Pointers?

Brute force:

```java
for i:
    for j:
        for k:
```

Time: \(O(n^3)\)

But agar array sort kar do:

```text
[-4, -1, -1, 0, 1, 2]
```

To ek number fix karke baaki do numbers ko Two Pointers se dhoondh sakte hain.

```text
fix nums[i]
find two numbers after i whose sum = -nums[i]
```

## Solution

```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();

    for (int i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;

        int left = i + 1;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];

            if (sum == 0) {
                ans.add(Arrays.asList(nums[i], nums[left], nums[right]));

                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;

                left++;
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
    }

    return ans;
}
```

## Why Skip Duplicates?

Agar same number repeat ho, to same triplet multiple times ban jayega.

```text
[-1, -1, 2]
```

Isliye:

```java
if (i > 0 && nums[i] == nums[i - 1]) continue;
```

## Complexity

- Sorting: \(O(n \log n)\)
- Two pointers for each `i`: \(O(n)\)
- Total: \(O(n^2)\)
- Space: \(O(1)\), excluding output

---

# 9. Example 5: Container With Most Water

## Problem

Array heights vertical lines represent karta hai. Do lines choose karo such that container holds maximum water.

```text
height =[1][2][3][4][5][6][7][8]
Output: 49
```

## Why Two Pointers?

Water capacity:

\[
\text{area} = \min(height[left], height[right]) \times (right - left)
\]

Start from widest container.

Ab width kam karni padegi, to sirf us side ka pointer move karo jiski height chhoti hai.

Kyunki:

- Agar chhoti height wali line ko retain karenge, to area kabhi improve nahi hoga.
- Badi height wali line ko rakhna better chance deta hai.

## Solution

```java
public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxWater = 0;

    while (left < right) {
        int width = right - left;
        int currentHeight = Math.min(height[left], height[right]);
        int area = width * currentHeight;

        maxWater = Math.max(maxWater, area);

        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }

    return maxWater;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 10. Example 6: Move Zeroes

## Problem

Array ke saare zeroes end mein move karo, relative order maintain karte hue.

```text
nums =[0][1][3][12]
Output:[1][3][12][0]
```

## Why Two Pointers?

- `fast`: non-zero elements dhundhega
- `slow`: non-zero elements ko correct position par likhega

## Solution

```java
public void moveZeroes(int[] nums) {
    int slow = 0;

    for (int fast = 0; fast < nums.length; fast++) {
        if (nums[fast] != 0) {
            int temp = nums[slow];
            nums[slow] = nums[fast];
            nums[fast] = temp;

            slow++;
        }
    }
}
```

## Dry Run

```text
nums =[0][1][3][12]

fast = 0: 0 -> skip

fast = 1: 1 -> swap with slow = 0
nums =[0][1][3][12]
slow = 1

fast = 2: 0 -> skip

fast = 3: 3 -> swap with slow = 1
nums =[0][1][3][12]
slow = 2

fast = 4: 12 -> swap with slow = 2
nums =[0][1][3][12]
slow = 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 11. Example 7: Linked List Cycle — Fast & Slow

## Problem

Linked list mein cycle hai ya nahi check karo.

## Why Fast & Slow?

Agar cycle hai:

- `slow` ek step move karta hai
- `fast` do steps move karta hai

Dono circular track par hain, to eventually meet karenge.

Agar cycle nahi hai, `fast` `null` par pahunch jayega.

## Solution

```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            return true;
        }
    }

    return false;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 12. Common Mistakes

## Mistake 1: Sorting Allowed Hai Ya Nahi Check Na Karna

3Sum mein sorting allowed hai, isliye sort kar sakte hain.

But agar problem keh de:

```text
Return original indices
```

To sorting original indices destroy kar degi. Phir HashMap better option ho sakta hai.

---

## Mistake 2: Wrong Pointer Movement

Sorted array mein:

```text
sum < target -> left++
sum > target -> right--
```

Ye logic ulta mat karna.

---

## Mistake 3: Infinite Loop

Har iteration mein kam se kam ek pointer move hona chahiye.

```java
while (left < right) {
    // agar koi pointer move nahi hua, infinite loop
}
```

---

## Mistake 4: Duplicates Handle Na Karna

3Sum mein duplicates skip karna zaroori hai:

```java
while (left < right && nums[left] == nums[left + 1]) left++;
while (left < right && nums[right] == nums[right - 1]) right--;
```

---

## Mistake 5: `left <= right` vs `left < right`

- Pair search / palindrome: usually `left < right`
- Binary search: usually `left <= right`

Two Pointers mein mostly:

```java
while (left < right)
```

---

# 13. Two Pointers Decision Flow

```text
Problem padho
      |
      v
Kya pair / triplet / two-position relation chahiye?
      |
      +-- No --> Two Pointers probably nahi
      |
      +-- Yes
          |
          v
Kya array sorted hai ya sort kar sakte ho?
      |
      +-- Yes --> Opposite Direction Two Pointers
      |
      +-- No, but in-place modification chahiye?
              |
              +-- Yes --> Reader-Writer Two Pointers
              |
              +-- Linked list cycle/middle?
                      |
                      +-- Yes --> Fast & Slow Pointers
```

---

# 14. Important Problems

## Easy

- Valid Palindrome
- Merge Sorted Array
- Move Zeroes
- Remove Duplicates from Sorted Array
- Squares of a Sorted Array

## Medium

- Two Sum II
- 3Sum
- 3Sum Closest
- Container With Most Water
- Sort Colors
- Remove Duplicates from Sorted Array II

## Hard

- Trapping Rain Water
- 4Sum
- Minimum Number of Moves to Make Palindrome

---

# 15. One-Line Recognition

> **Agar sorted array/string mein pair, triplet, palindrome, ya in-place rearrangement chahiye, aur brute force nested loop lage — Two Pointers try karo.**

Two Pointers ka main power ye hai ki ek comparison se hum poori range of possibilities eliminate kar dete hain, isliye \(O(n^2)\) se \(O(n)\) tak optimize hota hai.