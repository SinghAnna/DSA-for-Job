# 🌳 Trie (Prefix Tree) - Complete Guide

## 📚 Theory

### What is Trie?

A **Trie** (pronounced "try") is a tree-based data structure used for efficient retrieval of keys in a dataset of strings. It's particularly useful for problems involving prefixes, autocomplete, and dictionary operations.

### When to Use Trie?

Use trie when:
1. Need to search for **words with common prefixes**
2. Need to implement **autocomplete** or **spell checker**
3. Need to find **all words with given prefix**
4. Need to check if a word **exists in dictionary**
5. Need to find **longest common prefix**

### Trie Properties


### 1. Tree Structure

* Root node is empty

* Each node represents a character

* Path from root to node forms a word

* Leaf nodes mark end of words

### 2. Node Structure

* Array/Map of children (26 for lowercase English)

* Boolean flag to mark end of word

* Optional: count of words passing through

### 3. Time Complexity

* Insert: O(m) where m is word length

* Search: O(m)

* Delete: O(m)

* Prefix Search: O(m)

### 4. Space Complexity: O(n × m)

* n = number of words

* m = average word length


### Trie Visualization

```
Insert words: ["cat", "car", "card", "bat", "bad"]

Trie Structure:

root
/
c b
/ \ /
a a a a
/ \ | |
t r d d
/
d

Words formed:

root → c → a → t (cat)

root → c → a → r (car)

root → c → a → r → d (card)

root → b → a → t (bat)

root → b → a → d (bad)

Search "car":
root → c → a → r ✓ (exists)

Search "ca":
root → c → a ✓ (prefix exists, but not complete word)

Search "dog":
root → d ✗ (doesn't exist)

```

### Types of Trie Operations

1. **Insert** - Add word to trie
2. **Search** - Check if word exists
3. **StartsWith** - Check if prefix exists
4. **Delete** - Remove word from trie
5. **Count Words with Prefix** - Count words having given prefix
6. **Longest Common Prefix** - Find LCP of all words
7. **Autocomplete** - Find all words with given prefix

---

## 💻 Java Code Examples

### Problem 1: Implement Trie (Prefix Tree)

```java
/**
 * Problem: Implement Trie (Prefix Tree)
 * LeetCode: 208
 * 
 * Visual:
 * Operations:
 * insert("apple")
 * insert("app")
 * insert("application")
 * 
 * Trie:
 *         root
 *          |
 *          a
 *          |
 *          p
 *          |
 *          p  (end of "app")
 *          |
 *          l
 *          |
 *          e  (end of "apple")
 *          |
 *          i
 *          |
 *          c
 *          |
 *          a
 *          |
 *          t  (end of "application")
 *          |
 *          i
 *          |
 *          o
 *          |
 *          n
 * 
 * search("apple") → true
 * search("app") → true
 * search("appl") → false (prefix exists, but not word)
 * startsWith("appl") → true
 * 
 * Time Complexity:
 * - Insert: O(m)
 * - Search: O(m)
 * - StartsWith: O(m)
 * 
 * Space Complexity: O(n × m)
 */
public class Trie {
    
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        
        TrieNode() {
            children = new TrieNode; // 26 lowercase English letters
            isEndOfWord = false;
        }
    }
    
    private TrieNode root;
    
    // Constructor
    public Trie() {
        root = new TrieNode();
    }
    
    // Insert word into trie
    public void insert(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            current = current.children[index];
        }
        
        current.isEndOfWord = true;
    }
    
    // Search for word in trie
    public boolean search(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                return false;
            }
            
            current = current.children[index];
        }
        
        return current.isEndOfWord;
    }
    
    // Check if any word starts with given prefix
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                return false;
            }
            
            current = current.children[index];
        }
        
        return true;
    }
    
    // Get node at end of prefix (helper for other operations)
    private TrieNode getNode(String prefix) {
        TrieNode current = root;
        
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                return null;
            }
            
            current = current.children[index];
        }
        
        return current;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        
        System.out.println("search(\"apple\"): " + trie.search("apple")); // true
        System.out.println("search(\"app\"): " + trie.search("app")); // true
        System.out.println("search(\"appl\"): " + trie.search("appl")); // false
        System.out.println("startsWith(\"appl\"): " + trie.startsWith("appl")); // true
        System.out.println("search(\"banana\"): " + trie.search("banana")); // false
        
        // More operations
        trie.insert("banana");
        System.out.println("\nAfter inserting \"banana\":");
        System.out.println("search(\"banana\"): " + trie.search("banana")); // true
    }
}
```

---

### Problem 2: Implement Trie with Delete

```java
/**
 * Problem: Implement Trie with Delete Operation
 * 
 * Visual:
 * Insert: ["cat", "car", "card"]
 * 
 * Trie:
 *     root
 *      |
 *      c
 *      |
 *      a
 *     / \
 *    t   r (end)
 *        |
 *        d (end)
 * 
 * Delete "car":
 * - Mark 'r' as not end of word
 * - Don't delete nodes (needed for "card")
 * 
 * Delete "cat":
 * - Mark 't' as not end of word
 * - Can delete 't', 'a', 'c' (no other words use them)
 * 
 * Time Complexity:
 * - Delete: O(m)
 * 
 * Space Complexity: O(n × m)
 */
public class TrieWithDelete {
    
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        
        TrieNode() {
            children = new TrieNode;
            isEndOfWord = false;
        }
    }
    
    private TrieNode root;
    
    public TrieWithDelete() {
        root = new TrieNode();
    }
    
    // Insert word
    public void insert(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            current = current.children[index];
        }
        
        current.isEndOfWord = true;
    }
    
    // Search word
    public boolean search(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                return false;
            }
            
            current = current.children[index];
        }
        
        return current.isEndOfWord;
    }
    
    // Delete word
    public boolean delete(String word) {
        return delete(root, word, 0);
    }
    
    private boolean delete(TrieNode current, String word, int index) {
        // Base case: reached end of word
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false; // Word doesn't exist
            }
            current.isEndOfWord = false;
            // Check if this node has no children
            return isEmpty(current);
        }
        
        // Recursive case
        int charIndex = word.charAt(index) - 'a';
        TrieNode child = current.children[charIndex];
        
        if (child == null) {
            return false; // Word doesn't exist
        }
        
        // Recursively delete
        boolean shouldDeleteChild = delete(child, word, index + 1);
        
        // If child should be deleted
        if (shouldDeleteChild) {
            current.children[charIndex] = null;
            
            // Check if current node can be deleted
            return !current.isEndOfWord && isEmpty(current);
        }
        
        return false;
    }
    
    // Check if node has no children
    private boolean isEmpty(TrieNode node) {
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                return false;
            }
        }
        return true;
    }
    
    // Check if prefix exists
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                return false;
            }
            
            current = current.children[index];
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        TrieWithDelete trie = new TrieWithDelete();
        
        trie.insert("cat");
        trie.insert("car");
        trie.insert("card");
        
        System.out.println("Before delete:");
        System.out.println("search(\"cat\"): " + trie.search("cat")); // true
        System.out.println("search(\"car\"): " + trie.search("car")); // true
        System.out.println("search(\"card\"): " + trie.search("card")); // true
        
        // Delete "car"
        trie.delete("car");
        System.out.println("\nAfter deleting \"car\":");
        System.out.println("search(\"cat\"): " + trie.search("cat")); // true
        System.out.println("search(\"car\"): " + trie.search("car")); // false
        System.out.println("search(\"card\"): " + trie.search("card")); // true
        
        // Delete "cat"
        trie.delete("cat");
        System.out.println("\nAfter deleting \"cat\":");
        System.out.println("search(\"cat\"): " + trie.search("cat")); // false
        System.out.println("search(\"card\"): " + trie.search("card")); // true
    }
}
```

---

### Problem 3: Count Words with Prefix

```java
/**
 * Problem: Count Words with Given Prefix
 * 
 * Visual:
 * Insert: ["apple", "app", "application", "apply", "banana"]
 * 
 * Trie with count:
 *         root (count=5)
 *          |
 *          a (count=4)
 *          |
 *          p (count=4)
 *         / \
 *    (end)  p (count=3)
 *    app    |
 *           l (count=2)
 *          / \
 *     (end)  y (end)
 *    apple  apply
 * 
 * Count words with prefix "app": 4
 * Count words with prefix "appl": 2
 * Count words with prefix "ban": 1
 * Count words with prefix "xyz": 0
 * 
 * Time Complexity:
 * - Insert: O(m)
 * - Count: O(m)
 * 
 * Space Complexity: O(n × m)
 */
public class TrieWithCount {
    
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        int count; // Number of words passing through this node
        
        TrieNode() {
            children = new TrieNode;
            isEndOfWord = false;
            count = 0;
        }
    }
    
    private TrieNode root;
    
    public TrieWithCount() {
        root = new TrieNode();
    }
    
    // Insert word
    public void insert(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            current = current.children[index];
            current.count++; // Increment count
        }
        
        current.isEndOfWord = true;
    }
    
    // Count words with given prefix
    public int countWordsWithPrefix(String prefix) {
        TrieNode current = root;
        
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                return 0; // Prefix doesn't exist
            }
            
            current = current.children[index];
        }
        
        return current.count;
    }
    
    // Count words that end at or below given node
    public int countWordsStartingWith(String prefix) {
        TrieNode node = getNode(prefix);
        
        if (node == null) {
            return 0;
        }
        
        return countWords(node);
    }
    
    private int countWords(TrieNode node) {
        if (node == null) {
            return 0;
        }
        
        int count = node.isEndOfWord ? 1 : 0;
        
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                count += countWords(node.children[i]);
            }
        }
        
        return count;
    }
    
    private TrieNode getNode(String prefix) {
        TrieNode current = root;
        
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                return null;
            }
            
            current = current.children[index];
        }
        
        return current;
    }
    
    public static void main(String[] args) {
        TrieWithCount trie = new TrieWithCount();
        
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("apply");
        trie.insert("banana");
        
        System.out.println("Count words with prefix \"app\": " + 
                          trie.countWordsWithPrefix("app")); // 4
        System.out.println("Count words with prefix \"appl\": " + 
                          trie.countWordsWithPrefix("appl")); // 2
        System.out.println("Count words with prefix \"ban\": " + 
                          trie.countWordsWithPrefix("ban")); // 1
        System.out.println("Count words with prefix \"xyz\": " + 
                          trie.countWordsWithPrefix("xyz")); // 0
    }
}
```

---

### Problem 4: Longest Common Prefix using Trie

```java
/**
 * Problem: Longest Common Prefix using Trie
 * LeetCode: 14
 * 
 * Visual:
 * Words: ["flower", "flow", "flight"]
 * 
 * Trie:
 *         root
 *          |
 *          f
 *          |
 *          l
 *         / \
 *        o   i
 *       /     \
 *      w       g
 *     /         \
 *    (end)      h
 *    flow       t (end)
 *               |
 *               e
 *               |
 *               r (end)
 * 
 * Longest Common Prefix:
 * Traverse from root while node has exactly 1 child
 * root → f → l
 * At 'l', node has 2 children (o, i)
 * Stop here
 * 
 * LCP: "fl"
 * 
 * Time Complexity: O(n × m)
 * Space Complexity: O(n × m)
 */
public class LongestCommonPrefixTrie {
    
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        int count;
        
        TrieNode() {
            children = new TrieNode;
            isEndOfWord = false;
            count = 0;
        }
    }
    
    private TrieNode root;
    
    public LongestCommonPrefixTrie() {
        root = new TrieNode();
    }
    
    // Insert word
    public void insert(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            current = current.children[index];
            current.count++;
        }
        
        current.isEndOfWord = true;
    }
    
    // Find longest common prefix
    public String longestCommonPrefix(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        // Insert all words
        for (String word : words) {
            insert(word);
        }
        
        // Traverse trie to find LCP
        StringBuilder lcp = new StringBuilder();
        TrieNode current = root;
        
        while (true) {
            // Count non-null children
            int childCount = 0;
            int childIndex = -1;
            
            for (int i = 0; i < 26; i++) {
                if (current.children[i] != null) {
                    childCount++;
                    childIndex = i;
                }
            }
            
            // If not exactly 1 child or reached end of a word, stop
            if (childCount != 1 || current.isEndOfWord) {
                break;
            }
            
            // Add character to LCP
            lcp.append((char)('a' + childIndex));
            current = current.children[childIndex];
        }
        
        return lcp.toString();
    }
    
    public static void main(String[] args) {
        String[] words1 = {"flower", "flow", "flight"};
        LongestCommonPrefixTrie trie1 = new LongestCommonPrefixTrie();
        System.out.println("Words: " + java.util.Arrays.toString(words1));
        System.out.println("LCP: \"" + trie1.longestCommonPrefix(words1) + "\""); // "fl"
        
        String[] words2 = {"dog", "racecar", "car"};
        LongestCommonPrefixTrie trie2 = new LongestCommonPrefixTrie();
        System.out.println("\nWords: " + java.util.Arrays.toString(words2));
        System.out.println("LCP: \"" + trie2.longestCommonPrefix(words2) + "\""); // ""
        
        String[] words3 = {"apple", "app", "application"};
        LongestCommonPrefixTrie trie3 = new LongestCommonPrefixTrie();
        System.out.println("\nWords: " + java.util.Arrays.toString(words3));
        System.out.println("LCP: \"" + trie3.longestCommonPrefix(words3) + "\""); // "app"
    }
}
```

---

### Problem 5: Autocomplete System

```java
/**
 * Problem: Autocomplete System
 * LeetCode: 1268
 * 
 * Visual:
 * Products: ["mobile", "mouse", "moneypot", "monitor", "mousepad"]
 * Search: "mou"
 * 
 * Trie:
 *         root
 *          |
 *          m
 *          |
 *          o
 *         /|\
 *        b u n
 *       /  |  \
 *     ... se nitor
 *         /
 *       se (end)
 *       /
 *     pad (end)
 * 
 * Suggestions for "mou":
 * - mouse
 * - mousepad
 * 
 * Time Complexity:
 * - Build: O(n × m)
 * - Search: O(m + k) where k = number of suggestions
 * 
 * Space Complexity: O(n × m)
 */
public class AutocompleteSystem {
    
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        java.util.List<String> suggestions;
        
        TrieNode() {
            children = new TrieNode;
            isEndOfWord = false;
            suggestions = new java.util.ArrayList<>();
        }
    }
    
    private TrieNode root;
    
    public AutocompleteSystem() {
        root = new TrieNode();
    }
    
    // Insert word with suggestions
    public void insert(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            current = current.children[index];
            
            // Add to suggestions (keep top 3 lexicographically)
            if (current.suggestions.size() < 3) {
                current.suggestions.add(word);
                java.util.Collections.sort(current.suggestions);
            } else if (current.suggestions.size() == 3) {
                current.suggestions.add(word);
                java.util.Collections.sort(current.suggestions);
                current.suggestions.remove(3); // Remove 4th element
            }
        }
        
        current.isEndOfWord = true;
    }
    
    // Get suggestions for prefix
    public java.util.List<java.util.List<String>> search(String searchWord) {
        java.util.List<java.util.List<String>> result = new java.util.ArrayList<>();
        TrieNode current = root;
        
        for (char c : searchWord.toCharArray()) {
            int index = c - 'a';
            
            if (current.children[index] == null) {
                // No more suggestions
                while (result.size() < searchWord.length()) {
                    result.add(new java.util.ArrayList<>());
                }
                return result;
            }
            
            current = current.children[index];
            result.add(current.suggestions);
        }
        
        return result;
    }
    
    // Build trie from products
    public void buildFromProducts(String[] products) {
        java.util.Arrays.sort(products); // Sort for lexicographical order
        
        for (String product : products) {
            insert(product);
        }
    }
    
    public static void main(String[] args) {
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mou";
        
        System.out.println("Products: " + java.util.Arrays.toString(products));
        System.out.println("Search: \"" + searchWord + "\"");
        
        AutocompleteSystem autocomplete = new AutocompleteSystem();
        autocomplete.buildFromProducts(products);
        
        java.util.List<java.util.List<String>> result = autocomplete.search(searchWord);
        
        System.out.println("\nSuggestions:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + " char: " + result.get(i));
        }
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Implement Trie | Basic Trie | LeetCode 208 |
| Longest Common Prefix | Trie | LeetCode 14 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Implement Trie II | Trie with Count | LeetCode 1698 |
| Autocomplete System | Trie + DFS | LeetCode 1268 |
| Word Search II | Trie + Backtracking | LeetCode 212 |
| Replace Words | Trie | LeetCode 648 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Word Search II | Trie + Backtracking | LeetCode 212 |
| Maximum XOR of Two Numbers | Trie | LeetCode 421 |
| Concatenated Words | Trie + DP | LeetCode 472 |

---

## ✅ Key Takeaways

1. **Trie** - Tree for efficient string operations
2. **Node Structure** - Array of 26 children + isEndOfWord flag
3. **Insert** - O(m) time, create nodes as needed
4. **Search** - O(m) time, traverse tree
5. **StartsWith** - O(m) time, check prefix exists
6. **Delete** - O(m) time, remove nodes if not needed
7. **Count** - Track count at each node
8. **Applications** - Autocomplete, spell checker, LCP, dictionary

---

**Previous:** [Segment Tree](../segment-tree/01-theory.md)  
**Next:** [Union Find](../union-find/01-theory.md)