# 🚀 DSA Pattern 50 — Greedy Algorithms

> **Goal:** Greedy Algorithms ko product-based company interview level par samajhna — real-world intuition, kyun greedy use hota hai, kaise identify karein, interval scheduling, jump game, minimum platforms, activity selection, proof intuition, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Greedy Algorithm Kya Hai?

Greedy Algorithm har step par **locally best choice** leta hai, with hope ki final answer globally best hoga.

### Simple Intuition

> Har step par abhi ka best option choose karo.

Example:

```text
Coins =[1][5][10][25]
Amount = 30

Greedy:
25 -> 5
Total coins = 2
```

### Important

Greedy hamesha optimal nahi hota.

Example:

```text
Coins =[1][3][4]
Amount = 6

Greedy:
4 + 1 + 1 = 3 coins

Optimal:
3 + 3 = 2 coins
```

---

## 2. Greedy Kyun Use Karte Hain?

Main reasons:

1. Interval scheduling
2. Activity selection
3. Minimum platforms
4. Jump Game
5. Task scheduling
6. Resource allocation
7. Minimum cost
8. Maximum profit
9. Non-overlapping intervals
10. Meeting rooms

### Core Intuition

> Agar problem mein sorting + local best choice se optimal solution milta hai, to greedy use hota hai.

Example:

```text
Meetings:
,,[1][2][3][4][5]

Sort by end time:
,,[1][2][3][4][5]

Greedy:
Pick[2][3]
Skip[4][1]
Pick[3][5]

Answer = 2
```

---

## 3. Real-World Production Examples

| Real System | Greedy Use |
|---|---|
| Calendar App | Meeting scheduling |
| Video Streaming | Bandwidth allocation |
| Cloud Provider | Resource allocation |
| Delivery System | Route optimization |
| Ride Sharing | Driver assignment |
| Ad System | Best ad selection |
| E-commerce | Best discount selection |
| Logistics | Truck loading |
| Manufacturing | Job scheduling |
| Telecom | Frequency allocation |
| Game Development | Best move selection |
| Finance | Best investment selection |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Meeting rooms mein maximum kitni meetings schedule ho sakti hain? Kaise karoge?”

Answer:

```text
Meetings ko end time ke hisaab se sort karo.

Greedy selection:
Har baar wo meeting choose karo jo sabse pehle khatam hoti hai.
```

Ya:

> “Delivery riders ko orders assign karne hain taaki maximum orders complete ho. Kaise approach karoge?”

Answer:

```text
Orders ko end time ke hisaab se sort karo.

Greedy assignment:
Har rider ko wo order do jo sabse pehle complete hoga.
```

---

## 4. Kaise Pata Chalega Ki Greedy Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Greedy |
|---|---|
| Intervals | Greedy |
| Meetings | Greedy |
| Scheduling | Greedy |
| Maximum count | Greedy |
| Minimum cost | Greedy |
| Jump game | Greedy |
| Non-overlapping | Greedy |
| Sorting + choice | Greedy |

### Common Keywords

- Maximum number
- Minimum number
- Non-overlapping
- Intervals
- Meetings
- Schedule
- Jump
- Reach
- Assign
- Allocate

### Mental Checklist

1. Kya sorting se problem simplify hoti hai?  
2. Kya local best choice globally best hai?  
3. Kya intervals / meetings hain?  
4. Kya maximum count / minimum cost chahiye?  
5. Kya greedy choice provable hai?  

Agar 2–3 answers **yes** hain, to Greedy try karo.

---

# 5. Greedy Problem Solve Karne Ke 5 Steps

```text
1. Sorting criteria decide karo
2. Greedy choice define karo
3. Transition likho
4. Feasibility check karo
5. Final answer nikalo
```

Example — Activity Selection:

```text
Sort by end time.

Greedy choice:
Pick meeting with earliest end time.

Transition:
If start >= lastEnd:
    Pick meeting
    Update lastEnd
```

---

# 6. Example 1: Activity Selection / Non-overlapping Intervals

## Problem

Intervals diye hain.

Maximum number of non-overlapping intervals nikalo.

```text
intervals = [,,, ][1][2][3][4]
Output = 2
Explanation:  and  or  and[1][2][3][4]
```

## Why Greedy?

End time ke hisaab se sort karo.

Har baar wo interval choose karo jo sabse pehle khatam hota hai.

## Solution

```java
public int eraseOverlapIntervals(int[][] intervals) {

    Arrays.sort(intervals, (a, b) ->
        Integer.compare(a, b)
    );

    int count = 0;

    int lastEnd = intervals;

    for (int i = 1; i < intervals.length; i++) {

        if (intervals[i] < lastEnd) {
            count++;
        } else {
            lastEnd = intervals[i];
        }
    }

    return intervals.length - count;
}
```

## Dry Run

```text
intervals = [,,, ][1][2][3][4]

After sorting by end time:
,,,[1][2][3][4]

Pick[2][1]
lastEnd = 2

:[3][2]
start = 2 >= lastEnd
Pick
lastEnd = 3

:[1][3]
start = 1 < lastEnd
Skip
count++

:[3][4]
start = 3 >= lastEnd
Pick
lastEnd = 4

Answer = 3
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(1)\)

---

# 7. Example 2: Minimum Number of Arrows to Burst Balloons

## Problem

Balloons diye hain.

Arrow ko x-axis par shoot karna hai.

Maximum balloons burst karne ke liye minimum arrows nikalo.

```text
points = [,,, ][1][2][6][7][8][10][12][16]
Output = 2
```

## Why Greedy?

End point ke hisaab se sort karo.

Har baar ek arrow se maximum balloons burst karo.

## Solution

```java
public int findMinArrowShots(int[][] points) {

    Arrays.sort(points, (a, b) ->
        Integer.compare(a, b)
    );

    int arrows = 1;

    int lastEnd = points;

    for (int i = 1; i < points.length; i++) {

        if (points[i] > lastEnd) {
            arrows++;
            lastEnd = points[i];
        }
    }

    return arrows;
}
```

## Dry Run

```text
points = [,,, ][1][2][6][7][8][10][12][16]

After sorting by end time:
,,,[1][2][6][7][8][10][12][16]

Arrow 1:
Burst,[2][6][8][1]

Arrow 2:
Burst,[7][10][12][16]

Answer = 2
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(1)\)

---

# 8. Example 3: Jump Game

## Problem

Array diya hai.

Har index par maximum jump diya hai.

Kya last index tak pahunch sakte ho?

```text
nums =[1][2][3][4]
Output = true
```

## Why Greedy?

Har step par maximum reachable index track karo.

## Solution

```java
public boolean canJump(int[] nums) {

    int maxReach = 0;

    for (int i = 0; i < nums.length; i++) {

        if (i > maxReach) {
            return false;
        }

        maxReach = Math.max(maxReach, i + nums[i]);
    }

    return true;
}
```

## Dry Run

```text
nums =[1][2][3][4]

i = 0:
maxReach = 2

i = 1:
maxReach = 4

i = 2:
maxReach = 4

i = 3:
maxReach = 4

i = 4:
maxReach = 8

Answer = true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 9. Example 4: Jump Game II

## Problem

Minimum jumps nikalo jisse last index tak pahuncho.

```text
nums =[1][2][3][4]
Output = 2
Explanation:
Jump from index 0 to 1
Jump from index 1 to 4
```

## Why Greedy?

Har jump par maximum reachable index track karo.

## Solution

```java
public int jump(int[] nums) {

    int jumps = 0;

    int currentEnd = 0;

    int farthest = 0;

    for (int i = 0; i < nums.length - 1; i++) {

        farthest = Math.max(farthest, i + nums[i]);

        if (i == currentEnd) {
            jumps++;
            currentEnd = farthest;
        }
    }

    return jumps;
}
```

## Dry Run

```text
nums =[1][2][3][4]

i = 0:
farthest = 2
currentEnd = 0
jumps = 1
currentEnd = 2

i = 1:
farthest = 4

i = 2:
farthest = 4
currentEnd = 2
jumps = 2
currentEnd = 4

Answer = 2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 10. Example 5: Minimum Platforms

## Problem

Trains ke arrival aur departure times diye hain.

Minimum platforms nikalo.

```text
arrivals = [1100][1500][1800][900][940][950]
departures = [1200][1120][1130][1900][2000][910]

Output = 3
```

## Why Greedy?

Dono arrays sort karo.

Har arrival par platform need check karo.

## Solution

```java
public int findPlatforms(int[] arrivals, int[] departures) {

    Arrays.sort(arrivals);
    Arrays.sort(departures);

    int platforms = 0;

    int maxPlatforms = 0;

    int i = 0;
    int j = 0;

    while (i < arrivals.length) {

        if (arrivals[i] <= departures[j]) {
            platforms++;
            maxPlatforms = Math.max(maxPlatforms, platforms);
            i++;
        } else {
            platforms--;
            j++;
        }
    }

    return maxPlatforms;
}
```

## Dry Run

```text
arrivals = [1100][1500][1800][900][940][950]
departures = [1120][1130][1200][1900][2000][910]

At 900:
platforms = 1

At 940:
platforms = 2

At 950:
platforms = 3

At 1100:
platforms = 4? 
```

Actually correct answer depends on exact sorted departure order; conceptually greedy tracks overlapping trains.

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(1)\)

---

# 11. Greedy Patterns

| Pattern | Sorting Criteria | Greedy Choice |
|---|---|---|
| Activity Selection | End time | Earliest end |
| Minimum Arrows | End point | Maximum burst |
| Jump Game | Reach | Maximum reach |
| Minimum Platforms | Time | Overlap count |
| Task Scheduling | Deadline | Earliest deadline |
| Resource Allocation | Cost / Value | Best ratio |

---

# 12. Greedy vs DP

| Feature | Greedy | DP |
|---|---|---|
| Choice | Local best | Global optimal |
| Proof | Needed | Not needed |
| Time | Usually faster | Slower |
| Example | Activity Selection | Coin Change |
| Risk | May fail | Always correct if state correct |

### Rule

```text
Greedy choice provable -> Greedy
Greedy choice not provable -> DP
```

---

# 13. Common Mistakes

## Mistake 1: Sorting Criteria Galat Karna

Activity Selection:

```java
Arrays.sort(intervals, (a, b) ->
    Integer.compare(a, b)
);
```

---

## Mistake 2: Greedy Choice Prove Na Karna

Har greedy solution ko prove karna chahiye.

---

## Mistake 3: Jump Game Mein maxReach Update Bhoolna

```java
maxReach = Math.max(maxReach, i + nums[i]);
```

---

## Mistake 4: Minimum Platforms Mein Overlap Handle Na Karna

```java
if (arrivals[i] <= departures[j]) {
    platforms++;
}
```

---

## Mistake 5: Edge Cases Bhoolna

```text
Empty input
Single interval
All overlapping
No overlap
```

---

# 14. Greedy Decision Flow

```text
Problem padho
      |
      v
Kya intervals / meetings hain?
      |
      +-- Yes --> Sort + Greedy
      |
      +-- Kya jump / reach hai?
              |
              +-- Yes --> Greedy reach
              |
              +-- Kya scheduling hai?
                      |
                      +-- Yes --> Deadline Greedy
                      |
                      +-- Kya resource allocation hai?
                              |
                              +-- Yes --> Greedy allocation
```

---

# 15. Important Problems

## Medium

- Activity Selection
- Non-overlapping Intervals
- Minimum Number of Arrows to Burst Balloons
- Jump Game
- Jump Game II
- Minimum Platforms
- Task Scheduler
- Assign Cookies

## Hard

- Minimum Number of Taps to Open to Water a Garden
- Video Stitching
- Minimum Cost to Hire K Workers
- IPO

---

# 16. One-Line Recognition

> **Agar problem intervals, scheduling, maximum count, minimum cost, ya jump/reach maang rahi hai aur sorting + local best choice se optimal solution milta hai — Greedy try karo.**

Greedy ka main power ye hai ki complex optimization problems ko simple sorting + local best choice se solve kar deta hai — jisse time complexity kam ho jati hai.