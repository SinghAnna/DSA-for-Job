# 🚀 DSA Pattern 21 — In-place Reversal

> **Goal:** In-place Reversal ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, full reversal, partial reversal, K-group reversal, reorder list, palindrome, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. In-place Reversal Kya Hai?

In-place Reversal ka matlab hai:

```text
Linked list ya array ko reverse karna,
bina extra array/list banaye.
```

Linked list mein hum sirf pointers change karte hain.

Example:

```text
Before:
1 -> 2 -> 3 -> 4 -> 5

After:
5 -> 4 -> 3 -> 2 -> 1
```

Extra linked list nahi banate.

Sirf `next` pointers reverse karte hain.

---

## 2. In-place Reversal Kyun Use Karte Hain?

Main reasons:

1. **Extra space save karna** — \(O(1)\) space solution.
2. **Linked list ko reverse order mein process karna**.
3. **Palindrome check** — second half reverse karke compare karna.
4. **Reorder list** — first half aur reversed second half merge karna.
5. **Reverse nodes in K groups** — batch-wise reversal.
6. **Rotate list** — reversal trick se rotation.
7. **Production systems mein memory optimization**.

### Core Intuition

> Har node ka `next` pointer previous node ki taraf point karwa do.

```text
Before:
prev -> curr -> next

After:
prev <- curr <- next
```

---

## 3. Real-World Production Examples

| Real System | In-place Reversal Use |
|---|---|
| Browser History | Backward navigation |
| Undo / Redo System | Reverse action sequence |
| Music Player | Reverse playlist |
| Git-like Systems | Reverse commit traversal |
| Message Queue Replay | Reverse event processing |
| Blockchain | Reverse block traversal |
| LRU Cache | Reverse frequency/recency order |
| Text Editor | Reverse line/word order |
| Payment Ledger | Reverse transaction history |
| API Pagination | Reverse page order |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Tumhare chat application mein messages latest-to-oldest order mein dikhane hain, but DB se oldest-to-latest aa rahe hain. Memory optimize kaise karoge?”

Answer:

```text
Linked list of messages maintain karo.
In-place reversal se order reverse karo.
Extra message list banane ki zaroorat nahi.
```

Ya:

> “Undo-redo feature mein recent actions ko reverse order mein process karna hai. Kaise karoge?”

Answer:

```text
Action history ko linked list mein maintain karo.
Undo par reverse traversal / in-place reversal use karo.
```

---

## 4. Kaise Pata Chalega Ki In-place Reversal Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible In-place Reversal |
|---|---|
| Reverse linked list | Direct reversal |
| Reverse between positions | Partial reversal |
| Reverse nodes in K groups | Batch reversal |
| Reorder list | Reverse second half |
| Palindrome linked list | Reverse second half |
| Rotate list | Reversal algorithm |
| O(1) space required | In-place reversal |
| Reverse subarray | Two pointers reversal |

### Common Keywords

- Reverse
- In-place
- Rotate
- Reorder
- Palindrome
- K groups
- Reverse between
- Reverse sublist

### Mental Checklist

1. Kya linked list ya array reverse karna hai?  
2. Kya extra space allowed nahi hai?  
3. Kya sirf part reverse karna hai?  
4. Kya palindrome check karna hai?  
5. Kya list ko reorder/rotate karna hai?  

Agar 2–3 answers **yes** hain, to In-place Reversal try karo.

---

# 5. Linked List Reversal Ka Core Idea

Three pointers use karte hain:

```text
prev
curr
next
```

Steps:

```text
1. next = curr.next
2. curr.next = prev
3. prev = curr
4. curr = next
```

Visual:

```text
Before:
prev -> curr -> next

Step 1:
prev -> curr    next

Step 2:
prev <- curr    next

Step 3:
Move prev and curr forward
```

---

# 6. Template — Reverse Entire Linked List

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        ListNode next = curr.next;

        curr.next = prev;

        prev = curr;
        curr = next;
    }

    return prev;
}
```

### Why Return `prev`?

End mein:

```text
curr = null
prev = last node
```

Last node hi reversed list ka head hota hai.

---

# 7. Example 1: Reverse Linked List

## Problem

Linked list ko reverse karo.

```text
Input:
1 -> 2 -> 3 -> 4 -> 5

Output:
5 -> 4 -> 3 -> 2 -> 1
```

## Brute Force

Extra list banao:

```java
List<Integer> values = new ArrayList<>();

ListNode curr = head;

while (curr != null) {
    values.add(curr.val);
    curr = curr.next;
}

// Reverse values and rebuild list
```

Time: \(O(n)\)  
Space: \(O(n)\)

## Why In-place Reversal?

Sirf pointers change karne hain.

Space \(O(1)\) chahiye.

## Solution

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        ListNode next = curr.next;

        curr.next = prev;

        prev = curr;
        curr = next;
    }

    return prev;
}
```

## Dry Run

```text
Input:
1 -> 2 -> 3 -> 4 -> 5

Initial:
prev = null
curr = 1

Step 1:
next = 2
1 -> null
prev = 1
curr = 2

List:
1
2 -> 3 -> 4 -> 5

Step 2:
next = 3
2 -> 1
prev = 2
curr = 3

List:
2 -> 1
3 -> 4 -> 5

Step 3:
next = 4
3 -> 2
prev = 3
curr = 4

List:
3 -> 2 -> 1
4 -> 5

Step 4:
next = 5
4 -> 3
prev = 4
curr = 5

List:
4 -> 3 -> 2 -> 1
5

Step 5:
next = null
5 -> 4
prev = 5
curr = null

Final:
5 -> 4 -> 3 -> 2 -> 1
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 8. Example 2: Reverse Linked List II

## Problem

Linked list ko position `left` se `right` tak reverse karo.

```text
Input:
1 -> 2 -> 3 -> 4 -> 5
left = 2
right = 4

Output:
1 -> 4 -> 3 -> 2 -> 5
```

## Why Partial Reversal?

Poori list reverse nahi karni.

Sirf middle part reverse karna hai.

## Approach

1. `dummy` node banao.
2. `left - 1` steps move karke `prevBefore` dhundo.
3. `left` se `right` tak reverse karo.
4. Connections fix karo.

## Solution

```java
public ListNode reverseBetween(ListNode head, int left, int right) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode prev = dummy;

    for (int i = 1; i < left; i++) {
        prev = prev.next;
    }

    ListNode curr = prev.next;

    for (int i = 0; i < right - left; i++) {
        ListNode next = curr.next;

        curr.next = next.next;
        next.next = prev.next;
        prev.next = next;
    }

    return dummy.next;
}
```

## Dry Run

```text
Input:
1 -> 2 -> 3 -> 4 -> 5
left = 2
right = 4

dummy -> 1 -> 2 -> 3 -> 4 -> 5

Move prev to node before left:
prev = 1
curr = 2

Reverse loop:

Iteration 1:
next = 3
curr.next = next.next -> 2 -> 4
next.next = prev.next -> 3 -> 2
prev.next = next -> 1 -> 3

List:
1 -> 3 -> 2 -> 4 -> 5

Iteration 2:
curr = 2
next = 4

curr.next = next.next -> 2 -> 5
next.next = prev.next -> 4 -> 3
prev.next = next -> 1 -> 4

List:
1 -> 4 -> 3 -> 2 -> 5

Final:
1 -> 4 -> 3 -> 2 -> 5
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 9. Example 3: Reverse Nodes in k-Group

## Problem

Linked list ko `k` size ke groups mein reverse karo.

Agar last group mein `k` se kam nodes hain, to use as-it-is rehne do.

```text
Input:
1 -> 2 -> 3 -> 4 -> 5
k = 2

Output:
2 -> 1 -> 4 -> 3 -> 5
```

```text
Input:
1 -> 2 -> 3 -> 4 -> 5
k = 3

Output:
3 -> 2 -> 1 -> 4 -> 5
```

## Why Batch Reversal?

Har `k` nodes ka group reverse karna hai.

Pehle check karo ki group mein `k` nodes hain ya nahi.

## Solution

```java
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode prevGroupEnd = dummy;

    while (true) {
        ListNode groupStart = prevGroupEnd.next;

        ListNode groupEnd = prevGroupEnd;

        for (int i = 0; i < k; i++) {
            groupEnd = groupEnd.next;

            if (groupEnd == null) {
                return dummy.next;
            }
        }

        ListNode nextGroupStart = groupEnd.next;

        ListNode prev = nextGroupStart;
        ListNode curr = groupStart;

        while (curr != nextGroupStart) {
            ListNode next = curr.next;

            curr.next = prev;

            prev = curr;
            curr = next;
        }

        prevGroupEnd.next = groupEnd;
        prevGroupEnd = groupStart;
    }
}
```

## Dry Run

```text
Input:
1 -> 2 -> 3 -> 4 -> 5
k = 2

dummy -> 1 -> 2 -> 3 -> 4 -> 5

Group 1:
1 -> 2

Reverse:
2 -> 1

Connect:
dummy -> 2 -> 1 -> 3 -> 4 -> 5

Group 2:
3 -> 4

Reverse:
4 -> 3

Connect:
dummy -> 2 -> 1 -> 4 -> 3 -> 5

Group 3:
Only 5 node
k = 2 required
Not enough nodes

Return:
2 -> 1 -> 4 -> 3 -> 5
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 10. Example 4: Reorder List

## Problem

Linked list ko reorder karo:

```text
L0 -> L1 -> L2 -> ... -> Ln
```

Into:

```text
L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
```

```text
Input:
1 -> 2 -> 3 -> 4 -> 5

Output:
1 -> 5 -> 2 -> 4 -> 3
```

## Why Reverse Second Half?

Steps:

1. Fast & Slow se middle dhundo.
2. Second half reverse karo.
3. Dono halves merge karo.

## Solution

```java
public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
        return;
    }

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    ListNode secondHalf = reverse(slow.next);
    slow.next = null;

    ListNode firstHalf = head;

    while (secondHalf != null) {
        ListNode temp1 = firstHalf.next;
        ListNode temp2 = secondHalf.next;

        firstHalf.next = secondHalf;
        secondHalf.next = temp1;

        firstHalf = temp1;
        secondHalf = temp2;
    }
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
Input:
1 -> 2 -> 3 -> 4 -> 5

Middle:
slow = 3

Second half:
4 -> 5

Reverse:
5 -> 4

First half:
1 -> 2 -> 3

Merge:
1 -> 5 -> 2 -> 4 -> 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 11. Example 5: Palindrome Linked List

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

## Why Reverse Second Half?

Palindrome check ke liye:

```text
First half == Reverse of second half
```

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

    ListNode secondHalf = reverse(slow.next);
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
Input:
1 -> 2 -> 3 -> 2 -> 1

Middle:
slow = 3

Second half:
2 -> 1

Reverse:
1 -> 2

First half:
1 -> 2

Compare:
1 == 1
2 == 2

Palindrome = true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 12. Example 6: Rotate List

## Problem

Linked list ko right se `k` places rotate karo.

```text
Input:
1 -> 2 -> 3 -> 4 -> 5
k = 2

Output:
4 -> 5 -> 1 -> 2 -> 3
```

## Why Reversal?

Rotate right by `k`:

```text
1 -> 2 -> 3 -> 4 -> 5
```

Steps:

1. Reverse whole list:

```text
5 -> 4 -> 3 -> 2 -> 1
```

2. Reverse first `k` nodes:

```text
4 -> 5 -> 3 -> 2 -> 1
```

3. Reverse remaining nodes:

```text
4 -> 5 -> 1 -> 2 -> 3
```

## Solution

```java
public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
        return head;
    }

    int length = 1;
    ListNode tail = head;

    while (tail.next != null) {
        tail = tail.next;
        length++;
    }

    k = k % length;

    if (k == 0) {
        return head;
    }

    tail.next = head;

    int stepsToNewTail = length - k;
    ListNode newTail = head;

    for (int i = 1; i < stepsToNewTail; i++) {
        newTail = newTail.next;
    }

    ListNode newHead = newTail.next;

    newTail.next = null;

    return newHead;
}
```

## Dry Run

```text
Input:
1 -> 2 -> 3 -> 4 -> 5
k = 2

length = 5
k = 2 % 5 = 2

Make circular:
1 -> 2 -> 3 -> 4 -> 5 -> 1

New tail position:
length - k = 3

Move 2 steps from head:
newTail = 3

New head:
4

Break circle:
4 -> 5 -> 1 -> 2 -> 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 13. In-place Reversal vs Extra Space Reversal

| Feature | In-place Reversal | Extra Space Reversal |
|---|---|---|
| Space | \(O(1)\) | \(O(n)\) |
| Original list | Modified | Preserved |
| Speed | Fast | Slightly slower |
| Best for | Linked list | When original needed |
| Example | Reverse Linked List | Reverse using stack/array |

### Rule

```text
Original list preserve karni hai -> Extra space
O(1) space chahiye -> In-place reversal
```

---

# 14. Common Mistakes

## Mistake 1: `curr.next` Change Karne Se Pehle `next` Save Na Karna

Galat:

```java
curr.next = prev;
curr = curr.next;
```

Ab `curr.next` previous node ban gaya hai.

Correct:

```java
ListNode next = curr.next;
curr.next = prev;
curr = next;
```

---

## Mistake 2: Wrong Return Node

Reversal ke baad original `head` last node ban jata hai.

Correct return:

```java
return prev;
```

---

## Mistake 3: Partial Reversal Mein Dummy Node Bhoolna

Agar `left = 1`, to head change ho sakta hai.

Isliye dummy node use karo:

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
```

---

## Mistake 4: K-Group Mein Incomplete Group Reverse Karna

Pehle check karo:

```java
for (int i = 0; i < k; i++) {
    groupEnd = groupEnd.next;

    if (groupEnd == null) {
        return dummy.next;
    }
}
```

---

## Mistake 5: Reorder List Mein Second Half Ko Properly Disconnect Na Karna

Middle ke baad:

```java
slow.next = null;
```

Ye zaroori hai, warna cycle ban sakta hai.

---

## Mistake 6: Rotate List Mein `k % length` Na Karna

Agar:

```text
k = 100000
length = 5
```

To:

```java
k = k % length;
```

Warna unnecessary iterations hongi.

---

# 15. In-place Reversal Decision Flow

```text
Problem padho
      |
      v
Kya linked list reverse/reorder/rotate karni hai?
      |
      +-- No --> In-place Reversal probably nahi
      |
      +-- Yes
          |
          v
Kya poori list reverse karni hai?
      |
      +-- Yes --> Full Reversal
      |
      +-- Kya part reverse karna hai?
              |
              +-- Yes --> Partial Reversal
              |
              +-- Kya K groups hain?
                      |
                      +-- Yes --> K-Group Reversal
                      |
                      +-- Kya palindrome/reorder hai?
                              |
                              +-- Yes --> Reverse Second Half
```

---

# 16. Important Problems

## Easy

- Reverse Linked List
- Reverse String
- Reverse Array
- Palindrome Linked List

## Medium

- Reverse Linked List II
- Reorder List
- Rotate List
- Swap Nodes in Pairs
- Reverse Nodes in k-Group
- Remove Nth Node From End

## Hard

- Reverse Nodes in k-Group
- Reverse Linked List II
- Reorder List
- Palindrome Linked List

---

# 17. One-Line Recognition

> **Agar linked list ko reverse, partially reverse, rotate, reorder, ya palindrome check karna hai with O(1) space —