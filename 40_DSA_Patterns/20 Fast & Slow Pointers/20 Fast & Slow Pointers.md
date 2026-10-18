# 🚀 DSA Pattern 20 — Fast & Slow Pointers

> **Goal:** Fast & Slow Pointers ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, linked list cycle, cycle start, middle node, palindrome, happy number, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Fast & Slow Pointers Kya Hai?

Fast & Slow Pointers ek technique hai jisme do pointers same direction mein move karte hain, but **different speed** se.

```text
slow -> 1 step at a time
fast -> 2 steps at a time
```

Example:

```text
Linked List:
1 -> 2 -> 3 -> 4 -> 5

slow: 1 -> 2 -> 3
fast: 1 -> 3 -> 5

Middle node = 3
```

Ye pattern mainly linked list aur cycle-based problems mein use hota hai.

---

## 2. Fast & Slow Pointers Kyun Use Karte Hain?

Main reasons:

1. **Cycle detection** — linked list mein loop hai ya nahi.
2. **Middle node** — extra length count kiye bina.
3. **Cycle start** — cycle kis node se start hota hai.
4. **Palindrome linked list** — middle dhundh kar half reverse karna.
5. **Happy number** — number cycle mein phas gaya ya `1` par pahuncha.
6. **O(1) space solution** — HashSet ki jagah.

### Core Intuition

> Agar cycle hai, to fast pointer slow pointer ko pakad lega.  
> Agar cycle nahi hai, to fast pointer `null` par pahunch jayega.

---

## 3. Real-World Production Examples

| Real System | Fast & Slow Pointers Use |
|---|---|
| Circular Linked List | Infinite loop detection |
| Job Scheduler | Circular dependency detection |
| State Machine | Infinite state cycle detection |
| Streaming System | Duplicate event cycle detection |
| Distributed Systems | Leader election loop detection |
| Blockchain | Cycle in transaction chain |
| Game Development | Infinite animation/state loop |
| Browser History | Circular navigation detection |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Tumhare job scheduler mein agar jobs circularly depend kar rahi hain, to infinite loop kaise detect karoge?”

Answer:

```text
Har job ko node maan lo.
Dependency ko edge maan lo.

Fast & Slow pointer approach se cycle detect kar sakte hain,
ya Topological Sort se bhi detect kar sakte hain.
```

Ya:

> “Linked list based queue mein agar accidentally cycle ban gayi, to system hang ho jayega. Kaise detect karoge?”

Answer:

```text
Fast & Slow pointers use karo.
Agar dono meet karein, to cycle hai.
```

---

## 4. Kaise Pata Chalega Ki Fast & Slow Pointers Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Fast & Slow |
|---|---|
| Linked list cycle | Fast & Slow |
| Cycle start node | Fast & Slow |
| Middle of linked list | Fast & Slow |
| Palindrome linked list | Fast & Slow + Reverse |
| Happy number | Fast & Slow |
| Infinite loop detection | Fast & Slow |
| O(1) space cycle detection | Fast & Slow |
| Remove nth node from end | Fast & Slow |

### Common Keywords

- Cycle
- Loop
- Middle
- Half
- Palindrome
- Infinite
- Repeated state
- Linked list

### Mental Checklist

1. Kya linked list ya sequence hai?  
2. Kya cycle detect karna hai?  
3. Kya middle node chahiye?  
4. Kya O(1) space chahiye?  
5. Kya repeated state cycle mein phas sakta hai?  

Agar 2–3 answers **yes** hain, to Fast & Slow Pointers try karo.

---

# 5. Fast & Slow Pointers Ka Core Idea

## Cycle Detection

```text
slow ek step move karta hai
fast do steps move karta hai
```

Agar cycle hai:

```text
fast slow se aage nikal jayega,
but circular path mein wapas aayega,
aur eventually dono meet karenge.
```

Agar cycle nahi hai:

```text
fast null par pahunch jayega
```

### Why Meet Guarantee?

Cycle ke andar relative distance badhta hai:

```text
Har step par fast, slow se 1 step aage badhta hai.
```

Ek time aayega jab difference `0` ho jayega.

---

# 6. Template — Linked List Cycle Detection

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

### Important

Dono pointers ko `head` se start karo.

```java
ListNode slow = head;
ListNode fast = head;
```

Agar dono ko alag start karoge, to meeting logic change ho jayega.

---

# 7. Example 1: Linked List Cycle

## Problem

Linked list mein cycle hai ya nahi check karo.

```text
1 -> 2 -> 3 -> 4 -> 2
                ↑____|
```

Output: `true`

## Brute Force — HashSet

```java
public boolean hasCycleHashSet(ListNode head) {
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

Time: \(O(n)\)  
Space: \(O(n)\)

## Why Fast & Slow?

HashSet space leta hai.

Fast & Slow Pointers \(O(1)\) space mein cycle detect karte hain.

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

## Dry Run

```text
1 -> 2 -> 3 -> 4 -> 2

slow = 1
fast = 1

Step 1:
slow = 2
fast = 3

Step 2:
slow = 3
fast = 2

Step 3:
slow = 4
fast = 4

slow == fast
Cycle found
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 8. Example 2: Linked List Cycle II — Find Cycle Start

## Problem

Cycle start node return karo. Agar cycle nahi hai, `null` return karo.

```text
1 -> 2 -> 3 -> 4 -> 5
          ↑__________|
```

Cycle starts at node `3`.

## Why This Works?

Maan lo:

```text
head se cycle start tak distance = a
cycle start se meeting point tak distance = b
meeting point se cycle start tak distance = c
```

Jab slow aur fast meet karte hain:

```text
slow distance = a + b
fast distance = a + b + b + c
```

Fast ne slow se double distance travel kiya:

```text
2(a + b) = a + 2b + c

2a + 2b = a + 2b + c

a = c
```

Matlab:

```text
head se cycle start ki distance
= meeting point se cycle start ki distance
```

Isliye ek pointer `head` se aur ek meeting point se same speed se chalao.  
Jahan dono meet karenge, wahi cycle start hai.

## Solution

```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            ListNode start = head;

            while (start != slow) {
                start = start.next;
                slow = slow.next;
            }

            return start;
        }
    }

    return null;
}
```

## Dry Run

```text
1 -> 2 -> 3 -> 4 -> 5
          ↑__________|

Cycle start = 3

Phase 1:
slow aur fast meet karte hain.

Phase 2:
start = head = 1
slow = meeting point

Move both one step:

start -> 2
slow -> ...

Eventually:
start == slow == 3

Return node 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 9. Example 3: Middle of the Linked List

## Problem

Linked list ka middle node return karo.

Agar do middle nodes hain, to second middle return karo.

```text
1 -> 2 -> 3 -> 4 -> 5
Middle = 3
```

```text
1 -> 2 -> 3 -> 4
Middle = 3
```

## Why Fast & Slow?

Fast pointer 2x speed se chalega.

Jab fast end par pahunchega, slow middle par hoga.

## Solution

```java
public ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    return slow;
}
```

## Dry Run

### Odd Length

```text
1 -> 2 -> 3 -> 4 -> 5

slow = 1
fast = 1

Step 1:
slow = 2
fast = 3

Step 2:
slow = 3
fast = 5

fast.next = null
Stop

Middle = 3
```

### Even Length

```text
1 -> 2 -> 3 -> 4

slow = 1
fast = 1

Step 1:
slow = 2
fast = 3

Step 2:
slow = 3
fast = null

Stop

Middle = 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 10. Example 4: Palindrome Linked List

## Problem

Linked list palindrome hai ya nahi check karo.

```text
1 -> 2 -> 2 -> 1
Output: true
```

```text
1 -> 2 -> 3 -> 2 -> 1
Output: true
```

```text
1 -> 2 -> 3
Output: false
```

## Why Fast & Slow?

Steps:

1. Fast & Slow se middle node dhundo.
2. Second half reverse karo.
3. Dono halves compare karo.

## Solution

```java
public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
        return true;
    }

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    ListNode secondHalf = reverse(slow);
    ListNode firstHalf = head;

    while (secondHalf != null) {
        if (firstHalf.val != secondHalf.val) {
            return false;
        }

        firstHalf = firstHalf.next;
        secondHalf = secondHalf.next;
    }

    return true;
}

private ListNode reverse(ListNode head) {
    ListNode prev = null;

    while (head != null) {
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }

    return prev;
}
```

## Dry Run

```text
1 -> 2 -> 3 -> 2 -> 1

Find middle:
slow = 3
fast = 5

Reverse second half:
1 -> 2 -> 1

Compare:
1 == 1
2 == 2
1 == 1

Palindrome = true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 11. Example 5: Happy Number

## Problem

Number ke digits ke squares ka sum repeatedly calculate karo.

Agar `1` par pahunch jaye, to happy number.

Warna cycle mein phas jayega.

```text
n = 19

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

Output: true
```

## Why Fast & Slow?

Har number ko ek node maan lo.

`getNext(n)` ek edge hai.

Agar cycle mein `1` nahi aata, to infinite loop hoga.

Fast & Slow se cycle detect karo.

## Solution

```java
public boolean isHappy(int n) {
    int slow = n;
    int fast = getNext(n);

    while (fast != 1 && slow != fast) {
        slow = getNext(slow);
        fast = getNext(getNext(fast));
    }

    return fast == 1;
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

slow = 19
fast = getNext(19) = 82

slow = 82
fast = getNext(82) = 68

slow = 68
fast = getNext(68) = 100

slow = 100
fast = getNext(100) = 1

fast == 1
return true
```

## Complexity

- Time: \(O(\log n)\) per step
- Space: \(O(1)\)

---

# 12. Example 6: Remove Nth Node From End

## Problem

Linked list ke end se `n`th node remove karo.

```text
1 -> 2 -> 3 -> 4 -> 5
n = 2

Output:
1 -> 2 -> 3 -> 5
```

## Why Fast & Slow?

Fast pointer ko pehle `n + 1` steps move karo.

Phir dono ko ek saath move karo.

Jab fast `null` par pahunchega, slow deleted node ke just pehle hoga.

## Solution

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode fast = dummy;
    ListNode slow = dummy;

    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }

    while (fast != null) {
        fast = fast.next;
        slow = slow.next;
    }

    slow.next = slow.next.next;

    return dummy.next;
}
```

## Dry Run

```text
1 -> 2 -> 3 -> 4 -> 5
n = 2

dummy -> 1 -> 2 -> 3 -> 4 -> 5

Move fast n + 1 = 3 steps:
fast = 4

Move both:

fast = 5, slow = 1
fast = null, slow = 2

slow.next = slow.next.next
2 -> 4

Final:
1 -> 2 -> 3 -> 5
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 13. Fast & Slow vs Two Pointers

| Feature | Two Pointers | Fast & Slow Pointers |
|---|---|---|
| Direction | Opposite or same | Same direction |
| Speed | Usually custom | 1x and 2x |
| Main use | Sorted array pairs | Cycle / middle |
| Data structure | Array / string | Linked list / sequence |
| Space | \(O(1)\) | \(O(1)\) |
| Example | Two Sum II | Linked List Cycle |

---

# 14. Common Mistakes

## Mistake 1: `fast.next.next` Without Null Check

Galat:

```java
fast = fast.next.next;
```

Agar `fast.next == null`, to NullPointerException.

Correct:

```java
while (fast != null && fast.next != null) {
    fast = fast.next.next;
}
```

---

## Mistake 2: Cycle Start Mein Wrong Pointer Reset

Cycle start dhundhne ke liye:

```java
ListNode start = head;
```

Meeting point se nahi.

---

## Mistake 3: Middle Node Mein Off-by-One Error

Even length mein second middle return karna hota hai:

```text
1 -> 2 -> 3 -> 4
Middle = 3
```

Fast & Slow template naturally ye handle karta hai.

---

## Mistake 4: Palindrome Mein Original List Modify Karna

Reverse karne se original list change ho jati hai.

Agar original list preserve karni hai, to copy banao.

---

## Mistake 5: Happy Number Mein HashSet Use Karna Jab O(1) Space Chahiye

HashSet solution simple hai:

```java
Set<Integer> seen = new HashSet<>();
```

But space \(O(n)\) leta hai.

Fast & Slow \(O(1)\) space deta hai.

---

# 15. Fast & Slow Decision Flow

```text
Problem padho
      |
      v
Kya linked list / sequence hai?
      |
      +-- No --> Fast & Slow probably nahi
      |
      +-- Yes
          |
          v
Kya cycle detect karna hai?
      |
      +-- Yes --> Fast & Slow Cycle Detection
      |
      +-- Kya cycle start chahiye?
              |
              +-- Yes --> Fast & Slow + Reset Pointer
              |
              +-- Kya middle chahiye?
                      |
                      +-- Yes --> Fast & Slow Middle
                      |
                      +-- Kya palindrome chahiye?
                              |
                              +-- Yes --> Middle + Reverse + Compare
```

---

# 16. Important Problems

## Easy

- Linked List Cycle
- Middle of the Linked List
- Palindrome Linked List
- Happy Number
- Merge Two Sorted Lists

## Medium

- Linked List Cycle II
- Remove Nth Node From End of List
- Reorder List
- Rotate List
- Delete Middle Node

## Hard

- Palindrome Linked List
- Reorder List
- Rotate List
- Copy List with Random Pointer

---

# 17. One-Line Recognition

> **Agar linked list ya sequence mein cycle, middle node, palindrome, ya O(1) space detection chahiye — Fast & Slow Pointers try karo.**

Fast & Slow Pointers ka main power ye hai ki bina extra space ke cycle detect karte hain, middle node nikalte hain, aur linked list ke structural problems ko \(O(n)\) time mein solve karte hain.