# ⏱️ Time & Space Complexity (Big-O Notation)

## 📚 Theory

### What is Big-O Notation?

Big-O notation describes how the runtime of an algorithm grows as the input size increases. It gives us the **worst-case scenario**.

### Common Time Complexities

| Complexity | Name | Example |
|------------|------|---------|
| O(1) | Constant | Access array element |
| O(log n) | Logarithmic | Binary Search |
| O(n) | Linear | Linear Search |
| O(n log n) | Linearithmic | Merge Sort |
| O(n²) | Quadratic | Bubble Sort |
| O(2ⁿ) | Exponential | Recursive Fibonacci |
| O(n!) | Factorial | Permutations |

### Space Complexity

Space complexity measures how much extra memory an algorithm uses.

---

## 💻 Java Code Examples

### Example 1: O(1) - Constant Time

```java
/**
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class ConstantTime {
    public static int getFirstElement(int[] arr) {
        return arr; // Always 1 operation
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("First element: " + getFirstElement(arr));
    }
}
```

### Example 2: O(n) - Linear Time

```java
/**
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LinearTime {
    public static int findMax(int[] arr) {
        int max = arr;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7, 3};
        System.out.println("Maximum: " + findMax(arr));
    }
}
```

### Example 3: O(n²) - Quadratic Time

```java
/**
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
public class QuadraticTime {
    // Bubble Sort - O(n²)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        bubbleSort(arr);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

### Example 4: O(log n) - Logarithmic Time

```java
/**
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class LogarithmicTime {
    // Binary Search - O(log n)
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 7;
        int result = binarySearch(arr, target);
        System.out.println("Found at index: " + result);
    }
}
```

### Example 5: O(2ⁿ) - Exponential Time

```java
/**
 * Time Complexity: O(2ⁿ)
 * Space Complexity: O(n) - recursion stack
 */
public class ExponentialTime {
    // Recursive Fibonacci - O(2ⁿ)
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci(" + n + ") = " + fibonacci(n));
        
        // Print first 10 Fibonacci numbers
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}
```

### Example 6: O(n log n) - Linearithmic Time

```java
/**
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public class LinearithmicTime {
    // Merge Sort - O(n log n)
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

---

## 📝 Practice Problems

| Problem | Complexity | Link |
|---------|------------|------|
| Find Maximum in Array | O(n) | LeetCode Easy |
| Binary Search | O(log n) | LeetCode Easy |
| Two Sum | O(n) | LeetCode Easy |
| Contains Duplicate | O(n) | LeetCode Easy |

---

## ✅ Key Takeaways

1. **O(1)** is best, **O(n!)** is worst
2. For interviews, aim for **O(n)** or **O(n log n)** solutions
3. **Space complexity** matters too!
4. Always analyze both time and space before coding

---

**Next:** [Recursion Basics](./02-recursion-basics.md)