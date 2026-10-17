# 🚀 DSA Pattern 04 — Difference Array

> **Goal:** Samajhna ki Difference Array **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Difference Array Kya Hai?

Difference Array ek technique hai jisse hum **range updates** ko efficiently handle karte hain.

Normal approach mein agar humein range `[left, right]` ke har element mein `value` add karna ho, to loop chalana padega:

```text
for (int i = left; i <= right; i++) {
    arr[i] += value;
}
```

Time per update: \(O(n)\)

But Difference Array mein hum sirf **do positions** update karte hain:

```text
diff[left] += value
diff[right + 1] -= value
```

Phir end mein prefix sum lekar final array nikalte hain.

---

## 2. Difference Array Kyun Use Karte Hain?

Main reason: **bahut saare range updates efficiently karna.**

Example:

```text
arr =[0]

Update 1: index 1 se 3 tak +2
Update 2: index 2 se 4 tak +3
```

Brute force:

```text
Update 1:
[0][2]

Update 2:
[0][2][3][5]
```

Time: \(O(n)\) per update.

Difference Array:

```text
Update 1: [1, 3, +2]
diff += 2
diff -= 2

Update 2: [2, 4, +3]
diff += 3
diff -= 3
```

Phir prefix sum:

```text
diff = [0, 2, 3, 0, -2, -3]
prefix =[0][2][3][5]
```

Final array:

```text
[0][2][3][5]
```

### Core Intuition

> Range ke start par change ko start karo, aur range ke end ke baad us change ko cancel kar do.

---

## 3. Kaise Pata Chalega Ki Difference Array Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Difference Array Use |
|---|---|
| Multiple **range updates** | Direct Difference Array |
| Add/subtract value in range `[l, r]` | Difference Array |
| Final array after all updates chahiye | Difference Array |
| Car boarding/alighting at stops | Difference Array |
| Flight bookings between stations | Difference Array |
| Range increment/decrement operations | Difference Array |
| Query only after all updates | Difference Array |

### Mental Checklist

1. Kya multiple updates hain?  
2. Kya har update ek range `[left, right]` par apply hota hai?  
3. Kya humein final array chahiye, not intermediate answers?  
4. Kya har update add, subtract, ya fixed value change kar raha hai?  
5. Kya brute force TLE de sakta hai?  

Agar 3–4 answers **yes** hain, to Difference Array try karo.

---

# 4. Difference Array Ka Basic Idea

Maan lo array hai:

```text
arr =[0]
```

Hum range `[1, 3]` mein `+2` add karna chahte hain.

Normal update:

```text
arr =[0][2]
```

Difference Array approach:

```text
diff += 2
diff -= 2
```

```text
diff = [0, 2, 0, 0, -2, 0]
```

Ab prefix sum lo:

```text
index:     0  1  2  3  4
diff:      0  2  0  0 -2
prefix:    0  2  2  2  0
```

Final array:

```text
[0][2]
```

### Why `diff[right + 1] -= value`?

Kyunki change sirf `[left, right]` tak hona chahiye.

`right + 1` par change cancel kar dete hain, taki uske baad wale elements unaffected rahein.

---

# 5. Template — Range Add Update

```java
public int[] applyRangeUpdates(int n, int[][] updates) {
    int[] diff = new int[n + 1];

    for (int[] update : updates) {
        int left = update;
        int right = update;
        int value = update;

        diff[left] += value;
        diff[right + 1] -= value;
    }

    int[] result = new int[n];
    int runningSum = 0;

    for (int i = 0; i < n; i++) {
        runningSum += diff[i];
        result[i] = runningSum;
    }

    return result;
}
```

### Important

`diff` array ka size `n + 1` rakhte hain, kyunki:

```text
right + 1
```

`n - 1` ho sakta hai, to `right + 1 = n` ho jayega.

---

# 6. Example 1: Range Addition

## Problem

Length `n` ka zero-initialized array hai. Multiple updates diye hain.

Har update:

```text
[startIndex, endIndex, incrementValue]
```

Har update ke baad final array return karo.

```text
length = 5
updates = [,, [0, 2, -2]][1][2][3][4]

Output: [-2, 0, 3, 5, 3]
```

## Brute Force

```java
public int[] getModifiedArrayBruteForce(int length, int[][] updates) {
    int[] arr = new int[length];

    for (int[] update : updates) {
        int left = update;
        int right = update;
        int value = update;

        for (int i = left; i <= right; i++) {
            arr[i] += value;
        }
    }

    return arr;
}
```

Time: \(O(q \times n)\), where `q` = number of updates.

## Why Difference Array?

Har update sirf 2 positions ko change karti hai:

```text
diff[left] += value
diff[right + 1] -= value
```

Isliye har update \(O(1)\) mein ho jati hai.

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
    int runningSum = 0;

    for (int i = 0; i < length; i++) {
        runningSum += diff[i];
        result[i] = runningSum;
    }

    return result;
}
```

## Dry Run

```text
length = 5
updates = [,, [0, 2, -2]][1][2][3][4]

Initial diff:
[0]

Update:[1][2][3]
diff += 2
diff -= 2

diff = [0, 2, 0, 0, -2, 0]

Update:[2][3][4]
diff += 3
diff -= 3

diff = [0, 2, 3, 0, -2, -3]

Update [0, 2, -2]:
diff += -2
diff -= -2

diff = [-2, 2, 3, 2, -2, -3]
```

Prefix sum:

```text
index 0: -2
index 1: -2 + 2 = 0
index 2: 0 + 3 = 3
index 3: 3 + 2 = 5
index 4: 5 - 2 = 3
```

Final array:

```text
[-2, 0, 3, 5, 3]
```

## Complexity

- Each update: \(O(1)\)
- Final construction: \(O(n)\)
- Total: \(O(n + q)\)
- Space: \(O(n)\)

---

# 7. Example 2: Car Pooling

## Problem

Ek car mein `capacity` seats hain. Trips diya hai:

```text
trip = [numPassengers, startLocation, endLocation]
```

Check karo ki car sab trips safely complete kar sakti hai ya nahi.

```text
trips = [, ][1][2][3][5][7]
capacity = 4

Output: false
```

## Why Difference Array?

Har location par passengers change hote hain:

- `startLocation` par passengers **enter** karte hain
- `endLocation` par passengers **exit** karte hain

To ye classic range update problem hai.

## Solution

```java
public boolean carPooling(int[][] trips, int capacity) {
    int[] diff = new int[1001];

    for (int[] trip : trips) {
        int passengers = trip;
        int start = trip;
        int end = trip;

        diff[start] += passengers;
        diff[end] -= passengers;
    }

    int currentPassengers = 0;

    for (int i = 0; i < 1001; i++) {
        currentPassengers += diff[i];

        if (currentPassengers > capacity) {
            return false;
        }
    }

    return true;
}
```

## Dry Run

```text
trips = [, ][1][2][3][5][7]
capacity = 4

Trip 1:[1][2][5]
diff += 2
diff -= 2

Trip 2:[3][7]
diff += 3
diff -= 3

diff:
index 1: +2
index 3: +3
index 5: -2
index 7: -3
```

Prefix traversal:

```text
location 0: 0 passengers
location 1: 2 passengers
location 2: 2 passengers
location 3: 5 passengers
```

At location `3`:

```text
5 > capacity 4
```

Answer: `false`

## Complexity

- Time: \(O(n + m)\), where `m` is maximum location
- Space: \(O(m)\)

---

# 8. Example 3: Corporate Flight Bookings

## Problem

`n` flights hain. Bookings diya hai:

```text
booking = [first, last, seats]
```

Har flight ke liye total booked seats return karo.

```text
bookings = [,, ][1][2][3][5][10][20][25]
n = 5

Output:[10][25][45][55]
```

## Why Difference Array?

Har booking ek range update hai:

```text
flight first se last tak seats add karo
```

## Solution

```java
public int[] corpFlightBookings(int[][] bookings, int n) {
    int[] diff = new int[n + 1];

    for (int[] booking : bookings) {
        int first = booking - 1;
        int last = booking - 1;
        int seats = booking;

        diff[first] += seats;
        diff[last + 1] -= seats;
    }

    int[] answer = new int[n];
    int runningSum = 0;

    for (int i = 0; i < n; i++) {
        runningSum += diff[i];
        answer[i] = runningSum;
    }

    return answer;
}
```

## Dry Run

```text
bookings = [,, ][1][2][3][5][10][20][25]
n = 5

Convert to 0-based:
[0][1][10]
[1][2][20]
[4][25][1]

diff:
index 0: +10
index 1: +20 + 25 = +45
index 2: -10 + 20 = +10
index 3: -20
index 5: -25

diff = [10, 45, 10, -20, 0, -25]
```

Prefix sum:

```text
index 0: 10
index 1: 55
index 2: 65? 
```

Wait — dhyan do:

```text
diff = [10, 45, 10, -20, 0, -25]
```

But booking `[1, 2, 20]` means 0-based `[1, 2]`, so:

```text
diff += 20
diff -= 20
```

Booking `[2, 5, 25]` means 0-based `[1, 4]`, so:

```text
diff += 25
diff -= 25
```

Final diff:

```text
diff = 10
diff = 45
diff = 0
diff = -10
diff = 0
diff = -25
```

Prefix sum:

```text
index 0: 10
index 1: 55
index 2: 55
index 3: 45
index 4: 45
```

Final answer:

```text
[10][45][55]
```

## Complexity

- Time: \(O(n + q)\)
- Space: \(O(n)\)

---

# 9. Example 4: Shifting Letters II

## Problem

String ke characters ko range-wise shift karna hai.

```text
s = "abc"
shifts = [, ][0][1][2]

shift direction:
1 = forward
0 = backward
```

## Why Difference Array?

Har shift ek range update hai:

```text
range [start, end] mein +1 ya -1 shift
```

Hum sab shifts ko ek difference array mein collect kar sakte hain:

```text
forward shift -> +1
backward shift -> -1
```

## Solution

```java
public String shiftingLetters(String s, int[][] shifts) {
    int n = s.length();
    int[] diff = new int[n + 1];

    for (int[] shift : shifts) {
        int start = shift;
        int end = shift;
        int direction = shift;

        int value = direction == 1 ? 1 : -1;

        diff[start] += value;
        diff[end + 1] -= value;
    }

    StringBuilder sb = new StringBuilder();
    int totalShift = 0;

    for (int i = 0; i < n; i++) {
        totalShift += diff[i];

        int original = s.charAt(i) - 'a';

        int shifted = ((original + totalShift) % 26 + 26) % 26;

        sb.append((char) ('a' + shifted));
    }

    return sb.toString();
}
```

## Why Double Modulo?

```java
((original + totalShift) % 26 + 26) % 26
```

Kyunki `totalShift` negative ho sakta hai.

Example:

```text
original = 0
totalShift = -1

(0 - 1) % 26 = -1

((-1) + 26) % 26 = 25
```

So:

```text
a -> z
```

## Complexity

- Time: \(O(n + q)\)
- Space: \(O(n)\)

---

# 10. Difference Array vs Prefix Sum

| Feature | Prefix Sum | Difference Array |
|---|---|---|
| Main use | Range sum query fast nikalna | Range update fast karna |
| Build | Cumulative sum | Difference of adjacent values |
| Query | \(O(1)\) range sum | Final array after updates |
| Update | Usually not efficient | \(O(1)\) per range update |
| Final step | Direct answer | Prefix sum to build final array |
| Example | Range Sum Query | Range Addition |

### Relationship

Difference Array aur Prefix Sum **inverse** concepts hain.

```text
Difference Array -> Prefix Sum -> Final Updated Array
```

---

# 11. Common Mistakes

## Mistake 1: `diff` Array Size `n` Rakhna

Galat:

```java
int[] diff = new int[n];
```

Agar:

```text
right = n - 1
```

To:

```text
right + 1 = n
```

Index out of bounds aa jayega.

Correct:

```java
int[] diff = new int[n + 1];
```

---

## Mistake 2: Final Prefix Sum Na Chalana

Sirf `diff` update karne se final array nahi banta.

```java
diff[left] += value;
diff[right + 1] -= value;
```

Ke baad prefix sum lena zaroori hai:

```java
runningSum += diff[i];
result[i] = runningSum;
```

---

## Mistake 3: 0-Based vs 1-Based Index Confusion

Kuch problems 1-based index deti hain:

```text
bookings = [][1][2][10]
```

To pehle 0-based mein convert karo:

```java
int first = booking - 1;
int last = booking - 1;
```

---

## Mistake 4: Intermediate Queries Maangna

Difference Array best hai jab **saare updates ke baad** final array chahiye.

Agar beech-beech mein range sum query karni ho, to Segment Tree ya Binary Indexed Tree better ho sakta hai.

---

## Mistake 5: Negative Shift Handle Na Karna

Backward shift ke liye negative value use hoti hai:

```java
diff[start] += -1;
diff[end + 1] -= -1;
```

Aur modulo handle karna zaroori hai:

```java
((value % 26) + 26) % 26
```

---

# 12. Difference Array Decision Flow

```text
Problem padho
      |
      v
Kya multiple range updates hain?
      |
      +-- No --> Difference Array probably nahi
      |
      +-- Yes
          |
          v
Kya update range [left, right] par apply hota hai?
      |
      +-- No --> Difference Array probably nahi
      |
      +-- Yes
          |
          v
Kya final array chahiye after all updates?
      |
      +-- Yes --> Difference Array
      |
      +-- Intermediate range queries bhi chahiye?
              |
              +-- Yes --> Segment Tree / BIT
```

---

# 13. Important Problems

## Easy

- Running Sum of 1d Array
- Find Pivot Index

## Medium

- Range Addition
- Corporate Flight Bookings
- Car Pooling
- Shifting Letters II
- Maximum Sum of an Hourglass

## Hard

- Count Integers in Intervals
- My Calendar III
- Booking Concert Tickets in Groups

---

# 14. One-Line Recognition

> **Agar multiple range updates hain aur final array chahiye — Difference Array use karo.**

Difference Array ka main power ye hai ki ek range update ko sirf do boundary updates mein convert kar deta hai, isliye per-update time \(O(n)\) se \(O(1)\) ho jata hai.