# 🔤 String Problems - Complete Guide

## 📚 Theory

### What is a String?

A **String** is a sequence of characters. In Java, String is **immutable** (cannot be changed after creation).

### String vs StringBuilder

| Property | String | StringBuilder |
|----------|--------|---------------|
| Mutability | Immutable | Mutable |
| Performance | Slow for concatenation | Fast |
| Thread Safety | Yes | No |
| Memory | Creates new object | Modifies existing |

### Common String Operations

- **Length**: `str.length()` - O(1)
- **Char at**: `str.charAt(i)` - O(1)
- **Substring**: `str.substring(i, j)` - O(j-i)
- **Concatenate**: `str1 + str2` - O(n)
- **Compare**: `str1.equals(str2)` - O(n)
- **To Char Array**: `str.toCharArray()` - O(n)

---

## 💻 Java Code Examples

### Example 1: Basic String Operations

```java
/**
 * Basic String Operations
 */
public class BasicStringOperations {
    
    public static void main(String[] args) {
        String str = "Hello, World!";
        
        // Length
        System.out.println("Length: " + str.length());
        
        // Char at index
        System.out.println("Char at 0: " + str.charAt(0));
        
        // Substring
        System.out.println("Substring(0, 5): " + str.substring(0, 5));
        System.out.println("Substring(7): " + str.substring(7));
        
        // Index of
        System.out.println("Index of 'o': " + str.indexOf('o'));
        System.out.println("Index of 'World': " + str.indexOf("World"));
        
        // Replace
        System.out.println("Replace: " + str.replace("World", "DSA"));
        
        // Split
        String sentence = "Java is awesome";
        String[] words = sentence.split(" ");
        System.out.print("Words: ");
        for (String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();
        
        // To uppercase/lowercase
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Lowercase: " + str.toLowerCase());
        
        // Trim
        String withSpaces = "   Hello   ";
        System.out.println("Trimmed: '" + withSpaces.trim() + "'");
        
        // Contains
        System.out.println("Contains 'World': " + str.contains("World"));
        
        // StartsWith/EndsWith
        System.out.println("Starts with 'Hello': " + str.startsWith("Hello"));
        System.out.println("Ends with '!': " + str.endsWith("!"));
        
        // To char array
        char[] chars = str.toCharArray();
        System.out.print("Char array: ");
        for (char c : chars) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
```

### Example 2: String Builder Operations

```java
/**
 * StringBuilder - Mutable String
 * Use for frequent modifications
 */
public class StringBuilderOperations {
    
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        
        // Append
        sb.append(" World");
        System.out.println("After append: " + sb);
        
        // Insert
        sb.insert(5, ",");
        System.out.println("After insert: " + sb);
        
        // Delete
        sb.delete(5, 6);
        System.out.println("After delete: " + sb);
        
        // Replace
        sb.replace(6, 11, "DSA");
        System.out.println("After replace: " + sb);
        
        // Reverse
        sb.reverse();
        System.out.println("Reversed: " + sb);
        
        // Length
        System.out.println("Length: " + sb.length());
        
        // Char at
        System.out.println("Char at 0: " + sb.charAt(0));
        
        // Set char at
        sb.setCharAt(0, 'A');
        System.out.println("After setCharAt: " + sb);
        
        // Delete char at
        sb.deleteCharAt(0);
        System.out.println("After deleteCharAt: " + sb);
        
        // Convert to String
        String str = sb.toString();
        System.out.println("As String: " + str);
    }
}
```

### Example 3: Reverse String

```java
/**
 * Reverse String
 * Multiple approaches
 */
public class ReverseString {
    
    // Approach 1: Using StringBuilder - O(n) time, O(n) space
    public static String reverseWithStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    // Approach 2: Two pointers with char array - O(n) time, O(n) space
    public static String reverseWithCharArray(String str) {
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        
        return new String(chars);
    }
    
    // Approach 3: Recursion - O(n) time, O(n) space
    public static String reverseRecursive(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return reverseRecursive(str.substring(1)) + str.charAt(0);
    }
    
    // Approach 4: Using loop - O(n) time, O(n) space
    public static String reverseWithLoop(String str) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        String str = "Hello, World!";
        
        System.out.println("Original: " + str);
        System.out.println("StringBuilder: " + reverseWithStringBuilder(str));
        System.out.println("CharArray: " + reverseWithCharArray(str));
        System.out.println("Recursive: " + reverseRecursive(str));
        System.out.println("Loop: " + reverseWithLoop(str));
    }
}
```

### Example 4: Valid Palindrome

```java
/**
 * Valid Palindrome
 * Check if string reads same forwards and backwards
 * Ignore non-alphanumeric characters and case
 */
public class ValidPalindrome {
    
    // Approach 1: Two pointers - O(n) time, O(1) space
    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(str.charAt(left)) != 
                Character.toLowerCase(str.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    // Approach 2: Clean string first - O(n) time, O(n) space
    public static boolean isPalindromeClean(String str) {
        // Remove non-alphanumeric and convert to lowercase
        String cleaned = str.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        
        // Check if palindrome
        int left = 0, right = cleaned.length() - 1;
        
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    // Check if substring is palindrome
    public static boolean isPalindromeRange(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public static void main(String[] args) {
        String str1 = "A man, a plan, a canal: Panama";
        String str2 = "race a car";
        String str3 = "Was it a car or a cat I saw?";
        
        System.out.println("\"" + str1 + "\" is palindrome: " + isPalindrome(str1));
        System.out.println("\"" + str2 + "\" is palindrome: " + isPalindrome(str2));
        System.out.println("\"" + str3 + "\" is palindrome: " + isPalindrome(str3));
        
        // Test substring
        String str4 = "abca";
        System.out.println("\nIs \"abca\" palindrome after removing one char: " + 
                          canBePalindromeByRemovingOne(str4));
    }
    
    // Can make palindrome by removing at most one character
    public static boolean canBePalindromeByRemovingOne(String str) {
        int left = 0, right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                // Try removing left or right character
                return isPalindromeRange(str, left + 1, right) || 
                       isPalindromeRange(str, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true;
    }
}
```

### Example 5: Longest Common Prefix

```java
import java.util.Arrays;

/**
 * Longest Common Prefix
 * Find common prefix among array of strings
 */
public class LongestCommonPrefix {
    
    // Approach 1: Character by character - O(S) time, O(1) space
    // S = sum of all characters in all strings
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Take first string as reference
        for (int i = 0; i < strs.length(); i++) {
            char c = strs.charAt(i);
            
            // Check this character in all other strings
            for (int j = 1; j < strs.length; j++) {
                // If we reached end of any string or character doesn't match
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs.substring(0, i);
                }
            }
        }
        
        return strs;
    }
    
    // Approach 2: Sort and compare first & last - O(n*m*log n) time
    // n = number of strings, m = max string length
    public static String longestCommonPrefixSort(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Sort array
        Arrays.sort(strs);
        
        // Compare first and last string
        String first = strs;
        String last = strs[strs.length - 1];
        
        int i = 0;
        while (i < first.length() && i < last.length() && 
               first.charAt(i) == last.charAt(i)) {
            i++;
        }
        
        return first.substring(0, i);
    }
    
    // Approach 3: Divide and conquer - O(S) time
    public static String longestCommonPrefixDivide(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return divideAndConquer(strs, 0, strs.length - 1);
    }
    
    private static String divideAndConquer(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }
        
        int mid = (left + right) / 2;
        String lcpLeft = divideAndConquer(strs, left, mid);
        String lcpRight = divideAndConquer(strs, mid + 1, right);
        
        return commonPrefix(lcpLeft, lcpRight);
    }
    
    private static String commonPrefix(String str1, String str2) {
        int minLen = Math.min(str1.length(), str2.length());
        
        for (int i = 0; i < minLen; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        
        return str1.substring(0, minLen);
    }
    
    public static void main(String[] args) {
        String[] strs1 = {"flower", "flow", "flight"};
        String[] strs2 = {"dog", "racecar", "car"};
        String[] strs3 = {"interspecies", "interstellar", "interstate"};
        
        System.out.println("LCP of [flower, flow, flight]: " + 
                          longestCommonPrefix(strs1));
        System.out.println("LCP of [dog, racecar, car]: " + 
                          longestCommonPrefix(strs2));
        System.out.println("LCP of [interspecies...]: " + 
                          longestCommonPrefix(strs3));
    }
}
```

### Example 6: Valid Anagram

```java
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Valid Anagram
 * Check if two strings are anagrams of each other
 */
public class ValidAnagram {
    
    // Approach 1: Sort and compare - O(n log n) time, O(n) space
    public static boolean isAnagramSort(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        
        return Arrays.equals(sChars, tChars);
    }
    
    // Approach 2: Character frequency array - O(n) time, O(1) space
    // Assuming only lowercase English letters
    public static boolean isAnagramFrequency(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] count = new int;
        
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    // Approach 3: HashMap - O(n) time, O(n) space
    public static boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) {
                return false;
            }
        }
        
        return true;
    }
    
    // Group anagrams from array of strings
    public static java.util.List<java.util.List<String>> groupAnagrams(String[] strs) {
        Map<String, java.util.List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Create key by sorting characters
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            map.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(str);
        }
        
        return new java.util.ArrayList<>(map.values());
    }
    
    public static void main(String[] args) {
        String s1 = "anagram", t1 = "nagaram";
        String s2 = "rat", t2 = "car";
        
        System.out.println("\"" + s1 + "\" and \"" + t1 + "\" are anagrams: " + 
                          isAnagramFrequency(s1, t1));
        System.out.println("\"" + s2 + "\" and \"" + t2 + "\" are anagrams: " + 
                          isAnagramFrequency(s2, t2));
        
        // Group anagrams
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("\nGrouped anagrams: " + groupAnagrams(strs));
    }
}
```

### Example 7: Longest Substring Without Repeating Characters

```java
import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring Without Repeating Characters
 * Sliding Window approach
 * 
 * Visual:
 * Input: "abcabcbb"
 * 
 * Window expansion:
 * [a] -> [ab] -> [abc] -> [abca] (repeat!)
 *                          ^
 * Shrink from left:
 * [bca] -> [bcab] (repeat!)
 *              ^
 * Shrink:
 * [cab] -> [cabb] (repeat!)
 *              ^
 * Shrink:
 * [ab] -> [abb] (repeat!)
 * 
 * Result: "abc" (length 3)
 */
public class LongestSubstringWithoutRepeat {
    
    // Approach 1: Sliding Window with HashMap - O(n) time, O(min(m,n)) space
    public static int lengthOfLongestSubstring(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);
            
            // If character already in window, move left pointer
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            
            // Update character's last seen position
            map.put(c, right);
            
            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    // Approach 2: Sliding Window with Array (for ASCII characters)
    public static int lengthOfLongestSubstringArray(String str) {
        int[] lastIndex = new int; // ASCII characters
        java.util.Arrays.fill(lastIndex, -1);
        
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);
            
            // If character already seen, move left
            if (lastIndex[c] >= left) {
                left = lastIndex[c] + 1;
            }
            
            lastIndex[c] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    // Return the actual substring (not just length)
    public static String longestSubstring(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int maxStart = 0;
        int left = 0;
        
        for (int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);
            
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            
            map.put(c, right);
            
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxStart = left;
            }
        }
        
        return str.substring(maxStart, maxStart + maxLength);
    }
    
    public static void main(String[] args) {
        String str1 = "abcabcbb";
        String str2 = "bbbbb";
        String str3 = "pwwkew";
        String str4 = "";
        
        System.out.println("Input: \"" + str1 + "\"");
        System.out.println("Length: " + lengthOfLongestSubstring(str1));
        System.out.println("Substring: \"" + longestSubstring(str1) + "\"");
        System.out.println();
        
        System.out.println("Input: \"" + str2 + "\"");
        System.out.println("Length: " + lengthOfLongestSubstring(str2));
        System.out.println("Substring: \"" + longestSubstring(str2) + "\"");
        System.out.println();
        
        System.out.println("Input: \"" + str3 + "\"");
        System.out.println("Length: " + lengthOfLongestSubstring(str3));
        System.out.println("Substring: \"" + longestSubstring(str3) + "\"");
    }
}
```

### Example 8: Longest Repeating Character Replacement

```java
import java.util.HashMap;
import java.util.Map;

/**
 * Longest Repeating Character Replacement
 * Given string s and integer k, replace at most k characters
 * to get longest substring with same characters
 * 
 * Visual:
 * Input: s = "AABABBA", k = 1
 * 
 * Window: [AABABBA]
 *         ^     ^
 *         left  right
 * 
 * Count: A=4, B=3
 * Most frequent: A=4
 * Window size: 7
 * Replacements needed: 7-4=3 > k=1 (shrink)
 * 
 * Optimal: "AABBBBA" (replace one A with B)
 * Result: 4 (BBBB)
 */
public class LongestRepeatingCharReplacement {
    
    // Sliding Window approach - O(n) time, O(1) space
    public static int characterReplacement(String s, int k) {
        int[] count = new int; // For uppercase English letters
        int maxCount = 0;
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'A']++;
            
            // Update max frequency in current window
            maxCount = Math.max(maxCount, count[c - 'A']);
            
            // If replacements needed > k, shrink window
            // Window size - maxCount = replacements needed
            while ((right - left + 1) - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            
            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Input: s=\"" + s1 + "\", k=" + k1);
        System.out.println("Output: " + characterReplacement(s1, k1));
        // Expected: 4 (replace both A's with B's or vice versa)
        
        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("\nInput: s=\"" + s2 + "\", k=" + k2);
        System.out.println("Output: " + characterReplacement(s2, k2));
        // Expected: 4
    }
}
```

### Example 9: String to Integer (atoi)

```java
/**
 * String to Integer (atoi)
 * Implement basic atoi function
 * 
 * Steps:
 * 1. Skip whitespace
 * 2. Check for sign (+ or -)
 * 3. Read digits until non-digit
 * 4. Handle overflow
 */
public class StringToInteger {
    
    public static int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        
        int i = 0;
        int n = str.length();
        
        // Step 1: Skip whitespace
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }
        
        // Step 2: Check sign
        int sign = 1;
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            sign = (str.charAt(i) == '+') ? 1 : -1;
            i++;
        }
        
        // Step 3: Read digits
        int result = 0;
        int prevResult = 0;
        
        while (i < n && Character.isDigit(str.charAt(i))) {
            int digit = str.charAt(i) - '0';
            
            // Step 4: Check overflow
            result = result * 10 + digit;
            
            // Check if overflow occurred
            if (result / 10 != prevResult) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            prevResult = result;
            i++;
        }
        
        return result * sign;
    }
    
    public static void main(String[] args) {
        String[] tests = {
            "42",
            "   -42",
            "4193 with words",
            "words and 987",
            "-91283472332",
            "+1",
            "2147483648", // Overflow
            "-2147483649" // Underflow
        };
        
        for (String test : tests) {
            System.out.println("\"" + test + "\" -> " + myAtoi(test));
        }
    }
}
```

### Example 10: Implement strStr() (Find Substring)

```java
/**
 * Implement strStr() - Find first occurrence of needle in haystack
 * Multiple approaches
 */
public class ImplementStrStr {
    
    // Approach 1: Brute Force - O(m*n) time, O(1) space
    public static int strStrBruteForce(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        
        int m = haystack.length();
        int n = needle.length();
        
        for (int i = 0; i <= m - n; i++) {
            int j = 0;
            
            while (j < n && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            
            if (j == n) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Approach 2: Using built-in - O(m*n) time
    public static int strStrBuiltIn(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
    
    // Approach 3: KMP Algorithm - O(m+n) time, O(n) space
    public static int strStrKMP(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        
        int[] lps = computeLPS(needle);
        int i = 0; // index for haystack
        int j = 0; // index for needle
        
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                
                if (j == needle.length()) {
                    return i - j; // Found match
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return -1;
    }
    
    // Compute Longest Proper Prefix which is also Suffix array
    private static int[] computeLPS(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        
        lps = 0;
        
        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
    
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        
        System.out.println("Haystack: \"" + haystack + "\"");
        System.out.println("Needle: \"" + needle + "\"");
        System.out.println("Index (Brute Force): " + strStrBruteForce(haystack, needle));
        System.out.println("Index (Built-in): " + strStrBuiltIn(haystack, needle));
        System.out.println("Index (KMP): " + strStrKMP(haystack, needle));
        
        // Test KMP with pattern
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        System.out.println("\nKMP Test:");
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Found at index: " + strStrKMP(text, pattern));
    }
}
```

### Example 11: Rabin-Karp Algorithm (Pattern Matching)

```java
/**
 * Rabin-Karp Algorithm for Pattern Matching
 * Uses hashing to find pattern in text
 * Time Complexity: O(m+n) average, O(m*n) worst case
 * Space Complexity: O(1)
 * 
 * Visual:
 * Text:    A B C D E F G H
 *          ^ ^ ^
 *          | | |
 * Pattern: A B C
 * 
 * Hash of "ABC" = hash of text[0..2]? Match!
 * Hash of "BCD" = hash of text[1..3]? No match
 * Hash of "CDE" = hash of text[2..4]? No match
 * ...
 */
public class RabinKarp {
    
    static final int PRIME = 101; // Prime number for hashing
    
    // Calculate hash of a string
    private static long calculateHash(String str) {
        long hash = 0;
        
        for (int i = 0; i < str.length(); i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        
        return hash;
    }
    
    // Recalculate hash in O(1) using rolling hash
    private static long recalculateHash(String str, int oldIndex, int newIndex, 
                                       long oldHash, int patternLength) {
        long newHash = oldHash - str.charAt(oldIndex);
        newHash = newHash / PRIME;
        newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLength - 1);
        
        return newHash;
    }
    
    public static int rabinKarp(String text, String pattern) {
        if (text == null || pattern == null || 
            pattern.length() > text.length()) {
            return -1;
        }
        
        int m = pattern.length();
        int n = text.length();
        
        // Calculate hash of pattern
        long patternHash = calculateHash(pattern);
        
        // Calculate hash of first window
        long textHash = calculateHash(text.substring(0, m));
        
        // Check first window
        if (textHash == patternHash) {
            if (text.substring(0, m).equals(pattern)) {
                return 0;
            }
        }
        
        // Check remaining windows
        for (int i = 1; i <= n - m; i++) {
            textHash = recalculateHash(text, i - 1, i + m - 1, textHash, m);
            
            if (textHash == patternHash) {
                // Hash match, verify character by character
                if (text.substring(i, i + m).equals(pattern)) {
                    return i;
                }
            }
        }
        
        return -1;
    }
    
    // Find all occurrences
    public static java.util.List<Integer> findAllOccurrences(String text, String pattern) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        if (text == null || pattern == null || 
            pattern.length() > text.length()) {
            return result;
        }
        
        int m = pattern.length();
        int n = text.length();
        
        long patternHash = calculateHash(pattern);
        long textHash = calculateHash(text.substring(0, m));
        
        for (int i = 0; i <= n - m; i++) {
            if (i > 0) {
                textHash = recalculateHash(text, i - 1, i + m - 1, textHash, m);
            }
            
            if (textHash == patternHash) {
                if (text.substring(i, i + m).equals(pattern)) {
                    result.add(i);
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        String text = "ABCCDDAEFG";
        String pattern = "CDD";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Found at index: " + rabinKarp(text, pattern));
        
        // Find all occurrences
        String text2 = "AABAACAADAABAABA";
        String pattern2 = "AABA";
        System.out.println("\nText: " + text2);
        System.out.println("Pattern: " + pattern2);
        System.out.println("All occurrences: " + findAllOccurrences(text2, pattern2));
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Valid Palindrome | Two Pointers | LeetCode 125 |
| Valid Anagram | Frequency Array | LeetCode 242 |
| Longest Common Prefix | Character Comparison | LeetCode 14 |
| Reverse String | Two Pointers | LeetCode 344 |
| First Unique Character | Frequency Map | LeetCode 387 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Longest Substring Without Repeat | Sliding Window | LeetCode 3 |
| Longest Repeating Character Replacement | Sliding Window | LeetCode 424 |
| Group Anagrams | HashMap | LeetCode 49 |
| String to Integer (atoi) | Parsing | LeetCode 8 |
| Implement strStr() | String Matching | LeetCode 28 |
| Minimum Window Substring | Sliding Window | LeetCode 76 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Regular Expression Matching | DP | LeetCode 10 |
| Wildcard Matching | DP | LeetCode 44 |
| Edit Distance | DP | LeetCode 72 |
| Longest Valid Parentheses | Stack/DP | LeetCode 32 |

---

## ✅ Key Takeaways

1. **StringBuilder** - Use for frequent string modifications
2. **Two Pointers** - Great for palindrome, reverse problems
3. **Sliding Window** - Use for substring problems
4. **Frequency Array** - Efficient for anagram problems
5. **HashMap** - Use when characters are not limited to a-z
6. **KMP/Rabin-Karp** - Advanced pattern matching algorithms

---

**Previous:** [Array Problems](./01-theory.md)  
**Next:** [Linked List](../linked-list/01-theory.md)