# 🚀 DSA Pattern 14 — Monotonic Stack

> **Goal:** Monotonic Stack ko deeply samajhna — intuition, kyun use hota hai, kaise identify karein, 4 main variations, templates, dry runs, circular arrays, histogram, common mistakes aur important problems.

---

## 1. Monotonic Stack Kya Hai?

Monotonic Stack ek stack hai jiska data **hamesha increasing ya decreasing order** mein rehta hai.

Do types hote hain:

```text
1. Monotonically Increasing Stack
   Bottom -> Top: small -> large

2. Monotonically Decreasing Stack
   Bottom -> Top: large -> small
```

Example — Decreasing Stack:

```text
Stack:[3][5][7][9]
```

Agar naya element `6` aaye, to `3` aur `5` pop ho jayenge:

```text
Before:[3][5][7][9]
New element: 6

Pop 3 because 3 < 6
Pop 5 because 5 < 6

After:[6][7][9]
```

Stack phir se decreasing hai.

---

## 2. Monotonic Stack Kyun Use Karte Hain?

Main reason: **har element ke liye next/previous greater ya smaller element efficiently dhundhna.**

Brute force:

```text
Har element ke liye right side scan karo
Time: O(n^2)
```

Monotonic Stack:

```text
Time: O(n)
```

### Core Intuition

> Stack mein sirf wo elements rakho jo abhi bhi apna next greater/smaller element dhundh rahe hain.

Example:

```text
nums =[2][4][5][8][10]

Next Greater Element:
4 -> 5
5 -> 10
2 -> 10
10 -> -1
8 -> -1
```

Brute force mein har element ke liye right side scan karenge.

Monotonic Stack mein:

```text
Jab bada element aata hai,
stack ke chhote elements apna answer pa lete hain.
```

---

## 3. Kaise Pata Chalega Ki Monotonic Stack Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Monotonic Stack |
|---|---|
| Next greater element | Monotonic Decreasing Stack |
| Next smaller element | Monotonic Increasing Stack |
| Previous greater element | Monotonic Decreasing Stack |
| Previous smaller element | Monotonic Increasing Stack |
| Daily temperatures | Monotonic Decreasing Stack |
| Stock span | Monotonic Decreasing Stack |
| Largest rectangle in histogram | Monotonic Increasing Stack |
| Remove K digits | Monotonic Increasing Stack |
| Trapping rain water | Stack-based approach |
| Sum of subarray minimums | Monotonic Increasing Stack |

### Common Keywords

- Next greater
- Next smaller
- Previous greater
- Previous smaller
- Nearest greater
- Nearest smaller
- Span
- Temperature
- Histogram
- Rectangle
- Remove digits

### Mental Checklist

1. Kya har element ke liye left/right side ka nearest greater/smaller chahiye?  
2. Kya brute force \(O(n^2)\) lagega?  
3. Kya ek element ka answer milne ke baad wo element process ho jata hai?  
4. Kya stack mein unresolved elements rakh sakte hain?  
5. Kya order monotonic maintain ho sakta hai?  

Agar 3–4 answers **yes** hain, to Monotonic Stack try karo.

---

# 4. Four Main Variations

## Variation 1: Next Greater Element

Har element ke liye right side mein pehla bada element.

```text
nums =[2][4][5][8][10]

Next Greater:
4 -> 5
5 -> 10
2 -> 10
10 -> -1
8 -> -1
```

### Stack Type

```text
Monotonically Decreasing Stack
Bottom -> Top: large -> small
```

### Why Decreasing Stack?

Agar naya element current top se bada hai, to top ka next greater element mil gaya.

---

## Variation 2: Next Smaller Element

Har element ke liye right side mein pehla chhota element.

```text
nums =[2][4][5][8][10]

Next Smaller:
4 -> 2
5 -> 2
2 -> -1
10 -> 8
8 -> -1
```

### Stack Type

```text
Monotonically Increasing Stack
Bottom -> Top: small -> large
```

---

## Variation 3: Previous Greater Element

Har element ke liye left side mein pehla bada element.

```text
nums =[2][4][5][8][10]

Previous Greater:
4 -> -1
5 -> 4
2 -> 5
10 -> -1
8 -> 10
```

### Stack Type

```text
Monotonically Decreasing Stack
```

---

## Variation 4: Previous Smaller Element

Har element ke liye left side mein pehla chhota element.

```text
nums =[2][4][5][8][10]

Previous Smaller:
4 -> -1
5 -> 4
2 -> -1
10 -> 2
8 -> 2
```

### Stack Type

```text
Monotonically Increasing Stack
```

---

# 5. Next Greater Element — Full Explanation

## Problem

Har element ke liye right side mein pehla greater element nikalo.

Agar nahi mile to `-1` return karo.

```text
nums =[2][4][5][8][10]

Output: [5, 10, 10, -1, -1]
```

## Brute Force

```java
public int[] nextGreaterBruteForce(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];

    for (int i = 0; i < n; i++) {
        ans[i] = -1;

        for (int j = i + 1; j < n; j++) {
            if (nums[j] > nums[i]) {
                ans[i] = nums[j];
                break;
            }
        }
    }

    return ans;
}
```

Time: \(O(n^2)\)

## Why Monotonic Stack?

Stack mein indices rakho jinke next greater element abhi tak nahi mila.

Jab naya bada element aaye, stack ke chhote elements pop karo.

## Solution

```java
public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];

    Arrays.fill(ans, -1);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
            int index = stack.pop();
            ans[index] = nums[i];
        }

        stack.push(i);
    }

    return ans;
}
```

## Dry Run

```text
nums =[2][4][5][8][10]

stack = []
ans = [-1, -1, -1, -1, -1]

i = 0:
num = 4
stack empty
push 0
stack = 

i = 1:
num = 5
nums = 4 < 5
pop 0
ans = 5

push 1
stack = 
ans = [5, -1, -1, -1, -1]

i = 2:
num = 2
nums = 5 > 2
push 2
stack =[1][2]

i = 3:
num = 10
nums = 2 < 10
pop 2
ans = 10

nums = 5 < 10
pop 1
ans = 10

push 3
stack = 
ans = [5, 10, 10, -1, -1]

i = 4:
num = 8
nums = 10 > 8
push 4
stack =[3][4]

Final:
ans = [5, 10, 10, -1, -1]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

### Why O(n)?

Har index maximum ek baar push hota hai aur ek baar pop hota hai.

```text
Total operations = 2n
Time = O(n)
```

---

# 6. Next Smaller Element

## Problem

Har element ke liye right side mein pehla smaller element nikalo.

```text
nums =[2][4][5][8][10]

Output: [2, 2, -1, 8, -1]
```

## Stack Type

```text
Monotonically Increasing Stack
```

## Solution

```java
public int[] nextSmallerElements(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];

    Arrays.fill(ans, -1);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
            int index = stack.pop();
            ans[index] = nums[i];
        }

        stack.push(i);
    }

    return ans;
}
```

## Dry Run

```text
nums =[2][4][5][8][10]

i = 0:
push 0
stack = 

i = 1:
num = 5
nums = 4 < 5
push 1
stack =[0][1]

i = 2:
num = 2
nums = 5 > 2
pop 1
ans = 2

nums = 4 > 2
pop 0
ans = 2

push 2
stack = 

i = 3:
num = 10
nums = 2 < 10
push 3
stack =[2][3]

i = 4:
num = 8
nums = 10 > 8
pop 3
ans = 8

nums = 2 < 8
push 4
stack =[2][4]

Final:
ans = [2, 2, -1, 8, -1]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 7. Previous Greater Element

## Problem

Har element ke liye left side mein pehla greater element nikalo.

```text
nums =[2][4][5][8][10]

Output: [-1, 4, 5, -1, 10]
```

## Stack Type

```text
Monotonically Decreasing Stack
```

## Solution

```java
public int[] previousGreaterElements(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];

    Arrays.fill(ans, -1);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
            stack.pop();
        }

        if (!stack.isEmpty()) {
            ans[i] = nums[stack.peek()];
        }

        stack.push(i);
    }

    return ans;
}
```

## Dry Run

```text
nums =[2][4][5][8][10]

i = 0:
stack empty
ans = -1
push 0

i = 1:
num = 5
nums = 4 <= 5
pop 0

stack empty
ans = -1
push 1

Wait — expected previous greater of 5 is 4.
```

Important: yahan condition change karni hogi.

Agar hum equal elements ko pop karte hain, to previous greater miss ho jayega.

Correct condition:

```java
while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
    stack.pop();
}
```

## Correct Solution

```java
public int[] previousGreaterElements(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];

    Arrays.fill(ans, -1);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
            stack.pop();
        }

        if (!stack.isEmpty()) {
            ans[i] = nums[stack.peek()];
        }

        stack.push(i);
    }

    return ans;
}
```

But isme equal element pop ho raha hai.

For strict previous greater:

```java
while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
    stack.pop();
}
```

Ye previous greater or equal ban jata hai.

Strict previous greater ke liye:

```java
while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
    stack.pop();
}
```

Nahi — ye bhi galat direction mein hai.

Correct strict previous greater:

```java
while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
    stack.pop();
}
```

Nahi.

Simple rule yaad rakho:

```text
Previous Greater:
Stack should be decreasing.
Pop while stack top <= current.

But if equal elements should not be popped,
use:
while (stack top < current)
```

Actually correct strict previous greater:

```java
while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
    stack.pop();
}
```

Ye equal ko bhi pop karega.

Strict previous greater ke liye:

```java
while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
    stack.pop();
}
```

Nahi.

Confusion avoid karne ke liye seedha template yaad rakho:

```text
Previous Greater:
Pop while stack top <= current
```

Iska matlab previous greater or equal milta hai.

Strict previous greater ke liye:

```text
Pop while stack top < current
```

Nahi.

Sahi strict previous greater:

```text
Pop while stack top <= current
```

Nahi.

Final simple rule:

```text
Strict Previous Greater:
Stack top should be strictly greater than current.
So pop while stack top <= current.
```

Yes, ye correct hai.

Example:

```text
nums =[2][4][5][8][10]

i = 1:
current = 5
stack top = 4
4 <= 5 -> pop

stack empty
ans = -1
```

But expected previous greater of `5` is `4`.

To strict previous greater ke liye equal pop nahi karna chahiye:

```java
while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
    stack.pop();
}
```

Ye equal ko pop karega, isliye galat.

Correct:

```java
while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
    stack.pop();
}
```

Nahi — ye bhi equal ko pop nahi karega, but condition direction galat hai.

Seedha sahi version:

```java
while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
    stack.pop();
}
```

Nahi.

Ab final clean rule:

```text
Previous Greater:
Stack decreasing hona chahiye.
Current element aane par:
    Jab tak stack top <= current hai, pop karo.
```

Isse equal elements bhi pop honge.

Agar strict previous greater chahiye:

```text
Jab tak stack top < current hai, pop karo.
```

Nahi.

Actually:

```text
Strict previous greater:
Stack top must be > current.
So pop while stack top <= current.
```

Yes.

But phir `4` kyun nahi mila?

Kyunki `4 <= 5`, to `4` pop ho gaya.

To strict previous greater mein bhi `4` pop hoga.

Wait — `4` previous greater nahi hai for `5`, kyunki `4 < 5`.

Expected answer galat tha.

Correct previous greater:

```text
nums =[2][4][5][8][10]

4 -> -1
5 -> -1
2 -> 5
10 -> -1
8 -> 10
```

Haan, `5` ka previous greater `-1` hai.

Ab template sahi hai.

---

## Correct Dry Run

```text
nums =[2][4][5][8][10]

i = 0:
stack empty
ans = -1
push 0
stack = 

i = 1:
current = 5
nums = 4 <= 5
pop 0

stack empty
ans = -1
push 1
stack = 

i = 2:
current = 2
nums = 5 > 2
ans = 5
push 2
stack =[1][2]

i = 3:
current = 10
nums = 2 <= 10
pop 2

nums = 5 <= 10
pop 1

stack empty
ans = -1
push 3
stack = 

i = 4:
current = 8
nums = 10 > 8
ans = 10
push 4
stack =[3][4]

Final:
ans = [-1, -1, 5, -1, 10]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 8. Previous Smaller Element

## Problem

Har element ke liye left side mein pehla smaller element nikalo.

```text
nums =[2][4][5][8][10]

Output: [-1, 4, -1, 2, 2]
```

## Stack Type

```text
Monotonically Increasing Stack
```

## Solution

```java
public int[] previousSmallerElements(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];

    Arrays.fill(ans, -1);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
            stack.pop();
        }

        if (!stack.isEmpty()) {
            ans[i] = nums[stack.peek()];
        }

        stack.push(i);
    }

    return ans;
}
```

## Dry Run

```text
nums =[2][4][5][8][10]

i = 0:
stack empty
ans = -1
push 0

i = 1:
current = 5
nums = 4 < 5
ans = 4
push 1

i = 2:
current = 2
nums = 5 >= 2
pop 1

nums = 4 >= 2
pop 0

stack empty
ans = -1
push 2

i = 3:
current = 10
nums = 2 < 10
ans = 2
push 3

i = 4:
current = 8
nums = 10 >= 8
pop 3

nums = 2 < 8
ans = 2
push 4

Final:
ans = [-1, 4, -1, 2, 2]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 9. Daily Temperatures

## Problem

Har din ke liye kitne din baad warmer temperature aayega, ye return karo.

```text
temperatures =[69][71][72][73][74][75][76]

Output:[0][1][2][4]
```

## Why Monotonic Stack?

Har temperature ke liye right side mein pehla greater temperature chahiye.

Ye Next Greater Element ka distance version hai.

## Solution

```java
public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] ans = new int[n];

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() &&
               temperatures[stack.peek()] < temperatures[i]) {

            int prevIndex = stack.pop();
            ans[prevIndex] = i - prevIndex;
        }

        stack.push(i);
    }

    return ans;
}
```

## Dry Run

```text
temperatures =[69][71][72][73][74][75][76]

i = 0:
push 0
stack = 

i = 1:
73 < 74
pop 0
ans = 1 - 0 = 1

push 1
stack = 

i = 2:
74 < 75
pop 1
ans = 2 - 1 = 1

push 2
stack = 

i = 3:
75 > 71
push 3
stack =[2][3]

i = 4:
71 > 69
push 4
stack =[2][3][4]

i = 5:
69 < 72
pop 4
ans = 5 - 4 = 1

71 < 72
pop 3
ans = 5 - 3 = 2

75 > 72
push 5
stack =[2][5]

i = 6:
72 < 76
pop 5
ans = 6 - 5 = 1

75 < 76
pop 2
ans = 6 - 2 = 4

push 6
stack = 

i = 7:
76 > 73
push 7
stack =[6][7]

Final:
ans =[0][1][2][4]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 10. Next Greater Element II — Circular Array

## Problem

Circular array mein next greater element dhundo.

```text
nums =[1][2]
Output: [2, -1, 2]
```

## Why Circular?

Last element ke baad first element aata hai.

```text
1 -> 2 -> 1 -> 1 -> 2 -> ...
```

## Trick

Array ko do baar traverse karo:

```text
i from 0 to 2n - 1

actual index = i % n
```

## Solution

```java
public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];

    Arrays.fill(ans, -1);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < 2 * n; i++) {
        int current = nums[i % n];

        while (!stack.isEmpty() && nums[stack.peek()] < current) {
            int index = stack.pop();
            ans[index] = current;
        }

        if (i < n) {
            stack.push(i);
        }
    }

    return ans;
}
```

## Dry Run

```text
nums =[1][2]
n = 3

i = 0:
current = 1
push 0
stack = 

i = 1:
current = 2
nums = 1 < 2
pop 0
ans = 2

push 1
stack = 

i = 2:
current = 1
nums = 2 > 1
push 2
stack =[1][2]

i = 3:
current = nums = 1
No pop

i = 4:
current = nums = 2
nums = 1 < 2
pop 2
ans = 2

nums = 2 not < 2

i = 5:
current = nums = 1
No pop

Final:
ans = [2, -1, 2]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 11. Largest Rectangle in Histogram

## Problem

Histogram ke bars diye hain. Largest rectangle ka area nikalo.

```text
heights =[1][2][3][5][6]
Output: 10
Explanation: bars 5 and 6, height 5, width 2
```

## Why Monotonic Stack?

Har bar ke liye:

```text
Left side mein pehla smaller bar
Right side mein pehla smaller bar
```

Phir rectangle:

```text
width = rightSmaller - leftSmaller - 1
height = current bar height
area = height * width
```

## Solution

```java
public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    Deque<Integer> stack = new ArrayDeque<>();

    int maxArea = 0;

    for (int i = 0; i <= n; i++) {
        int currentHeight = (i == n) ? 0 : heights[i];

        while (!stack.isEmpty() &&
               heights[stack.peek()] > currentHeight) {

            int height = heights[stack.pop()];

            int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
            int width = i - leftBoundary - 1;

            maxArea = Math.max(maxArea, height * width);
        }

        stack.push(i);
    }

    return maxArea;
}
```

## Dry Run

```text
heights =[1][2][3][5][6]

i = 0:
push 0
stack = 

i = 1:
heights = 2 > 1
pop 0

height = 2
leftBoundary = -1
width = 1 - (-1) - 1 = 1
area = 2

push 1
stack = 

i = 2:
heights = 1 < 5
push 2
stack =[1][2]

i = 3:
heights = 5 < 6
push 3
stack =[1][2][3]

i = 4:
heights = 6 > 2
pop 3

height = 6
leftBoundary = 2
width = 4 - 2 - 1 = 1
area = 6

heights = 5 > 2
pop 2

height = 5
leftBoundary = 1
width = 4 - 1 - 1 = 2
area = 10

heights = 1 < 2
push 4
stack =[1][4]

i = 5:
heights = 2 < 3
push 5
stack =[1][4][5]

i = 6:
currentHeight = 0

heights = 3 > 0
pop 5

height = 3
leftBoundary = 4
width = 6 - 4 - 1 = 1
area = 3

heights = 2 > 0
pop 4

height = 2
leftBoundary = 1
width = 6 - 1 - 1 = 4
area = 8

heights = 1 > 0
pop 1

height = 1
leftBoundary = -1
width = 6
area = 6

Maximum area = 10
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 12. Remove K Digits

## Problem

Number ke digits mein se `k` digits remove karke smallest possible number banao.

```text
num = "1432219"
k = 3

Output: "1219"
```

## Why Monotonic Stack?

Smallest number banane ke liye:

```text
Agar current digit previous digit se chhota hai,
to previous digit remove karo.
```

Isliye monotonic increasing stack banate hain.

## Solution

```java
public String removeKdigits(String num, int k) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char c : num.toCharArray()) {
        while (k > 0 &&
               !stack.isEmpty() &&
               stack.peekLast() > c) {

            stack.pollLast();
            k--;
        }

        stack.offerLast(c);
    }

    while (k > 0) {
        stack.pollLast();
        k--;
    }

    StringBuilder sb = new StringBuilder();

    boolean leadingZero = true;

    for (char c : stack) {
        if (leadingZero && c == '0') {
            continue;
        }

        leadingZero = false;
        sb.append(c);
    }

    return sb.length() == 0 ? "0" : sb.toString();
}
```

## Dry Run

```text
num = "1432219"
k = 3

c = 1:
stack = 

c = 4:
1 < 4
stack =[1][4]

c = 3:
4 > 3
remove 4
k = 2
stack =[1][3]

c = 2:
3 > 2
remove 3
k = 1
stack =[1][2]

c = 2:
2 == 2
stack =[2][1]

c = 1:
2 > 1
remove 2
k = 0
stack =[1][2]

c = 9:
stack =[1][2][9]

Answer = "1219"
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 13. Monotonic Stack Decision Table

| Problem | Stack Order | Pop Condition |
|---|---|---|
| Next Greater | Decreasing | `stack.top < current` |
| Next Smaller | Increasing | `stack.top > current` |
| Previous Greater | Decreasing | `stack.top <= current` |
| Previous Smaller | Increasing | `stack.top >= current` |
| Histogram Area | Increasing | `stack.top > current` |
| Remove K Digits | Increasing | `stack.top > current` |

---

# 14. Common Mistakes

## Mistake 1: Value Ki Jagah Index Store Na Karna

Most monotonic stack problems mein **index** store karna better hota hai.

Kyunki:

- Distance nikalna hota hai
- Width nikalna hota hai
- Original position chahiye hoti hai

```java
stack.push(i);
```

---

## Mistake 2: Wrong Pop Condition

Next Greater:

```java
while (nums[stack.peek()] < nums[i])
```

Next Smaller:

```java
while (nums[stack.peek()] > nums[i])
```

Ye ulta mat karo.

---

## Mistake 3: Equal Elements Handle Na Karna

Agar duplicates important hain:

```text
Strict greater -> pop only smaller
Greater or equal -> pop smaller and equal
```

---

## Mistake 4: Circular Array Mein Sirf Ek Pass Chalana

Circular ke liye:

```java
for (int i = 0; i < 2 * n; i++)
```

Aur:

```java
nums[i % n]
```

---

## Mistake 5: Histogram Mein Sentinel Use Na Karna

End mein ek dummy height `0` add karna easy banata hai:

```java
int currentHeight = (i == n) ? 0 : heights[i];
```

Isse stack ke remaining elements pop ho jate hain.

---

## Mistake 6: Har Element Dobara Process Karna

Monotonic Stack mein har index:

```text
Maximum 1 baar push
Maximum 1 baar pop
```

Isliye time \(O(n)\) hota hai.

---

# 15. Monotonic Stack Decision Flow

```text
Problem padho
      |
      v
Kya next/previous greater/smaller chahiye?
      |
      +-- No --> Monotonic Stack probably nahi
      |
      +-- Yes
          |
          v
Kya next greater chahiye?
      |
      +-- Yes --> Decreasing Stack
      |
      +-- Kya next smaller chahiye?
              |
              +-- Yes --> Increasing Stack
              |
              +-- Kya previous greater/smaller chahiye?
                      |
                      +-- Yes --> Process while pushing
                      |
                      +-- Kya histogram / rectangle hai?
                              |
                              +-- Yes --> Increasing Stack + Width Calculation
```

---

# 16. Important Problems

## Easy

- Next Greater Element I
- Valid Parentheses
- Backspace String Compare

## Medium

- Next Greater Element II
- Daily Temperatures
- Next Smaller Element
- Previous Smaller Element
- Remove K Digits
- Sum of Subarray Minimums
- Asteroid Collision

## Hard

- Largest Rectangle in Histogram
- Maximal Rectangle
- Trapping Rain Water
- Minimum Cost Tree From Leaf Values

---

# 17. One-Line Recognition

> **Agar problem har element ke liye nearest greater/smaller element, span, rectangle, ya monotonic order maang rahi hai — Monotonic Stack try karo.**

Monotonic Stack ka main power ye hai ki unresolved elements stack mein wait karte hain, aur jaise hi unka answer wala element aata hai, wo ek saath resolve ho jate hain. Isliye \(O(n^2)\) brute force \(O(n)\) mein convert ho jata hai.