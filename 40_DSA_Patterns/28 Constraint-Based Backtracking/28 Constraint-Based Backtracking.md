# 🚀 DSA Pattern 28 — Constraint-Based Backtracking

> **Goal:** Constraint-Based Backtracking ko product-based company interview level par samajhna — real-world intuition, CSP concepts, constraints, pruning, N-Queens, Sudoku, Word Search, Rat in a Maze, Graph Coloring, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Constraint-Based Backtracking Kya Hai?

Constraint-Based Backtracking ek technique hai jisme hum:

```text
1. Ek choice choose karte hain
2. Constraints check karte hain
3. Valid choice par recurse karte hain
4. Invalid/failed choice ko undo karte hain
```

Simple flow:

```text
Choose -> Validate -> Explore -> Undo
```

Example:

```text
N-Queens:
Har row mein ek queen rakho.
Koi do queens same column, diagonal, ya anti-diagonal par nahi honi chahiye.
```

Yahan har placement ke liye constraints check karni padti hain.

---

## 2. Constraint-Based Backtracking Kyun Use Karte Hain?

Main reasons:

1. **Valid configurations dhundhna**
2. **All solutions explore karna**
3. **Constraints satisfy karna**
4. **Invalid branches early prune karna**
5. **Puzzle solving**
6. **Scheduling / allocation problems**
7. **Constraint Satisfaction Problem — CSP**

### CSP — Constraint Satisfaction Problem

CSP ke 3 parts hote hain:

```text
1. Variables
2. Domains
3. Constraints
```

Example — Sudoku:

```text
Variables: 81 cells
Domain: 1 to 9
Constraints:
- Row mein duplicate na ho
- Column mein duplicate na ho
- 3x3 box mein duplicate na ho
```

### Core Intuition

> Har step par valid choice lo. Agar koi choice future mein fail ho, to backtrack karke next choice try karo.

---

## 3. Real-World Production Examples

| Real System | Constraint-Based Backtracking Use |
|---|---|
| Airline System | Crew scheduling |
| Railway System | Train platform allocation |
| Exam System | Timetable generation |
| University System | Class scheduling |
| Hospital System | Doctor shift scheduling |
| Cloud System | VM/resource placement |
| Manufacturing | Machine task allocation |
| Logistics | Vehicle route constraints |
| Game Development | Puzzle generation |
| Compiler | Register allocation |
| Network Design | Frequency/channel allocation |
| Ride Sharing | Driver-shift constraints |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “University mein classes, teachers, rooms aur time slots hain. Valid timetable generate kaise karoge?”

Answer:

```text
Variables: Classes
Domain: Available time slots and rooms
Constraints:
- Ek teacher ek time par ek class nahi le sakta
- Ek room ek time par ek class nahi le sakta
- Student batch overlap nahi hona chahiye

Backtracking se valid timetable generate karenge.
Invalid placement par backtrack karenge.
```

Ya:

> “Cloud platform mein VMs ko servers par assign karna hai. CPU, memory aur region constraints hain. Kaise approach karoge?”

Answer:

```text
Variables: VMs
Domain: Available servers
Constraints:
- Server capacity
- Region restriction
- Anti-affinity rules

Backtracking + pruning se valid assignment dhundhenge.
```

---

## 4. Kaise Pata Chalega Ki Constraint-Based Backtracking Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Constraint Backtracking |
|---|---|
| Place items under rules | Constraint Backtracking |
| N-Queens | Constraint Backtracking |
| Sudoku | Constraint Backtracking |
| Word search | Constraint Backtracking |
| Rat in a maze | Constraint Backtracking |
| Graph coloring | Constraint Backtracking |
| Generate valid timetable | Constraint Backtracking |
| Allocate resources | Constraint Backtracking |
| All valid configurations | Constraint Backtracking |

### Common Keywords

- Place
- Assign
- Valid
- Constraint
- Rules
- Board
- Grid
- Puzzle
- Schedule
- Allocate
- Coloring

### Mental Checklist

1. Kya choices leni hain?  
2. Kya constraints hain?  
3. Kya invalid choice ko undo karna padega?  
4. Kya all valid configurations chahiye?  
5. Kya early pruning possible hai?  

Agar 2–3 answers **yes** hain, to Constraint-Based Backtracking try karo.

---

# 5. Backtracking Ka Core Structure

```java
public void backtrack(State state) {
    if (isSolution(state)) {
        saveResult(state);
        return;
    }

    for (Choice choice : getChoices(state)) {
        if (isValid(state, choice)) {
            makeChoice(state, choice);

            backtrack(state);

            undoChoice(state, choice);
        }
    }
}
```

### Four Important Steps

```text
1. Choose
2. Check constraint
3. Recurse
4. Undo
```

---

# 6. Example 1: N-Queens

## Problem

`n x n` chessboard mein `n` queens place karo such that koi do queens attack na karein.

Queens same row, column, diagonal, ya anti-diagonal par nahi honi chahiye.

```text
n = 4

Output:
[
  [".Q..",
   "...Q",
   "Q...",
   "..Q."],

  ["..Q.",
   "Q...",
   "...Q",
   ".Q.."]
]
```

## Why Backtracking?

Har row mein ek queen place karni hai.

Har placement ke liye constraints check karni hain.

Invalid placement par backtrack karna hai.

## Constraint Check

Queen `(row, col)` place karne se pehle check karo:

```text
1. Same column
2. Left diagonal
3. Right diagonal
```

## Solution

```java
public List<List<String>> solveNQueens(int n) {
    List<List<String>> ans = new ArrayList<>();

    char[][] board = new char[n][n];

    for (char[] row : board) {
        Arrays.fill(row, '.');
    }

    backtrack(board, 0, ans);

    return ans;
}

private void backtrack(char[][] board,
                       int row,
                       List<List<String>> ans) {

    if (row == board.length) {
        ans.add(construct(board));
        return;
    }

    for (int col = 0; col < board.length; col++) {
        if (isValid(board, row, col)) {
            board[row][col] = 'Q';

            backtrack(board, row + 1, ans);

            board[row][col] = '.';
        }
    }
}

private boolean isValid(char[][] board, int row, int col) {
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') {
            return false;
        }
    }

    for (int i = row - 1, j = col - 1;
         i >= 0 && j >= 0;
         i--, j--) {

        if (board[i][j] == 'Q') {
            return false;
        }
    }

    for (int i = row - 1, j = col + 1;
         i >= 0 && j < board.length;
         i--, j++) {

        if (board[i][j] == 'Q') {
            return false;
        }
    }

    return true;
}

private List<String> construct(char[][] board) {
    List<String> result = new ArrayList<>();

    for (char[] row : board) {
        result.add(new String(row));
    }

    return result;
}
```

## Dry Run

```text
n = 4

Row 0:
Try col 0:
Place Q at (0,0)

Row 1:
Try col 0 -> same column, invalid
Try col 1 -> diagonal, invalid
Try col 2 -> valid

Board:
Q...
..Q.
....

Row 2:
Try col 0 -> diagonal conflict
Try col 1 -> column conflict
Try col 2 -> diagonal conflict
Try col 3 -> valid

Board:
Q...
..Q.
...Q
....

Row 3:
No valid column

Backtrack

Eventually:
.Q..
...Q
Q...
..Q.

Valid solution
```

## Complexity

- Time: Approximately \(O(n!)\)
- Space: \(O(n^2)\)

---

# 7. Optimized N-Queens — Sets Used

Instead of checking board every time, sets use karo:

```text
columns
diagonals
anti-diagonals
```

## Key Formula

```text
Same diagonal:
row - col same

Same anti-diagonal:
row + col same
```

## Optimized Solution

```java
public List<List<String>> solveNQueensOptimized(int n) {
    List<List<String>> ans = new ArrayList<>();

    char[][] board = new char[n][n];

    for (char[] row : board) {
        Arrays.fill(row, '.');
    }

    Set<Integer> columns = new HashSet<>();
    Set<Integer> diagonals = new HashSet<>();
    Set<Integer> antiDiagonals = new HashSet<>();

    backtrack(board, 0, columns, diagonals, antiDiagonals, ans);

    return ans;
}

private void backtrack(char[][] board,
                       int row,
                       Set<Integer> columns,
                       Set<Integer> diagonals,
                       Set<Integer> antiDiagonals,
                       List<List<String>> ans) {

    if (row == board.length) {
        ans.add(construct(board));
        return;
    }

    for (int col = 0; col < board.length; col++) {
        int diagonal = row - col;
        int antiDiagonal = row + col;

        if (columns.contains(col) ||
            diagonals.contains(diagonal) ||
            antiDiagonals.contains(antiDiagonal)) {

            continue;
        }

        board[row][col] = 'Q';

        columns.add(col);
        diagonals.add(diagonal);
        antiDiagonals.add(antiDiagonal);

        backtrack(board, row + 1, columns, diagonals, antiDiagonals, ans);

        board[row][col] = '.';

        columns.remove(col);
        diagonals.remove(diagonal);
        antiDiagonals.remove(antiDiagonal);
    }
}

private List<String> construct(char[][] board) {
    List<String> result = new ArrayList<>();

    for (char[] row : board) {
        result.add(new String(row));
    }

    return result;
}
```

## Complexity

- Time: Approximately \(O(n!)\)
- Space: \(O(n)\), excluding output

---

# 8. Example 2: Sudoku Solver

## Problem

9x9 Sudoku board solve karo.

Rules:

```text
1. Har row mein 1 se 9 ek baar
2. Har column mein 1 se 9 ek baar
3. Har 3x3 box mein 1 se 9 ek baar
```

## Why Backtracking?

Empty cell mein 1 se 9 try karo.

Valid number place karo.

Agar future invalid ho, backtrack karo.

## Solution

```java
public void solveSudoku(char[][] board) {
    solve(board);
}

private boolean solve(char[][] board) {
    for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {

            if (board[row][col] == '.') {

                for (char num = '1'; num <= '9'; num++) {

                    if (isValid(board, row, col, num)) {
                        board[row][col] = num;

                        if (solve(board)) {
                            return true;
                        }

                        board[row][col] = '.';
                    }
                }

                return false;
            }
        }
    }

    return true;
}

private boolean isValid(char[][] board, int row, int col, char num) {
    for (int i = 0; i < 9; i++) {
        if (board[row][i] == num) {
            return false;
        }

        if (board[i][col] == num) {
            return false;
        }
    }

    int boxRow = (row / 3) * 3;
    int boxCol = (col / 3) * 3;

    for (int i = boxRow; i < boxRow + 3; i++) {
        for (int j = boxCol; j < boxCol + 3; j++) {
            if (board[i][j] == num) {
                return false;
            }
        }
    }

    return true;
}
```

## Dry Run

```text
Empty cell found at (0,0)

Try 1:
Valid?
Row, column, box check
Place 1

Move to next empty cell

Agar future fail:
Remove 1
Try 2
```

## Complexity

- Time: Exponential, approximately \(O(9^m)\), where `m` = empty cells
- Space: \(O(81)\)

---

# 9. Example 3: Word Search

## Problem

Grid mein word dhundo.

Har cell ek baar use ho sakta hai.

Adjacent cells se move kar sakte ho.

```text
board = [
  ['A', 'B', 'C', 'E'],
  ['S', 'F', 'C', 'S'],
  ['A', 'D', 'E', 'E']
]

word = "ABCCED"

Output: true
```

## Constraints

```text
1. Cell character word character se match kare
2. Cell visited na ho
3. Grid ke bahar na jayein
```

## Solution

```java
public boolean exist(char[][] board, String word) {
    int rows = board.length;
    int cols = board.length;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (dfs(board, word, i, j, 0)) {
                return true;
            }
        }
    }

    return false;
}

private boolean dfs(char[][] board, String word, int i, int j, int index) {
    if (index == word.length()) {
        return true;
    }

    if (i < 0 || i >= board.length ||
        j < 0 || j >= board.length ||
        board[i][j] != word.charAt(index)) {

        return false;
    }

    char temp = board[i][j];

    board[i][j] = '#';

    boolean found =
        dfs(board, word, i + 1, j, index + 1) ||
        dfs(board, word, i - 1, j, index + 1) ||
        dfs(board, word, i, j + 1, index + 1) ||
        dfs(board, word, i, j - 1, index + 1);

    board[i][j] = temp;

    return found;
}
```

## Dry Run

```text
word = "ABCCED"

Start at (0,0):
A matches

Move right:
B matches

Move right:
C matches

Move down:
C matches

Move left:
E matches

Move down:
D matches

Word complete
Return true
```

## Complexity

- Time: \(O(m \times n \times 4^L)\)
- Space: \(O(L)\)

---

# 10. Example 4: Rat in a Maze

## Problem

Rat ko `(0,0)` se `(n-1,n-1)` tak pahunchana hai.

`1` = path allowed  
`0` = blocked

Possible paths return karo.

```text
maze = [
 ,[0][1]
 ,[1][0]
 ,[0][1]
[1][0]
]
```

## Constraints

```text
1. Sirf 1 wale cells par jao
2. Visited cell dobara use na karo
3. Grid bounds follow karo
```

## Solution

```java
public List<String> findPath(int[][] maze) {
    List<String> ans = new ArrayList<>();

    if (maze == 0) {
        return ans;
    }

    boolean[][] visited = new boolean[maze.length][maze.length];

    backtrack(maze, 0, 0, new StringBuilder(), visited, ans);

    return ans;
}

private void backtrack(int[][] maze,
                       int row,
                       int col,
                       StringBuilder path,
                       boolean[][] visited,
                       List<String> ans) {

    int n = maze.length;

    if (row == n - 1 && col == n - 1) {
        ans.add(path.toString());
        return;
    }

    int[] rowDir = {1, 0, 0, -1};
    int[] colDir = {0, -1, 1, 0};
    char[] move = {'D', 'L', 'R', 'U'};

    for (int i = 0; i < 4; i++) {
        int newRow = row + rowDir[i];
        int newCol = col + colDir[i];

        if (newRow >= 0 && newRow < n &&
            newCol >= 0 && newCol < n &&
            maze[newRow][newCol] == 1 &&
            !visited[newRow][newCol]) {

            visited[newRow][newCol] = true;
            path.append(move[i]);

            backtrack(maze, newRow, newCol, path, visited, ans);

            path.deleteCharAt(path.length() - 1);
            visited[newRow][newCol] = false;
        }
    }
}
```

## Complexity

- Time: \(O(4^{n^2})\) worst case
- Space: \(O(n^2)\)

---

# 11. Example 5: Graph Coloring

## Problem

Graph ko `m` colors se color karo such that adjacent nodes ka color different ho.

```text
Graph:
0 - 1
0 - 2
1 - 2

m = 3
```

## Constraints

```text
Adjacent nodes different color
```

## Solution

```java
public boolean graphColoring(int[][] graph, int m, int n) {
    int[] colors = new int[n];

    return backtrack(graph, colors, 0, m, n);
}

private boolean backtrack(int[][] graph,
                          int[] colors,
                          int node,
                          int m,
                          int n) {

    if (node == n) {
        return true;
    }

    for (int color = 1; color <= m; color++) {
        if (isValidColor(graph, colors, node, color)) {
            colors[node] = color;

            if (backtrack(graph, colors, node + 1, m, n)) {
                return true;
            }

            colors[node] = 0;
        }
    }

    return false;
}

private boolean isValidColor(int[][] graph,
                             int[] colors,
                             int node,
                             int color) {

    for (int neighbor = 0; neighbor < graph.length; neighbor++) {
        if (graph[node][neighbor] == 1 &&
            colors[neighbor] == color) {

            return false;
        }
    }

    return true;
}
```

## Production Example

```text
Mobile network towers ko frequency assign karni hai.

Constraint:
Adjacent towers ko same frequency nahi deni chahiye.

Ye graph coloring problem hai.
```

## Complexity

- Time: \(O(m^n)\)
- Space: \(O(n)\)

---

# 12. Pruning Techniques

## 1. Early Constraint Check

Choice lagu karne se pehle validity check karo.

```java
if (!isValid()) {
    continue;
}
```

## 2. Sorted Input Pruning

Agar candidates sorted hain:

```java
if (candidate > target) {
    break;
}
```

## 3. Remaining Possibility Pruning

Agar remaining choices se target possible nahi:

```text
Backtrack immediately
```

## 4. Memoization / Visited States

Same state dobara explore mat karo.

## 5. Constraint Propagation

Ek choice lagu hone ke baad future invalid choices eliminate karo.

Example Sudoku:

```text
Row, column, box ke available numbers track karo
```

---

# 13. Constraint Backtracking vs Simple Backtracking

| Feature | Simple Backtracking | Constraint-Based Backtracking |
|---|---|---|
| Main goal | Generate combinations | Valid configuration dhundhna |
| Constraints | Optional | Central |
| Pruning | Optional | Very important |
| Examples | Subsets, permutations | N-Queens, Sudoku |
| Validation | Usually at end | Usually at every step |

---

# 14. Common Mistakes

## Mistake 1: Constraint Check Bhoolna

N-Queens mein:

```java
if (!isValid(board, row, col)) {
    continue;
}
```

Ye zaroori hai.

---

## Mistake 2: Undo Choice Bhoolna

```java
board[row][col] = 'Q';
backtrack(...);
board[row][col] = '.'; // Required
```

---

## Mistake 3: Visited Mark/Unmark Bhoolna

Word Search mein:

```java
board[i][j] = '#';
dfs(...);
board[i][j] = temp;
```

---

## Mistake 4: Pruning Na Karna

Sudoku mein invalid numbers try karna wasteful hai.

```java
if (!isValid(board, row, col, num)) {
    continue;
}
```

---

## Mistake 5: All Solutions vs One Solution Confusion

N-Queens:

```text
All solutions chahiye
Backtrack after saving solution
```

Sudoku:

```text
Ek solution chahiye
First valid solution par true return karo
```

---

## Mistake 6: Diagonal Formula Galat Karna

```text
Same diagonal: row - col
Same anti-diagonal: row + col
```

---

# 15. Constraint Backtracking Decision Flow

```text
Problem padho
      |
      v
Kya choices + constraints hain?
      |
      +-- No --> Normal backtracking
      |
      +-- Yes
          |
          v
Kya board/grid puzzle hai?
      |
      +-- Yes --> N-Queens / Sudoku / Word Search
      |
      +-- Kya resource allocation hai?
              |
              +-- Yes --> Scheduling / Coloring / Assignment
              |
              +-- Kya all valid configurations chahiye?
                      |
                      +-- Yes --> Collect all solutions
                      |
                      +-- Ek solution chahiye?
                              |
                              +-- Yes --> Return true on first success
```

---

# 16. Important Problems

## Medium

- N-Queens II
- Word Search
- Combination Sum
- Combination Sum II
- Generate Parentheses
- Palindrome Partitioning
- Letter Combinations of a Phone Number

## Hard

- N-Queens
- Sudoku Solver
- Word Search II
- Rat in a Maze
- Graph Coloring
- Sudoku Solver
- Restore IP Addresses

---

# 17. One-Line Recognition

> **Agar problem items place/assign karne, rules satisfy karne, valid configurations dhundhne, ya puzzle solve karne ki baat kar rahi hai — Constraint-Based Backtracking try karo.**

Constraint-Based Backtracking ka main power ye hai ki har step par valid choices choose karta hai, invalid branches early prune karta hai, aur fail hone par undo karke next possibility explore karta hai.