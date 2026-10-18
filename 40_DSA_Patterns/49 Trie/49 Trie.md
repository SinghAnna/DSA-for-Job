# 🚀 DSA Pattern 49 — Trie (Prefix Tree)

> **Goal:** Trie ko product-based company interview level par samajhna — real-world intuition, kyun Trie use hota hai, kaise identify karein, insert/search/startsWith, prefix matching, autocomplete, Word Search II, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Trie Kya Hai?

Trie — Prefix Tree — ek tree data structure hai jo strings ko **common prefix** ke basis par store karta hai.

### Example

```text
Words:
"cat"
"car"
"dog"

Trie:
        root
       /    \
      c      d
      |      |
      a      o
     / \     |
    t   r    g
```

### Core Intuition

> Same prefix wale words ek common path share karte hain.

Example:

```text
"cat" aur "car"
common prefix = "ca"
```

---

## 2. Trie Kyun Use Karte Hain?

Main reasons:

1. Fast prefix search
2. Autocomplete
3. Spell checking
4. Word search
5. IP routing
6. Dictionary implementation
7. Prefix matching
8. Search suggestions

### Core Intuition

> Trie prefix search ko O(length of prefix) time mein karta hai.

Example:

```text
Search prefix "ca"

Trie traversal:
root -> c -> a

Time = O(2)
```

---

## 3. Real-World Production Examples

| Real System | Trie Use |
|---|---|
| Search Engine | Autocomplete |
| Google Search | Search suggestions |
| E-commerce | Product search |
| Spell Checker | Word validation |
| IDE | Code completion |
| Contact App | Name search |
| File System | Path search |
| IP Router | Longest prefix match |
| Chat App | Emoji search |
| Dictionary App | Word lookup |
| Recommendation System | Query suggestion |
| Ad System | Keyword matching |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Search bar mein user type kare to relevant suggestions dikhane hain. Kaise karoge?”

Answer:

```text
Trie use karenge.

1. Saare words Trie mein insert karo
2. User prefix ke hisaab se node tak jao
3. Us node se saare words collect karo
```

Ya:

> “E-commerce app mein product names search karne hain. Kaise approach karoge?”

Answer:

```text
Product names Trie mein store karo.

Prefix search se fast suggestions milenge.
```

---

## 4. Kaise Pata Chalega Ki Trie Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Trie |
|---|---|
| Prefix search | Trie |
| Autocomplete | Trie |
| Search suggestions | Trie |
| Word dictionary | Trie |
| Spell check | Trie |
| Word Search II | Trie |
| Longest common prefix | Trie |
| IP routing | Trie |

### Common Keywords

- Prefix
- Autocomplete
- Search suggestions
- Dictionary
- Word search
- Spell check
- Longest common prefix
- Starts with

### Mental Checklist

1. Kya strings / words hain?  
2. Kya prefix search chahiye?  
3. Kya autocomplete chahiye?  
4. Kya dictionary banani hai?  
5. Kya multiple words search karne hain?  

Agar 2–3 answers **yes** hain, to Trie try karo.

---

# 5. Trie Ke Core Operations

| Operation | Time |
|---|---|
| Insert word | O(L) |
| Search word | O(L) |
| Search prefix | O(L) |
| Delete word | O(L) |

Where:

```text
L = length of word
```

---

# 6. Trie Node Structure

```java
class TrieNode {

    TrieNode[] children = new TrieNode;

    boolean isEnd = false;
}
```

### Explanation

```text
children -> 'a'
children -> 'b'
...
children -> 'z'

isEnd = true means word ends here
```

---

# 7. Trie Class Template

```java
class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode node = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {

        TrieNode node = searchPrefix(word);

        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {

        TrieNode node = searchPrefix(prefix);

        return node != null;
    }

    private TrieNode searchPrefix(String prefix) {

        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {

            int index = ch - 'a';

            if (node.children[index] == null) {
                return null;
            }

            node = node.children[index];
        }

        return node;
    }
}
```

---

# 8. Example 1: Implement Trie

## Problem

Trie implement karo.

Operations:

```text
1. insert(word)
2. search(word)
3. startsWith(prefix)
```

```text
insert("apple")
search("apple") -> true
search("app") -> false
startsWith("app") -> true
insert("app")
search("app") -> true
```

## Solution

```java
class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode node = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {

        TrieNode node = searchPrefix(word);

        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {

        TrieNode node = searchPrefix(prefix);

        return node != null;
    }

    private TrieNode searchPrefix(String prefix) {

        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {

            int index = ch - 'a';

            if (node.children[index] == null) {
                return null;
            }

            node = node.children[index];
        }

        return node;
    }
}

class TrieNode {

    TrieNode[] children = new TrieNode;

    boolean isEnd = false;
}
```

## Dry Run

```text
insert("apple")

root -> a -> p -> p -> l -> e
isEnd = true

search("apple")
root -> a -> p -> p -> l -> e
isEnd = true

Answer = true

search("app")
root -> a -> p -> p
isEnd = false

Answer = false

startsWith("app")
root -> a -> p -> p
node exists

Answer = true
```

## Complexity

- Insert: \(O(L)\)
- Search: \(O(L)\)
- startsWith: \(O(L)\)
- Space: \(O(N \times L)\)

---

# 9. Example 2: Autocomplete System

## Problem

Search suggestions nikalne hain.

```text
words = ["mobile", "mouse", "moneypot", "monitor", "mousepad"]
prefix = "m"

Output:
["mobile", "moneypot", "monitor", "mouse", "mousepad"]
```

## Why Trie?

Prefix search fast chahiye.

## Solution

```java
class TrieNode {

    TrieNode[] children = new TrieNode;

    boolean isEnd = false;
}

public List<String> autocomplete(String[] words, String prefix) {

    TrieNode root = new TrieNode();

    for (String word : words) {
        insert(root, word);
    }

    TrieNode node = searchPrefix(root, prefix);

    List<String> result = new ArrayList<>();

    if (node == null) {
        return result;
    }

    dfs(node, new StringBuilder(prefix), result);

    return result;
}

private void insert(TrieNode root, String word) {

    TrieNode node = root;

    for (char ch : word.toCharArray()) {

        int index = ch - 'a';

        if (node.children[index] == null) {
            node.children[index] = new TrieNode();
        }

        node = node.children[index];
    }

    node.isEnd = true;
}

private TrieNode searchPrefix(TrieNode root, String prefix) {

    TrieNode node = root;

    for (char ch : prefix.toCharArray()) {

        int index = ch - 'a';

        if (node.children[index] == null) {
            return null;
        }

        node = node.children[index];
    }

    return node;
}

private void dfs(
    TrieNode node,
    StringBuilder current,
    List<String> result
) {

    if (node.isEnd) {
        result.add(current.toString());
    }

    for (int i = 0; i < 26; i++) {

        if (node.children[i] != null) {

            current.append((char) ('a' + i));

            dfs(node.children[i], current, result);

            current.deleteCharAt(current.length() - 1);
        }
    }
}
```

## Dry Run

```text
words = ["mobile", "mouse", "moneypot", "monitor", "mousepad"]
prefix = "m"

Trie traversal:
root -> m

DFS:
mobile
moneypot
monitor
mouse
mousepad

Answer = sorted suggestions
```

## Complexity

- Insert: \(O(N \times L)\)
- Search: \(O(P)\)
- DFS: \(O(N \times L)\)
- Space: \(O(N \times L)\)

---

# 10. Example 3: Word Search II

## Problem

2D board aur words diye hain.

Board mein se words dhundo jo sequentially adjacent cells se bante hain.

```text
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

words = ["oath", "pea", "eat", "rain"]

Output = ["oath", "eat"]
```

## Why Trie?

Multiple words search karne hain.

## Solution

```java
class TrieNode {

    TrieNode[] children = new TrieNode;

    String word = null;
}

public List<String> findWords(char[][] board, String[] words) {

    TrieNode root = new TrieNode();

    for (String word : words) {
        insert(root, word);
    }

    List<String> result = new ArrayList<>();

    int rows = board.length;
    int cols = board.length;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            dfs(board, i, j, root, result);
        }
    }

    return result;
}

private void insert(TrieNode root, String word) {

    TrieNode node = root;

    for (char ch : word.toCharArray()) {

        int index = ch - 'a';

        if (node.children[index] == null) {
            node.children[index] = new TrieNode();
        }

        node = node.children[index];
    }

    node.word = word;
}

private void dfs(
    char[][] board,
    int i,
    int j,
    TrieNode node,
    List<String> result
) {

    char ch = board[i][j];

    if (ch == '#' || node.children[ch - 'a'] == null) {
        return;
    }

    node = node.children[ch - 'a'];

    if (node.word != null) {
        result.add(node.word);
        node.word = null;
    }

    board[i][j] = '#';

    int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    for (int[] dir : directions) {

        int newRow = i + dir;
        int newCol = j + dir;

        if (newRow >= 0 &&
            newRow < board.length &&
            newCol >= 0 &&
            newCol < board.length) {

            dfs(board, newRow, newCol, node, result);
        }
    }

    board[i][j] = ch;
}
```

## Dry Run

```text
board:
o a a n
e t a e
i h k r
i f l v

words = ["oath", "pea", "eat", "rain"]

Trie insert all words.

DFS from every cell.

Found:
"oath"
"eat"

Answer = ["oath", "eat"]
```

## Complexity

- Time: \(O(M \times N \times 4 \times 3^{L-1})\)
- Space: \(O(N \times L)\)

---

# 11. Example 4: Longest Common Prefix

## Problem

Array of strings diya hai.

Longest common prefix nikalo.

```text
words = ["flower", "flow", "flight"]
Output = "fl"
```

## Why Trie?

Common prefix fast nikalna hai.

## Solution

```java
class TrieNode {

    TrieNode[] children = new TrieNode;

    int count = 0;
}

public String longestCommonPrefix(String[] words) {

    if (words == null || words.length == 0) {
        return "";
    }

    TrieNode root = new TrieNode();

    for (String word : words) {
        insert(root, word);
    }

    StringBuilder sb = new StringBuilder();

    TrieNode node = root;

    while (true) {

        int childCount = 0;

        int nextIndex = -1;

        for (int i = 0; i < 26; i++) {

            if (node.children[i] != null) {

                childCount++;

                nextIndex = i;
            }
        }

        if (childCount != 1 || node.children[nextIndex].count != words.length) {
            break;
        }

        sb.append((char) ('a' + nextIndex));

        node = node.children[nextIndex];
    }

    return sb.toString();
}

private void insert(TrieNode root, String word) {

    TrieNode node = root;

    for (char ch : word.toCharArray()) {

        int index = ch - 'a';

        if (node.children[index] == null) {
            node.children[index] = new TrieNode();
        }

        node = node.children[index];

        node.count++;
    }
}
```

## Dry Run

```text
words = ["flower", "flow", "flight"]

Common prefix = "fl"

Answer = "fl"
```

## Complexity

- Insert: \(O(N \times L)\)
- Search: \(O(L)\)
- Space: \(O(N \times L)\)

---

# 12. Trie Patterns

| Pattern | Use |
|---|---|
| Insert | Dictionary build |
| Search | Exact word search |
| startsWith | Prefix search |
| DFS | Autocomplete |
| Trie + DFS | Word Search II |
| Trie + Count | Longest Common Prefix |
| Trie + Bit | XOR problems |

---

# 13. Trie vs HashMap

| Feature | Trie | HashMap |
|---|---|---|
| Prefix search | Fast | Slow |
| Autocomplete | Best | Poor |
| Memory | More | Less |
| Search time | O(L) | O(1) average |
| Best for | Prefix problems | Exact lookup |

### Rule

```text
Prefix search -> Trie
Exact lookup -> HashMap
```

---

# 14. Common Mistakes

## Mistake 1: isEnd Bhoolna

```java
node.isEnd = true;
```

---

## Mistake 2: children Array Size Galat Karna

```java
TrieNode[] children = new TrieNode;
```

---

## Mistake 3: Prefix Search Mein null Check Bhoolna

```java
if (node.children[index] == null) {
    return null;
}
```

---

## Mistake 4: Word Search II Mein Visited Mark Bhoolna

```java
board[i][j] = '#';
```

---

## Mistake 5: DFS Mein Backtrack Bhoolna

```java
board[i][j] = ch;
```

---

# 15. Trie Decision Flow

```text
Problem padho
      |
      v
Kya prefix search chahiye?
      |
      +-- Yes --> Trie
      |
      +-- Kya autocomplete chahiye?
              |
              +-- Yes --> Trie + DFS
              |
              +-- Kya multiple words search karne hain?
                      |
                      +-- Yes --> Trie + DFS
                      |
                      +-- Kya exact lookup hai?
                              |
                              +-- Yes --> HashMap
```

---

# 16. Important Problems

## Medium

- Implement Trie
- Design Add and Search Words Data Structure
- Word Search II
- Longest Common Prefix
- Replace Words
- Map Sum Pairs
- Maximum XOR of Two Numbers in an Array

## Hard

- Word Search II
- Prefix and Suffix Search
- Maximum Genetic Difference Query
- Concatenated Words

---

# 17. One-Line Recognition

> **Agar problem prefix search, autocomplete, dictionary, ya multiple word search maang rahi hai — Trie try karo.**

Trie ka main power ye hai ki prefix based string problems ko fast aur structured way mein solve karta hai — jisse search, autocomplete, aur word matching efficiently ho jata hai.