# 🎯 Greedy Algorithms - Complete Guide

## 📚 Theory

### What is Greedy Algorithm?

A **Greedy Algorithm** is an algorithmic paradigm that builds up a solution piece by piece, always choosing the next piece that offers the most immediate benefit (locally optimal choice) with the hope of finding a global optimum.

### Greedy vs DP

## Greedy:

* Make locally optimal choice

* Never reconsider choices

* Faster but not always optimal

* Use when greedy property holds

## Dynamic Programming:

* Consider all possibilities

* May reconsider choices

* Slower but always optimal

* Use when greedy doesn't work


### When to Use Greedy?

Use greedy when:
1. **Greedy Choice Property** - Local optimum leads to global optimum
2. **Optimal Substructure** - Optimal solution contains optimal subproblems
3. Problem asks for **maximum/minimum** value
4. Can sort and pick elements greedily

### Common Greedy Problems

1. **Activity Selection** - Maximum activities
2. **Fractional Knapsack** - Maximize value
3. **Huffman Coding** - Data compression
4. **Coin Change** - Minimum coins (specific denominations)
5. **Job Sequencing** - Maximize profit
6. **Minimum Spanning Tree** - Kruskal's, Prim's
7. **Dijkstra's Algorithm** - Shortest path
8. **Interval Problems** - Merge, schedule

---

## 💻 Java Code Examples

### Problem 1: Activity Selection

**Question:** Given n activities with their start and finish times, select the maximum number of activities that can be performed by a single person.

```java
/**
 * Problem: Activity Selection
 * GeeksforGeeks: Activity Selection
 * 
 * Visual:
 * Activities: [(1,3), (2,5), (4,7), (6,9), (8,10)]
 *             (start, finish)
 * 
 * Greedy Choice: Always pick activity with earliest finish time
 * 
 * Selection Process:
 * Sort by finish time: [(1,3), (2,5), (4,7), (6,9), (8,10)]
 * 
 * Step 1: Pick (1,3) - finishes at 3
 * Step 2: Skip (2,5) - starts at 2 < 3 (conflict)
 * Step 3: Pick (4,7) - starts at 4 ≥ 3 ✓
 * Step 4: Skip (6,9) - starts at 6 < 7 (conflict)
 * Step 5: Pick (8,10) - starts at 8 ≥ 7 ✓
 * 
 * Selected: [(1,3), (4,7), (8,10)]
 * Total: 3 activities
 * 
 * Time Complexity: O(n log n) for sorting
 * Space Complexity: O(1)
 */
public class ActivitySelection {
    
    static class Activity {
        int start;
        int finish;
        
        Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
        
        @Override
        public String toString() {
            return "(" + start + "," + finish + ")";
        }
    }
    
    public static java.util.List<Activity> selectActivities(Activity[] activities) {
        int n = activities.length;
        
        // Sort by finish time
        java.util.Arrays.sort(activities, (a, b) -> Integer.compare(a.finish, b.finish));
        
        java.util.List<Activity> selected = new java.util.ArrayList<>();
        
        // Always select first activity (earliest finish)
        selected.add(activities);
        int lastFinishTime = activities.finish;
        
        // Select remaining activities
        for (int i = 1; i < n; i++) {
            // If start time >= last finish time, select this activity
            if (activities[i].start >= lastFinishTime) {
                selected.add(activities[i]);
                lastFinishTime = activities[i].finish;
            }
        }
        
        return selected;
    }
    
    // Return count only
    public static int maxActivities(Activity[] activities) {
        if (activities.length == 0) {
            return 0;
        }
        
        java.util.Arrays.sort(activities, (a, b) -> Integer.compare(a.finish, b.finish));
        
        int count = 1;
        int lastFinishTime = activities.finish;
        
        for (int i = 1; i < activities.length; i++) {
            if (activities[i].start >= lastFinishTime) {
                count++;
                lastFinishTime = activities[i].finish;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        Activity[] activities = {
            new Activity(1, 3),
            new Activity(2, 5),
            new Activity(4, 7),
            new Activity(6, 9),
            new Activity(8, 10)
        };
        
        System.out.println("Activities: " + java.util.Arrays.toString(activities));
        
        java.util.List<Activity> selected = selectActivities(activities);
        
        System.out.println("\nSelected activities: " + selected);
        System.out.println("Maximum activities: " + selected.size());
        
        // More examples
        Activity[] activities2 = {
            new Activity(3, 5),
            new Activity(1, 2),
            new Activity(4, 6),
            new Activity(1, 3),
            new Activity(5, 7),
            new Activity(3, 4),
            new Activity(6, 8)
        };
        
        System.out.println("\nActivities: " + java.util.Arrays.toString(activities2));
        System.out.println("Maximum activities: " + maxActivities(activities2));
    }
}
```

---

### Problem 2: Fractional Knapsack

**Question:** Given weights and values of n items and a knapsack capacity W, find maximum value we can put in knapsack. We can break items.

```java
/**
 * Problem: Fractional Knapsack
 * 
 * Visual:
 * Items:
 * Item 1: value=60, weight=10, ratio=6
 * Item 2: value=100, weight=20, ratio=5
 * Item 3: value=120, weight=30, ratio=4
 * 
 * Capacity W = 50
 * 
 * Greedy Choice: Pick items with highest value/weight ratio
 * 
 * Selection Process:
 * Sort by ratio (descending): [Item1(6), Item2(5), Item3(4)]
 * 
 * Step 1: Pick Item 1 completely
 *         Value: 60, Weight: 10, Remaining: 40
 * 
 * Step 2: Pick Item 2 completely
 *         Value: 60+100=160, Weight: 10+20=30, Remaining: 20
 * 
 * Step 3: Pick Item 3 partially (20/30)
 *         Value: 160 + (20/30)×120 = 160 + 80 = 240
 *         Weight: 30+20=50, Remaining: 0
 * 
 * Total Value: 240
 * 
 * Time Complexity: O(n log n) for sorting
 * Space Complexity: O(n)
 */
public class FractionalKnapsack {
    
    static class Item {
        int value;
        int weight;
        double ratio;
        
        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
        
        @Override
        public String toString() {
            return "(v=" + value + ", w=" + weight + ", r=" + String.format("%.2f", ratio) + ")";
        }
    }
    
    public static double fractionalKnapsack(Item[] items, int capacity) {
        int n = items.length;
        
        // Sort by value/weight ratio (descending)
        java.util.Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        
        double totalValue = 0.0;
        int remainingCapacity = capacity;
        
        for (Item item : items) {
            // If can take whole item
            if (item.weight <= remainingCapacity) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
            }
            // Take fraction of item
            else {
                totalValue += item.ratio * remainingCapacity;
                remainingCapacity = 0;
                break;
            }
        }
        
        return totalValue;
    }
    
    // Get detailed breakdown
    public static class KnapsackResult {
        double totalValue;
        java.util.List<String> itemsTaken;
        
        KnapsackResult(double totalValue, java.util.List<String> itemsTaken) {
            this.totalValue = totalValue;
            this.itemsTaken = itemsTaken;
        }
    }
    
    public static KnapsackResult fractionalKnapsackDetailed(Item[] items, int capacity) {
        int n = items.length;
        java.util.Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        
        double totalValue = 0.0;
        int remainingCapacity = capacity;
        java.util.List<String> itemsTaken = new java.util.ArrayList<>();
        
        for (Item item : items) {
            if (item.weight <= remainingCapacity) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
                itemsTaken.add("Take Item (v=" + item.value + ", w=" + item.weight + ") completely");
            } else {
                double fraction = (double) remainingCapacity / item.weight;
                totalValue += item.ratio * remainingCapacity;
                itemsTaken.add("Take Item (v=" + item.value + ", w=" + item.weight + ") fraction: " + 
                              String.format("%.2f", fraction));
                remainingCapacity = 0;
                break;
            }
        }
        
        return new KnapsackResult(totalValue, itemsTaken);
    }
    
    public static void main(String[] args) {
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        int capacity = 50;
        
        System.out.println("Items: " + java.util.Arrays.toString(items));
        System.out.println("Capacity: " + capacity);
        
        double maxValue = fractionalKnapsack(items, capacity);
        System.out.println("\nMaximum value: " + maxValue);
        
        // Detailed breakdown
        Item[] items2 = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        KnapsackResult result = fractionalKnapsackDetailed(items2, capacity);
        
        System.out.println("\nDetailed breakdown:");
        for (String item : result.itemsTaken) {
            System.out.println("  " + item);
        }
        System.out.println("Total value: " + result.totalValue);
    }
}
```

---

### Problem 3: Minimum Coins (Indian Currency)

**Question:** Given an amount, find minimum number of coins/notes needed to make that amount using Indian currency denominations.

```java
/**
 * Problem: Minimum Coins (Indian Currency)
 * 
 * Visual:
 * Denominations: [2000][1][2][5][10][20][50][100][500]
 * Amount: 93
 * 
 * Greedy Choice: Always pick largest denomination ≤ remaining amount
 * 
 * Selection Process:
 * Amount: 93
 * 
 * Step 1: Pick 50 (largest ≤ 93)
 *         Remaining: 93 - 50 = 43
 * 
 * Step 2: Pick 20 (largest ≤ 43)
 *         Remaining: 43 - 20 = 23
 * 
 * Step 3: Pick 20 (largest ≤ 23)
 *         Remaining: 23 - 20 = 3
 * 
 * Step 4: Pick 2 (largest ≤ 3)
 *         Remaining: 3 - 2 = 1
 * 
 * Step 5: Pick 1 (largest ≤ 1)
 *         Remaining: 1 - 1 = 0 ✓
 * 
 * Coins:[1][2][20][50]
 * Total: 5 coins
 * 
 * Time Complexity: O(n) where n is number of denominations
 * Space Complexity: O(1)
 */
public class MinimumCoins {
    
    // Indian currency denominations
    private static final int[] DENOMINATIONS = {2000, 500, 100, 50, 20, 10, 5, 2, 1};
    
    public static java.util.List<Integer> minCoins(int amount) {
        java.util.List<Integer> coins = new java.util.ArrayList<>();
        
        for (int denom : DENOMINATIONS) {
            while (amount >= denom) {
                coins.add(denom);
                amount -= denom;
            }
        }
        
        return coins;
    }
    
    // Return count only
    public static int minCoinsCount(int amount) {
        int count = 0;
        
        for (int denom : DENOMINATIONS) {
            count += amount / denom;
            amount %= denom;
        }
        
        return count;
    }
    
    // Get count of each denomination
    public static java.util.Map<Integer, Integer> coinBreakdown(int amount) {
        java.util.Map<Integer, Integer> breakdown = new java.util.LinkedHashMap<>();
        
        for (int denom : DENOMINATIONS) {
            int count = amount / denom;
            if (count > 0) {
                breakdown.put(denom, count);
                amount %= denom;
            }
        }
        
        return breakdown;
    }
    
    // For arbitrary denominations (may not work optimally for all)
    public static java.util.List<Integer> minCoinsArbitrary(int[] denominations, int amount) {
        java.util.Arrays.sort(denominations); // Sort ascending
        java.util.List<Integer> coins = new java.util.ArrayList<>();
        
        for (int i = denominations.length - 1; i >= 0; i--) {
            while (amount >= denominations[i]) {
                coins.add(denominations[i]);
                amount -= denominations[i];
            }
        }
        
        return coins;
    }
    
    public static void main(String[] args) {
        int amount = 93;
        
        System.out.println("Amount: ₹" + amount);
        
        java.util.List<Integer> coins = minCoins(amount);
        System.out.println("Coins: " + coins);
        System.out.println("Total coins: " + coins.size());
        
        // Breakdown
        System.out.println("\nBreakdown:");
        java.util.Map<Integer, Integer> breakdown = coinBreakdown(amount);
        for (java.util.Map.Entry<Integer, Integer> entry : breakdown.entrySet()) {
            System.out.println("₹" + entry.getKey() + " × " + entry.getValue());
        }
        
        // More examples
        int[] amounts = {93, 567, 1234, 2899};
        System.out.println("\nMore examples:");
        for (int amt : amounts) {
            System.out.println("₹" + amt + " → " + minCoinsCount(amt) + " coins");
        }
        
        // Arbitrary denominations (US currency)
        int[] usDenoms = {1, 5, 10, 25}; // cents
        amount = 63;
        System.out.println("\nUS Coins for " + amount + " cents: " + 
                          minCoinsArbitrary(usDenoms, amount));
    }
}
```

---

### Problem 4: Job Sequencing with Deadlines

**Question:** Given jobs with deadlines and profits, find maximum profit subset of jobs such that every job is completed by its deadline.

```java
/**
 * Problem: Job Sequencing with Deadlines
 * 
 * Visual:
 * Jobs: [(A,2,100), (B,1,19), (C,2,27), (D,1,25), (E,3,15)]
 *       (id, deadline, profit)
 * 
 * Greedy Choice: Sort by profit (descending), schedule each job at latest possible slot
 * 
 * Selection Process:
 * Sort by profit: [A(100), C(27), D(25), B(19), E(15)]
 * 
 * Max deadline = 3, so slots:[1][2][3]
 * 
 * Step 1: Job A (deadline=2, profit=100)
 *         Schedule at slot 2
 *         Slots: [_, A, _]
 * 
 * Step 2: Job C (deadline=2, profit=27)
 *         Slot 2 occupied, try slot 1
 *         Schedule at slot 1
 *         Slots: [C, A, _]
 * 
 * Step 3: Job D (deadline=1, profit=25)
 *         Slot 1 occupied, no earlier slot
 *         Skip D
 * 
 * Step 4: Job B (deadline=1, profit=19)
 *         Slot 1 occupied, no earlier slot
 *         Skip B
 * 
 * Step 5: Job E (deadline=3, profit=15)
 *         Schedule at slot 3
 *         Slots: [C, A, E]
 * 
 * Selected: [C, A, E]
 * Total Profit: 27 + 100 + 15 = 142
 * 
 * Time Complexity: O(n²) or O(n log n) with union find
 * Space Complexity: O(n)
 */
public class JobSequencing {
    
    static class Job {
        char id;
        int deadline;
        int profit;
        
        Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
        
        @Override
        public String toString() {
            return "(" + id + ", d=" + deadline + ", p=" + profit + ")";
        }
    }
    
    public static java.util.List<Job> sequenceJobs(Job[] jobs) {
        int n = jobs.length;
        
        // Sort by profit (descending)
        java.util.Arrays.sort(jobs, (a, b) -> Integer.compare(b.profit, a.profit));
        
        // Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }
        
        // Track occupied slots
        boolean[] slotOccupied = new boolean[maxDeadline + 1];
        java.util.List<Job> result = new java.util.ArrayList<>();
        
        // Schedule each job
        for (Job job : jobs) {
            // Find latest free slot for this job
            for (int j = job.deadline; j > 0; j--) {
                if (!slotOccupied[j]) {
                    slotOccupied[j] = true;
                    result.add(job);
                    break;
                }
            }
        }
        
        return result;
    }
    
    // Return total profit
    public static int maxProfit(Job[] jobs) {
        java.util.List<Job> scheduled = sequenceJobs(jobs);
        int totalProfit = 0;
        
        for (Job job : scheduled) {
            totalProfit += job.profit;
        }
        
        return totalProfit;
    }
    
    public static void main(String[] args) {
        Job[] jobs = {
            new Job('A', 2, 100),
            new Job('B', 1, 19),
            new Job('C', 2, 27),
            new Job('D', 1, 25),
            new Job('E', 3, 15)
        };
        
        System.out.println("Jobs: " + java.util.Arrays.toString(jobs));
        
        java.util.List<Job> scheduled = sequenceJobs(jobs);
        
        System.out.println("\nScheduled jobs: " + scheduled);
        System.out.println("Total profit: " + maxProfit(jobs));
        
        // More examples
        Job[] jobs2 = {
            new Job('a', 4, 20),
            new Job('b', 1, 10),
            new Job('c', 1, 40),
            new Job('d', 1, 30)
        };
        
        System.out.println("\nJobs: " + java.util.Arrays.toString(jobs2));
        System.out.println("Maximum profit: " + maxProfit(jobs2));
    }
}
```

---

### Problem 5: Merge Intervals

**Question:** Given an array of intervals where intervals[i] = [start, end], merge all overlapping intervals.

```java
/**
 * Problem: Merge Intervals
 * LeetCode: 56
 * 
 * Visual:
 * Intervals: [,,, ][1][2][3][6][8][10][15][18]
 * 
 * Greedy Choice: Sort by start time, merge overlapping
 * 
 * Merge Process:
 * Sort: [,,, ][1][2][3][6][8][10][15][18]
 * 
 * Step 1: Start with[1][3]
 * 
 * Step 2: Check[2][6]
 *         2 ≤ 3 (overlap)
 *         Merge: [1, max(3,6)] =[1][6]
 * 
 * Step 3: Check[8][10]
 *         8 > 6 (no overlap)
 *         Add  to result[1][6]
 *         Start new:[8][10]
 * 
 * Step 4: Check[15][18]
 *         15 > 10 (no overlap)
 *         Add  to result[8][10]
 *         Start new:[15][18]
 * 
 * Result: [,, ][1][6][8][10][18][15]
 * 
 * Time Complexity: O(n log n) for sorting
 * Space Complexity: O(n) for result
 */
public class MergeIntervals {
    
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        
        // Sort by start time
        java.util.Arrays.sort(intervals, (a, b) -> Integer.compare(a, b));
        
        java.util.List<int[]> result = new java.util.ArrayList<>();
        int[] currentInterval = intervals;
        result.add(currentInterval);
        
        for (int[] interval : intervals) {
            int currentEnd = currentInterval;[1]
            int nextStart = interval;
            int nextEnd = interval;[1]
            
            if (nextStart <= currentEnd) {
                // Overlapping, merge
                currentInterval = Math.max(currentEnd, nextEnd);[1]
            } else {
                // No overlap, add new interval
                currentInterval = interval;
                result.add(currentInterval);
            }
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    // Variation: Insert Interval
    // LeetCode: 57
    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        java.util.List<int[]> result = new java.util.ArrayList<>();
        int i = 0;
        int n = intervals.length;
        
        // Add all intervals before newInterval
        while (i < n && intervals[i] < newInterval) {[1]
            result.add(intervals[i]);
            i++;
        }
        
        // Merge overlapping intervals
        while (i < n && intervals[i] <= newInterval) {[1]
            newInterval = Math.min(newInterval, intervals[i]);
            newInterval = Math.max(newInterval, intervals[i]);[1]
            i++;
        }
        result.add(newInterval);
        
        // Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    // Variation: Non-overlapping Intervals (minimum to remove)
    // LeetCode: 435
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        
        // Sort by end time
        java.util.Arrays.sort(intervals, (a, b) -> Integer.compare(a, b));[1]
        
        int count = 0;
        int prevEnd = intervals;[1]
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i] < prevEnd) {
                // Overlapping, remove this interval
                count++;
            } else {
                // No overlap, update prevEnd
                prevEnd = intervals[i];[1]
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        
        System.out.println("Intervals: " + java.util.Arrays.deepToString(intervals));
        
        int[][] merged = merge(intervals);
        System.out.println("Merged: " + java.util.Arrays.deepToString(merged));
        
        // Insert interval
        int[][] intervals2 = {{1,3}, {6,9}};
        int[] newInterval = {2, 5};
        System.out.println("\nIntervals: " + java.util.Arrays.deepToString(intervals2));
        System.out.println("Insert: " + java.util.Arrays.toString(newInterval));
        int[][] result = insertInterval(intervals2, newInterval);
        System.out.println("Result: " + java.util.Arrays.deepToString(result));
        
        // Non-overlapping
        int[][] intervals3 = {{1,2}, {2,3}, {3,4}, {1,3}};
        System.out.println("\nIntervals: " + java.util.Arrays.deepToString(intervals3));
        System.out.println("Minimum to remove: " + eraseOverlapIntervals(intervals3));
    }
}
```

---

### Problem 6: Minimum Platforms

**Question:** Given arrival and departure times of trains, find minimum number of platforms required.

```java
/**
 * Problem: Minimum Platforms
 * GeeksforGeeks: Minimum Platforms
 * 
 * Visual:
 * Arrival:  [1100][1500][1800][900][940][950]
 * Departure:[1200][1120][1130][1900][2000][910]
 * 
 * Greedy Choice: Process events in time order, track maximum trains at any time
 * 
 * Process:
 * Sort both arrays:
 * Arrival:   [1100][1500][1800][900][940][950]
 * Departure: [1120][1130][1200][1900][2000][910]
 * 
 * Time   Event     Platforms  Max
 * 900    Arrive    1          1
 * 910    Depart    0          1
 * 940    Arrive    1          1
 * 950    Arrive    2          2
 * 1100   Arrive    3          3 ← Maximum
 * 1120   Depart    2          3
 * 1130   Depart    1          3
 * 1200   Depart    0          3
 * 1500   Arrive    1          3
 * 1800   Arrive    2          3
 * 1900   Depart    1          3
 * 2000   Depart    0          3
 * 
 * Minimum platforms needed: 3
 * 
 * Time Complexity: O(n log n) for sorting
 * Space Complexity: O(1)
 */
public class MinimumPlatforms {
    
    public static int findPlatforms(int[] arrival, int[] departure) {
        int n = arrival.length;
        
        // Sort both arrays
        java.util.Arrays.sort(arrival);
        java.util.Arrays.sort(departure);
        
        int platforms = 1;
        int maxPlatforms = 1;
        
        int i = 1; // Index for arrival
        int j = 0; // Index for departure
        
        while (i < n && j < n) {
            if (arrival[i] <= departure[j]) {
                // Train arrives before another departs
                // Need one more platform
                platforms++;
                i++;
            } else {
                // Train departs before another arrives
                // One platform freed
                platforms--;
                j++;
            }
            
            // Update maximum
            maxPlatforms = Math.max(maxPlatforms, platforms);
        }
        
        return maxPlatforms;
    }
    
    // Alternative: Using TreeMap
    public static int findPlatformsMap(int[] arrival, int[] departure) {
        java.util.TreeMap<Integer, Integer> timeline = new java.util.TreeMap<>();
        
        // Mark arrivals and departures
        for (int time : arrival) {
            timeline.put(time, timeline.getOrDefault(time, 0) + 1);
        }
        for (int time : departure) {
            timeline.put(time, timeline.getOrDefault(time, 0) - 1);
        }
        
        int platforms = 0;
        int maxPlatforms = 0;
        
        for (int change : timeline.values()) {
            platforms += change;
            maxPlatforms = Math.max(maxPlatforms, platforms);
        }
        
        return maxPlatforms;
    }
    
    public static void main(String[] args) {
        int[] arrival = {900, 940, 950, 1100, 1500, 1800};
        int[] departure = {910, 1200, 1120, 1130, 1900, 2000};
        
        System.out.println("Arrival:   " + java.util.Arrays.toString(arrival));
        System.out.println("Departure: " + java.util.Arrays.toString(departure));
        
        int platforms = findPlatforms(arrival, departure);
        System.out.println("\nMinimum platforms needed: " + platforms);
        
        // More examples
        int[] arr1 = {900, 940};
        int[] dep1 = {910, 1200};
        System.out.println("\nExample 2: " + findPlatforms(arr1, dep1) + " platforms");
        
        int[] arr2 = {900, 940, 950};
        int[] dep2 = {910, 920, 930};
        System.out.println("Example 3: " + findPlatforms(arr2, dep2) + " platforms");
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Minimum Coins | Greedy | Practice |
| Maximum Activities | Activity Selection | GeeksforGeeks |
| Lemonade Change | Greedy | LeetCode 860 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Fractional Knapsack | Greedy | GeeksforGeeks |
| Job Sequencing | Greedy | GeeksforGeeks |
| Merge Intervals | Sorting + Greedy | LeetCode 56 |
| Minimum Platforms | Two Pointers | GeeksforGeeks |
| Jump Game | Greedy | LeetCode 55 |
| Jump Game II | Greedy | LeetCode 45 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Minimum Number of Arrows | Greedy | LeetCode 452 |
| Candy | Greedy | LeetCode 135 |
| Patching Array | Greedy | LeetCode 330 |

---

## ✅ Key Takeaways

1. **Greedy Choice Property** - Local optimum leads to global optimum
2. **Activity Selection** - Sort by finish time
3. **Fractional Knapsack** - Sort by value/weight ratio
4. **Coin Change** - Use largest denominations first
5. **Job Sequencing** - Sort by profit, schedule at latest slot
6. **Merge Intervals** - Sort by start time, merge overlapping
7. **When NOT to use** - 0/1 Knapsack, problems where greedy fails

---

**Previous:** [Backtracking](../backtracking/01-theory.md)  
**Next:** [Sliding Window](../sliding-window/01-theory.md)