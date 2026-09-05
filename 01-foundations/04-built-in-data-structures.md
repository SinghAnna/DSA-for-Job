# 🗂️ Built-in Data Structures in Java

## 📚 Theory

### Why Learn Built-in Data Structures?

- Java provides **ready-to-use** implementations
- **Optimized** and **tested** by experts
- Saves time in interviews and competitive programming
- Understanding these helps in choosing the right structure

### Overview

| Data Structure | Interface | Implementation | Time Complexity (Access/Search/Insert/Delete) |
|----------------|-----------|----------------|-----------------------------------------------|
| ArrayList | List | ArrayList | O(1) / O(n) / O(n) / O(n) |
| LinkedList | List | LinkedList | O(n) / O(n) / O(1) / O(1) |
| Stack | Deque | Stack/ArrayDeque | O(1) / O(n) / O(1) / O(1) |
| Queue | Queue | LinkedList/ArrayDeque | O(1) / O(n) / O(1) / O(1) |
| HashMap | Map | HashMap | O(1) / O(1) / O(1) / O(1) |
| HashSet | Set | HashSet | O(1) / O(1) / O(1) / O(1) |
| TreeMap | Map | TreeMap | O(log n) / O(log n) / O(log n) / O(log n) |
| PriorityQueue | Queue | PriorityQueue | O(1) / O(n) / O(log n) / O(log n) |

---

## 💻 Java Code Examples

### Example 1: ArrayList

```java
import java.util.ArrayList;
import java.util.Collections;

/**
 * ArrayList - Dynamic Array
 * Time Complexity:
 * - Get: O(1)
 * - Add: O(1) amortized
 * - Remove: O(n)
 * - Contains: O(n)
 */
public class ArrayListExample {
    
    public static void main(String[] args) {
        // Create ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        
        // Add elements
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        
        System.out.println("ArrayList: " + list);
        
        // Add at specific index
        list.add(2, 25);
        System.out.println("After adding 25 at index 2: " + list);
        
        // Get element
        System.out.println("Element at index 2: " + list.get(2));
        
        // Update element
        list.set(2, 100);
        System.out.println("After updating index 2: " + list);
        
        // Remove element
        list.remove(Integer.valueOf(100)); // Remove by value
        System.out.println("After removing 100: " + list);
        
        list.remove(2); // Remove by index
        System.out.println("After removing index 2: " + list);
        
        // Size
        System.out.println("Size: " + list.size());
        
        // Contains
        System.out.println("Contains 30: " + list.contains(30));
        
        // Iterate
        System.out.print("Iterating: ");
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Sort
        Collections.sort(list);
        System.out.println("Sorted: " + list);
        
        // Reverse
        Collections.reverse(list);
        System.out.println("Reversed: " + list);
        
        // Convert to array
        Integer[] arr = list.toArray(new Integer);
        System.out.print("As Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Clear
        list.clear();
        System.out.println("After clear: " + list);
    }
}
```

### Example 2: ArrayList with Custom Objects

```java
import java.util.ArrayList;

/**
 * ArrayList with Custom Objects
 */
class Student {
    int id;
    String name;
    int marks;
    
    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', marks=" + marks + "}";
    }
}

public class ArrayListCustomObjects {
    
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        
        students.add(new Student(1, "Alice", 95));
        students.add(new Student(2, "Bob", 87));
        students.add(new Student(3, "Charlie", 92));
        
        System.out.println("All Students:");
        for (Student s : students) {
            System.out.println(s);
        }
        
        // Find student with max marks
        Student maxMarksStudent = students.get(0);
        for (Student s : students) {
            if (s.marks > maxMarksStudent.marks) {
                maxMarksStudent = s;
            }
        }
        System.out.println("\nTop Student: " + maxMarksStudent);
    }
}
```

### Example 3: LinkedList

```java
import java.util.LinkedList;

/**
 * LinkedList - Doubly Linked List
 * Time Complexity:
 * - Get: O(n)
 * - Add: O(1)
 * - Remove: O(1)
 * - Contains: O(n)
 */
public class LinkedListExample {
    
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        
        // Add elements
        list.add(10);
        list.add(20);
        list.add(30);
        
        // Add at beginning
        list.addFirst(5);
        
        // Add at end
        list.addLast(40);
        
        System.out.println("LinkedList: " + list);
        
        // Get first and last
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        
        // Remove first and last
        System.out.println("Removed First: " + list.removeFirst());
        System.out.println("Removed Last: " + list.removeLast());
        
        System.out.println("After removal: " + list);
        
        // Iterate
        System.out.print("Iterating: ");
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
```

### Example 4: Stack

```java
import java.util.Stack;

/**
 * Stack - LIFO (Last In First Out)
 * Time Complexity:
 * - Push: O(1)
 * - Pop: O(1)
 * - Peek: O(1)
 * - Search: O(n)
 */
public class StackExample {
    
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        
        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        
        System.out.println("Stack: " + stack);
        
        // Peek (top element)
        System.out.println("Top element: " + stack.peek());
        
        // Pop (remove top)
        System.out.println("Popped: " + stack.pop());
        System.out.println("Stack after pop: " + stack);
        
        // Check if empty
        System.out.println("Is empty: " + stack.isEmpty());
        
        // Size
        System.out.println("Size: " + stack.size());
        
        // Search
        System.out.println("Position of 20: " + stack.search(20));
        
        // Clear
        stack.clear();
        System.out.println("After clear: " + stack);
    }
}
```

### Example 5: Stack Applications

```java
import java.util.Stack;

/**
 * Stack Applications
 */
public class StackApplications {
    
    // Check if parentheses are balanced
    public static boolean isBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : expr.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    // Reverse string using stack
    public static String reverse(String str) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : str.toCharArray()) {
            stack.push(ch);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test balanced parentheses
        String expr1 = "((()))";
        String expr2 = "(()";
        String expr3 = "{[()]}";
        
        System.out.println(expr1 + " is balanced: " + isBalanced(expr1));
        System.out.println(expr2 + " is balanced: " + isBalanced(expr2));
        System.out.println(expr3 + " is balanced: " + isBalanced(expr3));
        
        // Test reverse
        String str = "hello";
        System.out.println("\nOriginal: " + str);
        System.out.println("Reversed: " + reverse(str));
    }
}
```

### Example 6: Queue

```java
import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue - FIFO (First In First Out)
 * Time Complexity:
 * - Offer/Add: O(1)
 * - Poll/Remove: O(1)
 * - Peek: O(1)
 */
public class QueueExample {
    
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        
        // Add elements
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        queue.offer(40);
        
        System.out.println("Queue: " + queue);
        
        // Peek (front element)
        System.out.println("Front element: " + queue.peek());
        
        // Poll (remove front)
        System.out.println("Polled: " + queue.poll());
        System.out.println("Queue after poll: " + queue);
        
        // Size
        System.out.println("Size: " + queue.size());
        
        // Check if empty
        System.out.println("Is empty: " + queue.isEmpty());
        
        // Iterate
        System.out.print("Iterating: ");
        for (int num : queue) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
```

### Example 7: PriorityQueue (Min-Heap)

```java
import java.util.PriorityQueue;

/**
 * PriorityQueue - Min-Heap by default
 * Time Complexity:
 * - Offer: O(log n)
 * - Poll: O(log n)
 * - Peek: O(1)
 */
public class PriorityQueueExample {
    
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Add elements
        pq.offer(30);
        pq.offer(10);
        pq.offer(50);
        pq.offer(20);
        pq.offer(40);
        
        System.out.println("PriorityQueue: " + pq);
        
        // Peek (minimum element)
        System.out.println("Minimum: " + pq.peek());
        
        // Poll (remove minimum)
        System.out.print("Polling all: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }
}
```

### Example 8: PriorityQueue (Max-Heap)

```java
import java.util.PriorityQueue;
import java.util.Collections;

/**
 * PriorityQueue as Max-Heap
 */
public class MaxHeapExample {
    
    public static void main(String[] args) {
        // Max-Heap using Collections.reverseOrder()
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        pq.offer(30);
        pq.offer(10);
        pq.offer(50);
        pq.offer(20);
        pq.offer(40);
        
        System.out.println("Max-Heap: " + pq);
        
        System.out.println("Maximum: " + pq.peek());
        
        System.out.print("Polling all (descending): ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }
}
```

### Example 9: HashMap

```java
import java.util.HashMap;
import java.util.Map;

/**
 * HashMap - Key-Value Pairs
 * Time Complexity:
 * - Get: O(1)
 * - Put: O(1)
 * - Remove: O(1)
 * - ContainsKey: O(1)
 */
public class HashMapExample {
    
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        
        // Add elements
        map.put("Alice", 95);
        map.put("Bob", 87);
        map.put("Charlie", 92);
        map.put("David", 88);
        
        System.out.println("HashMap: " + map);
        
        // Get value
        System.out.println("Alice's marks: " + map.get("Alice"));
        
        // Get with default
        System.out.println("Eve's marks: " + map.getOrDefault("Eve", 0));
        
        // Contains key
        System.out.println("Contains Bob: " + map.containsKey("Bob"));
        
        // Contains value
        System.out.println("Contains 92: " + map.containsValue(92));
        
        // Update value
        map.put("Alice", 98);
        System.out.println("After update: " + map);
        
        // Remove
        map.remove("David");
        System.out.println("After remove: " + map);
        
        // Size
        System.out.println("Size: " + map.size());
        
        // Iterate keys
        System.out.print("Keys: ");
        for (String key : map.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();
        
        // Iterate values
        System.out.print("Values: ");
        for (int value : map.values()) {
            System.out.print(value + " ");
        }
        System.out.println();
        
        // Iterate entries
        System.out.println("Entries:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Clear
        map.clear();
        System.out.println("After clear: " + map);
    }
}
```

### Example 10: HashMap with Custom Objects

```java
import java.util.HashMap;
import java.util.Map;

/**
 * HashMap with Custom Key
 */
class Person {
    int id;
    String name;
    
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }
    
    @Override
    public int hashCode() {
        return id; // Important for HashMap
    }
    
    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + name + "'}";
    }
}

public class HashMapCustomKey {
    
    public static void main(String[] args) {
        HashMap<Person, String> map = new HashMap<>();
        
        Person p1 = new Person(1, "Alice");
        Person p2 = new Person(2, "Bob");
        
        map.put(p1, "Engineer");
        map.put(p2, "Doctor");
        
        System.out.println("HashMap: " + map);
        
        // Get using same id (equals and hashCode matter)
        Person p3 = new Person(1, "Alice Updated");
        System.out.println("Profession: " + map.get(p3));
    }
}
```

### Example 11: HashSet

```java
import java.util.HashSet;
import java.util.Set;

/**
 * HashSet - Unique Elements
 * Time Complexity:
 * - Add: O(1)
 * - Remove: O(1)
 * - Contains: O(1)
 */
public class HashSetExample {
    
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        
        // Add elements
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(10); // Duplicate - won't be added
        
        System.out.println("HashSet: " + set);
        
        // Contains
        System.out.println("Contains 20: " + set.contains(20));
        
        // Remove
        set.remove(20);
        System.out.println("After remove: " + set);
        
        // Size
        System.out.println("Size: " + set.size());
        
        // Iterate
        System.out.print("Iterating: ");
        for (int num : set) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Clear
        set.clear();
        System.out.println("After clear: " + set);
    }
}
```

### Example 12: TreeMap (Sorted Map)

```java
import java.util.TreeMap;
import java.util.Map;

/**
 * TreeMap - Sorted by Keys
 * Time Complexity:
 * - Get: O(log n)
 * - Put: O(log n)
 * - Remove: O(log n)
 */
public class TreeMapExample {
    
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        
        map.put(30, "Thirty");
        map.put(10, "Ten");
        map.put(50, "Fifty");
        map.put(20, "Twenty");
        
        System.out.println("TreeMap: " + map);
        
        // Get
        System.out.println("Key 20: " + map.get(20));
        
        // First and Last keys
        System.out.println("First key: " + map.firstKey());
        System.out.println("Last key: " + map.lastKey());
        
        // Lower and Higher keys
        System.out.println("Lower than 30: " + map.lowerKey(30));
        System.out.println("Higher than 30: " + map.higherKey(30));
        
        // SubMap
        System.out.println("SubMap (20-40): " + map.subMap(20, 40));
        
        // Iterate (sorted order)
        System.out.println("Entries (sorted):");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
```

### Example 13: ArrayDeque (Better than Stack)

```java
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ArrayDeque - Faster than Stack
 * Time Complexity: O(1) for all operations
 */
public class ArrayDequeExample {
    
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        
        // Add at front
        deque.addFirst(10);
        deque.addFirst(20);
        
        // Add at end
        deque.addLast(30);
        deque.addLast(40);
        
        System.out.println("Deque: " + deque);
        
        // Get first and last
        System.out.println("First: " + deque.getFirst());
        System.out.println("Last: " + deque.getLast());
        
        // Remove first and last
        System.out.println("Removed First: " + deque.removeFirst());
        System.out.println("Removed Last: " + deque.removeLast());
        
        System.out.println("After removal: " + deque);
    }
}
```

---

## 📝 Practice Problems

| Data Structure | Problem | Difficulty |
|----------------|---------|------------|
| ArrayList | Two Sum | Easy |
| HashMap | Group Anagrams | Medium |
| Stack | Valid Parentheses | Easy |
| Queue | Implement Queue using Stack | Easy |
| PriorityQueue | Top K Frequent Elements | Medium |
| HashSet | Contains Duplicate | Easy |
| TreeMap | Merge Intervals | Medium |

---

## ✅ Key Takeaways

1. **ArrayList** - Use when you need fast random access
2. **LinkedList** - Use when you need frequent insertions/deletions
3. **Stack/ArrayDeque** - Use for LIFO problems
4. **Queue** - Use for BFS, level-order traversal
5. **HashMap** - Use for O(1) lookups, frequency counting
6. **HashSet** - Use for uniqueness check
7. **PriorityQueue** - Use for Kth largest/smallest problems
8. **TreeMap** - Use when you need sorted keys

---

**Previous:** [I/O Handling](./03-io-handling.md)  
**Next:** [Mathematics for DSA](./05-mathematics-for-dsa.md)