# 🔢 Basic Mathematics for DSA

## 📚 Theory

### Why Mathematics in DSA?

- Many problems require **number theory** concepts
- **Modular arithmetic** is essential for large number problems
- **Prime numbers** appear frequently in competitive programming
- **Bit manipulation** is a powerful optimization technique

---

## 💻 Java Code Examples

### Topic 1: GCD and LCM

```java
/**
 * GCD (Greatest Common Divisor) and LCM (Least Common Multiple)
 * 
 * GCD: Largest number that divides both numbers
 * LCM: Smallest number that is divisible by both numbers
 * 
 * Formula: LCM(a, b) = (a * b) / GCD(a, b)
 */
public class GCDAndLCM {
    
    // GCD using Euclidean Algorithm - O(log min(a, b))
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    // Iterative GCD
    public static int gcdIterative(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // LCM using GCD
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    
    // LCM of array
    public static int lcmOfArray(int[] arr) {
        int result = arr;
        for (int i = 1; i < arr.length; i++) {
            result = lcm(result, arr[i]);
        }
        return result;
    }
    
    public static void main(String[] args) {
        int a = 12, b = 18;
        
        System.out.println("GCD(" + a + ", " + b + ") = " + gcd(a, b));
        System.out.println("GCD Iterative: " + gcdIterative(a, b));
        System.out.println("LCM(" + a + ", " + b + ") = " + lcm(a, b));
        
        // LCM of array
        int[] arr = {2, 3, 4, 5};
        System.out.println("LCM of  = " + lcmOfArray(arr));[2][3][4][5]
        // 2*3*4*5 = 120, but LCM = 60
    }
}
```

### Topic 2: Prime Numbers

```java
import java.util.Arrays;

/**
 * Prime Number Algorithms
 */
public class PrimeNumbers {
    
    // Check if number is prime - O(√n)
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        
        // Check from 5 to √n
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    // Count primes from 1 to n
    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }
    
    // Sieve of Eratosthenes - O(n log log n)
    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime = isPrime = false;
        
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                // Mark all multiples of i as not prime
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        return isPrime;
    }
    
    // Print all primes up to n
    public static void printPrimes(int n) {
        boolean[] isPrime = sieveOfEratosthenes(n);
        
        System.out.print("Primes up to " + n + ": ");
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    
    // Count primes using Sieve
    public static int countPrimesSieve(int n) {
        boolean[] isPrime = sieveOfEratosthenes(n);
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
    
    // Prime factorization
    public static void primeFactorization(int n) {
        System.out.print("Prime factors of " + n + ": ");
        
        // Handle 2
        while (n % 2 == 0) {
            System.out.print(2 + " ");
            n /= 2;
        }
        
        // Handle odd numbers from 3
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            }
        }
        
        // If n is still > 1, then it's a prime
        if (n > 1) {
            System.out.print(n);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Test isPrime
        System.out.println("Is 17 prime? " + isPrime(17));
        System.out.println("Is 20 prime? " + isPrime(20));
        
        // Count primes
        int n = 30;
        System.out.println("Count of primes up to " + n + ": " + countPrimes(n));
        
        // Sieve
        printPrimes(50);
        System.out.println("Count using Sieve: " + countPrimesSieve(50));
        
        // Prime factorization
        primeFactorization(84);
        // 84 = 2 * 2 * 3 * 7
    }
}
```

### Topic 3: Modular Arithmetic

```java
/**
 * Modular Arithmetic
 * 
 * Important for problems with large numbers
 * (a + b) % m = ((a % m) + (b % m)) % m
 * (a - b) % m = ((a % m) - (b % m) + m) % m
 * (a * b) % m = ((a % m) * (b % m)) % m
 */
public class ModularArithmetic {
    
    static final int MOD = 1_000_000_007; // Common modulo
    
    // Modular Addition
    public static int modAdd(int a, int b, int m) {
        return ((a % m) + (b % m)) % m;
    }
    
    // Modular Subtraction (handle negative result)
    public static int modSubtract(int a, int b, int m) {
        return ((a % m) - (b % m) + m) % m;
    }
    
    // Modular Multiplication
    public static int modMultiply(int a, int b, int m) {
        return ((a % m) * (b % m)) % m;
    }
    
    // Modular Exponentiation - O(log n)
    public static long modPower(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        
        while (exp > 0) {
            // If exp is odd, multiply base with result
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            // exp is even
            exp = exp >> 1; // exp = exp / 2
            base = (base * base) % mod;
        }
        
        return result;
    }
    
    // Modular Inverse using Fermat's Little Theorem
    // Only works when mod is prime
    public static long modInverse(long a, long mod) {
        return modPower(a, mod - 2, mod);
    }
    
    // Modular Division
    public static long modDivide(long a, long b, long mod) {
        return (a * modInverse(b, mod)) % mod;
    }
    
    // Factorial with modulo
    public static long factorialMod(int n, int mod) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % mod;
        }
        return result;
    }
    
    // nCr with modulo (using precomputed factorials)
    public static long nCrMod(int n, int r, int mod) {
        if (r > n) {
            return 0;
        }
        
        long[] fact = new long[n + 1];
        fact = 1;
        
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % mod;
        }
        
        // nCr = n! / (r! * (n-r)!)
        long numerator = fact[n];
        long denominator = (fact[r] * fact[n - r]) % mod;
        
        return (numerator * modInverse(denominator, mod)) % mod;
    }
    
    public static void main(String[] args) {
        int a = 15, b = 7, m = 10;
        
        System.out.println("Modular Addition: (" + a + " + " + b + ") % " + m + " = " + modAdd(a, b, m));
        System.out.println("Modular Subtraction: (" + a + " - " + b + ") % " + m + " = " + modSubtract(a, b, m));
        System.out.println("Modular Multiplication: (" + a + " * " + b + ") % " + m + " = " + modMultiply(a, b, m));
        
        // Modular Exponentiation
        long base = 2, exp = 10;
        System.out.println("\n" + base + "^" + exp + " % " + MOD + " = " + modPower(base, exp, MOD));
        
        // Factorial with modulo
        int n = 20;
        System.out.println(n + "! % " + MOD + " = " + factorialMod(n, MOD));
        
        // nCr with modulo
        System.out.println("10C3% " + MOD + " = " + nCrMod(10, 3, MOD));
        // 10C3 = 120
    }
}
```

### Topic 4: Bit Manipulation Basics

```java
/**
 * Bit Manipulation
 * 
 * Important Operators:
 * &  - AND
 * |  - OR
 * ^  - XOR
 * ~  - NOT
 * << - Left Shift
 * >> - Right Shift
 * >>> - Unsigned Right Shift
 */
public class BitManipulation {
    
    // Check if number is even
    public static boolean isEven(int n) {
        return (n & 1) == 0;
    }
    
    // Check if number is odd
    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }
    
    // Check if kth bit is set
    public static boolean isKthBitSet(int n, int k) {
        return ((n & (1 << k)) != 0);
    }
    
    // Set kth bit
    public static int setKthBit(int n, int k) {
        return (n | (1 << k));
    }
    
    // Unset kth bit
    public static int unsetKthBit(int n, int k) {
        return (n & ~(1 << k));
    }
    
    // Toggle kth bit
    public static int toggleKthBit(int n, int k) {
        return (n ^ (1 << k));
    }
    
    // Count set bits (naive) - O(number of bits)
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1);
            n >>= 1;
        }
        return count;
    }
    
    // Count set bits (Brian Kernighan's) - O(number of set bits)
    public static int countSetBitsOptimized(int n) {
        int count = 0;
        while (n > 0) {
            n = n & (n - 1); // Removes the rightmost set bit
            count++;
        }
        return count;
    }
    
    // Count set bits using built-in
    public static int countSetBitsBuiltIn(int n) {
        return Integer.bitCount(n);
    }
    
    // Check if number is power of 2
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
    
    // Find position of rightmost set bit
    public static int rightmostSetBit(int n) {
        if (n == 0) {
            return -1;
        }
        return Integer.numberOfTrailingZeros(n) + 1;
    }
    
    // Remove rightmost set bit
    public static int removeRightmostSetBit(int n) {
        return n & (n - 1);
    }
    
    // Swap two numbers without temp variable
    public static void swap(int[] arr) {
        arr = arr ^ arr;
        arr = arr ^ arr;
        arr = arr ^ arr;
    }
    
    // Find XOR of all numbers from 1 to n
    public static int xorFrom1ToN(int n) {
        int remainder = n % 4;
        if (remainder == 0) {
            return n;
        } else if (remainder == 1) {
            return 1;
        } else if (remainder == 2) {
            return n + 1;
        } else {
            return 0;
        }
    }
    
    // Find single number (appears once, others appear twice)
    public static int findSingleNumber(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }
    
    // Print binary representation
    public static void printBinary(int n) {
        System.out.print("Binary of " + n + ": ");
        for (int i = 31; i >= 0; i--) {
            int k = n >> i;
            if ((k & 1) == 1) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int n = 13; // Binary: 1101
        
        System.out.println("Number: " + n);
        printBinary(n);
        
        System.out.println("\nIs even? " + isEven(n));
        System.out.println("Is odd? " + isOdd(n));
        
        int k = 2;
        System.out.println("\nIs " + k + "th bit set? " + isKthBitSet(n, k));
        
        System.out.println("\nSet bit " + k + ": " + setKthBit(n, k));
        printBinary(setKthBit(n, k));
        
        System.out.println("\nUnset bit " + k + ": " + unsetKthBit(n, k));
        printBinary(unsetKthBit(n, k));
        
        System.out.println("\nToggle bit " + k + ": " + toggleKthBit(n, k));
        printBinary(toggleKthBit(n, k));
        
        System.out.println("\nCount set bits: " + countSetBits(n));
        System.out.println("Count set bits (optimized): " + countSetBitsOptimized(n));
        System.out.println("Count set bits (built-in): " + countSetBitsBuiltIn(n));
        
        System.out.println("\nIs power of 2? " + isPowerOfTwo(16));
        System.out.println("Is power of 2? " + isPowerOfTwo(13));
        
        System.out.println("\nRightmost set bit position: " + rightmostSetBit(n));
        
        // Swap
        int[] arr = {5, 10};
        System.out.println("\nBefore swap: " + arr + ", " + arr);
        swap(arr);
        System.out.println("After swap: " + arr + ", " + arr);
        
        // XOR from 1 to n
        System.out.println("\nXOR from 1 to 10: " + xorFrom1ToN(10));
        
        // Find single number
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println("\nSingle number: " + findSingleNumber(nums));
    }
}
```

### Topic 5: Advanced Bit Manipulation

```java
/**
 * Advanced Bit Manipulation Problems
 */
public class AdvancedBitManipulation {
    
    // Find two numbers that appear once (others appear twice)
    public static int[] findTwoSingleNumbers(int[] arr) {
        // XOR of all numbers
        int xor = 0;
        for (int num : arr) {
            xor ^= num;
        }
        
        // Find rightmost set bit
        int rightmostSetBit = xor & -xor;
        
        // Divide into two groups and XOR separately
        int num1 = 0, num2 = 0;
        for (int num : arr) {
            if ((num & rightmostSetBit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        
        return new int[]{num1, num2};
    }
    
    // Check if number is power of 4
    public static boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        // Check if power of 2 and set bit is at even position
        return (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
    
    // Reverse bits of a number
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= (n & 1);
            n >>= 1;
        }
        return result;
    }
    
    // Count total set bits from 1 to n
    public static int countTotalSetBits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += Integer.bitCount(i);
        }
        return count;
    }
    
    // Find XOR of subarray
    public static int xorSubarray(int[] arr, int left, int right) {
        int xor = 0;
        for (int i = left; i <= right; i++) {
            xor ^= arr[i];
        }
        return xor;
    }
    
    // Maximum XOR of two numbers in array
    public static int findMaximumXOR(int[] arr) {
        int maxXOR = 0;
        int mask = 0;
        
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            
            java.util.HashSet<Integer> set = new java.util.HashSet<>();
            for (int num : arr) {
                set.add(num & mask);
            }
            
            int candidate = maxXOR | (1 << i);
            for (int prefix : set) {
                if (set.contains(candidate ^ prefix)) {
                    maxXOR = candidate;
                    break;
                }
            }
        }
        
        return maxXOR;
    }
    
    public static void main(String[] args) {
        // Find two single numbers
        int[] arr1 = {1, 2, 1, 3, 2, 5};
        int[] result = findTwoSingleNumbers(arr1);
        System.out.println("Two single numbers: " + result + ", " + result);
        
        // Check power of 4
        System.out.println("\nIs 16 power of 4? " + isPowerOfFour(16));
        System.out.println("Is 8 power of 4? " + isPowerOfFour(8));
        
        // Reverse bits
        int n = 13; // Binary: 0000...1101
        System.out.println("\nNumber: " + n);
        System.out.println("Reversed bits: " + reverseBits(n));
        
        // Count total set bits
        System.out.println("\nTotal set bits from 1 to 10: " + countTotalSetBits(10));
        
        // XOR subarray
        int[] arr2 = {1, 3, 4, 8};
        System.out.println("\nXOR of subarray: " + xorSubarray(arr2, 0, 2));[0][2]
    }
}
```

---

## 📝 Practice Problems

| Topic | Problem | Difficulty | Link |
|-------|---------|------------|------|
| GCD/LCM | GCD of Array | Easy | GeeksforGeeks |
| Primes | Count Primes | Easy | LeetCode 204 |
| Primes | Sieve of Eratosthenes | Medium | Practice |
| Modular | Power Function | Medium | LeetCode 50 |
| Modular | nCr with Modulo | Hard | Practice |
| Bits | Single Number | Easy | LeetCode 136 |
| Bits | Single Number II | Medium | LeetCode 137 |
| Bits | Number of 1 Bits | Easy | LeetCode 191 |
| Bits | Reverse Bits | Easy | LeetCode 190 |
| Bits | Counting Bits | Medium | LeetCode 338 |

---

## ✅ Key Takeaways

1. **GCD/LCM** - Use Euclidean algorithm for GCD
2. **Primes** - Sieve of Eratosthenes is most efficient
3. **Modular Arithmetic** - Always apply modulo at each step
4. **Bit Manipulation** - XOR is powerful for finding unique elements
5. **Power of 2** - Check using `n & (n-1) == 0`
6. **Modular Exponentiation** - Use binary exponentiation O(log n)

---

## 🎯 Phase 1 Complete!

Congratulations! You've completed the **Foundations** phase. Now you're ready for:

**Next Phase:** [Data Structures - Arrays & Strings](../02-data-structures/arrays-strings/theory.md)

---

## 📊 Phase 1 Checklist

- [x] Time & Space Complexity
- [x] Recursion Basics
- [x] Input/Output Handling
- [x] Built-in Data Structures
- [x] Mathematics for DSA

---

**Previous:** [Built-in Data Structures](./04-built-in-data-structures.md)  
**Next:** [Arrays & Strings Theory](../02-data-structures/arrays-strings/theory.md)