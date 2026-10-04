# 📐 Matrix Problems - Complete Guide

## 📚 Theory

### What are Matrix Problems?

**Matrix Problems** involve 2D arrays where we need to perform operations like traversal, transformation, searching, or pattern finding.

### Matrix Representation

2D Array:
[,

,

]

Rows: 3
Columns: 3
Element at row i, column j: matrix[i][j]



### Common Matrix Patterns

1. **Traversal** - Row-wise, column-wise, diagonal, spiral
2. **Rotation** - 90°, 180°, clockwise, anticlockwise
3. **Searching** - Binary search in sorted matrix
4. **Dynamic Programming** - Path finding, min/max sum
5. **In-Place Operations** - Transpose, rotate without extra space

### Important Matrix Concepts



1. **Matrix Dimensions:** rows × cols

2. **Diagonal Elements:** matrix[i][i]

3. **Anti-Diagonal:** matrix[i][n-1-i]

4. **Transpose: matrix[i][j]** ↔ matrix[j][i]

5. **Row-Major Order:** Row by row traversal

6. **Column-Major Order:** Column by column traversal



---

## 💻 Java Code Examples

### Problem 1: Spiral Traversal of Matrix

```java
/**
 * Problem: Spiral Matrix
 * LeetCode: 54
 * 
 * Visual:
 * Matrix:
 *[1][2][3]
 *[4][5][6]
 *[7][8][9]
 * 
 * Spiral Order:
 * → Right:[1][2][3]
 * ↓ Down:[6][9]
 * ← Left:[7][8]
 * ↑ Up:[1]
 * → Right:[2]
 * 
 * Result:[1][2][3][4][5][6][7][8][9]
 * 
 * Time Complexity: O(m × n)
 * Space Complexity: O(1) excluding result
 */
public class SpiralMatrix {
    
    public static java.util.List<Integer> spiralOrder(int[][] matrix) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        
        int rows = matrix.length;
        int cols = matrix.length;
        
        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;
        
        while (top <= bottom && left <= right) {
            // Traverse right
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;
            
            // Traverse down
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            
            // Traverse left (if rows remain)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--;
            }
            
            // Traverse up (if columns remain)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        
        return result;
    }
    
    // Print spiral order
    public static void printSpiral(int[][] matrix) {
        java.util.List<Integer> result = spiralOrder(matrix);
        System.out.println("Spiral Order: " + result);
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        
        printSpiral(matrix);
        
        // More examples
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        System.out.println("\nMatrix 2:");
        for (int[] row : matrix2) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        printSpiral(matrix2);
    }
}
```

---

### Problem 2: Rotate Image (90° Clockwise)

```java
/**
 * Problem: Rotate Image
 * LeetCode: 48
 * 
 * Visual:
 * Original:
 *[1][2][3]
 *[4][5][6]
 *[7][8][9]
 * 
 * Step 1: Transpose
 *[1][4][7]
 *[2][5][8]
 *[3][6][9]
 * 
 * Step 2: Reverse each row
 *[1][4][7]
 *[2][5][8]
 *[3][6][9]
 * 
 * Result (90° clockwise):
 *[1][4][7]
 *[2][5][8]
 *[3][6][9]
 * 
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
public class RotateImage {
    
    // Rotate 90 degrees clockwise
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Step 1: Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
    
    // Rotate 90 degrees anticlockwise
    public static void rotateAnticlockwise(int[][] matrix) {
        int n = matrix.length;
        
        // Step 1: Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Step 2: Reverse each column
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n / 2; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }
    }
    
    // Print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Original:");
        printMatrix(matrix);
        
        rotate(matrix);
        
        System.out.println("\nRotated 90° clockwise:");
        printMatrix(matrix);
        
        // Rotate back
        rotate(matrix);
        rotate(matrix);
        rotate(matrix);
        
        System.out.println("\nRotated 270° clockwise (90° anticlockwise):");
        printMatrix(matrix);
    }
}
```

---

### Problem 3: Set Matrix Zeroes

```java
/**
 * Problem: Set Matrix Zeroes
 * LeetCode: 73
 * 
 * Visual:
 * Original:
 *[0][1][3]
 *[4][5][6]
 *[7][8][9]
 * 
 * Step 1: Mark rows and cols with 0
 * row0 = true (has 0)
 * col1 = true (has 0)
 * 
 * Step 2: Set marked rows and cols to 0
 *[0]
 *[6][0]
 *[9][0]
 * 
 * Time Complexity: O(m × n)
 * Space Complexity: O(1)
 */
public class SetMatrixZeroes {
    
    // Approach 1: Using extra space
    public static void setZeroesExtraSpace(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length;
        
        boolean[] rowZero = new boolean[rows];
        boolean[] colZero = new boolean[cols];
        
        // Mark rows and cols
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }
        
        // Set zeroes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rowZero[i] || colZero[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    // Approach 2: In-place (O(1) space)
    public static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length;
        
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        
        // Check if first row has zero
        for (int j = 0; j < cols; j++) {
            if (matrix[j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        
        // Check if first column has zero
        for (int i = 0; i < rows; i++) {
            if (matrix[i] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        
        // Use first row and column as markers
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i] = 0;
                    matrix[j] = 0;
                }
            }
        }
        
        // Set zeroes based on markers
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i] == 0 || matrix[j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Set first row to zero if needed
        if (firstRowHasZero) {
            for (int j = 0; j < cols; j++) {
                matrix[j] = 0;
            }
        }
        
        // Set first column to zero if needed
        if (firstColHasZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i] = 0;
            }
        }
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Original:");
        printMatrix(matrix);
        
        setZeroes(matrix);
        
        System.out.println("\nAfter setting zeroes:");
        printMatrix(matrix);
    }
}
```

---

### Problem 4: Search in 2D Matrix

```java
/**
 * Problem: Search a 2D Matrix
 * LeetCode: 74
 * 
 * Visual:
 * Matrix (sorted row-wise and column-wise):
 *[1][3][5][7]
 *[10][11][16][20]
 *[23][30][34][50]
 * 
 * Approach 1: Binary Search (treat as 1D sorted array)
 * Total elements: 12
 * Target: 3
 * Index in 1D: 1
 * Row: 1 / 4 = 0
 * Col: 1 % 4 = 1
 * matrix = 3 ✓[3]
 * 
 * Approach 2: Start from top-right
 * matrix = 7 > 3, move left[4]
 * matrix = 5 > 3, move left[5]
 * matrix = 3 == 3 ✓[3]
 * 
 * Time Complexity:
 * - Approach 1: O(log(m×n))
 * - Approach 2: O(m+n)
 * 
 * Space Complexity: O(1)
 */
public class Search2DMatrix {
    
    // Approach 1: Binary Search (treat as 1D array)
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix.length;
        
        int left = 0, right = rows * cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = matrix[mid / cols][mid % cols];
            
            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    // Approach 2: Start from top-right
    public static boolean searchMatrixStaircase(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix.length;
        
        int row = 0, col = cols - 1;
        
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--; // Move left
            } else {
                row++; // Move down
            }
        }
        
        return false;
    }
    
    // Variation: Search in 2D Matrix II
    // LeetCode: 240
    public static boolean searchMatrixII(int[][] matrix, int target) {
        return searchMatrixStaircase(matrix, target);
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1,  3,  5,  7},
            {10, 11, 16, 20},
            {23, 30, 34, 50}
        };
        
        int[] targets = {3, 13, 23, 50, 100};
        
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nSearch results:");
        for (int target : targets) {
            System.out.println("Target " + target + ": " + 
                              searchMatrix(matrix, target));
        }
    }
}
```

---

### Problem 5: Diagonal Traversal

```java
/**
 * Problem: Diagonal Traverse
 * LeetCode: 498
 * 
 * Visual:
 * Matrix:
 *[1][2][3]
 *[4][5][6]
 *[7][8][9]
 * 
 * Diagonals:
 * /[3]
 * /[2][4]
 * /[5][7][3]
 * /[6][8]
 * /[6]
 * 
 * Zigzag order:
 *[1][2][3][4][5][6][7][8][9]
 * 
 * Time Complexity: O(m × n)
 * Space Complexity: O(1) excluding result
 */
public class DiagonalTraversal {
    
    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int;
        }
        
        int rows = matrix.length;
        int cols = matrix.length;
        int[] result = new int[rows * cols];
        
        int row = 0, col = 0;
        int direction = 1; // 1 for up-right, -1 for down-left
        
        for (int i = 0; i < rows * cols; i++) {
            result[i] = matrix[row][col];
            
            if (direction == 1) {
                // Moving up-right
                if (col == cols - 1) {
                    row++;
                    direction = -1;
                } else if (row == 0) {
                    col++;
                    direction = -1;
                } else {
                    row--;
                    col++;
                }
            } else {
                // Moving down-left
                if (row == rows - 1) {
                    col++;
                    direction = 1;
                } else if (col == 0) {
                    row++;
                    direction = 1;
                } else {
                    row++;
                    col--;
                }
            }
        }
        
        return result;
    }
    
    // Print all diagonals
    public static void printDiagonals(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length;
        
        System.out.println("All diagonals:");
        
        // Print diagonals starting from first row
        for (int j = 0; j < cols; j++) {
            System.out.print("[");
            for (int i = 0; i <= Math.min(j, rows - 1); i++) {
                System.out.print(matrix[i][j - i]);
                if (i < Math.min(j, rows - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        
        // Print diagonals starting from first column (excluding )
        for (int i = 1; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j <= Math.min(i, cols - 1); j++) {
                System.out.print(matrix[i - j][j]);
                if (j < Math.min(i, cols - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        
        int[] result = findDiagonalOrder(matrix);
        System.out.println("\nDiagonal traversal: " + 
                          java.util.Arrays.toString(result));
        
        printDiagonals(matrix);
    }
}
```

---

### Problem 6: Rotate Matrix by 180 Degrees

```java
/**
 * Problem: Rotate Matrix by 180 Degrees
 * 
 * Visual:
 * Original:
 *[1][2][3]
 *[4][5][6]
 *[7][8][9]
 * 
 * Rotate 180°:
 *[8][9][7]
 *[4][5][6]
 *[1][2][3]
 * 
 * Method: Reverse each row, then reverse order of rows
 * 
 * Time Complexity: O(m × n)
 * Space Complexity: O(1)
 */
public class RotateMatrix180 {
    
    public static void rotate180(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length;
        
        // Reverse each row
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][cols - 1 - j];
                matrix[i][cols - 1 - j] = temp;
            }
        }
        
        // Reverse order of rows
        for (int i = 0; i < rows / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[rows - 1 - i];
            matrix[rows - 1 - i] = temp;
        }
    }
    
    // Alternative: Swap elements directly
    public static void rotate180Direct(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length;
        
        for (int i = 0; i <= (rows - 1) / 2; i++) {
            for (int j = 0; j <= (cols - 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[rows - 1 - i][cols - 1 - j];
                matrix[rows - 1 - i][cols - 1 - j] = temp;
            }
        }
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Original:");
        printMatrix(matrix);
        
        rotate180(matrix);
        
        System.out.println("\nRotated 180°:");
        printMatrix(matrix);
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Transpose Matrix | Matrix | LeetCode 867 |
| Toeplitz Matrix | Diagonal Check | LeetCode 766 |
| Valid Sudoku | Matrix Validation | LeetCode 36 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Spiral Matrix | Traversal | LeetCode 54 |
| Rotate Image | In-Place Rotation | LeetCode 48 |
| Set Matrix Zeroes | In-Place Modification | LeetCode 73 |
| Search a 2D Matrix | Binary Search | LeetCode 74 |
| Diagonal Traverse | Zigzag Traversal | LeetCode 498 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Spiral Matrix II | Generation | LeetCode 59 |
| Search a 2D Matrix II | Staircase Search | LeetCode 240 |
| Maximal Square | DP on Matrix | LeetCode 221 |

---

## ✅ Key Takeaways

1. **Spiral Traversal** - Use 4 pointers (top, bottom, left, right)
2. **Matrix Rotation** - Transpose + reverse rows
3. **Set Zeroes** - Use first row/col as markers
4. **2D Binary Search** - Convert 2D index to 1D
5. **Staircase Search** - Start from top-right or bottom-left
6. **Diagonal Traversal** - Track direction, handle boundaries

---

**Previous:** [Bit Manipulation](../bit-manipulation/01-theory.md)  
**Next:** [Linked List Advanced](../linked-list/02-advanced.md)