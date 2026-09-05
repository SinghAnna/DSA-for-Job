# 📚 Stack & Queue - Complete Guide

## 📚 Theory

### What is a Stack?

A **Stack** is a linear data structure that follows **LIFO** (Last In First Out) principle. The last element added is the first one to be removed.

### Stack Operations

| Operation | Description | Time Complexity |
|-----------|-------------|-----------------|
| Push | Add element to top | O(1) |
| Pop | Remove element from top | O(1) |
| Peek/Top | Get top element | O(1) |
| isEmpty | Check if empty | O(1) |
| Size | Get number of elements | O(1) |

### What is a Queue?

A **Queue** is a linear data structure that follows **FIFO** (First In First Out) principle. The first element added is the first one to be removed.

### Queue Operations

| Operation | Description | Time Complexity |
|-----------|-------------|-----------------|
| Enqueue | Add element to rear | O(1) |
| Dequeue | Remove element from front | O(1) |
| Front/Peek | Get front element | O(1) |
| isEmpty | Check if empty | O(1) |
| Size | Get number of elements | O(1) |

### Types of Queues

1. **Simple Queue** - Basic FIFO
2. **Circular Queue** - Last position connects to first
3. **Priority Queue** - Elements dequeued by priority
4. **Deque (Double-ended Queue)** - Insert/delete from both ends

### Stack vs Queue

| Property | Stack | Queue |
|----------|-------|-------|
| Principle | LIFO | FIFO |
| Insert | Top (Push) | Rear (Enqueue) |
| Delete | Top (Pop) | Front (Dequeue) |
| Use Cases | Recursion, Undo | BFS, Scheduling |
| Implementation | Array/LinkedList | Array/LinkedList |

### Java Implementations

```java
// Stack
Stack<Integer> stack = new Stack<>();           // Legacy
Deque<Integer> stack = new ArrayDeque<>();      // Recommended

// Queue
Queue<Integer> queue = new LinkedList<>();      // Standard queue
Deque<Integer> queue = new ArrayDeque<>();      // Better performance
PriorityQueue<Integer> pq = new PriorityQueue<>(); // Priority queue
```

---

## 💻 Java Code Examples

### Problem 1: Implement Stack using Array

**Question:** Implement a stack data structure using an array with push, pop, peek, and isEmpty operations.

```java
/**
 * Problem: Implement Stack using Array
 * 
 * Visual:
 * Push(1):               top=0[1]
 * Push(2):            top=1[2][1]
 * Push(3):         top=2[1][2][3]
 * Pop():              top=1, returns 3[1][2]
 * Peek():  returns 2
 * 
 * Time Complexity:
 * - Push: O(1)
 * - Pop: O(1)
 * - Peek: O(1)
 * - isEmpty: O(1)
 * 
 * Space Complexity: O(n)
 */
public class StackUsingArray {
    
    private int[] arr;
    private int top;
    private int capacity;
    
    // Constructor
    public StackUsingArray(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }
    
    // Push operation
    public void push(int x) {
        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
        System.out.println("Pushed: " + x);
    }
    
    // Pop operation
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top--];
    }
    
    // Peek operation
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return arr[top];
    }
    
    // Check if empty
    public boolean isEmpty() {
        return top == -1;
    }
    
    // Check if full
    public boolean isFull() {
        return top == capacity - 1;
    }
    
    // Get size
    public int size() {
        return top + 1;
    }
    
    // Display stack
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        
        System.out.print("Stack: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        StackUsingArray stack = new StackUsingArray(5);
        
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();
        
        System.out.println("Top element: " + stack.peek());
        System.out.println("Popped: " + stack.pop());
        stack.display();
        
        System.out.println("Size: " + stack.size());
        System.out.println("Is empty: " + stack.isEmpty());
    }
}
```

---

### Problem 2: Implement Queue using Array

**Question:** Implement a queue data structure using an array with enqueue, dequeue, front, and isEmpty operations.

```java
/**
 * Problem: Implement Queue using Array
 * 
 * Visual:
 * Enqueue(1):               front=0, rear=0[1]
 * Enqueue(2):            front=0, rear=1[1][2]
 * Enqueue(3):         front=0, rear=2[2][3][1]
 * Dequeue():  [_, 2, 3]        front=1, rear=2, returns 1
 * Front():    returns 2
 * 
 * Time Complexity:
 * - Enqueue: O(1)
 * - Dequeue: O(1)
 * - Front: O(1)
 * - isEmpty: O(1)
 * 
 * Space Complexity: O(n)
 */
public class QueueUsingArray {
    
    private int[] arr;
    private int front;
    private int rear;
    private int capacity;
    private int size;
    
    // Constructor
    public QueueUsingArray(int size) {
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = -1;
        size = 0;
    }
    
    // Enqueue operation
    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = x;
        size++;
        System.out.println("Enqueued: " + x);
    }
    
    // Dequeue operation
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int x = arr[front];
        front = (front + 1) % capacity;
        size--;
        return x;
    }
    
    // Front operation
    public int front() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return arr[front];
    }
    
    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Check if full
    public boolean isFull() {
        return size == capacity;
    }
    
    // Get size
    public int size() {
        return size;
    }
    
    // Display queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        
        System.out.print("Queue: ");
        int i = front;
        for (int count = 0; count < size; count++) {
            System.out.print(arr[i] + " ");
            i = (i + 1) % capacity;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        QueueUsingArray queue = new QueueUsingArray(5);
        
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();
        
        System.out.println("Front element: " + queue.front());
        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();
        
        System.out.println("Size: " + queue.size());
        System.out.println("Is empty: " + queue.isEmpty());
    }
}
```

---

### Problem 3: Implement Stack using Queue

**Question:** Implement a stack using only queue operations (enqueue, dequeue, front, isEmpty).

```java
/**
 * Problem: Implement Stack using Queues
 * LeetCode: 225
 * 
 * Visual (using 2 queues):
 * Push(1): q1=, q2=[][1]
 * Push(2): q1=, q2=[][1][2]
 * Push(3): q1=, q2=[][2][3][1]
 * Pop():   Move 1,2 to q2, pop 3 from q1
 *          q1=[], q2=[1][2]
 *          Swap q1 and q2
 *          q1=, q2=[][1][2]
 *          Returns 3
 * 
 * Approach 1: 2 Queues - Push O(n), Pop O(1)
 * Approach 2: 2 Queues - Push O(1), Pop O(n)
 * Approach 3: 1 Queue - Push O(n), Pop O(1)
 * 
 * Time Complexity:
 * - Push: O(n) or O(1)
 * - Pop: O(1) or O(n)
 * - Top: O(1)
 * 
 * Space Complexity: O(n)
 */
public class StackUsingQueues {
    
    // Approach 1: Two queues, push costly
    static class StackUsingQueues1 {
        private java.util.Queue<Integer> q1;
        private java.util.Queue<Integer> q2;
        
        public StackUsingQueues1() {
            q1 = new java.util.LinkedList<>();
            q2 = new java.util.LinkedList<>();
        }
        
        // Push - O(n)
        public void push(int x) {
            // Add to q2
            q2.add(x);
            
            // Move all from q1 to q2
            while (!q1.isEmpty()) {
                q2.add(q1.remove());
            }
            
            // Swap q1 and q2
            java.util.Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }
        
        // Pop - O(1)
        public int pop() {
            if (q1.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return q1.remove();
        }
        
        // Top - O(1)
        public int top() {
            if (q1.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return q1.peek();
        }
        
        // isEmpty - O(1)
        public boolean isEmpty() {
            return q1.isEmpty();
        }
    }
    
    // Approach 2: Two queues, pop costly
    static class StackUsingQueues2 {
        private java.util.Queue<Integer> q1;
        private java.util.Queue<Integer> q2;
        
        public StackUsingQueues2() {
            q1 = new java.util.LinkedList<>();
            q2 = new java.util.LinkedList<>();
        }
        
        // Push - O(1)
        public void push(int x) {
            q1.add(x);
        }
        
        // Pop - O(n)
        public int pop() {
            if (q1.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            
            // Move all except last to q2
            while (q1.size() > 1) {
                q2.add(q1.remove());
            }
            
            // Remove last element
            int x = q1.remove();
            
            // Swap q1 and q2
            java.util.Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            
            return x;
        }
        
        // Top - O(n)
        public int top() {
            if (q1.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            
            // Move all except last to q2
            while (q1.size() > 1) {
                q2.add(q1.remove());
            }
            
            // Get last element
            int x = q1.peek();
            q2.add(q1.remove());
            
            // Swap q1 and q2
            java.util.Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            
            return x;
        }
        
        public boolean isEmpty() {
            return q1.isEmpty();
        }
    }
    
    // Approach 3: Single queue
    static class StackUsingQueues3 {
        private java.util.Queue<Integer> q;
        
        public StackUsingQueues3() {
            q = new java.util.LinkedList<>();
        }
        
        // Push - O(n)
        public void push(int x) {
            q.add(x);
            
            // Rotate queue to make new element at front
            int size = q.size();
            for (int i = 1; i < size; i++) {
                q.add(q.remove());
            }
        }
        
        // Pop - O(1)
        public int pop() {
            if (q.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return q.remove();
        }
        
        // Top - O(1)
        public int top() {
            if (q.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return q.peek();
        }
        
        public boolean isEmpty() {
            return q.isEmpty();
        }
    }
    
    public static void main(String[] args) {
        StackUsingQueues1 stack = new StackUsingQueues1();
        
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Is empty: " + stack.isEmpty());
    }
}
```

---

### Problem 4: Implement Queue using Stack

**Question:** Implement a queue using only stack operations (push, pop, peek, isEmpty).

```java
/**
 * Problem: Implement Queue using Stacks
 * LeetCode: 232
 * 
 * Visual (using 2 stacks):
 * Enqueue(1): s1=, s2=[][1]
 * Enqueue(2): s1=, s2=[][1][2]
 * Enqueue(3): s1=, s2=[][2][3][1]
 * Dequeue():  Move all to s2: s1=[], s2=[1][2][3]
 *             Pop from s2: returns 1
 *             s1=[], s2=[2][3]
 * 
 * Approach 1: 2 Stacks - Enqueue O(1), Dequeue O(n) worst case
 * Approach 2: 2 Stacks - Enqueue O(n), Dequeue O(1)
 * 
 * Time Complexity:
 * - Enqueue: O(1) amortized
 * - Dequeue: O(1) amortized
 * - Peek: O(1) amortized
 * 
 * Space Complexity: O(n)
 */
public class QueueUsingStacks {
    
    // Approach 1: Enqueue O(1), Dequeue O(n) worst case
    static class QueueUsingStacks1 {
        private java.util.Stack<Integer> s1;
        private java.util.Stack<Integer> s2;
        
        public QueueUsingStacks1() {
            s1 = new java.util.Stack<>();
            s2 = new java.util.Stack<>();
        }
        
        // Enqueue - O(1)
        public void enqueue(int x) {
            s1.push(x);
        }
        
        // Dequeue - O(n) worst case, O(1) amortized
        public int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            
            // If s2 is empty, move all from s1
            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            
            return s2.pop();
        }
        
        // Peek - O(1) amortized
        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            
            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            
            return s2.peek();
        }
        
        public boolean isEmpty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }
    
    // Approach 2: Enqueue O(n), Dequeue O(1)
    static class QueueUsingStacks2 {
        private java.util.Stack<Integer> s1;
        private java.util.Stack<Integer> s2;
        
        public QueueUsingStacks2() {
            s1 = new java.util.Stack<>();
            s2 = new java.util.Stack<>();
        }
        
        // Enqueue - O(n)
        public void enqueue(int x) {
            // Move all from s1 to s2
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            
            // Push new element
            s1.push(x);
            
            // Move all back from s2 to s1
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        
        // Dequeue - O(1)
        public int dequeue() {
            if (s1.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return s1.pop();
        }
        
        // Peek - O(1)
        public int peek() {
            if (s1.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return s1.peek();
        }
        
        public boolean isEmpty() {
            return s1.isEmpty();
        }
    }
    
    public static void main(String[] args) {
        QueueUsingStacks1 queue = new QueueUsingStacks1();
        
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        
        System.out.println("Front: " + queue.peek());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Front: " + queue.peek());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Is empty: " + queue.isEmpty());
    }
}
```

---

### Problem 5: Valid Parentheses

**Question:** Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

```java
/**
 * Problem: Valid Parentheses
 * LeetCode: 20
 * 
 * Visual:
 * Input: "({[]})"
 * 
 * Stack operations:
 * '(': push -> ['(']
 * '{': push -> ['(', '{']
 * '[': push -> ['(', '{', '[']
 * ']': pop '[' matches -> ['(', '{']
 * '}': pop '{' matches -> ['(']
 * ')': pop '(' matches -> []
 * 
 * Result: Valid (stack is empty)
 * 
 * Input: "([)]"
 * Stack: ['('] -> ['(', '['] -> pop '[' doesn't match ')' -> Invalid
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ValidParentheses {
    
    public static boolean isValid(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        
        for (char c : s.toCharArray()) {
            // If opening bracket, push to stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // If closing bracket, check match
            else {
                // If stack is empty, no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                
                // Check if brackets match
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        
        // If stack is empty, all brackets matched
        return stack.isEmpty();
    }
    
    // Extended: Return type of brackets needed to balance
    public static String getMissingBrackets(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        java.util.Stack<Character> needed = new java.util.Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return "Invalid - extra closing brackets";
                }
                
                char top = stack.pop();
                
                if (c == ')' && top != '(') return "Invalid - mismatch";
                if (c == '}' && top != '{') return "Invalid - mismatch";
                if (c == ']' && top != '[') return "Invalid - mismatch";
            }
        }
        
        // Build string of needed closing brackets
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char open = stack.pop();
            if (open == '(') sb.append(')');
            else if (open == '{') sb.append('}');
            else if (open == '[') sb.append(']');
        }
        
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        String[] tests = {
            "()",
            "()[]{}",
            "(]",
            "([)]",
            "{[]}",
            "({[]})",
            "((()))",
            "(()"
        };
        
        for (String test : tests) {
            System.out.println("\"" + test + "\" is " + 
                             (isValid(test) ? "valid" : "invalid"));
        }
        
        // Get missing brackets
        System.out.println("\nMissing brackets for \"((()\": " + 
                          getMissingBrackets("((()"));
    }
}
```

---

### Problem 6: Next Greater Element

**Question:** Given an array of integers, return an array where each element is the next greater element to its right. If no greater element exists, return -1.

```java
/**
 * Problem: Next Greater Element
 * LeetCode: 496
 * 
 * Visual:
 * Input:[2][4][5][8][10]
 * Index:   0  1  2   3   4
 * 
 * Stack approach (monotonic decreasing):
 * 
 * i=0, val=4:  stack=, result=[?, ?, ?, ?, ?]
 * i=1, val=5:  5>4, so result=5, pop 0
 *              stack=, result=[5, ?, ?, ?, ?][1]
 * i=2, val=2:  2<5, push 2
 *              stack=, result=[5, ?, ?, ?, ?][1][2]
 * i=3, val=10: 10>2, result=10, pop 2[2]
 *              10>5, result=10, pop 1[1]
 *              stack=, result=[5, 10, 10, ?, ?][3]
 * i=4, val=8:  8<10, push 4
 *              stack=, result=[5, 10, 10, ?, ?][3][4]
 * 
 * Remaining in stack: set to -1
 * result=-1, result=-1[3][4]
 * 
 * Final: [5, 10, 10, -1, -1]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class NextGreaterElement {
    
    // Next greater element to the right
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements smaller than or equal to current
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            
            // If stack is empty, no greater element
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            
            // Push current element
            stack.push(nums[i]);
        }
        
        return result;
    }
    
    // Next greater element to the left
    public static int[] nextGreaterElementLeft(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        // Traverse from left to right
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            
            stack.push(nums[i]);
        }
        
        return result;
    }
    
    // Next greater element I (find in another array)
    public static int[] nextGreaterElementFind(int[] nums1, int[] nums2) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        // Find next greater for all elements in nums2
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        
        // Build result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return result;
    }
    
    // Circular array - next greater element II
    public static int[] nextGreaterElementCircular(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        java.util.Arrays.fill(result, -1);
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        // Traverse array twice (circular)
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                result[stack.pop()] = nums[idx];
            }
            
            if (i < n) {
                stack.push(idx);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {4, 5, 2, 10, 8};
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        System.out.println("Next Greater (right): " + 
                          java.util.Arrays.toString(nextGreaterElement(nums1)));
        System.out.println("Next Greater (left): " + 
                          java.util.Arrays.toString(nextGreaterElementLeft(nums1)));
        
        int[] nums2 = {1, 3, 4, 2};
        System.out.println("\nCircular Array: " + java.util.Arrays.toString(nums2));
        System.out.println("Next Greater (circular): " + 
                          java.util.Arrays.toString(nextGreaterElementCircular(nums2)));
        
        // Next greater element I
        int[] search = {4, 1, 2};
        int[] findIn = {1, 3, 4, 2};
        System.out.println("\nFind next greater for " + java.util.Arrays.toString(search) + 
                          " in " + java.util.Arrays.toString(findIn));
        System.out.println("Result: " + java.util.Arrays.toString(
                          nextGreaterElementFind(search, findIn)));
    }
}
```

---

### Problem 7: Implement Min Stack

**Question:** Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

```java
/**
 * Problem: Min Stack
 * LeetCode: 155
 * 
 * Visual:
 * Operations:
 * push(3)  -> stack=, minStack=[3]
 * push(5)  -> stack=, minStack=[3][5]
 * push(2)  -> stack=, minStack=[2][5][3]
 * push(4)  -> stack=, minStack=[2][3][4][5]
 * getMin() -> returns 2
 * pop()    -> stack=, minStack=[2][3][5]
 * getMin() -> returns 2
 * 
 * Approach 1: Two stacks - one for values, one for minimums
 * Approach 2: One stack with pairs (value, currentMin)
 * Approach 3: One stack with encoding (for space optimization)
 * 
 * Time Complexity:
 * - push: O(1)
 * - pop: O(1)
 * - top: O(1)
 * - getMin: O(1)
 * 
 * Space Complexity: O(n)
 */
public class MinStack {
    
    // Approach 1: Two stacks
    static class MinStackTwoStacks {
        private java.util.Stack<Integer> stack;
        private java.util.Stack<Integer> minStack;
        
        public MinStackTwoStacks() {
            stack = new java.util.Stack<>();
            minStack = new java.util.Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
            
            // Push to minStack if it's empty or val <= current min
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            } else {
                // Push current min again
                minStack.push(minStack.peek());
            }
        }
        
        public void pop() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            stack.pop();
            minStack.pop();
        }
        
        public int top() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return stack.peek();
        }
        
        public int getMin() {
            if (minStack.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return minStack.peek();
        }
    }
    
    // Approach 2: Optimized two stacks (only push to minStack when needed)
    static class MinStackOptimized {
        private java.util.Stack<Integer> stack;
        private java.util.Stack<Integer> minStack;
        
        public MinStackOptimized() {
            stack = new java.util.Stack<>();
            minStack = new java.util.Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
            
            // Only push to minStack if val <= current min
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }
        
        public void pop() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            
            int popped = stack.pop();
            
            // If popped element is current min, pop from minStack too
            if (popped == minStack.peek()) {
                minStack.pop();
            }
        }
        
        public int top() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return stack.peek();
        }
        
        public int getMin() {
            if (minStack.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return minStack.peek();
        }
    }
    
    // Approach 3: Single stack with encoding
    static class MinStackSingleStack {
        private java.util.Stack<Long> stack;
        private long minVal;
        
        public MinStackSingleStack() {
            stack = new java.util.Stack<>();
            minVal = -1;
        }
        
        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push((long)val);
                minVal = val;
            } else {
                if (val >= minVal) {
                    stack.push((long)val);
                } else {
                    // Encode: store difference
                    stack.push((long)val - minVal);
                    minVal = val;
                }
            }
        }
        
        public void pop() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            
            long top = stack.pop();
            
            if (top < 0) {
                // Encoded value, restore previous min
                minVal = minVal - top;
            }
        }
        
        public int top() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            
            long top = stack.peek();
            
            if (top < 0) {
                // Encoded value, actual value is minVal
                return (int)minVal;
            } else {
                return (int)(minVal + top);
            }
        }
        
        public int getMin() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return (int)minVal;
        }
    }
    
    public static void main(String[] args) {
        MinStackOptimized minStack = new MinStackOptimized();
        
        minStack.push(3);
        System.out.println("Min: " + minStack.getMin()); // 3
        
        minStack.push(5);
        System.out.println("Min: " + minStack.getMin()); // 3
        
        minStack.push(2);
        minStack.push(4);
        System.out.println("Min: " + minStack.getMin()); // 2
        
        System.out.println("Top: " + minStack.top()); // 4
        
        minStack.pop();
        System.out.println("Min after pop: " + minStack.getMin()); // 2
        
        minStack.pop();
        System.out.println("Min after pop: " + minStack.getMin()); // 3
    }
}
```

---

### Problem 8: Implement Queue with Priority

**Question:** Implement a priority queue that returns elements based on priority (higher priority first).

```java
/**
 * Problem: Priority Queue Implementation
 * 
 * Visual:
 * Enqueue(10, priority=2): [(10,2)]
 * Enqueue(20, priority=1): [(10,2), (20,1)]
 * Enqueue(30, priority=3): [(30,3), (10,2), (20,1)]
 * Dequeue(): returns 30 (highest priority)
 * 
 * Time Complexity:
 * - Enqueue: O(log n)
 * - Dequeue: O(log n)
 * - Peek: O(1)
 * 
 * Space Complexity: O(n)
 */
public class PriorityQueueImplementation {
    
    static class PriorityQueue {
        private java.util.Queue<Node> pq;
        
        static class Node implements Comparable<Node> {
            int value;
            int priority;
            
            Node(int value, int priority) {
                this.value = value;
                this.priority = priority;
            }
            
            @Override
            public int compareTo(Node other) {
                // Higher priority first (descending)
                return Integer.compare(other.priority, this.priority);
            }
        }
        
        public PriorityQueue() {
            pq = new java.util.PriorityQueue<>();
        }
        
        // Enqueue - O(log n)
        public void enqueue(int value, int priority) {
            pq.add(new Node(value, priority));
            System.out.println("Enqueued: " + value + " (priority=" + priority + ")");
        }
        
        // Dequeue - O(log n)
        public int dequeue() {
            if (pq.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return pq.remove().value;
        }
        
        // Peek - O(1)
        public int peek() {
            if (pq.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return pq.peek().value;
        }
        
        public boolean isEmpty() {
            return pq.isEmpty();
        }
        
        public int size() {
            return pq.size();
        }
    }
    
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        
        pq.enqueue(10, 2);
        pq.enqueue(20, 1);
        pq.enqueue(30, 3);
        pq.enqueue(40, 1);
        
        System.out.println("\nDequeue order (by priority):");
        while (!pq.isEmpty()) {
            System.out.println("Dequeued: " + pq.dequeue());
        }
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Valid Parentheses | Stack | LeetCode 20 |
| Implement Stack using Queues | Stack Simulation | LeetCode 225 |
| Implement Queue using Stacks | Queue Simulation | LeetCode 232 |
| Min Stack | Stack with Extra Info | LeetCode 155 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Next Greater Element | Monotonic Stack | LeetCode 496 |
| Evaluate Reverse Polish | Stack Evaluation | LeetCode 150 |
| Remove K Digits | Monotonic Stack | LeetCode 402 |
| Daily Temperatures | Monotonic Stack | LeetCode 739 |
| Design Circular Queue | Array Implementation | LeetCode 622 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Sliding Window Maximum | Monotonic Queue | LeetCode 239 |
| Merge k Sorted Lists | Priority Queue | LeetCode 23 |
| Find Median from Data Stream | Two Heaps | LeetCode 295 |
| Trapping Rain Water | Stack/Two Pointers | LeetCode 42 |

---

## ✅ Key Takeaways

1. **Stack (LIFO)** - Use for parentheses, undo operations, DFS
2. **Queue (FIFO)** - Use for BFS, scheduling, level-order traversal
3. **Monotonic Stack** - Next greater/smaller element problems
4. **Priority Queue** - When you need elements sorted by priority
5. **Two Stacks** - Can simulate queue, can track minimum
6. **Amortized O(1)** - Queue using stacks has O(1) amortized operations

---

**Previous:** [Linked List](../linked-list/01-theory.md)  
**Next:** [Trees](../trees/01-theory.md)