# 🚀 DSA Pattern 22 — Dummy Node

> **Goal:** Dummy Node ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, head deletion, merge lists, remove nth node, add two numbers, partition list, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Dummy Node Kya Hai?

Dummy Node ek **fake head node** hota hai jo actual linked list ke head se pehle lagate hain.

```text
Before:
head -> 1 -> 2 -> 3

After adding dummy:
dummy -> head -> 1 -> 2 -> 3
```

Example:

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
```

Dummy node ki value usually matter nahi karti.

```java
ListNode dummy = new ListNode(0);
```

---

## 2. Dummy Node Kyun Use Karte Hain?

Main reason: **head node change hone wale cases ko easily handle karna.**

Example:

```text
Linked List:
1 -> 2 -> 3

Remove head:
1 -> 2 -> 3
```

Agar head remove karna ho, to normal approach mein head reference change karna padega.

But dummy node ke saath:

```text
dummy -> 1 -> 2 -> 3

Remove 1:
dummy -> 2 -> 3
```

Ab head delete karna bhi normal middle deletion jaisa ho jata hai.

### Core Intuition

> Dummy node ek permanent starting point deta hai, isliye head deletion aur head insertion edge cases simple ho jate hain.

---

## 3. Real-World Production Examples

| Real System | Dummy Node Use |
|---|---|
| Browser History | Remove first visited page |
| Music Playlist | Remove first song |
| Chat Application | Remove first message |
| Task Queue | Remove first job |
| LRU Cache | Remove least recently used node |
| Undo / Redo | Remove oldest action |
| Message Queue | Remove processed message |
| Linked List-based Stack | Pop head safely |
| Linked List-based Queue | Remove front element |
| Payment Ledger | Remove first transaction |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Tumhare message queue mein oldest message delete karna ho, aur queue empty ho sakti hai. Edge cases kaise handle karoge?”

Answer:

```text
Dummy head node use karenge.

dummy -> firstMessage -> secondMessage

Agar first message delete karna ho:
dummy.next = dummy.next.next

Head deletion normal deletion jaisa ban jayega.
```

Ya:

> “LRU Cache mein least recently used node remove karna hai. Doubly linked list mein dummy head/tail kyun use karte hain?”

Answer:

```text
Dummy head aur tail se first/last node deletion simple ho jata hai.
Empty list aur single node edge cases avoid hote hain.
```

---

## 4. Kaise Pata Chalega Ki Dummy Node Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Dummy Node |
|---|---|
| Head node delete karna hai | Dummy Node |
| Head node insert karna hai | Dummy Node |
| Head node change ho sakta hai | Dummy Node |
| Merge two sorted lists | Dummy Node |
| Remove nth node from end | Dummy Node |
| Add two numbers | Dummy Node |
| Partition list | Dummy Node |
| Delete duplicates | Dummy Node |
| Reverse between positions | Dummy Node |

### Common Keywords

- Remove head
- Delete first node
- Insert at head
- Merge lists
- Remove nth node
- Add two numbers
- Partition
- Reorder
- Reverse between

### Mental Checklist

1. Kya head node change ho sakta hai?  
2. Kya head deletion possible hai?  
3. Kya result ka new head different ho sakta hai?  
4. Kya merging se naya list banna hai?  
5. Kya edge cases simplify karne hain?  

Agar 2–3 answers **yes** hain, to Dummy Node try karo.

---

# 5. Dummy Node Ka Core Idea

Without dummy:

```text
head -> 1 -> 2 -> 3
```

Head delete karna ho to:

```java
head = head.next;
```

But function mein head reference change karna tricky ho sakta hai.

With dummy:

```text
dummy -> 1 -> 2 -> 3
```

Head delete karna:

```java
dummy.next = dummy.next.next;
```

Ab return:

```java
return dummy.next;
```

---

# 6. Template — Dummy Node

```java
public ListNode solve(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode current = dummy;

    while (current.next != null) {
        // process current.next

        current = current.next;
    }

    return dummy.next;
}
```

### Important

Return hamesha:

```java
return dummy.next;
```

`dummy` return nahi karna.

---

# 7. Example 1: Remove Nth Node From End

## Problem

Linked list ke end se `n`th node remove karo.

```text
Input:
1 -> 2 -> 3 -> 4 -> 5
n = 2

Output:
1 -> 2 -> 3 -> 5
```

## Why Dummy Node?

Agar remove karna ho:

```text
1 -> 2
n = 2
```

To head hi delete hoga.

```text
Output:
2
```

Dummy node se head deletion normal deletion jaisa ban jata hai.

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
Input:
1 -> 2 -> 3 -> 4 -> 5
n = 2

dummy -> 1 -> 2 -> 3 -> 4 -> 5

Move fast n + 1 = 3 steps:
fast = 4

Move both:

fast = 5, slow = 1
fast = null, slow = 2

Delete slow.next:
slow.next = slow.next.next

2 -> 4

Final:
1 -> 2 -> 3 -> 5
```

### Head Deletion Case

```text
Input:
1 -> 2
n = 2

dummy -> 1 -> 2

Move fast 3 steps:
fast = null

Move both:
slow remains dummy

Delete dummy.next:
dummy.next = dummy.next.next

Final:
2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 8. Example 2: Merge Two Sorted Lists

## Problem

Do sorted linked lists ko merge karo.

```text
l1 = 1 -> 2 -> 4
l2 = 1 -> 3 -> 4

Output:
1 -> 1 -> 2 -> 3 -> 4 -> 4
```

## Why Dummy Node?

Result list ka head pehle se known nahi hota.

Dummy node se hum easily result build kar sakte hain.

## Solution

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            current.next = l1;
            l1 = l1.next;
        } else {
            current.next = l2;
            l2 = l2.next;
        }

        current = current.next;
    }

    current.next = (l1 != null) ? l1 : l2;

    return dummy.next;
}
```

## Dry Run

```text
l1 = 1 -> 2 -> 4
l2 = 1 -> 3 -> 4

dummy -> null
current = dummy

Compare 1 and 1:
1 <= 1
current.next = l1 node 1
l1 = 2
current = node 1

Compare 2 and 1:
2 > 1
current.next = l2 node 1
l2 = 3
current = node 1

Compare 2 and 3:
2 <= 3
current.next = l1 node 2
l1 = 4
current = node 2

Compare 4 and 3:
4 > 3
current.next = l2 node 3
l2 = 4
current = node 3

Compare 4 and 4:
4 <= 4
current.next = l1 node 4
l1 = null
current = node 4

l1 == null
current.next = l2 remaining node 4

Final:
1 -> 1 -> 2 -> 3 -> 4 -> 4
```

## Complexity

- Time: \(O(n + m)\)
- Space: \(O(1)\)

---

# 9. Example 3: Add Two Numbers

## Problem

Do non-empty linked lists diye hain jo numbers ko reverse order mein represent karti hain.

Dono numbers ka sum linked list return karo.

```text
l1 = 2 -> 4 -> 3  // 342
l2 = 5 -> 6 -> 4  // 465

Output:
7 -> 0 -> 8  // 807
```

## Why Dummy Node?

Result list ka head pehle se known nahi hota.

Carry bhi handle karna hai.

## Solution

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    int carry = 0;

    while (l1 != null || l2 != null || carry != 0) {
        int sum = carry;

        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }

        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }

        carry = sum / 10;

        current.next = new ListNode(sum % 10);
        current = current.next;
    }

    return dummy.next;
}
```

## Dry Run

```text
l1 = 2 -> 4 -> 3
l2 = 5 -> 6 -> 4

carry = 0

Step 1:
2 + 5 + 0 = 7
digit = 7
carry = 0

Result:
7

Step 2:
4 + 6 + 0 = 10
digit = 0
carry = 1

Result:
7 -> 0

Step 3:
3 + 4 + 1 = 8
digit = 8
carry = 0

Result:
7 -> 0 -> 8

Final:
7 -> 0 -> 8
```

## Complexity

- Time: \(O(\max(n, m))\)
- Space: \(O(\max(n, m))\)

---

# 10. Example 4: Partition List

## Problem

Linked list ko aise partition karo ki `x` se chhote nodes pehle aayein aur `x` se bade ya equal nodes baad mein.

Relative order preserve rakhna hai.

```text
Input:
1 -> 4 -> 3 -> 2 -> 5 -> 2
x = 3

Output:
1 -> 2 -> 2 -> 4 -> 3 -> 5
```

## Why Two Dummy Nodes?

Do separate lists banani hain:

```text
Before list: nodes < x
After list: nodes >= x
```

Phir dono ko connect karo.

## Solution

```java
public ListNode partition(ListNode head, int x) {
    ListNode beforeDummy = new ListNode(0);
    ListNode afterDummy = new ListNode(0);

    ListNode before = beforeDummy;
    ListNode after = afterDummy;

    ListNode current = head;

    while (current != null) {
        if (current.val < x) {
            before.next = current;
            before = before.next;
        } else {
            after.next = current;
            after = after.next;
        }

        current = current.next;
    }

    after.next = null;
    before.next = afterDummy.next;

    return beforeDummy.next;
}
```

## Dry Run

```text
Input:
1 -> 4 -> 3 -> 2 -> 5 -> 2
x = 3

Before list:
1 -> 2 -> 2

After list:
4 -> 3 -> 5

Connect:
1 -> 2 -> 2 -> 4 -> 3 -> 5
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 11. Example 5: Delete Node in a Linked List

## Problem

Delete node to delete karna hai, but head access nahi hai.

Sirf delete node ka access hai.

```text
4 -> 5 -> 1 -> 9
Delete node 5

Output:
4 -> 1 -> 9
```

## Why Copy Next Value?

Previous node access nahi hai.

Isliye:

1. Current node ki value next node ki value se replace karo.
2. Current node ka next skip karo.

## Solution

```java
public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
}
```

## Dry Run

```text
4 -> 5 -> 1 -> 9
Delete node 5

node.val = 1
node.next = node.next.next

4 -> 1 -> 9
```

## Complexity

- Time: \(O(1)\)
- Space: \(O(1)\)

---

# 12. Dummy Node vs Normal Head

| Feature | Normal Head | Dummy Node |
|---|---|---|
| Head deletion | Special case | Normal case |
| Head insertion | Special case | Normal case |
| Merge lists | Head track karna padta hai | Easy |
| Remove nth node | Head edge case | Easy |
| Code complexity | More edge cases | Cleaner |
| Return value | `head` | `dummy.next` |

### Rule

```text
Head change ho sakta hai -> Dummy Node
Result list build karni hai -> Dummy Node
Merge/partition/add numbers -> Dummy Node
```

---

# 13. Common Mistakes

## Mistake 1: `dummy` Return Kar Dena

Galat:

```java
return dummy;
```

Correct:

```java
return dummy.next;
```

---

## Mistake 2: Dummy Node Ki Value Confuse Karna

Dummy node ki value meaningful nahi hoti.

```java
ListNode dummy = new ListNode(0);
```

Ye sirf starting point hai.

---

## Mistake 3: Merge Lists Mein Remaining Nodes Bhoolna

Loop ke baad:

```java
current.next = (l1 != null) ? l1 : l2;
```

Ye zaroori hai.

---

## Mistake 4: Add Two Numbers Mein Final Carry Bhoolna

Loop condition:

```java
while (l1 != null || l2 != null || carry != 0)
```

Agar carry `1` bacha hai, to extra node banana zaroori hai.

---

## Mistake 5: Partition List Mein `after.next = null` Bhoolna

Agar ye nahi karoge, to cycle ban sakta hai:

```java
after.next = null;
```

---

## Mistake 6: Remove Nth Node Mein Fast Ko `n + 1` Steps Move Na Karna

Fast ko `n + 1` steps move karna zaroori hai, taki slow deleted node ke just pehle rahe.

```java
for (int i = 0; i <= n; i++) {
    fast = fast.next;
}
```

---

# 14. Dummy Node Decision Flow

```text
Problem padho
      |
      v
Kya head node change ho sakta hai?
      |
      +-- No --> Dummy Node optional
      |
      +-- Yes
          |
          v
Kya head delete/insert karna hai?
      |
      +-- Yes --> Dummy Node
      |
      +-- Kya merge karna hai?
              |
              +-- Yes --> Dummy Node
              |
              +-- Kya remove nth from end chahiye?
                      |
                      +-- Yes --> Dummy + Fast & Slow
                      |
                      +-- Kya partition/add numbers chahiye?
                              |
                              +-- Yes --> Dummy Node
```

---

# 15. Important Problems

## Easy

- Remove Linked List Elements
- Merge Two Sorted Lists
- Delete Node in a Linked List
- Intersection of Two Linked Lists

## Medium

- Remove Nth Node From End of List
- Add Two Numbers
- Partition List
- Odd Even Linked List
- Reorder List
- Reverse Linked List II

## Hard

- Merge K Sorted Lists
- Add Two Numbers II
- Reverse Nodes in k-Group
- Copy List with Random Pointer

---

# 16. One-Line Recognition

> **Agar linked list ka head change ho sakta hai, head deletion/insertion handle karna hai, ya nayi list build karni hai — Dummy Node use karo.**

Dummy Node ka main power ye hai ki head-related edge cases ko normal cases mein convert kar deta hai, isliye code simpler, safer aur cleaner ban jata hai.