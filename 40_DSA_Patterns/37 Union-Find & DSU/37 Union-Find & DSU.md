# 🚀 DSA Pattern 37 — Union-Find / DSU

> **Goal:** Union-Find / Disjoint Set Union (DSU) ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, find, union, path compression, union by rank/size, connected components, cycle detection, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Union-Find / DSU Kya Hai?

Union-Find ek data structure hai jo elements ko **disjoint sets** mein manage karta hai.

Do main operations hote hain:

```text
1. find(x)
   -> x kis set/group mein hai?

2. union(x, y)
   -> x aur y ke groups ko merge karo
```

Example:

```text
Initially:
1 -> {1}
2 -> {2}
3 -> {3}
4 -> {4}

union(1, 2):
{1, 2}, {3}, {4}

union(3, 4):
{1, 2}, {3, 4}

union(2, 3):
{1, 2, 3, 4}
```

---

## 2. Union-Find Kyun Use Karte Hain?

Main reasons:

1. **Dynamic connectivity**
2. **Connected components count**
3. **Cycle detection in undirected graph**
4. **Group merging**
5. **Network connectivity**
6. **Accounts merging**
7. **Redundant connection detection**

### Core Intuition

> Har element ek group ka member hai. Union se groups merge hote hain. Find se pata chalta hai ki do elements same group mein hain ya nahi.

Example:

```text
Users:
A, B, C, D

Friendships:
A-B
C-D

Groups:
{A, B}
{C, D}

Ab agar A-C friendship add ho:
{A, B, C, D}
```

---

## 3. Real-World Production Examples

| Real System | Union-Find Use |
|---|---|
| Social Network | Friend circles / connected users |
| LinkedIn | Network components |
| Payment System | Fraud account clustering |
| Bank System | Connected suspicious accounts |
| E-commerce | User-account merging |
| Cloud System | Network connectivity |
| Kubernetes | Cluster resource grouping |
| Logistics | Delivery region grouping |
| Image Processing | Connected pixels |
| Gaming | Player guild/group merging |
| Telecom Network | Connected towers |
| Database | Entity resolution |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Social media app mein kitne separate friend circles hain, ye dynamically kaise maintain karoge?”

Answer:

```text
Har user initially apna alag group hoga.

Jab friendship add ho:
union(user1, user2)

Friend circles count:
Number of unique roots.
```

Ya:

> “Fraud detection mein same device/email/payment method use karne wale accounts ko ek group mein merge karna hai. Kaise karoge?”

Answer:

```text
Har account initially alag DSU node.

Common email/device/payment method par:
union(account1, account2)

End mein unique roots = fraud groups.
```

---

## 4. Kaise Pata Chalega Ki Union-Find Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Union-Find |
|---|---|
| Connected components | Union-Find |
| Dynamic connectivity | Union-Find |
| Merge groups | Union-Find |
| Same group check | Union-Find |
| Cycle in undirected graph | Union-Find |
| Accounts merge | Union-Find |
| Number of provinces | Union-Find |
| Redundant connection | Union-Find |

### Common Keywords

- Connected
- Component
- Group
- Merge
- Union
- Friend circle
- Province
- Redundant
- Accounts
- Network

### Mental Checklist

1. Kya elements ko groups mein divide karna hai?  
2. Kya groups dynamically merge ho rahe hain?  
3. Kya do elements same group mein hain, ye check karna hai?  
4. Kya connected components chahiye?  
5. Kya undirected graph cycle detect karna hai?  

Agar 2–3 answers **yes** hain, to Union-Find try karo.

---

# 5. DSU Ka Basic Idea

Har element ka ek parent hota hai.

Initially:

```text
parent[i] = i
```

Matlab har element apna khud ka root hai.

Example:

```text
parent =[0][1][2][3][4]
```

### Find

```text
find(x) -> x ka root nikalo
```

### Union

```text
union(x, y) -> dono roots ko connect karo
```

---

# 6. Basic DSU — Without Optimization

```java
class DSU {
    int[] parent;

    public DSU(int n) {
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }

        return x;
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
}
```

### Problem

Worst case mein tree linear ban sakta hai:

```text
0 -> 1 -> 2 -> 3 -> 4
```

Find:

```text
O(n)
```

Isliye optimization zaroori hai.

---

# 7. Path Compression

## Idea

`find()` ke dauran har node ko directly root se connect kar do.

```text
Before:
0 -> 1 -> 2 -> 3 -> 4

After find(4):
0 -> 1
1 -> 2
2 -> 3
3 -> 4

Flattened:
0 -> 4
1 -> 4
2 -> 4
3 -> 4
```

## Template

```java
public int find(int x) {
    if (parent[x] != x) {
        parent[x] = find(parent[x]);
    }

    return parent[x];
}
```

### Benefit

Future find operations almost \(O(1)\) ho jate hain.

---

# 8. Union by Rank

## Idea

Chhota tree bade tree ke neeche attach karo.

```text
rank = tree height approximation
```

## Template

```java
public boolean union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX == rootY) {
        return false;
    }

    if (rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
    } else if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
    } else {
        parent[rootY] = rootX;
        rank[rootX]++;
    }

    return true;
}
```

---

# 9. Union by Size

## Idea

Chhote component ko bade component mein merge karo.

```text
size = number of elements in component
```

## Template

```java
public boolean union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX == rootY) {
        return false;
    }

    if (size[rootX] < size[rootY]) {
        parent[rootX] = rootY;
        size[rootY] += size[rootX];
    } else {
        parent[rootY] = rootX;
        size[rootX] += size[rootY];
    }

    return true;
}
```

---

# 10. Optimized DSU Template

```java
class DSU {
    private int[] parent;
    private int[] size;

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (size[rootX] < size[rootY]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }

        parent[rootY] = rootX;
        size[rootX] += size[rootY];

        return true;
    }

    public int getComponentSize(int x) {
        return size[find(x)];
    }
}
```

## Complexity

With path compression + union by size/rank:

```text
Find: Almost O(1)
Union: Almost O(1)

Exact:
O(α(n))
```

`α(n)` inverse Ackermann function hai, jo practically constant hoti hai.

---

# 11. Example 1: Number of Provinces

## Problem

`n` cities hain.

`isConnected[i][j] = 1` matlab city `i` aur `j` directly connected hain.

Number of provinces / connected components return karo.

```text
isConnected = [
 ,[0][1]
 ,[1][0]
[0][1]
]

Output: 2
```

## Why DSU?

Cities ko groups mein merge karo.

End mein unique roots count karo.

## Solution

```java
public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;

    DSU dsu = new DSU(n);

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (isConnected[i][j] == 1) {
                dsu.union(i, j);
            }
        }
    }

    Set<Integer> components = new HashSet<>();

    for (int i = 0; i < n; i++) {
        components.add(dsu.find(i));
    }

    return components.size();
}
```

## Dry Run

```text
isConnected:
0-1 connected
2 separate

union(0, 1):
Root of 0 and 1 same

Components:
{0, 1}
{2}

Answer = 2
```

## Complexity

- Time: \(O(n^2 \alpha(n))\)
- Space: \(O(n)\)

---

# 12. Example 2: Redundant Connection

## Problem

Undirected graph mein ek extra edge diya gaya hai jo cycle banati hai.

Wo edge return karo.

```text
edges = [,, ][1][2][3]

Output:[2][3]
```

## Why DSU?

Edge add karte waqt agar dono endpoints already same component mein hain, to wo edge redundant/cycle creating hai.

## Solution

```java
public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;

    DSU dsu = new DSU(n + 1);

    for (int[] edge : edges) {
        int u = edge;
        int v = edge;

        if (!dsu.union(u, v)) {
            return edge;
        }
    }

    return new int;
}
```

## Dry Run

```text
edges = [,, ][1][2][3]

Edge:[2][1]
Different roots
Union

Edge:[1][3]
Different roots
Union

Edge:[2][3]
find(2) == find(3)
Cycle

Return[2][3]
```

## Complexity

- Time: \(O(E \alpha(V))\)
- Space: \(O(V)\)

---

# 13. Example 3: Accounts Merge

## Problem

Accounts diye hain.

Har account mein name aur emails hain.

Agar do accounts mein koi common email ho, to unhe merge karo.

```text
accounts = [
  ["John", "johnsmith@mail.com", "john00@mail.com"],
  ["John", "johnnybravo@mail.com"],
  ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
  ["Mary", "mary@mail.com"]
]

Output:
[
  ["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"],
  ["John", "johnnybravo@mail.com"],
  ["Mary", "mary@mail.com"]
]
```

## Why DSU?

Emails ko nodes maano.

Same account ke emails union karo.

Phir email groups nikalo.

## Solution

```java
public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, Integer> emailToIndex = new HashMap<>();
    Map<String, String> emailToName = new HashMap<>();

    int index = 0;

    for (List<String> account : accounts) {
        String name = account.get(0);

        for (int i = 1; i < account.size(); i++) {
            String email = account.get(i);

            if (!emailToIndex.containsKey(email)) {
                emailToIndex.put(email, index++);
                emailToName.put(email, name);
            }
        }
    }

    DSU dsu = new DSU(index);

    for (List<String> account : accounts) {
        int firstEmailIndex =
            emailToIndex.get(account.get(1));

        for (int i = 2; i < account.size(); i++) {
            int emailIndex =
                emailToIndex.get(account.get(i));

            dsu.union(firstEmailIndex, emailIndex);
        }
    }

    Map<Integer, List<String>> merged = new HashMap<>();

    for (String email : emailToIndex.keySet()) {
        int root = dsu.find(emailToIndex.get(email));

        merged.computeIfAbsent(root, x -> new ArrayList<>()).add(email);
    }

    List<List<String>> ans = new ArrayList<>();

    for (List<String> emails : merged.values()) {
        Collections.sort(emails);

        String name = emailToName.get(emails.get(0));

        List<String> account = new ArrayList<>();

        account.add(name);
        account.addAll(emails);

        ans.add(account);
    }

    return ans;
}
```

## Complexity

Let:

```text
n = total emails
```

- Time: \(O(n \alpha(n) + n \log n)\)
- Space: \(O(n)\)

---

# 14. Example 4: Number of Operations to Make Network Connected

## Problem

`n` computers hain aur connections diye hain.

Minimum kitne cable operations chahiye sab computers connect karne ke liye?

```text
n = 4
connections = [,, ][0][1][2]

Output: 1
```

## Why DSU?

1. Extra/redundant connections count karo.
2. Connected components count karo.
3. Components connect karne ke liye `components - 1` cables chahiye.

## Solution

```java
public int makeConnected(int n, int[][] connections) {
    if (connections.length < n - 1) {
        return -1;
    }

    DSU dsu = new DSU(n);

    int components = n;

    for (int[] connection : connections) {
        if (dsu.union(connection, connection)) {
            components--;
        }
    }

    return components - 1;
}
```

## Dry Run

```text
n = 4
connections = [,, ][0][1][2]

Initial components = 4

Edge:[0][1]
Union successful
components = 3

Edge:[2][0]
Union successful
components = 2

Edge:[1][2]
Already connected
Redundant edge

Answer:
components - 1 = 1
```

## Complexity

- Time: \(O(E \alpha(V))\)
- Space: \(O(V)\)

---

# 15. DSU vs DFS / BFS

| Feature | DSU | DFS / BFS |
|---|---|---|
| Dynamic edges | Best | Rebuild required |
| Connected components | Excellent | Excellent |
| Cycle detection undirected | Excellent | Good |
| Shortest path | No | BFS possible |
| Space | \(O(V)\) | \(O(V + E)\) |
| Use case | Merging groups | Traversal |

### Rule

```text
Dynamic union/connectivity -> DSU
Static graph traversal -> DFS / BFS
```

---

# 16. Common Mistakes

## Mistake 1: Path Compression Bhoolna

Bina path compression:

```text
Find worst case O(n)
```

Correct:

```java
parent[x] = find(parent[x]);
```

---

## Mistake 2: Union by Rank/Size Bhoolna

Bina optimization ke tree skewed ho sakta hai.

---

## Mistake 3: Root Compare Karna Bhoolna

```java
if (find(x) == find(y)) {
    // already connected
}
```

---

## Mistake 4: Union Ke Baad Component Count Update Na Karna

```java
if (union(x, y)) {
    components--;
}
```

---

## Mistake 5: DSU Directed Graph Mein Use Karna

DSU mainly **undirected connectivity** ke liye best hai.

Directed graph dependencies ke liye:

```text
Topological Sort
DFS Cycle Detection
```

---

## Mistake 6: `find` Recursion Deep hone par Stack Overflow

Very large input mein iterative find better ho sakta hai.

---

# 17. DSU Decision Flow

```text
Problem padho
      |
      v
Kya dynamic connectivity / group merging chahiye?
      |
      +-- No --> DSU probably nahi
      |
      +-- Yes
          |
          v
Kya connected components chahiye?
      |
      +-- Yes --> DSU
      |
      +-- Kya cycle detection undirected graph mein chahiye?
              |
              +-- Yes --> DSU
              |
              +-- Kya accounts/users merge karne hain?
                      |
                      +-- Yes --> DSU + HashMap
```

---

# 18. Important Problems

## Easy

- Number of Provinces
- Find if Path Exists in Graph

## Medium

- Redundant Connection
- Accounts Merge
- Number of Operations to Make Network Connected
- Most Stones Removed with Same Row or Column
- Satisfiability of Equality Equations

## Hard

- Redundant Connection II
- Number of Islands II
- Making A Large Island
- Minimum Cost to Connect All Points

---

# 19. One-Line Recognition

> **Agar problem dynamic connectivity, group merging, connected components, friend circles, ya undirected cycle detection maang rahi hai — Union-Find / DSU try karo.**

DSU ka main power ye hai ki groups ko efficiently merge karta hai aur connectivity queries ko almost constant time mein answer karta hai.