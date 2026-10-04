# 🔢 Bit Manipulation - Complete Guide

## 📚 Theory

### What is Bit Manipulation?

**Bit Manipulation** is the process of applying logical operations on bits (0s and 1s) to achieve desired results. It's extremely fast and space-efficient.

### Why Learn Bit Manipulation?

1. **Speed** - Bitwise operations are fastest CPU operations
2. **Space Efficiency** - Can store multiple flags in single integer
3. **Problem Solving** - Many problems have elegant bit manipulation solutions
4. **Interviews** - Common topic in FAANG interviews
5. **System Design** - Used in compression, encryption, error detection

### Basic Bitwise Operators

| Operator | Name | Description | Example |
|----------|------|-------------|---------|
| & | AND | 1 if both bits are 1 | 5 & 3 = 1 |
| \| | OR | 1 if at least one bit is 1 | 5 \| 3 = 7 |
| ^ | XOR | 1 if bits are different | 5 ^ 3 = 6 |
| ~ | NOT | Inverts all bits | ~5 = -6 |
| << | Left Shift | Shifts bits left | 5 << 1 = 10 |
| >> | Right Shift | Shifts bits right | 5 >> 1 = 2 |
| >>> | Unsigned Right Shift | Shifts right, fills with 0 | -5 >>> 1 |

### Bitwise Truth Tables

#### AND (&): OR (|): XOR (^): NOT (~):
*  0 & 0 = 0 0 | 0 = 0 0 ^ 0 = 0 ~0 = 1
*  0 & 1 = 0 0 | 1 = 1 0 ^ 1 = 1 ~1 = 0
*  1 & 0 = 0 1 | 0 = 1 1 ^ 0 = 1
*  1 & 1 = 1 1 | 1 = 1 1 ^ 1 = 0


### Important Bit Properties


x & 0 = 0 (AND with 0 clears all bits)

x | 0 = x (OR with 0 keeps x unchanged)

x ^ 0 = x (XOR with 0 keeps x unchanged)

x & x = x (AND with itself)

x | x = x (OR with itself)

x ^ x = 0 (XOR with itself gives 0)

x & ~x = 0 (AND with complement gives 0)

x | ~x = -1 (OR with complement gives all 1s)

x ^ ~x = -1 (XOR with complement gives all 1s)

x & (x-1) = removes rightmost set bit

x & (-x) = isolates rightmost set bit


### Common Bit Patterns


Check if kth bit is set: (n & (1 << k)) != 0

Set kth bit: n | (1 << k)

Unset kth bit: n & ~(1 << k)

Toggle kth bit: n ^ (1 << k)

Clear all bits from k onwards: n & ((1 << k) - 1)

Clear all bits up to k: n & ~((1 << (k+1)) - 1)

Extract rightmost set bit: x & (-x)

Remove rightmost set bit: x & (x - 1)

Check if power of 2: (n & (n-1)) == 0 && n > 0

Swap two numbers: a ^= b; b ^= a; a ^= b;


---

## 💻 Java Code Examples

### Problem 1: Check if Kth Bit is Set

```java
/**
 * Problem: Check if Kth Bit is Set
 * 
 * Visual:
 * n = 20 = 10100 (binary)
 *        43210   (bit positions)
 * 
 * Check if 2nd bit is set:
 * 1 << 2 = 00100
 * 20 & 4 = 10100 & 00100 = 00100 ≠ 0
 * 2nd bit is SET ✓
 * 
 * Check if 1st bit is set:
 * 1 << 1 = 00010
 * 20 & 2 = 10100 & 00010 = 00000 = 0
 * 1st bit is NOT SET ✗
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class CheckKthBit {
    
    // Check if kth bit is set
    public static boolean isKthBitSet(int n, int k) {
        return (n & (1 << k)) != 0;
    }
    
    // Set kth bit
    public static int setKthBit(int n, int k) {
        return n | (1 << k);
    }
    
    // Unset kth bit
    public static int unsetKthBit(int n, int k) {
        return n & ~(1 << k);
    }
    
    // Toggle kth bit
    public static int toggleKthBit(int n, int k) {
        return n ^ (1 << k);
    }
    
    // Print binary representation
    public static void printBinary(int n) {
        System.out.print("Binary of " + n + ": ");
        for (int i = 31; i >= 0; i--) {
            int bit = (n >> i) & 1;
            System.out.print(bit);
            if (i % 4 == 0 && i > 0) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int n = 20; // 10100
        
        System.out.println("Number: " + n);
        printBinary(n);
        
        System.out.println("\nChecking bits:");
        for (int k = 0; k < 8; k++) {
            System.out.println("Bit " + k + " is " + 
                              (isKthBitSet(n, k) ? "SET" : "NOT SET"));
        }
        
        // Set bit
        System.out.println("\nAfter setting bit 1: " + setKthBit(n, 1));
        printBinary(setKthBit(n, 1));
        
        // Unset bit
        System.out.println("\nAfter unsetting bit 2: " + unsetKthBit(n, 2));
        printBinary(unsetKthBit(n, 2));
        
        // Toggle bit
        System.out.println("\nAfter toggling bit 3: " + toggleKthBit(n, 3));
        printBinary(toggleKthBit(n, 3));
    }
}
```

---

### Problem 2: Count Set Bits

```java
/**
 * Problem: Count Set Bits (Population Count)
 * LeetCode: 191
 * 
 * Visual:
 * n = 29 = 11101 (binary)
 * 
 * Method 1: Check each bit
 * 11101 → 4 set bits
 * 
 * Method 2: Brian Kernighan's Algorithm
 * n = 11101 (29)
 * n-1 = 11100 (28)
 * n & (n-1) = 11100 (28) - removed rightmost set bit
 * 
 * n = 11100 (28)
 * n-1 = 11011 (27)
 * n & (n-1) = 11000 (24) - removed rightmost set bit
 * 
 * n = 11000 (24)
 * n-1 = 10111 (23)
 * n & (n-1) = 10000 (16) - removed rightmost set bit
 * 
 * n = 10000 (16)
 * n-1 = 01111 (15)
 * n & (n-1) = 00000 (0) - removed rightmost set bit
 * 
 * Total iterations: 4 = number of set bits ✓
 * 
 * Time Complexity:
 * - Method 1: O(log n) or O(number of bits)
 * - Method 2: O(number of set bits)
 * 
 * Space Complexity: O(1)
 */
public class CountSetBits {
    
    // Method 1: Check each bit
    public static int countSetBitsMethod1(int n) {
        int count = 0;
        
        while (n > 0) {
            count += (n & 1); // Add LSB
            n >>= 1; // Right shift
        }
        
        return count;
    }
    
    // Method 2: Brian Kernighan's Algorithm
    public static int countSetBitsKernighan(int n) {
        int count = 0;
        
        while (n > 0) {
            n = n & (n - 1); // Remove rightmost set bit
            count++;
        }
        
        return count;
    }
    
    // Method 3: Built-in method
    public static int countSetBitsBuiltIn(int n) {
        return Integer.bitCount(n);
    }
    
    // Count set bits from 1 to n
    public static int countSetBitsFrom1ToN(int n) {
        int count = 0;
        
        for (int i = 1; i <= n; i++) {
            count += countSetBitsKernighan(i);
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int n = 29; // 11101
        
        System.out.println("Number: " + n);
        System.out.println("Binary: " + Integer.toBinaryString(n));
        
        System.out.println("\nCount of set bits:");
        System.out.println("Method 1: " + countSetBitsMethod1(n));
        System.out.println("Kernighan's: " + countSetBitsKernighan(n));
        System.out.println("Built-in: " + countSetBitsBuiltIn(n));
        
        // Count from 1 to n
        System.out.println("\nTotal set bits from 1 to " + n + ": " + 
                          countSetBitsFrom1ToN(n));
        
        // Test with more numbers
        int[] testCases = {0, 1, 5, 7, 15, 31, 29, 63};
        System.out.println("\nMore examples:");
        for (int num : testCases) {
            System.out.println(num + " (" + Integer.toBinaryString(num) + ") → " + 
                              countSetBitsBuiltIn(num) + " set bits");
        }
    }
}
```

---

### Problem 3: Check if Power of 2

```java
/**
 * Problem: Check if Number is Power of 2
 * LeetCode: 231
 * 
 * Visual:
 * Powers of 2:
 * 2⁰ = 1  = 00001
 * 2¹ = 2  = 00010
 * 2² = 4  = 00100
 * 2³ = 8  = 01000
 * 2⁴ = 16 = 10000
 * 
 * Observation: Only ONE bit is set!
 * 
 * Method 1: n & (n-1) removes rightmost set bit
 * If result is 0, then only one bit was set
 * 
 * Example: n = 8 (1000)
 * n-1 = 7 (0111)
 * n & (n-1) = 1000 & 0111 = 0000 ✓
 * 
 * Example: n = 6 (0110)
 * n-1 = 5 (0101)
 * n & (n-1) = 0110 & 0101 = 0100 ≠ 0 ✗
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class PowerOfTwo {
    
    // Method 1: Using n & (n-1)
    public static boolean isPowerOfTwo(int n) {
        // Must be positive and have only one bit set
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    // Method 2: Count set bits
    public static boolean isPowerOfTwoCount(int n) {
        if (n <= 0) {
            return false;
        }
        
        int count = 0;
        while (n > 0) {
            count += (n & 1);
            if (count > 1) {
                return false; // More than one bit set
            }
            n >>= 1;
        }
        
        return count == 1;
    }
    
    // Method 3: Using n & (-n)
    public static boolean isPowerOfTwoNegative(int n) {
        return n > 0 && (n & (-n)) == n;
    }
    
    // Variation: Check if Power of 4
    // LeetCode: 342
    public static boolean isPowerOfFour(int n) {
        // Must be power of 2 AND the set bit must be at even position
        // 4⁰ = 1  = 00001 (bit 0)
        // 4¹ = 4  = 00100 (bit 2)
        // 4² = 16 = 10000 (bit 4)
        // Mask: 0x55555555 = 01010101010101010101010101010101
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
    
    // Variation: Check if Power of 3
    // LeetCode: 326
    public static boolean isPowerOfThree(int n) {
        // 3^19 = 1162261467 is largest power of 3 in int range
        // If n is power of 3, it must divide 3^19
        return n > 0 && 1162261467% n == 0;
    }
    
    public static void main(String[] args) {
        int[] testCases = {0, 1, 2, 3, 4, 5, 8, 16, 31, 32, 64, 100};
        
        System.out.println("Power of 2 check:");
        for (int num : testCases) {
            System.out.println(num + " → " + isPowerOfTwo(num));
        }
        
        // Power of 4
        System.out.println("\nPower of 4 check:");
        int[] testCases2 = {1, 4, 16, 64, 256, 5, 12, 18};
        for (int num : testCases2) {
            System.out.println(num + " → " + isPowerOfFour(num));
        }
        
        // Power of 3
        System.out.println("\nPower of 3 check:");
        int[] testCases3 = {1, 3, 9, 27, 81, 2, 6, 10};
        for (int num : testCases3) {
            System.out.println(num + " → " + isPowerOfThree(num));
        }
    }
}
```

---

### Problem 4: Single Number

```java
/**
 * Problem: Single Number
 * LeetCode: 136
 * 
 * Visual:
 * nums =[1][2][4]
 * 
 * XOR Properties:
 * - x ^ 0 = x
 * - x ^ x = 0
 * - XOR is commutative and associative
 * 
 * XOR all elements:
 * 4 ^ 1 ^ 2 ^ 1 ^ 2
 * = 4 ^ (1 ^ 1) ^ (2 ^ 2)  (rearrange)
 * = 4 ^ 0 ^ 0              (x ^ x = 0)
 * = 4                      (x ^ 0 = x)
 * 
 * Result: 4 (the single number)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class SingleNumber {
    
    // Find single number (appears once, others appear twice)
    public static int singleNumber(int[] nums) {
        int result = 0;
        
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
    
    // Variation: Single Number II (others appear 3 times)
    // LeetCode: 137
    public static int singleNumberII(int[] nums) {
        int ones = 0, twos = 0;
        
        for (int num : nums) {
            // Add to ones if not in twos
            ones = (ones ^ num) & ~twos;
            
            // Add to twos if not in ones
            twos = (twos ^ num) & ~ones;
        }
        
        return ones; // ones contains the unique number
    }
    
    // Variation: Single Number III (two numbers appear once)
    // LeetCode: 261
    public static int[] singleNumberIII(int[] nums) {
        // XOR all numbers
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        
        // Find rightmost set bit (differs between the two numbers)
        int diffBit = xor & (-xor);
        
        // Divide into two groups and XOR separately
        int[] result = new int;[1]
        for (int num : nums) {
            if ((num & diffBit) == 0) {
                result ^= num;
            } else {
                result ^= num;[2]
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Single Number
        int[] nums1 = {4, 1, 2, 1, 2};
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        System.out.println("Single number: " + singleNumber(nums1));
        
        // Single Number II
        int[] nums2 = {2, 2, 3, 2};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        System.out.println("Single number (appears 3 times): " + singleNumberII(nums2));
        
        // Single Number III
        int[] nums3 = {1, 2, 1, 3, 2, 5};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums3));
        int[] result = singleNumberIII(nums3);
        System.out.println("Two single numbers: " + result + ", " + result);[2]
    }
}
```

---

### Problem 5: Reverse Bits

```java
/**
 * Problem: Reverse Bits
 * LeetCode: 190
 * 
 * Visual:
 * n = 43261596 = 00000010100101000001111010011100 (binary)
 * 
 * Reverse:
 * result = 00111001011110000010100101000000 = 964176192
 * 
 * Method:
 * Extract each bit from right to left
 * Add to result from left to right
 * 
 * Time Complexity: O(1) - always 32 bits
 * Space Complexity: O(1)
 */
public class ReverseBits {
    
    // Method 1: Bit by bit
    public static int reverseBits(int n) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            // Left shift result
            result <<= 1;
            
            // Add LSB of n to result
            result |= (n & 1);
            
            // Right shift n
            n >>= 1;
        }
        
        return result;
    }
    
    // Method 2: Using built-in
    public static int reverseBitsBuiltIn(int n) {
        return Integer.reverse(n);
    }
    
    // Method 3: Divide and conquer (faster)
    public static int reverseBitsFast(int n) {
        // Swap odd and even bits
        n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);
        
        // Swap consecutive pairs
        n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);
        
        // Swap nibbles
        n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);
        
        // Swap bytes
        n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);
        
        // Swap 2-byte groups
        n = (n >>> 16) | (n << 16);
        
        return n;
    }
    
    public static void main(String[] args) {
        int n = 43261596;
        
        System.out.println("Number: " + n);
        System.out.println("Binary: " + Integer.toBinaryString(n));
        
        System.out.println("\nReversed: " + reverseBits(n));
        System.out.println("Binary: " + Integer.toBinaryString(reverseBits(n)));
        
        // Verify with built-in
        System.out.println("\nUsing built-in: " + reverseBitsBuiltIn(n));
        System.out.println("Using fast method: " + reverseBitsFast(n));
    }
}
```

---

### Problem 6: Sum of Two Integers

```java
/**
 * Problem: Sum of Two Integers (without + or -)
 * LeetCode: 371
 * 
 * Visual:
 * a = 2 = 010
 * b = 3 = 011
 * 
 * XOR gives sum without carry:
 * 010 ^ 011 = 001 (1)
 * 
 * AND gives carry:
 * 010 & 011 = 010 (2)
 * 
 * Shift carry left:
 * 010 << 1 = 100 (4)
 * 
 * Repeat:
 * a = 001 (1)
 * b = 100 (4)
 * 
 * XOR: 001 ^ 100 = 101 (5)
 * AND: 001 & 100 = 000 (0)
 * 
 * No carry left, result = 101 (5) ✓
 * 
 * Time Complexity: O(1) - max 32 iterations
 * Space Complexity: O(1)
 */
public class SumOfTwoIntegers {
    
    // Add without + or -
    public static int getSum(int a, int b) {
        while (b != 0) {
            // Carry contains common set bits
            int carry = a & b;
            
            // Sum without carry
            a = a ^ b;
            
            // Carry is shifted left by 1
            b = carry << 1;
        }
        
        return a;
    }
    
    // Subtract without -
    public static int getDifference(int a, int b) {
        while (b != 0) {
            // Borrow contains bits where a has 0 and b has 1
            int borrow = (~a) & b;
            
            // Subtract without borrow
            a = a ^ b;
            
            // Borrow is shifted left by 1
            b = borrow << 1;
        }
        
        return a;
    }
    
    // Multiply using bit manipulation
    public static int multiply(int a, int b) {
        int result = 0;
        
        while (b != 0) {
            // If LSB of b is 1, add a to result
            if ((b & 1) != 0) {
                result = getSum(result, a);
            }
            
            // Double a
            a <<= 1;
            
            // Halve b
            b >>>= 1;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int a = 2, b = 3;
        
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Sum: " + getSum(a, b));
        System.out.println("Difference (a-b): " + getDifference(a, b));
        
        // Multiply
        int x = 5, y = 3;
        System.out.println("\n" + x + " × " + y + " = " + multiply(x, y));
        
        // More examples
        System.out.println("\nMore examples:");
        System.out.println("10 + 5 = " + getSum(10, 5));
        System.out.println("20 - 8 = " + getDifference(20, 8));
        System.out.println("7 × 6 = " + multiply(7, 6));
    }
}
```

---

### Problem 7: Number of 1 Bits (Hamming Weight)

```java
/**
 * Problem: Number of 1 Bits (Hamming Weight)
 * LeetCode: 191
 * 
 * Visual:
 * n = 11 = 1011 (binary)
 * 
 * Method 1: Check each bit
 * 1011 → 3 set bits
 * 
 * Method 2: Brian Kernighan's
 * 1011 & 1010 = 1010 (removed bit 0)
 * 1010 & 1001 = 1000 (removed bit 1)
 * 1000 & 0111 = 0000 (removed bit 3)
 * 
 * 3 iterations = 3 set bits ✓
 * 
 * Time Complexity: O(number of set bits)
 * Space Complexity: O(1)
 */
public class NumberOfOneBits {
    
    // Method 1: Check each bit
    public static int hammingWeight(int n) {
        int count = 0;
        
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>>= 1; // Unsigned right shift
        }
        
        return count;
    }
    
    // Method 2: Brian Kernighan's Algorithm
    public static int hammingWeightKernighan(int n) {
        int count = 0;
        
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        
        return count;
    }
    
    // Method 3: Built-in
    public static int hammingWeightBuiltIn(int n) {
        return Integer.bitCount(n);
    }
    
    // Variation: Count set bits in range [m, n]
    public static int countSetBitsInRange(int m, int n) {
        int count = 0;
        
        for (int i = m; i <= n; i++) {
            count += Integer.bitCount(i);
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int n = 11; // 1011
        
        System.out.println("Number: " + n);
        System.out.println("Binary: " + Integer.toBinaryString(n));
        
        System.out.println("\nHamming weight:");
        System.out.println("Method 1: " + hammingWeight(n));
        System.out.println("Kernighan's: " + hammingWeightKernighan(n));
        System.out.println("Built-in: " + hammingWeightBuiltIn(n));
        
        // Count in range
        System.out.println("\nSet bits from 1 to 10: " + countSetBitsInRange(1, 10));
    }
}
```

---

### Problem 8: Missing Number

```java
/**
 * Problem: Missing Number
 * LeetCode: 268
 * 
 * Visual:
 * nums =[0][1][3]
 * n = 3
 * Complete:[1][2][3][0]
 * Missing: 2
 * 
 * XOR Approach:
 * XOR all indices: 0 ^ 1 ^ 2 ^ 3
 * XOR all elements: 3 ^ 0 ^ 1
 * XOR both: (0^1^2^3) ^ (3^0^1) = 2 ✓
 * 
 * Why it works:
 * - x ^ x = 0 (all present numbers cancel out)
 * - x ^ 0 = x (missing number remains)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MissingNumber {
    
    // Method 1: XOR approach
    public static int missingNumberXOR(int[] nums) {
        int xor = 0;
        int n = nums.length;
        
        // XOR all indices
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        
        // XOR all elements
        for (int num : nums) {
            xor ^= num;
        }
        
        return xor; // Missing number
    }
    
    // Method 2: Sum approach
    public static int missingNumberSum(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        
        for (int num : nums) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }
    
    // Variation: Find All Missing Numbers
    // LeetCode: 448
    public static java.util.List<Integer> findDisappearedNumbers(int[] nums) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        int n = nums.length;
        
        // Mark presence by making index negative
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        
        // Find indices with positive values
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Missing number (XOR): " + missingNumberXOR(nums));
        System.out.println("Missing number (Sum): " + missingNumberSum(nums));
        
        // Find all missing
        int[] nums2 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        System.out.println("Missing numbers: " + findDisappearedNumbers(nums2));
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Number of 1 Bits | Count Set Bits | LeetCode 191 |
| Power of Two | Bit Check | LeetCode 231 |
| Missing Number | XOR | LeetCode 268 |
| Single Number | XOR | LeetCode 136 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Single Number II | Bit Counting | LeetCode 137 |
| Single Number III | XOR Partition | LeetCode 261 |
| Sum of Two Integers | Bit Addition | LeetCode 371 |
| Reverse Bits | Bit Reversal | LeetCode 190 |
| Counting Bits | DP + Bits | LeetCode 338 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Minimum Number of Flips | Bit Manipulation | LeetCode 1529 |
| Find XOR Sum of All Pairs | XOR Properties | LeetCode 1835 |

---

## ✅ Key Takeaways

1. **XOR Properties** - x^x=0, x^0=x, commutative, associative
2. **n & (n-1)** - Removes rightmost set bit
3. **n & (-n)** - Isolates rightmost set bit
4. **Check kth bit** - (n & (1<<k)) != 0
5. **Power of 2** - (n & (n-1)) == 0 && n > 0
6. **Single Number** - XOR all elements
7. **Missing Number** - XOR indices and elements
8. **Brian Kernighan's** - Count set bits efficiently

---

**Previous:** [Prefix Sum](../prefix-sum/01-theory.md)  
**Next:** [Matrix Problems](../matrix/01-theory.md)