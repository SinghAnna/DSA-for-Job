# 📥 Input/Output Handling in Java

## 📚 Theory

### Why I/O Matters in DSA?

- **Fast I/O** can be the difference between TLE (Time Limit Exceeded) and AC (Accepted)
- **Scanner** is slow, **BufferedReader** is fast
- **System.out.println()** is slow for large output

### I/O Methods Comparison

| Method | Speed | Use Case |
|--------|-------|----------|
| Scanner | Slow | Small input, easy to use |
| BufferedReader | Fast | Competitive programming |
| System.out.println() | Slow | Small output |
| PrintWriter | Fast | Large output |
| StringBuilder | Fastest | Build output then print |

---

## 💻 Java Code Examples

### Example 1: Using Scanner (Basic I/O)

```java
import java.util.Scanner;

/**
 * Basic I/O using Scanner
 * Good for small inputs
 */
public class ScannerIO {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read integer
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        System.out.println("You entered: " + num);
        
        // Read string (single word)
        System.out.print("Enter a word: ");
        String word = sc.next();
        System.out.println("Word: " + word);
        
        // Read line
        System.out.print("Enter a sentence: ");
        sc.nextLine(); // consume newline
        String line = sc.nextLine();
        System.out.println("Sentence: " + line);
        
        // Read multiple integers
        System.out.println("Enter 5 numbers:");
        int[] arr = new int;[1]
        for (int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
        }
        
        System.out.print("Array: ");
        for (int num2 : arr) {
            System.out.print(num2 + " ");
        }
        
        sc.close();
    }
}
```

### Example 2: Using BufferedReader (Fast I/O)

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Fast I/O using BufferedReader
 * Recommended for competitive programming
 */
public class BufferedReaderIO {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read line
        System.out.print("Enter a number: ");
        String line = br.readLine();
        int num = Integer.parseInt(line);
        System.out.println("You entered: " + num);
        
        // Read multiple integers from one line
        System.out.println("Enter 5 numbers (space-separated):");
        String[] parts = br.readLine().split(" ");
        int[] arr = new int[parts.length];
        
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        
        System.out.print("Array: ");
        for (int num2 : arr) {
            System.out.print(num2 + " ");
        }
        
        br.close();
    }
}
```

### Example 3: Fast I/O Template for Competitive Programming

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Fast I/O Template for Competitive Programming
 */
public class FastIO {
    
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        int nextInt() {
            return Integer.parseInt(next());
        }
        
        long nextLong() {
            return Long.parseLong(next());
        }
        
        double nextDouble() {
            return Double.parseDouble(next());
        }
        
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        
        // Read number of test cases
        int t = sc.nextInt();
        
        while (t-- > 0) {
            // Read array size
            int n = sc.nextInt();
            
            // Read array
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            
            // Process (example: sum)
            long sum = 0;
            for (int num : arr) {
                sum += num;
            }
            
            // Output
            out.println("Sum: " + sum);
        }
        
        out.flush();
        out.close();
    }
}
```

### Example 4: Reading 2D Array

```java
import java.util.Scanner;

/**
 * Reading 2D Array/Matrix
 */
public class MatrixIO {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read matrix dimensions
        System.out.print("Enter rows and columns: ");
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        
        // Create matrix
        int[][] matrix = new int[rows][cols];
        
        // Read matrix elements
        System.out.println("Enter matrix elements:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        
        // Print matrix
        System.out.println("\nMatrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        sc.close();
    }
}
```

### Example 5: Reading Multiple Test Cases

```java
import java.util.Scanner;

/**
 * Handling Multiple Test Cases
 * Common in competitive programming
 */
public class MultipleTestCases {
    
    // Solve one test case
    public static void solve() {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        // Example: find max
        int max = arr;
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        
        System.out.println("Maximum: " + max);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read number of test cases
        int t = sc.nextInt();
        
        // Process each test case
        while (t-- > 0) {
            solve();
        }
        
        sc.close();
    }
}
```

### Example 6: Using StringBuilder for Fast Output

```java
import java.util.Scanner;

/**
 * Fast Output using StringBuilder
 */
public class FastOutput {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        // Build output in StringBuilder
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= n; i++) {
            sb.append(i).append(" ");
            
            // Add newline after every 10 numbers
            if (i % 10 == 0) {
                sb.append("\n");
            }
        }
        
        // Print all at once
        System.out.println(sb.toString());
        
        sc.close();
    }
}
```

---

## 📝 Practice Problems

| Problem | I/O Type | Difficulty |
|---------|----------|------------|
| Sum of Array | Basic | Easy |
| Find Maximum | Basic | Easy |
| Matrix Addition | 2D Array | Easy |
| Reverse Array | Array | Easy |
| Count Frequencies | HashMap | Medium |

---

## ✅ Key Takeaways

1. **Use BufferedReader** for large inputs (>10⁵)
2. **Use StringBuilder** for large outputs
3. **Scanner is fine** for small inputs (<10⁴)
4. **Always close** your readers/scanners
5. **Handle exceptions** properly

---

**Previous:** [Recursion Basics](./02-recursion-basics.md)  
**Next:** [Built-in Data Structures](./04-built-in-data-structures.md)