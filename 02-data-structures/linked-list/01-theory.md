# 🔗 Linked List - Complete Guide

## 📚 Theory

### What is a Linked List?

A **Linked List** is a linear data structure where elements (nodes) are connected using pointers. Unlike arrays, elements are **not stored in contiguous memory**.

### Linked List vs Array

| Property | Array | Linked List |
|----------|-------|-------------|
| Memory | Contiguous | Non-contiguous |
| Access | O(1) random access | O(n) sequential |
| Insert/Delete at beginning | O(n) | O(1) |
| Insert/Delete at end | O(n) or O(1)* | O(n) or O(1)** |
| Size | Fixed | Dynamic |
| Extra Memory | No | Yes (for pointers) |

*ArrayList: O(1) amortized  
**With tail pointer: O(1)

### Types of Linked List

1. **Singly Linked List** - Each node points to next node
2. **Doubly Linked List** - Each node points to next and previous
3. **Circular Linked List** - Last node points to first node

### Node Structure

```java
// Singly Linked List Node
class ListNode {
    int val;
    ListNode next;
    
    ListNode(int val) {
        this.val = val;
    }
    
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// Doubly Linked List Node
class DoublyListNode {
    int val;
    DoublyListNode prev;
    DoublyListNode next;
    
    DoublyListNode(int val) {
        this.val = val;
    }
}
```

### Linked List Operations Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Access | O(n) | O(1) |
| Search | O(n) | O(1) |
| Insert at head | O(1) | O(1) |
| Insert at tail | O(n) or O(1)* | O(1) |
| Insert at index i | O(n) | O(1) |
| Delete at head | O(1) | O(1) |
| Delete at tail | O(n) or O(1)* | O(1) |
| Delete at index i | O(n) | O(1) |

*With tail pointer

---

## 💻 Java Code Examples

### Problem 1: Create and Display Linked List

**Question:** Create a singly linked list and display all elements.

```java
/**
 * Problem: Create and Display Linked List
 * 
 * Visual:
 * head ->  ->  ->  ->  -> null[1][2][3][4]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class CreateAndDisplay {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Create linked list from array
    public static ListNode createFromArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(arr);
        ListNode current = head;
        
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        
        return head;
    }
    
    // Display linked list
    public static void display(ListNode head) {
        ListNode current = head;
        
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }
    
    // Get length of linked list
    public static int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        
        while (current != null) {
            length++;
            current = current.next;
        }
        
        return length;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createFromArray(arr);
        
        System.out.print("Linked List: ");
        display(head);
        
        System.out.println("Length: " + getLength(head));
    }
}
```

---

### Problem 2: Reverse Linked List

**Question:** Given the head of a singly linked list, reverse the list and return the new head.

```java
/**
 * Problem: Reverse Linked List
 * LeetCode: 206
 * 
 * Visual:
 * Before: 1 -> 2 -> 3 -> 4 -> null
 * After:  4 -> 3 -> 2 -> 1 -> null
 * 
 * Approach:
 * Use three pointers: prev, current, next
 * 
 * Step-by-step:
 * Initial:  null <- 1    2 -> 3 -> 4
 *                  ^     ^
 *                prev  current
 * 
 * Step 1: null <- 1 <- 2    3 -> 4
 *                        ^   ^
 *                      prev current
 * 
 * Step 2: null <- 1 <- 2 <- 3    4
 *                            ^    ^
 *                          prev current
 * 
 * Final:  null <- 1 <- 2 <- 3 <- 4
 *                              ^  ^
 *                            prev current
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ReverseLinkedList {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Iterative approach
    public static ListNode reverseIterative(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            // Store next node
            ListNode next = current.next;
            
            // Reverse the link
            current.next = prev;
            
            // Move pointers
            prev = current;
            current = next;
        }
        
        // prev is now the new head
        return prev;
    }
    
    // Recursive approach
    public static ListNode reverseRecursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Reverse rest of the list
        ListNode newHead = reverseRecursive(head.next);
        
        // Reverse current link
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    
    // Helper to create list
    public static ListNode create(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
    
    // Helper to display
    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        ListNode head = create(new int[]{1, 2, 3, 4, 5});
        
        System.out.print("Original: ");
        display(head);
        
        ListNode reversed = reverseIterative(head);
        System.out.print("Reversed (Iterative): ");
        display(reversed);
        
        // Reverse again using recursion
        reversed = reverseRecursive(reversed);
        System.out.print("Reversed (Recursive): ");
        display(reversed);
    }
}
```

---

### Problem 3: Detect Cycle in Linked List

**Question:** Given head of a linked list, determine if the list has a cycle.

```java
/**
 * Problem: Detect Cycle in Linked List
 * LeetCode: 141
 * 
 * Visual (with cycle):
 *       1 -> 2 -> 3 -> 4
 *            ^         |
 *            |_________|
 * 
 * Cycle: 2 -> 3 -> 4 -> 2 -> 3 -> 4 ...
 * 
 * Approach: Floyd's Cycle Detection (Tortoise and Hare)
 * - Use two pointers: slow (moves 1 step) and fast (moves 2 steps)
 * - If there's a cycle, they will meet
 * - If no cycle, fast will reach null
 * 
 * Why it works:
 * - In a cycle, fast pointer will eventually catch up to slow pointer
 * - Like running on a circular track - faster runner will meet slower runner
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class DetectCycle {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Detect if cycle exists
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move 1 step
            fast = fast.next.next;     // Move 2 steps
            
            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        
        return false; // No cycle
    }
    
    // Find length of cycle
    public static int getCycleLength(ListNode meetingPoint) {
        ListNode current = meetingPoint;
        int length = 0;
        
        do {
            current = current.next;
            length++;
        } while (current != meetingPoint);
        
        return length;
    }
    
    // Find starting point of cycle
    public static ListNode findCycleStart(ListNode head, ListNode meetingPoint) {
        ListNode ptr1 = head;
        ListNode ptr2 = meetingPoint;
        
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        
        return ptr1; // or ptr2
    }
    
    // Create list with cycle for testing
    public static ListNode createListWithCycle(int[] arr, int cyclePos) {
        if (arr == null || arr.length == 0) return null;
        
        ListNode head = new ListNode(arr);
        ListNode current = head;
        ListNode cycleNode = null;
        
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
            
            if (i == cyclePos) {
                cycleNode = current;
            }
        }
        
        // Create cycle
        if (cycleNode != null) {
            current.next = cycleNode;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        // List without cycle
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        
        System.out.println("List without cycle: " + hasCycle(head1));
        
        // List with cycle
        ListNode head2 = createListWithCycle(new int[]{1, 2, 3, 4, 5}, 1);
        System.out.println("List with cycle: " + hasCycle(head2));
        
        // Find cycle details
        if (hasCycle(head2)) {
            // Find meeting point
            ListNode slow = head2, fast = head2;
            do {
                slow = slow.next;
                fast = fast.next.next;
            } while (slow != fast);
            
            System.out.println("Cycle length: " + getCycleLength(slow));
            System.out.println("Cycle starts at node with value: " + 
                             findCycleStart(head2, slow).val);
        }
    }
}
```

---

### Problem 4: Find Middle of Linked List

**Question:** Given the head of a singly linked list, return the middle node. If two middle nodes exist, return the second one.

```java
/**
 * Problem: Middle of Linked List
 * LeetCode: 876
 * 
 * Visual:
 * Case 1 (odd length):  1 -> 2 -> 3 -> 4 -> 5
 *                                    ^
 *                                 middle (3)
 * 
 * Case 2 (even length): 1 -> 2 -> 3 -> 4 -> 5 -> 6
 *                                        ^
 *                                     middle (4)
 * 
 * Approach: Fast & Slow Pointers
 * - Slow moves 1 step, fast moves 2 steps
 * - When fast reaches end, slow is at middle
 * 
 * Why it works:
 * - Fast moves twice as fast as slow
 * - When fast covers n nodes, slow covers n/2 nodes
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MiddleOfLinkedList {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Find middle node
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // Find middle node (return first middle for even length)
    public static ListNode middleNodeFirst(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; // Start fast from next
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // Helper methods
    public static ListNode create(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
    
    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        // Odd length
        ListNode head1 = create(new int[]{1, 2, 3, 4, 5});
        System.out.print("Odd length list: ");
        display(head1);
        System.out.println("Middle node: " + middleNode(head1).val);
        
        // Even length
        ListNode head2 = create(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Even length list: ");
        display(head2);
        System.out.println("Middle node (second): " + middleNode(head2).val);
        System.out.println("Middle node (first): " + middleNodeFirst(head2).val);
    }
}
```

---

### Problem 5: Merge Two Sorted Linked Lists

**Question:** Merge two sorted linked lists and return it as a new sorted list.

```java
/**
 * Problem: Merge Two Sorted Linked Lists
 * LeetCode: 21
 * 
 * Visual:
 * List 1: 1 -> 3 -> 5 -> 7
 * List 2: 2 -> 4 -> 6 -> 8
 * 
 * Merged: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
 * 
 * Approach:
 * - Use dummy node to simplify edge cases
 * - Compare nodes from both lists
 * - Attach smaller node to result
 * - Move pointer of attached list
 * 
 * Time Complexity: O(m + n)
 * Space Complexity: O(1)
 */
public class MergeTwoSortedLists {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Iterative approach
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Dummy node to simplify
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Compare and merge
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Attach remaining nodes
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    // Recursive approach
    public static ListNode mergeRecursive(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // Choose smaller node and recurse
        if (list1.val <= list2.val) {
            list1.next = mergeRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeRecursive(list1, list2.next);
            return list2;
        }
    }
    
    // Helper methods
    public static ListNode create(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
    
    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        ListNode list1 = create(new int[]{1, 3, 5, 7});
        ListNode list2 = create(new int[]{2, 4, 6, 8});
        
        System.out.print("List 1: ");
        display(list1);
        
        System.out.print("List 2: ");
        display(list2);
        
        ListNode merged = mergeTwoLists(list1, list2);
        System.out.print("Merged: ");
        display(merged);
    }
}
```

---

### Problem 6: Remove Nth Node From End

**Question:** Given the head of a linked list, remove the nth node from the end of the list and return its head.

```java
/**
 * Problem: Remove Nth Node From End of List
 * LeetCode: 19
 * 
 * Visual:
 * List:     1 -> 2 -> 3 -> 4 -> 5
 *           ^              ^
 *           |              |
 *         prev          toRemove (n=2 from end = node 4)
 * 
 * After:    1 -> 2 -> 3 -> 5
 * 
 * Approach: Two Pointers
 * - Move fast pointer n steps ahead
 * - Move both pointers until fast reaches end
 * - Slow will be at node before the one to delete
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class RemoveNthFromEnd {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // Create dummy node to handle edge case (removing head)
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // slow is now at node before the one to delete
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    // Helper methods
    public static ListNode create(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
    
    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        ListNode head = create(new int[]{1, 2, 3, 4, 5});
        
        System.out.print("Original: ");
        display(head);
        
        int n = 2;
        ListNode result = removeNthFromEnd(head, n);
        System.out.print("After removing " + n + "th from end: ");
        display(result);
        
        // Remove head
        head = create(new int[]{1, 2});
        n = 2;
        result = removeNthFromEnd(head, n);
        System.out.print("Remove head (n=" + n + "): ");
        display(result);
    }
}
```

---

### Problem 7: Add Two Numbers

**Question:** You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each node contains a single digit. Add the two numbers and return the sum as a linked list.

```java
/**
 * Problem: Add Two Numbers
 * LeetCode: 2
 * 
 * Visual:
 * List 1: 2 -> 4 -> 3    (represents 342)
 * List 2: 5 -> 6 -> 4    (represents 465)
 * 
 * Sum:    7 -> 0 -> 8    (represents 807)
 * 
 * Carry visualization:
 *   1  1       (carry)
 *   2  4  3
 * + 5  6  4
 * ----------
 *   7  0  8
 * 
 * Time Complexity: O(max(m, n))
 * Space Complexity: O(max(m, n))
 */
public class AddTwoNumbers {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        while (l1 != null || l2 != null || carry != 0) {
            // Get values from lists (or 0 if null)
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            
            // Calculate sum and new carry
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            
            // Create new node with digit
            current.next = new ListNode(sum % 10);
            current = current.next;
            
            // Move list pointers
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        return dummy.next;
    }
    
    // Helper methods
    public static ListNode create(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
    
    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        ListNode l1 = create(new int[]{2, 4, 3}); // 342
        ListNode l2 = create(new int[]{5, 6, 4}); // 465
        
        System.out.print("Number 1: ");
        display(l1);
        
        System.out.print("Number 2: ");
        display(l2);
        
        ListNode result = addTwoNumbers(l1, l2);
        System.out.print("Sum: ");
        display(result); // 7 -> 0 -> 8 (807)
        
        // Test with different lengths
        l1 = create(new int[]{9, 9, 9, 9}); // 9999
        l2 = create(new int[]{9, 9});       // 99
        System.out.println("\n9999 + 99:");
        display(addTwoNumbers(l1, l2)); // 8 -> 9 -> 9 -> 0 -> 1 (10098)
    }
}
```

---

### Problem 8: Copy List with Random Pointer

**Question:** A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null. Construct a deep copy of the list.

```java
/**
 * Problem: Copy List with Random Pointer
 * LeetCode: 138
 * 
 * Visual:
 * Original:
 * [7,null] ->  ->  ->  ->[0][1][2][4][10][11][13]
 * 
 * Where second value is index of random pointer
 * 
 * Approach 1: HashMap
 * - First pass: create all nodes and store in map
 * - Second pass: set next and random pointers
 * 
 * Approach 2: Interweaving (O(1) space)
 * Step 1: Create copy nodes and interweave
 *         Original: A -> B -> C
 *         After:    A -> A' -> B -> B' -> C -> C'
 * 
 * Step 2: Set random pointers for copies
 *         If A.random = C, then A'.random = C'
 * 
 * Step 3: Separate lists
 *         Original: A -> B -> C
 *         Copy:     A' -> B' -> C'
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) for approach 2
 */
public class CopyListWithRandomPointer {
    
    static class Node {
        int val;
        Node next;
        Node random;
        
        Node(int val) {
            this.val = val;
        }
    }
    
    // Approach 1: HashMap - O(n) space
    public static Node copyRandomListHashMap(Node head) {
        if (head == null) return null;
        
        java.util.Map<Node, Node> map = new java.util.HashMap<>();
        
        // First pass: create all nodes
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }
        
        // Second pass: set next and random
        current = head;
        while (current != null) {
            Node copy = map.get(current);
            copy.next = map.get(current.next);
            copy.random = map.get(current.random);
            current = current.next;
        }
        
        return map.get(head);
    }
    
    // Approach 2: Interweaving - O(1) space
    public static Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // Step 1: Create copy nodes and interweave
        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }
        
        // Step 2: Set random pointers for copies
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
        
        // Step 3: Separate lists
        Node original = head;
        Node copy = head.next;
        Node copyHead = copy;
        
        while (original != null) {
            original.next = original.next.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
            original = original.next;
            copy = copy.next;
        }
        
        return copyHead;
    }
    
    // Helper to display
    public static void display(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.val + ",");
            if (current.random != null) {
                System.out.print(current.random.val);
            } else {
                System.out.print("null");
            }
            System.out.print("] -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        // Create list: [7,null] ->  ->  ->  ->[0][1][2][4][10][11][13]
        Node n0 = new Node(7);
        Node n1 = new Node(13);
        Node n2 = new Node(11);
        Node n3 = new Node(10);
        Node n4 = new Node(1);
        
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        
        n0.random = null;
        n1.random = n0;
        n2.random = n4;
        n3.random = n2;
        n4.random = n0;
        
        System.out.println("Original:");
        display(n0);
        
        Node copied = copyRandomList(n0);
        System.out.println("Copied:");
        display(copied);
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Reverse Linked List | Pointer Manipulation | LeetCode 206 |
| Middle of Linked List | Fast & Slow Pointers | LeetCode 876 |
| Merge Two Sorted Lists | Dummy Node | LeetCode 21 |
| Palindrome Linked List | Fast & Slow + Reverse | LeetCode 234 |
| Delete Node | Pointer Manipulation | LeetCode 237 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Add Two Numbers | Math + Dummy Node | LeetCode 2 |
| Remove Nth Node From End | Two Pointers | LeetCode 19 |
| Copy List with Random Pointer | HashMap/Interweaving | LeetCode 138 |
| Reorder List | Fast & Slow + Reverse | LeetCode 143 |
| Rotate List | Two Pointers | LeetCode 61 |
| Swap Nodes in Pairs | Pointer Manipulation | LeetCode 24 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Reverse Nodes in k-Group | Pointer Manipulation | LeetCode 25 |
| Merge k Sorted Lists | Heap/Divide & Conquer | LeetCode 23 |
| LRU Cache | HashMap + Doubly LL | LeetCode 146 |
| LFU Cache | HashMap + Doubly LL | LeetCode 460 |

---

## ✅ Key Takeaways

1. **Dummy Node** - Simplifies edge cases (especially head operations)
2. **Fast & Slow Pointers** - Middle, cycle detection, kth from end
3. **Pointer Manipulation** - Reverse, reorder, swap operations
4. **HashMap** - When you need random access to nodes
5. **Interweaving** - O(1) space solution for copy problems
6. **Two Pointers** - Most linked list problems can be solved with this

---

**Previous:** [String Problems](../arrays-strings/02-string-problems.md)  
**Next:** [Stack & Queue](../stack-queue/01-theory.md)