# 🚀 DSA Pattern 16 — Stack for Parentheses

> **Goal:** Stack for Parentheses ko deeply samajhna — intuition, kyun use hota hai, kaise identify karein, valid parentheses, multiple bracket types, minimum removals, longest valid substring, templates, dry runs, common mistakes aur important problems.

---

## 1. Stack for Parentheses Kya Hai?

Parentheses problems mein hum brackets ke **opening aur closing pairs** ko track karte hain.

Stack isliye use hota hai kyunki brackets **nested order** mein close hote hain.

Example:

```text
"{[()]}"
```

Closing order:

```text
) -> pehle ] close hoga
] -> phir } close hoga
```

Last opened bracket ko sabse pehle close karna padta hai.

Yahi **LIFO — Last In First Out** behavior hai.

Isliye Stack perfect fit hai.

---

## 2. Stack Kyun Use Karte Hain?

Main reason: **brackets ka correct nesting order check karna.**

Example:

```text
s = "{[()]}"
```

Process:

```text
{ -> push
[ -> push
( -> push
) -> pop (
] -> pop ]
} -> pop }
```

End mein stack empty -> valid.

### Wrong Example

```text
s = "([)]"
```

Process:

```text
( -> push
[ -> push
) -> top is [, expected )
Invalid
```

### Core Intuition

> Jab closing bracket aaye, to sabse recently opened bracket hi close hona chahiye.

---

## 3. Kaise Pata Chalega Ki Stack for Parentheses Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Stack for Parentheses |
|---|---|
| Valid parentheses check | Stack |
| Multiple bracket types `()[]{}` | Stack |
| Minimum remove to make valid | Stack |
| Longest valid parentheses | Stack |
| Generate valid parentheses | Backtracking + Stack concept |
| Nested structure validation | Stack |
| Expression parsing | Stack |

### Common Keywords

- Valid parentheses
- Brackets
- Nested
- Minimum removals
- Longest valid
- Matching
- Expression

### Mental Checklist

1. Kya problem brackets ke baare mein hai?  
2. Kya opening/closing order matter karta hai?  
3. Kya nested structure check karna hai?  
4. Kya last opened bracket pehle close hona chahiye?  
5. Kya invalid brackets remove/fix karne hain?  

Agar 2–3 answers **yes** hain, to Stack for Parentheses try karo.

---

# 4. Bracket Matching Rules

## Valid Parentheses Rules

1. Har opening bracket ka ek matching closing bracket hona chahiye.
2. Closing bracket apne most recent unmatched opening bracket ko close kare.
3. End mein koi unmatched opening bracket na bache.

### Valid Examples

```text
()
()[]{}
{[()]}
```

### Invalid Examples

```text
(]
([)]
(((
)))
```

---

# 5. Template — Valid Parentheses

```java
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char c : s.toCharArray()) {
        if (c == '(') {
            stack.push(')');
        } else if (c == '[') {
            stack.push(']');
        } else if (c == '{') {
            stack.push('}');
        } else {
            if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
    }

    return stack.isEmpty();
}
```

### Trick

Opening bracket push karte waqt expected closing bracket push karo.

```text
( -> push )
[ -> push ]
{ -> push }
```

Isse closing bracket aane par direct compare ho jata hai.

---

# 6. Example 1: Valid Parentheses

## Problem

String mein sirf `()[]{}` characters hain. Check karo ki string valid hai ya nahi.

```text
s = "()[]{}"
Output: true
```

```text
s = "([)]"
Output: false
```

```text
s = "(]"
Output: false
```

## Brute Force Idea

Baar-baar `()` , `[]`, `{}` replace karo.

```text
"([)]"
-> "([])"? No

Ye approach messy hai.
```

## Why Stack?

Brackets nested hote hain.

Last opened bracket ko pehle close karna hota hai.

## Stack Solution

```java
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char c : s.toCharArray()) {
        if (c == '(') {
            stack.push(')');
        } else if (c == '[') {
            stack.push(']');
        } else if (c == '{') {
            stack.push('}');
        } else {
            if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
    }

    return stack.isEmpty();
}
```

## Dry Run

### Example 1

```text
s = "()[]{}"

c = (
push )
stack = [)]

c = )
pop )
match

c = [
push ]
stack = []]

c = ]
pop ]
match

c = {
push }
stack = [}]

c = }
pop }
match

stack empty
Answer = true
```

### Example 2

```text
s = "([)]"

c = (
push )
stack = [)]

c = [
push ]
stack = [), ]]

c = )
top = ]
expected )
mismatch
return false
```

### Example 3

```text
s = "(]"

c = (
push )
stack = [)]

c = ]
top = )
expected ]
mismatch
return false
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 7. Example 2: Minimum Remove to Make Valid Parentheses

## Problem

String mein lowercase letters aur `(` `)` brackets hain.

Minimum brackets remove karke string valid banao.

```text
s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
```

```text
s = "a)b(c)d"
Output: "ab(c)d"
```

## Why Stack?

Hum unmatched opening brackets aur unmatched closing brackets track karte hain.

## Approach

1. Traverse string.
2. `(` aaye to index stack mein push karo.
3. `)` aaye:
   - Agar stack non-empty hai, to matching `(` mil gaya -> pop.
   - Agar stack empty hai, to ye `)` invalid hai -> remove mark karo.
4. End mein jo `(` stack mein bache, wo bhi invalid hain -> remove mark karo.

## Solution

```java
public String minRemoveToMakeValid(String s) {
    StringBuilder sb = new StringBuilder(s);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (c == '(') {
            stack.push(i);
        } else if (c == ')') {
            if (!stack.isEmpty()) {
                stack.pop();
            } else {
                sb.setCharAt(i, '#');
            }
        }
    }

    while (!stack.isEmpty()) {
        sb.setCharAt(stack.pop(), '#');
    }

    StringBuilder result = new StringBuilder();

    for (char c : sb.toString().toCharArray()) {
        if (c != '#') {
            result.append(c);
        }
    }

    return result.toString();
}
```

## Dry Run

```text
s = "lee(t(c)o)de)"

Index:
0 l
1 e
2 e
3 (
4 t
5 (
6 c
7 )
8 o
9 )
10 d
11 e
12 )

i = 3:
push 3
stack = 

i = 5:
push 5
stack =[3][5]

i = 7:
pop 5
stack = 

i = 9:
pop 3
stack = []

i = 12:
stack empty
mark index 12 as #

Remove #:
"lee(t(c)o)de"

Answer = "lee(t(c)o)de"
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 8. Example 3: Longest Valid Parentheses

## Problem

Sirf `(` aur `)` wali string mein longest valid parentheses substring ki length nikalo.

```text
s = "(()"
Output: 2
Explanation: "()"
```

```text
s = ")()())"
Output: 4
Explanation: "()()"
```

## Why Stack?

Stack valid substring ke boundary indices track karta hai.

## Approach

1. Stack mein `-1` push karo as base index.
2. `(` aaye to index push karo.
3. `)` aaye:
   - Pop karo.
   - Agar stack empty ho gaya, to current index push karo as new base.
   - Warna current valid length:

```text
length = i - stack.peek()
```

## Solution

```java
public int longestValidParentheses(String s) {
    Deque<Integer> stack = new ArrayDeque<>();

    stack.push(-1);

    int maxLength = 0;

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (c == '(') {
            stack.push(i);
        } else {
            stack.pop();

            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                maxLength = Math.max(maxLength, i - stack.peek());
            }
        }
    }

    return maxLength;
}
```

## Dry Run

```text
s = ")()())"

stack = [-1]

i = 0:
c = )
pop -1
stack empty
push 0
stack = 

i = 1:
c = (
push 1
stack =[0][1]

i = 2:
c = )
pop 1
stack = 
length = 2 - 0 = 2
maxLength = 2

i = 3:
c = (
push 3
stack =[0][3]

i = 4:
c = )
pop 3
stack = 
length = 4 - 0 = 4
maxLength = 4

i = 5:
c = )
pop 0
stack empty
push 5
stack = 

Answer = 4
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 9. Example 4: Minimum Add to Make Parentheses Valid

## Problem

Minimum kitne brackets add karne padenge taaki string valid ban jaye.

```text
s = "())"
Output: 1
```

```text
s = "((("
Output: 3
```

```text
s = "()))(("
Output: 4
```

## Why Counting Instead of Full Stack?

Sirf `(` aur `)` hain.

Humein sirf ye track karna hai:

```text
openCount = abhi tak unmatched opening brackets
closeCount = unmatched closing brackets
```

## Solution

```java
public int minAddToMakeValid(String s) {
    int openCount = 0;
    int closeCount = 0;

    for (char c : s.toCharArray()) {
        if (c == '(') {
            openCount++;
        } else {
            if (openCount > 0) {
                openCount--;
            } else {
                closeCount++;
            }
        }
    }

    return openCount + closeCount;
}
```

## Dry Run

```text
s = "()))(("

c = (
openCount = 1

c = )
openCount = 0

c = )
openCount = 0
closeCount = 1

c = )
openCount = 0
closeCount = 2

c = (
openCount = 1

c = (
openCount = 2

Answer = openCount + closeCount
       = 2 + 2
       = 4
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 10. Example 5: Generate Parentheses

## Problem

`n` pairs of parentheses ke saare valid combinations generate karo.

```text
n = 3

Output:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

## Why Backtracking + Stack Concept?

Hum track karte hain:

```text
open = kitne ( use hue
close = kitne ) use hue
```

Rules:

```text
open < n -> ( add kar sakte hain
close < open -> ) add kar sakte hain
```

## Solution

```java
public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<>();

    backtrack(ans, new StringBuilder(), 0, 0, n);

    return ans;
}

private void backtrack(List<String> ans,
                       StringBuilder current,
                       int open,
                       int close,
                       int n) {

    if (current.length() == 2 * n) {
        ans.add(current.toString());
        return;
    }

    if (open < n) {
        current.append('(');
        backtrack(ans, current, open + 1, close, n);
        current.deleteCharAt(current.length() - 1);
    }

    if (close < open) {
        current.append(')');
        backtrack(ans, current, open, close + 1, n);
        current.deleteCharAt(current.length() - 1);
    }
}
```

## Dry Run

```text
n = 2

Start:
current = ""
open = 0
close = 0

Add (
current = "("
open = 1

Add (
current = "(("
open = 2

Add )
current = "(()"
close = 1

Add )
current = "(())"
close = 2
Answer added

Backtrack:
current = "("

Add )
current = "()"
close = 1

Add (
current = "()("
open = 2

Add )
current = "()()"
Answer added
```

## Complexity

- Time: \(O(4^n / \sqrt{n})\)
- Space: \(O(n)\)

---

# 11. Stack vs Simple Counter

| Problem | Stack | Counter |
|---|---|---|
| Only `()` brackets | Counter possible | `openCount`, `closeCount` |
| Multiple bracket types | Stack required | Counter difficult |
| Need indices of invalid brackets | Stack required | Not enough |
| Longest valid substring | Stack required | Counter difficult |
| Minimum additions | Counter possible | `open + close` |
| Expression parsing | Stack required | Counter difficult |

### Rule

```text
Sirf () aur validity/count -> Counter possible
Multiple brackets / indices / nesting -> Stack
```

---

# 12. Common Mistakes

## Mistake 1: Closing Bracket Par Empty Stack Check Na Karna

Galat:

```java
if (stack.pop() != c)
```

Agar stack empty hai, to exception aa jayega.

Correct:

```java
if (stack.isEmpty() || stack.pop() != c) {
    return false;
}
```

---

## Mistake 2: End Mein Stack Empty Check Bhoolna

```text
s = "((("
```

Process ke baad stack empty nahi hoga.

Isliye:

```java
return stack.isEmpty();
```

---

## Mistake 3: Expected Closing Bracket Push Na Karna

Direct opening bracket push karna bhi possible hai, but expected closing push karna comparison easy banata hai.

```java
if (c == '(') {
    stack.push(')');
}
```

---

## Mistake 4: Longest Valid Parentheses Mein Base Index Bhoolna

Stack mein initially `-1` push karna zaroori hai:

```java
stack.push(-1);
```

Ye valid substring ki left boundary represent karta hai.

---

## Mistake 5: Minimum Remove Mein Invalid Opening Brackets Handle Na Karna

End mein stack mein jo `(` bache hain, wo bhi invalid hain:

```java
while (!stack.isEmpty()) {
    sb.setCharAt(stack.pop(), '#');
}
```

---

# 13. Stack for Parentheses Decision Flow

```text
Problem padho
      |
      v
Kya brackets / nesting chahiye?
      |
      +-- No --> Stack for Parentheses probably nahi
      |
      +-- Yes
          |
          v
Kya multiple bracket types hain?
      |
      +-- Yes --> Stack with expected closing brackets
      |
      +-- Sirf () hain?
              |
              +-- Validity / count -> Counter possible
              |
              +-- Indices / longest valid -> Stack
              |
              +-- Generate all valid -> Backtracking
```

---

# 14. Important Problems

## Easy

- Valid Parentheses
- Minimum Add to Make Parentheses Valid
- Remove Outermost Parentheses

## Medium

- Minimum Remove to Make Valid Parentheses
- Generate Parentheses
- Simplify Path
- Decode String
- Asteroid Collision

## Hard

- Longest Valid Parentheses
- Basic Calculator
- Basic Calculator II
- Remove Invalid Parentheses

---

# 15. One-Line Recognition

> **Agar problem brackets, nesting, matching, invalid removal, ya valid substring ki baat kar rahi hai — Stack for Parentheses try karo.**

Stack ka main power ye hai ki wo **last opened bracket** ko track karta hai, aur closing bracket aane par usi ko pop karke correct nesting validate karta hai.